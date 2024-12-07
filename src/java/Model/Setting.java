/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Luu Bach
 */
public class Setting {

    int settingId;
    String settingValue;
    int settingOrder;
    String settingCategory;
    boolean status;

    public int getSettingId() {
        return settingId;
    }

    public void setSettingId(int settingId) {
        this.settingId = settingId;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public int getSettingOrder() {
        return settingOrder;
    }

    public void setSettingOrder(int settingOrder) {
        this.settingOrder = settingOrder;
    }

    public String getSettingCategory() {
        return settingCategory;
    }

    public void setSettingCategory(String settingCategory) {
        this.settingCategory = settingCategory;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
