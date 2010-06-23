package com.rcpcompany.uibindings.navigator.extests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.rcpcompany.uibindings.navigator.extests.editors.EditorPartDescriptorTest;
import com.rcpcompany.uibindings.navigator.extests.editors.EditorPartLifecycleTests;
import com.rcpcompany.uibindings.navigator.extests.editors.ShowViewTests;
import com.rcpcompany.uibindings.navigator.extests.manager.NavigatorModelManagerTest;
import com.rcpcompany.uibindings.navigator.extests.manager.PreferenceTests;
import com.rcpcompany.uibindings.navigator.internal.views.EditorPartFactoryTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({

/*
 * All the basic UI Bindings tests
 */
// AllBindingTests.class,

		/*
		 * Simple manager tests
		 */
		NavigatorModelManagerTest.class,

		/*
		 * Editor retrieval tests
		 */
		EditorPartDescriptorTest.class, PreferenceTests.class,

		/*
		 * View tests
		 */
		ShowViewTests.class, EditorPartLifecycleTests.class, EditorPartFactoryTests.class

})
public class AllEditorsTests {
}
