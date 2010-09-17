/*******************************************************************************
 * Copyright (c) 2007, 2010 The RCP Company and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     The RCP Company - initial API and implementation
 *******************************************************************************/
package com.rcpcompany.uibindings.units;

import com.rcpcompany.uibindings.IValueBinding;

/**
 * The context interface for {@link IUnitBindingSupport} methods.
 * 
 * @author Tonny Madsen, The RCP Company
 */
public interface IUnitBindingSupportContext {
	/**
	 * Returns the binding for the adapter method.
	 * 
	 * @return the binding
	 */
	IValueBinding getBinding();
}
