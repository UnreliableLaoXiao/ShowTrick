package cn.hallowebsite.lib.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class FileUtil {

    public static ArrayList<File> getList(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        return (ArrayList<File>) Arrays.asList(files);
    }

}
