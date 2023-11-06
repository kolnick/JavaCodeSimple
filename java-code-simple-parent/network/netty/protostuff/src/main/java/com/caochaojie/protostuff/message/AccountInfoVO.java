package com.caochaojie.protostuff.message;


/**
 * @Author: caochaojie
 * @Date: 2019/5/14
 */
public class AccountInfoVO {
    private Integer accountId;
    private Integer level;
    private Integer mainCityLevel;
    private Integer vipLevel;
    private String icon;
    private String iconFrame;


    public AccountInfoVO() {

    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getMainCityLevel() {
        return mainCityLevel;
    }

    public void setMainCityLevel(Integer mainCityLevel) {
        this.mainCityLevel = mainCityLevel;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconFrame() {
        return iconFrame;
    }

    public void setIconFrame(String iconFrame) {
        this.iconFrame = iconFrame;
    }
}
