package com.jhipster.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TSubunitCode.
 */
@Entity
@Table(name = "t_subunit_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TSubunitCode implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Size(max = 100)
	@Column(name = "subunt_subunit", length = 100)
	private String subuntSubunit;

	@Column(name = "subunt_headid")
	private Integer subuntHeadid;

	@Column(name = "subunt_acting_headid")
	private Integer subuntActingHeadid;

	@Column(name = "subunt_hrpay_id")
	private String subuntHrpayId;

	@Size(max = 10)
	@Column(name = "subunt_pap_id", length = 10)
	private String subuntPapId;

	@Size(max = 10)
	@Column(name = "subunt_pap_code", length = 10)
	private String subuntPapCode;

	@Size(max = 1)
	@Column(name = "subunt_pap_active", length = 1)
	private String subuntPapActive;

	@Size(max = 10)
	@Column(name = "subuny_unitid", length = 10)
	private String subunyUnitid;

	@ManyToOne
	@JsonIgnoreProperties(value = { "subunits" }, allowSetters = true)
	private TUnitCode unit;

	// jhipster-needle-entity-add-field - JHipster will add fields here

	public Long getId() {
		return this.id;
	}

	public TSubunitCode id(Long id) {
		this.setId(id);
		return this;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubuntSubunit() {
		return this.subuntSubunit;
	}

	public TSubunitCode subuntSubunit(String subuntSubunit) {
		this.setSubuntSubunit(subuntSubunit);
		return this;
	}

	public void setSubuntSubunit(String subuntSubunit) {
		this.subuntSubunit = subuntSubunit;
	}

	public Integer getSubuntHeadid() {
		return this.subuntHeadid;
	}

	public TSubunitCode subuntHeadid(Integer subuntHeadid) {
		this.setSubuntHeadid(subuntHeadid);
		return this;
	}

	public void setSubuntHeadid(Integer subuntHeadid) {
		this.subuntHeadid = subuntHeadid;
	}

	public Integer getSubuntActingHeadid() {
		return this.subuntActingHeadid;
	}

	public TSubunitCode subuntActingHeadid(Integer subuntActingHeadid) {
		this.setSubuntActingHeadid(subuntActingHeadid);
		return this;
	}

	public void setSubuntActingHeadid(Integer subuntActingHeadid) {
		this.subuntActingHeadid = subuntActingHeadid;
	}

	public String getSubuntHrpayId() {
		return this.subuntHrpayId;
	}

	public TSubunitCode subuntHrpayId(String subuntHrpayId) {
		this.setSubuntHrpayId(subuntHrpayId);
		return this;
	}

	public void setSubuntHrpayId(String subuntHrpayId) {
		this.subuntHrpayId = subuntHrpayId;
	}

	public String getSubuntPapId() {
		return this.subuntPapId;
	}

	public TSubunitCode subuntPapId(String subuntPapId) {
		this.setSubuntPapId(subuntPapId);
		return this;
	}

	public void setSubuntPapId(String subuntPapId) {
		this.subuntPapId = subuntPapId;
	}

	public String getSubuntPapCode() {
		return this.subuntPapCode;
	}

	public TSubunitCode subuntPapCode(String subuntPapCode) {
		this.setSubuntPapCode(subuntPapCode);
		return this;
	}

	public void setSubuntPapCode(String subuntPapCode) {
		this.subuntPapCode = subuntPapCode;
	}

	public String getSubuntPapActive() {
		return this.subuntPapActive;
	}

	public TSubunitCode subuntPapActive(String subuntPapActive) {
		this.setSubuntPapActive(subuntPapActive);
		return this;
	}

	public void setSubuntPapActive(String subuntPapActive) {
		this.subuntPapActive = subuntPapActive;
	}

	public String getSubunyUnitid() {
		return this.subunyUnitid;
	}

	public TSubunitCode subunyUnitid(String subunyUnitid) {
		this.setSubunyUnitid(subunyUnitid);
		return this;
	}

	public void setSubunyUnitid(String subunyUnitid) {
		this.subunyUnitid = subunyUnitid;
	}

	public TUnitCode getUnit() {
		return this.unit;
	}

	public void setUnit(TUnitCode tUnitCode) {
		this.unit = tUnitCode;
	}

	public TSubunitCode unit(TUnitCode tUnitCode) {
		this.setUnit(tUnitCode);
		return this;
	}

	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof TSubunitCode)) {
			return false;
		}
		return id != null && id.equals(((TSubunitCode) o).id);
	}

	@Override
	public int hashCode() {
		// see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
		return getClass().hashCode();
	}

	// prettier-ignore
    @Override
    public String toString() {
        return "TSubunitCode{" +
            "id=" + getId() +
            ", subuntSubunit='" + getSubuntSubunit() + "'" +
            ", subuntHeadid=" + getSubuntHeadid() +
            ", subuntActingHeadid=" + getSubuntActingHeadid() +
            ", subuntHrpayId='" + getSubuntHrpayId() + "'" +
            ", subuntPapId='" + getSubuntPapId() + "'" +
            ", subuntPapCode='" + getSubuntPapCode() + "'" +
            ", subuntPapActive='" + getSubuntPapActive() + "'" +
            ", subunyUnitid='" + getSubunyUnitid() + "'" +
            "}";
    }
}
