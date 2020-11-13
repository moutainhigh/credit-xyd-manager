package com.winchampion.credit.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.UUID;

public class FileUtil {

	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		out.write(file);
		out.flush();
		out.close();
	}

	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static String renameToUUID(String fileName) {
		return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	
	/**
	 * 将文件变为base64
	 * @author: zhangxin  
	 * @date:2020年2月26日  下午3:49:18
	 * @param imageFile
	 * @return
	 */
	public static String encodeImgageToBase64(byte[] bytes) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
//        ByteArrayOutputStream outputStream = null;
//        try {
//            BufferedImage bufferedImage = ImageIO.read(imageFile);
//            outputStream = new ByteArrayOutputStream();
//            ImageIO.write(bufferedImage, "jpg", outputStream);
//        } catch (MalformedURLException e1) {
//            e1.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // 对字节数组Base64编码
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);// 返回Base64编码过的字节数组字符串
    }
}
