package com.jhipster.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TOrganization.
 */
@Entity
@Table(name = "t_organization")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TOrganization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "org_hqid")
    private Integer orgHqid;

    @Column(name = "org_hq_br")
    private String orgHqBr;

    @Column(name = "org_code")
    private String orgCode;

    @Column(name = "org_brn")
    private String orgBrn;

    @Column(name = "org_ptaxid")
    private Long orgPtaxid;

    @Column(name = "org_default_tax_code")
    private String orgDefaultTaxCode;

    @Column(name = "org_company_gst_no")
    private String orgCompanyGstNo;

    @Column(name = "org_company_gst_reg_date")
    private LocalDate orgCompanyGstRegDate;

    @Column(name = "org_company_gst_dereg_date")
    private LocalDate orgCompanyGstDeregDate;

    @Column(name = "org_po_tax_inclusive")
    private String orgPoTaxInclusive;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "org_name_other")
    private String orgNameOther;

    @Column(name = "org_shortname")
    private String orgShortname;

    @Column(name = "org_address")
    private String orgAddress;

    @Column(name = "org_shipping_address")
    private String orgShippingAddress;

    @Column(name = "org_billing_address")
    private String orgBillingAddress;

    @Column(name = "org_postcode")
    private String orgPostcode;

    @Column(name = "org_city")
    private String orgCity;

    @Column(name = "org_state")
    private String orgState;

    @Column(name = "org_country")
    private String orgCountry;

    @Column(name = "org_off_phone_1")
    private String orgOffPhone1;

    @Column(name = "org_off_phone_2")
    private String orgOffPhone2;

    @Column(name = "org_off_phone_3")
    private String orgOffPhone3;

    @Column(name = "org_off_fax_1")
    private String orgOffFax1;

    @Column(name = "org_off_fax_2")
    private String orgOffFax2;

    @Column(name = "org_credittermid")
    private Long orgCredittermid;

    @Column(name = "org_credit_limit", precision = 21, scale = 2)
    private BigDecimal orgCreditLimit;

    @Column(name = "org_agencyid")
    private Long orgAgencyid;

    @Column(name = "org_division")
    private String orgDivision;

    @Column(name = "org_district")
    private String orgDistrict;

    @Column(name = "org_website")
    private String orgWebsite;

    @Column(name = "org_email")
    private String orgEmail;

    @Column(name = "org_supplier_category")
    private String orgSupplierCategory;

    @Column(name = "org_currency_code")
    private String orgCurrencyCode;

    @Column(name = "org_type")
    private String orgType;

    @Column(name = "org_sectorid")
    private Long orgSectorid;

    @Column(name = "org_sector")
    private String orgSector;

    @Column(name = "org_industry")
    private String orgIndustry;

    @Column(name = "org_sains_group")
    private String orgSainsGroup;

    @Column(name = "org_bumiputra")
    private String orgBumiputra;

    @Column(name = "org_upk_reg")
    private String orgUpkReg;

    @Column(name = "org_mof_reg")
    private String orgMofReg;

    @Column(name = "org_designation")
    private String orgDesignation;

    @Column(name = "org_contperson_title")
    private String orgContpersonTitle;

    @Column(name = "org_contperson")
    private String orgContperson;

    @Column(name = "org_directline")
    private String orgDirectline;

    @Column(name = "org_contp_email")
    private String orgContpEmail;

    @Column(name = "org_contp_hp")
    private String orgContpHp;

    @Column(name = "org_remarks")
    private String orgRemarks;

    @Column(name = "org_active_status")
    private String orgActiveStatus;

    @Column(name = "org_cc_gc")
    private String orgCcGc;

    @Column(name = "org_customer_cate_code")
    private String orgCustomerCateCode;

    @Column(name = "org_vendor_cate_code")
    private String orgVendorCateCode;

    @Column(name = "org_sales_cate_code")
    private String orgSalesCateCode;

    @Column(name = "org_outstanding_balance", precision = 21, scale = 2)
    private BigDecimal orgOutstandingBalance;

    @Column(name = "org_outstanding_balance_ex", precision = 21, scale = 2)
    private BigDecimal orgOutstandingBalanceEx;

    @Column(name = "org_company_code")
    private String orgCompanyCode;

    @Column(name = "org_dcrown_post_status")
    private String orgDcrownPostStatus;

    @Column(name = "confirmed_by")
    private Integer confirmedBy;

    @Column(name = "confirmed_date")
    private ZonedDateTime confirmedDate;

    @Column(name = "entered_by")
    private Integer enteredBy;

    @Column(name = "entered_date")
    private ZonedDateTime enteredDate;

    @Column(name = "modified_by")
    private Integer modifiedBy;

    @Column(name = "modified_date")
    private ZonedDateTime modifiedDate;

    @OneToMany(mappedBy = "tOrganization")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "tOrganization" }, allowSetters = true)
    private Set<TOrgContactPerson> tOrgContactPeople = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TOrganization id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrgHqid() {
        return this.orgHqid;
    }

    public TOrganization orgHqid(Integer orgHqid) {
        this.setOrgHqid(orgHqid);
        return this;
    }

    public void setOrgHqid(Integer orgHqid) {
        this.orgHqid = orgHqid;
    }

    public String getOrgHqBr() {
        return this.orgHqBr;
    }

    public TOrganization orgHqBr(String orgHqBr) {
        this.setOrgHqBr(orgHqBr);
        return this;
    }

    public void setOrgHqBr(String orgHqBr) {
        this.orgHqBr = orgHqBr;
    }

    public String getOrgCode() {
        return this.orgCode;
    }

    public TOrganization orgCode(String orgCode) {
        this.setOrgCode(orgCode);
        return this;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgBrn() {
        return this.orgBrn;
    }

    public TOrganization orgBrn(String orgBrn) {
        this.setOrgBrn(orgBrn);
        return this;
    }

    public void setOrgBrn(String orgBrn) {
        this.orgBrn = orgBrn;
    }

    public Long getOrgPtaxid() {
        return this.orgPtaxid;
    }

    public TOrganization orgPtaxid(Long orgPtaxid) {
        this.setOrgPtaxid(orgPtaxid);
        return this;
    }

    public void setOrgPtaxid(Long orgPtaxid) {
        this.orgPtaxid = orgPtaxid;
    }

    public String getOrgDefaultTaxCode() {
        return this.orgDefaultTaxCode;
    }

    public TOrganization orgDefaultTaxCode(String orgDefaultTaxCode) {
        this.setOrgDefaultTaxCode(orgDefaultTaxCode);
        return this;
    }

    public void setOrgDefaultTaxCode(String orgDefaultTaxCode) {
        this.orgDefaultTaxCode = orgDefaultTaxCode;
    }

    public String getOrgCompanyGstNo() {
        return this.orgCompanyGstNo;
    }

    public TOrganization orgCompanyGstNo(String orgCompanyGstNo) {
        this.setOrgCompanyGstNo(orgCompanyGstNo);
        return this;
    }

    public void setOrgCompanyGstNo(String orgCompanyGstNo) {
        this.orgCompanyGstNo = orgCompanyGstNo;
    }

    public LocalDate getOrgCompanyGstRegDate() {
        return this.orgCompanyGstRegDate;
    }

    public TOrganization orgCompanyGstRegDate(LocalDate orgCompanyGstRegDate) {
        this.setOrgCompanyGstRegDate(orgCompanyGstRegDate);
        return this;
    }

    public void setOrgCompanyGstRegDate(LocalDate orgCompanyGstRegDate) {
        this.orgCompanyGstRegDate = orgCompanyGstRegDate;
    }

    public LocalDate getOrgCompanyGstDeregDate() {
        return this.orgCompanyGstDeregDate;
    }

    public TOrganization orgCompanyGstDeregDate(LocalDate orgCompanyGstDeregDate) {
        this.setOrgCompanyGstDeregDate(orgCompanyGstDeregDate);
        return this;
    }

    public void setOrgCompanyGstDeregDate(LocalDate orgCompanyGstDeregDate) {
        this.orgCompanyGstDeregDate = orgCompanyGstDeregDate;
    }

    public String getOrgPoTaxInclusive() {
        return this.orgPoTaxInclusive;
    }

    public TOrganization orgPoTaxInclusive(String orgPoTaxInclusive) {
        this.setOrgPoTaxInclusive(orgPoTaxInclusive);
        return this;
    }

    public void setOrgPoTaxInclusive(String orgPoTaxInclusive) {
        this.orgPoTaxInclusive = orgPoTaxInclusive;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public TOrganization orgName(String orgName) {
        this.setOrgName(orgName);
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgNameOther() {
        return this.orgNameOther;
    }

    public TOrganization orgNameOther(String orgNameOther) {
        this.setOrgNameOther(orgNameOther);
        return this;
    }

    public void setOrgNameOther(String orgNameOther) {
        this.orgNameOther = orgNameOther;
    }

    public String getOrgShortname() {
        return this.orgShortname;
    }

    public TOrganization orgShortname(String orgShortname) {
        this.setOrgShortname(orgShortname);
        return this;
    }

    public void setOrgShortname(String orgShortname) {
        this.orgShortname = orgShortname;
    }

    public String getOrgAddress() {
        return this.orgAddress;
    }

    public TOrganization orgAddress(String orgAddress) {
        this.setOrgAddress(orgAddress);
        return this;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgShippingAddress() {
        return this.orgShippingAddress;
    }

    public TOrganization orgShippingAddress(String orgShippingAddress) {
        this.setOrgShippingAddress(orgShippingAddress);
        return this;
    }

    public void setOrgShippingAddress(String orgShippingAddress) {
        this.orgShippingAddress = orgShippingAddress;
    }

    public String getOrgBillingAddress() {
        return this.orgBillingAddress;
    }

    public TOrganization orgBillingAddress(String orgBillingAddress) {
        this.setOrgBillingAddress(orgBillingAddress);
        return this;
    }

    public void setOrgBillingAddress(String orgBillingAddress) {
        this.orgBillingAddress = orgBillingAddress;
    }

    public String getOrgPostcode() {
        return this.orgPostcode;
    }

    public TOrganization orgPostcode(String orgPostcode) {
        this.setOrgPostcode(orgPostcode);
        return this;
    }

    public void setOrgPostcode(String orgPostcode) {
        this.orgPostcode = orgPostcode;
    }

    public String getOrgCity() {
        return this.orgCity;
    }

    public TOrganization orgCity(String orgCity) {
        this.setOrgCity(orgCity);
        return this;
    }

    public void setOrgCity(String orgCity) {
        this.orgCity = orgCity;
    }

    public String getOrgState() {
        return this.orgState;
    }

    public TOrganization orgState(String orgState) {
        this.setOrgState(orgState);
        return this;
    }

    public void setOrgState(String orgState) {
        this.orgState = orgState;
    }

    public String getOrgCountry() {
        return this.orgCountry;
    }

    public TOrganization orgCountry(String orgCountry) {
        this.setOrgCountry(orgCountry);
        return this;
    }

    public void setOrgCountry(String orgCountry) {
        this.orgCountry = orgCountry;
    }

    public String getOrgOffPhone1() {
        return this.orgOffPhone1;
    }

    public TOrganization orgOffPhone1(String orgOffPhone1) {
        this.setOrgOffPhone1(orgOffPhone1);
        return this;
    }

    public void setOrgOffPhone1(String orgOffPhone1) {
        this.orgOffPhone1 = orgOffPhone1;
    }

    public String getOrgOffPhone2() {
        return this.orgOffPhone2;
    }

    public TOrganization orgOffPhone2(String orgOffPhone2) {
        this.setOrgOffPhone2(orgOffPhone2);
        return this;
    }

    public void setOrgOffPhone2(String orgOffPhone2) {
        this.orgOffPhone2 = orgOffPhone2;
    }

    public String getOrgOffPhone3() {
        return this.orgOffPhone3;
    }

    public TOrganization orgOffPhone3(String orgOffPhone3) {
        this.setOrgOffPhone3(orgOffPhone3);
        return this;
    }

    public void setOrgOffPhone3(String orgOffPhone3) {
        this.orgOffPhone3 = orgOffPhone3;
    }

    public String getOrgOffFax1() {
        return this.orgOffFax1;
    }

    public TOrganization orgOffFax1(String orgOffFax1) {
        this.setOrgOffFax1(orgOffFax1);
        return this;
    }

    public void setOrgOffFax1(String orgOffFax1) {
        this.orgOffFax1 = orgOffFax1;
    }

    public String getOrgOffFax2() {
        return this.orgOffFax2;
    }

    public TOrganization orgOffFax2(String orgOffFax2) {
        this.setOrgOffFax2(orgOffFax2);
        return this;
    }

    public void setOrgOffFax2(String orgOffFax2) {
        this.orgOffFax2 = orgOffFax2;
    }

    public Long getOrgCredittermid() {
        return this.orgCredittermid;
    }

    public TOrganization orgCredittermid(Long orgCredittermid) {
        this.setOrgCredittermid(orgCredittermid);
        return this;
    }

    public void setOrgCredittermid(Long orgCredittermid) {
        this.orgCredittermid = orgCredittermid;
    }

    public BigDecimal getOrgCreditLimit() {
        return this.orgCreditLimit;
    }

    public TOrganization orgCreditLimit(BigDecimal orgCreditLimit) {
        this.setOrgCreditLimit(orgCreditLimit);
        return this;
    }

    public void setOrgCreditLimit(BigDecimal orgCreditLimit) {
        this.orgCreditLimit = orgCreditLimit;
    }

    public Long getOrgAgencyid() {
        return this.orgAgencyid;
    }

    public TOrganization orgAgencyid(Long orgAgencyid) {
        this.setOrgAgencyid(orgAgencyid);
        return this;
    }

    public void setOrgAgencyid(Long orgAgencyid) {
        this.orgAgencyid = orgAgencyid;
    }

    public String getOrgDivision() {
        return this.orgDivision;
    }

    public TOrganization orgDivision(String orgDivision) {
        this.setOrgDivision(orgDivision);
        return this;
    }

    public void setOrgDivision(String orgDivision) {
        this.orgDivision = orgDivision;
    }

    public String getOrgDistrict() {
        return this.orgDistrict;
    }

    public TOrganization orgDistrict(String orgDistrict) {
        this.setOrgDistrict(orgDistrict);
        return this;
    }

    public void setOrgDistrict(String orgDistrict) {
        this.orgDistrict = orgDistrict;
    }

    public String getOrgWebsite() {
        return this.orgWebsite;
    }

    public TOrganization orgWebsite(String orgWebsite) {
        this.setOrgWebsite(orgWebsite);
        return this;
    }

    public void setOrgWebsite(String orgWebsite) {
        this.orgWebsite = orgWebsite;
    }

    public String getOrgEmail() {
        return this.orgEmail;
    }

    public TOrganization orgEmail(String orgEmail) {
        this.setOrgEmail(orgEmail);
        return this;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public String getOrgSupplierCategory() {
        return this.orgSupplierCategory;
    }

    public TOrganization orgSupplierCategory(String orgSupplierCategory) {
        this.setOrgSupplierCategory(orgSupplierCategory);
        return this;
    }

    public void setOrgSupplierCategory(String orgSupplierCategory) {
        this.orgSupplierCategory = orgSupplierCategory;
    }

    public String getOrgCurrencyCode() {
        return this.orgCurrencyCode;
    }

    public TOrganization orgCurrencyCode(String orgCurrencyCode) {
        this.setOrgCurrencyCode(orgCurrencyCode);
        return this;
    }

    public void setOrgCurrencyCode(String orgCurrencyCode) {
        this.orgCurrencyCode = orgCurrencyCode;
    }

    public String getOrgType() {
        return this.orgType;
    }

    public TOrganization orgType(String orgType) {
        this.setOrgType(orgType);
        return this;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public Long getOrgSectorid() {
        return this.orgSectorid;
    }

    public TOrganization orgSectorid(Long orgSectorid) {
        this.setOrgSectorid(orgSectorid);
        return this;
    }

    public void setOrgSectorid(Long orgSectorid) {
        this.orgSectorid = orgSectorid;
    }

    public String getOrgSector() {
        return this.orgSector;
    }

    public TOrganization orgSector(String orgSector) {
        this.setOrgSector(orgSector);
        return this;
    }

    public void setOrgSector(String orgSector) {
        this.orgSector = orgSector;
    }

    public String getOrgIndustry() {
        return this.orgIndustry;
    }

    public TOrganization orgIndustry(String orgIndustry) {
        this.setOrgIndustry(orgIndustry);
        return this;
    }

    public void setOrgIndustry(String orgIndustry) {
        this.orgIndustry = orgIndustry;
    }

    public String getOrgSainsGroup() {
        return this.orgSainsGroup;
    }

    public TOrganization orgSainsGroup(String orgSainsGroup) {
        this.setOrgSainsGroup(orgSainsGroup);
        return this;
    }

    public void setOrgSainsGroup(String orgSainsGroup) {
        this.orgSainsGroup = orgSainsGroup;
    }

    public String getOrgBumiputra() {
        return this.orgBumiputra;
    }

    public TOrganization orgBumiputra(String orgBumiputra) {
        this.setOrgBumiputra(orgBumiputra);
        return this;
    }

    public void setOrgBumiputra(String orgBumiputra) {
        this.orgBumiputra = orgBumiputra;
    }

    public String getOrgUpkReg() {
        return this.orgUpkReg;
    }

    public TOrganization orgUpkReg(String orgUpkReg) {
        this.setOrgUpkReg(orgUpkReg);
        return this;
    }

    public void setOrgUpkReg(String orgUpkReg) {
        this.orgUpkReg = orgUpkReg;
    }

    public String getOrgMofReg() {
        return this.orgMofReg;
    }

    public TOrganization orgMofReg(String orgMofReg) {
        this.setOrgMofReg(orgMofReg);
        return this;
    }

    public void setOrgMofReg(String orgMofReg) {
        this.orgMofReg = orgMofReg;
    }

    public String getOrgDesignation() {
        return this.orgDesignation;
    }

    public TOrganization orgDesignation(String orgDesignation) {
        this.setOrgDesignation(orgDesignation);
        return this;
    }

    public void setOrgDesignation(String orgDesignation) {
        this.orgDesignation = orgDesignation;
    }

    public String getOrgContpersonTitle() {
        return this.orgContpersonTitle;
    }

    public TOrganization orgContpersonTitle(String orgContpersonTitle) {
        this.setOrgContpersonTitle(orgContpersonTitle);
        return this;
    }

    public void setOrgContpersonTitle(String orgContpersonTitle) {
        this.orgContpersonTitle = orgContpersonTitle;
    }

    public String getOrgContperson() {
        return this.orgContperson;
    }

    public TOrganization orgContperson(String orgContperson) {
        this.setOrgContperson(orgContperson);
        return this;
    }

    public void setOrgContperson(String orgContperson) {
        this.orgContperson = orgContperson;
    }

    public String getOrgDirectline() {
        return this.orgDirectline;
    }

    public TOrganization orgDirectline(String orgDirectline) {
        this.setOrgDirectline(orgDirectline);
        return this;
    }

    public void setOrgDirectline(String orgDirectline) {
        this.orgDirectline = orgDirectline;
    }

    public String getOrgContpEmail() {
        return this.orgContpEmail;
    }

    public TOrganization orgContpEmail(String orgContpEmail) {
        this.setOrgContpEmail(orgContpEmail);
        return this;
    }

    public void setOrgContpEmail(String orgContpEmail) {
        this.orgContpEmail = orgContpEmail;
    }

    public String getOrgContpHp() {
        return this.orgContpHp;
    }

    public TOrganization orgContpHp(String orgContpHp) {
        this.setOrgContpHp(orgContpHp);
        return this;
    }

    public void setOrgContpHp(String orgContpHp) {
        this.orgContpHp = orgContpHp;
    }

    public String getOrgRemarks() {
        return this.orgRemarks;
    }

    public TOrganization orgRemarks(String orgRemarks) {
        this.setOrgRemarks(orgRemarks);
        return this;
    }

    public void setOrgRemarks(String orgRemarks) {
        this.orgRemarks = orgRemarks;
    }

    public String getOrgActiveStatus() {
        return this.orgActiveStatus;
    }

    public TOrganization orgActiveStatus(String orgActiveStatus) {
        this.setOrgActiveStatus(orgActiveStatus);
        return this;
    }

    public void setOrgActiveStatus(String orgActiveStatus) {
        this.orgActiveStatus = orgActiveStatus;
    }

    public String getOrgCcGc() {
        return this.orgCcGc;
    }

    public TOrganization orgCcGc(String orgCcGc) {
        this.setOrgCcGc(orgCcGc);
        return this;
    }

    public void setOrgCcGc(String orgCcGc) {
        this.orgCcGc = orgCcGc;
    }

    public String getOrgCustomerCateCode() {
        return this.orgCustomerCateCode;
    }

    public TOrganization orgCustomerCateCode(String orgCustomerCateCode) {
        this.setOrgCustomerCateCode(orgCustomerCateCode);
        return this;
    }

    public void setOrgCustomerCateCode(String orgCustomerCateCode) {
        this.orgCustomerCateCode = orgCustomerCateCode;
    }

    public String getOrgVendorCateCode() {
        return this.orgVendorCateCode;
    }

    public TOrganization orgVendorCateCode(String orgVendorCateCode) {
        this.setOrgVendorCateCode(orgVendorCateCode);
        return this;
    }

    public void setOrgVendorCateCode(String orgVendorCateCode) {
        this.orgVendorCateCode = orgVendorCateCode;
    }

    public String getOrgSalesCateCode() {
        return this.orgSalesCateCode;
    }

    public TOrganization orgSalesCateCode(String orgSalesCateCode) {
        this.setOrgSalesCateCode(orgSalesCateCode);
        return this;
    }

    public void setOrgSalesCateCode(String orgSalesCateCode) {
        this.orgSalesCateCode = orgSalesCateCode;
    }

    public BigDecimal getOrgOutstandingBalance() {
        return this.orgOutstandingBalance;
    }

    public TOrganization orgOutstandingBalance(BigDecimal orgOutstandingBalance) {
        this.setOrgOutstandingBalance(orgOutstandingBalance);
        return this;
    }

    public void setOrgOutstandingBalance(BigDecimal orgOutstandingBalance) {
        this.orgOutstandingBalance = orgOutstandingBalance;
    }

    public BigDecimal getOrgOutstandingBalanceEx() {
        return this.orgOutstandingBalanceEx;
    }

    public TOrganization orgOutstandingBalanceEx(BigDecimal orgOutstandingBalanceEx) {
        this.setOrgOutstandingBalanceEx(orgOutstandingBalanceEx);
        return this;
    }

    public void setOrgOutstandingBalanceEx(BigDecimal orgOutstandingBalanceEx) {
        this.orgOutstandingBalanceEx = orgOutstandingBalanceEx;
    }

    public String getOrgCompanyCode() {
        return this.orgCompanyCode;
    }

    public TOrganization orgCompanyCode(String orgCompanyCode) {
        this.setOrgCompanyCode(orgCompanyCode);
        return this;
    }

    public void setOrgCompanyCode(String orgCompanyCode) {
        this.orgCompanyCode = orgCompanyCode;
    }

    public String getOrgDcrownPostStatus() {
        return this.orgDcrownPostStatus;
    }

    public TOrganization orgDcrownPostStatus(String orgDcrownPostStatus) {
        this.setOrgDcrownPostStatus(orgDcrownPostStatus);
        return this;
    }

    public void setOrgDcrownPostStatus(String orgDcrownPostStatus) {
        this.orgDcrownPostStatus = orgDcrownPostStatus;
    }

    public Integer getConfirmedBy() {
        return this.confirmedBy;
    }

    public TOrganization confirmedBy(Integer confirmedBy) {
        this.setConfirmedBy(confirmedBy);
        return this;
    }

    public void setConfirmedBy(Integer confirmedBy) {
        this.confirmedBy = confirmedBy;
    }

    public ZonedDateTime getConfirmedDate() {
        return this.confirmedDate;
    }

    public TOrganization confirmedDate(ZonedDateTime confirmedDate) {
        this.setConfirmedDate(confirmedDate);
        return this;
    }

    public void setConfirmedDate(ZonedDateTime confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    public Integer getEnteredBy() {
        return this.enteredBy;
    }

    public TOrganization enteredBy(Integer enteredBy) {
        this.setEnteredBy(enteredBy);
        return this;
    }

    public void setEnteredBy(Integer enteredBy) {
        this.enteredBy = enteredBy;
    }

    public ZonedDateTime getEnteredDate() {
        return this.enteredDate;
    }

    public TOrganization enteredDate(ZonedDateTime enteredDate) {
        this.setEnteredDate(enteredDate);
        return this;
    }

    public void setEnteredDate(ZonedDateTime enteredDate) {
        this.enteredDate = enteredDate;
    }

    public Integer getModifiedBy() {
        return this.modifiedBy;
    }

    public TOrganization modifiedBy(Integer modifiedBy) {
        this.setModifiedBy(modifiedBy);
        return this;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public ZonedDateTime getModifiedDate() {
        return this.modifiedDate;
    }

    public TOrganization modifiedDate(ZonedDateTime modifiedDate) {
        this.setModifiedDate(modifiedDate);
        return this;
    }

    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<TOrgContactPerson> getTOrgContactPeople() {
        return this.tOrgContactPeople;
    }

    public void setTOrgContactPeople(Set<TOrgContactPerson> tOrgContactPeople) {
        if (this.tOrgContactPeople != null) {
            this.tOrgContactPeople.forEach(i -> i.setTOrganization(null));
        }
        if (tOrgContactPeople != null) {
            tOrgContactPeople.forEach(i -> i.setTOrganization(this));
        }
        this.tOrgContactPeople = tOrgContactPeople;
    }

    public TOrganization tOrgContactPeople(Set<TOrgContactPerson> tOrgContactPeople) {
        this.setTOrgContactPeople(tOrgContactPeople);
        return this;
    }

    public TOrganization addTOrgContactPerson(TOrgContactPerson tOrgContactPerson) {
        this.tOrgContactPeople.add(tOrgContactPerson);
        tOrgContactPerson.setTOrganization(this);
        return this;
    }

    public TOrganization removeTOrgContactPerson(TOrgContactPerson tOrgContactPerson) {
        this.tOrgContactPeople.remove(tOrgContactPerson);
        tOrgContactPerson.setTOrganization(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TOrganization)) {
            return false;
        }
        return id != null && id.equals(((TOrganization) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TOrganization{" +
            "id=" + getId() +
            ", orgHqid=" + getOrgHqid() +
            ", orgHqBr='" + getOrgHqBr() + "'" +
            ", orgCode='" + getOrgCode() + "'" +
            ", orgBrn='" + getOrgBrn() + "'" +
            ", orgPtaxid=" + getOrgPtaxid() +
            ", orgDefaultTaxCode='" + getOrgDefaultTaxCode() + "'" +
            ", orgCompanyGstNo='" + getOrgCompanyGstNo() + "'" +
            ", orgCompanyGstRegDate='" + getOrgCompanyGstRegDate() + "'" +
            ", orgCompanyGstDeregDate='" + getOrgCompanyGstDeregDate() + "'" +
            ", orgPoTaxInclusive='" + getOrgPoTaxInclusive() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", orgNameOther='" + getOrgNameOther() + "'" +
            ", orgShortname='" + getOrgShortname() + "'" +
            ", orgAddress='" + getOrgAddress() + "'" +
            ", orgShippingAddress='" + getOrgShippingAddress() + "'" +
            ", orgBillingAddress='" + getOrgBillingAddress() + "'" +
            ", orgPostcode='" + getOrgPostcode() + "'" +
            ", orgCity='" + getOrgCity() + "'" +
            ", orgState='" + getOrgState() + "'" +
            ", orgCountry='" + getOrgCountry() + "'" +
            ", orgOffPhone1='" + getOrgOffPhone1() + "'" +
            ", orgOffPhone2='" + getOrgOffPhone2() + "'" +
            ", orgOffPhone3='" + getOrgOffPhone3() + "'" +
            ", orgOffFax1='" + getOrgOffFax1() + "'" +
            ", orgOffFax2='" + getOrgOffFax2() + "'" +
            ", orgCredittermid=" + getOrgCredittermid() +
            ", orgCreditLimit=" + getOrgCreditLimit() +
            ", orgAgencyid=" + getOrgAgencyid() +
            ", orgDivision='" + getOrgDivision() + "'" +
            ", orgDistrict='" + getOrgDistrict() + "'" +
            ", orgWebsite='" + getOrgWebsite() + "'" +
            ", orgEmail='" + getOrgEmail() + "'" +
            ", orgSupplierCategory='" + getOrgSupplierCategory() + "'" +
            ", orgCurrencyCode='" + getOrgCurrencyCode() + "'" +
            ", orgType='" + getOrgType() + "'" +
            ", orgSectorid=" + getOrgSectorid() +
            ", orgSector='" + getOrgSector() + "'" +
            ", orgIndustry='" + getOrgIndustry() + "'" +
            ", orgSainsGroup='" + getOrgSainsGroup() + "'" +
            ", orgBumiputra='" + getOrgBumiputra() + "'" +
            ", orgUpkReg='" + getOrgUpkReg() + "'" +
            ", orgMofReg='" + getOrgMofReg() + "'" +
            ", orgDesignation='" + getOrgDesignation() + "'" +
            ", orgContpersonTitle='" + getOrgContpersonTitle() + "'" +
            ", orgContperson='" + getOrgContperson() + "'" +
            ", orgDirectline='" + getOrgDirectline() + "'" +
            ", orgContpEmail='" + getOrgContpEmail() + "'" +
            ", orgContpHp='" + getOrgContpHp() + "'" +
            ", orgRemarks='" + getOrgRemarks() + "'" +
            ", orgActiveStatus='" + getOrgActiveStatus() + "'" +
            ", orgCcGc='" + getOrgCcGc() + "'" +
            ", orgCustomerCateCode='" + getOrgCustomerCateCode() + "'" +
            ", orgVendorCateCode='" + getOrgVendorCateCode() + "'" +
            ", orgSalesCateCode='" + getOrgSalesCateCode() + "'" +
            ", orgOutstandingBalance=" + getOrgOutstandingBalance() +
            ", orgOutstandingBalanceEx=" + getOrgOutstandingBalanceEx() +
            ", orgCompanyCode='" + getOrgCompanyCode() + "'" +
            ", orgDcrownPostStatus='" + getOrgDcrownPostStatus() + "'" +
            ", confirmedBy=" + getConfirmedBy() +
            ", confirmedDate='" + getConfirmedDate() + "'" +
            ", enteredBy=" + getEnteredBy() +
            ", enteredDate='" + getEnteredDate() + "'" +
            ", modifiedBy=" + getModifiedBy() +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
