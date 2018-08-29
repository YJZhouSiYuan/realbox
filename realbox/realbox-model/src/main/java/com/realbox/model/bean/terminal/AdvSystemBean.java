package com.realbox.model.bean.terminal;

/**
 * Created by zph on 2017/12/28 0028.
 */
public class AdvSystemBean {
    private boolean isused;
    private String screenshot;
    private String scroll;
    private String clock;
    private String weather;
    private String language;


    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public void setScroll(String scroll) {
        this.scroll = scroll;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isused() {
        return isused;
    }

    public void setIsused(boolean isused) {
        this.isused = isused;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public String getScroll() {
        return scroll;
    }

    public String getClock() {
        return clock;
    }

    public String getWeather() {
        return weather;
    }

    public String getLanguage() {
        return language;
    }
}
