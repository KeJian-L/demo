package com.example.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Author: KjLi
 * @Description:
 * @Data: Create in 2019/12/11
 * @Modified By:
 */
public class LocalConfig {
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", new Locale("zh", "CN"));
    public static String address;

    public static String getValue(String key){
        if(!bundle.containsKey(key)) throw new NullPointerException("\""+key+"\" is not exist");
        return bundle.getString(key);
    }
}
