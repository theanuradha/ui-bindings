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
package com.rcpcompany.uibindings.utils;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;

import com.rcpcompany.uibindings.IDisposable;

/**
 * Utility class that returns the long name for a specific object using bindings.
 * <p>
 * Backward compatible information...
 * 
 * @author Tonny Madsen, The RCP Company
 */
public interface IBindingObjectLongName extends IDisposable {
	/**
	 * Factory for {@link IBindingObjectInformation}.
	 */
	final class Factory {
		private Factory() {
		}

		/**
		 * Returns the long name for the specified object - or "&lt;null&gt;" if <code>null</code>.
		 * 
		 * @param obj the object
		 * @return the name or "&lt;null&gt;"
		 */
		public static String getLongName(EObject obj) {
			return IBindingObjectInformation.Factory.getLongName(obj);
		}

		/**
		 * Returns a new long name object for the specified object with the specified binding type
		 * 
		 * @param obj the object
		 * @param type the binding type
		 * @return long name object
		 */
		public static IBindingObjectLongName createLongName(EObject obj, String type) {
			return IBindingObjectInformation.Factory.createObjectInformation(obj, type);
		}

		/**
		 * Returns the long name for the objects in the specified selection concatenated together.
		 * <p>
		 * If the selection is not a structured selection or it is empty, an empty string is
		 * returned.
		 * <p>
		 * Any objects in the selection that is not an {@link EObject EMF object} is silently
		 * ignored.
		 * 
		 * @param selection the selection
		 * @return the name
		 */
		public static String getLongName(ISelection selection) {
			return IBindingObjectInformation.Factory.getLongName(selection);
		}
	}

	/**
	 * Returns the current name for this object.
	 * 
	 * @return the name
	 */
	String getName();
}
