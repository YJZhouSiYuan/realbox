package com.realbox.model.bean.program;

/**
 * Created by Administrator on 2017/12/6 0006.
 */
public class ResListBean {

    /**
     * protocol : http
     * name : x536432.jpg
     * type : image
     * url : httpwww.baidu.com
     */
    private String protocol;
    private String name;
    private String type;
    private String url;

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
