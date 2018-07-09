package org.webdriver.duiaui.util;

public class UrlRecombine {

    public String urlRecombine(String url){
        String regex1 = "%3A" ,regex2="%2F" ,regex3="%3F",regex4="%3D";
        while(url.contains("%")){
            url.replaceAll(regex1,":");
            url.replaceAll(regex2,"/");
            url.replaceAll(regex3,"?");
            url.replaceAll(regex4,"=");
        }
        return url;
    }



    public enum URlMark{
        冒号(":", "%3A"), 反斜杠("/", "%2F"), 问号("?", "%3F"), 等号("=","%3D" );
        String mark;
        String key;
        URlMark(String mark, String key) {
            this.mark=mark;
            this.key=key;
        }
        public static String getMark(String key){
            for(URlMark u : URlMark.values()){
                if(u.getKey() == key){
                    return u.mark;
                }
            }
            return null;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
