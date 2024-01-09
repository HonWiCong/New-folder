package com.jhipster.app.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TAuditTrail.
 */
@Entity
@Table(name = "t_audit_trail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TAuditTrail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "date_time")
    private ZonedDateTime dateTime;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "audit_action")
    private String auditAction;

    @Column(name = "record_id")
    private Integer recordId;

    @Column(name = "field_desc")
    private String fieldDesc;

    @Column(name = "at_status")
    private String atStatus;

    @Column(name = "st_full_desc")
    private String stFullDesc;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TAuditTrail id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public TAuditTrail userId(Integer userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ZonedDateTime getDateTime() {
        return this.dateTime;
    }

    public TAuditTrail dateTime(ZonedDateTime dateTime) {
        this.setDateTime(dateTime);
        return this;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTableName() {
        return this.tableName;
    }

    public TAuditTrail tableName(String tableName) {
        this.setTableName(tableName);
        return this;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getAuditAction() {
        return this.auditAction;
    }

    public TAuditTrail auditAction(String auditAction) {
        this.setAuditAction(auditAction);
        return this;
    }

    public void setAuditAction(String auditAction) {
        this.auditAction = auditAction;
    }

    public Integer getRecordId() {
        return this.recordId;
    }

    public TAuditTrail recordId(Integer recordId) {
        this.setRecordId(recordId);
        return this;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getFieldDesc() {
        return this.fieldDesc;
    }

    public TAuditTrail fieldDesc(String fieldDesc) {
        this.setFieldDesc(fieldDesc);
        return this;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }

    public String getAtStatus() {
        return this.atStatus;
    }

    public TAuditTrail atStatus(String atStatus) {
        this.setAtStatus(atStatus);
        return this;
    }

    public void setAtStatus(String atStatus) {
        this.atStatus = atStatus;
    }

    public String getStFullDesc() {
        return this.stFullDesc;
    }

    public TAuditTrail stFullDesc(String stFullDesc) {
        this.setStFullDesc(stFullDesc);
        return this;
    }

    public void setStFullDesc(String stFullDesc) {
        this.stFullDesc = stFullDesc;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TAuditTrail)) {
            return false;
        }
        return id != null && id.equals(((TAuditTrail) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TAuditTrail{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", dateTime='" + getDateTime() + "'" +
            ", tableName='" + getTableName() + "'" +
            ", auditAction='" + getAuditAction() + "'" +
            ", recordId=" + getRecordId() +
            ", fieldDesc='" + getFieldDesc() + "'" +
            ", atStatus='" + getAtStatus() + "'" +
            ", stFullDesc='" + getStFullDesc() + "'" +
            "}";
    }
}
