package org.sig.crawler.fill_image.console_app;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.sig.crawler.fill_image.utils.FileManager;
import org.sig.crawler.fill_image.utils.XMLUtils;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/** 
 * Thành phần làm công việc chính của chương trình. Do có thể phát triển định hướng
 * làm GUI xịn nên mới đặt tên là ConsoleApp :)
 * @author haidang-ubuntu
 */
public class ConsoleApp {

	/** 
	 * Singleton instance
	 */
	private static ConsoleApp instance_;

	/**
	 * Prefix of file name to save - or directory
	 */
	private static final String DATA_DIR = "/home/haidang-ubuntu/Development/image/sig/java/data/";

	/**
	 * Tag bổ sung ảnh sau khi load về
	 */
	private static final String TO_ADD_TAG = "offfile";
	/**
	 * Tag chứa nội dung ảnh
	 */
	private static final String TO_FIX_TAG = "hasImage";
	/**
	 * Tag chứa link trong <code>TO_FIX_TAG</code>
	 */
	private static final String DATA_TAG = "url";

	/**
	 * Singleton model requires
	 */
	protected ConsoleApp() {}

	/**
	 * Singleton model requires
	 * @return singleton instance
	 */
	public static ConsoleApp getInstance(){
		if (instance_ == null)
			instance_ = new ConsoleApp();
		return instance_;
	}

	/**
	 * Lần gần hết mọi thứ, đọc nội dung xml, kiểm tra, bổ sung, lưu ra file mới.
	 * @param inp tên file dữ liệu
	 * @param out tên file output (tự động ghi đè mà không hỏi)
	 * @param nCores số threads để tăng tốc (có thể giảm ;))==> hiện chưa hỗ trợ
	 */
	public void fixFile(String inp, String out, int nCores){
		Document doc = XMLUtils.getInstance().getDocumentFromFile(inp);
		if (doc == null)
			return;
		//xử lý chính ở đoạn này keke
		NodeList instances = doc.getElementsByTagName(TO_FIX_TAG);
		int cnt = 0;
		for(int i = 0; i < instances.getLength(); i++)
			cnt += fixElement(instances.item(i));
		JOptionPane.showMessageDialog(new JFrame("No title"), "Fixed / All: " + cnt + "/" + instances.getLength());
		//kết thúc đoạn xử lý chính kè kè
		XMLUtils.getInstance().writeXmlToFile(doc, out);
	}

	/**
	 * Fix instance: find URL, leech it and append \<offfile\> tag to node. Auto-check if instance is fixable or not.
	 * If having more than 1 url, try to fix as possible as.
	 * @param item instance to fix
	 * @return 1 if instance fixed and 0 otherwise
	 */
	private int fixElement(Node item) {
		if (!isFixableElement(item))
			return 0;
		int cnt = 0;
		for(String url : getUrlsFromElement(item)) {
			String postFix = FileManager.getInstance().getPostFix(url);
			String fileName = FileManager.getInstance().getUniqueFileName(DATA_DIR, postFix);
			if (FileLeecher.getInstance().leechFile(url, fileName))
			{
				Element newEl = item.getOwnerDocument().createElement(TO_ADD_TAG);
				newEl.appendChild(item.getOwnerDocument().createTextNode(fileName));
				item.appendChild(newEl);
				cnt = 1;
			}
		}
		return cnt;
	}

	/**
	 * Kiểm tra một node có thể fix được hay không (đã bao gồm "có cần fix hay không")
	 * @param item node item cần check, xem hàm <code>fixElement</code> 
	 * @return tra về đúng nếu <code>item</code> cần được fix.
	 */
	private boolean isFixableElement(Node item) {
		for(int i = 0; i < item.getChildNodes().getLength(); i++)
			if (item.getChildNodes().item(i).getNodeName() == TO_ADD_TAG)
				return false;

		//check if data tag existed
		for(int i = 0; i < item.getChildNodes().getLength(); i++)
			if (item.getChildNodes().item(i).getNodeName() == DATA_TAG)
				return true;
		return false;
	}

	/**
	 * Trích các urls từ node item
	 * @param item item node để trích
	 * @return mảng chứa các url (dạng thô - chưa encode sang http request)
	 */
	private String[] getUrlsFromElement(Node item){
		ArrayList<String> ret = new ArrayList<>();  
		NodeList lnode;
		for(int i = 0; i < item.getChildNodes().getLength(); i++)
			if (item.getChildNodes().item(i).getNodeName() == DATA_TAG)
			{
				lnode = item.getChildNodes().item(i).getChildNodes();
				if (lnode.getLength() > 0)
					ret.add(lnode.item(0).getNodeValue());
			}
		return (String[])ret.toArray(new String[ret.size()]);
	}

	/**
	 * Hàm test, không cần biết
	 * @param fileName không cần biết
	 */
	public void test(String fileName) {
		try {
			/////////////////////////////
			//Creating an empty XML Document

			//We need a Document
			DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			////////////////////////
			//Creating the XML tree

			//create the root element and add it to the document
			Element root = doc.createElement("root");
			doc.appendChild(root);

			//create a comment and put it in the root element
			Comment comment = doc.createComment("Just a thought");
			root.appendChild(comment);

			//create child element, add an attribute, and add to root
			Element child = doc.createElement("child");
			child.setAttribute("name", "value");
			root.appendChild(child);

			//add a text element to the child
			Text text = doc.createTextNode("Filler, ... I could have had a foo!");
			child.appendChild(text);

			//Create sub child
			Element subChild = doc.createElement("sub_child");
			subChild.appendChild(doc.createTextNode("Data of subchild"));
			child.appendChild(subChild);

			//			for(int i = 0; i < child.getChildNodes().getLength(); i++)
			int i = 0;
			System.out.println("child: " + child.getChildNodes().item(i).getChildNodes().getLength());
			//System.out.println("child: " + child.getChildNodes().item(i).getChildNodes().item(0).getNodeValue());

			//print xml
			System.out.println("Here's the xml:\n\n" + XMLUtils.getInstance().xmlToString(doc));

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void start() {
		String inputFileName = (String)JOptionPane.showInputDialog("Enter input file name (xml file) ", "/home/haidang-ubuntu/Development/image/sig/img-retriever.xml");
		if (inputFileName == null)
			return;
		String outputFileName = (String)JOptionPane.showInputDialog("Enter output file name (xml file) ", "/home/haidang-ubuntu/Development/image/sig/img-retriever-out.xml");
		if (outputFileName == null)
			return;
		int nCores = 1;
		boolean success = false;
		do{
			String str = (String)JOptionPane.showInputDialog("Enter number of threads to create (not supported)", "1");
			try{
				nCores = Integer.parseInt(str);
				success = true;
			} catch (Exception e)
			{
				success = false;
			}
			fixFile(inputFileName, outputFileName, nCores);
			JOptionPane.showMessageDialog(new JFrame("No title"), "Application exited");
		} while (!success);		
	}
}
