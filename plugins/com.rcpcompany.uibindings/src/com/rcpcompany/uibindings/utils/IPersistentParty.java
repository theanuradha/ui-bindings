package com.rcpcompany.uibindings.utils;

import org.eclipse.ui.IMemento;

/**
 * A component, service or binding that is persistent.
 * 
 * @author Tonny Madsen, The RCP Company
 */
public interface IPersistentParty {
	/**
	 * The ID of this party.
	 * 
	 * @return the ID or <code>null</code>
	 */
	public String getId();

	/**
	 * Saves all state for the specified context.
	 * 
	 * @param memento the memento to save state into
	 */
	public void saveState(IMemento memento);

	/**
	 * Restores all state for the specified context.
	 * 
	 * @param memento the memento to restore state from
	 */
	public void restoreState(IMemento memento);
}