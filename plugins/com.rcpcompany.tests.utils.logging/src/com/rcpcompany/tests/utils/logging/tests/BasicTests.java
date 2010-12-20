/*******************************************************************************
 * Copyright (c) 2007, 2010 The RCP Company and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     The RCP Company - initial API and implementation
 *******************************************************************************/
package com.rcpcompany.tests.utils.logging.tests;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.junit.Test;

import com.rcpcompany.tests.utils.logging.helper.DummyOtherPlugin;
import com.rcpcompany.utils.logging.LogUtils;

public class BasicTests extends BaseTests {

	@Test
	public void testDebug() {
		LogUtils.debug(null, "Hello");
		assertLastStatus(IStatus.INFO, LogUtils.UNKNOWN_PLUGIN, -1, "Hello", false);
	}

	@Test
	public void testNullContext() {
		LogUtils.debug(null, "Hello");
		assertLastStatus(IStatus.INFO, LogUtils.UNKNOWN_PLUGIN, -1, "Hello", false);
	}

	@Test
	public void testStringContext() {
		LogUtils.debug("org.eclipse.xxx", "Hello again");
		assertLastStatus(IStatus.INFO, "org.eclipse.xxx", -1, "Hello again", false);
	}

	private class Dummy {

	}

	@Test
	public void testObjectContext() {
		LogUtils.debug(new Dummy(), "Hello again dummy");
		assertLastStatus(IStatus.INFO, "com.rcpcompany.tests.utils.logging", -1, "Hello again dummy", false);
	}

	@Test
	public void testObject2Context() {
		LogUtils.debug(new DummyOtherPlugin(), "Hello again other dummy");
		assertLastStatus(IStatus.INFO, "com.rcpcompany.tests.utils.logging.helper", -1, "Hello again other dummy",
				false);
	}

	@Test
	public void testCEContext() {
		final IExtensionRegistry er = Platform.getExtensionRegistry();
		final IConfigurationElement[] elements = er.getConfigurationElementsFor("org.eclipse.ui.views");
		assertTrue(elements.length > 0);
		final IConfigurationElement ce = elements[0];

		String message = "Hello registry";
		LogUtils.debug(ce, message);
		message = "{" + ce.getName() + "}: " + message;
		assertLastStatus(IStatus.INFO, ce.getContributor().getName(), -1, message, false);
	}

	@Test
	public void testErrorObjectString() {
		LogUtils.error(null, "Hello");
		assertLastStatus(IStatus.ERROR, LogUtils.UNKNOWN_PLUGIN, -1, "Hello", false);
	}

	@Test
	public void testErrorObjectThrowable() {
		final Throwable ex = new Exception("Ex occured");
		LogUtils.error(null, ex);
		assertLastStatus(IStatus.ERROR, LogUtils.UNKNOWN_PLUGIN, -1, "Ex occured", true);
	}

	@Test
	public void testLogPluginVersions() {
		LogUtils.logPluginVersions();
		assertLastStatus(IStatus.INFO, "com.rcpcompany.utils.logging", -1, null, false);
		final IStatus lastStatus = getLastStatus();
		final String m = lastStatus.getMessage();
		// A sample of the strings that should be present!
		final String[] ss = { "Plugin versions", "com.rcpcompany.utils.logging", "com.rcpcompany.tests.utils.logging",
				"org.eclipse.core.runtime", "org.eclipse.osgi" };
		for (final String s : ss) {
			assertTrue("Contains '" + s + "'", m.contains(s));
		}
	}

	@Test
	public void testLogMessage() {
		final Exception ex = new Exception();

		// The following two lines must follow exactly after one another
		ex.fillInStackTrace();
		final String message = LogUtils.logMessage("");
		// Section end

		System.out.println(">>>" + message + "<<<");

		final Pattern p = Pattern.compile("\\[(.*) +- +(.*) +- +(.*)\\.(.*) +\\((.*):(.*)\\)] +");
		final Matcher matcher = p.matcher(message);
		assertTrue("Basic structure", matcher.matches());

		final String date = matcher.group(1);
		final String threadName = matcher.group(2);
		final String className = matcher.group(3);
		final String methodName = matcher.group(4);
		final String fileName = matcher.group(5);
		final String lineNo = matcher.group(6);

		final StackTraceElement stackTraceElement = ex.getStackTrace()[0];

		assertEquals("Thread name", Thread.currentThread().getName(), threadName);
		String c = stackTraceElement.getClassName();
		if (c.lastIndexOf('.') >= 0) {
			c = c.substring(c.lastIndexOf('.') + 1);
		}
		assertEquals("Class name", c, className);
		assertEquals("Method name", stackTraceElement.getMethodName(), methodName);
		assertEquals("File name", stackTraceElement.getFileName(), fileName);
		assertEquals("Line number", "" + (stackTraceElement.getLineNumber() + 1), lineNo);
	}
}
