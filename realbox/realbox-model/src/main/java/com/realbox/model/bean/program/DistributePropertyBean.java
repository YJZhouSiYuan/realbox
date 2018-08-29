package com.realbox.model.bean.program;

/**
 * Created by Administrator on 2017/12/6 0006.
 */
public class DistributePropertyBean {

    /**
     * disStrategy : append
     * invalidTime : 2017-10-29
     * disType : now
     * playTimeRange : {}
     * playMode : loop
     */
    private String disStrategy;
    private String invalidTime;
    private String disType;
    private PlayTimeRangeBean playTimeRange;
    private String playMode;

    public void setDisStrategy(String disStrategy) {
        this.disStrategy = disStrategy;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

    public void setDisType(String disType) {
        this.disType = disType;
    }

    public void setPlayTimeRange(PlayTimeRangeBean playTimeRange) {
        this.playTimeRange = playTimeRange;
    }

    public void setPlayMode(String playMode) {
        this.playMode = playMode;
    }

    public String getDisStrategy() {
        return disStrategy;
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public String getDisType() {
        return disType;
    }

    public PlayTimeRangeBean getPlayTimeRange() {
        return playTimeRange;
    }

    public String getPlayMode() {
        return playMode;
    }

}
