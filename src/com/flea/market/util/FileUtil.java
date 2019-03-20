package com.flea.market.util;

/**
 * @author zl
 * @time 2019/3/7
 */
public class FileUtil {

    public static String getFileName(String head){
        if (head==null||"".equals(head)){
            throw new RuntimeException("头信息字符串不能为空");
        }
        return head.substring(head.lastIndexOf("=")+2,head.length()-1);
    }

    public static String getSuffix(String fileName){
        if (fileName==null||"".equals(fileName)) {
            throw new RuntimeException("文件名不能为空");
        }
        return fileName.substring(fileName.lastIndexOf("."), fileName.length());
    }

}
