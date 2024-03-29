package com.zhouzhu.springbootsecurity.demo.pojo.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zhouzhu.springbootsecurity.demo.pojo.DateAudit;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-06-13 14:16
 */
@MappedSuperclass
@JsonIgnoreProperties(
        value = {"createdBy","updatedBy"},
        allowGetters = true
)
public abstract class UserDateAudit extends DateAudit {
    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;

    @LastModifiedBy
    private Long updateBy;

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}
