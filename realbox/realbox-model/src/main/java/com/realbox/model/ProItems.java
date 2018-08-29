/**
 * 代码归 YIJIE 所有,任何公司和个人不得擅自使用, 我方保留通过法律手段追究责任的权利.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.realbox.model;

import java.io.Serializable;

/**
 * @author MJJ
 * @create Id: ProItems.java v 0.1 2017年12月28日 11:59 MJJ Exp $
 **/
public class ProItems implements Serializable {

    private static final long serialVersionUID = 5429575180516028149L;

    // ID
    private String id;
    // 模板元素ID
    private String itemsId;
    // 节目ID
    private String proId;
    // 资源ID
    private String resId;
    // 资源缩略图
    private String thumbnail;
    // 资源路径
    private String url;
    // 资源动作(仅图片)
    private String actionMode;
    // 播放时长
    private String playTime;
    // 图片播放时间
    private Integer imagePlayTime = 0;
    // 滚动速度
    private Integer scrollSpeed;
    // 滚动字体大小
    private Integer scrollFontSize;
    // 滚动字体持续时间
    private Integer scrollDuration;
    // 滚动方向:0从左到右,1从右到左
    private Integer scrollDirection;
    // 滚动字体
    private String scrollFontFamily;
    // 滚动字体颜色
    private String scrollColor;
    // 滚动字体透明度
    private String scrollFontTransparency;
    // 背景颜色
    private String scrollBGColor;
    // 背景透明度
    private String scrollBGTransparency;

    // 文本字体
    private String font;
    // 字体大小
    private Integer fontSize;
    // 颜色
    private String fontColor;
    // 文本对齐方式
    private String align;
    // 加粗
    private String bold;
    // 斜杠
    private String italic;
    // 下划线
    private String underline;
    // 层级
    private int zIndex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemsId() {
        return itemsId;
    }

    public void setItemsId(String itemsId) {
        this.itemsId = itemsId;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getActionMode() {
        return actionMode;
    }

    public void setActionMode(String actionMode) {
        this.actionMode = actionMode;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public Integer getImagePlayTime() {
        return imagePlayTime;
    }

    public void setImagePlayTime(Integer imagePlayTime) {
        this.imagePlayTime = imagePlayTime;
    }

    public Integer getScrollSpeed() {
        return scrollSpeed;
    }

    public void setScrollSpeed(Integer scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
    }

    public Integer getScrollFontSize() {
        return scrollFontSize;
    }

    public void setScrollFontSize(Integer scrollFontSize) {
        this.scrollFontSize = scrollFontSize;
    }

    public Integer getScrollDuration() {
        return scrollDuration;
    }

    public void setScrollDuration(Integer scrollDuration) {
        this.scrollDuration = scrollDuration;
    }

    public Integer getScrollDirection() {
        return scrollDirection;
    }

    public void setScrollDirection(Integer scrollDirection) {
        this.scrollDirection = scrollDirection;
    }

    public String getScrollFontFamily() {
        return scrollFontFamily;
    }

    public void setScrollFontFamily(String scrollFontFamily) {
        this.scrollFontFamily = scrollFontFamily;
    }

    public String getScrollColor() {
        return scrollColor;
    }

    public void setScrollColor(String scrollColor) {
        this.scrollColor = scrollColor;
    }

    public String getScrollFontTransparency() {
        return scrollFontTransparency;
    }

    public void setScrollFontTransparency(String scrollFontTransparency) {
        this.scrollFontTransparency = scrollFontTransparency;
    }

    public String getScrollBGColor() {
        return scrollBGColor;
    }

    public void setScrollBGColor(String scrollBGColor) {
        this.scrollBGColor = scrollBGColor;
    }

    public String getScrollBGTransparency() {
        return scrollBGTransparency;
    }

    public void setScrollBGTransparency(String scrollBGTransparency) {
        this.scrollBGTransparency = scrollBGTransparency;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getBold() {
        return bold;
    }

    public void setBold(String bold) {
        this.bold = bold;
    }

    public String getItalic() {
        return italic;
    }

    public void setItalic(String italic) {
        this.italic = italic;
    }

    public String getUnderline() {
        return underline;
    }

    public void setUnderline(String underline) {
        this.underline = underline;
    }

    public int getzIndex() {
        return zIndex;
    }

    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }
}
