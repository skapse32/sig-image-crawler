package org.sig.crawler.fill_image.gui_app.input_panel;

public class XmlTagInputPanel extends InputPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final IValueChecker tagChecker = new IValueChecker() {

		@Override
		public String getMessage(String value) {
			if (getCheckCode(value) == InputPanel.ValueReponseCode.ERROR)
				return "Invalid tag name";
			return "OK";
		}

		@Override
		public ValueReponseCode getCheckCode(String value) {
			for(char c : value.toUpperCase().toCharArray())
				if (!(c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c == '_'))
					return InputPanel.ValueReponseCode.ERROR;
			return InputPanel.ValueReponseCode.VALID;
		}
	}; 

	public XmlTagInputPanel(String message, String defaultValue) {
		super(message, defaultValue, tagChecker);
	}
}
