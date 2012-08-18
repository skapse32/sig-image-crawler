package org.sig.crawler.fill_image.gui_app.input_panel;

public class IntegerInputPanel extends InputPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final IValueChecker intChecker = new IValueChecker(){

		@Override
		public String getMessage(String value) {
			if (getCheckCode(value) == InputPanel.ValueReponseCode.ERROR)
				return "Not number!";
			return "OK";
			}

		@Override
		public ValueReponseCode getCheckCode(String value) {
			try{
				Integer.parseInt(value);
				return InputPanel.ValueReponseCode.VALID;
			} catch(Exception e){
				return InputPanel.ValueReponseCode.ERROR;
			}
		}
		
	};

	public IntegerInputPanel(String message, int defaultValue){
		super(message, String.valueOf(defaultValue), intChecker);
	}

}
