package com.jhipster.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCityCode.
 */
@Entity
@Table(name = "t_city_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TCityCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "entered_by")
    private Integer enteredBy;

    @Column(name = "entered_date")
    private ZonedDateTime enteredDate;

    @Column(name = "modify_by")
    private Integer modifyBy;

    @Column(name = "modified_date")
    private ZonedDateTime modifiedDate;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tCityCodes", "tDistrictCodes" }, allowSetters = true)
    private TDivisionCode tDivisionCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TCityCode id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public TCityCode cityCode(String cityCode) {
        this.setCityCode(cityCode);
        return this;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return this.cityName;
    }

    public TCityCode cityName(String cityName) {
        this.setCityName(cityName);
        return this;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getEnteredBy() {
        return this.enteredBy;
    }

    public TCityCode enteredBy(Integer enteredBy) {
        this.setEnteredBy(enteredBy);
        return this;
    }

    public void setEnteredBy(Integer enteredBy) {
        this.enteredBy = enteredBy;
    }

    public ZonedDateTime getEnteredDate() {
        return this.enteredDate;
    }

    public TCityCode enteredDate(ZonedDateTime enteredDate) {
        this.setEnteredDate(enteredDate);
        return this;
    }

    public void setEnteredDate(ZonedDateTime enteredDate) {
        this.enteredDate = enteredDate;
    }

    public Integer getModifyBy() {
        return this.modifyBy;
    }

    public TCityCode modifyBy(Integer modifyBy) {
        this.setModifyBy(modifyBy);
        return this;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public ZonedDateTime getModifiedDate() {
        return this.modifiedDate;
    }

    public TCityCode modifiedDate(ZonedDateTime modifiedDate) {
        this.setModifiedDate(modifiedDate);
        return this;
    }

    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public TDivisionCode getTDivisionCode() {
        return this.tDivisionCode;
    }

    public void setTDivisionCode(TDivisionCode tDivisionCode) {
        this.tDivisionCode = tDivisionCode;
    }

    public TCityCode tDivisionCode(TDivisionCode tDivisionCode) {
        this.setTDivisionCode(tDivisionCode);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCityCode)) {
            return false;
        }
        return id != null && id.equals(((TCityCode) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCityCode{" +
            "id=" + getId() +
            ", cityCode='" + getCityCode() + "'" +
            ", cityName='" + getCityName() + "'" +
            ", enteredBy=" + getEnteredBy() +
            ", enteredDate='" + getEnteredDate() + "'" +
            ", modifyBy=" + getModifyBy() +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
