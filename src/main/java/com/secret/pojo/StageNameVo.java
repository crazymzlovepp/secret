package com.secret.pojo;

import java.io.Serializable;
import java.util.Date;

public class StageNameVo implements Serializable {
    private String stageNameId;

    private String stageName;

    private Date createDate;

    private Date updateDate;

    private String remarks;

    private static final long serialVersionUID = 1L;

    public String getStageNameId() {
        return stageNameId;
    }

    public void setStageNameId(String stageNameId) {
        this.stageNameId = stageNameId == null ? null : stageNameId.trim();
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName == null ? null : stageName.trim();
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stageNameId=").append(stageNameId);
        sb.append(", stageName=").append(stageName);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}