package com.liu.common;

import java.io.Serializable;
import java.util.Date;

/**
 * [STRATO MyBatis Generator]
 * Table: game_user
 @mbggenerated do_not_delete_during_merge 2016-10-17 16:35:16
 */
public class GameUser implements Serializable{

    //private static final Serializable SERIALIZABLE =
    /**
     *   id  游戏用户id
     * Column: game_user.id
     @mbggenerated 2016-10-17 16:35:17
     */
    private Long id;

    /**
     *   用户pin
     * Column: game_user.user_pin
     @mbggenerated 2016-10-17 16:35:17
     */
    private String userPin;

    /**
     *   手机号
     * Column: game_user.mobile_phone
     @mbggenerated 2016-10-17 16:35:17
     */
    private String mobilePhone;

    /**
     *   0:删除;1:正常
     * Column: game_user.yn
     @mbggenerated 2016-10-17 16:35:17
     */
    private Byte yn;

    /**
     *   更新时间
     * Column: game_user.modified
     @mbggenerated 2016-10-17 16:35:17
     */
    private Date modified;

    /**
     *   创建时间
     * Column: game_user.created
     @mbggenerated 2016-10-17 16:35:17
     */
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserPin() {
        return userPin;
    }

    public void setUserPin(String userPin) {
        this.userPin = userPin == null ? null : userPin.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public Byte getYn() {
        return yn;
    }

    public void setYn(Byte yn) {
        this.yn = yn;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}