package com.rcpcompany.utils.extensionpoints;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.rcpcompany.utils.logging.LogUtils;

/**
 * Simple holder pattern.
 * <p>
 * Used to hold a reference to an object that is created via the extension registry.
 * 
 * @author Tonny Madsen, The RCP Company
 * 
 * @param <X> the concrete type of the wanted objects
 */
public class CEObjectHolder<X> {
	private final IConfigurationElement myCE;

	/**
	 * Returns the Configuration Element of this holder object.
	 * 
	 * @return the ConfigurationElement
	 */
	public IConfigurationElement getConfigurationElement() {
		return myCE;
	}

	private X myObject = null;
	private final String myAttrName;

	/**
	 * Constructs and returns a new holder object for the specified configuration element.
	 * <p>
	 * Short for <code>CEObjectHolder(ce, "class")</code>
	 * 
	 * @param ce the configuration element
	 */
	public CEObjectHolder(IConfigurationElement ce) {
		this(ce, "class");
	}

	/**
	 * Constructs and returns a new holder object for the specified configuration element.
	 * 
	 * @param ce the configuration element
	 * @param attrName the attribute name
	 */
	public CEObjectHolder(IConfigurationElement ce, String attrName) {
		myCE = ce;
		myAttrName = attrName;
	}

	/**
	 * Returns the object for the holder object.
	 * 
	 * @return the object or <code>null</code>
	 */
	public X getObject() {
		if (myObject == null) {
			try {
				myObject = (X) myCE.createExecutableExtension(myAttrName);
			} catch (final CoreException ex) {
				LogUtils.error(myCE, ex);
			}
		}
		return myObject;
	}
}