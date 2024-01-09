package com.jhipster.app.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TSectorCode.
 */
@Entity
@Table(name = "t_sector_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TSectorCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sector_name")
    private String sectorName;

    @Column(name = "sector_description")
    private String sectorDescription;

    @Column(name = "entered_by")
    private Integer enteredBy;

    @Column(name = "entered_date")
    private ZonedDateTime enteredDate;

    @Column(name = "modified_by")
    private Integer modifiedBy;

    @Column(name = "modified_date")
    private ZonedDateTime modifiedDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TSectorCode id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSectorName() {
        return this.sectorName;
    }

    public TSectorCode sectorName(String sectorName) {
        this.setSectorName(sectorName);
        return this;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getSectorDescription() {
        return this.sectorDescription;
    }

    public TSectorCode sectorDescription(String sectorDescription) {
        this.setSectorDescription(sectorDescription);
        return this;
    }

    public void setSectorDescription(String sectorDescription) {
        this.sectorDescription = sectorDescription;
    }

    public Integer getEnteredBy() {
        return this.enteredBy;
    }

    public TSectorCode enteredBy(Integer enteredBy) {
        this.setEnteredBy(enteredBy);
        return this;
    }

    public void setEnteredBy(Integer enteredBy) {
        this.enteredBy = enteredBy;
    }

    public ZonedDateTime getEnteredDate() {
        return this.enteredDate;
    }

    public TSectorCode enteredDate(ZonedDateTime enteredDate) {
        this.setEnteredDate(enteredDate);
        return this;
    }

    public void setEnteredDate(ZonedDateTime enteredDate) {
        this.enteredDate = enteredDate;
    }

    public Integer getModifiedBy() {
        return this.modifiedBy;
    }

    public TSectorCode modifiedBy(Integer modifiedBy) {
        this.setModifiedBy(modifiedBy);
        return this;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public ZonedDateTime getModifiedDate() {
        return this.modifiedDate;
    }

    public TSectorCode modifiedDate(ZonedDateTime modifiedDate) {
        this.setModifiedDate(modifiedDate);
        return this;
    }

    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TSectorCode)) {
            return false;
        }
        return id != null && id.equals(((TSectorCode) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TSectorCode{" +
            "id=" + getId() +
            ", sectorName='" + getSectorName() + "'" +
            ", sectorDescription='" + getSectorDescription() + "'" +
            ", enteredBy=" + getEnteredBy() +
            ", enteredDate='" + getEnteredDate() + "'" +
            ", modifiedBy=" + getModifiedBy() +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
