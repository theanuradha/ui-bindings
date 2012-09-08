package com.rcpcompany.test.utils;

/*******************************************************************************
 * Copyright (c) 2006-2011 The RCP Company and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     The RCP Company - initial API and implementation
 *******************************************************************************/

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

/**
 * Base class for all tests.
 * <p>
 * Provides a number of convenience methods...
 * 
 * @author Tonny Madsen, The RCP Company
 */
public class BaseTestUtils {
	private BaseTestUtils() {
	}

	public static void testPreferencePage(String pageId) {
		try {
			final IWorkbench workbench = PlatformUI.getWorkbench();
			final Shell[] shells = workbench.getDisplay().getShells();
			final ICommandService cs = (ICommandService) workbench
					.getService(ICommandService.class);
			final ParameterizedCommand command = cs
					.deserialize("org.eclipse.ui.window.preferences(preferencePageId="
							+ pageId + ")");
			assertNotNull(command);
	
			final IHandlerService hs = (IHandlerService) workbench
					.getService(IHandlerService.class);
			// Have to use timerExec to get the runnable executed after the
			// dialog is shown
			workbench.getDisplay().timerExec(2000, new Runnable() {
				@Override
				public void run() {
					assertEquals(shells.length + 1, workbench.getDisplay()
							.getShells().length);
					final Shell lastShell = findLastShell(workbench
							.getDisplay().getShells(), shells);
					assertNotNull(lastShell);
					final Object data = lastShell.getData();
					assertNotNull(data);
					assertTrue(data instanceof PreferenceDialog);
					lastShell.close();
	
					assertEquals(shells.length, workbench.getDisplay()
							.getShells().length);
				}
	
				private Shell findLastShell(Shell[] currentShells,
						Shell[] oldShells) {
					CheckNext : for (final Shell cs : currentShells) {
						for (final Shell os : oldShells) {
							if (os == cs) {
								continue CheckNext;
							}
						}
						return cs;
					}
					return null;
				}
			});
			hs.executeCommand(command, null);
		} catch (final Exception ex) {
			fail(ex.getMessage());
		}
	}

	/**
	 * Asserts that the specified runnable can be executed without any log
	 * messages or exceptions.
	 * 
	 * @param run
	 *            the runnable
	 */
	public static void assertNoLog(Runnable run) {
		assertNoLog(run, null);
	}

	/**
	 * Asserts that the specified runnable can be executed without any log
	 * messages or exceptions expect those that match any of the ignorePatterns.
	 * 
	 * @param run
	 *            the runnable
	 * @param ignorePatterns
	 *            a list of {@link Pattern} for messages that are ignored
	 */
	public static void assertNoLog(Runnable run, String[] ignorePatterns) {
		final NoLogListener ll = new NoLogListener(ignorePatterns);
		Platform.addLogListener(ll);
		try {
			run.run();
		} catch (final Exception ex) {
			ex.printStackTrace();
			fail("Exception occured: " + ex.getClass() + ": " + ex.getMessage());
		}
		Platform.removeLogListener(ll);
		assertTrue("Log message: " + ll.lastMessage, ll.called == 0);
	}

	/**
	 * Listener for log messages - used in
	 * {@link BaseTestUtils#assertNoLog(Runnable)}.
	 */
	private static class NoLogListener implements ILogListener {
		public int called = 0;
		public String lastMessage;
		private final String[] myIgnorePatterns;

		public NoLogListener(String[] ignorePatterns) {
			myIgnorePatterns = ignorePatterns;
		}

		@Override
		public void logging(IStatus status, String plugin) {
			// Ignore non-error messages
			if (status.getSeverity() != IStatus.ERROR)
				return;
			final String m = status.getMessage();
			if (myIgnorePatterns != null) {
				for (final String p : myIgnorePatterns) {
					if (m.matches(p))
						return;
				}
			}
			called++;
			lastMessage = m;
			final Throwable ex = status.getException();
			if (ex != null) {
				lastMessage = lastMessage + ": EXCEPTION: "
						+ ex.getClass().getSimpleName() + ": "
						+ ex.getMessage();
			}
		}
	}

	public static final Comparator<Object> OBJECT_COMPARATOR = new Comparator<Object>() {
		@Override
		public int compare(Object sf1, Object sf2) {
			return System.identityHashCode(sf1) - System.identityHashCode(sf2);
		}
	};

	/**
	 * Asserts that the specified runnable can be executed without any log
	 * messages or exceptions.
	 * 
	 * @param run
	 *            the runnable
	 * @return any last state message
	 */
	public static IStatus assertOneLog(Runnable run) {
		return assertNLog(1, run);
	}

	/**
	 * Asserts that the specified runnable can be executed without any log
	 * messages or exceptions.
	 * 
	 * @param run
	 *            the runnable
	 * @return any last state message
	 */
	public static IStatus assertNLog(int noMessages, Runnable run) {
		final NLogLogListener ll = new NLogLogListener();
		Platform.addLogListener(ll);
		try {
			run.run();
		} catch (final Exception ex) {
			fail("Exception occured: " + ex.getMessage());
		}
		Platform.removeLogListener(ll);
		assertTrue(ll.myMessages.size() + " log messages, expected "
				+ noMessages, ll.myMessages.size() == noMessages);

		return ll.myMessages.get(0);
	}

	/**
	 * Listener used to assert that exactly one log message is seen - used in
	 * {@link BaseTestUtils#assertOneLog(Runnable)}.
	 */
	private static class NLogLogListener implements ILogListener {
		public List<IStatus> myMessages = new ArrayList<IStatus>();

		@Override
		public void logging(IStatus status, String plugin) {
			// Ignore non-error messages
			if (status.getSeverity() != IStatus.ERROR)
				return;
			myMessages.add(status);
		}
	}

}
