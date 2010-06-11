package com.rcpcompany.uibindings.extests.bindings;

import static com.rcpcompany.uibindings.extests.BaseTestUtils.createTestView;
import static com.rcpcompany.uibindings.extests.BaseTestUtils.resetAll;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rcpcompany.uibindings.IBindingContext;
import com.rcpcompany.uibindings.IManager;
import com.rcpcompany.uibindings.UIBindingsUtils;
import com.rcpcompany.uibindings.extests.views.TestView;
import com.rcpcompany.uibindings.tests.shop.Shop;
import com.rcpcompany.uibindings.tests.shop.ShopFactory;
import com.rcpcompany.uibindings.utils.IFormCreator;

/**
 * Tests that the editing domain of a context is correct.
 * 
 * @author Tonny Madsen, The RCP Company
 */
public class ContextEditingDomainTest {

	private TestView myView;
	private IFormCreator myForm;

	@Before
	public void before() {
		resetAll();

		createView();

		myView.getSite().getPage().activate(myView);
	}

	/**
	 * Creates the view
	 */
	public void createView() {
		myView = createTestView(this);

		final Shop shop = ShopFactory.eINSTANCE.createShop();

		myForm = myView.createFormCreator(shop);
		myForm.finish();
	}

	@After
	public void disposeView() {
		if (myView != null) {
			myView.getSite().getPage().hideView(myView);
		}
	}

	@Test
	public void testED() {
		final IManager manager = IManager.Factory.getManager();
		final IBindingContext context = myForm.getContext();

		assertEquals(manager.getEditingDomain(), context.getEditingDomain());

		final EditingDomain newManagerEditingDomain = UIBindingsUtils.createEditingDomain();
		assertNotSame(newManagerEditingDomain, manager.getEditingDomain());

		manager.setEditingDomain(newManagerEditingDomain);
		assertEquals(newManagerEditingDomain, manager.getEditingDomain());
		assertEquals(manager.getEditingDomain(), context.getEditingDomain());

		final EditingDomain newContextEditingDomain = UIBindingsUtils.createEditingDomain();
		assertNotSame(newContextEditingDomain, manager.getEditingDomain());

		context.setEditingDomain(newContextEditingDomain);
		assertEquals(newContextEditingDomain, context.getEditingDomain());
		assertNotSame(manager.getEditingDomain(), context.getEditingDomain());

		context.setEditingDomain(null);
		assertEquals(manager.getEditingDomain(), context.getEditingDomain());
	}
}