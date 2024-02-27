package com.jhipster.app.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.jhipster.app.domain.TCountryCode} entity. This class is used
 * in {@link com.jhipster.app.web.rest.TCountryCodeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /t-country-codes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TCountryCodeCriteria implements Serializable, Criteria {

	private static final long serialVersionUID = 1L;

	private LongFilter id;

	private StringFilter countryCode;

	private StringFilter countryName;

	private StringFilter countryNationality;

	private IntegerFilter enteredBy;

	private ZonedDateTimeFilter enteredDate;

	private IntegerFilter modifiedBy;

	private ZonedDateTimeFilter modifiedDate;

	private StringFilter orgCustomerType;

	private LongFilter tStateCodeId;

	private Boolean distinct;

	public TCountryCodeCriteria() {}

	public TCountryCodeCriteria(TCountryCodeCriteria other) {
		this.id = other.id == null ? null : other.id.copy();
		this.countryCode = other.countryCode == null ? null : other.countryCode.copy();
		this.countryName = other.countryName == null ? null : other.countryName.copy();
		this.countryNationality = other.countryNationality == null ? null : other.countryNationality.copy();
		this.enteredBy = other.enteredBy == null ? null : other.enteredBy.copy();
		this.enteredDate = other.enteredDate == null ? null : other.enteredDate.copy();
		this.modifiedBy = other.modifiedBy == null ? null : other.modifiedBy.copy();
		this.modifiedDate = other.modifiedDate == null ? null : other.modifiedDate.copy();
		this.orgCustomerType = other.orgCustomerType == null ? null : other.orgCustomerType.copy();
		this.tStateCodeId = other.tStateCodeId == null ? null : other.tStateCodeId.copy();
		this.distinct = other.distinct;
	}

	@Override
	public TCountryCodeCriteria copy() {
		return new TCountryCodeCriteria(this);
	}

	public LongFilter getId() {
		return id;
	}

	public LongFilter id() {
		if (id == null) {
			id = new LongFilter();
		}
		return id;
	}

	public void setId(LongFilter id) {
		this.id = id;
	}

	public StringFilter getCountryCode() {
		return countryCode;
	}

	public StringFilter countryCode() {
		if (countryCode == null) {
			countryCode = new StringFilter();
		}
		return countryCode;
	}

	public void setCountryCode(StringFilter countryCode) {
		this.countryCode = countryCode;
	}

	public StringFilter getCountryName() {
		return countryName;
	}

	public StringFilter countryName() {
		if (countryName == null) {
			countryName = new StringFilter();
		}
		return countryName;
	}

	public void setCountryName(StringFilter countryName) {
		this.countryName = countryName;
	}

	public StringFilter getCountryNationality() {
		return countryNationality;
	}

	public StringFilter countryNationality() {
		if (countryNationality == null) {
			countryNationality = new StringFilter();
		}
		return countryNationality;
	}

	public void setCountryNationality(StringFilter countryNationality) {
		this.countryNationality = countryNationality;
	}

	public IntegerFilter getEnteredBy() {
		return enteredBy;
	}

	public IntegerFilter enteredBy() {
		if (enteredBy == null) {
			enteredBy = new IntegerFilter();
		}
		return enteredBy;
	}

	public void setEnteredBy(IntegerFilter enteredBy) {
		this.enteredBy = enteredBy;
	}

	public ZonedDateTimeFilter getEnteredDate() {
		return enteredDate;
	}

	public ZonedDateTimeFilter enteredDate() {
		if (enteredDate == null) {
			enteredDate = new ZonedDateTimeFilter();
		}
		return enteredDate;
	}

	public void setEnteredDate(ZonedDateTimeFilter enteredDate) {
		this.enteredDate = enteredDate;
	}

	public IntegerFilter getModifiedBy() {
		return modifiedBy;
	}

	public IntegerFilter modifiedBy() {
		if (modifiedBy == null) {
			modifiedBy = new IntegerFilter();
		}
		return modifiedBy;
	}

	public void setModifiedBy(IntegerFilter modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public ZonedDateTimeFilter getModifiedDate() {
		return modifiedDate;
	}

	public ZonedDateTimeFilter modifiedDate() {
		if (modifiedDate == null) {
			modifiedDate = new ZonedDateTimeFilter();
		}
		return modifiedDate;
	}

	public void setModifiedDate(ZonedDateTimeFilter modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public StringFilter getOrgCustomerType() {
		return orgCustomerType;
	}

	public StringFilter orgCustomerType() {
		if (orgCustomerType == null) {
			orgCustomerType = new StringFilter();
		}
		return orgCustomerType;
	}

	public void setOrgCustomerType(StringFilter orgCustomerType) {
		this.orgCustomerType = orgCustomerType;
	}

	public LongFilter getTStateCodeId() {
		return tStateCodeId;
	}

	public LongFilter tStateCodeId() {
		if (tStateCodeId == null) {
			tStateCodeId = new LongFilter();
		}
		return tStateCodeId;
	}

	public void setTStateCodeId(LongFilter tStateCodeId) {
		this.tStateCodeId = tStateCodeId;
	}

	public Boolean getDistinct() {
		return distinct;
	}

	public void setDistinct(Boolean distinct) {
		this.distinct = distinct;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final TCountryCodeCriteria that = (TCountryCodeCriteria) o;
		return (
			Objects.equals(id, that.id) &&
			Objects.equals(countryCode, that.countryCode) &&
			Objects.equals(countryName, that.countryName) &&
			Objects.equals(countryNationality, that.countryNationality) &&
			Objects.equals(enteredBy, that.enteredBy) &&
			Objects.equals(enteredDate, that.enteredDate) &&
			Objects.equals(modifiedBy, that.modifiedBy) &&
			Objects.equals(modifiedDate, that.modifiedDate) &&
			Objects.equals(orgCustomerType, that.orgCustomerType) &&
			Objects.equals(tStateCodeId, that.tStateCodeId) &&
			Objects.equals(distinct, that.distinct)
		);
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			id,
			countryCode,
			countryName,
			countryNationality,
			enteredBy,
			enteredDate,
			modifiedBy,
			modifiedDate,
			orgCustomerType,
			tStateCodeId,
			distinct
		);
	}

	// prettier-ignore
    @Override
    public String toString() {
        return "TCountryCodeCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (countryCode != null ? "countryCode=" + countryCode + ", " : "") +
            (countryName != null ? "countryName=" + countryName + ", " : "") +
            (countryNationality != null ? "countryNationality=" + countryNationality + ", " : "") +
            (enteredBy != null ? "enteredBy=" + enteredBy + ", " : "") +
            (enteredDate != null ? "enteredDate=" + enteredDate + ", " : "") +
            (modifiedBy != null ? "modifiedBy=" + modifiedBy + ", " : "") +
            (modifiedDate != null ? "modifiedDate=" + modifiedDate + ", " : "") +
            (orgCustomerType != null ? "orgCustomerType=" + orgCustomerType + ", " : "") +
            (tStateCodeId != null ? "tStateCodeId=" + tStateCodeId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
