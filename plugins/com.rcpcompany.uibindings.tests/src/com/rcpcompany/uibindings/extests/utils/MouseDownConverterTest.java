package com.rcpcompany.uibindings.extests.utils;

import static com.rcpcompany.uibindings.extests.BaseTestUtils.postMouseDown;
import static com.rcpcompany.uibindings.extests.BaseTestUtils.postMouseMove;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.SerializationException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;

import com.rcpcompany.uibindings.internal.utils.MouseDownConverter;

/**
 * Test of {@link MouseDownConverter}.
 * 
 * @author Tonny Madsen, The RCP Company
 */
public class MouseDownConverterTest {
	@Test
	public void testIllegalCommand() {
		try {
			new MouseDownConverter(4, SWT.NONE, "xxx");
			fail();
		} catch (final NotDefinedException ex) {
			// OK
		} catch (final SerializationException ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testConverter() {
		final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

		final Point size = shell.getSize();
		size.x /= 2;
		size.y /= 2;
		postMouseMove(shell, size);

		press(shell, false);

		MouseDownConverter mouseDownConverter = null;
		try {
			mouseDownConverter = new MouseDownConverter(4, SWT.NONE,
					"com.rcpcompany.uibindings.tests.commands.MouseDownConverterTestCommand");
		} catch (final Exception ex) {
			fail(ex.getMessage());
		}

		press(shell, true);

		mouseDownConverter.dispose();

		press(shell, false);
	}

	/**
	 * @param shell
	 * @param commandExecuted TODO
	 */
	private void press(final Shell shell, boolean commandExecuted) {
		myCommandActuallyExecuted = false;
		postMouseDown("SPACE", 4, shell, 1);
		assertEquals(commandExecuted, myCommandActuallyExecuted);
	}

	public static boolean myCommandActuallyExecuted;

	public static class Handler extends AbstractHandler {
		@Override
		public Object execute(ExecutionEvent event) throws ExecutionException {
			myCommandActuallyExecuted = true;
			return null;
		}
	}
}