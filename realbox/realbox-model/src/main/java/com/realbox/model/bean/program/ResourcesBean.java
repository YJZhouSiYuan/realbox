package com.realbox.model.bean.program;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6 0006.
 */
public class ResourcesBean {

    /**
     * resList : []
     * itemId : 12345
     */
    private List<ResListBean> resList;
    private String itemId;

    public List<ResListBean> getResList() {
        return resList;
    }

    public void setResList(List<ResListBean> resList) {
        this.resList = resList;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
