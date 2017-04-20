package org.vaadin.thomas.devday.memleaks;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
@CDIUI("")
public class MyUI extends UI {

	private static final long serialVersionUID = -8454408720814290815L;

	@Inject
	private HoggerWindow w;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();

		final Button button = new Button("Click Me");
		button.addClickListener(e -> openHoggerWindow());

		layout.addComponents(button);

		setContent(layout);
	}

	private void openHoggerWindow() {

		w.close();

		// We could inject it, but we might need many
		final HoggerWindow window = CDI.current().select(HoggerWindow.class).get();
		getUI().addWindow(window);
	}

}
