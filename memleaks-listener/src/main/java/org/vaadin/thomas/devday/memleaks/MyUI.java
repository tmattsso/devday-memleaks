package org.vaadin.thomas.devday.memleaks;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.Registration;
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
@Push
public class MyUI extends UI {

	private static final long serialVersionUID = -8454408720814290815L;
	private Button button;
	private Registration registration;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();

		button = new Button("Count is: ");
		button.addClickListener(e -> openHoggerWindow());

		layout.addComponents(button);

		setContent(layout);

		registration = PushNotifierService.addRegistration(c -> updatePopup(c));
	}

	@Override
	public void close() {
		registration.remove();
	}

	// @Override
	// public void detach() {
	// super.detach();
	// registration.remove();
	// }

	private void updatePopup(int count) {
		getUI().access(() -> button.setCaption("Count is: " + count));
	}

	private void openHoggerWindow() {
		getUI().addWindow(new HoggerWindow());
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

		private static final long serialVersionUID = 1117916787169822227L;
	}
}
