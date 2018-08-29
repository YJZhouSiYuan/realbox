/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author MJJ
 * @create Id: Inter.java v 0.1 2018年03月08日 17:49 MJJ Exp $
 **/
public class Inter implements Serializable {

    private static final long serialVersionUID = 3794270094456085182L;

    // ID
    private String id;
    // 语言
    private String language;
    // 初始化树
    private List<Tree> trees;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public void setTrees(List<Tree> trees) {
        this.trees = trees;
    }

    public Inter() {
    }

    public Inter(String language, List<Tree> trees) {
        this.language = language;
        this.trees = trees;
    }
}
