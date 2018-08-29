package com.realbox.model.bean.program;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6 0006.
 */
public class ProgrammeListBean {

    /**
     * id : 456
     * programmesData : []
     */
    private String id;
    private List<ProgrammesDataBean> programmesData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProgrammesDataBean> getProgrammesData() {
        return programmesData;
    }

    public void setProgrammesData(List<ProgrammesDataBean> programmesData) {
        this.programmesData = programmesData;
    }
}
