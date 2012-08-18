package org.sig.crawler.fill_image;

import org.sig.crawler.fill_image.gui_app.GUIApp;

/**
 * Class chính
 * @author haidang-ubuntu
 *
 */
public class Main {
	
	/**Đầu vào chính chương trình, yêu cầu người dùng nhập tên file (xml) vào và tên file ra (xml).
	 * Ngoài ra còn có chức năng nhập số threads để tạo nhằm tăng tốc (đồng bộ tốc độ máy với tốc độ mạng)
	 * (chức năng hiện chưa hỗ trợ).
	 * Chương trình nhận file xml đúng dạng (well form), đọc các tag <code>TO_FIX_TAG</code>, kiểm tra có link và local
	 * file sẵn chưa để tự động tiến hành các bổ sung nếu có
	 * @param args tham số chuẩn, không cần có
	 */
	public static void main(String[] args) {
		//ConsoleApp.getInstance().start();
		GUIApp.getInstance().start();
	}
}