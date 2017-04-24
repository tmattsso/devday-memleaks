package org.vaadin.thomas.devday.memleaks;

import javax.ejb.Stateless;

@Stateless
public class DummyEJB {

	public String getString() {
		return "foo";
	}
}
