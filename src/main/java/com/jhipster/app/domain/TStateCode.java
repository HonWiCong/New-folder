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
 * A TStateCode.
 */
@Entity
@Table(name = "t_state_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TStateCode implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "state_name")
	private String stateName;

	@Column(name = "state_code")
	private String stateCode;

	@Column(name = "entered_by")
	private Integer enteredBy;

	@Column(name = "entered_date")
	private ZonedDateTime enteredDate;

	@Column(name = "modified_by")
	private Integer modifiedBy;

	@Column(name = "modified_date")
	private ZonedDateTime modifiedDate;

	@ManyToOne
	@JsonIgnoreProperties(value = { "tStateCodes" }, allowSetters = true)
	private TCountryCode tCountryCode;

	@OneToMany(mappedBy = "tStateCode")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JsonIgnoreProperties(value = { "tStateCode" }, allowSetters = true)
	private Set<TCityCode> tCityCodes = new HashSet<>();

	// jhipster-needle-entity-add-field - JHipster will add fields here

	public Long getId() {
		return this.id;
	}

	public TStateCode id(Long id) {
		this.setId(id);
		return this;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStateName() {
		return this.stateName;
	}

	public TStateCode stateName(String stateName) {
		this.setStateName(stateName);
		return this;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateCode() {
		return this.stateCode;
	}

	public TStateCode stateCode(String stateCode) {
		this.setStateCode(stateCode);
		return this;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getEnteredBy() {
		return this.enteredBy;
	}

	public TStateCode enteredBy(Integer enteredBy) {
		this.setEnteredBy(enteredBy);
		return this;
	}

	public void setEnteredBy(Integer enteredBy) {
		this.enteredBy = enteredBy;
	}

	public ZonedDateTime getEnteredDate() {
		return this.enteredDate;
	}

	public TStateCode enteredDate(ZonedDateTime enteredDate) {
		this.setEnteredDate(enteredDate);
		return this;
	}

	public void setEnteredDate(ZonedDateTime enteredDate) {
		this.enteredDate = enteredDate;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public TStateCode modifiedBy(Integer modifiedBy) {
		this.setModifiedBy(modifiedBy);
		return this;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public ZonedDateTime getModifiedDate() {
		return this.modifiedDate;
	}

	public TStateCode modifiedDate(ZonedDateTime modifiedDate) {
		this.setModifiedDate(modifiedDate);
		return this;
	}

	public void setModifiedDate(ZonedDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public TCountryCode getTCountryCode() {
		return this.tCountryCode;
	}

	public void setTCountryCode(TCountryCode tCountryCode) {
		this.tCountryCode = tCountryCode;
	}

	public TStateCode tCountryCode(TCountryCode tCountryCode) {
		this.setTCountryCode(tCountryCode);
		return this;
	}

	public Set<TCityCode> getTCityCodes() {
		return this.tCityCodes;
	}

	public void setTCityCodes(Set<TCityCode> tCityCodes) {
		if (this.tCityCodes != null) {
			this.tCityCodes.forEach(i -> i.setTStateCode(null));
		}
		if (tCityCodes != null) {
			tCityCodes.forEach(i -> i.setTStateCode(this));
		}
		this.tCityCodes = tCityCodes;
	}

	public TStateCode tCityCodes(Set<TCityCode> tCityCodes) {
		this.setTCityCodes(tCityCodes);
		return this;
	}

	public TStateCode addTCityCode(TCityCode tCityCode) {
		this.tCityCodes.add(tCityCode);
		tCityCode.setTStateCode(this);
		return this;
	}

	public TStateCode removeTCityCode(TCityCode tCityCode) {
		this.tCityCodes.remove(tCityCode);
		tCityCode.setTStateCode(null);
		return this;
	}

	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof TStateCode)) {
			return false;
		}
		return id != null && id.equals(((TStateCode) o).id);
	}

	@Override
	public int hashCode() {
		// see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
		return getClass().hashCode();
	}

	// prettier-ignore
    @Override
    public String toString() {
        return "TStateCode{" +
            "id=" + getId() +
            ", stateName='" + getStateName() + "'" +
            ", stateCode='" + getStateCode() + "'" +
            ", enteredBy=" + getEnteredBy() +
            ", enteredDate='" + getEnteredDate() + "'" +
            ", modifiedBy=" + getModifiedBy() +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
