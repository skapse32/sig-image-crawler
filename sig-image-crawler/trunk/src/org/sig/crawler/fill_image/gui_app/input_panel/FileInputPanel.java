package org.sig.crawler.fill_image.gui_app.input_panel;

import java.io.File;


public class FileInputPanel extends InputPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final IValueChecker inputFileChecker = new IValueChecker() {
		@Override
		public String getMessage(String value) {
			if (getCheckCode(value) == InputPanel.ValueReponseCode.VALID)
				return "OK. File exists!";
			return "Opps. File not exist!";
		}

		@Override
		public ValueReponseCode getCheckCode(String value) {
			if (new File(value).exists())
				return InputPanel.ValueReponseCode.VALID;
			return InputPanel.ValueReponseCode.ERROR;
		}
	};
	private static final IValueChecker outputFileChecker = new IValueChecker() {
		
		@Override
		public String getMessage(String value) {
			if (getCheckCode(value) == InputPanel.ValueReponseCode.VALID)
				return "OK. File not exist!";
			return "File exists. Program will overrite it";
		}

		@Override
		public ValueReponseCode getCheckCode(String value) {
			if ((new File(value)).exists())
				return InputPanel.ValueReponseCode.WARNING;
			return InputPanel.ValueReponseCode.VALID;
			
		}
	};

	public FileInputPanel(String message, String defaultValue,
			boolean isInputFile) {
		super(message, defaultValue, null);
		if (isInputFile)
		super.setValueChecker(inputFileChecker);
		else{
			super.setValueChecker(outputFileChecker);
		}
	}

}
