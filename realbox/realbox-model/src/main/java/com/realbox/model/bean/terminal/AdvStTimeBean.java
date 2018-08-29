package com.realbox.model.bean.terminal;

import java.util.List;

/**
 * Created by zph on 2017/12/28 0028.
 */
public class AdvStTimeBean {


    /**
     * isused : False
     * time : []
     */
    private boolean isused;
    private List<String> time;

    public boolean isused() {
        return isused;
    }

    public void setIsused(boolean isused) {
        this.isused = isused;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }


    public List<String> getTime() {
        return time;
    }
}
