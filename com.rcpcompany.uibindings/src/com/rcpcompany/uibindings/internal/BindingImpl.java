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
package com.rcpcompany.uibindings.internal;

import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateListStrategy;
import org.eclipse.core.databinding.UpdateSetStrategy;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

import com.rcpcompany.uibindings.BindingState;
import com.rcpcompany.uibindings.Constants;
import com.rcpcompany.uibindings.IArgumentContext;
import com.rcpcompany.uibindings.IArgumentInformation;
import com.rcpcompany.uibindings.IArgumentProvider;
import com.rcpcompany.uibindings.IArgumentValue;
import com.rcpcompany.uibindings.IBinding;
import com.rcpcompany.uibindings.IBindingContext;
import com.rcpcompany.uibindings.IBindingDataType;
import com.rcpcompany.uibindings.IManager;
import com.rcpcompany.uibindings.IUIBindingsPackage;
import com.rcpcompany.uibindings.utils.EditingDomainUtils;
import com.rcpcompany.utils.basic.ClassUtils;
import com.rcpcompany.utils.basic.ToStringUtils;
import com.rcpcompany.utils.logging.LogUtils;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Binding</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getContext <em>Context</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getState <em>State</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#isChangeable <em>Changeable</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getCreationPoint <em>Creation Point
 * </em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getId <em>Id</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getType <em>Type</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getLabel <em>Label</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getStaticDataType <em>Static Data Type
 * </em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getDataType <em>Data Type</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getModelEType <em>Model EType</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getModelType <em>Model Type</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getUIType <em>UI Type</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getDBBindings <em>DB Bindings</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getMonitoredDBBindings <em>Monitored DB
 * Bindings</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getErrorConditions <em>Error Conditions
 * </em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getWidget <em>Widget</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getControl <em>Control</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.internal.BindingImpl#getExtraArgumentProviders <em>Extra
 * Argument Providers</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class BindingImpl extends BaseObjectImpl implements IBinding {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected BindingImpl() {
		super();

		if (Activator.getDefault().CREATION_POINT_STACK_LEVELS > 0) {
			final Throwable cp = new Throwable();
			cp.fillInStackTrace();
			final StackTraceElement[] stackTrace = cp.getStackTrace();
			int i = 0;
			for (i = 0; i < stackTrace.length; i++) {
				if (stackTrace[i].getMethodName().equals("addBinding")) {
					// Ignore this frame as well
					i++;
					break;
				}
			}
			final StackTraceElement[] newStackTrace = new StackTraceElement[min(stackTrace.length - i,
					Activator.getDefault().CREATION_POINT_STACK_LEVELS)];
			System.arraycopy(stackTrace, i, newStackTrace, 0, newStackTrace.length);
			cp.setStackTrace(newStackTrace);
			setCreationPoint(cp);
		}

		if (Activator.getDefault().TRACE_LIFECYCLE_BINDINGS) {
			LogUtils.debug(this, this + " constructed"); //$NON-NLS-1$
		}
	}

	@Override
	public void dispose() {
		setState(BindingState.DISPOSE_PENDING);
		if (Activator.getDefault().TRACE_LIFECYCLE_BINDINGS) {
			LogUtils.debug(this, this + " disposed"); //$NON-NLS-1$
		}
		/*
		 * The context can be null if the binding was never properly created... which of cause
		 * should only happen if something doesn't work properly :-)
		 */
		if (getContext() != null) {
			getContext().getOkBindings().remove(this);
		}
		setState(BindingState.DISPOSED);
		setContext(null);
	}

	@Override
	public void setArguments(Map<String, Object> arguments) {
		if (arguments == null) return;
		getArguments().putAll(arguments);
	}

	@Override
	public void assertTrue(boolean b, String message) {
		if (!b) {
			LogUtils.throwException(this, message, getCreationPoint());
		}
	}

	@Override
	public void addDBBinding(Binding dataBinding, boolean monitorStatus) {
		getDBBindings().add(dataBinding);
		if (monitorStatus) {
			getMonitoredDBBindings().add(dataBinding);
		}
	}

	@Override
	public void bindList(IObservableList targetObservableList, IObservableList modelObservableList,
			UpdateListStrategy targetToModel, UpdateListStrategy modelToTarget, boolean monitorStatus) {
		final Binding b = getContext().getDbContext().bindList(targetObservableList, modelObservableList,
				targetToModel, modelToTarget);
		addDBBinding(b, monitorStatus);
	}

	@Override
	public void bindSet(IObservableSet targetObservableSet, IObservableSet modelObservableSet,
			UpdateSetStrategy targetToModel, UpdateSetStrategy modelToTarget) {
		final Binding b = getContext().getDbContext().bindSet(targetObservableSet, modelObservableSet, targetToModel,
				modelToTarget);
		addDBBinding(b, false);
	}

	@Override
	public Binding bindValue(IObservableValue targetObservableValue, IObservableValue modelObservableValue,
			UpdateValueStrategy targetToModel, UpdateValueStrategy modelToTarget, boolean monitorStatus) {
		final Binding b = getContext().getDbContext().bindValue(targetObservableValue, modelObservableValue,
				targetToModel, modelToTarget);
		addDBBinding(b, monitorStatus);
		return b;
	}

	/**
	 * The default value of the '{@link #getState() <em>State</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected static final BindingState STATE_EDEFAULT = BindingState.INIT;

	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected BindingState state = STATE_EDEFAULT;

	/**
	 * The default value of the '{@link #isChangeable() <em>Changeable</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isChangeable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CHANGEABLE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #getCreationPoint() <em>Creation Point</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCreationPoint()
	 * @generated
	 * @ordered
	 */
	protected static final Throwable CREATION_POINT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreationPoint() <em>Creation Point</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCreationPoint()
	 * @generated
	 * @ordered
	 */
	protected Throwable creationPoint = CREATION_POINT_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStaticDataType() <em>Static Data Type</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStaticDataType()
	 * @generated
	 * @ordered
	 */
	protected IBindingDataType staticDataType;

	/**
	 * The cached value of the '{@link #getDBBindings() <em>DB Bindings</em>}' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDBBindings()
	 * @generated
	 * @ordered
	 */
	protected EList<Binding> dbBindings;

	/**
	 * The cached value of the '{@link #getMonitoredDBBindings() <em>Monitored DB Bindings</em>}'
	 * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMonitoredDBBindings()
	 * @generated
	 * @ordered
	 */
	protected EList<Binding> monitoredDBBindings;

	/**
	 * The cached value of the '{@link #getErrorConditions() <em>Error Conditions</em>}' attribute
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getErrorConditions()
	 * @generated
	 * @ordered
	 */
	protected EList<String> errorConditions;

	/**
	 * The default value of the '{@link #getWidget() <em>Widget</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getWidget()
	 * @generated
	 * @ordered
	 */
	protected static final Widget WIDGET_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getControl() <em>Control</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getControl()
	 * @generated
	 * @ordered
	 */
	protected static final Control CONTROL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExtraArgumentProviders()
	 * <em>Extra Argument Providers</em>}' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getExtraArgumentProviders()
	 * @generated
	 * @ordered
	 */
	protected EList<IArgumentProvider> extraArgumentProviders;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IUIBindingsPackage.Literals.BINDING;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IBindingContext getContext() {
		if (eContainerFeatureID() != IUIBindingsPackage.BINDING__CONTEXT) return null;
		return (IBindingContext) eContainer();
	}

	@Override
	public EditingDomain getEditingDomain() {
		final IBindingContext context = getContext();
		if (context == null) return EditingDomainUtils.getEditingDomain();
		return context.getEditingDomain();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetContext(IBindingContext newContext, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newContext, IUIBindingsPackage.BINDING__CONTEXT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setContext(IBindingContext newContext) {
		if (newContext != eInternalContainer()
				|| (eContainerFeatureID() != IUIBindingsPackage.BINDING__CONTEXT && newContext != null)) {
			if (EcoreUtil.isAncestor(this, newContext))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newContext != null) {
				msgs = ((InternalEObject) newContext).eInverseAdd(this, IUIBindingsPackage.BINDING_CONTEXT__BINDINGS,
						IBindingContext.class, msgs);
			}
			msgs = basicSetContext(newContext, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, IUIBindingsPackage.BINDING__CONTEXT, newContext,
					newContext));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public BindingState getState() {
		return state;
	}

	@Override
	public boolean isDisposed() {
		switch (getState()) {
		case DISPOSE_PENDING:
		case DISPOSED:
			return true;
		default:
			return false;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setState(BindingState newState) {
		final BindingState oldState = state;
		state = newState == null ? STATE_EDEFAULT : newState;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, IUIBindingsPackage.BINDING__STATE, oldState, state));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setStateGen(BindingState newState) {
		final BindingState oldState = state;
		state = newState == null ? STATE_EDEFAULT : newState;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, IUIBindingsPackage.BINDING__STATE, oldState, state));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isChangeable() {
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Throwable getCreationPoint() {
		return creationPoint;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setCreationPoint(Throwable newCreationPoint) {
		final Throwable oldCreationPoint = creationPoint;
		creationPoint = newCreationPoint;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, IUIBindingsPackage.BINDING__CREATION_POINT,
					oldCreationPoint, creationPoint));
		}
	}

	/**
	 * A map with all binding specific arguments added with {@link IBinding#arg(String, Object)}.
	 */
	private Map<String, Object> myArguments = null;

	@Override
	public Map<String, Object> getArguments() {
		if (myArguments == null) {
			myArguments = new HashMap<String, Object>();
		}
		return myArguments;
	}

	@Override
	public boolean hasArguments() {
		return myArguments != null && !myArguments.isEmpty();
	}

	@Override
	public IBinding getParentBinding() {
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		final String oldId = id;
		id = newId;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, IUIBindingsPackage.BINDING__ID, oldId, id));
		}
	}

	@Override
	public Object getArgument(String name) {
		if (!hasArguments()) return null;
		return getArguments().get(name);
	}

	/**
	 * Cached results from {@link #getArgument(String, Class, Object)}.
	 */
	private final Map<String, Object> myCachedArguments = new HashMap<String, Object>();

	/**
	 * The package prefix name for all internal packages.
	 */
	private static final String INTERNAL_PACKAGE_NAME = BindingImpl.class.getPackage().getName();

	/**
	 * The package prefix name for all internal spy packages.
	 */
	private static final String SPY_PACKAGE_NAME = INTERNAL_PACKAGE_NAME + ".spy"; //$NON-NLS-1$

	@Override
	public void clearCachedArguments() {
		myCachedArguments.clear();
	}

	/**
	 * Implementation of {@link IArgumentValue}.
	 * 
	 * @param <ArgumentType> the type of the argument value
	 */
	public static class ArgumentValue<ArgumentType> implements IArgumentValue<ArgumentType> {
		private final Object mySource;
		private final ArgumentType myValue;

		/**
		 * Constructs and returns a new argument value object.
		 * 
		 * @param source the source
		 * @param value the value
		 */
		public ArgumentValue(Object source, ArgumentType value) {
			super();
			mySource = source;
			myValue = value;
		}

		@Override
		public Object getSource() {
			return mySource;
		}

		@Override
		public ArgumentType getValue() {
			return myValue;
		}

	}

	@Override
	public <ArgumentType> List<IArgumentValue<ArgumentType>> getArguments(final String name,
			final Class<? extends ArgumentType> argumentType, final boolean firstOnly) {
		final List<IArgumentValue<ArgumentType>> results = new ArrayList<IArgumentValue<ArgumentType>>();

		final String type;
		/*
		 * Prevent recursion
		 */
		if (!name.equals(Constants.ARG_TYPE)) {
			type = getType();
		} else {
			type = null;
		}

		final IManager manager = IManager.Factory.getManager();
		final IArgumentInformation ai = manager.getArgumentInformation(name);
		final Set<IBindingDataType> visitedDataTypes = new HashSet<IBindingDataType>();

		final IArgumentContext<ArgumentType> context = new IArgumentContext<ArgumentType>() {
			@Override
			public IBinding getBinding() {
				return BindingImpl.this;
			}

			@Override
			public String getName() {
				return name;
			}

			@Override
			public IArgumentInformation getArgumentInformation() {
				return ai;
			}

			@Override
			public String getType() {
				return type;
			}

			@Override
			public Class<? extends ArgumentType> getArgumentType() {
				return argumentType;
			}

			@Override
			public boolean firstOnly() {
				return firstOnly;
			}

			@Override
			public void addResult(Object source, ArgumentType value) {
				results.add(new ArgumentValue<ArgumentType>(source, value));
			}

			@Override
			public boolean isResultFound() {
				return firstOnly() && !results.isEmpty();
			}

			@Override
			public boolean addDataType(IBindingDataType dataType) {
				return visitedDataTypes.add(dataType);
			}
		};

		try {
			/*
			 * Try all enabled extenders
			 */
			addDecoratorExtenderArguments(context);
			if (context.isResultFound()) return results;

			/*
			 * Check direct arguments
			 */
			addDirectArguments(context);
			if (context.isResultFound()) return results;

			/*
			 * Add decorator provider arguments
			 */
			addDecoratorProviderArguments(context);
			if (context.isResultFound()) return results;

			/*
			 * Add any arguments from the parent binding: value -> column and column -> viewer
			 */
			if (context.getArgumentInformation().isLookupParent()) {
				final IArgumentProvider parentBinding = getParentBinding();
				if (parentBinding != null) {
					manager.addArgumentProviderArguments(parentBinding, context);
					if (context.isResultFound()) return results;
				}
			}

			/*
			 * Now use the list of IBDT object to look for annotations.
			 */
			final IBindingDataType dynamicDataType = getDataType();
			if (dynamicDataType != null) {
				// LogUtils.debug(this, this + ": " + getStaticDataType() + "/" + getDataType());
				dynamicDataType.addArguments(context);
				if (context.isResultFound()) return results;

				dynamicDataType.addSFSuperContainingClassArguments(context);
				if (context.isResultFound()) return results;

				dynamicDataType.addSuperDataTypeArguments(context);
				if (context.isResultFound()) return results;
			}

			/*
			 * Try the (static) model data type...
			 */
			final IBindingDataType sDataType = getStaticDataType();
			if (sDataType != null) {
				sDataType.addArguments(context);
				if (context.isResultFound()) return results;

				sDataType.addSFSuperContainingClassArguments(context);
				if (context.isResultFound()) return results;

				sDataType.addSuperDataTypeArguments(context);
				if (context.isResultFound()) return results;
			}

			/*
			 * Now use the parent IBDTs if they exists
			 */
			if (dynamicDataType != null) {
				dynamicDataType.addParentDataTypeArguments(context);
				if (context.isResultFound()) return results;
			}

			/*
			 * Try the model data type...
			 */
			if (sDataType != null) {
				sDataType.addParentDataTypeArguments(context);
				if (context.isResultFound()) return results;
			}

			/*
			 * And then any extra argument providers added to the binding
			 */
			if (eIsSet(IUIBindingsPackage.Literals.BINDING__EXTRA_ARGUMENT_PROVIDERS)) {
				for (final IArgumentProvider ap : getExtraArgumentProviders()) {
					manager.addArgumentProviderArguments(ap, context);
					if (context.isResultFound()) return results;
				}
			}

			return results;
		} finally {
			if (Activator.getDefault().TRACE_ARGUMENTS
					&& (Activator.getDefault().TRACE_ARGUMENT_TYPES == null || Activator.getDefault().TRACE_ARGUMENT_TYPES
							.contains(name))
					&& (Activator.getDefault().TRACE_ARGUMENT_BINDINGS == null || Activator.getDefault().TRACE_ARGUMENT_TYPES
							.contains(getBaseType()))) {
				final StringBuilder sb = new StringBuilder(400);
				sb.append(this).append("(").append(name).append(") [firstOnly=").append(firstOnly).append("]");
				for (final IArgumentValue<ArgumentType> a : results) {
					sb.append("\n    '").append(a.getValue()).append("': ").append(a.getSource());
				}
				LogUtils.debug(this, sb.toString());
			}
		}
	}

	/**
	 * Handles any additions of arguments from decorator extenders.
	 * 
	 * @param <ArgumentType> the argument type
	 * @param results the result list
	 * @param name the name of the wanted argument
	 * @param argumentType the argument type
	 * @param firstOnly <code>true</code> if only the first result is of interest
	 * @return <code>true</code> if ant results was found
	 */
	public <ArgumentType> void addDecoratorExtenderArguments(IArgumentContext<ArgumentType> context) {
	}

	/**
	 * Handles any additions of arguments from decorator provider.
	 * 
	 * @param <ArgumentType> the argument type
	 * @param results the result list
	 * @param name the name of the wanted argument
	 * @param argumentType the argument type
	 * @param firstOnly <code>true</code> if only the first result is of interest
	 * @return <code>true</code> if ant results was found
	 */
	public <ArgumentType> void addDecoratorProviderArguments(IArgumentContext<ArgumentType> context) {
	}

	/**
	 * Handles any additions of arguments from direct arguments.
	 * 
	 * @param <ArgumentType> the argument type
	 * @param results the result list
	 * @param name the name of the wanted argument
	 * @param argumentType the argument type
	 * @param firstOnly <code>true</code> if only the first result is of interest
	 * @return <code>true</code> if ant results was found
	 */
	public <ArgumentType> void addDirectArguments(IArgumentContext<ArgumentType> context) {
		IManager.Factory.getManager().addArgumentProviderArguments(this, context);
	}

	@Override
	public <ArgumentType> ArgumentType getArgument(String name, Class<? extends ArgumentType> argumentType,
			ArgumentType defaultValue) {
		/*
		 * Check any cached previous results
		 */
		if (myCachedArguments.containsKey(name)) {
			final Object value = myCachedArguments.get(name);
			if (value == null) return null;
			if (argumentType.isInstance(value)) {
				if (Activator.getDefault().TRACE_ARGUMENT
						&& (Activator.getDefault().TRACE_ARGUMENT_TYPES == null || Activator.getDefault().TRACE_ARGUMENT_TYPES
								.contains(name))
						&& (Activator.getDefault().TRACE_ARGUMENT_BINDINGS == null || Activator.getDefault().TRACE_ARGUMENT_TYPES
								.contains(getBaseType()))) {
					LogUtils.debug(this, this + "(" + name + ") [cached]=" + value);
				}
				return (ArgumentType) value;
			}
			LogUtils.error(this, "Cached argument '" + name + "' value '" + value + "' not of right type (expected " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					+ argumentType + ", got " + value.getClass() + "). Ignored.", getCreationPoint()); //$NON-NLS-1$ //$NON-NLS-2$
			myCachedArguments.remove(name);
		}

		final List<IArgumentValue<ArgumentType>> list = getArguments(name, argumentType, true);

		/*
		 * If no results was found, then use the default value
		 */
		if (list == null || list.size() == 0) {
			if (Activator.getDefault().TRACE_ARGUMENT
					&& (Activator.getDefault().TRACE_ARGUMENT_TYPES == null || Activator.getDefault().TRACE_ARGUMENT_TYPES
							.contains(name))
					&& (Activator.getDefault().TRACE_ARGUMENT_BINDINGS == null || Activator.getDefault().TRACE_ARGUMENT_TYPES
							.contains(getBaseType()))) {
				LogUtils.debug(this, this + "(" + name + ") [default]=" + defaultValue);
			}
			myCachedArguments.put(name, defaultValue);
			return defaultValue;
		}

		final ArgumentType value = list.get(0).getValue();
		myCachedArguments.put(name, value);
		if (Activator.getDefault().TRACE_ARGUMENT
				&& (Activator.getDefault().TRACE_ARGUMENT_TYPES == null || Activator.getDefault().TRACE_ARGUMENT_TYPES
						.contains(name))
				&& (Activator.getDefault().TRACE_ARGUMENT_BINDINGS == null || Activator.getDefault().TRACE_ARGUMENT_TYPES
						.contains(getBaseType()))) {
			LogUtils.debug(this, this + "(" + name + ") [calc]=" + value + "\nsource: " + list.get(0).getSource());
		}

		return value;
	}

	@Override
	public void registerWidget(Widget widget) {
		assertTrue(widget != null, "widget null"); //$NON-NLS-1$
		assertTrue(widget.getData(InternalConstants.WIDGET_KEY) == null, widget
				+ ": Widget already registered to binding"); //$NON-NLS-1$
		widget.setData(InternalConstants.WIDGET_KEY, this);
	}

	@Override
	public void unregisterWidget(Widget widget) {
		assertTrue(widget != null, "widget null"); //$NON-NLS-1$
		if (!widget.isDisposed()) {
			widget.setData(InternalConstants.WIDGET_KEY, null);
		}
	}

	public static IBinding getBindingForWidget(Widget widget) {
		if (widget == null) return null;
		while (widget != null) {
			final IBinding b = (IBinding) widget.getData(InternalConstants.WIDGET_KEY);
			if (b != null) return b;
			if (widget.isDisposed()) return null;
			if (!(widget instanceof Control)) return null;
			widget = ((Control) widget).getParent();
		}

		return null;
	}

	@Override
	public abstract void finish1();

	@Override
	public void finish2() {
	}

	@Override
	public void finish3() {
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getType() {
		return getArgument(Constants.ARG_TYPE, String.class, ""); //$NON-NLS-1$
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getLabel() {
		String name;
		name = getArgument(Constants.ARG_LABEL, String.class, null);
		if (name == null) {
			name = ToStringUtils.formatHumanReadable(getDataType().getName());
		}
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IBindingDataType getStaticDataType() {
		return staticDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setStaticDataType(IBindingDataType newStaticDataType) {
		final IBindingDataType oldStaticDataType = staticDataType;
		staticDataType = newStaticDataType;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, IUIBindingsPackage.BINDING__STATIC_DATA_TYPE,
					oldStaticDataType, staticDataType));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public IBindingDataType getDataType() {
		return getStaticDataType();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public EClassifier getModelEType() {
		final IBindingDataType dt = getDataType();
		if (dt == null) return null;
		return dt.getEType();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Class<?> getModelType() {
		final IBindingDataType dt = getDataType();
		if (dt == null) return null;
		return dt.getDataType();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public abstract Class<?> getUIType();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Binding> getDBBindings() {
		if (dbBindings == null) {
			dbBindings = new EDataTypeUniqueEList<Binding>(Binding.class, this, IUIBindingsPackage.BINDING__DB_BINDINGS);
		}
		return dbBindings;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Binding> getMonitoredDBBindings() {
		if (monitoredDBBindings == null) {
			monitoredDBBindings = new EDataTypeUniqueEList<Binding>(Binding.class, this,
					IUIBindingsPackage.BINDING__MONITORED_DB_BINDINGS);
		}
		return monitoredDBBindings;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<String> getErrorConditions() {
		if (errorConditions == null) {
			errorConditions = new EDataTypeUniqueEList<String>(String.class, this,
					IUIBindingsPackage.BINDING__ERROR_CONDITIONS);
		}
		return errorConditions;
	}

	@Override
	public void addErrorCondition(String error) {
		LogUtils.error(this, this + ": " + error, getCreationPoint());
		getErrorConditions().add(error);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public abstract Widget getWidget();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public abstract Control getControl();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<IArgumentProvider> getExtraArgumentProviders() {
		if (extraArgumentProviders == null) {
			extraArgumentProviders = new EObjectEList<IArgumentProvider>(IArgumentProvider.class, this,
					IUIBindingsPackage.BINDING__EXTRA_ARGUMENT_PROVIDERS);
		}
		return extraArgumentProviders;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case IUIBindingsPackage.BINDING__CONTEXT:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetContext((IBindingContext) otherEnd, msgs);
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
		case IUIBindingsPackage.BINDING__CONTEXT:
			return basicSetContext(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case IUIBindingsPackage.BINDING__CONTEXT:
			return eInternalContainer().eInverseRemove(this, IUIBindingsPackage.BINDING_CONTEXT__BINDINGS,
					IBindingContext.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case IUIBindingsPackage.BINDING__CONTEXT:
			return getContext();
		case IUIBindingsPackage.BINDING__STATE:
			return getState();
		case IUIBindingsPackage.BINDING__CHANGEABLE:
			return isChangeable();
		case IUIBindingsPackage.BINDING__CREATION_POINT:
			return getCreationPoint();
		case IUIBindingsPackage.BINDING__ID:
			return getId();
		case IUIBindingsPackage.BINDING__TYPE:
			return getType();
		case IUIBindingsPackage.BINDING__LABEL:
			return getLabel();
		case IUIBindingsPackage.BINDING__STATIC_DATA_TYPE:
			return getStaticDataType();
		case IUIBindingsPackage.BINDING__DATA_TYPE:
			return getDataType();
		case IUIBindingsPackage.BINDING__MODEL_ETYPE:
			return getModelEType();
		case IUIBindingsPackage.BINDING__MODEL_TYPE:
			return getModelType();
		case IUIBindingsPackage.BINDING__UI_TYPE:
			return getUIType();
		case IUIBindingsPackage.BINDING__DB_BINDINGS:
			return getDBBindings();
		case IUIBindingsPackage.BINDING__MONITORED_DB_BINDINGS:
			return getMonitoredDBBindings();
		case IUIBindingsPackage.BINDING__ERROR_CONDITIONS:
			return getErrorConditions();
		case IUIBindingsPackage.BINDING__WIDGET:
			return getWidget();
		case IUIBindingsPackage.BINDING__CONTROL:
			return getControl();
		case IUIBindingsPackage.BINDING__EXTRA_ARGUMENT_PROVIDERS:
			return getExtraArgumentProviders();
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
		case IUIBindingsPackage.BINDING__CONTEXT:
			setContext((IBindingContext) newValue);
			return;
		case IUIBindingsPackage.BINDING__STATE:
			setState((BindingState) newValue);
			return;
		case IUIBindingsPackage.BINDING__CREATION_POINT:
			setCreationPoint((Throwable) newValue);
			return;
		case IUIBindingsPackage.BINDING__ID:
			setId((String) newValue);
			return;
		case IUIBindingsPackage.BINDING__STATIC_DATA_TYPE:
			setStaticDataType((IBindingDataType) newValue);
			return;
		case IUIBindingsPackage.BINDING__DB_BINDINGS:
			getDBBindings().clear();
			getDBBindings().addAll((Collection<? extends Binding>) newValue);
			return;
		case IUIBindingsPackage.BINDING__MONITORED_DB_BINDINGS:
			getMonitoredDBBindings().clear();
			getMonitoredDBBindings().addAll((Collection<? extends Binding>) newValue);
			return;
		case IUIBindingsPackage.BINDING__ERROR_CONDITIONS:
			getErrorConditions().clear();
			getErrorConditions().addAll((Collection<? extends String>) newValue);
			return;
		case IUIBindingsPackage.BINDING__EXTRA_ARGUMENT_PROVIDERS:
			getExtraArgumentProviders().clear();
			getExtraArgumentProviders().addAll((Collection<? extends IArgumentProvider>) newValue);
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
		case IUIBindingsPackage.BINDING__CONTEXT:
			setContext((IBindingContext) null);
			return;
		case IUIBindingsPackage.BINDING__STATE:
			setState(STATE_EDEFAULT);
			return;
		case IUIBindingsPackage.BINDING__CREATION_POINT:
			setCreationPoint(CREATION_POINT_EDEFAULT);
			return;
		case IUIBindingsPackage.BINDING__ID:
			setId(ID_EDEFAULT);
			return;
		case IUIBindingsPackage.BINDING__STATIC_DATA_TYPE:
			setStaticDataType((IBindingDataType) null);
			return;
		case IUIBindingsPackage.BINDING__DB_BINDINGS:
			getDBBindings().clear();
			return;
		case IUIBindingsPackage.BINDING__MONITORED_DB_BINDINGS:
			getMonitoredDBBindings().clear();
			return;
		case IUIBindingsPackage.BINDING__ERROR_CONDITIONS:
			getErrorConditions().clear();
			return;
		case IUIBindingsPackage.BINDING__EXTRA_ARGUMENT_PROVIDERS:
			getExtraArgumentProviders().clear();
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
		case IUIBindingsPackage.BINDING__CONTEXT:
			return getContext() != null;
		case IUIBindingsPackage.BINDING__STATE:
			return state != STATE_EDEFAULT;
		case IUIBindingsPackage.BINDING__CHANGEABLE:
			return isChangeable() != CHANGEABLE_EDEFAULT;
		case IUIBindingsPackage.BINDING__CREATION_POINT:
			return CREATION_POINT_EDEFAULT == null ? creationPoint != null : !CREATION_POINT_EDEFAULT
					.equals(creationPoint);
		case IUIBindingsPackage.BINDING__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case IUIBindingsPackage.BINDING__TYPE:
			return TYPE_EDEFAULT == null ? getType() != null : !TYPE_EDEFAULT.equals(getType());
		case IUIBindingsPackage.BINDING__LABEL:
			return LABEL_EDEFAULT == null ? getLabel() != null : !LABEL_EDEFAULT.equals(getLabel());
		case IUIBindingsPackage.BINDING__STATIC_DATA_TYPE:
			return staticDataType != null;
		case IUIBindingsPackage.BINDING__DATA_TYPE:
			return getDataType() != null;
		case IUIBindingsPackage.BINDING__MODEL_ETYPE:
			return getModelEType() != null;
		case IUIBindingsPackage.BINDING__MODEL_TYPE:
			return getModelType() != null;
		case IUIBindingsPackage.BINDING__UI_TYPE:
			return getUIType() != null;
		case IUIBindingsPackage.BINDING__DB_BINDINGS:
			return dbBindings != null && !dbBindings.isEmpty();
		case IUIBindingsPackage.BINDING__MONITORED_DB_BINDINGS:
			return monitoredDBBindings != null && !monitoredDBBindings.isEmpty();
		case IUIBindingsPackage.BINDING__ERROR_CONDITIONS:
			return errorConditions != null && !errorConditions.isEmpty();
		case IUIBindingsPackage.BINDING__WIDGET:
			return WIDGET_EDEFAULT == null ? getWidget() != null : !WIDGET_EDEFAULT.equals(getWidget());
		case IUIBindingsPackage.BINDING__CONTROL:
			return CONTROL_EDEFAULT == null ? getControl() != null : !CONTROL_EDEFAULT.equals(getControl());
		case IUIBindingsPackage.BINDING__EXTRA_ARGUMENT_PROVIDERS:
			return extraArgumentProviders != null && !extraArgumentProviders.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String toString() {
		String baseType = getBaseType();
		if (hasArguments()) {
			for (final Entry<String, Object> e : getArguments().entrySet()) {
				String s = e.getValue() == null ? Messages.ValueBindingImpl_NullString : e.getValue().toString();
				if (s.length() > 15) {
					s = s.substring(0, 3) + "..." + s.substring(s.length() - 3); //$NON-NLS-1$
				}
				baseType += ", " + e.getKey() + "=" + s; //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		if (getState() != BindingState.OK) {
			baseType += ", STATE=" + getState();
		}

		return ClassUtils.getLastClassName(this) + "[" + baseType + "]#" + System.identityHashCode(this); //$NON-NLS-1$ //$NON-NLS-2$
	}

	protected String getBaseType() {
		final EClassifier modelEType = getModelEType();
		if (modelEType != null) return modelEType.getName();

		return "";
	}

	@Override
	public void updateBinding() {
		// Do nothing
	}

	@Override
	public void updateBinding(Object[] objects) {
		updateBinding();
	}
} // BindingImpl
