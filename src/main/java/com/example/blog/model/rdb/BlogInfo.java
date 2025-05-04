package com.example.blog.model.rdb;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "BLOG")
public class BlogInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OBJ_ID")
    private Integer objectId;

    @Column(name = "BLOG_ID")
    private String unid;

    @Column(name = "BLOG_HEADLINE")
    private String headline;

    @Column(name = "BLOG_OWNER")
    private String author;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

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

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public LocalDateTime getCreateTime() {

        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {

        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
