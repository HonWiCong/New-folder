package com.jhipster.app.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TIndustryCode.
 */
@Entity
@Table(name = "t_industry_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TIndustryCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "industry_name")
    private String industryName;

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

    public TIndustryCode id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndustryName() {
        return this.industryName;
    }

    public TIndustryCode industryName(String industryName) {
        this.setIndustryName(industryName);
        return this;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public Integer getEnteredBy() {
        return this.enteredBy;
    }

    public TIndustryCode enteredBy(Integer enteredBy) {
        this.setEnteredBy(enteredBy);
        return this;
    }

    public void setEnteredBy(Integer enteredBy) {
        this.enteredBy = enteredBy;
    }

    public ZonedDateTime getEnteredDate() {
        return this.enteredDate;
    }

    public TIndustryCode enteredDate(ZonedDateTime enteredDate) {
        this.setEnteredDate(enteredDate);
        return this;
    }

    public void setEnteredDate(ZonedDateTime enteredDate) {
        this.enteredDate = enteredDate;
    }

    public Integer getModifiedBy() {
        return this.modifiedBy;
    }

    public TIndustryCode modifiedBy(Integer modifiedBy) {
        this.setModifiedBy(modifiedBy);
        return this;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public ZonedDateTime getModifiedDate() {
        return this.modifiedDate;
    }

    public TIndustryCode modifiedDate(ZonedDateTime modifiedDate) {
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
        if (!(o instanceof TIndustryCode)) {
            return false;
        }
        return id != null && id.equals(((TIndustryCode) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TIndustryCode{" +
            "id=" + getId() +
            ", industryName='" + getIndustryName() + "'" +
            ", enteredBy=" + getEnteredBy() +
            ", enteredDate='" + getEnteredDate() + "'" +
            ", modifiedBy=" + getModifiedBy() +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
