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
package com.rcpcompany.uibindings.grid;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.grid.GridItem;

import com.rcpcompany.uibindings.IDisposable;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Binding Row Information</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link com.rcpcompany.uibindings.grid.IGridBindingRowInformation#getGrid <em>Grid</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.grid.IGridBindingRowInformation#getId <em>Id</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.grid.IGridBindingRowInformation#getColumnCells <em>Column
 * Cells</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.grid.IGridBindingRowInformation#getGridItem <em>Grid Item
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see com.rcpcompany.uibindings.grid.IGridPackage#getGridBindingRowInformation()
 * @generated
 */
public interface IGridBindingRowInformation extends EObject, IDisposable {
	/**
	 * Returns the value of the '<em><b>Grid</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grid</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Grid</em>' reference.
	 * @see com.rcpcompany.uibindings.grid.IGridPackage#getGridBindingRowInformation_Grid()
	 * @generated
	 */
	IGridBinding getGrid();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see com.rcpcompany.uibindings.grid.IGridPackage#getGridBindingRowInformation_Id()
	 * @generated
	 */
	Object getId();

	/**
	 * Returns the value of the '<em><b>Column Cells</b></em>' reference list. The list contents are
	 * of type {@link com.rcpcompany.uibindings.grid.IGridBindingCellInformation}. It is
	 * bidirectional and its opposite is '
	 * {@link com.rcpcompany.uibindings.grid.IGridBindingCellInformation#getRow <em>Row</em>}'. <!--
	 * begin-user-doc -->
	 * <p>
	 * Please note that the order of cells object has no meaning.
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Column Cells</em>' reference list.
	 * @see com.rcpcompany.uibindings.grid.IGridPackage#getGridBindingRowInformation_ColumnCells()
	 * @see com.rcpcompany.uibindings.grid.IGridBindingCellInformation#getRow
	 * @generated
	 */
	EList<IGridBindingCellInformation> getColumnCells();

	/**
	 * Returns the value of the '<em><b>Grid Item</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grid Item</em>' attribute isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Grid Item</em>' attribute.
	 * @see com.rcpcompany.uibindings.grid.IGridPackage#getGridBindingRowInformation_GridItem()
	 * @generated
	 */
	GridItem getGridItem();

	/**
	 * Returns the position of this row in the grid. Top row (a header) is <code>0</code>.
	 * <p>
	 * Includes column header columns (<code>GridUtils.isHeader(r.getId())</code>).
	 * 
	 * @param visualModel <code>true</code> if the visual model should be used rather than the
	 *            logical model
	 * 
	 * @return the position
	 */
	int getPosition(boolean visualModel);
} // IGridBindingRowInformation
