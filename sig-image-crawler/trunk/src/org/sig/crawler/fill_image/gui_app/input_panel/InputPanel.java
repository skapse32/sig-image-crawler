package org.sig.crawler.fill_image.gui_app.input_panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class InputPanel extends JPanel {

	/**
	 * @param valueChecker: can be null
	 */
	private static final long serialVersionUID = 1L;
	private IValueChecker valueChecker_;
	private JLabel label_;
	private JTextField text_;
	private JLabel notification_;
	private static final int DEFAULT_TEXT_LENGTH = 40; 
	private static final int DEFAULT_LABEL_WIDTH = 200;
	private static final int DEFAULT_NOTIFICATION_WIDTH = 200;
	private DocumentListener textCheckerListener_;
	private static final Color WARNING_NOFTIFICATION_COLOR = Color.ORANGE;
	private static final Color ERROR_NOFTIFICATION_COLOR = Color.RED;
	private static final Color VALID_NOTIFICATION_COLOR = Color.GREEN;
	private static final Color ERROR_VALUE_COLOR = ERROR_NOFTIFICATION_COLOR;
	private static final Color VALID_VALUE_COLOR = VALID_NOTIFICATION_COLOR;
	public enum ValueReponseCode{
		ERROR, WARNING, VALID
	};

	public InputPanel(String message, String defaultValue, IValueChecker valueChecker){
		super();
		label_ = new JLabel(message, JLabel.LEFT);
		label_.setPreferredSize(new Dimension(DEFAULT_LABEL_WIDTH, (int)getPreferredSize().getHeight()));
		text_ = new JTextField(defaultValue, DEFAULT_TEXT_LENGTH);
		notification_ = new JLabel("Any");//TODO: do not understand why need to put any other than empty string to this construct??
		notification_.setPreferredSize(new Dimension(DEFAULT_NOTIFICATION_WIDTH, (int)notification_.getPreferredSize().getHeight()));
		valueChecker_ = valueChecker;

		this.add(label_);
		this.add(text_);
		this.add(notification_);

		text_.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				text_.select(0,  0);
			}

			@Override
			public void focusGained(FocusEvent e) {
				text_.selectAll();
			}
		});
		setValueChecker(valueChecker);
	}

	protected void setValueChecker(IValueChecker iValueChecker) {
		if (valueChecker_ != null)
			if (textCheckerListener_ != null)
				text_.getDocument().removeDocumentListener(textCheckerListener_);
		valueChecker_ = iValueChecker;
		if (valueChecker_ != null){
			textCheckerListener_ = new DocumentListener() {

				@Override
				public void removeUpdate(DocumentEvent e) {
					checkValue();
				}

				private void checkValue() {
					if (valueChecker_ == null)
						return;
					ValueReponseCode code = valueChecker_.getCheckCode((text_.getText()));
					if (code == ValueReponseCode.ERROR){
						text_.setForeground(ERROR_VALUE_COLOR);
						notification_.setForeground(ERROR_NOFTIFICATION_COLOR);
					}
					else{
						text_.setForeground(VALID_VALUE_COLOR);
						if (code == ValueReponseCode.WARNING)
							notification_.setForeground(WARNING_NOFTIFICATION_COLOR);
						else
							notification_.setForeground(VALID_NOTIFICATION_COLOR);
					}
					notification_.setText(valueChecker_.getMessage(text_.getText()));
				}

				@Override
				public void insertUpdate(DocumentEvent e) {
					checkValue();
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					checkValue();
				}
			};
			text_.getDocument().addDocumentListener(textCheckerListener_);
		}
		text_.setText(text_.getText());
	}

	public boolean hasError() {
		if (valueChecker_ == null)
			return false;
		return valueChecker_.getCheckCode(text_.getText()) == ValueReponseCode.ERROR;
	}
}