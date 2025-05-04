package com.example.customer.dto;

public class CustomerDto {
    private Integer objectId;
    private String unid;
    private String name;
    private String email;
    private String createTime;



    public CustomerDto(String unid, String name, String email, String createTime) {
        this.unid = unid;
        this.name = name;
        this.email = email;
        this.createTime = createTime;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
