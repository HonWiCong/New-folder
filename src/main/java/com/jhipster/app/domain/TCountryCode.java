package com.jhipster.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCountryCode.
 */
@Entity
@Table(name = "t_country_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TCountryCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "country_nationality")
    private String countryNationality;

    @Column(name = "entered_by")
    private Integer enteredBy;

    @Column(name = "entered_date")
    private ZonedDateTime enteredDate;

    @Column(name = "modify_by")
    private Integer modifyBy;

    @Column(name = "modified_date")
    private ZonedDateTime modifiedDate;

    @Column(name = "org_customer_type")
    private String orgCustomerType;

    @OneToMany(mappedBy = "tCountryCode")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "tCountryCode" }, allowSetters = true)
    private Set<TStateCode> tStateCodes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TCountryCode id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public TCountryCode countryCode(String countryCode) {
        this.setCountryCode(countryCode);
        return this;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public TCountryCode countryName(String countryName) {
        this.setCountryName(countryName);
        return this;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryNationality() {
        return this.countryNationality;
    }

    public TCountryCode countryNationality(String countryNationality) {
        this.setCountryNationality(countryNationality);
        return this;
    }

    public void setCountryNationality(String countryNationality) {
        this.countryNationality = countryNationality;
    }

    public Integer getEnteredBy() {
        return this.enteredBy;
    }

    public TCountryCode enteredBy(Integer enteredBy) {
        this.setEnteredBy(enteredBy);
        return this;
    }

    public void setEnteredBy(Integer enteredBy) {
        this.enteredBy = enteredBy;
    }

    public ZonedDateTime getEnteredDate() {
        return this.enteredDate;
    }

    public TCountryCode enteredDate(ZonedDateTime enteredDate) {
        this.setEnteredDate(enteredDate);
        return this;
    }

    public void setEnteredDate(ZonedDateTime enteredDate) {
        this.enteredDate = enteredDate;
    }

    public Integer getModifyBy() {
        return this.modifyBy;
    }

    public TCountryCode modifyBy(Integer modifyBy) {
        this.setModifyBy(modifyBy);
        return this;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public ZonedDateTime getModifiedDate() {
        return this.modifiedDate;
    }

    public TCountryCode modifiedDate(ZonedDateTime modifiedDate) {
        this.setModifiedDate(modifiedDate);
        return this;
    }

    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getOrgCustomerType() {
        return this.orgCustomerType;
    }

    public TCountryCode orgCustomerType(String orgCustomerType) {
        this.setOrgCustomerType(orgCustomerType);
        return this;
    }

    public void setOrgCustomerType(String orgCustomerType) {
        this.orgCustomerType = orgCustomerType;
    }

    public Set<TStateCode> getTStateCodes() {
        return this.tStateCodes;
    }

    public void setTStateCodes(Set<TStateCode> tStateCodes) {
        if (this.tStateCodes != null) {
            this.tStateCodes.forEach(i -> i.setTCountryCode(null));
        }
        if (tStateCodes != null) {
            tStateCodes.forEach(i -> i.setTCountryCode(this));
        }
        this.tStateCodes = tStateCodes;
    }

    public TCountryCode tStateCodes(Set<TStateCode> tStateCodes) {
        this.setTStateCodes(tStateCodes);
        return this;
    }

    public TCountryCode addTStateCode(TStateCode tStateCode) {
        this.tStateCodes.add(tStateCode);
        tStateCode.setTCountryCode(this);
        return this;
    }

    public TCountryCode removeTStateCode(TStateCode tStateCode) {
        this.tStateCodes.remove(tStateCode);
        tStateCode.setTCountryCode(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCountryCode)) {
            return false;
        }
        return id != null && id.equals(((TCountryCode) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCountryCode{" +
            "id=" + getId() +
            ", countryCode='" + getCountryCode() + "'" +
            ", countryName='" + getCountryName() + "'" +
            ", countryNationality='" + getCountryNationality() + "'" +
            ", enteredBy=" + getEnteredBy() +
            ", enteredDate='" + getEnteredDate() + "'" +
            ", modifyBy=" + getModifyBy() +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", orgCustomerType='" + getOrgCustomerType() + "'" +
            "}";
    }
}
