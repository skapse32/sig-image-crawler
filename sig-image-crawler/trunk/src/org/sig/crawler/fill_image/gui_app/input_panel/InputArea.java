package org.sig.crawler.fill_image.gui_app.input_panel;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;


public class InputArea extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_INPUT_FILE_PATH = "/home/haidang-ubuntu/Development/image/sig/java/img-retriever.xml";
	private static final String DEFAULT_OUTPUT_FILE_PATH = "/home/haidang-ubuntu/Development/image/sig/java/img-retriever.xml";
	private static final String DEFAULT_OUTPUT_DATA_DIR = "/home/haidang-ubuntu/Development/image/sig/java/data";
	private static final int MIN_NUM_THREADS = 2;
	private static final int DEFAULT_NUM_THREADS = 4;
	private static final String DEFAULT_IMG_TAG = "hasImage";
	private static final String DEFAULT_URL_TAG = "url";
	private static final String DEFAULT_ADD_TAG = "offfile";
	private FileInputPanel inputFileInputer_;
	private FileInputPanel outputFileInputer_;
	private FileInputPanel dataDirInputer_;
	private IntegerLowerBoundInputer nThreadsInputer_;
	private XmlTagInputPanel imageTagInputer_;
	private XmlTagInputPanel urlTagInputer_;
	private XmlTagInputPanel dataTagInputer_;
	private ArrayList<InputPanel> inputList_;

	public InputArea(){
		super();
		super.setLayout(new GridLayout(7, 1, 10, 10));
		super.setAutoscrolls(true);
		inputList_ = new ArrayList<>();
		inputFileInputer_ = new FileInputPanel("Input file path", DEFAULT_INPUT_FILE_PATH, true);
		outputFileInputer_ = new FileInputPanel("Output file path", DEFAULT_OUTPUT_FILE_PATH, false);
		dataDirInputer_ = new FileInputPanel("Crawled data directory", DEFAULT_OUTPUT_DATA_DIR, true);
		nThreadsInputer_ = new IntegerLowerBoundInputer("Number of threads (≥" + MIN_NUM_THREADS + ")", DEFAULT_NUM_THREADS, MIN_NUM_THREADS);
		imageTagInputer_ = new XmlTagInputPanel("Tag of image to fill", DEFAULT_IMG_TAG);
		urlTagInputer_ = new XmlTagInputPanel("Tag to get url", DEFAULT_URL_TAG);
		dataTagInputer_ = new XmlTagInputPanel("Tag to create will obtain local path", DEFAULT_ADD_TAG);
		
		inputList_.add(inputFileInputer_);
		inputList_.add(outputFileInputer_);
		inputList_.add(dataDirInputer_);
		inputList_.add(nThreadsInputer_);
		inputList_.add(imageTagInputer_);
		inputList_.add(urlTagInputer_);
		inputList_.add(dataTagInputer_);
		for(InputPanel it : inputList_)
			this.add(it);
		super.setBorder(BorderFactory.createTitledBorder("Input parameter"));
	}
	
	public boolean hasError(){
		for(InputPanel it : inputList_)
			if (it.hasError())
				return true;
		return false;
	}
	
}