/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.common.utils;

/**
 * @author MJJ
 * @version Id: Pages.java, v 0.1 2017年07月18日 15:09 MJJ Exp $
 **/
public class Pages {
    // 当前页数
    private Integer pageNo;
    // 分页值
    private Integer pageNum;
    // 总页面数
    private Integer pageMax;
    // 总数量
    private Long count;
    // 页面显示数量
    private Integer pageCount;

    public Pages() { }

    public Pages(Integer pageNo, Integer pageCount, Long count) {
        this.pageNo = pageNo;
        this.pageCount = pageCount;
        this.count = count;
    }

    /**
     * 获取最大页面
     *
     * @return
     */
    public Integer getMax() {
        int max = this.getCount().intValue()/this.getPageCount();
        if (this.getCount() % this.getPageCount() !=0) {
            return max + 1;
        }
        return max;
    }

    /**
     * 验证当前页码
     *
     * @return
     */
    public Integer getPageNo() {
        if (this.pageNo <= 0) {
            return this.pageNo = 1;
        }
        if (this.pageNo > getPageMax()) {
            this.pageNo = getPageMax();
        }
        return this.pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageMax() {
        return getMax();
    }

    public void setPageMax(Integer pageMax) {
        this.pageMax = pageMax;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getPageNum() {
        return (getPageNo() -1) * getPageCount();
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
