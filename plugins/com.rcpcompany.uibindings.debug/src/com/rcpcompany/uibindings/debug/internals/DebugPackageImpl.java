/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.rcpcompany.uibindings.debug.internals;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.rcpcompany.uibindings.debug.IDebugFactory;
import com.rcpcompany.uibindings.debug.IDebugPackage;
import com.rcpcompany.uibindings.debug.IScriptConsoleContext;
import com.rcpcompany.uibindings.scripting.IScriptEnginePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class DebugPackageImpl extends EPackageImpl implements IDebugPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass scriptConsoleContextEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package package URI
	 * value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init
	 * init()}, which also performs initialization of the package, or returns the registered
	 * package, if one already exists. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.rcpcompany.uibindings.debug.IDebugPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DebugPackageImpl() {
		super(eNS_URI, IDebugFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others
	 * upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link IDebugPackage#eINSTANCE} when that field is
	 * accessed. Clients should not invoke it directly. Instead, they should simply access that
	 * field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static IDebugPackage init() {
		if (isInited) return (IDebugPackage) EPackage.Registry.INSTANCE.getEPackage(IDebugPackage.eNS_URI);

		// Obtain or create and register package
		final DebugPackageImpl theDebugPackage = (DebugPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DebugPackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI) : new DebugPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		IScriptEnginePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theDebugPackage.createPackageContents();

		// Initialize created meta-data
		theDebugPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDebugPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(IDebugPackage.eNS_URI, theDebugPackage);
		return theDebugPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getScriptConsoleContext() {
		return scriptConsoleContextEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getScriptConsoleContext_Object() {
		return (EReference) scriptConsoleContextEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getScriptConsoleContext_Language() {
		return (EAttribute) scriptConsoleContextEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getScriptConsoleContext_Script() {
		return (EAttribute) scriptConsoleContextEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getScriptConsoleContext_Expression() {
		return (EReference) scriptConsoleContextEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getScriptConsoleContext_Result() {
		return (EAttribute) scriptConsoleContextEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getScriptConsoleContext_Exception() {
		return (EAttribute) scriptConsoleContextEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IDebugFactory getDebugFactory() {
		return (IDebugFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is guarded to have no affect on
	 * any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		scriptConsoleContextEClass = createEClass(SCRIPT_CONSOLE_CONTEXT);
		createEReference(scriptConsoleContextEClass, SCRIPT_CONSOLE_CONTEXT__OBJECT);
		createEAttribute(scriptConsoleContextEClass, SCRIPT_CONSOLE_CONTEXT__LANGUAGE);
		createEAttribute(scriptConsoleContextEClass, SCRIPT_CONSOLE_CONTEXT__SCRIPT);
		createEReference(scriptConsoleContextEClass, SCRIPT_CONSOLE_CONTEXT__EXPRESSION);
		createEAttribute(scriptConsoleContextEClass, SCRIPT_CONSOLE_CONTEXT__RESULT);
		createEAttribute(scriptConsoleContextEClass, SCRIPT_CONSOLE_CONTEXT__EXCEPTION);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This method is guarded to have
	 * no affect on any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		final IScriptEnginePackage theScriptEnginePackage = (IScriptEnginePackage) EPackage.Registry.INSTANCE
				.getEPackage(IScriptEnginePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(scriptConsoleContextEClass, IScriptConsoleContext.class, "ScriptConsoleContext", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScriptConsoleContext_Object(), ecorePackage.getEObject(), null, "object", null, 1, 1,
				IScriptConsoleContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptConsoleContext_Language(), ecorePackage.getEString(), "language", null, 1, 1,
				IScriptConsoleContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptConsoleContext_Script(), ecorePackage.getEString(), "script", null, 1, 1,
				IScriptConsoleContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScriptConsoleContext_Expression(), theScriptEnginePackage.getScriptExpression(), null,
				"expression", null, 0, 1, IScriptConsoleContext.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptConsoleContext_Result(), ecorePackage.getEString(), "result", null, 0, 1,
				IScriptConsoleContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptConsoleContext_Exception(), ecorePackage.getEString(), "exception", null, 0, 1,
				IScriptConsoleContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} // DebugPackageImpl
