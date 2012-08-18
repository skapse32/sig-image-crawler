package org.sig.crawler.fill_image.gui_app;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;


public class GUIApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static GUIApp instance_;
	private static final Dimension DEFAULT_FRAME_SiZE = new Dimension(1000, 500);
	
	protected GUIApp(){
		super("Sig auto image leecher");
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(DEFAULT_FRAME_SiZE);
		super.getContentPane().add(new JScrollPane(new MainPanel()), BorderLayout.CENTER);
	}

	public static GUIApp getInstance() {
		if (instance_ == null)
			instance_ = new GUIApp();
		return instance_;
	}

	public void start() {
		super.setVisible(true);
	}

}
