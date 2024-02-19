package com.jhipster.app.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TOfficeCode.
 */
@Entity
@Table(name = "t_office_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TOfficeCode implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "off_name")
	private String offName;

	@Column(name = "off_address")
	private String offAddress;

	@Column(name = "off_postcode")
	private String offPostcode;

	@Column(name = "off_city")
	private String offCity;

	@Column(name = "off_state")
	private String offState;

	@Column(name = "off_offphone")
	private String offOffphone;

	@Column(name = "off_offfax")
	private String offOfffax;

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

	public TOfficeCode id(Long id) {
		this.setId(id);
		return this;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOffName() {
		return this.offName;
	}

	public TOfficeCode offName(String offName) {
		this.setOffName(offName);
		return this;
	}

	public void setOffName(String offName) {
		this.offName = offName;
	}

	public String getOffAddress() {
		return this.offAddress;
	}

	public TOfficeCode offAddress(String offAddress) {
		this.setOffAddress(offAddress);
		return this;
	}

	public void setOffAddress(String offAddress) {
		this.offAddress = offAddress;
	}

	public String getOffPostcode() {
		return this.offPostcode;
	}

	public TOfficeCode offPostcode(String offPostcode) {
		this.setOffPostcode(offPostcode);
		return this;
	}

	public void setOffPostcode(String offPostcode) {
		this.offPostcode = offPostcode;
	}

	public String getOffCity() {
		return this.offCity;
	}

	public TOfficeCode offCity(String offCity) {
		this.setOffCity(offCity);
		return this;
	}

	public void setOffCity(String offCity) {
		this.offCity = offCity;
	}

	public String getOffState() {
		return this.offState;
	}

	public TOfficeCode offState(String offState) {
		this.setOffState(offState);
		return this;
	}

	public void setOffState(String offState) {
		this.offState = offState;
	}

	public String getOffOffphone() {
		return this.offOffphone;
	}

	public TOfficeCode offOffphone(String offOffphone) {
		this.setOffOffphone(offOffphone);
		return this;
	}

	public void setOffOffphone(String offOffphone) {
		this.offOffphone = offOffphone;
	}

	public String getOffOfffax() {
		return this.offOfffax;
	}

	public TOfficeCode offOfffax(String offOfffax) {
		this.setOffOfffax(offOfffax);
		return this;
	}

	public void setOffOfffax(String offOfffax) {
		this.offOfffax = offOfffax;
	}

	public Integer getEnteredBy() {
		return this.enteredBy;
	}

	public TOfficeCode enteredBy(Integer enteredBy) {
		this.setEnteredBy(enteredBy);
		return this;
	}

	public void setEnteredBy(Integer enteredBy) {
		this.enteredBy = enteredBy;
	}

	public ZonedDateTime getEnteredDate() {
		return this.enteredDate;
	}

	public TOfficeCode enteredDate(ZonedDateTime enteredDate) {
		this.setEnteredDate(enteredDate);
		return this;
	}

	public void setEnteredDate(ZonedDateTime enteredDate) {
		this.enteredDate = enteredDate;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public TOfficeCode modifiedBy(Integer modifiedBy) {
		this.setModifiedBy(modifiedBy);
		return this;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public ZonedDateTime getModifiedDate() {
		return this.modifiedDate;
	}

	public TOfficeCode modifiedDate(ZonedDateTime modifiedDate) {
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
		if (!(o instanceof TOfficeCode)) {
			return false;
		}
		return id != null && id.equals(((TOfficeCode) o).id);
	}

	@Override
	public int hashCode() {
		// see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
		return getClass().hashCode();
	}

	// prettier-ignore
    @Override
    public String toString() {
        return "TOfficeCode{" +
            "id=" + getId() +
            ", offName='" + getOffName() + "'" +
            ", offAddress='" + getOffAddress() + "'" +
            ", offPostcode='" + getOffPostcode() + "'" +
            ", offCity='" + getOffCity() + "'" +
            ", offState='" + getOffState() + "'" +
            ", offOffphone='" + getOffOffphone() + "'" +
            ", offOfffax='" + getOffOfffax() + "'" +
            ", enteredBy=" + getEnteredBy() +
            ", enteredDate='" + getEnteredDate() + "'" +
            ", modifiedBy=" + getModifiedBy() +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
