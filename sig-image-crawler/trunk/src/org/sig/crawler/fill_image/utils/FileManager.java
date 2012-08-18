package org.sig.crawler.fill_image.utils;

import java.io.File;

/**
 * Các hàm liên quan đến tập tin.
 * @author haidang-ubuntu
 *
 */
public class FileManager {
	
	/**
	 * Singleton pattern
	 */
	private static FileManager instance_;
	
	/**
	 * Singleton pattern
	 * @return singleton instance
	 */
	public static FileManager getInstance(){
		if (instance_ == null)
			instance_ = new FileManager();
		return instance_;
	}
	
	/**
	 * Tìm ra file hiện không có trên hệ thống ở thư mục hiện tại, 
	 * với phần mở rỗng cho trước
	 * @param postFix phần mở rộng (tự điền dấu chấm vào)
	 * @return trả về tên file
	 */
	public String getUniqueFileName(String prefix, String postFix){
		for(int i = 0; ; i++)
		{
			String name = prefix + String.valueOf(i) + postFix;
			if (!(new File(name).exists()))
				return name;
		}
	}
	
	/** Từ một string, dự đoán ra định dạng của tập tin 
	 * (hình ảnh tải về). Có bắt lỗi trong trường hợp link lởm. 
	 * VD: không có phần đuổi xác định định dạng hoặc có định dạng
	 * sai.
	 * @param url đường dẫn cần lấy.
	 * @return trả về phần đuổi (có chứa dấu chấm) nếu thấy có vẻ đúng
	 * hoặc rỗng nếu ngược lại.
	 */
	public String getPostFix(String url){
		int id = url.lastIndexOf('.');
		if (id == -1)
			return "";
		String tmp = url.substring(id, url.length()).replace(' ', '-');
		if (tmp.length() > 4)
			return "";
		return tmp;
	}
}