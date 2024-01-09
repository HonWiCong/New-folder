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
 * A TDivisionCode.
 */
@Entity
@Table(name = "t_division_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TDivisionCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "div_name")
    private String divName;

    @Column(name = "entered_by")
    private Integer enteredBy;

    @Column(name = "entered_date")
    private ZonedDateTime enteredDate;

    @Column(name = "modify_by")
    private Integer modifyBy;

    @Column(name = "modified_date")
    private ZonedDateTime modifiedDate;

    @OneToMany(mappedBy = "tDivisionCode")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "tDivisionCode" }, allowSetters = true)
    private Set<TCityCode> tCityCodes = new HashSet<>();

    @OneToMany(mappedBy = "tDivisionCode")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "tDivisionCode" }, allowSetters = true)
    private Set<TDistrictCode> tDistrictCodes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TDivisionCode id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDivName() {
        return this.divName;
    }

    public TDivisionCode divName(String divName) {
        this.setDivName(divName);
        return this;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public Integer getEnteredBy() {
        return this.enteredBy;
    }

    public TDivisionCode enteredBy(Integer enteredBy) {
        this.setEnteredBy(enteredBy);
        return this;
    }

    public void setEnteredBy(Integer enteredBy) {
        this.enteredBy = enteredBy;
    }

    public ZonedDateTime getEnteredDate() {
        return this.enteredDate;
    }

    public TDivisionCode enteredDate(ZonedDateTime enteredDate) {
        this.setEnteredDate(enteredDate);
        return this;
    }

    public void setEnteredDate(ZonedDateTime enteredDate) {
        this.enteredDate = enteredDate;
    }

    public Integer getModifyBy() {
        return this.modifyBy;
    }

    public TDivisionCode modifyBy(Integer modifyBy) {
        this.setModifyBy(modifyBy);
        return this;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public ZonedDateTime getModifiedDate() {
        return this.modifiedDate;
    }

    public TDivisionCode modifiedDate(ZonedDateTime modifiedDate) {
        this.setModifiedDate(modifiedDate);
        return this;
    }

    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<TCityCode> getTCityCodes() {
        return this.tCityCodes;
    }

    public void setTCityCodes(Set<TCityCode> tCityCodes) {
        if (this.tCityCodes != null) {
            this.tCityCodes.forEach(i -> i.setTDivisionCode(null));
        }
        if (tCityCodes != null) {
            tCityCodes.forEach(i -> i.setTDivisionCode(this));
        }
        this.tCityCodes = tCityCodes;
    }

    public TDivisionCode tCityCodes(Set<TCityCode> tCityCodes) {
        this.setTCityCodes(tCityCodes);
        return this;
    }

    public TDivisionCode addTCityCode(TCityCode tCityCode) {
        this.tCityCodes.add(tCityCode);
        tCityCode.setTDivisionCode(this);
        return this;
    }

    public TDivisionCode removeTCityCode(TCityCode tCityCode) {
        this.tCityCodes.remove(tCityCode);
        tCityCode.setTDivisionCode(null);
        return this;
    }

    public Set<TDistrictCode> getTDistrictCodes() {
        return this.tDistrictCodes;
    }

    public void setTDistrictCodes(Set<TDistrictCode> tDistrictCodes) {
        if (this.tDistrictCodes != null) {
            this.tDistrictCodes.forEach(i -> i.setTDivisionCode(null));
        }
        if (tDistrictCodes != null) {
            tDistrictCodes.forEach(i -> i.setTDivisionCode(this));
        }
        this.tDistrictCodes = tDistrictCodes;
    }

    public TDivisionCode tDistrictCodes(Set<TDistrictCode> tDistrictCodes) {
        this.setTDistrictCodes(tDistrictCodes);
        return this;
    }

    public TDivisionCode addTDistrictCode(TDistrictCode tDistrictCode) {
        this.tDistrictCodes.add(tDistrictCode);
        tDistrictCode.setTDivisionCode(this);
        return this;
    }

    public TDivisionCode removeTDistrictCode(TDistrictCode tDistrictCode) {
        this.tDistrictCodes.remove(tDistrictCode);
        tDistrictCode.setTDivisionCode(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TDivisionCode)) {
            return false;
        }
        return id != null && id.equals(((TDivisionCode) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TDivisionCode{" +
            "id=" + getId() +
            ", divName='" + getDivName() + "'" +
            ", enteredBy=" + getEnteredBy() +
            ", enteredDate='" + getEnteredDate() + "'" +
            ", modifyBy=" + getModifyBy() +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
