package org.vaadin.thomas.devday.memleaks;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;

import com.vaadin.ui.Grid;
import com.vaadin.ui.Window;

@Dependent
public class HoggerWindow extends Window {

	private static final long serialVersionUID = -1859991693920652434L;

	public HoggerWindow() {

		final List<DataHogger> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(new DataHogger());
		}

		final Grid<DataHogger> grid = new Grid<>(DataHogger.class);
		grid.setItems(list);
		grid.setSizeFull();
		setContent(grid);

		setCaption("Data!");
		center();
		setHeight("60%");
		setWidth("600px");
		setModal(true);
	}
}
