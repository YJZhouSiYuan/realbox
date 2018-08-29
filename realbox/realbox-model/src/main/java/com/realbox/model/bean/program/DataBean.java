package com.realbox.model.bean.program;

/**
 * Created by Administrator on 2017/12/6 0006.
 */
public class DataBean {

    /**
     * distributeProperty : {}
     * programmeList : {}
     * method : add
     */
    private DistributePropertyBean distributeProperty;
    private ProgrammeListBean programmeList;
    private String method;

    public void setDistributeProperty(DistributePropertyBean distributeProperty) {
        this.distributeProperty = distributeProperty;
    }

    public void setProgrammeList(ProgrammeListBean programmeList) {
        this.programmeList = programmeList;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public DistributePropertyBean getDistributeProperty() {
        return distributeProperty;
    }

    public ProgrammeListBean getProgrammeList() {
        return programmeList;
    }

    public String getMethod() {
        return method;
    }


    @Override
    public String toString() {
        return "DataBean{" +
                "distributeProperty=" + distributeProperty +
                ", programmeList=" + programmeList +
                ", method='" + method + '\'' +
                '}';
    }
}
