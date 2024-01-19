package com.jhipster.app.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TUserRoleCode.
 */
@Entity
@Table(name = "t_user_role_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TUserRoleCode implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "role_head")
	private String roleHead;

	@Column(name = "act_approver_1")
	private Integer actApprover1;

	@Column(name = "act_approver_2")
	private Integer actApprover2;

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

	public TUserRoleCode id(Long id) {
		this.setId(id);
		return this;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public TUserRoleCode roleName(String roleName) {
		this.setRoleName(roleName);
		return this;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleHead() {
		return this.roleHead;
	}

	public TUserRoleCode roleHead(String roleHead) {
		this.setRoleHead(roleHead);
		return this;
	}

	public void setRoleHead(String roleHead) {
		this.roleHead = roleHead;
	}

	public Integer getActApprover1() {
		return this.actApprover1;
	}

	public TUserRoleCode actApprover1(Integer actApprover1) {
		this.setActApprover1(actApprover1);
		return this;
	}

	public void setActApprover1(Integer actApprover1) {
		this.actApprover1 = actApprover1;
	}

	public Integer getActApprover2() {
		return this.actApprover2;
	}

	public TUserRoleCode actApprover2(Integer actApprover2) {
		this.setActApprover2(actApprover2);
		return this;
	}

	public void setActApprover2(Integer actApprover2) {
		this.actApprover2 = actApprover2;
	}

	public Integer getEnteredBy() {
		return this.enteredBy;
	}

	public TUserRoleCode enteredBy(Integer enteredBy) {
		this.setEnteredBy(enteredBy);
		return this;
	}

	public void setEnteredBy(Integer enteredBy) {
		this.enteredBy = enteredBy;
	}

	public ZonedDateTime getEnteredDate() {
		return this.enteredDate;
	}

	public TUserRoleCode enteredDate(ZonedDateTime enteredDate) {
		this.setEnteredDate(enteredDate);
		return this;
	}

	public void setEnteredDate(ZonedDateTime enteredDate) {
		this.enteredDate = enteredDate;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public TUserRoleCode modifiedBy(Integer modifiedBy) {
		this.setModifiedBy(modifiedBy);
		return this;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public ZonedDateTime getModifiedDate() {
		return this.modifiedDate;
	}

	public TUserRoleCode modifiedDate(ZonedDateTime modifiedDate) {
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
		if (!(o instanceof TUserRoleCode)) {
			return false;
		}
		return id != null && id.equals(((TUserRoleCode) o).id);
	}

	@Override
	public int hashCode() {
		// see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
		return getClass().hashCode();
	}

	// prettier-ignore
    @Override
    public String toString() {
        return "TUserRoleCode{" +
            "id=" + getId() +
            ", roleName='" + getRoleName() + "'" +
            ", roleHead='" + getRoleHead() + "'" +
            ", actApprover1=" + getActApprover1() +
            ", actApprover2=" + getActApprover2() +
            ", enteredBy=" + getEnteredBy() +
            ", enteredDate='" + getEnteredDate() + "'" +
            ", modifiedBy=" + getModifiedBy() +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
