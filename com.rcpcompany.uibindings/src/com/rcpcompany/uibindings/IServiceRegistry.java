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
package com.rcpcompany.uibindings;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The service registry is a basic registry that allows various services to
 * be registered with a base object - currently one of {@link IBindingContext},
 * {@link IViewerBinding}, {@link IColumnBinding} and {@link IValueBinding}. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link com.rcpcompany.uibindings.IServiceRegistry#getServices <em>Services</em>}</li>
 * </ul>
 * </p>
 * 
 * @see com.rcpcompany.uibindings.IUIBindingsPackage#getServiceRegistry()
 * @generated
 */
public interface IServiceRegistry extends EObject {
	/**
	 * Returns the service with the specified interface if it has been registered with the base
	 * object.
	 * 
	 * @param <T> the type of the service
	 * @param serviceClass the type of the service
	 * @return the service or <code>null</code>
	 */
	<T> T getService(Class<T> serviceClass);

	/**
	 * Registers the specified service with the base object.
	 * <p>
	 * If the object is already registered, then it is ignored.
	 * 
	 * @param <T> the type of the service
	 * @param serviceObject the service to register
	 */
	<T> void registerService(T serviceObject);

	/**
	 * Unregisters the specified service from the base object.
	 * 
	 * @param <T> the type of the service
	 * @param serviceObject the service to deregister
	 */
	<T> void unregisterService(T serviceObject);

	/**
	 * Returns the value of the '<em><b>Services</b></em>' attribute list. The list contents are of
	 * type {@link java.lang.Object}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Services</em>' attribute list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Services</em>' attribute list.
	 * @see com.rcpcompany.uibindings.IUIBindingsPackage#getServiceRegistry_Services()
	 * @generated
	 */
	EList<Object> getServices();

} // IServiceRegistry
