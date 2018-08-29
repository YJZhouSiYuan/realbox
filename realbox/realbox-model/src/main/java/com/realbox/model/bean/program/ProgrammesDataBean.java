package com.realbox.model.bean.program;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6 0006.
 */
public class ProgrammesDataBean {

    /**
     * id : 998114
     * resources : []
     * items : []
     */
    private String id;
    private List<ResourcesBean> resources;
    private List<ItemsBean> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ResourcesBean> getResources() {
        return resources;
    }

    public void setResources(List<ResourcesBean> resources) {
        this.resources = resources;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }
}
