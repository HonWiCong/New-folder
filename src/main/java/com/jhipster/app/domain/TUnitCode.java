package com.jhipster.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TUnitCode.
 */
@Entity
@Table(name = "t_unit_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TUnitCode implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Size(max = 100)
	@Column(name = "unt_unit", length = 100)
	private String untUnit;

	@Column(name = "unt_headid")
	private Integer untHeadid;

	@Column(name = "unt_acting_headid")
	private Integer untActingHeadid;

	@Column(name = "unt_hrpay_id")
	private String untHrpayId;

	@Size(max = 10)
	@Column(name = "unt_pap_id", length = 10)
	private String untPapId;

	@Size(max = 10)
	@Column(name = "unt_pap_code", length = 10)
	private String untPapCode;

	@Size(max = 1)
	@Column(name = "unt_pap_active", length = 1)
	private String untPapActive;

	@Column(name = "unt_pap_departmentid")
	private String untPapDepartmentid;

	@Column(name = "entered_by")
	private Integer enteredBy;

	@Column(name = "entered_date")
	private ZonedDateTime enteredDate;

	@Column(name = "modified_by")
	private Integer modifiedBy;

	@Column(name = "modified_date")
	private ZonedDateTime modifiedDate;

	@OneToMany(mappedBy = "unit")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JsonIgnoreProperties(value = { "unit" }, allowSetters = true)
	private Set<TSubunitCode> subunits = new HashSet<>();

	// jhipster-needle-entity-add-field - JHipster will add fields here

	public Long getId() {
		return this.id;
	}

	public TUnitCode id(Long id) {
		this.setId(id);
		return this;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUntUnit() {
		return this.untUnit;
	}

	public TUnitCode untUnit(String untUnit) {
		this.setUntUnit(untUnit);
		return this;
	}

	public void setUntUnit(String untUnit) {
		this.untUnit = untUnit;
	}

	public Integer getUntHeadid() {
		return this.untHeadid;
	}

	public TUnitCode untHeadid(Integer untHeadid) {
		this.setUntHeadid(untHeadid);
		return this;
	}

	public void setUntHeadid(Integer untHeadid) {
		this.untHeadid = untHeadid;
	}

	public Integer getUntActingHeadid() {
		return this.untActingHeadid;
	}

	public TUnitCode untActingHeadid(Integer untActingHeadid) {
		this.setUntActingHeadid(untActingHeadid);
		return this;
	}

	public void setUntActingHeadid(Integer untActingHeadid) {
		this.untActingHeadid = untActingHeadid;
	}

	public String getUntHrpayId() {
		return this.untHrpayId;
	}

	public TUnitCode untHrpayId(String untHrpayId) {
		this.setUntHrpayId(untHrpayId);
		return this;
	}

	public void setUntHrpayId(String untHrpayId) {
		this.untHrpayId = untHrpayId;
	}

	public String getUntPapId() {
		return this.untPapId;
	}

	public TUnitCode untPapId(String untPapId) {
		this.setUntPapId(untPapId);
		return this;
	}

	public void setUntPapId(String untPapId) {
		this.untPapId = untPapId;
	}

	public String getUntPapCode() {
		return this.untPapCode;
	}

	public TUnitCode untPapCode(String untPapCode) {
		this.setUntPapCode(untPapCode);
		return this;
	}

	public void setUntPapCode(String untPapCode) {
		this.untPapCode = untPapCode;
	}

	public String getUntPapActive() {
		return this.untPapActive;
	}

	public TUnitCode untPapActive(String untPapActive) {
		this.setUntPapActive(untPapActive);
		return this;
	}

	public void setUntPapActive(String untPapActive) {
		this.untPapActive = untPapActive;
	}

	public String getUntPapDepartmentid() {
		return this.untPapDepartmentid;
	}

	public TUnitCode untPapDepartmentid(String untPapDepartmentid) {
		this.setUntPapDepartmentid(untPapDepartmentid);
		return this;
	}

	public void setUntPapDepartmentid(String untPapDepartmentid) {
		this.untPapDepartmentid = untPapDepartmentid;
	}

	public Integer getEnteredBy() {
		return this.enteredBy;
	}

	public TUnitCode enteredBy(Integer enteredBy) {
		this.setEnteredBy(enteredBy);
		return this;
	}

	public void setEnteredBy(Integer enteredBy) {
		this.enteredBy = enteredBy;
	}

	public ZonedDateTime getEnteredDate() {
		return this.enteredDate;
	}

	public TUnitCode enteredDate(ZonedDateTime enteredDate) {
		this.setEnteredDate(enteredDate);
		return this;
	}

	public void setEnteredDate(ZonedDateTime enteredDate) {
		this.enteredDate = enteredDate;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public TUnitCode modifiedBy(Integer modifiedBy) {
		this.setModifiedBy(modifiedBy);
		return this;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public ZonedDateTime getModifiedDate() {
		return this.modifiedDate;
	}

	public TUnitCode modifiedDate(ZonedDateTime modifiedDate) {
		this.setModifiedDate(modifiedDate);
		return this;
	}

	public void setModifiedDate(ZonedDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Set<TSubunitCode> getSubunits() {
		return this.subunits;
	}

	public void setSubunits(Set<TSubunitCode> tSubunitCodes) {
		if (this.subunits != null) {
			this.subunits.forEach(i -> i.setUnit(null));
		}
		if (tSubunitCodes != null) {
			tSubunitCodes.forEach(i -> i.setUnit(this));
		}
		this.subunits = tSubunitCodes;
	}

	public TUnitCode subunits(Set<TSubunitCode> tSubunitCodes) {
		this.setSubunits(tSubunitCodes);
		return this;
	}

	public TUnitCode addSubunit(TSubunitCode tSubunitCode) {
		this.subunits.add(tSubunitCode);
		tSubunitCode.setUnit(this);
		return this;
	}

	public TUnitCode removeSubunit(TSubunitCode tSubunitCode) {
		this.subunits.remove(tSubunitCode);
		tSubunitCode.setUnit(null);
		return this;
	}

	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof TUnitCode)) {
			return false;
		}
		return id != null && id.equals(((TUnitCode) o).id);
	}

	@Override
	public int hashCode() {
		// see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
		return getClass().hashCode();
	}

	// prettier-ignore
    @Override
    public String toString() {
        return "TUnitCode{" +
            "id=" + getId() +
            ", untUnit='" + getUntUnit() + "'" +
            ", untHeadid=" + getUntHeadid() +
            ", untActingHeadid=" + getUntActingHeadid() +
            ", untHrpayId='" + getUntHrpayId() + "'" +
            ", untPapId='" + getUntPapId() + "'" +
            ", untPapCode='" + getUntPapCode() + "'" +
            ", untPapActive='" + getUntPapActive() + "'" +
            ", untPapDepartmentid='" + getUntPapDepartmentid() + "'" +
            ", enteredBy=" + getEnteredBy() +
            ", enteredDate='" + getEnteredDate() + "'" +
            ", modifiedBy=" + getModifiedBy() +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
