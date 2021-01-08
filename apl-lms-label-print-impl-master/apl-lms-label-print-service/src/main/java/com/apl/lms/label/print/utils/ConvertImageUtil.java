package com.apl.lms.label.print.utils;

import com.apl.lib.exception.AplException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import java.io.*;

/**
 * @author hjr start
 * @Classname ConvertImageUtil
 * @Date 2021/1/6 10:01
 */
@Component
public class ConvertImageUtil {

    enum ConvertImageUtilEnum{

        THE_PATH_TO_SAVE_THE_FILE_CANNOT_BE_EMPTY("THE_PATH_TO_SAVE_THE_FILE_CANNOT_BE_EMPTY", "保存文件的路径不能为空");

        private String code;
        private String msg;

        ConvertImageUtilEnum(String code, String msg){
            this.code = code;
            this.msg = msg;
        }
    }

    public static void base64StringToImage(String base64Content, String filePath) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            byte[] bytes = decoder.decodeBuffer(base64Content);//base64编码内容转换为字节数组
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            bis = new BufferedInputStream(byteInputStream);
            File file = new File(filePath);
            File path = file.getParentFile();
            if (!path.exists()) {
                path.mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while (length != -1) {
                bos.write(buffer, 0, length);
                length = bis.read(buffer);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != bis)
                bis.close();
            if (null != fos)
                fos.close();
            if (null != bos)
                bos.close();
        }
    }

    /**
     * 图片解码
     *
     * @param base64Data    base64编码
     * @param fileSaveFullPath 保存文件的完整路径
     * @return
     * @throws IOException
     */
    public static boolean base64GeneratorImage(String base64Data, String fileSaveFullPath) throws IOException { // 对字节数组字符串进行Base64解码并生成图片

        if (base64Data == null) // 图像数据为空
            return false;

        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try {
            out = new FileOutputStream(fileSaveFullPath);
            // Base64解码
            byte[] b = decoder.decodeBuffer(base64Data);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据 把-1 全改成255
                    b[i] += 256;
                }
            }
            out.write(b);

        } catch (Exception e) {
            throw new AplException(e.getMessage(), e.getCause().toString());
        } finally {
            out.flush();
            out.close();
            return true;
        }
    }

    /**
     * 获取xml主节点
     * @param xmlFile
     * @return
     */
    public static Element createXmlElement(File xmlFile){

        if (null == xmlFile) {
            return null;
        }

        Element rootNode;
        try {
            //读取xml
            SAXReader reader = new SAXReader();
            org.dom4j.Document document = reader.read(xmlFile);
            rootNode = document.getRootElement();
        }catch (Exception e){
            throw new AplException(e.getMessage(), e.getCause().toString());
        }
        return rootNode;
    }


}
