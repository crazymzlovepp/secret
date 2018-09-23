package com.secret.pojo;

import java.io.Serializable;
import java.util.Date;

public class ArticleVo implements Serializable {
    private String articleId;

    private String userId;

    private String articleUsername;

    private String articleTitle;

    private String articleType;

    private Long browse;

    private String reportTag;

    private Integer reportNum;

    private String deleteTag;

    private Date createDate;

    private Date updateDate;

    private String createIp;

    private String updateIp;

    private String content;

    private static final long serialVersionUID = 1L;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getArticleUsername() {
        return articleUsername;
    }

    public void setArticleUsername(String articleUsername) {
        this.articleUsername = articleUsername == null ? null : articleUsername.trim();
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType == null ? null : articleType.trim();
    }

    public Long getBrowse() {
        return browse;
    }

    public void setBrowse(Long browse) {
        this.browse = browse;
    }

    public String getReportTag() {
        return reportTag;
    }

    public void setReportTag(String reportTag) {
        this.reportTag = reportTag == null ? null : reportTag.trim();
    }

    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }

    public String getDeleteTag() {
        return deleteTag;
    }

    public void setDeleteTag(String deleteTag) {
        this.deleteTag = deleteTag == null ? null : deleteTag.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp == null ? null : updateIp.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleId=").append(articleId);
        sb.append(", userId=").append(userId);
        sb.append(", articleUsername=").append(articleUsername);
        sb.append(", articleTitle=").append(articleTitle);
        sb.append(", articleType=").append(articleType);
        sb.append(", browse=").append(browse);
        sb.append(", reportTag=").append(reportTag);
        sb.append(", reportNum=").append(reportNum);
        sb.append(", deleteTag=").append(deleteTag);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", createIp=").append(createIp);
        sb.append(", updateIp=").append(updateIp);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}