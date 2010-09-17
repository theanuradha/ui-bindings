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
package com.rcpcompany.uibindings.navigator.views;

import org.eclipse.core.databinding.observable.list.IObservableList;

/**
 * Abstract base class for {@link INavigatorBaseViewAdvisor}.
 * 
 * @author Tonny Madsen, The RCP Company
 */
public abstract class AbstractNavigatorBaseViewAdvisor implements INavigatorBaseViewAdvisor {

	@Override
	public abstract IObservableList getRootElements();

	@Override
	public String getTreeID() {
		return "";
	}
}
