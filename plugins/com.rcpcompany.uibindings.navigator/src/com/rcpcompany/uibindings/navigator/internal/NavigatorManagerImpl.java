/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package com.rcpcompany.uibindings.navigator.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.rcpcompany.uibindings.UIBindingsUtils;
import com.rcpcompany.uibindings.navigator.IEditorModelType;
import com.rcpcompany.uibindings.navigator.IEditorPartDescriptor;
import com.rcpcompany.uibindings.navigator.IEditorPartFactory;
import com.rcpcompany.uibindings.navigator.IEditorPartView;
import com.rcpcompany.uibindings.navigator.INavigatorManager;
import com.rcpcompany.uibindings.navigator.INavigatorModelFactory;
import com.rcpcompany.uibindings.navigator.INavigatorModelPackage;
import com.rcpcompany.uibindings.navigator.editorFactories.GenericPlainFormEditorPartFactory;
import com.rcpcompany.utils.extensionpoints.CEObjectHolder;
import com.rcpcompany.utils.extensionpoints.CEResourceHolder;
import com.rcpcompany.utils.logging.LogUtils;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Manager</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link com.rcpcompany.uibindings.navigator.internal.NavigatorManagerImpl#getModelTypes <em>
 * Model Types</em>}</li>
 * <li>
 * {@link com.rcpcompany.uibindings.navigator.internal.NavigatorManagerImpl#isUseGenericEditorPartFallback
 * <em>Use Generic Editor Part Fallback</em>}</li>
 * <li>
 * {@link com.rcpcompany.uibindings.navigator.internal.NavigatorManagerImpl#isPinEditorByDefault
 * <em>Pin Editor By Default</em>}</li>
 * <li>{@link com.rcpcompany.uibindings.navigator.internal.NavigatorManagerImpl#isOpenMustOpenNew
 * <em>Open Must Open New</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class NavigatorManagerImpl extends EObjectImpl implements INavigatorManager {
	/**
	 * The cached value of the '{@link #getModelTypes() <em>Model Types</em>}' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getModelTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<IEditorModelType> modelTypes;

	/**
	 * The default value of the '{@link #isUseGenericEditorPartFallback()
	 * <em>Use Generic Editor Part Fallback</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isUseGenericEditorPartFallback()
	 * @generated
	 * @ordered
	 */
	protected static final boolean USE_GENERIC_EDITOR_PART_FALLBACK_EDEFAULT = true;
	/**
	 * The cached value of the '{@link #isUseGenericEditorPartFallback()
	 * <em>Use Generic Editor Part Fallback</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isUseGenericEditorPartFallback()
	 * @generated
	 * @ordered
	 */
	protected boolean useGenericEditorPartFallback = USE_GENERIC_EDITOR_PART_FALLBACK_EDEFAULT;

	/**
	 * The default value of the '{@link #isPinEditorByDefault() <em>Pin Editor By Default</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isPinEditorByDefault()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PIN_EDITOR_BY_DEFAULT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPinEditorByDefault() <em>Pin Editor By Default</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isPinEditorByDefault()
	 * @generated
	 * @ordered
	 */
	protected boolean pinEditorByDefault = PIN_EDITOR_BY_DEFAULT_EDEFAULT;

	/**
	 * The default value of the '{@link #isOpenMustOpenNew() <em>Open Must Open New</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isOpenMustOpenNew()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OPEN_MUST_OPEN_NEW_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOpenMustOpenNew() <em>Open Must Open New</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isOpenMustOpenNew()
	 * @generated
	 * @ordered
	 */
	protected boolean openMustOpenNew = OPEN_MUST_OPEN_NEW_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected NavigatorManagerImpl() {
		super();

		extensionReader();
		preferenceReader();
	}

	@Override
	public IEditorModelType getModelType(EObject obj) {
		if (obj == null) return null;
		final Class<? extends EObject> cls = obj.getClass();
		final Class<?>[] classes = Platform.getAdapterManager().computeClassOrder(cls);
		for (final Class<?> c : classes) {
			final String typeName = c.getName();
			for (final IEditorModelType mt : getModelTypes()) {
				if (mt.getModelType().equals(typeName)) return mt;
			}
		}

		return null;
	}

	@Override
	public IEditorPartDescriptor getEditorPartDescriptor(EObject obj) {
		if (obj == null) return null;
		IEditorModelType mt = getModelType(obj);
		/*
		 * Found a match. Find the preferred or the one with the highest priority.
		 */
		if (mt != null) return mt.getPreferredEditor();

		/*
		 * If falling back on the generic factory, then create and install this properly.
		 */
		if (isUseGenericEditorPartFallback()) {
			mt = INavigatorModelFactory.eINSTANCE.createEditorModelType();
			Class<? extends EObject> cls = obj.getClass();
			if (!cls.isInterface()) {
				final Class<?>[] interfaces = cls.getInterfaces();
				if (interfaces.length > 0) {
					cls = (Class<? extends EObject>) interfaces[0];
				}
			}

			mt.setModelType(cls.getName());
			mt.setAutoGenerated(true);

			final IEditorPartDescriptor desc = INavigatorModelFactory.eINSTANCE.createEditorPartDescriptor();

			desc.setId(mt.getModelType() + "." + GenericPlainFormEditorPartFactory.class.getName());
			desc.setFactory(new CEObjectHolder<IEditorPartFactory>(new GenericPlainFormEditorPartFactory()));
			desc.setName("Generic Information");
			desc.setPriority(1000);

			mt.getEditors().add(desc);
			mt.setPreferredEditor(desc);

			getModelTypes().add(mt);

			return desc;
		}

		return null;
	}

	private void extensionReader() {
		final IExtensionRegistry registry = Platform.getExtensionRegistry();

		for (final IConfigurationElement ce : registry
				.getConfigurationElementsFor(NavigatorConstants.EDITORS_EXTENSION_POINT)) {
			final String elementName = ce.getName();
			if (NavigatorConstants.EDITOR_TAG.equals(elementName)) {
				String id = ce.getAttribute(NavigatorConstants.ID_TAG);
				if (id == null || id.length() == 0) {
					id = "<unspecified>"; //$NON-NLS-1$
				}

				final String modelType = ce.getAttribute(NavigatorConstants.MODEL_TYPE_TAG);
				if (modelType == null || modelType.length() == 0) {
					LogUtils.error(ce, NavigatorConstants.MODEL_TYPE_TAG + " must be specified. Ignored"); //$NON-NLS-1$
					continue;
				}

				IEditorModelType emt = null;
				for (final IEditorModelType e : getModelTypes()) {
					if (e.getModelType().equals(modelType)) {
						emt = e;
						break;
					}
				}
				if (emt == null) {
					emt = INavigatorModelFactory.eINSTANCE.createEditorModelType();
					emt.setModelType(modelType);

					getModelTypes().add(emt);
				}

				final String name = ce.getAttribute(NavigatorConstants.NAME_TAG);
				if (name == null || name.length() == 0) {
					LogUtils.error(ce, NavigatorConstants.NAME_TAG + " must be specified. Ignored"); //$NON-NLS-1$
					continue;
				}

				final IEditorPartDescriptor descriptor = INavigatorModelFactory.eINSTANCE.createEditorPartDescriptor();
				emt.getEditors().add(descriptor);
				descriptor.setId(id);
				descriptor.setName(name);
				descriptor.setImage(new CEResourceHolder(ce, NavigatorConstants.IMAGE_TAG));
				descriptor.setFactory(new CEObjectHolder<IEditorPartFactory>(ce, NavigatorConstants.FACTORY_TAG));

				final String priority = ce.getAttribute(NavigatorConstants.PRIORITY_TAG);
				if (priority != null && priority.length() > 0) {
					try {
						descriptor.setPriority(Integer.parseInt(priority));
					} catch (final NumberFormatException ex) {
						LogUtils.error(ce, ex);
						continue;
					}
				} else {
					descriptor.setPriority(1000);
				}
			} else {
				LogUtils.error(ce, "Unknown element name: '" + elementName + "'"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}

		/*
		 * Sort the editors of model types
		 */
		final Comparator<IEditorPartDescriptor> comparator = new Comparator<IEditorPartDescriptor>() {
			@Override
			public int compare(IEditorPartDescriptor o1, IEditorPartDescriptor o2) {
				return o2.getPriority() - o1.getPriority();
			}
		};
		for (final IEditorModelType mt : getModelTypes()) {
			UIBindingsUtils.sort(mt.getEditors(), comparator);
			mt.setPreferredEditor(mt.getEditors().get(0));
		}
	}

	/**
	 * Reads the current preferences and updates the defaults
	 */
	private void preferenceReader() {
		final IPreferenceStore ps = Activator.getDefault().getPreferenceStore();

		/*
		 * Setupo defaults
		 */
		ps.setDefault(NavigatorConstants.PREF_USE_GENERIC_EDITOR_PART_FALLBACK,
				NavigatorManagerImpl.USE_GENERIC_EDITOR_PART_FALLBACK_EDEFAULT);
		ps.setDefault(NavigatorConstants.PREF_OPEN_MUST_OPEN_NEW, NavigatorManagerImpl.OPEN_MUST_OPEN_NEW_EDEFAULT);
		ps.setDefault(NavigatorConstants.PREF_PIN_EDITOR_BY_DEFAULT,
				NavigatorManagerImpl.PIN_EDITOR_BY_DEFAULT_EDEFAULT);

		for (final IEditorModelType mt : getModelTypes()) {
			ps.setDefault(mt.getModelType(), mt.getEditors().get(0).getId());
		}

		/**
		 * Monitor changes
		 */
		ps.addPropertyChangeListener(myPreferenceListener);
		myPreferenceListener.propertyChange(null);
	}

	private final IPropertyChangeListener myPreferenceListener = new IPropertyChangeListener() {
		final IPreferenceStore ps = Activator.getDefault().getPreferenceStore();

		@Override
		public void propertyChange(PropertyChangeEvent event) {
			boolean b = ps.getBoolean(NavigatorConstants.PREF_USE_GENERIC_EDITOR_PART_FALLBACK);
			if (isUseGenericEditorPartFallback() != b) {
				setUseGenericEditorPartFallback(b);
			}
			b = ps.getBoolean(NavigatorConstants.PREF_OPEN_MUST_OPEN_NEW);
			if (isOpenMustOpenNew() != b) {
				setOpenMustOpenNew(b);
			}
			b = ps.getBoolean(NavigatorConstants.PREF_PIN_EDITOR_BY_DEFAULT);
			if (isPinEditorByDefault() != b) {
				setPinEditorByDefault(b);
			}

			for (final IEditorModelType mt : getModelTypes()) {
				/*
				 * Find the current preference - possibly the default we just set...
				 */
				final String id = ps.getString(mt.getModelType());
				IEditorPartDescriptor pref = null;
				for (final IEditorPartDescriptor e : mt.getEditors()) {
					if (e.getId().equals(id)) {
						pref = e;
						break;
					}
				}
				if (pref == null && (id == null || id.length() == 0)) {
					pref = mt.getEditors().get(0);
				}
				if (pref == null) {
					/*
					 * The preference was not found. Can happen if a newer version of the
					 * application does not support the editor any more... or if somebody have
					 * changed the id by mistake.
					 */
					LogUtils.error(this, "Preference not found for " + mt.getModelType() + ": '" + id
							+ "'. Reset to default.");
					pref = mt.getEditors().get(0);
				}
				if (pref != mt.getPreferredEditor()) {
					mt.setPreferredEditor(pref);
				}
			}
		}
	};

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return INavigatorModelPackage.Literals.NAVIGATOR_MANAGER;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<IEditorModelType> getModelTypes() {
		if (modelTypes == null) {
			modelTypes = new EObjectEList<IEditorModelType>(IEditorModelType.class, this,
					INavigatorModelPackage.NAVIGATOR_MANAGER__MODEL_TYPES);
		}
		return modelTypes;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isUseGenericEditorPartFallback() {
		return useGenericEditorPartFallback;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setUseGenericEditorPartFallback(boolean newUseGenericEditorPartFallback) {
		setUseGenericEditorPartFallbackGen(newUseGenericEditorPartFallback);
		Activator.getDefault().getPreferenceStore()
				.setValue(NavigatorConstants.PREF_USE_GENERIC_EDITOR_PART_FALLBACK, newUseGenericEditorPartFallback);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setUseGenericEditorPartFallbackGen(boolean newUseGenericEditorPartFallback) {
		final boolean oldUseGenericEditorPartFallback = useGenericEditorPartFallback;
		useGenericEditorPartFallback = newUseGenericEditorPartFallback;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					INavigatorModelPackage.NAVIGATOR_MANAGER__USE_GENERIC_EDITOR_PART_FALLBACK,
					oldUseGenericEditorPartFallback, useGenericEditorPartFallback));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isPinEditorByDefault() {
		return pinEditorByDefault;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setPinEditorByDefault(boolean newPinEditorByDefault) {
		setPinEditorByDefaultGen(newPinEditorByDefault);
		Activator.getDefault().getPreferenceStore()
				.setValue(NavigatorConstants.PREF_PIN_EDITOR_BY_DEFAULT, newPinEditorByDefault);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPinEditorByDefaultGen(boolean newPinEditorByDefault) {
		final boolean oldPinEditorByDefault = pinEditorByDefault;
		pinEditorByDefault = newPinEditorByDefault;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					INavigatorModelPackage.NAVIGATOR_MANAGER__PIN_EDITOR_BY_DEFAULT, oldPinEditorByDefault,
					pinEditorByDefault));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isOpenMustOpenNew() {
		return openMustOpenNew;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setOpenMustOpenNew(boolean newOpenMustOpenNew) {
		setOpenMustOpenNewGen(newOpenMustOpenNew);
		Activator.getDefault().getPreferenceStore()
				.setValue(NavigatorConstants.PREF_OPEN_MUST_OPEN_NEW, newOpenMustOpenNew);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setOpenMustOpenNewGen(boolean newOpenMustOpenNew) {
		final boolean oldOpenMustOpenNew = openMustOpenNew;
		openMustOpenNew = newOpenMustOpenNew;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					INavigatorModelPackage.NAVIGATOR_MANAGER__OPEN_MUST_OPEN_NEW, oldOpenMustOpenNew, openMustOpenNew));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case INavigatorModelPackage.NAVIGATOR_MANAGER__MODEL_TYPES:
			return getModelTypes();
		case INavigatorModelPackage.NAVIGATOR_MANAGER__USE_GENERIC_EDITOR_PART_FALLBACK:
			return isUseGenericEditorPartFallback();
		case INavigatorModelPackage.NAVIGATOR_MANAGER__PIN_EDITOR_BY_DEFAULT:
			return isPinEditorByDefault();
		case INavigatorModelPackage.NAVIGATOR_MANAGER__OPEN_MUST_OPEN_NEW:
			return isOpenMustOpenNew();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case INavigatorModelPackage.NAVIGATOR_MANAGER__MODEL_TYPES:
			getModelTypes().clear();
			getModelTypes().addAll((Collection<? extends IEditorModelType>) newValue);
			return;
		case INavigatorModelPackage.NAVIGATOR_MANAGER__USE_GENERIC_EDITOR_PART_FALLBACK:
			setUseGenericEditorPartFallback((Boolean) newValue);
			return;
		case INavigatorModelPackage.NAVIGATOR_MANAGER__PIN_EDITOR_BY_DEFAULT:
			setPinEditorByDefault((Boolean) newValue);
			return;
		case INavigatorModelPackage.NAVIGATOR_MANAGER__OPEN_MUST_OPEN_NEW:
			setOpenMustOpenNew((Boolean) newValue);
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
		case INavigatorModelPackage.NAVIGATOR_MANAGER__MODEL_TYPES:
			getModelTypes().clear();
			return;
		case INavigatorModelPackage.NAVIGATOR_MANAGER__USE_GENERIC_EDITOR_PART_FALLBACK:
			setUseGenericEditorPartFallback(USE_GENERIC_EDITOR_PART_FALLBACK_EDEFAULT);
			return;
		case INavigatorModelPackage.NAVIGATOR_MANAGER__PIN_EDITOR_BY_DEFAULT:
			setPinEditorByDefault(PIN_EDITOR_BY_DEFAULT_EDEFAULT);
			return;
		case INavigatorModelPackage.NAVIGATOR_MANAGER__OPEN_MUST_OPEN_NEW:
			setOpenMustOpenNew(OPEN_MUST_OPEN_NEW_EDEFAULT);
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
		case INavigatorModelPackage.NAVIGATOR_MANAGER__MODEL_TYPES:
			return modelTypes != null && !modelTypes.isEmpty();
		case INavigatorModelPackage.NAVIGATOR_MANAGER__USE_GENERIC_EDITOR_PART_FALLBACK:
			return useGenericEditorPartFallback != USE_GENERIC_EDITOR_PART_FALLBACK_EDEFAULT;
		case INavigatorModelPackage.NAVIGATOR_MANAGER__PIN_EDITOR_BY_DEFAULT:
			return pinEditorByDefault != PIN_EDITOR_BY_DEFAULT_EDEFAULT;
		case INavigatorModelPackage.NAVIGATOR_MANAGER__OPEN_MUST_OPEN_NEW:
			return openMustOpenNew != OPEN_MUST_OPEN_NEW_EDEFAULT;
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
		result.append(" (useGenericEditorPartFallback: ");
		result.append(useGenericEditorPartFallback);
		result.append(", pinEditorByDefault: ");
		result.append(pinEditorByDefault);
		result.append(", openMustOpenNew: ");
		result.append(openMustOpenNew);
		result.append(')');
		return result.toString();
	}

	/**
	 * Secondary of next editor
	 */
	private int myNextSecondaryId = 0;;

	@Override
	public IEditorPartView openView(EObject obj) {
		final Collection<IEditorPartView> views = getAllViews();

		/*
		 * Look for a perfect match
		 */
		for (final IEditorPartView v : views) {
			if (v.getCurrentObject() == obj) {
				/*
				 * This might change the view
				 */
				v.setCurrentObject(obj);
				return v;
			}
		}

		/*
		 * Look for an un-pinned view
		 */
		for (final IEditorPartView v : views) {
			if (!v.isPinned()) {
				v.setCurrentObject(obj);
				return v;
			}
		}

		/*
		 * Create a new view
		 */
		IViewPart view = null;
		try {
			view = PlatformUI
					.getWorkbench()
					.getActiveWorkbenchWindow()
					.getActivePage()
					.showView(NavigatorConstants.EDITOR_VIEW_ID, "" + myNextSecondaryId++, IWorkbenchPage.VIEW_ACTIVATE);
		} catch (final PartInitException ex) {
			LogUtils.error(this, ex);
			return null;
		}
		if (!(view instanceof IEditorPartView)) {
			LogUtils.error(view, "Part has ID " + NavigatorConstants.EDITOR_VIEW_ID + " but is not a IEditorPartView");
			return null;
		}

		final IEditorPartView v = (IEditorPartView) view;
		v.setCurrentObject(obj);

		return v;
	}

	@Override
	public Collection<IEditorPartView> getAllViews() {
		final Collection<IEditorPartView> views = new ArrayList<IEditorPartView>();
		final IWorkbenchWindow ww = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		for (final IViewReference ref : ww.getActivePage().getViewReferences()) {
			if (!ref.getId().equals(NavigatorConstants.EDITOR_VIEW_ID)) {
				continue;
			}

			final IWorkbenchPart part = ref.getPart(false);
			if (!(part instanceof IEditorPartView)) {
				LogUtils.error(part, "Part has ID " + NavigatorConstants.EDITOR_VIEW_ID
						+ " but is not a IEditorPartView");
				continue;
			}
			views.add((IEditorPartView) part);
		}
		return views;
	}

	@Override
	public void closeAllViews() {
		for (final IEditorPartView v : getAllViews()) {
			final IViewPart p = (IViewPart) v;
			p.getSite().getPage().hideView(p);
		}
	}

} // NavigatorManagerImpl
