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

package org.eclipse.jface.viewers;

import java.util.List;

import org.eclipse.jface.viewers.CellEditor.LayoutData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * This is an editor-implementation for {@link Table}.
 * <p>
 * <em>NOTE</em>: Special implement for UI Bindings that extends
 * {@link #processTraverseEvent(int, ViewerRow, org.eclipse.swt.events.TraverseEvent)}. The original
 * {@link TableViewerEditor} is final, therefore this copy.
 * 
 * @since 1.1
 * 
 */
public class MyTableViewerEditor extends ColumnViewerEditor {
	/**
	 * This viewer's table editor.
	 */
	private final TableEditor tableEditor;

	private final SWTFocusCellManager focusCellManager;

	private final int myFeature;

	private final TableViewer myViewer;

	/**
	 * @param viewer the viewer the editor is attached to
	 * @param focusCellManager the cell focus manager if one used or <code>null</code>
	 * @param editorActivationStrategy the strategy used to decide about the editor activation
	 * @param feature the feature mask
	 */
	MyTableViewerEditor(TableViewer viewer, SWTFocusCellManager focusCellManager,
			ColumnViewerEditorActivationStrategy editorActivationStrategy, int feature) {
		super(viewer, editorActivationStrategy, feature);
		myViewer = viewer;
		myFeature = feature;
		tableEditor = new TableEditor(viewer.getTable());
		this.focusCellManager = focusCellManager;
	}

	/**
	 * Create a customized editor with focusable cells.
	 * 
	 * @param viewer the viewer the editor is created for
	 * @param focusCellManager the cell focus manager if one needed else <code>null</code>
	 * @param editorActivationStrategy activation strategy to control if an editor activated
	 * @param feature bit mask controlling the editor
	 *            <ul>
	 *            <li>{@link ColumnViewerEditor#DEFAULT}</li>
	 *            <li>{@link ColumnViewerEditor#TABBING_CYCLE_IN_ROW}</li>
	 *            <li>{@link ColumnViewerEditor#TABBING_HORIZONTAL}</li>
	 *            <li>{@link ColumnViewerEditor#TABBING_MOVE_TO_ROW_NEIGHBOR}</li>
	 *            <li>{@link ColumnViewerEditor#TABBING_VERTICAL}</li>
	 *            </ul>
	 * @see #create(TableViewer, ColumnViewerEditorActivationStrategy, int)
	 */
	public static void create(TableViewer viewer, SWTFocusCellManager focusCellManager,
			ColumnViewerEditorActivationStrategy editorActivationStrategy, int feature) {
		final MyTableViewerEditor editor = new MyTableViewerEditor(viewer, focusCellManager, editorActivationStrategy,
				feature);
		viewer.setColumnViewerEditor(editor);
		if (focusCellManager != null) {
			focusCellManager.init();
		}
	}

	/**
	 * Create a customized editor whose activation process is customized.
	 * 
	 * @param viewer the viewer the editor is created for
	 * @param editorActivationStrategy activation strategy to control if an editor activated
	 * @param feature bit mask controlling the editor
	 *            <ul>
	 *            <li>{@link ColumnViewerEditor#DEFAULT}</li>
	 *            <li>{@link ColumnViewerEditor#TABBING_CYCLE_IN_ROW}</li>
	 *            <li>{@link ColumnViewerEditor#TABBING_HORIZONTAL}</li>
	 *            <li>{@link ColumnViewerEditor#TABBING_MOVE_TO_ROW_NEIGHBOR}</li>
	 *            <li>{@link ColumnViewerEditor#TABBING_VERTICAL}</li>
	 *            </ul>
	 */
	public static void create(TableViewer viewer, ColumnViewerEditorActivationStrategy editorActivationStrategy,
			int feature) {
		create(viewer, null, editorActivationStrategy, feature);
	}

	@Override
	protected void setEditor(Control w, Item item, int columnNumber) {
		tableEditor.setEditor(w, (TableItem) item, columnNumber);
	}

	@Override
	protected void setLayoutData(LayoutData layoutData) {
		tableEditor.grabHorizontal = layoutData.grabHorizontal;
		tableEditor.horizontalAlignment = layoutData.horizontalAlignment;
		tableEditor.minimumWidth = layoutData.minimumWidth;
		tableEditor.verticalAlignment = layoutData.verticalAlignment;

		if (layoutData.minimumHeight != SWT.DEFAULT) {
			tableEditor.minimumHeight = layoutData.minimumHeight;
		}
	}

	@Override
	public ViewerCell getFocusCell() {
		if (focusCellManager != null) return focusCellManager.getFocusCell();

		return super.getFocusCell();
	}

	@Override
	protected void updateFocusCell(ViewerCell focusCell, ColumnViewerEditorActivationEvent event) {
		// Update the focus cell when we activated the editor with these 2
		// events
		if (event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC
				|| event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL) {

			final List l = getViewer().getSelectionFromWidget();

			if (!l.contains(focusCell.getElement())) {
				getViewer().setSelection(new StructuredSelection(focusCell.getElement()), true);
			}

			// Set the focus cell after the selection is updated because else
			// the cell is not scrolled into view
			if (focusCellManager != null) {
				focusCellManager.setFocusCell(focusCell);
			}
		}
	}

	// @Override
	// protected void processTraverseEvent(int columnIndex, ViewerRow row, TraverseEvent event) {
	//
	// ViewerCell cell2edit = null;
	//
	// if (event.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
	// event.doit = false;
	//
	// if ((event.stateMask & SWT.CTRL) == SWT.CTRL && (myFeature & TABBING_VERTICAL) ==
	// TABBING_VERTICAL) {
	// cell2edit = searchCellAboveBelow(row, myViewer, columnIndex, true);
	// } else if ((myFeature & TABBING_HORIZONTAL) == TABBING_HORIZONTAL) {
	// cell2edit = searchPreviousCell(row, row.getCell(columnIndex), row.getCell(columnIndex),
	// myViewer);
	// }
	// } else if (event.detail == SWT.TRAVERSE_TAB_NEXT) {
	// event.doit = false;
	//
	// if ((event.stateMask & SWT.CTRL) == SWT.CTRL && (myFeature & TABBING_VERTICAL) ==
	// TABBING_VERTICAL) {
	// cell2edit = searchCellAboveBelow(row, myViewer, columnIndex, false);
	// } else if ((myFeature & TABBING_HORIZONTAL) == TABBING_HORIZONTAL) {
	// cell2edit = searchNextCell(row, row.getCell(columnIndex), row.getCell(columnIndex),
	// myViewer);
	// }
	// }
	//
	// if (cell2edit != null) {
	//
	// myViewer.getControl().setRedraw(false);
	// final ColumnViewerEditorActivationEvent acEvent = new
	// ColumnViewerEditorActivationEvent(cell2edit, event);
	// myViewer.triggerEditorActivationEvent(acEvent);
	// myViewer.getControl().setRedraw(true);
	// }
	// }
}
