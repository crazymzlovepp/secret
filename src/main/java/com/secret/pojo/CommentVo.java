package com.secret.pojo;

import java.io.Serializable;
import java.util.Date;

public class CommentVo implements Serializable {
    private String articleId;

    private String commentId;

    private String commentName;

    private String parentCommentId;

    private String parentCommentName;

    private String content;

    private Date commentDate;

    private String commentIp;

    private static final long serialVersionUID = 1L;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId == null ? null : commentId.trim();
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName == null ? null : commentName.trim();
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId == null ? null : parentCommentId.trim();
    }

    public String getParentCommentName() {
        return parentCommentName;
    }

    public void setParentCommentName(String parentCommentName) {
        this.parentCommentName = parentCommentName == null ? null : parentCommentName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentIp() {
        return commentIp;
    }

    public void setCommentIp(String commentIp) {
        this.commentIp = commentIp == null ? null : commentIp.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleId=").append(articleId);
        sb.append(", commentId=").append(commentId);
        sb.append(", commentName=").append(commentName);
        sb.append(", parentCommentId=").append(parentCommentId);
        sb.append(", parentCommentName=").append(parentCommentName);
        sb.append(", content=").append(content);
        sb.append(", commentDate=").append(commentDate);
        sb.append(", commentIp=").append(commentIp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}