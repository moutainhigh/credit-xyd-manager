package com.winchampion.credit.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;

/**
 * @author 王立朝 wanglichao@champion-credit.com
 * @date 2020/10/30
 * @description:
 */
public class DownloadFileUtil {

    public static final String separator = File.separator;
    private static Logger logger = LoggerFactory.getLogger(DownloadFileUtil.class);
    /**
     * 下载样表
     * @param filePath 文件上级目录
     * @param newName  下载的展示文件名
     * @return 响应
     */
    public static ResponseEntity<InputStreamResource> download(String filePath,  String newName) {
        //String route = "static" + separator + "templates" + separator;
        String path = null;
        ResponseEntity<InputStreamResource> response = null;
        try {
           // path = route + filePath + separator + fileName;
            path = filePath ;
            File file = new File(path);
            ClassPathResource classPathResource = new ClassPathResource(path);

            //InputStream inputStream = classPathResource.getInputStream();
            InputStream inputStream = new FileInputStream(file);
            //File file = new File(path);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition",
                    "attachment; filename="
                            + new String(newName.getBytes("gbk"), "iso8859-1") + ".xlsx");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            response = ResponseEntity.ok().headers(headers)
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(inputStream));
        } catch (FileNotFoundException e1) {
            logger.error("找不到指定的文件", e1);
        } catch (IOException e) {
            logger.error("获取不到文件流", e);
        }
        return response;
    }

    public static ResponseEntity<InputStreamResource> download(InputStream inputStream,  String newName) {

        ResponseEntity<InputStreamResource> response = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition",
                    "attachment; filename="
                            + new String(newName.getBytes("gbk"), "iso8859-1") + ".xlsx");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            response = ResponseEntity.ok().headers(headers)
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(inputStream));
        } catch (IOException e) {
            logger.error("获取不到文件流", e);
        }
        return response;
    }
}

