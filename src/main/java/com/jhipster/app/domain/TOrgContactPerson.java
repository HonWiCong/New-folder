package com.jhipster.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TOrgContactPerson.
 */
@Entity
@Table(name = "t_org_contact_person")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TOrgContactPerson implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ocp_orgcodeid")
    private Long ocpOrgcodeid;

    @Column(name = "ocp_title")
    private String ocpTitle;

    @Column(name = "ocp_name")
    private String ocpName;

    @Column(name = "ocp_designation")
    private String ocpDesignation;

    @Column(name = "ocp_telephone_1")
    private String ocpTelephone1;

    @Column(name = "ocp_handphone")
    private String ocpHandphone;

    @Column(name = "ocp_mail")
    private String ocpMail;

    @Column(name = "ocp_sector")
    private String ocpSector;

    @Column(name = "ocp_status")
    private String ocpStatus;

    @Column(name = "entered_by")
    private Integer enteredBy;

    @Column(name = "entered_date")
    private ZonedDateTime enteredDate;

    @Column(name = "modified_by")
    private Integer modifiedBy;

    @Column(name = "modified_date")
    private ZonedDateTime modifiedDate;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tOrgContactPeople" }, allowSetters = true)
    private TOrganization tOrganization;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TOrgContactPerson id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOcpOrgcodeid() {
        return this.ocpOrgcodeid;
    }

    public TOrgContactPerson ocpOrgcodeid(Long ocpOrgcodeid) {
        this.setOcpOrgcodeid(ocpOrgcodeid);
        return this;
    }

    public void setOcpOrgcodeid(Long ocpOrgcodeid) {
        this.ocpOrgcodeid = ocpOrgcodeid;
    }

    public String getOcpTitle() {
        return this.ocpTitle;
    }

    public TOrgContactPerson ocpTitle(String ocpTitle) {
        this.setOcpTitle(ocpTitle);
        return this;
    }

    public void setOcpTitle(String ocpTitle) {
        this.ocpTitle = ocpTitle;
    }

    public String getOcpName() {
        return this.ocpName;
    }

    public TOrgContactPerson ocpName(String ocpName) {
        this.setOcpName(ocpName);
        return this;
    }

    public void setOcpName(String ocpName) {
        this.ocpName = ocpName;
    }

    public String getOcpDesignation() {
        return this.ocpDesignation;
    }

    public TOrgContactPerson ocpDesignation(String ocpDesignation) {
        this.setOcpDesignation(ocpDesignation);
        return this;
    }

    public void setOcpDesignation(String ocpDesignation) {
        this.ocpDesignation = ocpDesignation;
    }

    public String getOcpTelephone1() {
        return this.ocpTelephone1;
    }

    public TOrgContactPerson ocpTelephone1(String ocpTelephone1) {
        this.setOcpTelephone1(ocpTelephone1);
        return this;
    }

    public void setOcpTelephone1(String ocpTelephone1) {
        this.ocpTelephone1 = ocpTelephone1;
    }

    public String getOcpHandphone() {
        return this.ocpHandphone;
    }

    public TOrgContactPerson ocpHandphone(String ocpHandphone) {
        this.setOcpHandphone(ocpHandphone);
        return this;
    }

    public void setOcpHandphone(String ocpHandphone) {
        this.ocpHandphone = ocpHandphone;
    }

    public String getOcpMail() {
        return this.ocpMail;
    }

    public TOrgContactPerson ocpMail(String ocpMail) {
        this.setOcpMail(ocpMail);
        return this;
    }

    public void setOcpMail(String ocpMail) {
        this.ocpMail = ocpMail;
    }

    public String getOcpSector() {
        return this.ocpSector;
    }

    public TOrgContactPerson ocpSector(String ocpSector) {
        this.setOcpSector(ocpSector);
        return this;
    }

    public void setOcpSector(String ocpSector) {
        this.ocpSector = ocpSector;
    }

    public String getOcpStatus() {
        return this.ocpStatus;
    }

    public TOrgContactPerson ocpStatus(String ocpStatus) {
        this.setOcpStatus(ocpStatus);
        return this;
    }

    public void setOcpStatus(String ocpStatus) {
        this.ocpStatus = ocpStatus;
    }

    public Integer getEnteredBy() {
        return this.enteredBy;
    }

    public TOrgContactPerson enteredBy(Integer enteredBy) {
        this.setEnteredBy(enteredBy);
        return this;
    }

    public void setEnteredBy(Integer enteredBy) {
        this.enteredBy = enteredBy;
    }

    public ZonedDateTime getEnteredDate() {
        return this.enteredDate;
    }

    public TOrgContactPerson enteredDate(ZonedDateTime enteredDate) {
        this.setEnteredDate(enteredDate);
        return this;
    }

    public void setEnteredDate(ZonedDateTime enteredDate) {
        this.enteredDate = enteredDate;
    }

    public Integer getModifiedBy() {
        return this.modifiedBy;
    }

    public TOrgContactPerson modifiedBy(Integer modifiedBy) {
        this.setModifiedBy(modifiedBy);
        return this;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public ZonedDateTime getModifiedDate() {
        return this.modifiedDate;
    }

    public TOrgContactPerson modifiedDate(ZonedDateTime modifiedDate) {
        this.setModifiedDate(modifiedDate);
        return this;
    }

    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public TOrganization getTOrganization() {
        return this.tOrganization;
    }

    public void setTOrganization(TOrganization tOrganization) {
        this.tOrganization = tOrganization;
    }

    public TOrgContactPerson tOrganization(TOrganization tOrganization) {
        this.setTOrganization(tOrganization);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TOrgContactPerson)) {
            return false;
        }
        return id != null && id.equals(((TOrgContactPerson) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TOrgContactPerson{" +
            "id=" + getId() +
            ", ocpOrgcodeid=" + getOcpOrgcodeid() +
            ", ocpTitle='" + getOcpTitle() + "'" +
            ", ocpName='" + getOcpName() + "'" +
            ", ocpDesignation='" + getOcpDesignation() + "'" +
            ", ocpTelephone1='" + getOcpTelephone1() + "'" +
            ", ocpHandphone='" + getOcpHandphone() + "'" +
            ", ocpMail='" + getOcpMail() + "'" +
            ", ocpSector='" + getOcpSector() + "'" +
            ", ocpStatus='" + getOcpStatus() + "'" +
            ", enteredBy=" + getEnteredBy() +
            ", enteredDate='" + getEnteredDate() + "'" +
            ", modifiedBy=" + getModifiedBy() +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
