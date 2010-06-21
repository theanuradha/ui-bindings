/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.rcpcompany.uibindings.navigator.editors.internal;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.rcpcompany.uibindings.IUIBindingsPackage;
import com.rcpcompany.uibindings.navigator.editors.IEditiorModelType;
import com.rcpcompany.uibindings.navigator.editors.IEditor;
import com.rcpcompany.uibindings.navigator.editors.IEditorDescriptor;
import com.rcpcompany.uibindings.navigator.editors.IEditorManager;
import com.rcpcompany.uibindings.navigator.editors.IEditorsFactory;
import com.rcpcompany.uibindings.navigator.editors.IEditorsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EditorsPackageImpl extends EPackageImpl implements IEditorsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass editorManagerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass editiorModelTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass editorDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iEditorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringToModelTypeMapEntryEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.rcpcompany.uibindings.navigator.editors.IEditorsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EditorsPackageImpl() {
		super(eNS_URI, IEditorsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link IEditorsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static IEditorsPackage init() {
		if (isInited) return (IEditorsPackage)EPackage.Registry.INSTANCE.getEPackage(IEditorsPackage.eNS_URI);

		// Obtain or create and register package
		EditorsPackageImpl theEditorsPackage = (EditorsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EditorsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new EditorsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		IUIBindingsPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theEditorsPackage.createPackageContents();

		// Initialize created meta-data
		theEditorsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEditorsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(IEditorsPackage.eNS_URI, theEditorsPackage);
		return theEditorsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEditorManager() {
		return editorManagerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEditorManager_ModelTypes() {
		return (EReference)editorManagerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEditiorModelType() {
		return editiorModelTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEditiorModelType_Editors() {
		return (EReference)editiorModelTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEditiorModelType_ModelType() {
		return (EAttribute)editiorModelTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEditorDescriptor() {
		return editorDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEditorDescriptor_Id() {
		return (EAttribute)editorDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEditorDescriptor_Name() {
		return (EAttribute)editorDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEditorDescriptor_Priority() {
		return (EAttribute)editorDescriptorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEditorDescriptor_Factory() {
		return (EAttribute)editorDescriptorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEditorDescriptor_Image() {
		return (EAttribute)editorDescriptorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIEditor() {
		return iEditorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringToModelTypeMapEntry() {
		return stringToModelTypeMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringToModelTypeMapEntry_Key() {
		return (EAttribute)stringToModelTypeMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringToModelTypeMapEntry_Value() {
		return (EReference)stringToModelTypeMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IEditorsFactory getEditorsFactory() {
		return (IEditorsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		editorManagerEClass = createEClass(EDITOR_MANAGER);
		createEReference(editorManagerEClass, EDITOR_MANAGER__MODEL_TYPES);

		editiorModelTypeEClass = createEClass(EDITIOR_MODEL_TYPE);
		createEReference(editiorModelTypeEClass, EDITIOR_MODEL_TYPE__EDITORS);
		createEAttribute(editiorModelTypeEClass, EDITIOR_MODEL_TYPE__MODEL_TYPE);

		editorDescriptorEClass = createEClass(EDITOR_DESCRIPTOR);
		createEAttribute(editorDescriptorEClass, EDITOR_DESCRIPTOR__ID);
		createEAttribute(editorDescriptorEClass, EDITOR_DESCRIPTOR__NAME);
		createEAttribute(editorDescriptorEClass, EDITOR_DESCRIPTOR__PRIORITY);
		createEAttribute(editorDescriptorEClass, EDITOR_DESCRIPTOR__FACTORY);
		createEAttribute(editorDescriptorEClass, EDITOR_DESCRIPTOR__IMAGE);

		iEditorEClass = createEClass(IEDITOR);

		stringToModelTypeMapEntryEClass = createEClass(STRING_TO_MODEL_TYPE_MAP_ENTRY);
		createEAttribute(stringToModelTypeMapEntryEClass, STRING_TO_MODEL_TYPE_MAP_ENTRY__KEY);
		createEReference(stringToModelTypeMapEntryEClass, STRING_TO_MODEL_TYPE_MAP_ENTRY__VALUE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
		IUIBindingsPackage theUIBindingsPackage = (IUIBindingsPackage)EPackage.Registry.INSTANCE.getEPackage(IUIBindingsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(editorManagerEClass, IEditorManager.class, "EditorManager", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEditorManager_ModelTypes(), this.getStringToModelTypeMapEntry(), null, "modelTypes", null, 0, -1, IEditorManager.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(editiorModelTypeEClass, IEditiorModelType.class, "EditiorModelType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEditiorModelType_Editors(), this.getEditorDescriptor(), null, "editors", null, 0, -1, IEditiorModelType.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEditiorModelType_ModelType(), ecorePackage.getEString(), "modelType", null, 1, 1, IEditiorModelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(editorDescriptorEClass, IEditorDescriptor.class, "EditorDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEditorDescriptor_Id(), ecorePackage.getEString(), "id", null, 1, 1, IEditorDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEditorDescriptor_Name(), ecorePackage.getEString(), "name", null, 1, 1, IEditorDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEditorDescriptor_Priority(), ecorePackage.getEInt(), "priority", null, 1, 1, IEditorDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		EGenericType g1 = createEGenericType(theUIBindingsPackage.getCEObjectHolder());
		EGenericType g2 = createEGenericType(this.getIEditor());
		g1.getETypeArguments().add(g2);
		initEAttribute(getEditorDescriptor_Factory(), g1, "factory", null, 1, 1, IEditorDescriptor.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEditorDescriptor_Image(), theUIBindingsPackage.getCEResourceHolder(), "image", null, 1, 1, IEditorDescriptor.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iEditorEClass, IEditor.class, "IEditor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(stringToModelTypeMapEntryEClass, Map.Entry.class, "StringToModelTypeMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToModelTypeMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStringToModelTypeMapEntry_Value(), this.getEditiorModelType(), null, "value", null, 0, 1, Map.Entry.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //EditorsPackageImpl
