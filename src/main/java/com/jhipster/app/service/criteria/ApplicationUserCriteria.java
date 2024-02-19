package com.jhipster.app.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.jhipster.app.domain.ApplicationUser} entity. This class is used
 * in {@link com.jhipster.app.web.rest.ApplicationUserResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /application-users?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ApplicationUserCriteria implements Serializable, Criteria {

	private static final long serialVersionUID = 1L;

	private LongFilter id;

	private StringFilter ic;

	private StringFilter status;

	private LongFilter internalUserId;

	private LongFilter userRoleId;

	private LongFilter divisionId;

	private LongFilter sectionId;

	private LongFilter unitId;

	private LongFilter officeId;

	private Boolean distinct;

	public ApplicationUserCriteria() {}

	public ApplicationUserCriteria(ApplicationUserCriteria other) {
		this.id = other.id == null ? null : other.id.copy();
		this.ic = other.ic == null ? null : other.ic.copy();
		this.status = other.status == null ? null : other.status.copy();
		this.internalUserId = other.internalUserId == null ? null : other.internalUserId.copy();
		this.userRoleId = other.userRoleId == null ? null : other.userRoleId.copy();
		this.divisionId = other.divisionId == null ? null : other.divisionId.copy();
		this.sectionId = other.sectionId == null ? null : other.sectionId.copy();
		this.unitId = other.unitId == null ? null : other.unitId.copy();
		this.officeId = other.officeId == null ? null : other.officeId.copy();
		this.distinct = other.distinct;
	}

	@Override
	public ApplicationUserCriteria copy() {
		return new ApplicationUserCriteria(this);
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

	public StringFilter getIc() {
		return ic;
	}

	public StringFilter ic() {
		if (ic == null) {
			ic = new StringFilter();
		}
		return ic;
	}

	public void setIc(StringFilter ic) {
		this.ic = ic;
	}

	public StringFilter getStatus() {
		return status;
	}

	public StringFilter status() {
		if (status == null) {
			status = new StringFilter();
		}
		return status;
	}

	public void setStatus(StringFilter status) {
		this.status = status;
	}

	public LongFilter getInternalUserId() {
		return internalUserId;
	}

	public LongFilter internalUserId() {
		if (internalUserId == null) {
			internalUserId = new LongFilter();
		}
		return internalUserId;
	}

	public void setInternalUserId(LongFilter internalUserId) {
		this.internalUserId = internalUserId;
	}

	public LongFilter getUserRoleId() {
		return userRoleId;
	}

	public LongFilter userRoleId() {
		if (userRoleId == null) {
			userRoleId = new LongFilter();
		}
		return userRoleId;
	}

	public void setUserRoleId(LongFilter userRoleId) {
		this.userRoleId = userRoleId;
	}

	public LongFilter getDivisionId() {
		return divisionId;
	}

	public LongFilter divisionId() {
		if (divisionId == null) {
			divisionId = new LongFilter();
		}
		return divisionId;
	}

	public void setDivisionId(LongFilter divisionId) {
		this.divisionId = divisionId;
	}

	public LongFilter getSectionId() {
		return sectionId;
	}

	public LongFilter sectionId() {
		if (sectionId == null) {
			sectionId = new LongFilter();
		}
		return sectionId;
	}

	public void setSectionId(LongFilter sectionId) {
		this.sectionId = sectionId;
	}

	public LongFilter getUnitId() {
		return unitId;
	}

	public LongFilter unitId() {
		if (unitId == null) {
			unitId = new LongFilter();
		}
		return unitId;
	}

	public void setUnitId(LongFilter unitId) {
		this.unitId = unitId;
	}

	public LongFilter getOfficeId() {
		return officeId;
	}

	public LongFilter officeId() {
		if (officeId == null) {
			officeId = new LongFilter();
		}
		return officeId;
	}

	public void setOfficeId(LongFilter officeId) {
		this.officeId = officeId;
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
		final ApplicationUserCriteria that = (ApplicationUserCriteria) o;
		return (
			Objects.equals(id, that.id) &&
			Objects.equals(ic, that.ic) &&
			Objects.equals(status, that.status) &&
			Objects.equals(internalUserId, that.internalUserId) &&
			Objects.equals(userRoleId, that.userRoleId) &&
			Objects.equals(divisionId, that.divisionId) &&
			Objects.equals(sectionId, that.sectionId) &&
			Objects.equals(unitId, that.unitId) &&
			Objects.equals(officeId, that.officeId) &&
			Objects.equals(distinct, that.distinct)
		);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, ic, status, internalUserId, userRoleId, divisionId, sectionId, unitId, officeId, distinct);
	}

	// prettier-ignore
    @Override
    public String toString() {
        return "ApplicationUserCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (ic != null ? "ic=" + ic + ", " : "") +
            (status != null ? "status=" + status + ", " : "") +
            (internalUserId != null ? "internalUserId=" + internalUserId + ", " : "") +
            (userRoleId != null ? "userRoleId=" + userRoleId + ", " : "") +
            (divisionId != null ? "divisionId=" + divisionId + ", " : "") +
            (sectionId != null ? "sectionId=" + sectionId + ", " : "") +
            (unitId != null ? "unitId=" + unitId + ", " : "") +
            (officeId != null ? "officeId=" + officeId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
