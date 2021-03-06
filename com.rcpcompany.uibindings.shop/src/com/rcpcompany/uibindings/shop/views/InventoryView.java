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
package com.rcpcompany.uibindings.shop.views;

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.part.ViewPart;

import com.rcpcompany.uibindings.Constants;
import com.rcpcompany.uibindings.IBindingContext;
import com.rcpcompany.uibindings.IColumnBinding;
import com.rcpcompany.uibindings.IValueBinding;
import com.rcpcompany.uibindings.observables.CountObservableValue;
import com.rcpcompany.uibindings.tests.shop.ShopFactory;
import com.rcpcompany.uibindings.tests.shop.ShopItem;
import com.rcpcompany.uibindings.tests.shop.ShopPackage;
import com.rcpcompany.uibindings.utils.EditingDomainUtils;
import com.rcpcompany.uibindings.utils.IBindingContextSelectionProvider;
import com.rcpcompany.uibindings.utils.IDnDSupport;
import com.rcpcompany.uibindings.utils.IFormChooser;
import com.rcpcompany.uibindings.utils.IFormChooserCreator;
import com.rcpcompany.uibindings.utils.IFormCreator;
import com.rcpcompany.uibindings.utils.ITableCreator;

/**
 * The inventory view.
 * 
 * @author Tonny Madsen, The RCP Company
 */
public class InventoryView extends ViewPart {

	private Table myTable;
	private IFormCreator myForm;

	@Override
	public void createPartControl(Composite parent) {
		myForm = IFormCreator.Factory.createScrolledForm(
				ShopFactory.eINSTANCE.getShop(EditingDomainUtils.getEditingDomain()), parent, "Inventory");

		final ITableCreator table = myForm.addTableCreator(ShopPackage.Literals.SHOP__SHOP_ITEMS, true,
				ITableCreator.FILTER | ITableCreator.RESIZE);
		table.addColumn("name(w=20em)");
		table.addColumn("forSale(w=2em)");
		table.addColumn("group(w=10em)");
		table.addColumn("price(w=10em)");
		table.addColumn("properties{name='type': value}(w=10em,label='Type')");

		final IFormCreator pSection = myForm.addSection("Properties", table.getBinding().getSingleSelection());
		final ITableCreator pTable = pSection.addTableCreator(ShopPackage.Literals.SHOP_ITEM__PROPERTIES, true,
				ITableCreator.FILTER | ITableCreator.RESIZE);
		pTable.addColumn("name(w=20em)");
		pTable.addColumn("value(w=20em)");

		final IObservableFactory usesFactory = new IObservableFactory() {
			@Override
			public IObservable createObservable(Object target) {
				final ShopItem si = (ShopItem) target;
				return new CountObservableValue(EMFObservables.observeList(si,
						ShopPackage.Literals.SHOP_ITEM__ORDER_ITEMS));
			}
		};
		final IColumnBinding orders = table.addColumn("__NONE__(w=10em,a=r,label='Orders')")
				.model(usesFactory, EcorePackage.Literals.EINT).readonly();
		// table.addColumn("information(w=16em,ww=200)").dynamic();

		myForm.addField(orders.getColumnVisibility(), SWT.NONE).label("Show Orders");

		final IFormCreator detailsForm = myForm.addSection("Details", table.getBinding().getSingleSelection());
		detailsForm.addObjectMessages();

		detailsForm.addField("name(w=20em)");
		detailsForm.addField("forSale(w=4em)");
		detailsForm.addField("group(w=10em)");
		detailsForm.addField("price(w=10em)");
		// final IValueBinding advPriceBinding = UIBXtextUtils.createEditorBinding(detailsForm,
		// "advancedPrice",
		// getXTextInjectorModule());
		// final Composite c = (Composite) advPriceBinding.getControl();
		// final TableWrapData layoutData = (TableWrapData) c.getLayoutData();

		detailsForm.addField("properties{name='type': value}(w=10em,label='Type')");

		final IObservableList list = WritableList.withElementType(EClass.class);
		list.add(ShopPackage.Literals.SHOP_ITEM_DESCRIPTION);
		list.add(ShopPackage.Literals.SHOP_ITEM_URL);
		final IValueBinding discriminant = detailsForm.addField("information")
				.arg(Constants.ARG_PREFERRED_CONTROL, CCombo.class.getName()).type("eobjectCreator").validValues(list);

		final IFormChooser chooser = myForm.addFormChooser(discriminant);
		chooser.addFormEClass(ShopPackage.Literals.SHOP_ITEM_DESCRIPTION, new IFormChooserCreator() {
			@Override
			public void createForm(IBindingContext context, IObservableValue discriminant, Composite parent) {
				final IFormCreator sub = detailsForm.subForm(parent);
				final IObservableValue subValue = new WritableValue(discriminant.getValue(),
						ShopPackage.Literals.SHOP_ITEM_DESCRIPTION);
				sub.addField(subValue, "description");
				sub.finish();
			}
		});
		chooser.addFormEClass(ShopPackage.Literals.SHOP_ITEM_URL, new IFormChooserCreator() {
			@Override
			public void createForm(IBindingContext context, IObservableValue discriminant, Composite parent) {
				final IFormCreator sub = myForm.subForm(parent);
				final IObservableValue subValue = new WritableValue(discriminant.getValue(),
						ShopPackage.Literals.SHOP_ITEM_URL);
				sub.addField(subValue, "url(label='URL')");
				sub.finish();
			}
		});

		myForm.finish();
		IBindingContextSelectionProvider.Factory.adapt(myForm.getContext(), getSite());
		IDnDSupport.Factory.installOn(myForm.getContext());
	}

	@Override
	public void setFocus() {
		myForm.setFocus();
	}
}
