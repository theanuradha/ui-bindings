package com.rcpcompany.uibindings.internal.uiAttributeFactories;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Widget;

import com.rcpcompany.uibindings.IUIAttribute;
import com.rcpcompany.uibindings.IUIAttributeFactory;
import com.rcpcompany.uibindings.internal.observables.StyledTextRangesObservableList;
import com.rcpcompany.uibindings.internal.observables.TextObservableValue;
import com.rcpcompany.uibindings.internal.uiAttributeFactories.contentAdapters.StyledTextContentAdapter;
import com.rcpcompany.uibindings.uiAttributes.SimpleUIAttribute;

public class StyledTextDefaultUIAttributeFactory implements IUIAttributeFactory {

	@Override
	public IUIAttribute create(Widget widget, String attribute) {
		return new Attribute(widget, attribute);
	}

	private static class Attribute extends SimpleUIAttribute {
		private final IControlContentAdapter myAdapter;

		public Attribute(Widget widget, String attribute) {
			super(widget, attribute, new TextObservableValue((StyledText) widget), true);

			myAdapter = new StyledTextContentAdapter();
		}

		@Override
		public IObservableList getStyleRangeList() {
			final StyledText c = (StyledText) getWidget();
			return addObservable(new StyledTextRangesObservableList(c));
		}

		@Override
		public IControlContentAdapter getFieldAssistAdapter() {
			return myAdapter;
		}
	}
}