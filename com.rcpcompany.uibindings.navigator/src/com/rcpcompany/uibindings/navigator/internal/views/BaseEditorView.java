/*******************************************************************************
 * Copyright (c) 2006-2013 The RCP Company and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     The RCP Company - initial API and implementation
 *******************************************************************************/
package com.rcpcompany.uibindings.navigator.internal.views;

import java.util.List;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.eclipse.ui.part.ViewPart;

import com.rcpcompany.uibindings.IManager;
import com.rcpcompany.uibindings.model.utils.BasicUtils;
import com.rcpcompany.uibindings.navigator.IEditorPart;
import com.rcpcompany.uibindings.navigator.IEditorPartContext;
import com.rcpcompany.uibindings.navigator.IEditorPartDescriptor;
import com.rcpcompany.uibindings.navigator.IEditorPartFactory;
import com.rcpcompany.uibindings.navigator.IEditorPartView;
import com.rcpcompany.uibindings.navigator.INavigatorManager;
import com.rcpcompany.uibindings.navigator.internal.Activator;
import com.rcpcompany.uibindings.navigator.internal.NavigatorConstants;
import com.rcpcompany.uibindings.navigator.internal.handlers.SelectEditorPartMenuContributor;
import com.rcpcompany.uibindings.utils.IBindingObjectInformation;
import com.rcpcompany.uibindings.utils.IGlobalNavigationManager.IGetSelectionTarget;
import com.rcpcompany.utils.logging.LogUtils;
import com.rcpcompany.utils.selection.SelectionUtils;

/**
 * The base view for editor parts.
 * 
 * @author Tonny Madsen, The RCP Company
 */
public class BaseEditorView extends ViewPart implements ISetSelectionTarget, IGetSelectionTarget, IEditorPartView {
	/**
	 * The parent Composite for the current editor part - if any
	 */
	/* package */Composite myParent;

	/**
	 * The parent Composite from {@link IViewPart#createPartControl(Composite)}.
	 */
	/* package */Composite myViewPartParent;

	/**
	 * Whether this editor is currently pinned.
	 * <p>
	 * If the editor is pinned, it will not react to selection changes and a new editor is created
	 * instead of reusing an existing editor.
	 */
	/* package */boolean myIsPinned = INavigatorManager.Factory.getManager().isPinEditorByDefault();

	/**
	 * The current editor part descriptor.
	 */
	private/* package */IEditorPartDescriptor myCurrentDescriptor;

	/**
	 * The current editor part for this editor.
	 */
	/* package */IEditorPart myCurrentEditorPart = null;

	/**
	 * The current base observable value of the editor.
	 * <p>
	 * The type of the value is based on the model type of the current editor descriptor.
	 */
	/* package */IObservableValue myCurrentValue;

	/**
	 * The factory context used in {@link IEditorPartFactory}.
	 */
	private final IEditorPartContext myFactoryContext = new IEditorPartContext() {
		@Override
		public IWorkbenchPart getWorkbenchPart() {
			return BaseEditorView.this;
		}

		@Override
		public Composite getParent() {
			return myParent;
		};

		@Override
		public IObservableValue getCurrentValue() {
			return myCurrentValue;
		}

		@Override
		public IEditorPartDescriptor getDescriptor() {
			return getCurrentDescriptor();
		}
	};

	/**
	 * Constructs and returns a editor container.
	 */
	public BaseEditorView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		myViewPartParent = parent;

		// final ISelectionService ss = getSite().getWorkbenchWindow().getSelectionService();
		// ss.addPostSelectionListener(mySelectionListener);
		// selectReveal(ss.getSelection());
	}

	@Override
	public void dispose() {
		cleanEditorPart();
		// final ISelectionService ss = getSite().getWorkbenchWindow().getSelectionService();
		// ss.removePostSelectionListener(mySelectionListener);
		super.dispose();
	}

	@Override
	public void activateView() {
		getSite().getPage().activate(this);
	}

	@Override
	public void setFocus() {
		myViewPartParent.setFocus();
	}

	@Override
	public void selectReveal(ISelection selection) {
		if (Activator.getDefault().TRACE_EDITOR_PARTS_LIFECYCLE) {
			LogUtils.debug(this, IBindingObjectInformation.Factory.getLongName(selection));
		}
		if (myCurrentEditorPart != null && isPinned()) return;
		final List<EObject> list = SelectionUtils.computeSelection(selection, EObject.class);
		if (list.isEmpty()) return;

		setCurrentObject(list.get(0));
	}

	@Override
	public ISelection getCurrentSelection() {
		if (myCurrentValue == null) return StructuredSelection.EMPTY;

		return new StructuredSelection(myCurrentValue.getValue());
	}

	@Override
	public EObject getCurrentObject() {
		if (myCurrentValue != null) return (EObject) myCurrentValue.getValue();
		return null;
	}

	/**
	 * Updates the editor when the current object changes
	 * 
	 * @param obj the new current object of the editor
	 */
	@Override
	public void setCurrentObject(final EObject obj) {
		final IEditorPartDescriptor desc = INavigatorManager.Factory.getManager().getEditorPartDescriptor(obj);
		if (Activator.getDefault().TRACE_EDITOR_PARTS_LIFECYCLE) {
			LogUtils.debug(this, "Descriptor found: " + obj + "\n-> " + desc);
		}
		/*
		 * Iff we have the same descriptor and the current editor can accept changes in the object,
		 * then just change the object without re-creating the editor part... Otherwise go the long
		 * route and first dispose and then re-create the editor part.
		 */
		if (desc == myCurrentDescriptor
				&& ((myCurrentEditorPart != null && myCurrentEditorPart.canAcceptObjectChanges()) || (myCurrentValue != null && BasicUtils
						.equals(obj, myCurrentValue.getValue())))) {
			/*
			 * The editor part itself did not change... just update the observable value.
			 */
			if (myCurrentValue != null) {
				if (Activator.getDefault().TRACE_EDITOR_PARTS_LIFECYCLE) {
					LogUtils.debug(this, "Editor part value changed to " + obj);
				}
				BusyIndicator.showWhile(myParent.getDisplay(), new Runnable() {
					public void run() {
						myCurrentValue.setValue(obj);
					}
				});
				final IBindingObjectInformation info = IBindingObjectInformation.Factory.createObjectInformation(
						(EObject) myCurrentValue.getValue(), null);
				setPartName(info.getName() + ": " + getCurrentDescriptor().getName());
			}
			return;
		}

		/*
		 * No new descriptor....
		 */
		if (desc == null) return;

		if (Activator.getDefault().TRACE_EDITOR_PARTS_LIFECYCLE) {
			LogUtils.debug(this, "Editor part description\n" + myCurrentDescriptor + "\n-> " + desc);
		}

		cleanEditorPart();

		/*
		 * - create the observable value for the editor part based on the type of the editor
		 * descriptor
		 */
		myCurrentValue = new WritableValue(obj, obj.eClass());
		IManager.Factory.getManager().startMonitorObservableDispose(myCurrentValue, this);
		setCurrentDescriptor(desc);

		/*
		 * - create the editor part itself
		 * 
		 * If it fails, the clean up again...
		 */
		final IEditorPartFactory factory = desc.getFactory().getObject();
		try {
			if (factory != null) {
				myParent = new Composite(myViewPartParent, SWT.NONE);
				myParent.setLayout(new FillLayout());
				try {
					BusyIndicator.showWhile(myParent.getDisplay(), new Runnable() {
						public void run() {
							myCurrentEditorPart = factory.createEditorPart(myFactoryContext);
						}
					});
				} catch (final Exception ex) {
					LogUtils.error(factory, "Error detected during editor creation", ex);
				}
				myViewPartParent.layout(true);
			}
			if (getCurrentDescriptor() == null) {
				LogUtils.error(factory, "Editor Part Factory returned null");
			}
		} catch (final Exception ex) {
			LogUtils.error(factory, ex);
		}

		if (myCurrentEditorPart == null) {
			if (Activator.getDefault().TRACE_EDITOR_PARTS_LIFECYCLE) {
				LogUtils.debug(this, "Failed");
			}
			cleanEditorPart();
			setPartName("");
			setTitleImage(null);
			return;
		}

		/*
		 * - Update the view part info
		 */
		final IBindingObjectInformation info = IBindingObjectInformation.Factory.createObjectInformation(
				(EObject) myCurrentValue.getValue(), null);
		setPartName(info.getName() + ": " + getCurrentDescriptor().getName());
		Image image = null;
		if (image == null && getCurrentDescriptor().getImage() != null) {
			getCurrentDescriptor().getImage().getImage();
		}
		if (image == null) {
			image = info.getImage();
		}
		setTitleImage(image);
	}

	/**
	 * 
	 */
	private void cleanEditorPart() {
		/*
		 * - Clean up after the previous editor part
		 */
		if (myCurrentEditorPart != null) {
			myCurrentEditorPart.dispose();
			if (!myViewPartParent.isDisposed()) {
				for (final Control c : myViewPartParent.getChildren()) {
					c.dispose();
				}
			}
		}
		if (myCurrentValue != null && !myCurrentValue.isDisposed()) {
			IManager.Factory.getManager().stopMonitorObservableDispose(myCurrentValue);
			myCurrentValue.dispose();
		}
		myCurrentValue = null;
		myCurrentEditorPart = null;
		setCurrentDescriptor(null);
	}

	@Override
	public void setPinned(boolean pinned) {
		myIsPinned = pinned;
	}

	@Override
	public boolean isPinned() {
		return myIsPinned;
	}

	/**
	 * Sets the current descriptor.
	 * 
	 * @param desciptor the current descriptor to set
	 */
	public void setCurrentDescriptor(IEditorPartDescriptor desciptor) {
		myCurrentDescriptor = desciptor;

		/*
		 * Make all select editor parts update themselves...
		 */
		final ICommandService commandService = (ICommandService) getSite().getService(ICommandService.class);
		commandService.refreshElements(NavigatorConstants.SELECT_EDITOR_PART_COMMAND, null);
	}

	/**
	 * Returns the current descriptor.
	 * 
	 * @return the current descriptor
	 */
	@Override
	public IEditorPartDescriptor getCurrentDescriptor() {
		return myCurrentDescriptor;
	}

	/**
	 * Selection lister that simply updated the current selection of the editor.
	 */
	private final ISelectionListener mySelectionListener = new ISelectionListener() {
		@Override
		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			/*
			 * The changes that results from this part is ignoreed.
			 */
			if (part == BaseEditorView.this) return;
			selectReveal(selection);
		}
	};

	private SelectEditorPartMenuContributor mySelectEditorPartFactoryItem;
}
