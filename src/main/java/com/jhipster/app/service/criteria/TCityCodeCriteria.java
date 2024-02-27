package com.jhipster.app.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.jhipster.app.domain.TCityCode} entity. This class is used
 * in {@link com.jhipster.app.web.rest.TCityCodeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /t-city-codes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TCityCodeCriteria implements Serializable, Criteria {

	private static final long serialVersionUID = 1L;

	private LongFilter id;

	private StringFilter cityCode;

	private StringFilter cityName;

	private IntegerFilter enteredBy;

	private ZonedDateTimeFilter enteredDate;

	private IntegerFilter modifiedBy;

	private ZonedDateTimeFilter modifiedDate;

	private LongFilter tStateCodeId;

	private Boolean distinct;

	public TCityCodeCriteria() {}

	public TCityCodeCriteria(TCityCodeCriteria other) {
		this.id = other.id == null ? null : other.id.copy();
		this.cityCode = other.cityCode == null ? null : other.cityCode.copy();
		this.cityName = other.cityName == null ? null : other.cityName.copy();
		this.enteredBy = other.enteredBy == null ? null : other.enteredBy.copy();
		this.enteredDate = other.enteredDate == null ? null : other.enteredDate.copy();
		this.modifiedBy = other.modifiedBy == null ? null : other.modifiedBy.copy();
		this.modifiedDate = other.modifiedDate == null ? null : other.modifiedDate.copy();
		this.tStateCodeId = other.tStateCodeId == null ? null : other.tStateCodeId.copy();
		this.distinct = other.distinct;
	}

	@Override
	public TCityCodeCriteria copy() {
		return new TCityCodeCriteria(this);
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

	public StringFilter getCityCode() {
		return cityCode;
	}

	public StringFilter cityCode() {
		if (cityCode == null) {
			cityCode = new StringFilter();
		}
		return cityCode;
	}

	public void setCityCode(StringFilter cityCode) {
		this.cityCode = cityCode;
	}

	public StringFilter getCityName() {
		return cityName;
	}

	public StringFilter cityName() {
		if (cityName == null) {
			cityName = new StringFilter();
		}
		return cityName;
	}

	public void setCityName(StringFilter cityName) {
		this.cityName = cityName;
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
		final TCityCodeCriteria that = (TCityCodeCriteria) o;
		return (
			Objects.equals(id, that.id) &&
			Objects.equals(cityCode, that.cityCode) &&
			Objects.equals(cityName, that.cityName) &&
			Objects.equals(enteredBy, that.enteredBy) &&
			Objects.equals(enteredDate, that.enteredDate) &&
			Objects.equals(modifiedBy, that.modifiedBy) &&
			Objects.equals(modifiedDate, that.modifiedDate) &&
			Objects.equals(tStateCodeId, that.tStateCodeId) &&
			Objects.equals(distinct, that.distinct)
		);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cityCode, cityName, enteredBy, enteredDate, modifiedBy, modifiedDate, tStateCodeId, distinct);
	}

	// prettier-ignore
    @Override
    public String toString() {
        return "TCityCodeCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (cityCode != null ? "cityCode=" + cityCode + ", " : "") +
            (cityName != null ? "cityName=" + cityName + ", " : "") +
            (enteredBy != null ? "enteredBy=" + enteredBy + ", " : "") +
            (enteredDate != null ? "enteredDate=" + enteredDate + ", " : "") +
            (modifiedBy != null ? "modifiedBy=" + modifiedBy + ", " : "") +
            (modifiedDate != null ? "modifiedDate=" + modifiedDate + ", " : "") +
            (tStateCodeId != null ? "tStateCodeId=" + tStateCodeId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
