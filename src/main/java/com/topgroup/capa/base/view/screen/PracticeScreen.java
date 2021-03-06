package com.topgroup.capa.base.view.screen;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@Component
@Scope("prototype")
public class PracticeScreen extends CustomComponent {

	private static final long serialVersionUID = -4479639018984308437L;
		
	private VerticalLayout mainLayout;
	
	public PracticeScreen() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	@AutoGenerated
	private void buildMainLayout() {
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		setWidth("100.0%");
		setHeight("100.0%");
		Label label = new Label("Práctica de Commons- Vaadin");
		label.addStyleName("title");
		mainLayout.addComponent(label);
	}
}
