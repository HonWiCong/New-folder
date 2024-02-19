package com.jhipster.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TUserRole.
 */
@Entity
@Table(name = "t_user_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TUserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "sysuser_id")
	private Integer sysuserId;

	@Column(name = "usr_roleid")
	private Integer usrRoleid;

	@Column(name = "entered_by")
	private Integer enteredBy;

	@Column(name = "entered_date")
	private ZonedDateTime enteredDate;

	@Column(name = "modified_by")
	private Integer modifiedBy;

	@Column(name = "modified_date")
	private ZonedDateTime modifiedDate;

	@ManyToOne
	@JsonIgnoreProperties(value = { "userRoles" }, allowSetters = true)
	private TUserRoleCode role;

	@ManyToOne
	@JsonIgnoreProperties(value = { "internalUser", "userRoles", "division", "section", "unit", "office" }, allowSetters = true)
	private ApplicationUser user;

	// jhipster-needle-entity-add-field - JHipster will add fields here

	public Long getId() {
		return this.id;
	}

	public TUserRole id(Long id) {
		this.setId(id);
		return this;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSysuserId() {
		return this.sysuserId;
	}

	public TUserRole sysuserId(Integer sysuserId) {
		this.setSysuserId(sysuserId);
		return this;
	}

	public void setSysuserId(Integer sysuserId) {
		this.sysuserId = sysuserId;
	}

	public Integer getUsrRoleid() {
		return this.usrRoleid;
	}

	public TUserRole usrRoleid(Integer usrRoleid) {
		this.setUsrRoleid(usrRoleid);
		return this;
	}

	public void setUsrRoleid(Integer usrRoleid) {
		this.usrRoleid = usrRoleid;
	}

	public Integer getEnteredBy() {
		return this.enteredBy;
	}

	public TUserRole enteredBy(Integer enteredBy) {
		this.setEnteredBy(enteredBy);
		return this;
	}

	public void setEnteredBy(Integer enteredBy) {
		this.enteredBy = enteredBy;
	}

	public ZonedDateTime getEnteredDate() {
		return this.enteredDate;
	}

	public TUserRole enteredDate(ZonedDateTime enteredDate) {
		this.setEnteredDate(enteredDate);
		return this;
	}

	public void setEnteredDate(ZonedDateTime enteredDate) {
		this.enteredDate = enteredDate;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public TUserRole modifiedBy(Integer modifiedBy) {
		this.setModifiedBy(modifiedBy);
		return this;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public ZonedDateTime getModifiedDate() {
		return this.modifiedDate;
	}

	public TUserRole modifiedDate(ZonedDateTime modifiedDate) {
		this.setModifiedDate(modifiedDate);
		return this;
	}

	public void setModifiedDate(ZonedDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public TUserRoleCode getRole() {
		return this.role;
	}

	public void setRole(TUserRoleCode tUserRoleCode) {
		this.role = tUserRoleCode;
	}

	public TUserRole role(TUserRoleCode tUserRoleCode) {
		this.setRole(tUserRoleCode);
		return this;
	}

	public ApplicationUser getUser() {
		return this.user;
	}

	public void setUser(ApplicationUser applicationUser) {
		this.user = applicationUser;
	}

	public TUserRole user(ApplicationUser applicationUser) {
		this.setUser(applicationUser);
		return this;
	}

	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof TUserRole)) {
			return false;
		}
		return id != null && id.equals(((TUserRole) o).id);
	}

	@Override
	public int hashCode() {
		// see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
		return getClass().hashCode();
	}

	// prettier-ignore
    @Override
    public String toString() {
        return "TUserRole{" +
            "id=" + getId() +
            ", sysuserId=" + getSysuserId() +
            ", usrRoleid=" + getUsrRoleid() +
            ", enteredBy=" + getEnteredBy() +
            ", enteredDate='" + getEnteredDate() + "'" +
            ", modifiedBy=" + getModifiedBy() +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
