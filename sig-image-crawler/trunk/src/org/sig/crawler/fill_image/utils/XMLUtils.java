package org.sig.crawler.fill_image.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * Các hàm làm việc với XML và tập tin 
 * @author haidang-ubuntu
 *
 */
public class XMLUtils {
	
	private static XMLUtils instance_;
	
	public static XMLUtils getInstance(){
		if (instance_ == null)
			instance_ = new XMLUtils();
		return instance_;
	}
	
	protected XMLUtils(){}
    
	/**
	 * Chuyển xml file (Document) thành String
	 * @param doc Xml file cần chuyển
	 * @return trả về xâu - thông tin đọc trực tiếp được khi in ra, đọc vào
	 * @throws TransformerException trả về lỗi cho các hàm bên ngoài kiểm soát để thống kê
	 */
	public String xmlToString(Document doc) throws TransformerException{
		//set up a transformer
		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer trans = transfac.newTransformer();
		trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		trans.setOutputProperty(OutputKeys.INDENT, "yes");

		//create string from xml tree
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc);
		trans.transform(source, result);
		return sw.toString();
	}
	
	/**
	 * Ghi xml file ra tập tin hệ thống
	 * @param doc xml file cần ghi
	 * @param fileName tên file cần lưu
	 */
	public void writeXmlToFile(Document doc, String fileName){
		FileWriter fw = null;
		BufferedWriter bwt = null;
		try {
			fw = new FileWriter(fileName);
			bwt = new BufferedWriter(fw);  
			bwt.write(xmlToString(doc));
			JOptionPane.showMessageDialog(new JFrame("No title"), "Write file success");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame("No title"), "Write file failed\nMessage: " + e.getMessage());
		} finally{
			try{
			if (bwt != null)
				bwt.close();
			if (fw != null)
				fw.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Nhập file xml từ một tập tin trên hệ thống
	 * @param fileName tên tập tin cần nhập
	 * @return trả về xml file nếu có lỗi thì đưa ra thông báo và trả về null
	 */
	public Document getDocumentFromFile(String fileName){
		Document doc = null;
		File inputFile = new File(fileName);
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputFile);
			JOptionPane.showMessageDialog(new JFrame("No title"), "Read file success");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame("No title"), "Read file failed\nMessage: " + e.getMessage());
		}
		return doc;
	}

	public boolean isValidTag(String value) {
		for(char c : value.toUpperCase().toCharArray())
		{
			if (!(c >= '0' && c <= '9' || c >='A' && c <= 'Z' || c == '_'))
				return false;
		}
			return true;
	}


}