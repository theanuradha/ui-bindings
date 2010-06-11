package com.rcpcompany.uibindings;

import java.text.MessageFormat;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionInfo;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISources;

import com.rcpcompany.uibindings.internal.Activator;
import com.rcpcompany.uibindings.internal.decorators.NumberBindingDecorator;
import com.rcpcompany.uibindings.internal.propertyTesters.ManagerPropertyTester;

/**
 * Various constants that does not belong anywhere else.
 * 
 * @author Tonny Madsen, The RCP Company
 * @since 1.3
 */
public interface Constants {

	/**
	 * The EMF annotation source name for annotations on EMF objects that are used by the UI Bindings framework.
	 */
	public static final String EMF_ANNOTATION_SOURCE = "http://rcp-company.com/schemas/uibindings"; //$NON-NLS-1$

	/**
	 * Argument name for the message format of a value.
	 * <p>
	 * The argument value is {@link String}. In most cases, this effectively makes the binding read-only. Not all
	 * decorators supports this argument. The format is described in {@link MessageFormat}.
	 */
	public static final String ARG_MESSAGE_FORMAT = "format"; //$NON-NLS-1$

	/**
	 * Argument name for the label of a structural feature or a class when it cannot be deduced correctly automatically.
	 * <p>
	 * The argument value is {@link String}. The default is found automatically based on the name of the structural
	 * feature or class.
	 */
	public static final String ARG_LABEL = "label"; //$NON-NLS-1$

	/**
	 * Argument name for the constant text to use for a binding.
	 * <p>
	 * Primary used for constant cells in trees
	 * <p>
	 * The argument value is {@link String}.
	 */
	public static final String ARG_TEXT = "text"; //$NON-NLS-1$

	/**
	 * Argument name for the label used instead of the empty string for <code>null</code> values.
	 * <p>
	 * The argument value is {@link String}. The default is the empty string.
	 */
	public static final String ARG_NULL_LABEL = "nullLabel"; //$NON-NLS-1$

	/**
	 * Argument name for the width of the binding.
	 * <p>
	 * The argument value is {@link Integer}.
	 */
	public static final String ARG_WIDTH = "width"; //$NON-NLS-1$

	/**
	 * Argument name for the height of the binding.
	 * <p>
	 * The argument value is {@link Integer}.
	 */
	public static final String ARG_HEIGHT = "height"; //$NON-NLS-1$

	/**
	 * Argument name for dynamic bindings.
	 * <p>
	 * The argument value is {@link Boolean}. The default is <code>false</code>.
	 */
	public static final String ARG_DYNAMIC = "dynamic"; //$NON-NLS-1$

	/**
	 * Argument name for columns that should have "label decoration". Only relevant for {@link IColumnBinding}.
	 * <p>
	 * The argument value is {@link Boolean}. The default is <code>false</code>.
	 */
	public static final String ARG_LABEL_DECORATOR = "labelDecorator"; //$NON-NLS-1$

	/**
	 * Argument name for the image to show with the value.
	 * <p>
	 * The argument value is {@link ImageDescriptor}. The default is no image.
	 * <p>
	 * When used with the <code>uiBindings</code> extension point, this is expected to be a plug-in local image.
	 */
	public static final String ARG_IMAGE = "image"; //$NON-NLS-1$

	/**
	 * Argument name for when a reference type of attribute should allow new values apart from the current set.
	 * <p>
	 * The argument value is either {@link Boolean#TRUE} or {@link Boolean#FALSE}. The default is <code>false</code> .
	 */
	public static final String ARG_NEW_ALLOWED = "newAllowed"; //$NON-NLS-1$

	/**
	 * Argument name for when all (error and warning) messages for the model object or objects of the binding should be
	 * added to the context automatically.
	 * <p>
	 * The meaning of the argument differs slightly for different bindings:
	 * <ul>
	 * <li>For a {@link IValueBinding value binding}, this means all messages associated with the model object will be
	 * decorated in the binding.</li>
	 * <li>For a {@link IViewerBinding viewer binding}, this means all messages associated with any of the row elements
	 * will be associated with the viewer. The messages will not be decorated in the viewer and thus it is normally
	 * better to use the argument for a specific column in the viewer.</li>
	 * <li>For a {@link IColumnBinding column binding}, this means all messages associated with the model object of the
	 * specific column and row will be decorated in the column.</li>
	 * </ul>
	 * <p>
	 * The argument value is either {@link Boolean#TRUE} or {@link Boolean#FALSE}. The default is <code>false</code>.
	 */
	public static final String ARG_MODEL_OBJECT_MESSAGES = "modelObjectMessages"; //$NON-NLS-1$

	/**
	 * Argument name for when all (error and warning) messages for the value object or objects of the binding should be
	 * added to the context automatically.
	 * <p>
	 * This is of particular interest when a binding is for a reference and not an attribute.
	 * <p>
	 * The argument value is either {@link Boolean#TRUE} or {@link Boolean#FALSE}. The default is <code>false</code>.
	 */
	public static final String ARG_VALUE_OBJECT_MESSAGES = "valueObjectMessages"; //$NON-NLS-1$

	/**
	 * Argument name for when one field is filtered by another field.
	 * <p>
	 * The argument value is an {@link IObservableValue observable value}, but the exact meaning depends on the context.
	 */
	public static final String ARG_FILTER = "filter"; //$NON-NLS-1$

	/**
	 * Argument name for when an {@link IObservableList observable list} is used to supply all valid values.
	 * <p>
	 * The argument value is an {@link IObservableList observable list}, but the exact meaning depends on the context.
	 */
	public static final String ARG_VALID_VALUES = "validValues"; //$NON-NLS-1$

	/**
	 * Argument name for a binding should be read/only.
	 * <p>
	 * The argument value is either {@link Boolean#TRUE} or {@link Boolean#FALSE}. The default is <code>false</code>.
	 */
	public static final String ARG_READONLY = "readonly"; //$NON-NLS-1$

	/**
	 * Argument name for the tool tip text of a control.
	 * <p>
	 * The argument value is a {@link String}.
	 */
	public static final String ARG_TOOL_TIP_TEXT = "toolTipText"; //$NON-NLS-1$

	/**
	 * Argument name for the help ID of a control.
	 * <p>
	 * The argument value is a {@link String}.
	 */
	public static final String ARG_HELP_ID = "helpID"; //$NON-NLS-1$

	/**
	 * Argument name for the minimum value of a widget.
	 * <p>
	 * The argument value is a {@link Number}.
	 */
	public static final String ARG_MIN = "min"; //$NON-NLS-1$

	/**
	 * Argument name for the maximum value of a widget.
	 * <p>
	 * The argument value is a {@link Number}.
	 */
	public static final String ARG_MAX = "max"; //$NON-NLS-1$

	/**
	 * Argument name for the legal range of a {@link NumberBindingDecorator}.
	 * <p>
	 * The argument value is an interval on the form "[&lt;start&gt;;&lt;end&gt;]". "&lt;start&gt;" and "&lt;end&gt;"
	 * can each be missing meaning the natural limit for the basic decorator type - e.g. -128 for a byte.
	 * <p>
	 * The intervals are started and ended with a combination of "[" and "]":
	 * <ul>
	 * <li>"[]" - min and max inclusive</li>
	 * <li>"[[" - min inclusive and max exclusive</li>
	 * <li>"]]" - min exclusive and max inclusive</li>
	 * <li>"][" - min and max exclusive</li>
	 * </ul>
	 * <p>
	 * Examples
	 * <ul>
	 * <li>"[1;2]" - 1 &lt;= x &lt;= 2</li>
	 * <li>"[1;2[" - 1 &lt;= x &lt; 2</li>
	 * <li>"[0;1[,[2;3[" - 0 &lt;= x &lt; 1 or 2 &lt;= x &lt; 3</li>
	 * </ul>
	 * <p>
	 * <strong>NOTE:</strong> <em>currently only one interval is supported. In some future a list of
	 * comma separated intervals may be specified.</em>
	 * <p>
	 */
	public static final String ARG_RANGE = "range"; //$NON-NLS-1$

	/**
	 * Argument name for the feature name used to decorate a specific reference.
	 * <p>
	 * The argument value is a {@link String} with the name of a specific feature in the referenced object.
	 */
	public static final String ARG_FEATURE_NAME = "featureName"; //$NON-NLS-1$

	/**
	 * Argument name for the feature name used the allowed extensions of file name.
	 * <p>
	 * The argument value is a {@link String String array} with the extensions
	 */
	public static final String ARG_EXTENSIONS = "extensions"; //$NON-NLS-1$

	/**
	 * Argument name for whether a value for this binding is required.
	 * <p>
	 * The argument value is either {@link Boolean#TRUE} or {@link Boolean#FALSE}. The default is false.
	 */
	public static final String ARG_REQUIRED = "required"; //$NON-NLS-1$

	/**
	 * Argument name for the command to execute to open an item - either via CTRL-click or via a menu item.
	 * <p>
	 * The argument value is a {@link String} formatted as specified in {@link ParameterizedCommand#serialize()}.
	 */
	public static final String ARG_OPEN_COMMAND = "openCommand"; //$NON-NLS-1$

	/**
	 * Argument name for the alignment of a binding widget.
	 * <p>
	 * The argument value is one of "left", "center" or "right".
	 */
	public static final String ARG_ALIGNMENT = "alignment"; //$NON-NLS-1$

	/**
	 * Argument name for the command to execute to double-click an item.
	 * <p>
	 * The argument value is a {@link String} formatted as specified in {@link ParameterizedCommand#serialize()}.
	 */
	public static final String ARG_DOUBLE_CLICK_COMMAND = "doubleClickCommand"; //$NON-NLS-1$

	/**
	 * Argument name for a functor object that can create a new object in a viewer.
	 * <p>
	 * The argument value is an
	 */
	public static final String ARG_ITEM_CREATOR = "itemCreator"; //$NON-NLS-1$

	/**
	 * Argument name for a functor object that can delete an exiting object in a viewer.
	 * <p>
	 * The argument value is an {@link IViewerItemDeletor}.
	 */
	public static final String ARG_ITEM_DELETOR = "itemDeletor"; //$NON-NLS-1$

	/**
	 * Argument name for the text commit strategy.
	 * <p>
	 * The argument value is one of {@link TextCommitStrategy}.
	 */
	public static final String ARG_TEXT_COMMIT_STRATEGY = "textCommitStrategy"; //$NON-NLS-1$

	/**
	 * Argument name for the preferred Control for the binding.
	 * <p>
	 * The argument value is the name of a class that
	 * <ul>
	 * <li>Inherits from {@link Control} - directly or indirectly</li>
	 * <li>Supports a constructor {@code (Composite, int)}</li>
	 * </ul>
	 */
	public static final String ARG_PREFERRED_CONTROL = "preferredControl"; //$NON-NLS-1$

	/**
	 * Argument name for the preferred {@link Control} factory for the binding.
	 * <p>
	 * The argument value is {@link IControlFactory}.
	 */
	public static final String ARG_PREFERRED_CONTROL_FACTORY = "preferredControlFactory"; //$NON-NLS-1$

	/**
	 * Argument name for the preferred Control for cell editing for the binding.
	 * <p>
	 * The argument value is one of {@code org.eclipse.swt.widgets.Text}, {@code org.eclipse.swt.widgets.Button},
	 * {@code org.eclipse.swt.widgets.Combo}, or {@code org.eclipse.swt.custom.CCombo}.
	 */
	public static final String ARG_PREFERRED_CELL_EDITOR = "preferredCellEditor"; //$NON-NLS-1$

	/**
	 * Argument name for the preferred cell editor factory for cell editing for the binding.
	 * <p>
	 * The argument value is {@link ICellEditorFactory}.
	 */
	public static final String ARG_PREFERRED_CELL_EDITOR_FACTORY = "preferredCellEditorFactory"; //$NON-NLS-1$

	/**
	 * Argument name for the type of a binding if not specified directly with <code>type(...)</code>.
	 */
	public static final String ARG_TYPE = "type"; //$NON-NLS-1$

	/**
	 * Argument name for the type of a cell editor binding.
	 */
	public static final String ARG_CELL_EDITOR_TYPE = "cellEditorType"; //$NON-NLS-1$

	/**
	 * Argument name for the unit of a binding.
	 * <p>
	 * The argument is a text string formatted according to the base units of SI. TODO
	 */
	public static final String ARG_UNIT = "unit"; //$NON-NLS-1$

	/**
	 * Prefix for all IDs.
	 */
	public static final String PREFIX = Activator.ID + "."; //$NON-NLS-1$

	/**
	 * The common context ID.
	 */
	public static final String COMMON_CONTEXT_ID = PREFIX + "contexts.baseContext"; //$NON-NLS-1$

	/**
	 * The context ID for the widgets.
	 */
	public static final String WIDGET_CONTEXT_ID = PREFIX + "contexts.widgetContext"; //$NON-NLS-1$

	/**
	 * The context ID for the containers (viewers and grids).
	 */
	public static final String CONTAINER_CONTEXT_ID = PREFIX + "contexts.containerContext"; //$NON-NLS-1$

	/**
	 * The prefix for all sources.
	 */
	public static final String SOURCES = PREFIX + "sourceProviders."; //$NON-NLS-1$

	/**
	 * Source provider name for the manager itself.
	 * <p>
	 * The type of the value is {@link IManager}.
	 */
	public static final String SOURCES_THE_MANAGER = SOURCES + "theManager"; //$NON-NLS-1$

	/**
	 * Source provider name for the active binding context.
	 * <p>
	 * The type of the value is {@link IBindingContext}.
	 */
	public static final String SOURCES_ACTIVE_CONTEXT = SOURCES + "activeContext"; //$NON-NLS-1$

	/**
	 * Source provider name for the active binding.
	 * <p>
	 * The type of the value is {@link IBinding}.
	 */
	public static final String SOURCES_ACTIVE_BINDING = SOURCES + "activeBinding"; //$NON-NLS-1$

	/**
	 * Source provider name for the structural feature of current binding value.
	 * <p>
	 * The type of the value is {@link EStructuralFeature}.
	 */
	public static final String SOURCES_ACTIVE_BINDING_FEATURE = SOURCES_ACTIVE_BINDING + "#feature"; //$NON-NLS-1$

	/**
	 * Source provider name for whether the active binding is unsettable.
	 * <p>
	 * The type of the value is {@link Boolean}.
	 */
	public static final String SOURCES_ACTIVE_BINDING_UNSETTABLE = SOURCES_ACTIVE_BINDING + "#unsettable"; //$NON-NLS-1$

	/**
	 * Source provider name for the "open command" of the current binding if any is defined.
	 * <p>
	 * The type of the value is {@link ParameterizedCommand}.
	 */
	public static final String SOURCES_ACTIVE_BINDING_OPEN_COMMAND = SOURCES_ACTIVE_BINDING + "#openCommand"; //$NON-NLS-1$

	/**
	 * Source provider name for the binding type of the active binding or <code>null</code>.
	 * <p>
	 * The type of the value is {@link String}.
	 */
	public static final String SOURCES_ACTIVE_BINDING_TYPE = SOURCES_ACTIVE_BINDING + "#type"; //$NON-NLS-1$

	/**
	 * Source provider name for the model object of the active binding or <code>null</code>.
	 * <p>
	 * The type of the value is {@link Object}.
	 */
	public static final String SOURCES_ACTIVE_BINDING_MODEL_OBJECT = SOURCES_ACTIVE_BINDING + "#modelobject"; //$NON-NLS-1$

	/**
	 * Source provider name for the read-only status of the active binding in the table.
	 * <p>
	 * The type of the value is {@link Boolean}.
	 */
	public static final String SOURCES_ACTIVE_BINDING_RO = SOURCES_ACTIVE_BINDING + "#ro"; //$NON-NLS-1$

	/**
	 * Source provider name for the active binding value. This is defined if {@link #SOURCES_ACTIVE_BINDING} is non-
	 * <code>null</code>.
	 */
	public static final String SOURCES_ACTIVE_BINDING_VALUE = SOURCES_ACTIVE_BINDING + "Value"; //$NON-NLS-1$

	/**
	 * Source provider name for the display value of current binding value.
	 * <p>
	 * The type of the value is {@link String}.
	 */
	public static final String SOURCES_ACTIVE_BINDING_VALUE_DISPLAY = SOURCES_ACTIVE_BINDING_VALUE + "#display"; //$NON-NLS-1$

	/**
	 * Source provider name for the active container binding if any.
	 * <p>
	 * A container binding is a binding that contains the current {@link #SOURCES_ACTIVE_BINDING active binding} - e.g.
	 * a viewer binding or a grid binding
	 * <p>
	 * The type of the value is the container binding.
	 */
	public static final String SOURCES_ACTIVE_CONTAINER_BINDING = SOURCES + "activeContainerBinding"; //$NON-NLS-1$

	/**
	 * Source provider name for the active container binding property "noComparatorOrFilter".
	 * <p>
	 * The type of the value is <code>true</code> if the active container doesn't have a comparator or filter otherwise
	 * <code>false</code>.
	 */
	public static final String SOURCES_ACTIVE_CONTAINER_BINDING_NO_CAF = SOURCES_ACTIVE_CONTAINER_BINDING
			+ "#noComparatorOrFilter"; //$NON-NLS-1$

	/**
	 * Source provider name for the active row element in the table or <code>null</code>.
	 */
	public static final String SOURCES_ACTIVE_VIEWER_ELEMENT = SOURCES + "activeViewerElement"; //$NON-NLS-1$

	/**
	 * Source provider name for the type of row elements in the table or <code>null</code>.
	 */
	public static final String SOURCES_ACTIVE_VIEWER_ELEMENT_TYPE = SOURCES_ACTIVE_VIEWER_ELEMENT + "#type"; //$NON-NLS-1$

	/**
	 * Source provider name for the property that the active row element can be moved up.
	 */
	public static final String SOURCES_ACTIVE_VIEWER_ELEMENT_MOVE_UP = SOURCES_ACTIVE_VIEWER_ELEMENT + "#moveUp"; //$NON-NLS-1$

	/**
	 * Source provider name for the property that the active row element can be moved down.
	 */
	public static final String SOURCES_ACTIVE_VIEWER_ELEMENT_MOVE_DOWN = SOURCES_ACTIVE_VIEWER_ELEMENT + "#moveDown"; //$NON-NLS-1$

	/**
	 * Source provider name for the property that the active row element can be deleted.
	 */
	public static final String SOURCES_ACTIVE_VIEWER_ELEMENT_DELETE = SOURCES_ACTIVE_VIEWER_ELEMENT + "#delete"; //$NON-NLS-1$

	/**
	 * The ID of the default "Open" command used when no specific open command is associated with the specific
	 * classifier or feature.
	 */
	public static final String DEFAULT_OPEN_COMMAND = PREFIX + "commands.openBinding"; //$NON-NLS-1$

	/**
	 * The ID of the "Use Defaults" command.
	 */
	public static final String USE_DEFAULT_TOGGLE_COMMAND = PREFIX + "commands.UseDefaultValue"; //$NON-NLS-1$

	public static final String ATTR_TEXT = "text"; //$NON-NLS-1$

	public static final String ATTR_IMAGE = "image"; //$NON-NLS-1$

	public static final String ATTR_WIDTH = "width"; //$NON-NLS-1$

	public static final String ATTR_ALIGNMENT = "alignment"; //$NON-NLS-1$

	public static final String ATTR_TOOLTIP = "tooltip"; //$NON-NLS-1$

	public static final String ATTR_VISIBLE = "visible"; //$NON-NLS-1$

	public static final String ATTR_ENABLED = "enabled"; //$NON-NLS-1$

	public static final String ATTR_FONT = "font"; //$NON-NLS-1$

	public static final String ATTR_FOREGROUND = "foreground"; //$NON-NLS-1$

	public static final String ATTR_BACKGROUND = "background"; //$NON-NLS-1$

	public static final String ATTR_MAX = "max"; //$NON-NLS-1$

	public static final String ATTR_MIN = "min"; //$NON-NLS-1$

	public static final String ATTR_SELECTION = "selection"; //$NON-NLS-1$

	public static final String ATTR_CURSOR = "cursor"; //$NON-NLS-1$

	/**
	 * Property name used in {@link ManagerPropertyTester}.
	 */
	public static final String PROPERTY_CAN_REDO = "canRedo"; //$NON-NLS-1$

	/**
	 * Property name used in {@link ManagerPropertyTester}.
	 */
	public static final String PROPERTY_CAN_UNDO = "canUndo"; //$NON-NLS-1$

	/**
	 * The {@link IBinding} type used for fields that should have a long human readable name.
	 */
	public static final String TYPE_LONG_NAME = "longName"; //$NON-NLS-1$

	/**
	 * The {@link IBinding} type used for fields that contains a file name.
	 */
	public static final String TYPE_FILE_NAME = "fileName";

	/**
	 * The {@link IBinding} type used for fields that contains a directory name.
	 */
	public static final String TYPE_DIRECTORY_NAME = "directoryName";

	/**
	 * This expression evaluates to <code>true</code> and has a very high source priority.
	 * <p>
	 * This expression is used to provoke that a local undo handler takes priority over global handlers.
	 */
	public final static Expression TRUE_EXPRESSION = new Expression() {
		@Override
		public void collectExpressionInfo(ExpressionInfo info) {
			super.collectExpressionInfo(info);
			info.addVariableNameAccess(ISources.ACTIVE_CURRENT_SELECTION_NAME);
		}

		@Override
		public EvaluationResult evaluate(IEvaluationContext context) throws CoreException {
			return EvaluationResult.TRUE;
		}
	};

	/**
	 * The color definition used for even rows.
	 * 
	 * @see IManager#isAlternateRowColors()
	 */
	public static final String COLOR_DEFINITIONS_EVEN_ROW_BACKGROUND = PREFIX + "colorDefinitions.EvenRowBackground"; //$NON-NLS-1$

	/**
	 * The color definition used for cells that have focus when the control have focus.
	 */
	public static final String COLOR_SELECTION_FOCUS_BACKGROUND = PREFIX + "colorDefinitions.SelectionFocusBackground"; //$NON-NLS-1$

	/**
	 * The color definition used for cells that have focus when the control does not have focus.
	 */
	public static final String COLOR_SELECTION_NO_FOCUS_BACKGROUND = PREFIX
			+ "colorDefinitions.SelectionNoFocusBackground"; //$NON-NLS-1$
}