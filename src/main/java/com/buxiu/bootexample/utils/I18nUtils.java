package com.buxiu.bootexample.utils;
 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class I18nUtils {

    public final static String ZH = "zh";
    public final static String EN = "en";
    public static String language = ZH;

    static Properties props = new Properties();
    static Properties props_en = new Properties(); 
    static {
        try {
            props.load(new InputStreamReader(I18nUtils.class.getResourceAsStream("/i18.properties"), "UTF-8"));
            props_en.load(new InputStreamReader(I18nUtils.class.getResourceAsStream("/i18_en.properties"), "UTF-8"));
//            System.out.println(props.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getKey(String language, String key) {
        if (EN.equals(language))
            return props_en.getProperty(key, key);
        return props.getProperty(key, key);
    }

    public static String getErrorDescription(int errorcode) {
        String key = "ERROR" + String.valueOf(errorcode);

        if(ZH.equals(language)){
            return props.getProperty(key, key);
        }else if(EN.equals(language)){
            return  props_en.getProperty(key, key);
        }else{
            return props_en.getProperty(key, key);
        }
    }

//    private static int getLanguageIndex(String language) {
//        if (EN.equals(language))
//            return 1;
//        return 0;
//    }


}
