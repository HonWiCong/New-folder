package com.jhipster.app.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.jhipster.app.domain.TStateCode} entity. This class is used
 * in {@link com.jhipster.app.web.rest.TStateCodeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /t-state-codes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TStateCodeCriteria implements Serializable, Criteria {

	private static final long serialVersionUID = 1L;

	private LongFilter id;

	private StringFilter stateName;

	private StringFilter stateCode;

	private IntegerFilter enteredBy;

	private ZonedDateTimeFilter enteredDate;

	private IntegerFilter modifiedBy;

	private ZonedDateTimeFilter modifiedDate;

	private LongFilter tCountryCodeId;

	private LongFilter tCityCodeId;

	private Boolean distinct;

	private StringFilter countryName;

	public TStateCodeCriteria() {}

	public TStateCodeCriteria(TStateCodeCriteria other) {
		this.id = other.id == null ? null : other.id.copy();
		this.stateName = other.stateName == null ? null : other.stateName.copy();
		this.stateCode = other.stateCode == null ? null : other.stateCode.copy();
		this.enteredBy = other.enteredBy == null ? null : other.enteredBy.copy();
		this.enteredDate = other.enteredDate == null ? null : other.enteredDate.copy();
		this.modifiedBy = other.modifiedBy == null ? null : other.modifiedBy.copy();
		this.modifiedDate = other.modifiedDate == null ? null : other.modifiedDate.copy();
		this.tCountryCodeId = other.tCountryCodeId == null ? null : other.tCountryCodeId.copy();
		this.tCityCodeId = other.tCityCodeId == null ? null : other.tCityCodeId.copy();
		this.distinct = other.distinct;
		this.countryName = other.countryName == null ? null : other.countryName.copy();
	}

	@Override
	public TStateCodeCriteria copy() {
		return new TStateCodeCriteria(this);
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

	public StringFilter getStateName() {
		return stateName;
	}

	public StringFilter stateName() {
		if (stateName == null) {
			stateName = new StringFilter();
		}
		return stateName;
	}

	public void setStateName(StringFilter stateName) {
		this.stateName = stateName;
	}

	public StringFilter getStateCode() {
		return stateCode;
	}

	public StringFilter stateCode() {
		if (stateCode == null) {
			stateCode = new StringFilter();
		}
		return stateCode;
	}

	public void setStateCode(StringFilter stateCode) {
		this.stateCode = stateCode;
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

	public LongFilter getTCountryCodeId() {
		return tCountryCodeId;
	}

	public LongFilter tCountryCodeId() {
		if (tCountryCodeId == null) {
			tCountryCodeId = new LongFilter();
		}
		return tCountryCodeId;
	}

	public void setTCountryCodeId(LongFilter tCountryCodeId) {
		this.tCountryCodeId = tCountryCodeId;
	}

	public LongFilter getTCityCodeId() {
		return tCityCodeId;
	}

	public LongFilter tCityCodeId() {
		if (tCityCodeId == null) {
			tCityCodeId = new LongFilter();
		}
		return tCityCodeId;
	}

	public void setTCityCodeId(LongFilter tCityCodeId) {
		this.tCityCodeId = tCityCodeId;
	}

	public Boolean getDistinct() {
		return distinct;
	}

	public void setDistinct(Boolean distinct) {
		this.distinct = distinct;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final TStateCodeCriteria that = (TStateCodeCriteria) o;
		return (
			Objects.equals(id, that.id) &&
			Objects.equals(stateName, that.stateName) &&
			Objects.equals(stateCode, that.stateCode) &&
			Objects.equals(enteredBy, that.enteredBy) &&
			Objects.equals(enteredDate, that.enteredDate) &&
			Objects.equals(modifiedBy, that.modifiedBy) &&
			Objects.equals(modifiedDate, that.modifiedDate) &&
			Objects.equals(tCountryCodeId, that.tCountryCodeId) &&
			Objects.equals(tCityCodeId, that.tCityCodeId) &&
			Objects.equals(distinct, that.distinct) &&
			Objects.equals(countryName, that.countryName)
		);
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			id,
			stateName,
			stateCode,
			enteredBy,
			enteredDate,
			modifiedBy,
			modifiedDate,
			tCountryCodeId,
			tCityCodeId,
			distinct,
			countryName
		);
	}

	// prettier-ignore
    @Override
    public String toString() {
        return "TStateCodeCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (stateName != null ? "stateName=" + stateName + ", " : "") +
            (stateCode != null ? "stateCode=" + stateCode + ", " : "") +
            (enteredBy != null ? "enteredBy=" + enteredBy + ", " : "") +
            (enteredDate != null ? "enteredDate=" + enteredDate + ", " : "") +
            (modifiedBy != null ? "modifiedBy=" + modifiedBy + ", " : "") +
            (modifiedDate != null ? "modifiedDate=" + modifiedDate + ", " : "") +
            (tCountryCodeId != null ? "tCountryCodeId=" + tCountryCodeId + ", " : "") +
            (tCityCodeId != null ? "tCityCodeId=" + tCityCodeId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
			(countryName != null ? "countryName=" + countryName + ", " : "") +
            "}";
    }
}
