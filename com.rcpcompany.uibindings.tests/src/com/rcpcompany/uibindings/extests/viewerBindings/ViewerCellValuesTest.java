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
package com.rcpcompany.uibindings.extests.viewerBindings;

import static com.rcpcompany.test.utils.ui.UITestUtils.*;
import static org.junit.Assert.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rcpcompany.uibindings.IBindingContext;
import com.rcpcompany.uibindings.IViewerBinding;
import com.rcpcompany.uibindings.tests.shop.Shop;
import com.rcpcompany.uibindings.tests.shop.ShopFactory;
import com.rcpcompany.uibindings.tests.shop.ShopItem;
import com.rcpcompany.uibindings.tests.shop.ShopPackage;
import com.rcpcompany.uibindings.tests.utils.BaseUIBTestUtils;
import com.rcpcompany.uibindings.tests.utils.views.UIBTestView;
import com.rcpcompany.uibindings.utils.ITableCreator;

/**
 * Tests the basic value of cells.
 * 
 * @author Tonny Madsen, The RCP Company
 */
public class ViewerCellValuesTest {
	private Shop myShop;
	private ShopItem myShopItem1;
	private ShopItem myShopItem2;

	private UIBTestView myView;
	private Composite myBody;

	private IBindingContext myContext;
	private ITableCreator myCreator;

	@Before
	public void before() {
		BaseUIBTestUtils.resetAll();

		createShop();
		createView();

		myView.getSite().getPage().activate(myView);
	}

	/**
	 * Creates the shop itself
	 */
	public void createShop() {
		myShop = ShopFactory.eINSTANCE.createShop();

		myShopItem1 = ShopFactory.eINSTANCE.createShopItem();
		myShopItem1.setName("item 1");
		myShopItem1.setPrice(1f);
		myShopItem1.setForSale(true);

		myShopItem2 = ShopFactory.eINSTANCE.createShopItem();
		myShopItem2.setName("item 2");
		myShopItem2.setPrice(2f);
		myShopItem2.setForSale(false);

		myShop.getShopItems().add(myShopItem1);
		myShop.getShopItems().add(myShopItem2);
	}

	/**
	 * Creates the view
	 */
	public void createView() {
		myView = BaseUIBTestUtils.createUIBTestView(this);
		myBody = myView.getBody();

		myContext = IBindingContext.Factory.createContext(myView.getScrolledForm());
		myCreator = ITableCreator.Factory.create(myContext, myBody, SWT.NONE, myShop,
				ShopPackage.Literals.SHOP__SHOP_ITEMS);
		myCreator.addColumn("name(w=100)");
		myCreator.addColumn("price(w=100)").type("price-dummy"); // Speciel decorator with format
																	// "%,.3f"

		myContext.finish();
		yield();
	}

	@After
	public void disposeView() {
		if (myView != null) {
			myView.getSite().getPage().hideView(myView);
		}
	}

	/**
	 * Tests the values of all cells
	 */
	@Test
	public void testCellValuesRow0() {
		oneCell(0, 0, myShopItem1.getName());
		oneCell(1, 0, String.format("%,.3f", myShopItem1.getPrice()));
	}

	/**
	 * Tests the values of all cells
	 */
	@Test
	public void testCellValuesRow1() {
		oneCell(0, 1, myShopItem2.getName());
		oneCell(1, 1, String.format("%,.3f", myShopItem2.getPrice()));
	}

	/**
	 * Tests the value of the specified cell.
	 * 
	 * @param columnNo
	 * @param rowNo
	 * @param expectedText the expected text of the cell.
	 */
	public void oneCell(int columnNo, int rowNo, String expectedText) {
		// final Table table = myCreator.getTable();
		// assertNotNull(table);
		// final TableItem item = table.getItem(rowNo);
		// assertNotNull(item);
		//
		// assertEquals(expectedText, item.getText(columnNo + viewer.getFirstTableColumnOffset()));

		final IViewerBinding viewer = myCreator.getBinding();
		assertNotNull(viewer);

		assertEquals(expectedText, viewer.getCell(columnNo, rowNo, true).getDisplayText());
	}
}
