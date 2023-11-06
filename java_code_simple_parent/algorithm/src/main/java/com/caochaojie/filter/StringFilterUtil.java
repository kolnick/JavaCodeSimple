package com.caochaojie.filter;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.CharsetUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.List;

public class StringFilterUtil {
    private static TrieFilter tf = new TrieFilter();
    private static String filterFileName = "filter.txt";

    public static void load() {
        List<String> list = null;
        Resource resourceObj = ResourceUtil.getResourceObj(filterFileName);
        File file = new File(resourceObj.getUrl().getPath());
        list = FileUtil.readLines(file, CharsetUtil.UTF_8);
        if (list != null) {
            for (String key : list) {
                tf.addKey(key);
            }
        }
    }

    public static String filter(String content) {
        return tf.replace(content, '*');
    }

    public static boolean isLegal(String content) {
        return !tf.hasBadWord(content);
    }

}
