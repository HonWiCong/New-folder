package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static com.jhipster.app.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TOrganization;
import com.jhipster.app.repository.TOrganizationRepository;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TOrganizationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TOrganizationResourceIT {

	private static final Integer DEFAULT_ORG_HQID = 1;
	private static final Integer UPDATED_ORG_HQID = 2;

	private static final String DEFAULT_ORG_HQ_BR = "AAAAAAAAAA";
	private static final String UPDATED_ORG_HQ_BR = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_CODE = "AAAAAAAAAA";
	private static final String UPDATED_ORG_CODE = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_BRN = "AAAAAAAAAA";
	private static final String UPDATED_ORG_BRN = "BBBBBBBBBB";

	private static final Long DEFAULT_ORG_PTAXID = 1L;
	private static final Long UPDATED_ORG_PTAXID = 2L;

	private static final String DEFAULT_ORG_DEFAULT_TAX_CODE = "AAAAAAAAAA";
	private static final String UPDATED_ORG_DEFAULT_TAX_CODE = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_COMPANY_GST_NO = "AAAAAAAAAA";
	private static final String UPDATED_ORG_COMPANY_GST_NO = "BBBBBBBBBB";

	private static final LocalDate DEFAULT_ORG_COMPANY_GST_REG_DATE = LocalDate.ofEpochDay(0L);
	private static final LocalDate UPDATED_ORG_COMPANY_GST_REG_DATE = LocalDate.now(ZoneId.systemDefault());

	private static final LocalDate DEFAULT_ORG_COMPANY_GST_DEREG_DATE = LocalDate.ofEpochDay(0L);
	private static final LocalDate UPDATED_ORG_COMPANY_GST_DEREG_DATE = LocalDate.now(ZoneId.systemDefault());

	private static final String DEFAULT_ORG_PO_TAX_INCLUSIVE = "AAAAAAAAAA";
	private static final String UPDATED_ORG_PO_TAX_INCLUSIVE = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_NAME = "AAAAAAAAAA";
	private static final String UPDATED_ORG_NAME = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_NAME_OTHER = "AAAAAAAAAA";
	private static final String UPDATED_ORG_NAME_OTHER = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_SHORTNAME = "AAAAAAAAAA";
	private static final String UPDATED_ORG_SHORTNAME = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_ADDRESS = "AAAAAAAAAA";
	private static final String UPDATED_ORG_ADDRESS = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_SHIPPING_ADDRESS = "AAAAAAAAAA";
	private static final String UPDATED_ORG_SHIPPING_ADDRESS = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_BILLING_ADDRESS = "AAAAAAAAAA";
	private static final String UPDATED_ORG_BILLING_ADDRESS = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_POSTCODE = "AAAAAAAAAA";
	private static final String UPDATED_ORG_POSTCODE = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_CITY = "AAAAAAAAAA";
	private static final String UPDATED_ORG_CITY = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_STATE = "AAAAAAAAAA";
	private static final String UPDATED_ORG_STATE = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_COUNTRY = "AAAAAAAAAA";
	private static final String UPDATED_ORG_COUNTRY = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_OFF_PHONE_1 = "AAAAAAAAAA";
	private static final String UPDATED_ORG_OFF_PHONE_1 = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_OFF_PHONE_2 = "AAAAAAAAAA";
	private static final String UPDATED_ORG_OFF_PHONE_2 = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_OFF_PHONE_3 = "AAAAAAAAAA";
	private static final String UPDATED_ORG_OFF_PHONE_3 = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_OFF_FAX_1 = "AAAAAAAAAA";
	private static final String UPDATED_ORG_OFF_FAX_1 = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_OFF_FAX_2 = "AAAAAAAAAA";
	private static final String UPDATED_ORG_OFF_FAX_2 = "BBBBBBBBBB";

	private static final Long DEFAULT_ORG_CREDITTERMID = 1L;
	private static final Long UPDATED_ORG_CREDITTERMID = 2L;

	private static final BigDecimal DEFAULT_ORG_CREDIT_LIMIT = new BigDecimal(1);
	private static final BigDecimal UPDATED_ORG_CREDIT_LIMIT = new BigDecimal(2);

	private static final Long DEFAULT_ORG_AGENCYID = 1L;
	private static final Long UPDATED_ORG_AGENCYID = 2L;

	private static final String DEFAULT_ORG_DIVISION = "AAAAAAAAAA";
	private static final String UPDATED_ORG_DIVISION = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_DISTRICT = "AAAAAAAAAA";
	private static final String UPDATED_ORG_DISTRICT = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_WEBSITE = "AAAAAAAAAA";
	private static final String UPDATED_ORG_WEBSITE = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_EMAIL = "AAAAAAAAAA";
	private static final String UPDATED_ORG_EMAIL = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_SUPPLIER_CATEGORY = "AAAAAAAAAA";
	private static final String UPDATED_ORG_SUPPLIER_CATEGORY = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_CURRENCY_CODE = "AAAAAAAAAA";
	private static final String UPDATED_ORG_CURRENCY_CODE = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_TYPE = "AAAAAAAAAA";
	private static final String UPDATED_ORG_TYPE = "BBBBBBBBBB";

	private static final Long DEFAULT_ORG_SECTORID = 1L;
	private static final Long UPDATED_ORG_SECTORID = 2L;

	private static final String DEFAULT_ORG_SECTOR = "AAAAAAAAAA";
	private static final String UPDATED_ORG_SECTOR = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_INDUSTRY = "AAAAAAAAAA";
	private static final String UPDATED_ORG_INDUSTRY = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_SAINS_GROUP = "AAAAAAAAAA";
	private static final String UPDATED_ORG_SAINS_GROUP = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_BUMIPUTRA = "AAAAAAAAAA";
	private static final String UPDATED_ORG_BUMIPUTRA = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_UPK_REG = "AAAAAAAAAA";
	private static final String UPDATED_ORG_UPK_REG = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_MOF_REG = "AAAAAAAAAA";
	private static final String UPDATED_ORG_MOF_REG = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_DESIGNATION = "AAAAAAAAAA";
	private static final String UPDATED_ORG_DESIGNATION = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_CONTPERSON_TITLE = "AAAAAAAAAA";
	private static final String UPDATED_ORG_CONTPERSON_TITLE = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_CONTPERSON = "AAAAAAAAAA";
	private static final String UPDATED_ORG_CONTPERSON = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_DIRECTLINE = "AAAAAAAAAA";
	private static final String UPDATED_ORG_DIRECTLINE = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_CONTP_EMAIL = "AAAAAAAAAA";
	private static final String UPDATED_ORG_CONTP_EMAIL = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_CONTP_HP = "AAAAAAAAAA";
	private static final String UPDATED_ORG_CONTP_HP = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_REMARKS = "AAAAAAAAAA";
	private static final String UPDATED_ORG_REMARKS = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_ACTIVE_STATUS = "AAAAAAAAAA";
	private static final String UPDATED_ORG_ACTIVE_STATUS = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_CC_GC = "AAAAAAAAAA";
	private static final String UPDATED_ORG_CC_GC = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_CUSTOMER_CATE_CODE = "AAAAAAAAAA";
	private static final String UPDATED_ORG_CUSTOMER_CATE_CODE = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_VENDOR_CATE_CODE = "AAAAAAAAAA";
	private static final String UPDATED_ORG_VENDOR_CATE_CODE = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_SALES_CATE_CODE = "AAAAAAAAAA";
	private static final String UPDATED_ORG_SALES_CATE_CODE = "BBBBBBBBBB";

	private static final BigDecimal DEFAULT_ORG_OUTSTANDING_BALANCE = new BigDecimal(1);
	private static final BigDecimal UPDATED_ORG_OUTSTANDING_BALANCE = new BigDecimal(2);

	private static final BigDecimal DEFAULT_ORG_OUTSTANDING_BALANCE_EX = new BigDecimal(1);
	private static final BigDecimal UPDATED_ORG_OUTSTANDING_BALANCE_EX = new BigDecimal(2);

	private static final String DEFAULT_ORG_COMPANY_CODE = "AAAAAAAAAA";
	private static final String UPDATED_ORG_COMPANY_CODE = "BBBBBBBBBB";

	private static final String DEFAULT_ORG_DCROWN_POST_STATUS = "AAAAAAAAAA";
	private static final String UPDATED_ORG_DCROWN_POST_STATUS = "BBBBBBBBBB";

	private static final Integer DEFAULT_CONFIRMED_BY = 1;
	private static final Integer UPDATED_CONFIRMED_BY = 2;

	private static final ZonedDateTime DEFAULT_CONFIRMED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
	private static final ZonedDateTime UPDATED_CONFIRMED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

	private static final Integer DEFAULT_ENTERED_BY = 1;
	private static final Integer UPDATED_ENTERED_BY = 2;

	private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
	private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

	private static final Integer DEFAULT_MODIFIED_BY = 1;
	private static final Integer UPDATED_MODIFIED_BY = 2;

	private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
	private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

	private static final String ENTITY_API_URL = "/api/t-organizations";
	private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

	private static Random random = new Random();
	private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

	@Autowired
	private TOrganizationRepository tOrganizationRepository;

	@Autowired
	private EntityManager em;

	@Autowired
	private MockMvc restTOrganizationMockMvc;

	private TOrganization tOrganization;

	/**
	 * Create an entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TOrganization createEntity(EntityManager em) {
		TOrganization tOrganization = new TOrganization()
			.orgHqid(DEFAULT_ORG_HQID)
			.orgHqBr(DEFAULT_ORG_HQ_BR)
			.orgCode(DEFAULT_ORG_CODE)
			.orgBrn(DEFAULT_ORG_BRN)
			.orgPtaxid(DEFAULT_ORG_PTAXID)
			.orgDefaultTaxCode(DEFAULT_ORG_DEFAULT_TAX_CODE)
			.orgCompanyGstNo(DEFAULT_ORG_COMPANY_GST_NO)
			.orgCompanyGstRegDate(DEFAULT_ORG_COMPANY_GST_REG_DATE)
			.orgCompanyGstDeregDate(DEFAULT_ORG_COMPANY_GST_DEREG_DATE)
			.orgPoTaxInclusive(DEFAULT_ORG_PO_TAX_INCLUSIVE)
			.orgName(DEFAULT_ORG_NAME)
			.orgNameOther(DEFAULT_ORG_NAME_OTHER)
			.orgShortname(DEFAULT_ORG_SHORTNAME)
			.orgAddress(DEFAULT_ORG_ADDRESS)
			.orgShippingAddress(DEFAULT_ORG_SHIPPING_ADDRESS)
			.orgBillingAddress(DEFAULT_ORG_BILLING_ADDRESS)
			.orgPostcode(DEFAULT_ORG_POSTCODE)
			.orgCity(DEFAULT_ORG_CITY)
			.orgState(DEFAULT_ORG_STATE)
			.orgCountry(DEFAULT_ORG_COUNTRY)
			.orgOffPhone1(DEFAULT_ORG_OFF_PHONE_1)
			.orgOffPhone2(DEFAULT_ORG_OFF_PHONE_2)
			.orgOffPhone3(DEFAULT_ORG_OFF_PHONE_3)
			.orgOffFax1(DEFAULT_ORG_OFF_FAX_1)
			.orgOffFax2(DEFAULT_ORG_OFF_FAX_2)
			.orgCredittermid(DEFAULT_ORG_CREDITTERMID)
			.orgCreditLimit(DEFAULT_ORG_CREDIT_LIMIT)
			.orgAgencyid(DEFAULT_ORG_AGENCYID)
			.orgDivision(DEFAULT_ORG_DIVISION)
			.orgDistrict(DEFAULT_ORG_DISTRICT)
			.orgWebsite(DEFAULT_ORG_WEBSITE)
			.orgEmail(DEFAULT_ORG_EMAIL)
			.orgSupplierCategory(DEFAULT_ORG_SUPPLIER_CATEGORY)
			.orgCurrencyCode(DEFAULT_ORG_CURRENCY_CODE)
			.orgType(DEFAULT_ORG_TYPE)
			.orgSectorid(DEFAULT_ORG_SECTORID)
			.orgSector(DEFAULT_ORG_SECTOR)
			.orgIndustry(DEFAULT_ORG_INDUSTRY)
			.orgSainsGroup(DEFAULT_ORG_SAINS_GROUP)
			.orgBumiputra(DEFAULT_ORG_BUMIPUTRA)
			.orgUpkReg(DEFAULT_ORG_UPK_REG)
			.orgMofReg(DEFAULT_ORG_MOF_REG)
			.orgDesignation(DEFAULT_ORG_DESIGNATION)
			.orgContpersonTitle(DEFAULT_ORG_CONTPERSON_TITLE)
			.orgContperson(DEFAULT_ORG_CONTPERSON)
			.orgDirectline(DEFAULT_ORG_DIRECTLINE)
			.orgContpEmail(DEFAULT_ORG_CONTP_EMAIL)
			.orgContpHp(DEFAULT_ORG_CONTP_HP)
			.orgRemarks(DEFAULT_ORG_REMARKS)
			.orgActiveStatus(DEFAULT_ORG_ACTIVE_STATUS)
			.orgCcGc(DEFAULT_ORG_CC_GC)
			.orgCustomerCateCode(DEFAULT_ORG_CUSTOMER_CATE_CODE)
			.orgVendorCateCode(DEFAULT_ORG_VENDOR_CATE_CODE)
			.orgSalesCateCode(DEFAULT_ORG_SALES_CATE_CODE)
			.orgOutstandingBalance(DEFAULT_ORG_OUTSTANDING_BALANCE)
			.orgOutstandingBalanceEx(DEFAULT_ORG_OUTSTANDING_BALANCE_EX)
			.orgCompanyCode(DEFAULT_ORG_COMPANY_CODE)
			.orgDcrownPostStatus(DEFAULT_ORG_DCROWN_POST_STATUS)
			.confirmedBy(DEFAULT_CONFIRMED_BY)
			.confirmedDate(DEFAULT_CONFIRMED_DATE)
			.enteredBy(DEFAULT_ENTERED_BY)
			.enteredDate(DEFAULT_ENTERED_DATE)
			.modifiedBy(DEFAULT_MODIFIED_BY)
			.modifiedDate(DEFAULT_MODIFIED_DATE);
		return tOrganization;
	}

	/**
	 * Create an updated entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TOrganization createUpdatedEntity(EntityManager em) {
		TOrganization tOrganization = new TOrganization()
			.orgHqid(UPDATED_ORG_HQID)
			.orgHqBr(UPDATED_ORG_HQ_BR)
			.orgCode(UPDATED_ORG_CODE)
			.orgBrn(UPDATED_ORG_BRN)
			.orgPtaxid(UPDATED_ORG_PTAXID)
			.orgDefaultTaxCode(UPDATED_ORG_DEFAULT_TAX_CODE)
			.orgCompanyGstNo(UPDATED_ORG_COMPANY_GST_NO)
			.orgCompanyGstRegDate(UPDATED_ORG_COMPANY_GST_REG_DATE)
			.orgCompanyGstDeregDate(UPDATED_ORG_COMPANY_GST_DEREG_DATE)
			.orgPoTaxInclusive(UPDATED_ORG_PO_TAX_INCLUSIVE)
			.orgName(UPDATED_ORG_NAME)
			.orgNameOther(UPDATED_ORG_NAME_OTHER)
			.orgShortname(UPDATED_ORG_SHORTNAME)
			.orgAddress(UPDATED_ORG_ADDRESS)
			.orgShippingAddress(UPDATED_ORG_SHIPPING_ADDRESS)
			.orgBillingAddress(UPDATED_ORG_BILLING_ADDRESS)
			.orgPostcode(UPDATED_ORG_POSTCODE)
			.orgCity(UPDATED_ORG_CITY)
			.orgState(UPDATED_ORG_STATE)
			.orgCountry(UPDATED_ORG_COUNTRY)
			.orgOffPhone1(UPDATED_ORG_OFF_PHONE_1)
			.orgOffPhone2(UPDATED_ORG_OFF_PHONE_2)
			.orgOffPhone3(UPDATED_ORG_OFF_PHONE_3)
			.orgOffFax1(UPDATED_ORG_OFF_FAX_1)
			.orgOffFax2(UPDATED_ORG_OFF_FAX_2)
			.orgCredittermid(UPDATED_ORG_CREDITTERMID)
			.orgCreditLimit(UPDATED_ORG_CREDIT_LIMIT)
			.orgAgencyid(UPDATED_ORG_AGENCYID)
			.orgDivision(UPDATED_ORG_DIVISION)
			.orgDistrict(UPDATED_ORG_DISTRICT)
			.orgWebsite(UPDATED_ORG_WEBSITE)
			.orgEmail(UPDATED_ORG_EMAIL)
			.orgSupplierCategory(UPDATED_ORG_SUPPLIER_CATEGORY)
			.orgCurrencyCode(UPDATED_ORG_CURRENCY_CODE)
			.orgType(UPDATED_ORG_TYPE)
			.orgSectorid(UPDATED_ORG_SECTORID)
			.orgSector(UPDATED_ORG_SECTOR)
			.orgIndustry(UPDATED_ORG_INDUSTRY)
			.orgSainsGroup(UPDATED_ORG_SAINS_GROUP)
			.orgBumiputra(UPDATED_ORG_BUMIPUTRA)
			.orgUpkReg(UPDATED_ORG_UPK_REG)
			.orgMofReg(UPDATED_ORG_MOF_REG)
			.orgDesignation(UPDATED_ORG_DESIGNATION)
			.orgContpersonTitle(UPDATED_ORG_CONTPERSON_TITLE)
			.orgContperson(UPDATED_ORG_CONTPERSON)
			.orgDirectline(UPDATED_ORG_DIRECTLINE)
			.orgContpEmail(UPDATED_ORG_CONTP_EMAIL)
			.orgContpHp(UPDATED_ORG_CONTP_HP)
			.orgRemarks(UPDATED_ORG_REMARKS)
			.orgActiveStatus(UPDATED_ORG_ACTIVE_STATUS)
			.orgCcGc(UPDATED_ORG_CC_GC)
			.orgCustomerCateCode(UPDATED_ORG_CUSTOMER_CATE_CODE)
			.orgVendorCateCode(UPDATED_ORG_VENDOR_CATE_CODE)
			.orgSalesCateCode(UPDATED_ORG_SALES_CATE_CODE)
			.orgOutstandingBalance(UPDATED_ORG_OUTSTANDING_BALANCE)
			.orgOutstandingBalanceEx(UPDATED_ORG_OUTSTANDING_BALANCE_EX)
			.orgCompanyCode(UPDATED_ORG_COMPANY_CODE)
			.orgDcrownPostStatus(UPDATED_ORG_DCROWN_POST_STATUS)
			.confirmedBy(UPDATED_CONFIRMED_BY)
			.confirmedDate(UPDATED_CONFIRMED_DATE)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);
		return tOrganization;
	}

	@BeforeEach
	public void initTest() {
		tOrganization = createEntity(em);
	}

	@Test
	@Transactional
	void createTOrganization() throws Exception {
		int databaseSizeBeforeCreate = tOrganizationRepository.findAll().size();
		// Create the TOrganization
		restTOrganizationMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tOrganization)))
			.andExpect(status().isCreated());

		// Validate the TOrganization in the database
		List<TOrganization> tOrganizationList = tOrganizationRepository.findAll();
		assertThat(tOrganizationList).hasSize(databaseSizeBeforeCreate + 1);
		TOrganization testTOrganization = tOrganizationList.get(tOrganizationList.size() - 1);
		assertThat(testTOrganization.getOrgHqid()).isEqualTo(DEFAULT_ORG_HQID);
		assertThat(testTOrganization.getOrgHqBr()).isEqualTo(DEFAULT_ORG_HQ_BR);
		assertThat(testTOrganization.getOrgCode()).isEqualTo(DEFAULT_ORG_CODE);
		assertThat(testTOrganization.getOrgBrn()).isEqualTo(DEFAULT_ORG_BRN);
		assertThat(testTOrganization.getOrgPtaxid()).isEqualTo(DEFAULT_ORG_PTAXID);
		assertThat(testTOrganization.getOrgDefaultTaxCode()).isEqualTo(DEFAULT_ORG_DEFAULT_TAX_CODE);
		assertThat(testTOrganization.getOrgCompanyGstNo()).isEqualTo(DEFAULT_ORG_COMPANY_GST_NO);
		assertThat(testTOrganization.getOrgCompanyGstRegDate()).isEqualTo(DEFAULT_ORG_COMPANY_GST_REG_DATE);
		assertThat(testTOrganization.getOrgCompanyGstDeregDate()).isEqualTo(DEFAULT_ORG_COMPANY_GST_DEREG_DATE);
		assertThat(testTOrganization.getOrgPoTaxInclusive()).isEqualTo(DEFAULT_ORG_PO_TAX_INCLUSIVE);
		assertThat(testTOrganization.getOrgName()).isEqualTo(DEFAULT_ORG_NAME);
		assertThat(testTOrganization.getOrgNameOther()).isEqualTo(DEFAULT_ORG_NAME_OTHER);
		assertThat(testTOrganization.getOrgShortname()).isEqualTo(DEFAULT_ORG_SHORTNAME);
		assertThat(testTOrganization.getOrgAddress()).isEqualTo(DEFAULT_ORG_ADDRESS);
		assertThat(testTOrganization.getOrgShippingAddress()).isEqualTo(DEFAULT_ORG_SHIPPING_ADDRESS);
		assertThat(testTOrganization.getOrgBillingAddress()).isEqualTo(DEFAULT_ORG_BILLING_ADDRESS);
		assertThat(testTOrganization.getOrgPostcode()).isEqualTo(DEFAULT_ORG_POSTCODE);
		assertThat(testTOrganization.getOrgCity()).isEqualTo(DEFAULT_ORG_CITY);
		assertThat(testTOrganization.getOrgState()).isEqualTo(DEFAULT_ORG_STATE);
		assertThat(testTOrganization.getOrgCountry()).isEqualTo(DEFAULT_ORG_COUNTRY);
		assertThat(testTOrganization.getOrgOffPhone1()).isEqualTo(DEFAULT_ORG_OFF_PHONE_1);
		assertThat(testTOrganization.getOrgOffPhone2()).isEqualTo(DEFAULT_ORG_OFF_PHONE_2);
		assertThat(testTOrganization.getOrgOffPhone3()).isEqualTo(DEFAULT_ORG_OFF_PHONE_3);
		assertThat(testTOrganization.getOrgOffFax1()).isEqualTo(DEFAULT_ORG_OFF_FAX_1);
		assertThat(testTOrganization.getOrgOffFax2()).isEqualTo(DEFAULT_ORG_OFF_FAX_2);
		assertThat(testTOrganization.getOrgCredittermid()).isEqualTo(DEFAULT_ORG_CREDITTERMID);
		assertThat(testTOrganization.getOrgCreditLimit()).isEqualByComparingTo(DEFAULT_ORG_CREDIT_LIMIT);
		assertThat(testTOrganization.getOrgAgencyid()).isEqualTo(DEFAULT_ORG_AGENCYID);
		assertThat(testTOrganization.getOrgDivision()).isEqualTo(DEFAULT_ORG_DIVISION);
		assertThat(testTOrganization.getOrgDistrict()).isEqualTo(DEFAULT_ORG_DISTRICT);
		assertThat(testTOrganization.getOrgWebsite()).isEqualTo(DEFAULT_ORG_WEBSITE);
		assertThat(testTOrganization.getOrgEmail()).isEqualTo(DEFAULT_ORG_EMAIL);
		assertThat(testTOrganization.getOrgSupplierCategory()).isEqualTo(DEFAULT_ORG_SUPPLIER_CATEGORY);
		assertThat(testTOrganization.getOrgCurrencyCode()).isEqualTo(DEFAULT_ORG_CURRENCY_CODE);
		assertThat(testTOrganization.getOrgType()).isEqualTo(DEFAULT_ORG_TYPE);
		assertThat(testTOrganization.getOrgSectorid()).isEqualTo(DEFAULT_ORG_SECTORID);
		assertThat(testTOrganization.getOrgSector()).isEqualTo(DEFAULT_ORG_SECTOR);
		assertThat(testTOrganization.getOrgIndustry()).isEqualTo(DEFAULT_ORG_INDUSTRY);
		assertThat(testTOrganization.getOrgSainsGroup()).isEqualTo(DEFAULT_ORG_SAINS_GROUP);
		assertThat(testTOrganization.getOrgBumiputra()).isEqualTo(DEFAULT_ORG_BUMIPUTRA);
		assertThat(testTOrganization.getOrgUpkReg()).isEqualTo(DEFAULT_ORG_UPK_REG);
		assertThat(testTOrganization.getOrgMofReg()).isEqualTo(DEFAULT_ORG_MOF_REG);
		assertThat(testTOrganization.getOrgDesignation()).isEqualTo(DEFAULT_ORG_DESIGNATION);
		assertThat(testTOrganization.getOrgContpersonTitle()).isEqualTo(DEFAULT_ORG_CONTPERSON_TITLE);
		assertThat(testTOrganization.getOrgContperson()).isEqualTo(DEFAULT_ORG_CONTPERSON);
		assertThat(testTOrganization.getOrgDirectline()).isEqualTo(DEFAULT_ORG_DIRECTLINE);
		assertThat(testTOrganization.getOrgContpEmail()).isEqualTo(DEFAULT_ORG_CONTP_EMAIL);
		assertThat(testTOrganization.getOrgContpHp()).isEqualTo(DEFAULT_ORG_CONTP_HP);
		assertThat(testTOrganization.getOrgRemarks()).isEqualTo(DEFAULT_ORG_REMARKS);
		assertThat(testTOrganization.getOrgActiveStatus()).isEqualTo(DEFAULT_ORG_ACTIVE_STATUS);
		assertThat(testTOrganization.getOrgCcGc()).isEqualTo(DEFAULT_ORG_CC_GC);
		assertThat(testTOrganization.getOrgCustomerCateCode()).isEqualTo(DEFAULT_ORG_CUSTOMER_CATE_CODE);
		assertThat(testTOrganization.getOrgVendorCateCode()).isEqualTo(DEFAULT_ORG_VENDOR_CATE_CODE);
		assertThat(testTOrganization.getOrgSalesCateCode()).isEqualTo(DEFAULT_ORG_SALES_CATE_CODE);
		assertThat(testTOrganization.getOrgOutstandingBalance()).isEqualByComparingTo(DEFAULT_ORG_OUTSTANDING_BALANCE);
		assertThat(testTOrganization.getOrgOutstandingBalanceEx()).isEqualByComparingTo(DEFAULT_ORG_OUTSTANDING_BALANCE_EX);
		assertThat(testTOrganization.getOrgCompanyCode()).isEqualTo(DEFAULT_ORG_COMPANY_CODE);
		assertThat(testTOrganization.getOrgDcrownPostStatus()).isEqualTo(DEFAULT_ORG_DCROWN_POST_STATUS);
		assertThat(testTOrganization.getConfirmedBy()).isEqualTo(DEFAULT_CONFIRMED_BY);
		assertThat(testTOrganization.getConfirmedDate()).isEqualTo(DEFAULT_CONFIRMED_DATE);
		assertThat(testTOrganization.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
		assertThat(testTOrganization.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
		assertThat(testTOrganization.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
		assertThat(testTOrganization.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void createTOrganizationWithExistingId() throws Exception {
		// Create the TOrganization with an existing ID
		tOrganization.setId(1L);

		int databaseSizeBeforeCreate = tOrganizationRepository.findAll().size();

		// An entity with an existing ID cannot be created, so this API call must fail
		restTOrganizationMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tOrganization)))
			.andExpect(status().isBadRequest());

		// Validate the TOrganization in the database
		List<TOrganization> tOrganizationList = tOrganizationRepository.findAll();
		assertThat(tOrganizationList).hasSize(databaseSizeBeforeCreate);
	}

	@Test
	@Transactional
	void getAllTOrganizations() throws Exception {
		// Initialize the database
		tOrganizationRepository.saveAndFlush(tOrganization);

		// Get all the tOrganizationList
		restTOrganizationMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.[*].id").value(hasItem(tOrganization.getId().intValue())))
			.andExpect(jsonPath("$.[*].orgHqid").value(hasItem(DEFAULT_ORG_HQID)))
			.andExpect(jsonPath("$.[*].orgHqBr").value(hasItem(DEFAULT_ORG_HQ_BR)))
			.andExpect(jsonPath("$.[*].orgCode").value(hasItem(DEFAULT_ORG_CODE)))
			.andExpect(jsonPath("$.[*].orgBrn").value(hasItem(DEFAULT_ORG_BRN)))
			.andExpect(jsonPath("$.[*].orgPtaxid").value(hasItem(DEFAULT_ORG_PTAXID.intValue())))
			.andExpect(jsonPath("$.[*].orgDefaultTaxCode").value(hasItem(DEFAULT_ORG_DEFAULT_TAX_CODE)))
			.andExpect(jsonPath("$.[*].orgCompanyGstNo").value(hasItem(DEFAULT_ORG_COMPANY_GST_NO)))
			.andExpect(jsonPath("$.[*].orgCompanyGstRegDate").value(hasItem(DEFAULT_ORG_COMPANY_GST_REG_DATE.toString())))
			.andExpect(jsonPath("$.[*].orgCompanyGstDeregDate").value(hasItem(DEFAULT_ORG_COMPANY_GST_DEREG_DATE.toString())))
			.andExpect(jsonPath("$.[*].orgPoTaxInclusive").value(hasItem(DEFAULT_ORG_PO_TAX_INCLUSIVE)))
			.andExpect(jsonPath("$.[*].orgName").value(hasItem(DEFAULT_ORG_NAME)))
			.andExpect(jsonPath("$.[*].orgNameOther").value(hasItem(DEFAULT_ORG_NAME_OTHER)))
			.andExpect(jsonPath("$.[*].orgShortname").value(hasItem(DEFAULT_ORG_SHORTNAME)))
			.andExpect(jsonPath("$.[*].orgAddress").value(hasItem(DEFAULT_ORG_ADDRESS)))
			.andExpect(jsonPath("$.[*].orgShippingAddress").value(hasItem(DEFAULT_ORG_SHIPPING_ADDRESS)))
			.andExpect(jsonPath("$.[*].orgBillingAddress").value(hasItem(DEFAULT_ORG_BILLING_ADDRESS)))
			.andExpect(jsonPath("$.[*].orgPostcode").value(hasItem(DEFAULT_ORG_POSTCODE)))
			.andExpect(jsonPath("$.[*].orgCity").value(hasItem(DEFAULT_ORG_CITY)))
			.andExpect(jsonPath("$.[*].orgState").value(hasItem(DEFAULT_ORG_STATE)))
			.andExpect(jsonPath("$.[*].orgCountry").value(hasItem(DEFAULT_ORG_COUNTRY)))
			.andExpect(jsonPath("$.[*].orgOffPhone1").value(hasItem(DEFAULT_ORG_OFF_PHONE_1)))
			.andExpect(jsonPath("$.[*].orgOffPhone2").value(hasItem(DEFAULT_ORG_OFF_PHONE_2)))
			.andExpect(jsonPath("$.[*].orgOffPhone3").value(hasItem(DEFAULT_ORG_OFF_PHONE_3)))
			.andExpect(jsonPath("$.[*].orgOffFax1").value(hasItem(DEFAULT_ORG_OFF_FAX_1)))
			.andExpect(jsonPath("$.[*].orgOffFax2").value(hasItem(DEFAULT_ORG_OFF_FAX_2)))
			.andExpect(jsonPath("$.[*].orgCredittermid").value(hasItem(DEFAULT_ORG_CREDITTERMID.intValue())))
			.andExpect(jsonPath("$.[*].orgCreditLimit").value(hasItem(sameNumber(DEFAULT_ORG_CREDIT_LIMIT))))
			.andExpect(jsonPath("$.[*].orgAgencyid").value(hasItem(DEFAULT_ORG_AGENCYID.intValue())))
			.andExpect(jsonPath("$.[*].orgDivision").value(hasItem(DEFAULT_ORG_DIVISION)))
			.andExpect(jsonPath("$.[*].orgDistrict").value(hasItem(DEFAULT_ORG_DISTRICT)))
			.andExpect(jsonPath("$.[*].orgWebsite").value(hasItem(DEFAULT_ORG_WEBSITE)))
			.andExpect(jsonPath("$.[*].orgEmail").value(hasItem(DEFAULT_ORG_EMAIL)))
			.andExpect(jsonPath("$.[*].orgSupplierCategory").value(hasItem(DEFAULT_ORG_SUPPLIER_CATEGORY)))
			.andExpect(jsonPath("$.[*].orgCurrencyCode").value(hasItem(DEFAULT_ORG_CURRENCY_CODE)))
			.andExpect(jsonPath("$.[*].orgType").value(hasItem(DEFAULT_ORG_TYPE)))
			.andExpect(jsonPath("$.[*].orgSectorid").value(hasItem(DEFAULT_ORG_SECTORID.intValue())))
			.andExpect(jsonPath("$.[*].orgSector").value(hasItem(DEFAULT_ORG_SECTOR)))
			.andExpect(jsonPath("$.[*].orgIndustry").value(hasItem(DEFAULT_ORG_INDUSTRY)))
			.andExpect(jsonPath("$.[*].orgSainsGroup").value(hasItem(DEFAULT_ORG_SAINS_GROUP)))
			.andExpect(jsonPath("$.[*].orgBumiputra").value(hasItem(DEFAULT_ORG_BUMIPUTRA)))
			.andExpect(jsonPath("$.[*].orgUpkReg").value(hasItem(DEFAULT_ORG_UPK_REG)))
			.andExpect(jsonPath("$.[*].orgMofReg").value(hasItem(DEFAULT_ORG_MOF_REG)))
			.andExpect(jsonPath("$.[*].orgDesignation").value(hasItem(DEFAULT_ORG_DESIGNATION)))
			.andExpect(jsonPath("$.[*].orgContpersonTitle").value(hasItem(DEFAULT_ORG_CONTPERSON_TITLE)))
			.andExpect(jsonPath("$.[*].orgContperson").value(hasItem(DEFAULT_ORG_CONTPERSON)))
			.andExpect(jsonPath("$.[*].orgDirectline").value(hasItem(DEFAULT_ORG_DIRECTLINE)))
			.andExpect(jsonPath("$.[*].orgContpEmail").value(hasItem(DEFAULT_ORG_CONTP_EMAIL)))
			.andExpect(jsonPath("$.[*].orgContpHp").value(hasItem(DEFAULT_ORG_CONTP_HP)))
			.andExpect(jsonPath("$.[*].orgRemarks").value(hasItem(DEFAULT_ORG_REMARKS)))
			.andExpect(jsonPath("$.[*].orgActiveStatus").value(hasItem(DEFAULT_ORG_ACTIVE_STATUS)))
			.andExpect(jsonPath("$.[*].orgCcGc").value(hasItem(DEFAULT_ORG_CC_GC)))
			.andExpect(jsonPath("$.[*].orgCustomerCateCode").value(hasItem(DEFAULT_ORG_CUSTOMER_CATE_CODE)))
			.andExpect(jsonPath("$.[*].orgVendorCateCode").value(hasItem(DEFAULT_ORG_VENDOR_CATE_CODE)))
			.andExpect(jsonPath("$.[*].orgSalesCateCode").value(hasItem(DEFAULT_ORG_SALES_CATE_CODE)))
			.andExpect(jsonPath("$.[*].orgOutstandingBalance").value(hasItem(sameNumber(DEFAULT_ORG_OUTSTANDING_BALANCE))))
			.andExpect(jsonPath("$.[*].orgOutstandingBalanceEx").value(hasItem(sameNumber(DEFAULT_ORG_OUTSTANDING_BALANCE_EX))))
			.andExpect(jsonPath("$.[*].orgCompanyCode").value(hasItem(DEFAULT_ORG_COMPANY_CODE)))
			.andExpect(jsonPath("$.[*].orgDcrownPostStatus").value(hasItem(DEFAULT_ORG_DCROWN_POST_STATUS)))
			.andExpect(jsonPath("$.[*].confirmedBy").value(hasItem(DEFAULT_CONFIRMED_BY)))
			.andExpect(jsonPath("$.[*].confirmedDate").value(hasItem(sameInstant(DEFAULT_CONFIRMED_DATE))))
			.andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
			.andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
			.andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
			.andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
	}

	@Test
	@Transactional
	void getTOrganization() throws Exception {
		// Initialize the database
		tOrganizationRepository.saveAndFlush(tOrganization);

		// Get the tOrganization
		restTOrganizationMockMvc
			.perform(get(ENTITY_API_URL_ID, tOrganization.getId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.id").value(tOrganization.getId().intValue()))
			.andExpect(jsonPath("$.orgHqid").value(DEFAULT_ORG_HQID))
			.andExpect(jsonPath("$.orgHqBr").value(DEFAULT_ORG_HQ_BR))
			.andExpect(jsonPath("$.orgCode").value(DEFAULT_ORG_CODE))
			.andExpect(jsonPath("$.orgBrn").value(DEFAULT_ORG_BRN))
			.andExpect(jsonPath("$.orgPtaxid").value(DEFAULT_ORG_PTAXID.intValue()))
			.andExpect(jsonPath("$.orgDefaultTaxCode").value(DEFAULT_ORG_DEFAULT_TAX_CODE))
			.andExpect(jsonPath("$.orgCompanyGstNo").value(DEFAULT_ORG_COMPANY_GST_NO))
			.andExpect(jsonPath("$.orgCompanyGstRegDate").value(DEFAULT_ORG_COMPANY_GST_REG_DATE.toString()))
			.andExpect(jsonPath("$.orgCompanyGstDeregDate").value(DEFAULT_ORG_COMPANY_GST_DEREG_DATE.toString()))
			.andExpect(jsonPath("$.orgPoTaxInclusive").value(DEFAULT_ORG_PO_TAX_INCLUSIVE))
			.andExpect(jsonPath("$.orgName").value(DEFAULT_ORG_NAME))
			.andExpect(jsonPath("$.orgNameOther").value(DEFAULT_ORG_NAME_OTHER))
			.andExpect(jsonPath("$.orgShortname").value(DEFAULT_ORG_SHORTNAME))
			.andExpect(jsonPath("$.orgAddress").value(DEFAULT_ORG_ADDRESS))
			.andExpect(jsonPath("$.orgShippingAddress").value(DEFAULT_ORG_SHIPPING_ADDRESS))
			.andExpect(jsonPath("$.orgBillingAddress").value(DEFAULT_ORG_BILLING_ADDRESS))
			.andExpect(jsonPath("$.orgPostcode").value(DEFAULT_ORG_POSTCODE))
			.andExpect(jsonPath("$.orgCity").value(DEFAULT_ORG_CITY))
			.andExpect(jsonPath("$.orgState").value(DEFAULT_ORG_STATE))
			.andExpect(jsonPath("$.orgCountry").value(DEFAULT_ORG_COUNTRY))
			.andExpect(jsonPath("$.orgOffPhone1").value(DEFAULT_ORG_OFF_PHONE_1))
			.andExpect(jsonPath("$.orgOffPhone2").value(DEFAULT_ORG_OFF_PHONE_2))
			.andExpect(jsonPath("$.orgOffPhone3").value(DEFAULT_ORG_OFF_PHONE_3))
			.andExpect(jsonPath("$.orgOffFax1").value(DEFAULT_ORG_OFF_FAX_1))
			.andExpect(jsonPath("$.orgOffFax2").value(DEFAULT_ORG_OFF_FAX_2))
			.andExpect(jsonPath("$.orgCredittermid").value(DEFAULT_ORG_CREDITTERMID.intValue()))
			.andExpect(jsonPath("$.orgCreditLimit").value(sameNumber(DEFAULT_ORG_CREDIT_LIMIT)))
			.andExpect(jsonPath("$.orgAgencyid").value(DEFAULT_ORG_AGENCYID.intValue()))
			.andExpect(jsonPath("$.orgDivision").value(DEFAULT_ORG_DIVISION))
			.andExpect(jsonPath("$.orgDistrict").value(DEFAULT_ORG_DISTRICT))
			.andExpect(jsonPath("$.orgWebsite").value(DEFAULT_ORG_WEBSITE))
			.andExpect(jsonPath("$.orgEmail").value(DEFAULT_ORG_EMAIL))
			.andExpect(jsonPath("$.orgSupplierCategory").value(DEFAULT_ORG_SUPPLIER_CATEGORY))
			.andExpect(jsonPath("$.orgCurrencyCode").value(DEFAULT_ORG_CURRENCY_CODE))
			.andExpect(jsonPath("$.orgType").value(DEFAULT_ORG_TYPE))
			.andExpect(jsonPath("$.orgSectorid").value(DEFAULT_ORG_SECTORID.intValue()))
			.andExpect(jsonPath("$.orgSector").value(DEFAULT_ORG_SECTOR))
			.andExpect(jsonPath("$.orgIndustry").value(DEFAULT_ORG_INDUSTRY))
			.andExpect(jsonPath("$.orgSainsGroup").value(DEFAULT_ORG_SAINS_GROUP))
			.andExpect(jsonPath("$.orgBumiputra").value(DEFAULT_ORG_BUMIPUTRA))
			.andExpect(jsonPath("$.orgUpkReg").value(DEFAULT_ORG_UPK_REG))
			.andExpect(jsonPath("$.orgMofReg").value(DEFAULT_ORG_MOF_REG))
			.andExpect(jsonPath("$.orgDesignation").value(DEFAULT_ORG_DESIGNATION))
			.andExpect(jsonPath("$.orgContpersonTitle").value(DEFAULT_ORG_CONTPERSON_TITLE))
			.andExpect(jsonPath("$.orgContperson").value(DEFAULT_ORG_CONTPERSON))
			.andExpect(jsonPath("$.orgDirectline").value(DEFAULT_ORG_DIRECTLINE))
			.andExpect(jsonPath("$.orgContpEmail").value(DEFAULT_ORG_CONTP_EMAIL))
			.andExpect(jsonPath("$.orgContpHp").value(DEFAULT_ORG_CONTP_HP))
			.andExpect(jsonPath("$.orgRemarks").value(DEFAULT_ORG_REMARKS))
			.andExpect(jsonPath("$.orgActiveStatus").value(DEFAULT_ORG_ACTIVE_STATUS))
			.andExpect(jsonPath("$.orgCcGc").value(DEFAULT_ORG_CC_GC))
			.andExpect(jsonPath("$.orgCustomerCateCode").value(DEFAULT_ORG_CUSTOMER_CATE_CODE))
			.andExpect(jsonPath("$.orgVendorCateCode").value(DEFAULT_ORG_VENDOR_CATE_CODE))
			.andExpect(jsonPath("$.orgSalesCateCode").value(DEFAULT_ORG_SALES_CATE_CODE))
			.andExpect(jsonPath("$.orgOutstandingBalance").value(sameNumber(DEFAULT_ORG_OUTSTANDING_BALANCE)))
			.andExpect(jsonPath("$.orgOutstandingBalanceEx").value(sameNumber(DEFAULT_ORG_OUTSTANDING_BALANCE_EX)))
			.andExpect(jsonPath("$.orgCompanyCode").value(DEFAULT_ORG_COMPANY_CODE))
			.andExpect(jsonPath("$.orgDcrownPostStatus").value(DEFAULT_ORG_DCROWN_POST_STATUS))
			.andExpect(jsonPath("$.confirmedBy").value(DEFAULT_CONFIRMED_BY))
			.andExpect(jsonPath("$.confirmedDate").value(sameInstant(DEFAULT_CONFIRMED_DATE)))
			.andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
			.andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
			.andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
			.andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
	}

	@Test
	@Transactional
	void getNonExistingTOrganization() throws Exception {
		// Get the tOrganization
		restTOrganizationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	void putExistingTOrganization() throws Exception {
		// Initialize the database
		tOrganizationRepository.saveAndFlush(tOrganization);

		int databaseSizeBeforeUpdate = tOrganizationRepository.findAll().size();

		// Update the tOrganization
		TOrganization updatedTOrganization = tOrganizationRepository.findById(tOrganization.getId()).get();
		// Disconnect from session so that the updates on updatedTOrganization are not directly saved in db
		em.detach(updatedTOrganization);
		updatedTOrganization
			.orgHqid(UPDATED_ORG_HQID)
			.orgHqBr(UPDATED_ORG_HQ_BR)
			.orgCode(UPDATED_ORG_CODE)
			.orgBrn(UPDATED_ORG_BRN)
			.orgPtaxid(UPDATED_ORG_PTAXID)
			.orgDefaultTaxCode(UPDATED_ORG_DEFAULT_TAX_CODE)
			.orgCompanyGstNo(UPDATED_ORG_COMPANY_GST_NO)
			.orgCompanyGstRegDate(UPDATED_ORG_COMPANY_GST_REG_DATE)
			.orgCompanyGstDeregDate(UPDATED_ORG_COMPANY_GST_DEREG_DATE)
			.orgPoTaxInclusive(UPDATED_ORG_PO_TAX_INCLUSIVE)
			.orgName(UPDATED_ORG_NAME)
			.orgNameOther(UPDATED_ORG_NAME_OTHER)
			.orgShortname(UPDATED_ORG_SHORTNAME)
			.orgAddress(UPDATED_ORG_ADDRESS)
			.orgShippingAddress(UPDATED_ORG_SHIPPING_ADDRESS)
			.orgBillingAddress(UPDATED_ORG_BILLING_ADDRESS)
			.orgPostcode(UPDATED_ORG_POSTCODE)
			.orgCity(UPDATED_ORG_CITY)
			.orgState(UPDATED_ORG_STATE)
			.orgCountry(UPDATED_ORG_COUNTRY)
			.orgOffPhone1(UPDATED_ORG_OFF_PHONE_1)
			.orgOffPhone2(UPDATED_ORG_OFF_PHONE_2)
			.orgOffPhone3(UPDATED_ORG_OFF_PHONE_3)
			.orgOffFax1(UPDATED_ORG_OFF_FAX_1)
			.orgOffFax2(UPDATED_ORG_OFF_FAX_2)
			.orgCredittermid(UPDATED_ORG_CREDITTERMID)
			.orgCreditLimit(UPDATED_ORG_CREDIT_LIMIT)
			.orgAgencyid(UPDATED_ORG_AGENCYID)
			.orgDivision(UPDATED_ORG_DIVISION)
			.orgDistrict(UPDATED_ORG_DISTRICT)
			.orgWebsite(UPDATED_ORG_WEBSITE)
			.orgEmail(UPDATED_ORG_EMAIL)
			.orgSupplierCategory(UPDATED_ORG_SUPPLIER_CATEGORY)
			.orgCurrencyCode(UPDATED_ORG_CURRENCY_CODE)
			.orgType(UPDATED_ORG_TYPE)
			.orgSectorid(UPDATED_ORG_SECTORID)
			.orgSector(UPDATED_ORG_SECTOR)
			.orgIndustry(UPDATED_ORG_INDUSTRY)
			.orgSainsGroup(UPDATED_ORG_SAINS_GROUP)
			.orgBumiputra(UPDATED_ORG_BUMIPUTRA)
			.orgUpkReg(UPDATED_ORG_UPK_REG)
			.orgMofReg(UPDATED_ORG_MOF_REG)
			.orgDesignation(UPDATED_ORG_DESIGNATION)
			.orgContpersonTitle(UPDATED_ORG_CONTPERSON_TITLE)
			.orgContperson(UPDATED_ORG_CONTPERSON)
			.orgDirectline(UPDATED_ORG_DIRECTLINE)
			.orgContpEmail(UPDATED_ORG_CONTP_EMAIL)
			.orgContpHp(UPDATED_ORG_CONTP_HP)
			.orgRemarks(UPDATED_ORG_REMARKS)
			.orgActiveStatus(UPDATED_ORG_ACTIVE_STATUS)
			.orgCcGc(UPDATED_ORG_CC_GC)
			.orgCustomerCateCode(UPDATED_ORG_CUSTOMER_CATE_CODE)
			.orgVendorCateCode(UPDATED_ORG_VENDOR_CATE_CODE)
			.orgSalesCateCode(UPDATED_ORG_SALES_CATE_CODE)
			.orgOutstandingBalance(UPDATED_ORG_OUTSTANDING_BALANCE)
			.orgOutstandingBalanceEx(UPDATED_ORG_OUTSTANDING_BALANCE_EX)
			.orgCompanyCode(UPDATED_ORG_COMPANY_CODE)
			.orgDcrownPostStatus(UPDATED_ORG_DCROWN_POST_STATUS)
			.confirmedBy(UPDATED_CONFIRMED_BY)
			.confirmedDate(UPDATED_CONFIRMED_DATE)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTOrganizationMockMvc
			.perform(
				put(ENTITY_API_URL_ID, updatedTOrganization.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(updatedTOrganization))
			)
			.andExpect(status().isOk());

		// Validate the TOrganization in the database
		List<TOrganization> tOrganizationList = tOrganizationRepository.findAll();
		assertThat(tOrganizationList).hasSize(databaseSizeBeforeUpdate);
		TOrganization testTOrganization = tOrganizationList.get(tOrganizationList.size() - 1);
		assertThat(testTOrganization.getOrgHqid()).isEqualTo(UPDATED_ORG_HQID);
		assertThat(testTOrganization.getOrgHqBr()).isEqualTo(UPDATED_ORG_HQ_BR);
		assertThat(testTOrganization.getOrgCode()).isEqualTo(UPDATED_ORG_CODE);
		assertThat(testTOrganization.getOrgBrn()).isEqualTo(UPDATED_ORG_BRN);
		assertThat(testTOrganization.getOrgPtaxid()).isEqualTo(UPDATED_ORG_PTAXID);
		assertThat(testTOrganization.getOrgDefaultTaxCode()).isEqualTo(UPDATED_ORG_DEFAULT_TAX_CODE);
		assertThat(testTOrganization.getOrgCompanyGstNo()).isEqualTo(UPDATED_ORG_COMPANY_GST_NO);
		assertThat(testTOrganization.getOrgCompanyGstRegDate()).isEqualTo(UPDATED_ORG_COMPANY_GST_REG_DATE);
		assertThat(testTOrganization.getOrgCompanyGstDeregDate()).isEqualTo(UPDATED_ORG_COMPANY_GST_DEREG_DATE);
		assertThat(testTOrganization.getOrgPoTaxInclusive()).isEqualTo(UPDATED_ORG_PO_TAX_INCLUSIVE);
		assertThat(testTOrganization.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
		assertThat(testTOrganization.getOrgNameOther()).isEqualTo(UPDATED_ORG_NAME_OTHER);
		assertThat(testTOrganization.getOrgShortname()).isEqualTo(UPDATED_ORG_SHORTNAME);
		assertThat(testTOrganization.getOrgAddress()).isEqualTo(UPDATED_ORG_ADDRESS);
		assertThat(testTOrganization.getOrgShippingAddress()).isEqualTo(UPDATED_ORG_SHIPPING_ADDRESS);
		assertThat(testTOrganization.getOrgBillingAddress()).isEqualTo(UPDATED_ORG_BILLING_ADDRESS);
		assertThat(testTOrganization.getOrgPostcode()).isEqualTo(UPDATED_ORG_POSTCODE);
		assertThat(testTOrganization.getOrgCity()).isEqualTo(UPDATED_ORG_CITY);
		assertThat(testTOrganization.getOrgState()).isEqualTo(UPDATED_ORG_STATE);
		assertThat(testTOrganization.getOrgCountry()).isEqualTo(UPDATED_ORG_COUNTRY);
		assertThat(testTOrganization.getOrgOffPhone1()).isEqualTo(UPDATED_ORG_OFF_PHONE_1);
		assertThat(testTOrganization.getOrgOffPhone2()).isEqualTo(UPDATED_ORG_OFF_PHONE_2);
		assertThat(testTOrganization.getOrgOffPhone3()).isEqualTo(UPDATED_ORG_OFF_PHONE_3);
		assertThat(testTOrganization.getOrgOffFax1()).isEqualTo(UPDATED_ORG_OFF_FAX_1);
		assertThat(testTOrganization.getOrgOffFax2()).isEqualTo(UPDATED_ORG_OFF_FAX_2);
		assertThat(testTOrganization.getOrgCredittermid()).isEqualTo(UPDATED_ORG_CREDITTERMID);
		assertThat(testTOrganization.getOrgCreditLimit()).isEqualByComparingTo(UPDATED_ORG_CREDIT_LIMIT);
		assertThat(testTOrganization.getOrgAgencyid()).isEqualTo(UPDATED_ORG_AGENCYID);
		assertThat(testTOrganization.getOrgDivision()).isEqualTo(UPDATED_ORG_DIVISION);
		assertThat(testTOrganization.getOrgDistrict()).isEqualTo(UPDATED_ORG_DISTRICT);
		assertThat(testTOrganization.getOrgWebsite()).isEqualTo(UPDATED_ORG_WEBSITE);
		assertThat(testTOrganization.getOrgEmail()).isEqualTo(UPDATED_ORG_EMAIL);
		assertThat(testTOrganization.getOrgSupplierCategory()).isEqualTo(UPDATED_ORG_SUPPLIER_CATEGORY);
		assertThat(testTOrganization.getOrgCurrencyCode()).isEqualTo(UPDATED_ORG_CURRENCY_CODE);
		assertThat(testTOrganization.getOrgType()).isEqualTo(UPDATED_ORG_TYPE);
		assertThat(testTOrganization.getOrgSectorid()).isEqualTo(UPDATED_ORG_SECTORID);
		assertThat(testTOrganization.getOrgSector()).isEqualTo(UPDATED_ORG_SECTOR);
		assertThat(testTOrganization.getOrgIndustry()).isEqualTo(UPDATED_ORG_INDUSTRY);
		assertThat(testTOrganization.getOrgSainsGroup()).isEqualTo(UPDATED_ORG_SAINS_GROUP);
		assertThat(testTOrganization.getOrgBumiputra()).isEqualTo(UPDATED_ORG_BUMIPUTRA);
		assertThat(testTOrganization.getOrgUpkReg()).isEqualTo(UPDATED_ORG_UPK_REG);
		assertThat(testTOrganization.getOrgMofReg()).isEqualTo(UPDATED_ORG_MOF_REG);
		assertThat(testTOrganization.getOrgDesignation()).isEqualTo(UPDATED_ORG_DESIGNATION);
		assertThat(testTOrganization.getOrgContpersonTitle()).isEqualTo(UPDATED_ORG_CONTPERSON_TITLE);
		assertThat(testTOrganization.getOrgContperson()).isEqualTo(UPDATED_ORG_CONTPERSON);
		assertThat(testTOrganization.getOrgDirectline()).isEqualTo(UPDATED_ORG_DIRECTLINE);
		assertThat(testTOrganization.getOrgContpEmail()).isEqualTo(UPDATED_ORG_CONTP_EMAIL);
		assertThat(testTOrganization.getOrgContpHp()).isEqualTo(UPDATED_ORG_CONTP_HP);
		assertThat(testTOrganization.getOrgRemarks()).isEqualTo(UPDATED_ORG_REMARKS);
		assertThat(testTOrganization.getOrgActiveStatus()).isEqualTo(UPDATED_ORG_ACTIVE_STATUS);
		assertThat(testTOrganization.getOrgCcGc()).isEqualTo(UPDATED_ORG_CC_GC);
		assertThat(testTOrganization.getOrgCustomerCateCode()).isEqualTo(UPDATED_ORG_CUSTOMER_CATE_CODE);
		assertThat(testTOrganization.getOrgVendorCateCode()).isEqualTo(UPDATED_ORG_VENDOR_CATE_CODE);
		assertThat(testTOrganization.getOrgSalesCateCode()).isEqualTo(UPDATED_ORG_SALES_CATE_CODE);
		assertThat(testTOrganization.getOrgOutstandingBalance()).isEqualByComparingTo(UPDATED_ORG_OUTSTANDING_BALANCE);
		assertThat(testTOrganization.getOrgOutstandingBalanceEx()).isEqualByComparingTo(UPDATED_ORG_OUTSTANDING_BALANCE_EX);
		assertThat(testTOrganization.getOrgCompanyCode()).isEqualTo(UPDATED_ORG_COMPANY_CODE);
		assertThat(testTOrganization.getOrgDcrownPostStatus()).isEqualTo(UPDATED_ORG_DCROWN_POST_STATUS);
		assertThat(testTOrganization.getConfirmedBy()).isEqualTo(UPDATED_CONFIRMED_BY);
		assertThat(testTOrganization.getConfirmedDate()).isEqualTo(UPDATED_CONFIRMED_DATE);
		assertThat(testTOrganization.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTOrganization.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTOrganization.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTOrganization.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void putNonExistingTOrganization() throws Exception {
		int databaseSizeBeforeUpdate = tOrganizationRepository.findAll().size();
		tOrganization.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTOrganizationMockMvc
			.perform(
				put(ENTITY_API_URL_ID, tOrganization.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tOrganization))
			)
			.andExpect(status().isBadRequest());

		// Validate the TOrganization in the database
		List<TOrganization> tOrganizationList = tOrganizationRepository.findAll();
		assertThat(tOrganizationList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithIdMismatchTOrganization() throws Exception {
		int databaseSizeBeforeUpdate = tOrganizationRepository.findAll().size();
		tOrganization.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTOrganizationMockMvc
			.perform(
				put(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tOrganization))
			)
			.andExpect(status().isBadRequest());

		// Validate the TOrganization in the database
		List<TOrganization> tOrganizationList = tOrganizationRepository.findAll();
		assertThat(tOrganizationList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithMissingIdPathParamTOrganization() throws Exception {
		int databaseSizeBeforeUpdate = tOrganizationRepository.findAll().size();
		tOrganization.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTOrganizationMockMvc
			.perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tOrganization)))
			.andExpect(status().isMethodNotAllowed());

		// Validate the TOrganization in the database
		List<TOrganization> tOrganizationList = tOrganizationRepository.findAll();
		assertThat(tOrganizationList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void partialUpdateTOrganizationWithPatch() throws Exception {
		// Initialize the database
		tOrganizationRepository.saveAndFlush(tOrganization);

		int databaseSizeBeforeUpdate = tOrganizationRepository.findAll().size();

		// Update the tOrganization using partial update
		TOrganization partialUpdatedTOrganization = new TOrganization();
		partialUpdatedTOrganization.setId(tOrganization.getId());

		partialUpdatedTOrganization
			.orgHqid(UPDATED_ORG_HQID)
			.orgHqBr(UPDATED_ORG_HQ_BR)
			.orgCode(UPDATED_ORG_CODE)
			.orgPtaxid(UPDATED_ORG_PTAXID)
			.orgDefaultTaxCode(UPDATED_ORG_DEFAULT_TAX_CODE)
			.orgCompanyGstRegDate(UPDATED_ORG_COMPANY_GST_REG_DATE)
			.orgName(UPDATED_ORG_NAME)
			.orgAddress(UPDATED_ORG_ADDRESS)
			.orgBillingAddress(UPDATED_ORG_BILLING_ADDRESS)
			.orgState(UPDATED_ORG_STATE)
			.orgCountry(UPDATED_ORG_COUNTRY)
			.orgOffPhone2(UPDATED_ORG_OFF_PHONE_2)
			.orgOffPhone3(UPDATED_ORG_OFF_PHONE_3)
			.orgOffFax2(UPDATED_ORG_OFF_FAX_2)
			.orgAgencyid(UPDATED_ORG_AGENCYID)
			.orgDivision(UPDATED_ORG_DIVISION)
			.orgEmail(UPDATED_ORG_EMAIL)
			.orgCurrencyCode(UPDATED_ORG_CURRENCY_CODE)
			.orgSectorid(UPDATED_ORG_SECTORID)
			.orgSector(UPDATED_ORG_SECTOR)
			.orgSainsGroup(UPDATED_ORG_SAINS_GROUP)
			.orgUpkReg(UPDATED_ORG_UPK_REG)
			.orgContpEmail(UPDATED_ORG_CONTP_EMAIL)
			.orgContpHp(UPDATED_ORG_CONTP_HP)
			.orgActiveStatus(UPDATED_ORG_ACTIVE_STATUS)
			.orgCustomerCateCode(UPDATED_ORG_CUSTOMER_CATE_CODE)
			.orgVendorCateCode(UPDATED_ORG_VENDOR_CATE_CODE)
			.confirmedDate(UPDATED_CONFIRMED_DATE)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY);

		restTOrganizationMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTOrganization.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTOrganization))
			)
			.andExpect(status().isOk());

		// Validate the TOrganization in the database
		List<TOrganization> tOrganizationList = tOrganizationRepository.findAll();
		assertThat(tOrganizationList).hasSize(databaseSizeBeforeUpdate);
		TOrganization testTOrganization = tOrganizationList.get(tOrganizationList.size() - 1);
		assertThat(testTOrganization.getOrgHqid()).isEqualTo(UPDATED_ORG_HQID);
		assertThat(testTOrganization.getOrgHqBr()).isEqualTo(UPDATED_ORG_HQ_BR);
		assertThat(testTOrganization.getOrgCode()).isEqualTo(UPDATED_ORG_CODE);
		assertThat(testTOrganization.getOrgBrn()).isEqualTo(DEFAULT_ORG_BRN);
		assertThat(testTOrganization.getOrgPtaxid()).isEqualTo(UPDATED_ORG_PTAXID);
		assertThat(testTOrganization.getOrgDefaultTaxCode()).isEqualTo(UPDATED_ORG_DEFAULT_TAX_CODE);
		assertThat(testTOrganization.getOrgCompanyGstNo()).isEqualTo(DEFAULT_ORG_COMPANY_GST_NO);
		assertThat(testTOrganization.getOrgCompanyGstRegDate()).isEqualTo(UPDATED_ORG_COMPANY_GST_REG_DATE);
		assertThat(testTOrganization.getOrgCompanyGstDeregDate()).isEqualTo(DEFAULT_ORG_COMPANY_GST_DEREG_DATE);
		assertThat(testTOrganization.getOrgPoTaxInclusive()).isEqualTo(DEFAULT_ORG_PO_TAX_INCLUSIVE);
		assertThat(testTOrganization.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
		assertThat(testTOrganization.getOrgNameOther()).isEqualTo(DEFAULT_ORG_NAME_OTHER);
		assertThat(testTOrganization.getOrgShortname()).isEqualTo(DEFAULT_ORG_SHORTNAME);
		assertThat(testTOrganization.getOrgAddress()).isEqualTo(UPDATED_ORG_ADDRESS);
		assertThat(testTOrganization.getOrgShippingAddress()).isEqualTo(DEFAULT_ORG_SHIPPING_ADDRESS);
		assertThat(testTOrganization.getOrgBillingAddress()).isEqualTo(UPDATED_ORG_BILLING_ADDRESS);
		assertThat(testTOrganization.getOrgPostcode()).isEqualTo(DEFAULT_ORG_POSTCODE);
		assertThat(testTOrganization.getOrgCity()).isEqualTo(DEFAULT_ORG_CITY);
		assertThat(testTOrganization.getOrgState()).isEqualTo(UPDATED_ORG_STATE);
		assertThat(testTOrganization.getOrgCountry()).isEqualTo(UPDATED_ORG_COUNTRY);
		assertThat(testTOrganization.getOrgOffPhone1()).isEqualTo(DEFAULT_ORG_OFF_PHONE_1);
		assertThat(testTOrganization.getOrgOffPhone2()).isEqualTo(UPDATED_ORG_OFF_PHONE_2);
		assertThat(testTOrganization.getOrgOffPhone3()).isEqualTo(UPDATED_ORG_OFF_PHONE_3);
		assertThat(testTOrganization.getOrgOffFax1()).isEqualTo(DEFAULT_ORG_OFF_FAX_1);
		assertThat(testTOrganization.getOrgOffFax2()).isEqualTo(UPDATED_ORG_OFF_FAX_2);
		assertThat(testTOrganization.getOrgCredittermid()).isEqualTo(DEFAULT_ORG_CREDITTERMID);
		assertThat(testTOrganization.getOrgCreditLimit()).isEqualByComparingTo(DEFAULT_ORG_CREDIT_LIMIT);
		assertThat(testTOrganization.getOrgAgencyid()).isEqualTo(UPDATED_ORG_AGENCYID);
		assertThat(testTOrganization.getOrgDivision()).isEqualTo(UPDATED_ORG_DIVISION);
		assertThat(testTOrganization.getOrgDistrict()).isEqualTo(DEFAULT_ORG_DISTRICT);
		assertThat(testTOrganization.getOrgWebsite()).isEqualTo(DEFAULT_ORG_WEBSITE);
		assertThat(testTOrganization.getOrgEmail()).isEqualTo(UPDATED_ORG_EMAIL);
		assertThat(testTOrganization.getOrgSupplierCategory()).isEqualTo(DEFAULT_ORG_SUPPLIER_CATEGORY);
		assertThat(testTOrganization.getOrgCurrencyCode()).isEqualTo(UPDATED_ORG_CURRENCY_CODE);
		assertThat(testTOrganization.getOrgType()).isEqualTo(DEFAULT_ORG_TYPE);
		assertThat(testTOrganization.getOrgSectorid()).isEqualTo(UPDATED_ORG_SECTORID);
		assertThat(testTOrganization.getOrgSector()).isEqualTo(UPDATED_ORG_SECTOR);
		assertThat(testTOrganization.getOrgIndustry()).isEqualTo(DEFAULT_ORG_INDUSTRY);
		assertThat(testTOrganization.getOrgSainsGroup()).isEqualTo(UPDATED_ORG_SAINS_GROUP);
		assertThat(testTOrganization.getOrgBumiputra()).isEqualTo(DEFAULT_ORG_BUMIPUTRA);
		assertThat(testTOrganization.getOrgUpkReg()).isEqualTo(UPDATED_ORG_UPK_REG);
		assertThat(testTOrganization.getOrgMofReg()).isEqualTo(DEFAULT_ORG_MOF_REG);
		assertThat(testTOrganization.getOrgDesignation()).isEqualTo(DEFAULT_ORG_DESIGNATION);
		assertThat(testTOrganization.getOrgContpersonTitle()).isEqualTo(DEFAULT_ORG_CONTPERSON_TITLE);
		assertThat(testTOrganization.getOrgContperson()).isEqualTo(DEFAULT_ORG_CONTPERSON);
		assertThat(testTOrganization.getOrgDirectline()).isEqualTo(DEFAULT_ORG_DIRECTLINE);
		assertThat(testTOrganization.getOrgContpEmail()).isEqualTo(UPDATED_ORG_CONTP_EMAIL);
		assertThat(testTOrganization.getOrgContpHp()).isEqualTo(UPDATED_ORG_CONTP_HP);
		assertThat(testTOrganization.getOrgRemarks()).isEqualTo(DEFAULT_ORG_REMARKS);
		assertThat(testTOrganization.getOrgActiveStatus()).isEqualTo(UPDATED_ORG_ACTIVE_STATUS);
		assertThat(testTOrganization.getOrgCcGc()).isEqualTo(DEFAULT_ORG_CC_GC);
		assertThat(testTOrganization.getOrgCustomerCateCode()).isEqualTo(UPDATED_ORG_CUSTOMER_CATE_CODE);
		assertThat(testTOrganization.getOrgVendorCateCode()).isEqualTo(UPDATED_ORG_VENDOR_CATE_CODE);
		assertThat(testTOrganization.getOrgSalesCateCode()).isEqualTo(DEFAULT_ORG_SALES_CATE_CODE);
		assertThat(testTOrganization.getOrgOutstandingBalance()).isEqualByComparingTo(DEFAULT_ORG_OUTSTANDING_BALANCE);
		assertThat(testTOrganization.getOrgOutstandingBalanceEx()).isEqualByComparingTo(DEFAULT_ORG_OUTSTANDING_BALANCE_EX);
		assertThat(testTOrganization.getOrgCompanyCode()).isEqualTo(DEFAULT_ORG_COMPANY_CODE);
		assertThat(testTOrganization.getOrgDcrownPostStatus()).isEqualTo(DEFAULT_ORG_DCROWN_POST_STATUS);
		assertThat(testTOrganization.getConfirmedBy()).isEqualTo(DEFAULT_CONFIRMED_BY);
		assertThat(testTOrganization.getConfirmedDate()).isEqualTo(UPDATED_CONFIRMED_DATE);
		assertThat(testTOrganization.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
		assertThat(testTOrganization.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTOrganization.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTOrganization.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void fullUpdateTOrganizationWithPatch() throws Exception {
		// Initialize the database
		tOrganizationRepository.saveAndFlush(tOrganization);

		int databaseSizeBeforeUpdate = tOrganizationRepository.findAll().size();

		// Update the tOrganization using partial update
		TOrganization partialUpdatedTOrganization = new TOrganization();
		partialUpdatedTOrganization.setId(tOrganization.getId());

		partialUpdatedTOrganization
			.orgHqid(UPDATED_ORG_HQID)
			.orgHqBr(UPDATED_ORG_HQ_BR)
			.orgCode(UPDATED_ORG_CODE)
			.orgBrn(UPDATED_ORG_BRN)
			.orgPtaxid(UPDATED_ORG_PTAXID)
			.orgDefaultTaxCode(UPDATED_ORG_DEFAULT_TAX_CODE)
			.orgCompanyGstNo(UPDATED_ORG_COMPANY_GST_NO)
			.orgCompanyGstRegDate(UPDATED_ORG_COMPANY_GST_REG_DATE)
			.orgCompanyGstDeregDate(UPDATED_ORG_COMPANY_GST_DEREG_DATE)
			.orgPoTaxInclusive(UPDATED_ORG_PO_TAX_INCLUSIVE)
			.orgName(UPDATED_ORG_NAME)
			.orgNameOther(UPDATED_ORG_NAME_OTHER)
			.orgShortname(UPDATED_ORG_SHORTNAME)
			.orgAddress(UPDATED_ORG_ADDRESS)
			.orgShippingAddress(UPDATED_ORG_SHIPPING_ADDRESS)
			.orgBillingAddress(UPDATED_ORG_BILLING_ADDRESS)
			.orgPostcode(UPDATED_ORG_POSTCODE)
			.orgCity(UPDATED_ORG_CITY)
			.orgState(UPDATED_ORG_STATE)
			.orgCountry(UPDATED_ORG_COUNTRY)
			.orgOffPhone1(UPDATED_ORG_OFF_PHONE_1)
			.orgOffPhone2(UPDATED_ORG_OFF_PHONE_2)
			.orgOffPhone3(UPDATED_ORG_OFF_PHONE_3)
			.orgOffFax1(UPDATED_ORG_OFF_FAX_1)
			.orgOffFax2(UPDATED_ORG_OFF_FAX_2)
			.orgCredittermid(UPDATED_ORG_CREDITTERMID)
			.orgCreditLimit(UPDATED_ORG_CREDIT_LIMIT)
			.orgAgencyid(UPDATED_ORG_AGENCYID)
			.orgDivision(UPDATED_ORG_DIVISION)
			.orgDistrict(UPDATED_ORG_DISTRICT)
			.orgWebsite(UPDATED_ORG_WEBSITE)
			.orgEmail(UPDATED_ORG_EMAIL)
			.orgSupplierCategory(UPDATED_ORG_SUPPLIER_CATEGORY)
			.orgCurrencyCode(UPDATED_ORG_CURRENCY_CODE)
			.orgType(UPDATED_ORG_TYPE)
			.orgSectorid(UPDATED_ORG_SECTORID)
			.orgSector(UPDATED_ORG_SECTOR)
			.orgIndustry(UPDATED_ORG_INDUSTRY)
			.orgSainsGroup(UPDATED_ORG_SAINS_GROUP)
			.orgBumiputra(UPDATED_ORG_BUMIPUTRA)
			.orgUpkReg(UPDATED_ORG_UPK_REG)
			.orgMofReg(UPDATED_ORG_MOF_REG)
			.orgDesignation(UPDATED_ORG_DESIGNATION)
			.orgContpersonTitle(UPDATED_ORG_CONTPERSON_TITLE)
			.orgContperson(UPDATED_ORG_CONTPERSON)
			.orgDirectline(UPDATED_ORG_DIRECTLINE)
			.orgContpEmail(UPDATED_ORG_CONTP_EMAIL)
			.orgContpHp(UPDATED_ORG_CONTP_HP)
			.orgRemarks(UPDATED_ORG_REMARKS)
			.orgActiveStatus(UPDATED_ORG_ACTIVE_STATUS)
			.orgCcGc(UPDATED_ORG_CC_GC)
			.orgCustomerCateCode(UPDATED_ORG_CUSTOMER_CATE_CODE)
			.orgVendorCateCode(UPDATED_ORG_VENDOR_CATE_CODE)
			.orgSalesCateCode(UPDATED_ORG_SALES_CATE_CODE)
			.orgOutstandingBalance(UPDATED_ORG_OUTSTANDING_BALANCE)
			.orgOutstandingBalanceEx(UPDATED_ORG_OUTSTANDING_BALANCE_EX)
			.orgCompanyCode(UPDATED_ORG_COMPANY_CODE)
			.orgDcrownPostStatus(UPDATED_ORG_DCROWN_POST_STATUS)
			.confirmedBy(UPDATED_CONFIRMED_BY)
			.confirmedDate(UPDATED_CONFIRMED_DATE)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTOrganizationMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTOrganization.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTOrganization))
			)
			.andExpect(status().isOk());

		// Validate the TOrganization in the database
		List<TOrganization> tOrganizationList = tOrganizationRepository.findAll();
		assertThat(tOrganizationList).hasSize(databaseSizeBeforeUpdate);
		TOrganization testTOrganization = tOrganizationList.get(tOrganizationList.size() - 1);
		assertThat(testTOrganization.getOrgHqid()).isEqualTo(UPDATED_ORG_HQID);
		assertThat(testTOrganization.getOrgHqBr()).isEqualTo(UPDATED_ORG_HQ_BR);
		assertThat(testTOrganization.getOrgCode()).isEqualTo(UPDATED_ORG_CODE);
		assertThat(testTOrganization.getOrgBrn()).isEqualTo(UPDATED_ORG_BRN);
		assertThat(testTOrganization.getOrgPtaxid()).isEqualTo(UPDATED_ORG_PTAXID);
		assertThat(testTOrganization.getOrgDefaultTaxCode()).isEqualTo(UPDATED_ORG_DEFAULT_TAX_CODE);
		assertThat(testTOrganization.getOrgCompanyGstNo()).isEqualTo(UPDATED_ORG_COMPANY_GST_NO);
		assertThat(testTOrganization.getOrgCompanyGstRegDate()).isEqualTo(UPDATED_ORG_COMPANY_GST_REG_DATE);
		assertThat(testTOrganization.getOrgCompanyGstDeregDate()).isEqualTo(UPDATED_ORG_COMPANY_GST_DEREG_DATE);
		assertThat(testTOrganization.getOrgPoTaxInclusive()).isEqualTo(UPDATED_ORG_PO_TAX_INCLUSIVE);
		assertThat(testTOrganization.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
		assertThat(testTOrganization.getOrgNameOther()).isEqualTo(UPDATED_ORG_NAME_OTHER);
		assertThat(testTOrganization.getOrgShortname()).isEqualTo(UPDATED_ORG_SHORTNAME);
		assertThat(testTOrganization.getOrgAddress()).isEqualTo(UPDATED_ORG_ADDRESS);
		assertThat(testTOrganization.getOrgShippingAddress()).isEqualTo(UPDATED_ORG_SHIPPING_ADDRESS);
		assertThat(testTOrganization.getOrgBillingAddress()).isEqualTo(UPDATED_ORG_BILLING_ADDRESS);
		assertThat(testTOrganization.getOrgPostcode()).isEqualTo(UPDATED_ORG_POSTCODE);
		assertThat(testTOrganization.getOrgCity()).isEqualTo(UPDATED_ORG_CITY);
		assertThat(testTOrganization.getOrgState()).isEqualTo(UPDATED_ORG_STATE);
		assertThat(testTOrganization.getOrgCountry()).isEqualTo(UPDATED_ORG_COUNTRY);
		assertThat(testTOrganization.getOrgOffPhone1()).isEqualTo(UPDATED_ORG_OFF_PHONE_1);
		assertThat(testTOrganization.getOrgOffPhone2()).isEqualTo(UPDATED_ORG_OFF_PHONE_2);
		assertThat(testTOrganization.getOrgOffPhone3()).isEqualTo(UPDATED_ORG_OFF_PHONE_3);
		assertThat(testTOrganization.getOrgOffFax1()).isEqualTo(UPDATED_ORG_OFF_FAX_1);
		assertThat(testTOrganization.getOrgOffFax2()).isEqualTo(UPDATED_ORG_OFF_FAX_2);
		assertThat(testTOrganization.getOrgCredittermid()).isEqualTo(UPDATED_ORG_CREDITTERMID);
		assertThat(testTOrganization.getOrgCreditLimit()).isEqualByComparingTo(UPDATED_ORG_CREDIT_LIMIT);
		assertThat(testTOrganization.getOrgAgencyid()).isEqualTo(UPDATED_ORG_AGENCYID);
		assertThat(testTOrganization.getOrgDivision()).isEqualTo(UPDATED_ORG_DIVISION);
		assertThat(testTOrganization.getOrgDistrict()).isEqualTo(UPDATED_ORG_DISTRICT);
		assertThat(testTOrganization.getOrgWebsite()).isEqualTo(UPDATED_ORG_WEBSITE);
		assertThat(testTOrganization.getOrgEmail()).isEqualTo(UPDATED_ORG_EMAIL);
		assertThat(testTOrganization.getOrgSupplierCategory()).isEqualTo(UPDATED_ORG_SUPPLIER_CATEGORY);
		assertThat(testTOrganization.getOrgCurrencyCode()).isEqualTo(UPDATED_ORG_CURRENCY_CODE);
		assertThat(testTOrganization.getOrgType()).isEqualTo(UPDATED_ORG_TYPE);
		assertThat(testTOrganization.getOrgSectorid()).isEqualTo(UPDATED_ORG_SECTORID);
		assertThat(testTOrganization.getOrgSector()).isEqualTo(UPDATED_ORG_SECTOR);
		assertThat(testTOrganization.getOrgIndustry()).isEqualTo(UPDATED_ORG_INDUSTRY);
		assertThat(testTOrganization.getOrgSainsGroup()).isEqualTo(UPDATED_ORG_SAINS_GROUP);
		assertThat(testTOrganization.getOrgBumiputra()).isEqualTo(UPDATED_ORG_BUMIPUTRA);
		assertThat(testTOrganization.getOrgUpkReg()).isEqualTo(UPDATED_ORG_UPK_REG);
		assertThat(testTOrganization.getOrgMofReg()).isEqualTo(UPDATED_ORG_MOF_REG);
		assertThat(testTOrganization.getOrgDesignation()).isEqualTo(UPDATED_ORG_DESIGNATION);
		assertThat(testTOrganization.getOrgContpersonTitle()).isEqualTo(UPDATED_ORG_CONTPERSON_TITLE);
		assertThat(testTOrganization.getOrgContperson()).isEqualTo(UPDATED_ORG_CONTPERSON);
		assertThat(testTOrganization.getOrgDirectline()).isEqualTo(UPDATED_ORG_DIRECTLINE);
		assertThat(testTOrganization.getOrgContpEmail()).isEqualTo(UPDATED_ORG_CONTP_EMAIL);
		assertThat(testTOrganization.getOrgContpHp()).isEqualTo(UPDATED_ORG_CONTP_HP);
		assertThat(testTOrganization.getOrgRemarks()).isEqualTo(UPDATED_ORG_REMARKS);
		assertThat(testTOrganization.getOrgActiveStatus()).isEqualTo(UPDATED_ORG_ACTIVE_STATUS);
		assertThat(testTOrganization.getOrgCcGc()).isEqualTo(UPDATED_ORG_CC_GC);
		assertThat(testTOrganization.getOrgCustomerCateCode()).isEqualTo(UPDATED_ORG_CUSTOMER_CATE_CODE);
		assertThat(testTOrganization.getOrgVendorCateCode()).isEqualTo(UPDATED_ORG_VENDOR_CATE_CODE);
		assertThat(testTOrganization.getOrgSalesCateCode()).isEqualTo(UPDATED_ORG_SALES_CATE_CODE);
		assertThat(testTOrganization.getOrgOutstandingBalance()).isEqualByComparingTo(UPDATED_ORG_OUTSTANDING_BALANCE);
		assertThat(testTOrganization.getOrgOutstandingBalanceEx()).isEqualByComparingTo(UPDATED_ORG_OUTSTANDING_BALANCE_EX);
		assertThat(testTOrganization.getOrgCompanyCode()).isEqualTo(UPDATED_ORG_COMPANY_CODE);
		assertThat(testTOrganization.getOrgDcrownPostStatus()).isEqualTo(UPDATED_ORG_DCROWN_POST_STATUS);
		assertThat(testTOrganization.getConfirmedBy()).isEqualTo(UPDATED_CONFIRMED_BY);
		assertThat(testTOrganization.getConfirmedDate()).isEqualTo(UPDATED_CONFIRMED_DATE);
		assertThat(testTOrganization.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTOrganization.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTOrganization.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTOrganization.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void patchNonExistingTOrganization() throws Exception {
		int databaseSizeBeforeUpdate = tOrganizationRepository.findAll().size();
		tOrganization.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTOrganizationMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, tOrganization.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tOrganization))
			)
			.andExpect(status().isBadRequest());

		// Validate the TOrganization in the database
		List<TOrganization> tOrganizationList = tOrganizationRepository.findAll();
		assertThat(tOrganizationList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithIdMismatchTOrganization() throws Exception {
		int databaseSizeBeforeUpdate = tOrganizationRepository.findAll().size();
		tOrganization.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTOrganizationMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tOrganization))
			)
			.andExpect(status().isBadRequest());

		// Validate the TOrganization in the database
		List<TOrganization> tOrganizationList = tOrganizationRepository.findAll();
		assertThat(tOrganizationList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithMissingIdPathParamTOrganization() throws Exception {
		int databaseSizeBeforeUpdate = tOrganizationRepository.findAll().size();
		tOrganization.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTOrganizationMockMvc
			.perform(
				patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tOrganization))
			)
			.andExpect(status().isMethodNotAllowed());

		// Validate the TOrganization in the database
		List<TOrganization> tOrganizationList = tOrganizationRepository.findAll();
		assertThat(tOrganizationList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void deleteTOrganization() throws Exception {
		// Initialize the database
		tOrganizationRepository.saveAndFlush(tOrganization);

		int databaseSizeBeforeDelete = tOrganizationRepository.findAll().size();

		// Delete the tOrganization
		restTOrganizationMockMvc
			.perform(delete(ENTITY_API_URL_ID, tOrganization.getId()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());

		// Validate the database contains one less item
		List<TOrganization> tOrganizationList = tOrganizationRepository.findAll();
		assertThat(tOrganizationList).hasSize(databaseSizeBeforeDelete - 1);
	}
}
