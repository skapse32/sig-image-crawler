package org.sig.crawler.fill_image.gui_app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import org.sig.crawler.fill_image.gui_app.func_panel.FuncArea;
import org.sig.crawler.fill_image.gui_app.input_panel.InputArea;


public class MainPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InputArea inputArea_;
	
	public MainPanel(){
		super();
		inputArea_ = new InputArea();
		super.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(inputArea_, gbc);
		gbc.gridy = 1;
		this.add(new FuncArea(this), gbc);
	}

//	public Tuple<int,int> abc(){}
//	public ArrayList<> getParamsList() {
//		return null;
//	}

	public boolean hasError() {
		return inputArea_.hasError();
	}

}
