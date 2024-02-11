package com.sergion.model;

public class User implements IUser {

    private String id;
    private String name;
    private String groupId;
    private String status;
    private String userType;

    public User(String id, String name, String groupId, String status, String userType) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
        this.status = status;
        this.userType = userType;
    }
    public User(){

    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGroupId() {
        return groupId;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getUserType() {
        return userType;
    }
}
