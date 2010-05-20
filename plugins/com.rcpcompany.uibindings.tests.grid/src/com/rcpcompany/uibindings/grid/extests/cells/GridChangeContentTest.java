package com.rcpcompany.uibindings.grid.extests.cells;

import static com.rcpcompany.uibindings.extests.BaseTestUtils.createTestView;
import static com.rcpcompany.uibindings.extests.BaseTestUtils.postKeyStroke;
import static com.rcpcompany.uibindings.extests.BaseTestUtils.resetAll;
import static com.rcpcompany.uibindings.extests.BaseTestUtils.yield;
import static com.rcpcompany.uibindings.grid.extests.GridBaseTestUtils.createTestGrid;
import static com.rcpcompany.uibindings.grid.extests.GridBaseTestUtils.postMouse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.swt.SWT;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rcpcompany.uibinding.tests.model.TestGrid;
import com.rcpcompany.uibinding.tests.model.TestGridCell;
import com.rcpcompany.uibinding.tests.model.TestGridColumn;
import com.rcpcompany.uibinding.tests.model.TestGridRow;
import com.rcpcompany.uibinding.tests.model.TestModelPackage;
import com.rcpcompany.uibindings.IBindingContext;
import com.rcpcompany.uibindings.IManager;
import com.rcpcompany.uibindings.extests.views.TestView;
import com.rcpcompany.uibindings.grid.IGridBinding;
import com.rcpcompany.uibindings.grid.IGridBindingCellInformation;
import com.rcpcompany.uibindings.grid.IGridModel;
import com.rcpcompany.uibindings.grid.extests.models.TestGridGridModel;

/**
 * Basic test of cell binding: changes in the values of the model and ui is reflected correctly.
 * 
 * @author Tonny Madsen, The RCP Company
 */
public class GridChangeContentTest {

	private TestView myView;
	private Grid myGrid;
	private IGridBinding myGridBinding;

	private TestGrid myTestGrid;
	private TestGridGridModel myModel;

	@Before
	public void setup() {
		resetAll();

		createModel();
		createView();
	}

	private void createModel() {
		myTestGrid = createTestGrid();
		myModel = new TestGridGridModel(IManager.Factory.getManager().getEditingDomain(), myTestGrid,
				TestModelPackage.Literals.TEST_GRID_CELL__DETAILS, null);
	}

	private void createView() {
		myView = createTestView(this);

		myGrid = new Grid(myView.getBody(), SWT.NONE);
		final IBindingContext context = IBindingContext.Factory.createContext(myView.getBody());
		myGridBinding = IGridBinding.Factory.createGrid(context, myGrid, myModel);
		context.finish();
		yield();

		myView.getBody().layout();

		yield();
	}

	@After
	public void disposeView() {
		if (myView != null) {
			myView.getSite().getPage().hideView(myView);
		}
	}

	@Test
	public void testModelToUIContent() {
		final TestGridCell modelCell = myTestGrid.getColumns().get(0).getCells().get(0);
		final IGridBindingCellInformation gridCell = myGridBinding.getCell(0, 0);
		yield();
		assertEquals(modelCell.getDetails(), gridCell.getDisplayText());

		modelCell.setDetails("hello");
		yield();
		assertEquals(modelCell.getDetails(), gridCell.getDisplayText());
	}

	@Test
	public void testModelToUIColumnHeader() {
		final TestGridColumn modelColumn = myTestGrid.getColumns().get(0);
		final IGridBindingCellInformation gridCell = myGridBinding.getCell(modelColumn, IGridModel.HEADER1);
		yield();
		assertEquals(modelColumn.getName(), gridCell.getDisplayText());

		modelColumn.setName("hello");
		yield();
		assertEquals(modelColumn.getName(), gridCell.getDisplayText());
	}

	@Test
	public void testModelToUIRowHeader() {
		final TestGridRow modelRow = myTestGrid.getRows().get(0);
		final IGridBindingCellInformation gridCell = myGridBinding.getCell(IGridModel.HEADER1, modelRow);
		yield();
		assertEquals(modelRow.getNumber() + "", gridCell.getDisplayText());

		modelRow.setNumber(1000);
		yield();
		assertEquals(modelRow.getNumber() + "", gridCell.getDisplayText());
	}

	@Test
	public void testUIToModel() {
		assertTrue(IManager.Factory.getManager().isEditCellSingleClick());
		final TestGridCell modelCell = myTestGrid.getColumns().get(1).getCells().get(0);
		final IGridBindingCellInformation gridCell = myGridBinding.getCell(1, 0);
		final String originalValue = modelCell.getDetails();
		yield();
		assertEquals(originalValue, gridCell.getDisplayText());
		assertTrue(!myGridBinding.isEditing());

		postMouse(myGrid, 1, 0);

		assertTrue(myGridBinding.isEditing());
		assertEquals(gridCell, myGridBinding.getFocusCell());

		postKeyStroke(myGrid, "4");
		postKeyStroke(myGrid, "2");
		postKeyStroke(myGrid, "ENTER");

		assertTrue(!myGridBinding.isEditing());
		assertEquals(gridCell, myGridBinding.getFocusCell());

		yield();
		assertEquals("42", modelCell.getDetails());
		assertEquals("42", gridCell.getDisplayText());
	}
}
