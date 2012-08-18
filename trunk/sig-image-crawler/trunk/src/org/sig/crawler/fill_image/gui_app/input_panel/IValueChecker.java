package org.sig.crawler.fill_image.gui_app.input_panel;


public interface IValueChecker {
	String getMessage(String value);
	InputPanel.ValueReponseCode getCheckCode(String value);
}
