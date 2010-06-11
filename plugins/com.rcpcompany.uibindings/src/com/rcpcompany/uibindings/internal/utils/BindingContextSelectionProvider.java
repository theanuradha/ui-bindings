package com.rcpcompany.uibindings.internal.utils;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPartSite;

import com.rcpcompany.uibindings.IBinding;
import com.rcpcompany.uibindings.IBindingContext;
import com.rcpcompany.uibindings.IDisposable;
import com.rcpcompany.uibindings.IValueBinding;
import com.rcpcompany.uibindings.IViewerBinding;
import com.rcpcompany.uibindings.utils.IBindingContextSelectionProvider;
import com.rcpcompany.uibindings.utils.IFilteringTableAdapter;
import com.rcpcompany.utils.logging.LogUtils;

/**
 * Implementation of {@link IBindingContextSelectionProvider}.
 * 
 * @author Tonny Madsen, The RCP Company
 */
public class BindingContextSelectionProvider extends AbstractContextMonitor implements
		IBindingContextSelectionProvider, ISelectionProvider, IDisposable {

	/**
	 * Factory method for {@link IBindingContextSelectionProvider} that checks a provider does not already exist.
	 * 
	 * @param context the context
	 * @param site the site
	 * @return the new selection provider
	 */
	public static IBindingContextSelectionProvider adapt(IBindingContext context, IWorkbenchPartSite site) {
		final BindingContextSelectionProvider provider = context.getService(BindingContextSelectionProvider.class);
		if (provider != null) {
			return provider;
		}
		return new BindingContextSelectionProvider(context, site);
	}

	/**
	 * Constructs and initializes a new selection provider...
	 * 
	 * @param context the binding
	 * @param site the workbench site
	 */
	public BindingContextSelectionProvider(IBindingContext context, IWorkbenchPartSite site) {
		super(context);
		mySite = site;

		if (context.getTop() == null) {
			final IllegalStateException ex = new IllegalStateException("No top component set.");
			LogUtils.error(context, null, ex);
			throw ex;
		}

		init();
	}

	/**
	 * Initializes this filter.
	 */
	@Override
	public void init() {
		mySite.setSelectionProvider(this);

		Display.getCurrent().addFilter(SWT.FocusIn, myFocusListener);
		createContextMenu();

		super.init();
	}

	/**
	 * Map of all registered controls along with their providers.
	 */
	private final Map<Control, ISelectionProvider> myProviders = new HashMap<Control, ISelectionProvider>();

	private final ISelectionChangedListener mySelectionChangedListener = new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			checkSelection();
		}
	};

	protected final ISelection myEmptySelection = new ISelection() {
		@Override
		public boolean isEmpty() {
			return true;
		}
	};

	public void addControl(Control control, ISelectionProvider provider) {
		myProviders.put(control, provider);
		control.setMenu(myMenuManager.getMenu());
		checkFocus();
	}

	public void removeControl(Control control) {
		myProviders.remove(control);
		control.setMenu(null);
		checkFocus();
	}

	public void addControl(Control control, IObservableValue selection) {
		addControl(control, new ObservableValueSelectionProvider(selection));
	}

	public void addViewer(Viewer viewer) {
		addControl(viewer.getControl(), viewer);
	}

	/**
	 * Check if the current focus control has a registered selection provider.
	 * <p>
	 * If so, a {@link ISelectionChangedListener} is added to the control.
	 * <p>
	 * Called whenever the focus has changed or the registered selection providers change
	 */
	protected void checkFocus() {
		final ISelectionProvider provider = myProviders.get(myFocusControl);
		if (provider == myCurrentProvider) {
			return;
		}

		if (myCurrentProvider != null) {
			myCurrentProvider.removeSelectionChangedListener(mySelectionChangedListener);
		}
		myCurrentProvider = provider;
		if (myCurrentProvider != null) {
			myCurrentProvider.addSelectionChangedListener(mySelectionChangedListener);
			checkSelection();
		}
	}

	protected void checkSelection() {
		ISelection selection = myCurrentProvider.getSelection();

		if (selection == null) {
			selection = myEmptySelection;
		}
		if (selection == myCurrentSelection) {
			return;
		}
		myCurrentSelection = selection;
		fireSelectionChanged(new SelectionChangedEvent(myCurrentProvider, myCurrentSelection));
	}

	/**
	 * The workbench site where the menu is registered
	 */
	private final IWorkbenchPartSite mySite;

	/**
	 * The current selection provider.
	 */
	protected ISelectionProvider myCurrentProvider = null;

	/**
	 * The current selection.
	 */
	private ISelection myCurrentSelection = null;

	/**
	 * The menu
	 */
	private final MenuManager myMenuManager = new MenuManager();

	/**
	 * List of selection change listeners (element type: <code>ISelectionChangedListener</code>).
	 * 
	 * @see #fireSelectionChanged
	 */
	private final ListenerList selectionChangedListeners = new ListenerList();

	protected Control myFocusControl;

	private final Listener myFocusListener = new Listener() {
		@Override
		public void handleEvent(Event event) {
			if (event.type == SWT.FocusIn) {
				myFocusControl = (Control) event.widget;
				checkFocus();
			}
		}
	};

	/**
	 * Creates the context menu
	 */
	private void createContextMenu() {
		mySite.registerContextMenu(myMenuManager, this);
		myMenuManager.add(new Separator("open"));
		myMenuManager.add(new Separator("undo"));
		myMenuManager.add(new Separator("new"));
		myMenuManager.add(new Separator("delete"));
		myMenuManager.add(new Separator("select"));
		myMenuManager.add(new Separator("navigation"));
		myMenuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		myMenuManager.createContextMenu(getContext().getTop());
		getContext().getTop().setMenu(myMenuManager.getMenu());
	}

	/**
	 * Removes the filtering functionality again.
	 */
	@Override
	public void dispose() {
		myFocusControl = null;
		checkFocus();
		// mySite.unregisterContextMenu(myMenuManager, this);

		mySite.setSelectionProvider(null);
		Display.getCurrent().removeFilter(SWT.FocusIn, myFocusListener);

		super.dispose();
	}

	@Override
	protected void bindingAdded(IBinding binding) {
		if (binding instanceof IValueBinding) {
			final IValueBinding vb = (IValueBinding) binding;
			final Control control = vb.getControl();
			if (control == null) {
				return;
			}
			if (vb.getModelObservableValue() != null) {
				addControl(control, vb.getModelObservableValue());
				return;
			}
		} else if (binding instanceof IViewerBinding) {
			final IViewerBinding vb = (IViewerBinding) binding;
			addViewer(vb.getViewer());
			final IFilteringTableAdapter filtering = vb.getService(IFilteringTableAdapter.class);
			if (filtering != null) {
				addControl(filtering.getText(), vb.getViewer());
			}
			return;
		}
	}

	@Override
	protected void bindingRemoved(IBinding binding) {
		if (binding instanceof IValueBinding) {
			final IValueBinding vb = (IValueBinding) binding;
			final Control control = vb.getControl();
			if (control == null) {
				return;
			}
			removeControl(control);
			return;
		} else if (binding instanceof IViewerBinding) {
			final IViewerBinding vb = (IViewerBinding) binding;
			removeControl(vb.getViewer().getControl());
			return;
		}
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}

	@Override
	public ISelection getSelection() {
		return myCurrentSelection;
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	@Override
	public void setSelection(ISelection selection) {
		if (myCurrentProvider != null) {
			myCurrentProvider.setSelection(selection);
		}
	}

	/**
	 * Notifies any selection changed listeners that the viewer's selection has changed. Only listeners registered at
	 * the time this method is called are notified.
	 * 
	 * @param event a selection changed event
	 * 
	 * @see ISelectionChangedListener#selectionChanged
	 */
	protected void fireSelectionChanged(final SelectionChangedEvent event) {
		final Object[] listeners = selectionChangedListeners.getListeners();
		for (int i = 0; i < listeners.length; ++i) {
			final ISelectionChangedListener l = (ISelectionChangedListener) listeners[i];
			SafeRunnable.run(new SafeRunnable() {
				public void run() {
					l.selectionChanged(event);
				}
			});
		}
	}

	/**
	 * Simple selection provider based on the value of an observable value.
	 */
	private class ObservableValueSelectionProvider implements ISelectionProvider, IValueChangeListener {
		private final ListenerList selectionChangedListeners = new ListenerList();

		/**
		 * The current selection
		 */
		private ISelection selection = null;

		/**
		 * The observable value that forms the base of the selection provider
		 */
		private final IObservableValue myValue;

		public ObservableValueSelectionProvider(IObservableValue value) {
			myValue = value;
			value.addValueChangeListener(this);
		}

		@Override
		public void addSelectionChangedListener(ISelectionChangedListener listener) {
			selectionChangedListeners.add(listener);
		}

		@Override
		public void removeSelectionChangedListener(ISelectionChangedListener listener) {
			selectionChangedListeners.remove(listener);
		}

		@Override
		public ISelection getSelection() {
			return selection;
		}

		@Override
		public void setSelection(ISelection selection) {
			// Not supported as this is a not a viewer
		}

		private void fireSelectionChanged() {
			if (selectionChangedListeners != null) {
				final SelectionChangedEvent event = new SelectionChangedEvent(this, getSelection());

				final Object[] listeners = selectionChangedListeners.getListeners();
				for (final Object listener2 : listeners) {
					final ISelectionChangedListener listener = (ISelectionChangedListener) listener2;
					listener.selectionChanged(event);
				}
			}
		}

		@Override
		public void handleValueChange(ValueChangeEvent event) {
			final Object value = myValue.getValue();
			if (value == null) {
				selection = myEmptySelection;
			} else {
				selection = new StructuredSelection(value);
			}
			fireSelectionChanged();
		}
	}
}