package com.examine.common.util;


import java.io.File;

public class FolderUtils {

    public static boolean createFolder(String baseUrl,String folderName) {
        boolean mStatus = false;
        StringBuilder sb = new StringBuilder();
        File file = new File(
                sb.append(baseUrl).append(folderName).toString());
        //判断文件夹是否存在u
        if(!file.exists()){
            mStatus = file.mkdir();
        }
        return mStatus;
    }

    public static void main(String[] args) {
        System.out.println(createFolder("src/main/resources/static/upload/","test"));
    }
}
