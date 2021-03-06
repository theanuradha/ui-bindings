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

import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;

/**
 * This interface is used by all classes that can provide "semi-constant" arguments to the bindings.
 * <p>
 * It is primary arguments declared in <code>plugin.xml</code> - in which case the value is an
 * {@link IConfigurationElement} - but it can also be arguments provided via a
 * {@link IModelArgumentMediator}.
 * <p>
 * If the value is an {@link IConfigurationElement}, this registry element will either have an
 * attribute with the key name or alternative have the attributes <code>name</code> and
 * <code>value</code> with the value.
 * 
 * @author Tonny Madsen, The RCP Company
 */
public interface IArgumentProvider {
	/**
	 * Returns a map with all the declared arguments.
	 * 
	 * @return the map
	 */
	Map<String, Object> getArguments();

	/**
	 * Returns whether this argument provider has any declared arguments.
	 * 
	 * @return <code>true</code> if arguments are declared.
	 */
	boolean hasArguments();
} // IArgumentProvider
