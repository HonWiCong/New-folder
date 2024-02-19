package com.jhipster.app.service.dto;

import com.jhipster.app.domain.ApplicationUser;
import com.jhipster.app.domain.TOfficeCode;
import com.jhipster.app.domain.TSectionCode;
import com.jhipster.app.domain.TSubunitCode;
import com.jhipster.app.domain.TUnitCode;
import com.jhipster.app.domain.TUserRole;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;

public class ApplicationUserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private AdminUserDTO internalUser;

	@Size(max = 15)
	private String ic;

	private TSectionCode division;

	private TUnitCode section;

	private TSubunitCode unit;

	private TOfficeCode office;

	private Set<TUserRole> userRoles = new HashSet<>();

	@Size(max = 1)
	private String status;

	public ApplicationUserDTO() {
		// Empty constructor needed for Jackson.
	}

	public ApplicationUserDTO(AdminUserDTO user, ApplicationUser applicationUser) {
		// default jhipster user
		this.internalUser = user;

		// application user
		this.id = applicationUser.getId();
		this.ic = applicationUser.getIc();
		this.division = applicationUser.getDivision();
		this.section = applicationUser.getSection();
		this.unit = applicationUser.getUnit();
		this.office = applicationUser.getOffice();
	}

	public AdminUserDTO getInternalUser() {
		return this.internalUser;
	}

	public void setInternalUser(AdminUserDTO user) {
		this.internalUser = user;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long applicationUserId) {
		this.id = applicationUserId;
	}

	public String getIc() {
		return this.ic;
	}

	public void setIc(String ic) {
		this.ic = ic;
	}

	public TSectionCode getDivision() {
		return this.division;
	}

	public void setDivision(TSectionCode division) {
		this.division = division;
	}

	public TUnitCode getSection() {
		return this.section;
	}

	public void setSection(TUnitCode section) {
		this.section = section;
	}

	public TSubunitCode getUnit() {
		return this.unit;
	}

	public void setUnit(TSubunitCode unit) {
		this.unit = unit;
	}

	public TOfficeCode getOffice() {
		return this.office;
	}

	public void setOffice(TOfficeCode office) {
		this.office = office;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<TUserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<TUserRole> tUserRoles) {
		this.userRoles = tUserRoles;
	}

	// prettier-ignore
    @Override
    public String toString() {
        return "ApplicationUserDTO{" +
            "user id='" + internalUser + '\'' +
            ", application user id='" + id + '\'' +
            // ", login='" + user.getLogin() + '\'' +
            // ", firstName='" + user.getFirstName() + '\'' +
            // ", lastName='" + user.getLastName() + '\'' +
            // ", email='" + user.getEmail() + '\'' +
            // ", imageUrl='" + user.getImageUrl() + '\'' +
            // ", activated=" + user.isActivated() +
            // ", langKey='" + user.getLangKey() + '\'' +
            // ", createdBy=" + user.getCreatedBy() +
            // ", createdDate=" + user.getCreatedDate() +
            // ", lastModifiedBy='" + user.getLastModifiedBy() + '\'' +
            // ", lastModifiedDate=" + user.getLastModifiedDate() +
            // ", authorities=" + user.getAuthorities() +
			", ic='" + ic + '\'' +
			", divisionId=" + division +
			", sectionId=" + section +
			", unitId=" + unit +
			", officeId=" + office +
			", status=" + status +
            "}";
    }
}
