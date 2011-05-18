/*******************************************************************************
 * Copyright (c) 2017, 2011 The RCP Company and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     The RCP Company - initial API and implementation
 *******************************************************************************/
package com.rcpcompany.uibindings.extests.quickfixes;

import static com.rcpcompany.uibindings.extests.BaseTestUtils.*;
import static org.junit.Assert.*;

import org.eclipse.swt.widgets.Text;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rcpcompany.uibinding.tests.model.TestModelFactory;
import com.rcpcompany.uibinding.tests.model.TestObject;
import com.rcpcompany.uibindings.Constants;
import com.rcpcompany.uibindings.IManager;
import com.rcpcompany.uibindings.IValueBinding;
import com.rcpcompany.uibindings.TextCommitStrategy;
import com.rcpcompany.uibindings.extests.UIBindingsTestUtils;
import com.rcpcompany.uibindings.extests.views.TestView;
import com.rcpcompany.uibindings.internal.bindingMessages.ValueBindingMessageImageDecorator;
import com.rcpcompany.uibindings.utils.IFormCreator;
import com.rcpcompany.uibindings.validators.EValidatorAdapter;
import com.rcpcompany.uibindings.validators.IValidatorAdapterManager;

/**
 * Tests that the correct nameing quickfixes are generated
 * <p>
 * Depends on:
 * <ul>
 * <li></li>
 * </ul>
 * 
 * @author Tonny Madsen, The RCP Company
 */
public class DirectoryNameQuickfixTest {

	private int VD;
	private TestObject myObject;
	private TestView myView;
	private IFormCreator myForm;
	private IValueBinding myBinding;
	private ValueBindingMessageImageDecorator myMessageDecorator;
	private final IValidatorAdapterManager myValidatorManager = IValidatorAdapterManager.Factory.getManager();
	private final EValidatorAdapter myValidationAdapter = new EValidatorAdapter();

	@Before
	public void setup() {
		resetAll();
		IManager.Factory.getManager().setTextCommitStrategy(TextCommitStrategy.ON_MODIFY);
		VD = IManager.Factory.getManager().getValidationDelay();

		myObject = TestModelFactory.eINSTANCE.createTestObject();

		myView = UIBindingsTestUtils.createUIBTestView(this);
		myForm = myView.createFormCreator(myObject);

		myBinding = myForm.addField("text(w=100)").type(Constants.TYPE_DIRECTORY_NAME)
				.arg(Constants.ARG_EXTENSIONS, "glob:/Windows/**");

		myForm.finish();
		yield();

		myMessageDecorator = myBinding.getService(ValueBindingMessageImageDecorator.class);

		myValidatorManager.addRoot(myObject, myValidationAdapter);
	}

	@After
	public void disposeView() {
		if (myView != null) {
			myView.getSite().getPage().hideView(myView);
		}
	}

	@Test
	public void testValue() {
		final Text t = (Text) myBinding.getUIAttribute().getFieldAssistControl();
		assertNotNull(t);

		assertNotNull(myMessageDecorator.getQuickfixes());
		assertEquals(0, myMessageDecorator.getQuickfixes().size());

		assertNotNull(myMessageDecorator.getMessages());
		assertEquals(0, myMessageDecorator.getMessages().size());

		// // No messages or quickfixes
		// t.setText("c:\\Windows\\");
		// sleep(VD + 200);
		// assertEquals(0, myMessageDecorator.getMessages().size());
		// assertEquals(0, myMessageDecorator.getQuickfixes().size());
		//
		// // No quickfixes
		// t.setText("n");
		// sleep(VD + 200);
		// assertEquals(1, myMessageDecorator.getMessages().size());
		// assertEquals(0, myMessageDecorator.getQuickfixes().size());

		// Remove part of name for file that exist
		t.setText("c:/Windows/notepad.exe");
		sleep(VD + 200);
		assertEquals(1, myMessageDecorator.getMessages().size());
		assertEquals(1, myMessageDecorator.getQuickfixes().size());
		assertEquals("Remove last file name", myMessageDecorator.getQuickfixes().get(0).getLabel());

		// extra letter
		t.setText("c:/Wind2ows/");
		sleep(VD + 200);
		assertEquals(1, myMessageDecorator.getMessages().size());
		assertEquals(1, myMessageDecorator.getQuickfixes().size());
		assertEquals("Remove extra letter", myMessageDecorator.getQuickfixes().get(0).getLabel());
	}
}
