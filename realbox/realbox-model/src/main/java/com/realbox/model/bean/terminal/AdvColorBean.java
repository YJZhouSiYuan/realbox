package com.realbox.model.bean.terminal;

/**
 * Created by zph on 2017/12/28 0028.
 */
public class AdvColorBean {
    private int contrast;
    private boolean isused;
    private int hue;
    private int strength;

    public void setContrast(int contrast) {
        this.contrast = contrast;
    }


    public void setHue(int hue) {
        this.hue = hue;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getContrast() {
        return contrast;
    }

    public boolean isused() {
        return isused;
    }

    public void setIsused(boolean isused) {
        this.isused = isused;
    }

    public int getHue() {
        return hue;
    }

    public int getStrength() {
        return strength;
    }
}
