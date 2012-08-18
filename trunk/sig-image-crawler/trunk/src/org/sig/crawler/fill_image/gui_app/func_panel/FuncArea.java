package org.sig.crawler.fill_image.gui_app.func_panel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.sig.crawler.fill_image.gui_app.MainPanel;

public class FuncArea extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainPanel parent_;
	
	protected FuncArea(){}
	
	public FuncArea(MainPanel parent){
		super();
		super.setAutoscrolls(true);
		parent_ = parent;
		super.setLayout(new GridLayout(1, 2, 10, 10));
		JButton okButton = new JButton("OK");
		okButton.setPreferredSize(new Dimension(20, 20));
		super.add(okButton);
		super.add(new JButton("Quit"));
		
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				if (parent_.hasError())
//					JOptionPane.showMessageDialog(parent_, "Input errors");
//				else
//					LeechFrame.getInstance().run(parent_.getParamsList());
			}
		});
	}

}
