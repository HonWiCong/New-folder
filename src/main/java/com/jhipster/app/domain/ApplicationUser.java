package com.jhipster.app.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ApplicationUser.
 */
@Entity
@Table(name = "application_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ApplicationUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Size(max = 15)
	@Column(name = "ic", length = 15)
	private String ic;

	@Size(max = 1)
	@Column(name = "status", length = 1)
	private String status;

	// @OneToOne
	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(unique = true)
	private User internalUser;

	// @OneToMany(mappedBy = "user")
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	// @JsonIgnoreProperties(value = { "role", "user" }, allowSetters = true)
	@JsonIgnoreProperties(value = { "user" }, allowSetters = true)
	private Set<TUserRole> userRoles = new HashSet<>();

	@Transient
	public Set<TUserRole> newUserRoles = new HashSet<>();

	@ManyToOne
	private TSectionCode division;

	@ManyToOne
	@JsonIgnoreProperties(value = { "subunits" }, allowSetters = true)
	private TUnitCode section;

	@ManyToOne
	@JsonIgnoreProperties(value = { "unit" }, allowSetters = true)
	private TSubunitCode unit;

	@ManyToOne
	private TOfficeCode office;

	// jhipster-needle-entity-add-field - JHipster will add fields here

	public Long getId() {
		return this.id;
	}

	public ApplicationUser id(Long id) {
		this.setId(id);
		return this;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIc() {
		return this.ic;
	}

	public ApplicationUser ic(String ic) {
		this.setIc(ic);
		return this;
	}

	public void setIc(String ic) {
		this.ic = ic;
	}

	public String getStatus() {
		return this.status;
	}

	public ApplicationUser status(String status) {
		this.setStatus(status);
		return this;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getInternalUser() {
		return this.internalUser;
	}

	public void setInternalUser(User user) {
		this.internalUser = user;
	}

	public ApplicationUser internalUser(User user) {
		this.setInternalUser(user);
		return this;
	}

	public Set<TUserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<TUserRole> tUserRoles) {
		if (this.userRoles != null) {
			this.userRoles.forEach(i -> i.setUser(null));
		}
		if (tUserRoles != null) {
			tUserRoles.forEach(i -> i.setUser(this));
		}
		this.userRoles = tUserRoles;
	}

	public ApplicationUser userRoles(Set<TUserRole> tUserRoles) {
		this.setUserRoles(tUserRoles);
		return this;
	}

	public ApplicationUser addUserRole(TUserRole tUserRole) {
		this.userRoles.add(tUserRole);
		tUserRole.setUser(this);
		return this;
	}

	public ApplicationUser removeUserRole(TUserRole tUserRole) {
		this.userRoles.remove(tUserRole);
		tUserRole.setUser(null);
		return this;
	}

	@JsonGetter("newUserRoles")
	public Set<TUserRole> getNewUserRoles() {
		return this.newUserRoles;
	}

	@JsonSetter("newUserRoles")
	public void setNewUserRoles(Set<TUserRole> tUserRoles) {
		if (this.newUserRoles != null) {
			this.newUserRoles.forEach(i -> i.setUser(null));
		}
		if (tUserRoles != null) {
			tUserRoles.forEach(i -> i.setUser(this));
		}
		this.newUserRoles = tUserRoles;
	}

	public TSectionCode getDivision() {
		return this.division;
	}

	public void setDivision(TSectionCode tSectionCode) {
		this.division = tSectionCode;
	}

	public ApplicationUser division(TSectionCode tSectionCode) {
		this.setDivision(tSectionCode);
		return this;
	}

	public TUnitCode getSection() {
		return this.section;
	}

	public void setSection(TUnitCode tUnitCode) {
		this.section = tUnitCode;
	}

	public ApplicationUser section(TUnitCode tUnitCode) {
		this.setSection(tUnitCode);
		return this;
	}

	public TSubunitCode getUnit() {
		return this.unit;
	}

	public void setUnit(TSubunitCode tSubunitCode) {
		this.unit = tSubunitCode;
	}

	public ApplicationUser unit(TSubunitCode tSubunitCode) {
		this.setUnit(tSubunitCode);
		return this;
	}

	public TOfficeCode getOffice() {
		return this.office;
	}

	public void setOffice(TOfficeCode tOfficeCode) {
		this.office = tOfficeCode;
	}

	public ApplicationUser office(TOfficeCode tOfficeCode) {
		this.setOffice(tOfficeCode);
		return this;
	}

	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ApplicationUser)) {
			return false;
		}
		return id != null && id.equals(((ApplicationUser) o).id);
	}

	@Override
	public int hashCode() {
		// see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
		return getClass().hashCode();
	}

	// prettier-ignore
    @Override
    public String toString() {
        return "ApplicationUser{" +
            "id=" + getId() +
            ", ic='" + getIc() + "'" +
            ", status='" + getStatus() + "'" +
			", newUserRoles='" + getNewUserRoles() + "'" +
            "}";
    }
}
