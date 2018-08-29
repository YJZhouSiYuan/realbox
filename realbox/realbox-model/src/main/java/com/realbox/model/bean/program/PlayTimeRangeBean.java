package com.realbox.model.bean.program;

/**
 * Created by Administrator on 2017/12/6 0006.
 */
public class PlayTimeRangeBean {

    /**
     * datetime : {}
     * type : date
     */
    private DatetimeBean datetime;
    private String type;

    public void setDatetime(DatetimeBean datetime) {
        this.datetime = datetime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DatetimeBean getDatetime() {
        return datetime;
    }

    public String getType() {
        return type;
    }
}
