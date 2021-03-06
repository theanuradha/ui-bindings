/**
 */
package com.rcpcompany.uibindings.moao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Severity</b></em>', and utility methods for working with them. <!-- end-user-doc -->
 * 
 * @see com.rcpcompany.uibindings.moao.IMOAOPackage#getSeverity()
 * @generated
 */
public enum Severity implements Enumerator {
	/**
	 * The '<em><b>WARNING</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #WARNING_VALUE
	 * @generated
	 * @ordered
	 */
	WARNING(0, "WARNING", "Warning"), /**
	 * The '<em><b>ERROR</b></em>' literal object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	ERROR(1, "ERROR", "Error"), /**
	 * The '<em><b>INFO</b></em>' literal object. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #INFO_VALUE
	 * @generated
	 * @ordered
	 */
	INFO(2, "INFO", "Info"), /**
	 * The '<em><b>COMMENT</b></em>' literal object. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #COMMENT_VALUE
	 * @generated
	 * @ordered
	 */
	COMMENT(3, "COMMENT", "Comment");

	/**
	 * The '<em><b>WARNING</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc --> *
	 * <p>
	 * Warning message. <!-- end-model-doc -->
	 * 
	 * @see #WARNING
	 * @generated
	 * @ordered
	 */
	public static final int WARNING_VALUE = 0;

	/**
	 * The '<em><b>ERROR</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> *
	 * <p>
	 * Error message. <!-- end-model-doc -->
	 * 
	 * @see #ERROR
	 * @generated
	 * @ordered
	 */
	public static final int ERROR_VALUE = 1;

	/**
	 * The '<em><b>INFO</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> *
	 * <p>
	 * Informational message. <!-- end-model-doc -->
	 * 
	 * @see #INFO
	 * @generated
	 * @ordered
	 */
	public static final int INFO_VALUE = 2;

	/**
	 * The '<em><b>COMMENT</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc --> *
	 * <p>
	 * Comment. <!-- end-model-doc -->
	 * 
	 * @see #COMMENT
	 * @generated
	 * @ordered
	 */
	public static final int COMMENT_VALUE = 3;

	/**
	 * An array of all the '<em><b>Severity</b></em>' enumerators. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	private static final Severity[] VALUES_ARRAY = new Severity[] { WARNING, ERROR, INFO, COMMENT, };

	/**
	 * A public read-only list of all the '<em><b>Severity</b></em>' enumerators. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final List<Severity> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Severity</b></em>' literal with the specified literal value. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static Severity get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Severity result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) { return result; }
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Severity</b></em>' literal with the specified name. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static Severity getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Severity result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) { return result; }
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Severity</b></em>' literal with the specified integer value. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static Severity get(int value) {
		switch (value) {
		case WARNING_VALUE:
			return WARNING;
		case ERROR_VALUE:
			return ERROR;
		case INFO_VALUE:
			return INFO;
		case COMMENT_VALUE:
			return COMMENT;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private Severity(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getLiteral() {
		return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}

} // Severity
