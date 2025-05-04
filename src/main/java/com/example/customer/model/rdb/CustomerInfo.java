package com.example.customer.model.rdb;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;


@Entity
@Table(name = "TCUSTOMER")
public class CustomerInfo {

    public CustomerInfo(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OBJ_ID")
    private Integer objectId;

    @Column(name = "USER_ID")
    private String unid;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unId) {
        this.unid = unId;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
