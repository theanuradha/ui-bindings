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
package com.rcpcompany.uibindings.internal.cellEditors;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;

import com.rcpcompany.uibindings.Constants;
import com.rcpcompany.uibindings.IBindingContext;
import com.rcpcompany.uibindings.IManager;
import com.rcpcompany.uibindings.internal.Activator;
import com.rcpcompany.utils.logging.LogUtils;

/**
 * Special edition of {@link TextCellEditor}.
 * 
 * @author Tonny Madsen, The RCP Company
 */
public class MyTextCellEditor extends TextCellEditor {
	/**
	 * The context of the cell editor.
	 */
	protected final IBindingContext myContext;
	// Used to signal that the text has just been replaced and should not be selected
	private boolean justReplacedText = false;

	/**
	 * The text widget itself.
	 */
	private Text myText;
	private IHandlerActivation myActiveUndoHandler;
	private IHandlerService myHandlerService;
	IHandler undoHandler;

	/**
	 * Constructs and returns a new cell editors for Text.
	 * 
	 * @param parent the parent composite
	 * @param context the column of the cell editor
	 */
	public MyTextCellEditor(Composite parent, IBindingContext context) {
		super(parent, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
		myContext = context;
		undoHandler = new AbstractHandler() {
			@Override
			public Object execute(ExecutionEvent event) {
				if (Activator.getDefault().TRACE_HANDLERS) {
					LogUtils.debug(this, "");
				}
				fireCancelEditor();
				return null;
			}
		};
	}

	@Override
	public void activate(ColumnViewerEditorActivationEvent activationEvent) {
		super.activate(activationEvent);
		myText = (Text) getControl();

		/*
		 * If the editor was activated using a key - see Manager.isEditCellAnyKey() - then replace
		 * the content and consume the event
		 */
		if (activationEvent.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED
				&& !Character.isISOControl(activationEvent.character)) {
			final String s = new String(new char[] { activationEvent.character });
			myText.setText(s);
			justReplacedText = true;
			if (activationEvent.sourceEvent instanceof KeyEvent) {
				final KeyEvent e = (KeyEvent) activationEvent.sourceEvent;
				e.doit = false;
			}
		}

		final String commandID = IManager.Factory.getManager().getCommandIDs()
				.get(IWorkbenchCommandConstants.EDIT_UNDO);
		if (Activator.getDefault().TRACE_HANDLERS) {
			LogUtils.debug(undoHandler, "activating '" + commandID + "' handler");
		}
		myHandlerService = (IHandlerService) myContext.getServiceLocator().getService(IHandlerService.class);
		myActiveUndoHandler = myHandlerService.activateHandler(commandID, undoHandler, Constants.TRUE_EXPRESSION);
	}

	@Override
	public void deactivate() {
		super.deactivate();
		if (myActiveUndoHandler != null) {
			if (Activator.getDefault().TRACE_HANDLERS) {
				final String commandID = IManager.Factory.getManager().getCommandIDs()
						.get(IWorkbenchCommandConstants.EDIT_UNDO);
				LogUtils.debug(undoHandler, "deactivating '" + commandID + "' handler");
			}
			myHandlerService.deactivateHandler(myActiveUndoHandler);
			myActiveUndoHandler = null;
		}
	}

	@Override
	protected void doSetFocus() {
		super.doSetFocus();
		if (justReplacedText) {
			final Text text = (Text) getControl();
			if (text != null) {
				text.setSelection(text.getText().length());
			}
			justReplacedText = false;
		}
	}
}
