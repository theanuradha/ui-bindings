package com.rcpcompany.uibindings.shop.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.rcpcompany.uibindings.tests.shop.Shop;
import com.rcpcompany.uibindings.tests.shop.ShopFactory;
import com.rcpcompany.uibindings.tests.shop.ShopPackage;
import com.rcpcompany.uibindings.utils.IBindingContextSelectionProvider;
import com.rcpcompany.uibindings.utils.IFormCreator;
import com.rcpcompany.uibindings.utils.ITableCreator;

public class OrderView extends ViewPart {

	private IFormCreator myForm;

	@Override
	public void createPartControl(Composite parent) {
		final Shop shop = ShopFactory.eINSTANCE.getShop();
		myForm = IFormCreator.Factory.createScrolledForm(shop, parent, "Orders");

		final ITableCreator orderTable = myForm.addTableCreator(ShopPackage.Literals.SHOP__ORDERS, true,
				ITableCreator.FILTER);

		orderTable.addColumn("no(w=40)");
		orderTable.addColumn("customer(w=150)");
		orderTable.addColumn("price(w=50)");

		final IFormCreator detailsSection = myForm.addSection("Items", orderTable.getBinding().getSingleSelection(),
				true);
		detailsSection.addObjectMessages();
		final ITableCreator detailsTable = ITableCreator.Factory.create(detailsSection.getContext(), detailsSection
				.addComposite(true, true), ITableCreator.FILTER, orderTable.getBinding().getSingleSelection(),
				ShopPackage.Literals.ORDER__ITEMS);
		// final ITableCreator detailsTable = detailsSection.addTableCreator(ShopPackage.Literals.ORDER__ITEMS, true,
		// SWT.SEARCH);

		detailsTable.addColumn("no(w=40)");
		detailsTable.addColumn("count(w=40)");
		detailsTable.addColumn("item.name(w=200)");
		detailsTable.addColumn("item.price(w=100)");

		myForm.finish();
		IBindingContextSelectionProvider.Factory.adapt(myForm.getContext(), getSite());
	}

	@Override
	public void setFocus() {
		myForm.setFocus();
	}
}