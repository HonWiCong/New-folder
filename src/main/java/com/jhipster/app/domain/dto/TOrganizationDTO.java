package com.jhipster.app.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jhipster.app.domain.TOrgContactPerson;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

public class TOrganizationDTO {

	public List<Long> deletedId;

	private Long id;

	private Integer orgHqid;

	private String orgHqBr;

	private String orgCode;

	private String orgBrn;

	private Long orgPtaxid;

	private String orgDefaultTaxCode;

	private String orgCompanyGstNo;

	private LocalDate orgCompanyGstRegDate;

	private LocalDate orgCompanyGstDeregDate;

	private String orgPoTaxInclusive;

	private String orgName;

	private String orgNameOther;

	private String orgShortname;

	private String orgAddress;

	private String orgShippingAddress;

	private String orgBillingAddress;

	private String orgPostcode;

	private String orgCity;

	private String orgState;

	private String orgCountry;

	private String orgOffPhone1;

	private String orgOffPhone2;

	private String orgOffPhone3;

	private String orgOffFax1;

	private String orgOffFax2;

	private Long orgCredittermid;

	private BigDecimal orgCreditLimit;

	private Long orgAgencyid;

	private String orgDivision;

	private String orgDistrict;

	private String orgWebsite;

	private String orgEmail;

	private String orgSupplierCategory;

	private String orgCurrencyCode;

	private String orgType;

	private Long orgSectorid;

	private String orgSector;

	private String orgIndustry;

	private String orgSainsGroup;

	private String orgBumiputra;

	private String orgUpkReg;

	private String orgMofReg;

	private String orgDesignation;

	private String orgContpersonTitle;

	private String orgContperson;

	private String orgDirectline;

	private String orgContpEmail;

	private String orgContpHp;

	private String orgRemarks;

	private String orgActiveStatus;

	private String orgCcGc;

	private String orgCustomerCateCode;

	private String orgVendorCateCode;

	private String orgSalesCateCode;

	private BigDecimal orgOutstandingBalance;

	private BigDecimal orgOutstandingBalanceEx;

	private String orgCompanyCode;

	private String orgDcrownPostStatus;

	private Integer confirmedBy;

	private ZonedDateTime confirmedDate;

	private Integer enteredBy;

	private ZonedDateTime enteredDate;

	private Integer modifiedBy;

	private ZonedDateTime modifiedDate;

	@JsonIgnore
	public Set<TOrgContactPerson> tOrgContactPeople;

	public void setTOrgContactPeople(Set<TOrgContactPerson> tOrgContactPeople) {
		this.tOrgContactPeople = tOrgContactPeople;
	}

	public Set<TOrgContactPerson> getTOrgContactPeople() {
		return tOrgContactPeople;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here

	public Long getId() {
		return this.id;
	}

	public TOrganizationDTO id(Long id) {
		this.setId(id);
		return this;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrgHqid() {
		return this.orgHqid;
	}

	public TOrganizationDTO orgHqid(Integer orgHqid) {
		this.setOrgHqid(orgHqid);
		return this;
	}

	public void setOrgHqid(Integer orgHqid) {
		this.orgHqid = orgHqid;
	}

	public String getOrgHqBr() {
		return this.orgHqBr;
	}

	public TOrganizationDTO orgHqBr(String orgHqBr) {
		this.setOrgHqBr(orgHqBr);
		return this;
	}

	public void setOrgHqBr(String orgHqBr) {
		this.orgHqBr = orgHqBr;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public TOrganizationDTO orgCode(String orgCode) {
		this.setOrgCode(orgCode);
		return this;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgBrn() {
		return this.orgBrn;
	}

	public TOrganizationDTO orgBrn(String orgBrn) {
		this.setOrgBrn(orgBrn);
		return this;
	}

	public void setOrgBrn(String orgBrn) {
		this.orgBrn = orgBrn;
	}

	public Long getOrgPtaxid() {
		return this.orgPtaxid;
	}

	public TOrganizationDTO orgPtaxid(Long orgPtaxid) {
		this.setOrgPtaxid(orgPtaxid);
		return this;
	}

	public void setOrgPtaxid(Long orgPtaxid) {
		this.orgPtaxid = orgPtaxid;
	}

	public String getOrgDefaultTaxCode() {
		return this.orgDefaultTaxCode;
	}

	public TOrganizationDTO orgDefaultTaxCode(String orgDefaultTaxCode) {
		this.setOrgDefaultTaxCode(orgDefaultTaxCode);
		return this;
	}

	public void setOrgDefaultTaxCode(String orgDefaultTaxCode) {
		this.orgDefaultTaxCode = orgDefaultTaxCode;
	}

	public String getOrgCompanyGstNo() {
		return this.orgCompanyGstNo;
	}

	public TOrganizationDTO orgCompanyGstNo(String orgCompanyGstNo) {
		this.setOrgCompanyGstNo(orgCompanyGstNo);
		return this;
	}

	public void setOrgCompanyGstNo(String orgCompanyGstNo) {
		this.orgCompanyGstNo = orgCompanyGstNo;
	}

	public LocalDate getOrgCompanyGstRegDate() {
		return this.orgCompanyGstRegDate;
	}

	public TOrganizationDTO orgCompanyGstRegDate(LocalDate orgCompanyGstRegDate) {
		this.setOrgCompanyGstRegDate(orgCompanyGstRegDate);
		return this;
	}

	public void setOrgCompanyGstRegDate(LocalDate orgCompanyGstRegDate) {
		this.orgCompanyGstRegDate = orgCompanyGstRegDate;
	}

	public LocalDate getOrgCompanyGstDeregDate() {
		return this.orgCompanyGstDeregDate;
	}

	public TOrganizationDTO orgCompanyGstDeregDate(LocalDate orgCompanyGstDeregDate) {
		this.setOrgCompanyGstDeregDate(orgCompanyGstDeregDate);
		return this;
	}

	public void setOrgCompanyGstDeregDate(LocalDate orgCompanyGstDeregDate) {
		this.orgCompanyGstDeregDate = orgCompanyGstDeregDate;
	}

	public String getOrgPoTaxInclusive() {
		return this.orgPoTaxInclusive;
	}

	public TOrganizationDTO orgPoTaxInclusive(String orgPoTaxInclusive) {
		this.setOrgPoTaxInclusive(orgPoTaxInclusive);
		return this;
	}

	public void setOrgPoTaxInclusive(String orgPoTaxInclusive) {
		this.orgPoTaxInclusive = orgPoTaxInclusive;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public TOrganizationDTO orgName(String orgName) {
		this.setOrgName(orgName);
		return this;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgNameOther() {
		return this.orgNameOther;
	}

	public TOrganizationDTO orgNameOther(String orgNameOther) {
		this.setOrgNameOther(orgNameOther);
		return this;
	}

	public void setOrgNameOther(String orgNameOther) {
		this.orgNameOther = orgNameOther;
	}

	public String getOrgShortname() {
		return this.orgShortname;
	}

	public TOrganizationDTO orgShortname(String orgShortname) {
		this.setOrgShortname(orgShortname);
		return this;
	}

	public void setOrgShortname(String orgShortname) {
		this.orgShortname = orgShortname;
	}

	public String getOrgAddress() {
		return this.orgAddress;
	}

	public TOrganizationDTO orgAddress(String orgAddress) {
		this.setOrgAddress(orgAddress);
		return this;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	public String getOrgShippingAddress() {
		return this.orgShippingAddress;
	}

	public TOrganizationDTO orgShippingAddress(String orgShippingAddress) {
		this.setOrgShippingAddress(orgShippingAddress);
		return this;
	}

	public void setOrgShippingAddress(String orgShippingAddress) {
		this.orgShippingAddress = orgShippingAddress;
	}

	public String getOrgBillingAddress() {
		return this.orgBillingAddress;
	}

	public TOrganizationDTO orgBillingAddress(String orgBillingAddress) {
		this.setOrgBillingAddress(orgBillingAddress);
		return this;
	}

	public void setOrgBillingAddress(String orgBillingAddress) {
		this.orgBillingAddress = orgBillingAddress;
	}

	public String getOrgPostcode() {
		return this.orgPostcode;
	}

	public TOrganizationDTO orgPostcode(String orgPostcode) {
		this.setOrgPostcode(orgPostcode);
		return this;
	}

	public void setOrgPostcode(String orgPostcode) {
		this.orgPostcode = orgPostcode;
	}

	public String getOrgCity() {
		return this.orgCity;
	}

	public TOrganizationDTO orgCity(String orgCity) {
		this.setOrgCity(orgCity);
		return this;
	}

	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}

	public String getOrgState() {
		return this.orgState;
	}

	public TOrganizationDTO orgState(String orgState) {
		this.setOrgState(orgState);
		return this;
	}

	public void setOrgState(String orgState) {
		this.orgState = orgState;
	}

	public String getOrgCountry() {
		return this.orgCountry;
	}

	public TOrganizationDTO orgCountry(String orgCountry) {
		this.setOrgCountry(orgCountry);
		return this;
	}

	public void setOrgCountry(String orgCountry) {
		this.orgCountry = orgCountry;
	}

	public String getOrgOffPhone1() {
		return this.orgOffPhone1;
	}

	public TOrganizationDTO orgOffPhone1(String orgOffPhone1) {
		this.setOrgOffPhone1(orgOffPhone1);
		return this;
	}

	public void setOrgOffPhone1(String orgOffPhone1) {
		this.orgOffPhone1 = orgOffPhone1;
	}

	public String getOrgOffPhone2() {
		return this.orgOffPhone2;
	}

	public TOrganizationDTO orgOffPhone2(String orgOffPhone2) {
		this.setOrgOffPhone2(orgOffPhone2);
		return this;
	}

	public void setOrgOffPhone2(String orgOffPhone2) {
		this.orgOffPhone2 = orgOffPhone2;
	}

	public String getOrgOffPhone3() {
		return this.orgOffPhone3;
	}

	public TOrganizationDTO orgOffPhone3(String orgOffPhone3) {
		this.setOrgOffPhone3(orgOffPhone3);
		return this;
	}

	public void setOrgOffPhone3(String orgOffPhone3) {
		this.orgOffPhone3 = orgOffPhone3;
	}

	public String getOrgOffFax1() {
		return this.orgOffFax1;
	}

	public TOrganizationDTO orgOffFax1(String orgOffFax1) {
		this.setOrgOffFax1(orgOffFax1);
		return this;
	}

	public void setOrgOffFax1(String orgOffFax1) {
		this.orgOffFax1 = orgOffFax1;
	}

	public String getOrgOffFax2() {
		return this.orgOffFax2;
	}

	public TOrganizationDTO orgOffFax2(String orgOffFax2) {
		this.setOrgOffFax2(orgOffFax2);
		return this;
	}

	public void setOrgOffFax2(String orgOffFax2) {
		this.orgOffFax2 = orgOffFax2;
	}

	public Long getOrgCredittermid() {
		return this.orgCredittermid;
	}

	public TOrganizationDTO orgCredittermid(Long orgCredittermid) {
		this.setOrgCredittermid(orgCredittermid);
		return this;
	}

	public void setOrgCredittermid(Long orgCredittermid) {
		this.orgCredittermid = orgCredittermid;
	}

	public BigDecimal getOrgCreditLimit() {
		return this.orgCreditLimit;
	}

	public TOrganizationDTO orgCreditLimit(BigDecimal orgCreditLimit) {
		this.setOrgCreditLimit(orgCreditLimit);
		return this;
	}

	public void setOrgCreditLimit(BigDecimal orgCreditLimit) {
		this.orgCreditLimit = orgCreditLimit;
	}

	public Long getOrgAgencyid() {
		return this.orgAgencyid;
	}

	public TOrganizationDTO orgAgencyid(Long orgAgencyid) {
		this.setOrgAgencyid(orgAgencyid);
		return this;
	}

	public void setOrgAgencyid(Long orgAgencyid) {
		this.orgAgencyid = orgAgencyid;
	}

	public String getOrgDivision() {
		return this.orgDivision;
	}

	public TOrganizationDTO orgDivision(String orgDivision) {
		this.setOrgDivision(orgDivision);
		return this;
	}

	public void setOrgDivision(String orgDivision) {
		this.orgDivision = orgDivision;
	}

	public String getOrgDistrict() {
		return this.orgDistrict;
	}

	public TOrganizationDTO orgDistrict(String orgDistrict) {
		this.setOrgDistrict(orgDistrict);
		return this;
	}

	public void setOrgDistrict(String orgDistrict) {
		this.orgDistrict = orgDistrict;
	}

	public String getOrgWebsite() {
		return this.orgWebsite;
	}

	public TOrganizationDTO orgWebsite(String orgWebsite) {
		this.setOrgWebsite(orgWebsite);
		return this;
	}

	public void setOrgWebsite(String orgWebsite) {
		this.orgWebsite = orgWebsite;
	}

	public String getOrgEmail() {
		return this.orgEmail;
	}

	public TOrganizationDTO orgEmail(String orgEmail) {
		this.setOrgEmail(orgEmail);
		return this;
	}

	public void setOrgEmail(String orgEmail) {
		this.orgEmail = orgEmail;
	}

	public String getOrgSupplierCategory() {
		return this.orgSupplierCategory;
	}

	public TOrganizationDTO orgSupplierCategory(String orgSupplierCategory) {
		this.setOrgSupplierCategory(orgSupplierCategory);
		return this;
	}

	public void setOrgSupplierCategory(String orgSupplierCategory) {
		this.orgSupplierCategory = orgSupplierCategory;
	}

	public String getOrgCurrencyCode() {
		return this.orgCurrencyCode;
	}

	public TOrganizationDTO orgCurrencyCode(String orgCurrencyCode) {
		this.setOrgCurrencyCode(orgCurrencyCode);
		return this;
	}

	public void setOrgCurrencyCode(String orgCurrencyCode) {
		this.orgCurrencyCode = orgCurrencyCode;
	}

	public String getOrgType() {
		return this.orgType;
	}

	public TOrganizationDTO orgType(String orgType) {
		this.setOrgType(orgType);
		return this;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public Long getOrgSectorid() {
		return this.orgSectorid;
	}

	public TOrganizationDTO orgSectorid(Long orgSectorid) {
		this.setOrgSectorid(orgSectorid);
		return this;
	}

	public void setOrgSectorid(Long orgSectorid) {
		this.orgSectorid = orgSectorid;
	}

	public String getOrgSector() {
		return this.orgSector;
	}

	public TOrganizationDTO orgSector(String orgSector) {
		this.setOrgSector(orgSector);
		return this;
	}

	public void setOrgSector(String orgSector) {
		this.orgSector = orgSector;
	}

	public String getOrgIndustry() {
		return this.orgIndustry;
	}

	public TOrganizationDTO orgIndustry(String orgIndustry) {
		this.setOrgIndustry(orgIndustry);
		return this;
	}

	public void setOrgIndustry(String orgIndustry) {
		this.orgIndustry = orgIndustry;
	}

	public String getOrgSainsGroup() {
		return this.orgSainsGroup;
	}

	public TOrganizationDTO orgSainsGroup(String orgSainsGroup) {
		this.setOrgSainsGroup(orgSainsGroup);
		return this;
	}

	public void setOrgSainsGroup(String orgSainsGroup) {
		this.orgSainsGroup = orgSainsGroup;
	}

	public String getOrgBumiputra() {
		return this.orgBumiputra;
	}

	public TOrganizationDTO orgBumiputra(String orgBumiputra) {
		this.setOrgBumiputra(orgBumiputra);
		return this;
	}

	public void setOrgBumiputra(String orgBumiputra) {
		this.orgBumiputra = orgBumiputra;
	}

	public String getOrgUpkReg() {
		return this.orgUpkReg;
	}

	public TOrganizationDTO orgUpkReg(String orgUpkReg) {
		this.setOrgUpkReg(orgUpkReg);
		return this;
	}

	public void setOrgUpkReg(String orgUpkReg) {
		this.orgUpkReg = orgUpkReg;
	}

	public String getOrgMofReg() {
		return this.orgMofReg;
	}

	public TOrganizationDTO orgMofReg(String orgMofReg) {
		this.setOrgMofReg(orgMofReg);
		return this;
	}

	public void setOrgMofReg(String orgMofReg) {
		this.orgMofReg = orgMofReg;
	}

	public String getOrgDesignation() {
		return this.orgDesignation;
	}

	public TOrganizationDTO orgDesignation(String orgDesignation) {
		this.setOrgDesignation(orgDesignation);
		return this;
	}

	public void setOrgDesignation(String orgDesignation) {
		this.orgDesignation = orgDesignation;
	}

	public String getOrgContpersonTitle() {
		return this.orgContpersonTitle;
	}

	public TOrganizationDTO orgContpersonTitle(String orgContpersonTitle) {
		this.setOrgContpersonTitle(orgContpersonTitle);
		return this;
	}

	public void setOrgContpersonTitle(String orgContpersonTitle) {
		this.orgContpersonTitle = orgContpersonTitle;
	}

	public String getOrgContperson() {
		return this.orgContperson;
	}

	public TOrganizationDTO orgContperson(String orgContperson) {
		this.setOrgContperson(orgContperson);
		return this;
	}

	public void setOrgContperson(String orgContperson) {
		this.orgContperson = orgContperson;
	}

	public String getOrgDirectline() {
		return this.orgDirectline;
	}

	public TOrganizationDTO orgDirectline(String orgDirectline) {
		this.setOrgDirectline(orgDirectline);
		return this;
	}

	public void setOrgDirectline(String orgDirectline) {
		this.orgDirectline = orgDirectline;
	}

	public String getOrgContpEmail() {
		return this.orgContpEmail;
	}

	public TOrganizationDTO orgContpEmail(String orgContpEmail) {
		this.setOrgContpEmail(orgContpEmail);
		return this;
	}

	public void setOrgContpEmail(String orgContpEmail) {
		this.orgContpEmail = orgContpEmail;
	}

	public String getOrgContpHp() {
		return this.orgContpHp;
	}

	public TOrganizationDTO orgContpHp(String orgContpHp) {
		this.setOrgContpHp(orgContpHp);
		return this;
	}

	public void setOrgContpHp(String orgContpHp) {
		this.orgContpHp = orgContpHp;
	}

	public String getOrgRemarks() {
		return this.orgRemarks;
	}

	public TOrganizationDTO orgRemarks(String orgRemarks) {
		this.setOrgRemarks(orgRemarks);
		return this;
	}

	public void setOrgRemarks(String orgRemarks) {
		this.orgRemarks = orgRemarks;
	}

	public String getOrgActiveStatus() {
		return this.orgActiveStatus;
	}

	public TOrganizationDTO orgActiveStatus(String orgActiveStatus) {
		this.setOrgActiveStatus(orgActiveStatus);
		return this;
	}

	public void setOrgActiveStatus(String orgActiveStatus) {
		this.orgActiveStatus = orgActiveStatus;
	}

	public String getOrgCcGc() {
		return this.orgCcGc;
	}

	public TOrganizationDTO orgCcGc(String orgCcGc) {
		this.setOrgCcGc(orgCcGc);
		return this;
	}

	public void setOrgCcGc(String orgCcGc) {
		this.orgCcGc = orgCcGc;
	}

	public String getOrgCustomerCateCode() {
		return this.orgCustomerCateCode;
	}

	public TOrganizationDTO orgCustomerCateCode(String orgCustomerCateCode) {
		this.setOrgCustomerCateCode(orgCustomerCateCode);
		return this;
	}

	public void setOrgCustomerCateCode(String orgCustomerCateCode) {
		this.orgCustomerCateCode = orgCustomerCateCode;
	}

	public String getOrgVendorCateCode() {
		return this.orgVendorCateCode;
	}

	public TOrganizationDTO orgVendorCateCode(String orgVendorCateCode) {
		this.setOrgVendorCateCode(orgVendorCateCode);
		return this;
	}

	public void setOrgVendorCateCode(String orgVendorCateCode) {
		this.orgVendorCateCode = orgVendorCateCode;
	}

	public String getOrgSalesCateCode() {
		return this.orgSalesCateCode;
	}

	public TOrganizationDTO orgSalesCateCode(String orgSalesCateCode) {
		this.setOrgSalesCateCode(orgSalesCateCode);
		return this;
	}

	public void setOrgSalesCateCode(String orgSalesCateCode) {
		this.orgSalesCateCode = orgSalesCateCode;
	}

	public BigDecimal getOrgOutstandingBalance() {
		return this.orgOutstandingBalance;
	}

	public TOrganizationDTO orgOutstandingBalance(BigDecimal orgOutstandingBalance) {
		this.setOrgOutstandingBalance(orgOutstandingBalance);
		return this;
	}

	public void setOrgOutstandingBalance(BigDecimal orgOutstandingBalance) {
		this.orgOutstandingBalance = orgOutstandingBalance;
	}

	public BigDecimal getOrgOutstandingBalanceEx() {
		return this.orgOutstandingBalanceEx;
	}

	public TOrganizationDTO orgOutstandingBalanceEx(BigDecimal orgOutstandingBalanceEx) {
		this.setOrgOutstandingBalanceEx(orgOutstandingBalanceEx);
		return this;
	}

	public void setOrgOutstandingBalanceEx(BigDecimal orgOutstandingBalanceEx) {
		this.orgOutstandingBalanceEx = orgOutstandingBalanceEx;
	}

	public String getOrgCompanyCode() {
		return this.orgCompanyCode;
	}

	public TOrganizationDTO orgCompanyCode(String orgCompanyCode) {
		this.setOrgCompanyCode(orgCompanyCode);
		return this;
	}

	public void setOrgCompanyCode(String orgCompanyCode) {
		this.orgCompanyCode = orgCompanyCode;
	}

	public String getOrgDcrownPostStatus() {
		return this.orgDcrownPostStatus;
	}

	public TOrganizationDTO orgDcrownPostStatus(String orgDcrownPostStatus) {
		this.setOrgDcrownPostStatus(orgDcrownPostStatus);
		return this;
	}

	public void setOrgDcrownPostStatus(String orgDcrownPostStatus) {
		this.orgDcrownPostStatus = orgDcrownPostStatus;
	}

	public Integer getConfirmedBy() {
		return this.confirmedBy;
	}

	public TOrganizationDTO confirmedBy(Integer confirmedBy) {
		this.setConfirmedBy(confirmedBy);
		return this;
	}

	public void setConfirmedBy(Integer confirmedBy) {
		this.confirmedBy = confirmedBy;
	}

	public ZonedDateTime getConfirmedDate() {
		return this.confirmedDate;
	}

	public TOrganizationDTO confirmedDate(ZonedDateTime confirmedDate) {
		this.setConfirmedDate(confirmedDate);
		return this;
	}

	public void setConfirmedDate(ZonedDateTime confirmedDate) {
		this.confirmedDate = confirmedDate;
	}

	public Integer getEnteredBy() {
		return this.enteredBy;
	}

	public TOrganizationDTO enteredBy(Integer enteredBy) {
		this.setEnteredBy(enteredBy);
		return this;
	}

	public void setEnteredBy(Integer enteredBy) {
		this.enteredBy = enteredBy;
	}

	public ZonedDateTime getEnteredDate() {
		return this.enteredDate;
	}

	public TOrganizationDTO enteredDate(ZonedDateTime enteredDate) {
		this.setEnteredDate(enteredDate);
		return this;
	}

	public void setEnteredDate(ZonedDateTime enteredDate) {
		this.enteredDate = enteredDate;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public TOrganizationDTO modifiedBy(Integer modifiedBy) {
		this.setModifiedBy(modifiedBy);
		return this;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public ZonedDateTime getModifiedDate() {
		return this.modifiedDate;
	}

	public TOrganizationDTO modifiedDate(ZonedDateTime modifiedDate) {
		this.setModifiedDate(modifiedDate);
		return this;
	}

	public void setModifiedDate(ZonedDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String toString() {
		return (
			"TOrganization{" +
			"id=" +
			getId() +
			", orgHqid=" +
			getOrgHqid() +
			", orgHqBr='" +
			getOrgHqBr() +
			"'" +
			", orgCode='" +
			getOrgCode() +
			"'" +
			", orgBrn='" +
			getOrgBrn() +
			"'" +
			", orgPtaxid=" +
			getOrgPtaxid() +
			", orgDefaultTaxCode='" +
			getOrgDefaultTaxCode() +
			"'" +
			", orgCompanyGstNo='" +
			getOrgCompanyGstNo() +
			"'" +
			", orgCompanyGstRegDate='" +
			getOrgCompanyGstRegDate() +
			"'" +
			", orgCompanyGstDeregDate='" +
			getOrgCompanyGstDeregDate() +
			"'" +
			", orgPoTaxInclusive='" +
			getOrgPoTaxInclusive() +
			"'" +
			", orgName='" +
			getOrgName() +
			"'" +
			", orgNameOther='" +
			getOrgNameOther() +
			"'" +
			", orgShortname='" +
			getOrgShortname() +
			"'" +
			", orgAddress='" +
			getOrgAddress() +
			"'" +
			", orgShippingAddress='" +
			getOrgShippingAddress() +
			"'" +
			", orgBillingAddress='" +
			getOrgBillingAddress() +
			"'" +
			", orgPostcode='" +
			getOrgPostcode() +
			"'" +
			", orgCity='" +
			getOrgCity() +
			"'" +
			", orgState='" +
			getOrgState() +
			"'" +
			", orgCountry='" +
			getOrgCountry() +
			"'" +
			", orgOffPhone1='" +
			getOrgOffPhone1() +
			"'" +
			", orgOffPhone2='" +
			getOrgOffPhone2() +
			"'" +
			", orgOffPhone3='" +
			getOrgOffPhone3() +
			"'" +
			", orgOffFax1='" +
			getOrgOffFax1() +
			"'" +
			", orgOffFax2='" +
			getOrgOffFax2() +
			"'" +
			", orgCredittermid=" +
			getOrgCredittermid() +
			", orgCreditLimit=" +
			getOrgCreditLimit() +
			", orgAgencyid=" +
			getOrgAgencyid() +
			", orgDivision='" +
			getOrgDivision() +
			"'" +
			", orgDistrict='" +
			getOrgDistrict() +
			"'" +
			", orgWebsite='" +
			getOrgWebsite() +
			"'" +
			", orgEmail='" +
			getOrgEmail() +
			"'" +
			", orgSupplierCategory='" +
			getOrgSupplierCategory() +
			"'" +
			", orgCurrencyCode='" +
			getOrgCurrencyCode() +
			"'" +
			", orgType='" +
			getOrgType() +
			"'" +
			", orgSectorid=" +
			getOrgSectorid() +
			", orgSector='" +
			getOrgSector() +
			"'" +
			", orgIndustry='" +
			getOrgIndustry() +
			"'" +
			", orgSainsGroup='" +
			getOrgSainsGroup() +
			"'" +
			", orgBumiputra='" +
			getOrgBumiputra() +
			"'" +
			", orgUpkReg='" +
			getOrgUpkReg() +
			"'" +
			", orgMofReg='" +
			getOrgMofReg() +
			"'" +
			", orgDesignation='" +
			getOrgDesignation() +
			"'" +
			", orgContpersonTitle='" +
			getOrgContpersonTitle() +
			"'" +
			", orgContperson='" +
			getOrgContperson() +
			"'" +
			", orgDirectline='" +
			getOrgDirectline() +
			"'" +
			", orgContpEmail='" +
			getOrgContpEmail() +
			"'" +
			", orgContpHp='" +
			getOrgContpHp() +
			"'" +
			", orgRemarks='" +
			getOrgRemarks() +
			"'" +
			", orgActiveStatus='" +
			getOrgActiveStatus() +
			"'" +
			", orgCcGc='" +
			getOrgCcGc() +
			"'" +
			", orgCustomerCateCode='" +
			getOrgCustomerCateCode() +
			"'" +
			", orgVendorCateCode='" +
			getOrgVendorCateCode() +
			"'" +
			", orgSalesCateCode='" +
			getOrgSalesCateCode() +
			"'" +
			", orgOutstandingBalance=" +
			getOrgOutstandingBalance() +
			", orgOutstandingBalanceEx=" +
			getOrgOutstandingBalanceEx() +
			", orgCompanyCode='" +
			getOrgCompanyCode() +
			"'" +
			", orgDcrownPostStatus='" +
			getOrgDcrownPostStatus() +
			"'" +
			", confirmedBy=" +
			getConfirmedBy() +
			", confirmedDate='" +
			getConfirmedDate() +
			"'" +
			", enteredBy=" +
			getEnteredBy() +
			", enteredDate='" +
			getEnteredDate() +
			"'" +
			", modifiedBy=" +
			getModifiedBy() +
			", modifiedDate='" +
			getModifiedDate() +
			"'" +
			"}"
		);
	}
}
