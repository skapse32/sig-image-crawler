package org.sig.crawler.fill_image.gui_app.input_panel;


public class IntegerLowerBoundInputer extends InputPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final IValueChecker intLowerBoundChecker(final int lowerBound){
		return new IValueChecker(){
			@Override
			public String getMessage(String value) {
				InputPanel.ValueReponseCode code = getCheckCode(value);
				if (code == InputPanel.ValueReponseCode.WARNING)
					return "Value lower than required";
				if (code == InputPanel.ValueReponseCode.ERROR)
					return "Not integer";
				//InputPanel.ValueReponseCode.VALID:
				return "OK";
			};

			@Override
			public ValueReponseCode getCheckCode(String value) {
				try{
					if (Integer.parseInt(value) > lowerBound)
						return InputPanel.ValueReponseCode.VALID;
					return InputPanel.ValueReponseCode.WARNING;
				}catch (Exception e){
					return InputPanel.ValueReponseCode.ERROR;
				}
			};
		};
	}

	public IntegerLowerBoundInputer(String message, int defaultValue, final int lowerBound) {
		super(message, String.valueOf(defaultValue), intLowerBoundChecker(lowerBound));
	}
}