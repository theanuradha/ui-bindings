package com.rcpcompany.utils.selection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * A number of utility methods used to manage selections.
 * 
 * @author Tonny Madsen, The RCP Company
 */
public class SelectionUtils {

	private static List EMPTY_LIST = new ArrayList();

	/**
	 * Computes and returns a list with the objects of the selection that are -
	 * or can adapt to - the specified base class.
	 * 
	 * @param <T>
	 *            the type of the baseClass
	 * @param selection
	 *            the selection to handle
	 * @param baseClass
	 *            the wanted base class
	 * @return a list with the found objects
	 */
	public static <T> List<T> computeSelection(ISelection selection, Class<T> baseClass) {
		if (!(selection instanceof IStructuredSelection)) {
			return EMPTY_LIST;
		}
		final IStructuredSelection ss = (IStructuredSelection) selection;
		List<T> list = null;
		for (Iterator e = ss.iterator(); e.hasNext();) {
			Object next = e.next();
			T c = (T) Platform.getAdapterManager().getAdapter(next, baseClass);
			if (c != null) {
				// OK
			} else if (baseClass.isInstance(next)) {
				c = (T) next;
			} else if (next instanceof IAdaptable) {
				c = (T) ((IAdaptable) next).getAdapter(baseClass);
			}
			if (c != null) {
				if (list == null)
					list = new ArrayList<T>(ss.size());
				list.add(c);
			}
		}
		if (list == null)
			return EMPTY_LIST;
		return list;
	}
}