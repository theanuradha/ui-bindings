/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.rcpcompany.uibindings.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.services.IServiceLocator;

import com.rcpcompany.uibindings.BindingState;
import com.rcpcompany.uibindings.IBinding;
import com.rcpcompany.uibindings.IBindingContext;
import com.rcpcompany.uibindings.IManager;
import com.rcpcompany.uibindings.IUIBindingsFactory;
import com.rcpcompany.uibindings.IUIBindingsPackage;
import com.rcpcompany.uibindings.IValueBinding;
import com.rcpcompany.uibindings.IViewerBinding;
import com.rcpcompany.uibindings.TextCommitStrategy;
import com.rcpcompany.uibindings.internal.bindingMessages.ContextMessageDecorator;
import com.rcpcompany.uibindings.internal.bindingMessages.IContextMessageDecoratorAdapter;
import com.rcpcompany.uibindings.internal.bindingMessages.contextAdapters.ScrolledFormContextMessageDecoratorAdapter;
import com.rcpcompany.uibindings.internal.bindingMessages.contextAdapters.WizardPageContextMessageDecoratorAdapter;
import com.rcpcompany.utils.logging.LogUtils;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Binding Context</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingContextImpl#getBindings <em>Bindings</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingContextImpl#getTop <em>Top</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingContextImpl#getDbContext <em>Db Context</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingContextImpl#getServiceLocator <em>Service Locator</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingContextImpl#getState <em>State</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingContextImpl#getTextCommitStrategy <em>Text Commit Strategy</em>}
 * </li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingContextImpl#getTextCommitStrategyCalculated <em>Text Commit
 * Strategy Calculated</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BindingContextImpl extends BaseObjectImpl implements IBindingContext {
	/**
	 * The cached value of the '{@link #getBindings() <em>Bindings</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBindings()
	 * @generated
	 * @ordered
	 */
	protected EList<IBinding> bindings;

	/**
	 * The cached value of the '{@link #getOkBindings() <em>Ok Bindings</em>}' reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getOkBindings()
	 * @generated
	 * @ordered
	 */
	protected EList<IBinding> okBindings;

	/**
	 * The default value of the '{@link #getTop() <em>Top</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getTop()
	 * @generated
	 * @ordered
	 */
	protected static final Composite TOP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTop() <em>Top</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTop()
	 * @generated
	 * @ordered
	 */
	protected Composite top = TOP_EDEFAULT;

	/**
	 * The default value of the '{@link #getDbContext() <em>Db Context</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDbContext()
	 * @generated
	 * @ordered
	 */
	protected static final DataBindingContext DB_CONTEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDbContext() <em>Db Context</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDbContext()
	 * @generated
	 * @ordered
	 */
	protected DataBindingContext dbContext = DB_CONTEXT_EDEFAULT;

	/**
	 * The default value of the '{@link #getServiceLocator() <em>Service Locator</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getServiceLocator()
	 * @generated
	 * @ordered
	 */
	protected static final IServiceLocator SERVICE_LOCATOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServiceLocator() <em>Service Locator</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getServiceLocator()
	 * @generated
	 * @ordered
	 */
	protected IServiceLocator serviceLocator = SERVICE_LOCATOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getState() <em>State</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected static final BindingState STATE_EDEFAULT = BindingState.INIT;

	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected BindingState state = STATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTextCommitStrategy() <em>Text Commit Strategy</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTextCommitStrategy()
	 * @generated NOT
	 * @ordered
	 */
	protected static final TextCommitStrategy TEXT_COMMIT_STRATEGY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTextCommitStrategy() <em>Text Commit Strategy</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTextCommitStrategy()
	 * @generated
	 * @ordered
	 */
	protected TextCommitStrategy textCommitStrategy = TEXT_COMMIT_STRATEGY_EDEFAULT;

	/**
	 * The default value of the '{@link #getTextCommitStrategyCalculated() <em>Text Commit Strategy Calculated</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTextCommitStrategyCalculated()
	 * @generated
	 * @ordered
	 */
	protected static final TextCommitStrategy TEXT_COMMIT_STRATEGY_CALCULATED_EDEFAULT = TextCommitStrategy.ON_MODIFY;

	/**
	 * The cached value of the '{@link #getTextCommitStrategyCalculated() <em>Text Commit Strategy Calculated</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTextCommitStrategyCalculated()
	 * @generated
	 * @ordered
	 */
	protected TextCommitStrategy textCommitStrategyCalculated = TEXT_COMMIT_STRATEGY_CALCULATED_EDEFAULT;

	/**
	 * The default value of the '{@link #getEditingDomain() <em>Editing Domain</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getEditingDomain()
	 * @generated
	 * @ordered
	 */
	protected static final EditingDomain EDITING_DOMAIN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEditingDomain() <em>Editing Domain</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getEditingDomain()
	 * @generated
	 * @ordered
	 */
	protected EditingDomain editingDomain = EDITING_DOMAIN_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public BindingContextImpl() {
		super();
		dbContext = new DataBindingContext();

		// TODO Use the feature map
		eAdapters().add(new AdapterImpl() {
			@Override
			public void notifyChanged(Notification msg) {
				if (msg.getEventType() == Notification.ADD
						&& msg.getFeature() == IUIBindingsPackage.Literals.BINDING_CONTEXT__BINDINGS) {
					myNewBindings.add((IBinding) msg.getNewValue());
				}
			}
		});

		final IManager manager = IManager.Factory.getManager();
		manager.getContexts().add(this);
		manager.eAdapters().add(myTextCommitStrategyAdapter);
		// Force recalcalation of the attribute
		getTextCommitStrategyCalculated();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IUIBindingsPackage.Literals.BINDING_CONTEXT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IBinding> getBindings() {
		if (bindings == null) {
			bindings = new EObjectContainmentWithInverseEList<IBinding>(IBinding.class, this,
					IUIBindingsPackage.BINDING_CONTEXT__BINDINGS, IUIBindingsPackage.BINDING__CONTEXT);
		}
		return bindings;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IBinding> getOkBindings() {
		if (okBindings == null) {
			okBindings = new EObjectEList<IBinding>(IBinding.class, this,
					IUIBindingsPackage.BINDING_CONTEXT__OK_BINDINGS);
		}
		return okBindings;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Composite getTop() {
		return top;
	}

	/**
	 * Sets the top-level element for the context to the specified scrolled form.
	 * 
	 * @param top the top element
	 */
	protected void setTop(final ScrolledForm top) {
		myContextMessageDecoratorAdapter = new ScrolledFormContextMessageDecoratorAdapter(top);
		myFormReflow = new IFormReflow() {
			@Override
			public void reflow() {
				top.getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						if (!top.isDisposed()) {
							top.layout(true, true);
							top.reflow(true);
						}
					}
				});
			}
		};

		setTop(top.getBody());
	}

	/**
	 * Sets the top-level element for the context to the specified wizard page.
	 * 
	 * @param page the wizard page
	 */
	protected void setTop(WizardPage page) {
		myContextMessageDecoratorAdapter = new WizardPageContextMessageDecoratorAdapter(page);
		setTextCommitStrategy(TextCommitStrategy.ON_MODIFY);
		setTop(page.getWizard().getContainer().getShell());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setTop(Composite newTop) {
		setTopGen(newTop);
		getTop().addDisposeListener(myDisposeListener);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setTopGen(Composite newTop) {
		final Composite oldTop = top;
		top = newTop;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, IUIBindingsPackage.BINDING_CONTEXT__TOP, oldTop, top));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DataBindingContext getDbContext() {
		return dbContext;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public IServiceLocator getServiceLocator() {
		IServiceLocator sl = getServiceLocatorGen();
		if (sl == null) {
			sl = PlatformUI.getWorkbench();
		}

		return sl;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IServiceLocator getServiceLocatorGen() {
		return serviceLocator;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setServiceLocator(IServiceLocator newServiceLocator) {
		final IServiceLocator oldServiceLocator = serviceLocator;
		serviceLocator = newServiceLocator;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, IUIBindingsPackage.BINDING_CONTEXT__SERVICE_LOCATOR,
					oldServiceLocator, serviceLocator));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public BindingState getState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setState(BindingState newState) {
		final BindingState oldState = state;
		state = newState == null ? STATE_EDEFAULT : newState;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, IUIBindingsPackage.BINDING_CONTEXT__STATE, oldState,
					state));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public TextCommitStrategy getTextCommitStrategy() {
		return textCommitStrategy;
	}

	/**
	 * Sets the value of the '{@link IBindingContext#getTextCommitStrategy <em>Text Commit Strategy</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Text Commit Strategy</em>' attribute.
	 * @see #getTextCommitStrategy()
	 * @generated NOT
	 */
	public void setTextCommitStrategy(TextCommitStrategy newTextCommitStrategy) {
		setTextCommitStrategyGen(newTextCommitStrategy);
		getTextCommitStrategyCalculated();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setTextCommitStrategyGen(TextCommitStrategy newTextCommitStrategy) {
		final TextCommitStrategy oldTextCommitStrategy = textCommitStrategy;
		textCommitStrategy = newTextCommitStrategy == null ? TEXT_COMMIT_STRATEGY_EDEFAULT : newTextCommitStrategy;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					IUIBindingsPackage.BINDING_CONTEXT__TEXT_COMMIT_STRATEGY, oldTextCommitStrategy, textCommitStrategy));
		}
	}

	private final Adapter myTextCommitStrategyAdapter = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.isTouch()) {
				return;
			}
			if (msg.getFeature() == IUIBindingsPackage.Literals.MANAGER__TEXT_COMMIT_STRATEGY) {
				getTextCommitStrategyCalculated();
			}
		}
	};

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public TextCommitStrategy getTextCommitStrategyCalculated() {
		TextCommitStrategy strategy = getTextCommitStrategy();
		if (strategy == null) {
			strategy = IManager.Factory.getManager().getTextCommitStrategy();
		}

		final TextCommitStrategy oldTextCommitStrategyCalculated = textCommitStrategyCalculated;
		textCommitStrategyCalculated = strategy;
		if (oldTextCommitStrategyCalculated != textCommitStrategyCalculated && eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					IUIBindingsPackage.BINDING_CONTEXT__TEXT_COMMIT_STRATEGY_CALCULATED,
					oldTextCommitStrategyCalculated, textCommitStrategyCalculated));
		}

		return textCommitStrategyCalculated;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EditingDomain getEditingDomain() {
		if (editingDomain == null) {
			return IManager.Factory.getManager().getEditingDomain();
		}
		return editingDomain;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setEditingDomain(EditingDomain newEditingDomain) {
		final EditingDomain oldEditingDomain = editingDomain;
		editingDomain = newEditingDomain;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, IUIBindingsPackage.BINDING_CONTEXT__EDITING_DOMAIN,
					oldEditingDomain, editingDomain));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case IUIBindingsPackage.BINDING_CONTEXT__BINDINGS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getBindings()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case IUIBindingsPackage.BINDING_CONTEXT__BINDINGS:
			return ((InternalEList<?>) getBindings()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case IUIBindingsPackage.BINDING_CONTEXT__BINDINGS:
			return getBindings();
		case IUIBindingsPackage.BINDING_CONTEXT__OK_BINDINGS:
			return getOkBindings();
		case IUIBindingsPackage.BINDING_CONTEXT__TOP:
			return getTop();
		case IUIBindingsPackage.BINDING_CONTEXT__DB_CONTEXT:
			return getDbContext();
		case IUIBindingsPackage.BINDING_CONTEXT__SERVICE_LOCATOR:
			return getServiceLocator();
		case IUIBindingsPackage.BINDING_CONTEXT__STATE:
			return getState();
		case IUIBindingsPackage.BINDING_CONTEXT__TEXT_COMMIT_STRATEGY:
			return getTextCommitStrategy();
		case IUIBindingsPackage.BINDING_CONTEXT__TEXT_COMMIT_STRATEGY_CALCULATED:
			return getTextCommitStrategyCalculated();
		case IUIBindingsPackage.BINDING_CONTEXT__EDITING_DOMAIN:
			return getEditingDomain();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case IUIBindingsPackage.BINDING_CONTEXT__BINDINGS:
			getBindings().clear();
			getBindings().addAll((Collection<? extends IBinding>) newValue);
			return;
		case IUIBindingsPackage.BINDING_CONTEXT__OK_BINDINGS:
			getOkBindings().clear();
			getOkBindings().addAll((Collection<? extends IBinding>) newValue);
			return;
		case IUIBindingsPackage.BINDING_CONTEXT__TOP:
			setTop((Composite) newValue);
			return;
		case IUIBindingsPackage.BINDING_CONTEXT__SERVICE_LOCATOR:
			setServiceLocator((IServiceLocator) newValue);
			return;
		case IUIBindingsPackage.BINDING_CONTEXT__STATE:
			setState((BindingState) newValue);
			return;
		case IUIBindingsPackage.BINDING_CONTEXT__TEXT_COMMIT_STRATEGY:
			setTextCommitStrategy((TextCommitStrategy) newValue);
			return;
		case IUIBindingsPackage.BINDING_CONTEXT__EDITING_DOMAIN:
			setEditingDomain((EditingDomain) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case IUIBindingsPackage.BINDING_CONTEXT__BINDINGS:
			getBindings().clear();
			return;
		case IUIBindingsPackage.BINDING_CONTEXT__OK_BINDINGS:
			getOkBindings().clear();
			return;
		case IUIBindingsPackage.BINDING_CONTEXT__TOP:
			setTop(TOP_EDEFAULT);
			return;
		case IUIBindingsPackage.BINDING_CONTEXT__SERVICE_LOCATOR:
			setServiceLocator(SERVICE_LOCATOR_EDEFAULT);
			return;
		case IUIBindingsPackage.BINDING_CONTEXT__STATE:
			setState(STATE_EDEFAULT);
			return;
		case IUIBindingsPackage.BINDING_CONTEXT__TEXT_COMMIT_STRATEGY:
			setTextCommitStrategy(TEXT_COMMIT_STRATEGY_EDEFAULT);
			return;
		case IUIBindingsPackage.BINDING_CONTEXT__EDITING_DOMAIN:
			setEditingDomain(EDITING_DOMAIN_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case IUIBindingsPackage.BINDING_CONTEXT__BINDINGS:
			return bindings != null && !bindings.isEmpty();
		case IUIBindingsPackage.BINDING_CONTEXT__OK_BINDINGS:
			return okBindings != null && !okBindings.isEmpty();
		case IUIBindingsPackage.BINDING_CONTEXT__TOP:
			return TOP_EDEFAULT == null ? top != null : !TOP_EDEFAULT.equals(top);
		case IUIBindingsPackage.BINDING_CONTEXT__DB_CONTEXT:
			return DB_CONTEXT_EDEFAULT == null ? dbContext != null : !DB_CONTEXT_EDEFAULT.equals(dbContext);
		case IUIBindingsPackage.BINDING_CONTEXT__SERVICE_LOCATOR:
			return SERVICE_LOCATOR_EDEFAULT == null ? serviceLocator != null : !SERVICE_LOCATOR_EDEFAULT
					.equals(serviceLocator);
		case IUIBindingsPackage.BINDING_CONTEXT__STATE:
			return state != STATE_EDEFAULT;
		case IUIBindingsPackage.BINDING_CONTEXT__TEXT_COMMIT_STRATEGY:
			return textCommitStrategy != TEXT_COMMIT_STRATEGY_EDEFAULT;
		case IUIBindingsPackage.BINDING_CONTEXT__TEXT_COMMIT_STRATEGY_CALCULATED:
			return textCommitStrategyCalculated != TEXT_COMMIT_STRATEGY_CALCULATED_EDEFAULT;
		case IUIBindingsPackage.BINDING_CONTEXT__EDITING_DOMAIN:
			return EDITING_DOMAIN_EDEFAULT == null ? editingDomain != null : !EDITING_DOMAIN_EDEFAULT
					.equals(editingDomain);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		final StringBuffer result = new StringBuffer(super.toString());
		result.append(" (top: "); //$NON-NLS-1$
		result.append(top);
		result.append(", dbContext: "); //$NON-NLS-1$
		result.append(dbContext);
		result.append(", serviceLocator: "); //$NON-NLS-1$
		result.append(serviceLocator);
		result.append(", state: "); //$NON-NLS-1$
		result.append(state);
		result.append(", textCommitStrategy: "); //$NON-NLS-1$
		result.append(textCommitStrategy);
		result.append(", textCommitStrategyCalculated: "); //$NON-NLS-1$
		result.append(textCommitStrategyCalculated);
		result.append(", editingDomain: "); //$NON-NLS-1$
		result.append(editingDomain);
		result.append(')');
		return result.toString();
	}

	public IValueBinding addBinding() {
		final IValueBinding binding = IUIBindingsFactory.eINSTANCE.createValueBinding();
		binding.setContext(this);
		return binding;
	}

	public IValueBinding addBinding(Widget widget, EObject object, EStructuralFeature feature) {
		return addBinding().ui(widget).model(object, feature);
	}

	public IValueBinding addBinding(Widget widget, String attribute, EObject object, EStructuralFeature feature) {
		return addBinding().ui(widget, attribute).model(object, feature);
	}

	public IValueBinding addBinding(Widget widget, IObservableValue object, EStructuralFeature feature) {
		return addBinding().ui(widget).model(object, feature);
	}

	public IValueBinding addBinding(Widget widget, String attribute, IObservableValue object, EStructuralFeature feature) {
		return addBinding().ui(widget, attribute).model(object, feature);
	}

	@Override
	public IViewerBinding addViewer() {
		final IViewerBinding binding = IUIBindingsFactory.eINSTANCE.createViewerBinding();
		binding.setContext(this);
		return binding;
	}

	@Override
	public IViewerBinding addViewer(ColumnViewer viewer, EObject object, EReference reference) {
		return addViewer().viewer(viewer).model(object, reference);
	}

	@Override
	public IViewerBinding addViewer(Table table, EObject object, EReference reference) {
		return addViewer().viewer(table).model(object, reference);
	}

	@Override
	public IViewerBinding addViewer(Table table, IObservableValue object, EReference reference) {
		return addViewer().viewer(table).model(object, reference);
	}

	@Override
	public IViewerBinding addViewer(ColumnViewer viewer, IObservableValue object, EReference reference) {
		return addViewer().viewer(viewer).model(object, reference);
	}

	@Override
	public IViewerBinding addViewer(ColumnViewer viewer, IObservableList list) {
		return addViewer().viewer(viewer).model(list);
		// return new ViewerBinding(this, viewer, list);
	}

	/**
	 * Guards {@link #finish()}.
	 */
	private boolean isInFinish = false;

	/**
	 * Set to <code>true</code> when all needed support has been hooked into the context in {@link #finish()}.
	 */
	private boolean hasSupportBeenAdded = false;

	final protected List<IBinding> myNewBindings = new ArrayList<IBinding>();

	/**
	 * Shows whether {@link #finish()} has been deferred.
	 */
	protected boolean myFinishDeferred = false;

	@Override
	public void finish() {
		finish(FinishOption.LAZY);
	}

	@Override
	public void finish(FinishOption option) {
		switch (option) {
		case IF_ALREADY_FINISHED:
			switch (getState()) {
			case OK:
			case PHASE1:
			case PHASE2:
			case PHASE3:
				break;
			default:
				return;
			}
			//$FALL-THROUGH$
		case LAZY:
			finishDeferred();
			// if (myFinishDeferred)
			// return;
			//
			// getTop().getDisplay().asyncExec(new Runnable() {
			// @Override
			// public void run() {
			// finishDeferred();
			// }
			// });
			// myFinishDeferred = true;
			break;
		case FORCE:
			finishDeferred();
			break;
		}
	}

	protected void finishDeferred() {
		myFinishDeferred = false;
		if (isInFinish) {
			if (Activator.getDefault().TRACE_LIFECYCLE_BINDINGS) {
				LogUtils.debug(this, "recursive finish"); //$NON-NLS-1$
			}
			return;
		}
		try {
			isInFinish = true;
			if (Activator.getDefault().TRACE_LIFECYCLE_BINDINGS) {
				LogUtils.debug(this, "finish start"); //$NON-NLS-1$
			}
			final List<IBinding> bindingsToProcess = new ArrayList<IBinding>();

			while (myNewBindings.size() > 0) {
				bindingsToProcess.addAll(myNewBindings);
				myNewBindings.clear();

				for (final IBinding b : bindingsToProcess) {
					if (myNewBindings.size() > 0) {
						break;
					}
					if (b.getState() == BindingState.INIT) {
						setState(BindingState.PHASE1);
						b.setState(BindingState.PHASE1);
						try {
							b.finish1();
						} catch (final Exception ex) {
							LogUtils.error(b, ex);
							b.setState(BindingState.DISPOSE_PENDING);
							continue;
						}
					}
				}

				for (final IBinding b : bindingsToProcess) {
					if (myNewBindings.size() > 0) {
						break;
					}
					if (b.getState() == BindingState.PHASE1) {
						setState(BindingState.PHASE2);
						b.setState(BindingState.PHASE2);
						try {
							b.finish2();
						} catch (final Exception ex) {
							LogUtils.error(b, ex);
							b.setState(BindingState.DISPOSE_PENDING);
							continue;
						}
					}
				}

				for (final IBinding b : bindingsToProcess) {
					if (myNewBindings.size() > 0) {
						break;
					}
					if (b.getState() == BindingState.PHASE2) {
						setState(BindingState.PHASE3);
						b.setState(BindingState.PHASE3);
						try {
							b.finish3();
						} catch (final Exception ex) {
							LogUtils.error(b, ex);
							b.setState(BindingState.DISPOSE_PENDING);
							continue;
						}
						b.setState(BindingState.OK);
						getOkBindings().add(b);
					}
				}
			}

			for (final IBinding b : bindingsToProcess) {
				if (b.getState() == BindingState.DISPOSE_PENDING) {
					b.dispose();
				}
			}

			if (!hasSupportBeenAdded) {
				if (myContextMessageDecoratorAdapter != null) {
					new ContextMessageDecorator(this, myContextMessageDecoratorAdapter);
				}

				hasSupportBeenAdded = true;
			}
			setState(BindingState.OK);
		} finally {
			isInFinish = false;
			if (Activator.getDefault().TRACE_LIFECYCLE_BINDINGS) {
				LogUtils.debug(this, "finish end"); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Interface used to reflow the form/wizard/whatever that hosts this context.
	 */
	private interface IFormReflow {
		/**
		 * Reflows the the form/wizard/whatever that hosts this context.
		 */
		public void reflow();
	}

	/**
	 * The context adapter used by the messages decorator support
	 * 
	 * TODO move
	 */
	private IContextMessageDecoratorAdapter myContextMessageDecoratorAdapter;

	/**
	 * Object used to reflow the form/wizard/whatever that hosts this context.
	 */
	private IFormReflow myFormReflow;

	@Override
	public void reflow() {
		if (myFormReflow != null) {
			myFormReflow.reflow();
		}
	}

	private final DisposeListener myDisposeListener = new DisposeListener() {
		@Override
		public void widgetDisposed(DisposeEvent e) {
			dispose();
		}
	};

	@Override
	public void dispose() {
		setState(BindingState.DISPOSED);
		final IManager manager = IManager.Factory.getManager();
		manager.getContexts().remove(this);
		manager.eAdapters().remove(myTextCommitStrategyAdapter);

		disposeServices();

		if (getTop() != null && !getTop().isDisposed()) {
			getTop().removeDisposeListener(myDisposeListener);
		}

		while (getBindings().size() > 0) {
			getBindings().get(0).dispose();
		}

		try {
			getDbContext().dispose();
		} catch (final NullPointerException ex) {
			LogUtils.error(this, ex);
		}
	}

	@Override
	public void updateBindings(Object[] objects) {
		for (final IBinding b : getBindings()) {
			b.updateBinding();
		}
	}
} // BindingContextImpl