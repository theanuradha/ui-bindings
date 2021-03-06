/*******************************************************************************
 * Copyright (c) 2006-2013 The RCP Company and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     The RCP Company - initial API and implementation
 *******************************************************************************/
package com.rcpcompany.uibindings.internal.observables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.DisposeEvent;
import org.eclipse.core.databinding.observable.IDisposeListener;
import org.eclipse.core.databinding.observable.IObserving;
import org.eclipse.core.databinding.observable.ObservableTracker;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.ObservableList;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.util.Policy;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.rcpcompany.uibindings.model.utils.BasicUtils;

/**
 * 
 */

public class MyDetailObservableList extends ObservableList implements IObserving {

	private final boolean updating = false;

	private IListChangeListener innerChangeListener = new IListChangeListener() {
		@Override
		public void handleListChange(ListChangeEvent event) {
			/*
			 * Solves SIMA-694: Unhandled exception: java.lang.Double cannot be cast to
			 * org.eclipse.emf.common.util.EList -
			 * http://jira.marintek.sintef.no/jira/browse/SIMA-694
			 */
			if (isDisposed()) return;
			if (!updating) {
				fireListChange(event.diff);
			}
		}
	};

	private Object currentOuterValue;

	private IObservableList innerObservableList;

	private IObservableFactory factory;

	private IObservableValue outerObservableValue;

	private final Object detailType;

	/**
	 * @param factory
	 * @param outerObservableValue
	 * @param detailType
	 */
	public MyDetailObservableList(IObservableFactory factory, IObservableValue outerObservableValue, Object detailType) {
		super(outerObservableValue.getRealm(), Collections.EMPTY_LIST, detailType);
		Assert.isTrue(!outerObservableValue.isDisposed(), "Master observable is disposed"); //$NON-NLS-1$

		this.factory = factory;
		this.outerObservableValue = outerObservableValue;
		this.detailType = detailType;

		outerObservableValue.addDisposeListener(new IDisposeListener() {
			@Override
			public void handleDispose(DisposeEvent staleEvent) {
				dispose();
			}
		});

		ObservableTracker.runAndIgnore(new Runnable() {
			@Override
			public void run() {
				updateInnerObservableList();
			}
		});
		outerObservableValue.addValueChangeListener(outerChangeListener);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[]";
	};

	IValueChangeListener outerChangeListener = new IValueChangeListener() {
		@Override
		public void handleValueChange(ValueChangeEvent event) {
			/*
			 * Solves SIMA-694: Unhandled exception: java.lang.Double cannot be cast to
			 * org.eclipse.emf.common.util.EList -
			 * http://jira.marintek.sintef.no/jira/browse/SIMA-694
			 */
			if (isDisposed()) return;
			ObservableTracker.runAndIgnore(new Runnable() {
				@Override
				public void run() {
					final List oldList = new ArrayList(wrappedList);
					updateInnerObservableList();
					fireListChange(Diffs.computeListDiff(oldList, wrappedList));
				}
			});
		}
	};

	private void updateInnerObservableList() {
		if (innerObservableList != null) {
			innerObservableList.removeListChangeListener(innerChangeListener);
			innerObservableList.dispose();
		}
		currentOuterValue = outerObservableValue.getValue();
		if (currentOuterValue == null) {
			innerObservableList = null;
			wrappedList = Collections.EMPTY_LIST;
		} else {
			ObservableTracker.runAndIgnore(new Runnable() {
				@Override
				public void run() {
					innerObservableList = (IObservableList) factory.createObservable(currentOuterValue);
				}
			});
			warnIfDifferentRealms(getRealm(), innerObservableList.getRealm());
			wrappedList = innerObservableList;

			if (detailType != null) {
				final Object innerValueType = innerObservableList.getElementType();
				Assert.isTrue(getElementType().equals(innerValueType),
						"Cannot change value type in a nested observable list"); //$NON-NLS-1$
			}
			innerObservableList.addListChangeListener(innerChangeListener);
		}
	}

	@Override
	public boolean add(final Object o) {
		final boolean[] result = new boolean[1];
		ObservableTracker.runAndIgnore(new Runnable() {
			@Override
			public void run() {
				result[0] = wrappedList.add(o);
			}
		});
		return result[0];
	}

	@Override
	public void add(final int index, final Object element) {
		ObservableTracker.runAndIgnore(new Runnable() {
			@Override
			public void run() {
				wrappedList.add(index, element);
			}
		});
	}

	@Override
	public boolean remove(final Object o) {
		final boolean[] result = new boolean[1];
		ObservableTracker.runAndIgnore(new Runnable() {
			@Override
			public void run() {
				result[0] = wrappedList.remove(o);
			}
		});
		return result[0];
	}

	@Override
	public Object set(final int index, final Object element) {
		final Object[] result = new Object[1];
		ObservableTracker.runAndIgnore(new Runnable() {
			@Override
			public void run() {
				result[0] = wrappedList.set(index, element);
			}
		});
		return result[0];
	}

	@Override
	public Object move(final int oldIndex, final int newIndex) {
		if (innerObservableList != null) {
			final Object[] result = new Object[1];
			ObservableTracker.runAndIgnore(new Runnable() {
				@Override
				public void run() {
					result[0] = innerObservableList.move(oldIndex, newIndex);
				}
			});
			return result[0];
		}
		return super.move(oldIndex, newIndex);
	}

	@Override
	public Object remove(final int index) {
		final Object[] result = new Object[1];
		ObservableTracker.runAndIgnore(new Runnable() {
			@Override
			public void run() {
				result[0] = wrappedList.remove(index);
			}
		});
		return result[0];
	}

	@Override
	public boolean addAll(final Collection c) {
		final boolean[] result = new boolean[1];
		ObservableTracker.runAndIgnore(new Runnable() {
			@Override
			public void run() {
				result[0] = wrappedList.addAll(c);
			}
		});
		return result[0];
	}

	@Override
	public boolean addAll(final int index, final Collection c) {
		final boolean[] result = new boolean[1];
		ObservableTracker.runAndIgnore(new Runnable() {
			@Override
			public void run() {
				result[0] = wrappedList.addAll(index, c);
			}
		});
		return result[0];
	}

	@Override
	public boolean removeAll(final Collection c) {
		final boolean[] result = new boolean[1];
		ObservableTracker.runAndIgnore(new Runnable() {
			@Override
			public void run() {
				result[0] = wrappedList.removeAll(c);
			}
		});
		return result[0];
	}

	@Override
	public boolean retainAll(final Collection c) {
		final boolean[] result = new boolean[1];
		ObservableTracker.runAndIgnore(new Runnable() {
			@Override
			public void run() {
				result[0] = wrappedList.retainAll(c);
			}
		});
		return result[0];
	}

	@Override
	public void clear() {
		ObservableTracker.runAndIgnore(new Runnable() {
			@Override
			public void run() {
				wrappedList.clear();
			}
		});
	}

	@Override
	public synchronized void dispose() {
		super.dispose();

		if (outerObservableValue != null) {
			outerObservableValue.removeValueChangeListener(outerChangeListener);
		}
		if (innerObservableList != null) {
			innerObservableList.removeListChangeListener(innerChangeListener);
			innerObservableList.dispose();
		}
		outerObservableValue = null;
		outerChangeListener = null;
		currentOuterValue = null;
		factory = null;
		innerObservableList = null;
		innerChangeListener = null;
	}

	@Override
	public Object getObserved() {
		if (innerObservableList instanceof IObserving) return ((IObserving) innerObservableList).getObserved();
		return null;
	}

	private static void warnIfDifferentRealms(Realm detailRealm, Realm innerObservableRealm) {
		if (!BasicUtils.equals(detailRealm, innerObservableRealm)) {
			final Throwable throwable = new Throwable();
			throwable.fillInStackTrace();
			final String message = "Inner observable realm (" + innerObservableRealm //$NON-NLS-1$
					+ ") not equal to detail realm (" //$NON-NLS-1$
					+ detailRealm + ")"; //$NON-NLS-1$
			Policy.getLog().log(new Status(IStatus.WARNING, Policy.JFACE_DATABINDING, message, throwable));
		}
	}
}
