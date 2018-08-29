package com.realbox.model.bean.program;

/**
 * Created by Administrator on 2017/12/6 0006.
 */
public class ItemsBean {

    /**
     * id : 12345
     * itemType : image
     * data : {}
     * geometry : {}
     */
    private String id;
    private String itemType;
    private DataEntityBean dataEntity;
    private GeometryBean geometry;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public DataEntityBean getDataEntity() {
        return dataEntity;
    }

    public void setDataEntity(DataEntityBean dataEntity) {
        this.dataEntity = dataEntity;
    }

    public GeometryBean getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryBean geometry) {
        this.geometry = geometry;
    }
}
