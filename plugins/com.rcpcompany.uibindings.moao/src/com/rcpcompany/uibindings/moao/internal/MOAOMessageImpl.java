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
package com.rcpcompany.uibindings.moao.internal;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.rcpcompany.uibindings.moao.IMOAO;
import com.rcpcompany.uibindings.moao.IMOAOMessage;
import com.rcpcompany.uibindings.moao.IMOAOPackage;
import com.rcpcompany.uibindings.moao.Severity;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Message</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link com.rcpcompany.uibindings.moao.internal.MOAOMessageImpl#getOwner <em>Owner</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.moao.internal.MOAOMessageImpl#getObject <em>Object</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.moao.internal.MOAOMessageImpl#getFeature <em>Feature</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.moao.internal.MOAOMessageImpl#getDescription <em>Description
 * </em>}</li>
 * <li>{@link com.rcpcompany.uibindings.moao.internal.MOAOMessageImpl#getSeverity <em>Severity</em>}
 * </li>
 * <li>{@link com.rcpcompany.uibindings.moao.internal.MOAOMessageImpl#getDetails <em>Details</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MOAOMessageImpl extends EObjectImpl implements IMOAOMessage {
	/**
	 * The default value of the '{@link #getOwner() <em>Owner</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getOwner()
	 * @generated
	 * @ordered
	 */
	protected static final String OWNER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOwner() <em>Owner</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getOwner()
	 * @generated
	 * @ordered
	 */
	protected String owner = OWNER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFeature() <em>Feature</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFeature()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature feature;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getSeverity() <em>Severity</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSeverity()
	 * @generated
	 * @ordered
	 */
	protected static final Severity SEVERITY_EDEFAULT = Severity.WARNING;

	/**
	 * The cached value of the '{@link #getSeverity() <em>Severity</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSeverity()
	 * @generated
	 * @ordered
	 */
	protected Severity severity = SEVERITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getDetails() <em>Details</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDetails()
	 * @generated
	 * @ordered
	 */
	protected static final String DETAILS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDetails() <em>Details</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDetails()
	 * @generated
	 * @ordered
	 */
	protected String details = DETAILS_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MOAOMessageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IMOAOPackage.Literals.MOAO_MESSAGE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getOwner() {
		return owner;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setOwner(String newOwner) {
		final String oldOwner = owner;
		owner = newOwner;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, IMOAOPackage.MOAO_MESSAGE__OWNER, oldOwner, owner));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IMOAO getObject() {
		if (eContainerFeatureID() != IMOAOPackage.MOAO_MESSAGE__OBJECT) return null;
		return (IMOAO) eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetObject(IMOAO newObject, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newObject, IMOAOPackage.MOAO_MESSAGE__OBJECT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setObject(IMOAO newObject) {
		if (newObject != eInternalContainer()
				|| (eContainerFeatureID() != IMOAOPackage.MOAO_MESSAGE__OBJECT && newObject != null)) {
			if (EcoreUtil.isAncestor(this, newObject))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newObject != null) {
				msgs = ((InternalEObject) newObject).eInverseAdd(this, IMOAOPackage.MOAO__MESSAGES, IMOAO.class, msgs);
			}
			msgs = basicSetObject(newObject, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, IMOAOPackage.MOAO_MESSAGE__OBJECT, newObject,
					newObject));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EStructuralFeature getFeature() {
		if (feature != null && ((EObject) feature).eIsProxy()) {
			final InternalEObject oldFeature = (InternalEObject) feature;
			feature = (EStructuralFeature) eResolveProxy(oldFeature);
			if (feature != oldFeature) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IMOAOPackage.MOAO_MESSAGE__FEATURE,
							oldFeature, feature));
				}
			}
		}
		return feature;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EStructuralFeature basicGetFeature() {
		return feature;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setFeature(EStructuralFeature newFeature) {
		final EStructuralFeature oldFeature = feature;
		feature = newFeature;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, IMOAOPackage.MOAO_MESSAGE__FEATURE, oldFeature,
					feature));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		final String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, IMOAOPackage.MOAO_MESSAGE__DESCRIPTION,
					oldDescription, description));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Severity getSeverity() {
		return severity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setSeverity(Severity newSeverity) {
		final Severity oldSeverity = severity;
		severity = newSeverity == null ? SEVERITY_EDEFAULT : newSeverity;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, IMOAOPackage.MOAO_MESSAGE__SEVERITY, oldSeverity,
					severity));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getDetails() {
		return details;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDetails(String newDetails) {
		final String oldDetails = details;
		details = newDetails;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, IMOAOPackage.MOAO_MESSAGE__DETAILS, oldDetails,
					details));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case IMOAOPackage.MOAO_MESSAGE__OBJECT:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetObject((IMOAO) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case IMOAOPackage.MOAO_MESSAGE__OBJECT:
			return basicSetObject(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case IMOAOPackage.MOAO_MESSAGE__OBJECT:
			return eInternalContainer().eInverseRemove(this, IMOAOPackage.MOAO__MESSAGES, IMOAO.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case IMOAOPackage.MOAO_MESSAGE__OWNER:
			return getOwner();
		case IMOAOPackage.MOAO_MESSAGE__OBJECT:
			return getObject();
		case IMOAOPackage.MOAO_MESSAGE__FEATURE:
			if (resolve) return getFeature();
			return basicGetFeature();
		case IMOAOPackage.MOAO_MESSAGE__DESCRIPTION:
			return getDescription();
		case IMOAOPackage.MOAO_MESSAGE__SEVERITY:
			return getSeverity();
		case IMOAOPackage.MOAO_MESSAGE__DETAILS:
			return getDetails();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case IMOAOPackage.MOAO_MESSAGE__OWNER:
			setOwner((String) newValue);
			return;
		case IMOAOPackage.MOAO_MESSAGE__OBJECT:
			setObject((IMOAO) newValue);
			return;
		case IMOAOPackage.MOAO_MESSAGE__FEATURE:
			setFeature((EStructuralFeature) newValue);
			return;
		case IMOAOPackage.MOAO_MESSAGE__DESCRIPTION:
			setDescription((String) newValue);
			return;
		case IMOAOPackage.MOAO_MESSAGE__SEVERITY:
			setSeverity((Severity) newValue);
			return;
		case IMOAOPackage.MOAO_MESSAGE__DETAILS:
			setDetails((String) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case IMOAOPackage.MOAO_MESSAGE__OWNER:
			setOwner(OWNER_EDEFAULT);
			return;
		case IMOAOPackage.MOAO_MESSAGE__OBJECT:
			setObject((IMOAO) null);
			return;
		case IMOAOPackage.MOAO_MESSAGE__FEATURE:
			setFeature((EStructuralFeature) null);
			return;
		case IMOAOPackage.MOAO_MESSAGE__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case IMOAOPackage.MOAO_MESSAGE__SEVERITY:
			setSeverity(SEVERITY_EDEFAULT);
			return;
		case IMOAOPackage.MOAO_MESSAGE__DETAILS:
			setDetails(DETAILS_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case IMOAOPackage.MOAO_MESSAGE__OWNER:
			return OWNER_EDEFAULT == null ? owner != null : !OWNER_EDEFAULT.equals(owner);
		case IMOAOPackage.MOAO_MESSAGE__OBJECT:
			return getObject() != null;
		case IMOAOPackage.MOAO_MESSAGE__FEATURE:
			return feature != null;
		case IMOAOPackage.MOAO_MESSAGE__DESCRIPTION:
			return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
		case IMOAOPackage.MOAO_MESSAGE__SEVERITY:
			return severity != SEVERITY_EDEFAULT;
		case IMOAOPackage.MOAO_MESSAGE__DETAILS:
			return DETAILS_EDEFAULT == null ? details != null : !DETAILS_EDEFAULT.equals(details);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		final StringBuffer result = new StringBuffer(super.toString());
		result.append(" (owner: ");
		result.append(owner);
		result.append(", description: ");
		result.append(description);
		result.append(", severity: ");
		result.append(severity);
		result.append(", details: ");
		result.append(details);
		result.append(')');
		return result.toString();
	}

} // MOAOMessageImpl
