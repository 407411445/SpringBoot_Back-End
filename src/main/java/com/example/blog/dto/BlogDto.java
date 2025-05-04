package com.example.blog.dto;

public class BlogDto {
    private Integer objectId;
    private String unid;
    private String headline;
    private String author;
    private String content;
    private String createTime;
    private String updateTime;
    public BlogDto(String unid, String author,String content, String createTime, String updateTime ) {
        this.unid = unid;
        this.author = author;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
