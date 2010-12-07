/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.rcpcompany.uibindings.internal.scripting;

import com.rcpcompany.uibindings.scripting.*;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.rcpcompany.uibindings.scripting.IScriptDependency;
import com.rcpcompany.uibindings.scripting.IScriptEngineDescriptor;
import com.rcpcompany.uibindings.scripting.IScriptEngineFactory;
import com.rcpcompany.uibindings.scripting.IScriptEnginePackage;
import com.rcpcompany.uibindings.scripting.IScriptEvaluationContext;
import com.rcpcompany.uibindings.scripting.IScriptExpression;
import com.rcpcompany.uibindings.scripting.IScriptManager;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class ScriptEngineFactoryImpl extends EFactoryImpl implements IScriptEngineFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static IScriptEngineFactory init() {
		try {
			IScriptEngineFactory theScriptEngineFactory = (IScriptEngineFactory)EPackage.Registry.INSTANCE.getEFactory("http://rcp-company.com/schemas/uibindings/scriptEngine.ecore"); 
			if (theScriptEngineFactory != null) {
				return theScriptEngineFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ScriptEngineFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptEngineFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case IScriptEnginePackage.SCRIPT_MANAGER: return createScriptManager();
			case IScriptEnginePackage.SCRIPT_ENGINE_DESCRIPTOR: return createScriptEngineDescriptor();
			case IScriptEnginePackage.SCRIPT_EVALUATION_CONTEXT: return createScriptEvaluationContext();
			case IScriptEnginePackage.SCRIPT_EXPRESSION: return createScriptExpression();
			case IScriptEnginePackage.SCRIPT_DEPENDENCY: return createScriptDependency();
			case IScriptEnginePackage.STRING_TO_SCRIPT_ENGINE_MAP_ENTRY: return (EObject)createStringToScriptEngineMapEntry();
			case IScriptEnginePackage.EOBJECT_TO_SCRIPT_ENGINE_MAP_ENTRY: return (EObject)createEObjectToScriptEngineMapEntry();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IScriptManager createScriptManager() {
		ScriptManagerImpl scriptManager = new ScriptManagerImpl();
		return scriptManager;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IScriptEngineDescriptor createScriptEngineDescriptor() {
		ScriptEngineDescriptorImpl scriptEngineDescriptor = new ScriptEngineDescriptorImpl();
		return scriptEngineDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IScriptEvaluationContext createScriptEvaluationContext() {
		ScriptEvaluationContextImpl scriptEvaluationContext = new ScriptEvaluationContextImpl();
		return scriptEvaluationContext;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IScriptExpression createScriptExpression() {
		ScriptExpressionImpl scriptExpression = new ScriptExpressionImpl();
		return scriptExpression;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IScriptDependency createScriptDependency() {
		ScriptDependencyImpl scriptDependency = new ScriptDependencyImpl();
		return scriptDependency;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, IScriptEngineDescriptor> createStringToScriptEngineMapEntry() {
		StringToScriptEngineMapEntryImpl stringToScriptEngineMapEntry = new StringToScriptEngineMapEntryImpl();
		return stringToScriptEngineMapEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<EObject, IScriptEvaluationContext> createEObjectToScriptEngineMapEntry() {
		EObjectToScriptEngineMapEntryImpl eObjectToScriptEngineMapEntry = new EObjectToScriptEngineMapEntryImpl();
		return eObjectToScriptEngineMapEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IScriptEnginePackage getScriptEnginePackage() {
		return (IScriptEnginePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static IScriptEnginePackage getPackage() {
		return IScriptEnginePackage.eINSTANCE;
	}

} // ScriptEngineFactoryImpl
