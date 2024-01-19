package com.jhipster.app.domain;

import com.jhipster.app.domain.enumeration.SectPapActive;
import com.jhipster.app.domain.enumeration.SectSainsSubsidiary;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TSectionCode.
 */
@Entity
@Table(name = "t_section_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TSectionCode implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "sect_name")
	private String sectName;

	@Column(name = "sect_headid")
	private Integer sectHeadid;

	@Column(name = "sect_acting_headid")
	private Integer sectActingHeadid;

	@Enumerated(EnumType.STRING)
	@Column(name = "sect_sains_subsidiary")
	private SectSainsSubsidiary sectSainsSubsidiary;

	@Column(name = "sect_hrpay_id")
	private String sectHrpayId;

	@Column(name = "sect_pap_id")
	private String sectPapId;

	@Column(name = "sect_pap_code")
	private String sectPapCode;

	@Column(name = "sect_pap_company")
	private String sectPapCompany;

	@Enumerated(EnumType.STRING)
	@Column(name = "sect_pap_active")
	private SectPapActive sectPapActive;

	@Column(name = "sect_cc_code")
	private String sectCcCode;

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

	public TSectionCode id(Long id) {
		this.setId(id);
		return this;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSectName() {
		return this.sectName;
	}

	public TSectionCode sectName(String sectName) {
		this.setSectName(sectName);
		return this;
	}

	public void setSectName(String sectName) {
		this.sectName = sectName;
	}

	public Integer getSectHeadid() {
		return this.sectHeadid;
	}

	public TSectionCode sectHeadid(Integer sectHeadid) {
		this.setSectHeadid(sectHeadid);
		return this;
	}

	public void setSectHeadid(Integer sectHeadid) {
		this.sectHeadid = sectHeadid;
	}

	public Integer getSectActingHeadid() {
		return this.sectActingHeadid;
	}

	public TSectionCode sectActingHeadid(Integer sectActingHeadid) {
		this.setSectActingHeadid(sectActingHeadid);
		return this;
	}

	public void setSectActingHeadid(Integer sectActingHeadid) {
		this.sectActingHeadid = sectActingHeadid;
	}

	public SectSainsSubsidiary getSectSainsSubsidiary() {
		return this.sectSainsSubsidiary;
	}

	public TSectionCode sectSainsSubsidiary(SectSainsSubsidiary sectSainsSubsidiary) {
		this.setSectSainsSubsidiary(sectSainsSubsidiary);
		return this;
	}

	public void setSectSainsSubsidiary(SectSainsSubsidiary sectSainsSubsidiary) {
		this.sectSainsSubsidiary = sectSainsSubsidiary;
	}

	public String getSectHrpayId() {
		return this.sectHrpayId;
	}

	public TSectionCode sectHrpayId(String sectHrpayId) {
		this.setSectHrpayId(sectHrpayId);
		return this;
	}

	public void setSectHrpayId(String sectHrpayId) {
		this.sectHrpayId = sectHrpayId;
	}

	public String getSectPapId() {
		return this.sectPapId;
	}

	public TSectionCode sectPapId(String sectPapId) {
		this.setSectPapId(sectPapId);
		return this;
	}

	public void setSectPapId(String sectPapId) {
		this.sectPapId = sectPapId;
	}

	public String getSectPapCode() {
		return this.sectPapCode;
	}

	public TSectionCode sectPapCode(String sectPapCode) {
		this.setSectPapCode(sectPapCode);
		return this;
	}

	public void setSectPapCode(String sectPapCode) {
		this.sectPapCode = sectPapCode;
	}

	public String getSectPapCompany() {
		return this.sectPapCompany;
	}

	public TSectionCode sectPapCompany(String sectPapCompany) {
		this.setSectPapCompany(sectPapCompany);
		return this;
	}

	public void setSectPapCompany(String sectPapCompany) {
		this.sectPapCompany = sectPapCompany;
	}

	public SectPapActive getSectPapActive() {
		return this.sectPapActive;
	}

	public TSectionCode sectPapActive(SectPapActive sectPapActive) {
		this.setSectPapActive(sectPapActive);
		return this;
	}

	public void setSectPapActive(SectPapActive sectPapActive) {
		this.sectPapActive = sectPapActive;
	}

	public String getSectCcCode() {
		return this.sectCcCode;
	}

	public TSectionCode sectCcCode(String sectCcCode) {
		this.setSectCcCode(sectCcCode);
		return this;
	}

	public void setSectCcCode(String sectCcCode) {
		this.sectCcCode = sectCcCode;
	}

	public Integer getEnteredBy() {
		return this.enteredBy;
	}

	public TSectionCode enteredBy(Integer enteredBy) {
		this.setEnteredBy(enteredBy);
		return this;
	}

	public void setEnteredBy(Integer enteredBy) {
		this.enteredBy = enteredBy;
	}

	public ZonedDateTime getEnteredDate() {
		return this.enteredDate;
	}

	public TSectionCode enteredDate(ZonedDateTime enteredDate) {
		this.setEnteredDate(enteredDate);
		return this;
	}

	public void setEnteredDate(ZonedDateTime enteredDate) {
		this.enteredDate = enteredDate;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public TSectionCode modifiedBy(Integer modifiedBy) {
		this.setModifiedBy(modifiedBy);
		return this;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public ZonedDateTime getModifiedDate() {
		return this.modifiedDate;
	}

	public TSectionCode modifiedDate(ZonedDateTime modifiedDate) {
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
		if (!(o instanceof TSectionCode)) {
			return false;
		}
		return id != null && id.equals(((TSectionCode) o).id);
	}

	@Override
	public int hashCode() {
		// see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
		return getClass().hashCode();
	}

	// prettier-ignore
    @Override
    public String toString() {
        return "TSectionCode{" +
            "id=" + getId() +
            ", sectName='" + getSectName() + "'" +
            ", sectHeadid=" + getSectHeadid() +
            ", sectActingHeadid=" + getSectActingHeadid() +
            ", sectSainsSubsidiary='" + getSectSainsSubsidiary() + "'" +
            ", sectHrpayId='" + getSectHrpayId() + "'" +
            ", sectPapId='" + getSectPapId() + "'" +
            ", sectPapCode='" + getSectPapCode() + "'" +
            ", sectPapCompany='" + getSectPapCompany() + "'" +
            ", sectPapActive='" + getSectPapActive() + "'" +
            ", sectCcCode='" + getSectCcCode() + "'" +
            ", enteredBy=" + getEnteredBy() +
            ", enteredDate='" + getEnteredDate() + "'" +
            ", modifiedBy=" + getModifiedBy() +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
