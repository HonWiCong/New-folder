package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TCountryCode;
import com.jhipster.app.domain.TStateCode;
import com.jhipster.app.repository.TCountryCodeRepository;
import com.jhipster.app.service.criteria.TCountryCodeCriteria;
import java.time.Instant;
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
 * Integration tests for the {@link TCountryCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCountryCodeResourceIT {

	private static final String DEFAULT_COUNTRY_CODE = "AAAAAAAAAA";
	private static final String UPDATED_COUNTRY_CODE = "BBBBBBBBBB";

	private static final String DEFAULT_COUNTRY_NAME = "AAAAAAAAAA";
	private static final String UPDATED_COUNTRY_NAME = "BBBBBBBBBB";

	private static final String DEFAULT_COUNTRY_NATIONALITY = "AAAAAAAAAA";
	private static final String UPDATED_COUNTRY_NATIONALITY = "BBBBBBBBBB";

	private static final Integer DEFAULT_ENTERED_BY = 1;
	private static final Integer UPDATED_ENTERED_BY = 2;
	private static final Integer SMALLER_ENTERED_BY = 1 - 1;

	private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
	private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
	private static final ZonedDateTime SMALLER_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

	private static final Integer DEFAULT_MODIFIED_BY = 1;
	private static final Integer UPDATED_MODIFIED_BY = 2;
	private static final Integer SMALLER_MODIFIED_BY = 1 - 1;

	private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
	private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
	private static final ZonedDateTime SMALLER_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

	private static final String DEFAULT_ORG_CUSTOMER_TYPE = "AAAAAAAAAA";
	private static final String UPDATED_ORG_CUSTOMER_TYPE = "BBBBBBBBBB";

	private static final String ENTITY_API_URL = "/api/t-country-codes";
	private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

	private static Random random = new Random();
	private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

	@Autowired
	private TCountryCodeRepository tCountryCodeRepository;

	@Autowired
	private EntityManager em;

	@Autowired
	private MockMvc restTCountryCodeMockMvc;

	private TCountryCode tCountryCode;

	/**
	 * Create an entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TCountryCode createEntity(EntityManager em) {
		TCountryCode tCountryCode = new TCountryCode()
			.countryCode(DEFAULT_COUNTRY_CODE)
			.countryName(DEFAULT_COUNTRY_NAME)
			.countryNationality(DEFAULT_COUNTRY_NATIONALITY)
			.enteredBy(DEFAULT_ENTERED_BY)
			.enteredDate(DEFAULT_ENTERED_DATE)
			.modifiedBy(DEFAULT_MODIFIED_BY)
			.modifiedDate(DEFAULT_MODIFIED_DATE)
			.orgCustomerType(DEFAULT_ORG_CUSTOMER_TYPE);
		return tCountryCode;
	}

	/**
	 * Create an updated entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TCountryCode createUpdatedEntity(EntityManager em) {
		TCountryCode tCountryCode = new TCountryCode()
			.countryCode(UPDATED_COUNTRY_CODE)
			.countryName(UPDATED_COUNTRY_NAME)
			.countryNationality(UPDATED_COUNTRY_NATIONALITY)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE)
			.orgCustomerType(UPDATED_ORG_CUSTOMER_TYPE);
		return tCountryCode;
	}

	@BeforeEach
	public void initTest() {
		tCountryCode = createEntity(em);
	}

	@Test
	@Transactional
	void createTCountryCode() throws Exception {
		int databaseSizeBeforeCreate = tCountryCodeRepository.findAll().size();
		// Create the TCountryCode
		restTCountryCodeMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCountryCode)))
			.andExpect(status().isCreated());

		// Validate the TCountryCode in the database
		List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
		assertThat(tCountryCodeList).hasSize(databaseSizeBeforeCreate + 1);
		TCountryCode testTCountryCode = tCountryCodeList.get(tCountryCodeList.size() - 1);
		assertThat(testTCountryCode.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
		assertThat(testTCountryCode.getCountryName()).isEqualTo(DEFAULT_COUNTRY_NAME);
		assertThat(testTCountryCode.getCountryNationality()).isEqualTo(DEFAULT_COUNTRY_NATIONALITY);
		assertThat(testTCountryCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
		assertThat(testTCountryCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
		assertThat(testTCountryCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
		assertThat(testTCountryCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
		assertThat(testTCountryCode.getOrgCustomerType()).isEqualTo(DEFAULT_ORG_CUSTOMER_TYPE);
	}

	@Test
	@Transactional
	void createTCountryCodeWithExistingId() throws Exception {
		// Create the TCountryCode with an existing ID
		tCountryCode.setId(1L);

		int databaseSizeBeforeCreate = tCountryCodeRepository.findAll().size();

		// An entity with an existing ID cannot be created, so this API call must fail
		restTCountryCodeMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCountryCode)))
			.andExpect(status().isBadRequest());

		// Validate the TCountryCode in the database
		List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
		assertThat(tCountryCodeList).hasSize(databaseSizeBeforeCreate);
	}

	@Test
	@Transactional
	void getAllTCountryCodes() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList
		restTCountryCodeMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.[*].id").value(hasItem(tCountryCode.getId().intValue())))
			.andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
			.andExpect(jsonPath("$.[*].countryName").value(hasItem(DEFAULT_COUNTRY_NAME)))
			.andExpect(jsonPath("$.[*].countryNationality").value(hasItem(DEFAULT_COUNTRY_NATIONALITY)))
			.andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
			.andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
			.andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
			.andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))))
			.andExpect(jsonPath("$.[*].orgCustomerType").value(hasItem(DEFAULT_ORG_CUSTOMER_TYPE)));
	}

	@Test
	@Transactional
	void getTCountryCode() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get the tCountryCode
		restTCountryCodeMockMvc
			.perform(get(ENTITY_API_URL_ID, tCountryCode.getId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.id").value(tCountryCode.getId().intValue()))
			.andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE))
			.andExpect(jsonPath("$.countryName").value(DEFAULT_COUNTRY_NAME))
			.andExpect(jsonPath("$.countryNationality").value(DEFAULT_COUNTRY_NATIONALITY))
			.andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
			.andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
			.andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
			.andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)))
			.andExpect(jsonPath("$.orgCustomerType").value(DEFAULT_ORG_CUSTOMER_TYPE));
	}

	@Test
	@Transactional
	void getTCountryCodesByIdFiltering() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		Long id = tCountryCode.getId();

		defaultTCountryCodeShouldBeFound("id.equals=" + id);
		defaultTCountryCodeShouldNotBeFound("id.notEquals=" + id);

		defaultTCountryCodeShouldBeFound("id.greaterThanOrEqual=" + id);
		defaultTCountryCodeShouldNotBeFound("id.greaterThan=" + id);

		defaultTCountryCodeShouldBeFound("id.lessThanOrEqual=" + id);
		defaultTCountryCodeShouldNotBeFound("id.lessThan=" + id);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByCountryCodeIsEqualToSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where countryCode equals to DEFAULT_COUNTRY_CODE
		defaultTCountryCodeShouldBeFound("countryCode.equals=" + DEFAULT_COUNTRY_CODE);

		// Get all the tCountryCodeList where countryCode equals to UPDATED_COUNTRY_CODE
		defaultTCountryCodeShouldNotBeFound("countryCode.equals=" + UPDATED_COUNTRY_CODE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByCountryCodeIsInShouldWork() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where countryCode in DEFAULT_COUNTRY_CODE or UPDATED_COUNTRY_CODE
		defaultTCountryCodeShouldBeFound("countryCode.in=" + DEFAULT_COUNTRY_CODE + "," + UPDATED_COUNTRY_CODE);

		// Get all the tCountryCodeList where countryCode equals to UPDATED_COUNTRY_CODE
		defaultTCountryCodeShouldNotBeFound("countryCode.in=" + UPDATED_COUNTRY_CODE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByCountryCodeIsNullOrNotNull() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where countryCode is not null
		defaultTCountryCodeShouldBeFound("countryCode.specified=true");

		// Get all the tCountryCodeList where countryCode is null
		defaultTCountryCodeShouldNotBeFound("countryCode.specified=false");
	}

	@Test
	@Transactional
	void getAllTCountryCodesByCountryCodeContainsSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where countryCode contains DEFAULT_COUNTRY_CODE
		defaultTCountryCodeShouldBeFound("countryCode.contains=" + DEFAULT_COUNTRY_CODE);

		// Get all the tCountryCodeList where countryCode contains UPDATED_COUNTRY_CODE
		defaultTCountryCodeShouldNotBeFound("countryCode.contains=" + UPDATED_COUNTRY_CODE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByCountryCodeNotContainsSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where countryCode does not contain DEFAULT_COUNTRY_CODE
		defaultTCountryCodeShouldNotBeFound("countryCode.doesNotContain=" + DEFAULT_COUNTRY_CODE);

		// Get all the tCountryCodeList where countryCode does not contain UPDATED_COUNTRY_CODE
		defaultTCountryCodeShouldBeFound("countryCode.doesNotContain=" + UPDATED_COUNTRY_CODE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByCountryNameIsEqualToSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where countryName equals to DEFAULT_COUNTRY_NAME
		defaultTCountryCodeShouldBeFound("countryName.equals=" + DEFAULT_COUNTRY_NAME);

		// Get all the tCountryCodeList where countryName equals to UPDATED_COUNTRY_NAME
		defaultTCountryCodeShouldNotBeFound("countryName.equals=" + UPDATED_COUNTRY_NAME);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByCountryNameIsInShouldWork() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where countryName in DEFAULT_COUNTRY_NAME or UPDATED_COUNTRY_NAME
		defaultTCountryCodeShouldBeFound("countryName.in=" + DEFAULT_COUNTRY_NAME + "," + UPDATED_COUNTRY_NAME);

		// Get all the tCountryCodeList where countryName equals to UPDATED_COUNTRY_NAME
		defaultTCountryCodeShouldNotBeFound("countryName.in=" + UPDATED_COUNTRY_NAME);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByCountryNameIsNullOrNotNull() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where countryName is not null
		defaultTCountryCodeShouldBeFound("countryName.specified=true");

		// Get all the tCountryCodeList where countryName is null
		defaultTCountryCodeShouldNotBeFound("countryName.specified=false");
	}

	@Test
	@Transactional
	void getAllTCountryCodesByCountryNameContainsSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where countryName contains DEFAULT_COUNTRY_NAME
		defaultTCountryCodeShouldBeFound("countryName.contains=" + DEFAULT_COUNTRY_NAME);

		// Get all the tCountryCodeList where countryName contains UPDATED_COUNTRY_NAME
		defaultTCountryCodeShouldNotBeFound("countryName.contains=" + UPDATED_COUNTRY_NAME);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByCountryNameNotContainsSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where countryName does not contain DEFAULT_COUNTRY_NAME
		defaultTCountryCodeShouldNotBeFound("countryName.doesNotContain=" + DEFAULT_COUNTRY_NAME);

		// Get all the tCountryCodeList where countryName does not contain UPDATED_COUNTRY_NAME
		defaultTCountryCodeShouldBeFound("countryName.doesNotContain=" + UPDATED_COUNTRY_NAME);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByCountryNationalityIsEqualToSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where countryNationality equals to DEFAULT_COUNTRY_NATIONALITY
		defaultTCountryCodeShouldBeFound("countryNationality.equals=" + DEFAULT_COUNTRY_NATIONALITY);

		// Get all the tCountryCodeList where countryNationality equals to UPDATED_COUNTRY_NATIONALITY
		defaultTCountryCodeShouldNotBeFound("countryNationality.equals=" + UPDATED_COUNTRY_NATIONALITY);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByCountryNationalityIsInShouldWork() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where countryNationality in DEFAULT_COUNTRY_NATIONALITY or UPDATED_COUNTRY_NATIONALITY
		defaultTCountryCodeShouldBeFound("countryNationality.in=" + DEFAULT_COUNTRY_NATIONALITY + "," + UPDATED_COUNTRY_NATIONALITY);

		// Get all the tCountryCodeList where countryNationality equals to UPDATED_COUNTRY_NATIONALITY
		defaultTCountryCodeShouldNotBeFound("countryNationality.in=" + UPDATED_COUNTRY_NATIONALITY);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByCountryNationalityIsNullOrNotNull() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where countryNationality is not null
		defaultTCountryCodeShouldBeFound("countryNationality.specified=true");

		// Get all the tCountryCodeList where countryNationality is null
		defaultTCountryCodeShouldNotBeFound("countryNationality.specified=false");
	}

	@Test
	@Transactional
	void getAllTCountryCodesByCountryNationalityContainsSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where countryNationality contains DEFAULT_COUNTRY_NATIONALITY
		defaultTCountryCodeShouldBeFound("countryNationality.contains=" + DEFAULT_COUNTRY_NATIONALITY);

		// Get all the tCountryCodeList where countryNationality contains UPDATED_COUNTRY_NATIONALITY
		defaultTCountryCodeShouldNotBeFound("countryNationality.contains=" + UPDATED_COUNTRY_NATIONALITY);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByCountryNationalityNotContainsSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where countryNationality does not contain DEFAULT_COUNTRY_NATIONALITY
		defaultTCountryCodeShouldNotBeFound("countryNationality.doesNotContain=" + DEFAULT_COUNTRY_NATIONALITY);

		// Get all the tCountryCodeList where countryNationality does not contain UPDATED_COUNTRY_NATIONALITY
		defaultTCountryCodeShouldBeFound("countryNationality.doesNotContain=" + UPDATED_COUNTRY_NATIONALITY);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByEnteredByIsEqualToSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where enteredBy equals to DEFAULT_ENTERED_BY
		defaultTCountryCodeShouldBeFound("enteredBy.equals=" + DEFAULT_ENTERED_BY);

		// Get all the tCountryCodeList where enteredBy equals to UPDATED_ENTERED_BY
		defaultTCountryCodeShouldNotBeFound("enteredBy.equals=" + UPDATED_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByEnteredByIsInShouldWork() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where enteredBy in DEFAULT_ENTERED_BY or UPDATED_ENTERED_BY
		defaultTCountryCodeShouldBeFound("enteredBy.in=" + DEFAULT_ENTERED_BY + "," + UPDATED_ENTERED_BY);

		// Get all the tCountryCodeList where enteredBy equals to UPDATED_ENTERED_BY
		defaultTCountryCodeShouldNotBeFound("enteredBy.in=" + UPDATED_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByEnteredByIsNullOrNotNull() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where enteredBy is not null
		defaultTCountryCodeShouldBeFound("enteredBy.specified=true");

		// Get all the tCountryCodeList where enteredBy is null
		defaultTCountryCodeShouldNotBeFound("enteredBy.specified=false");
	}

	@Test
	@Transactional
	void getAllTCountryCodesByEnteredByIsGreaterThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where enteredBy is greater than or equal to DEFAULT_ENTERED_BY
		defaultTCountryCodeShouldBeFound("enteredBy.greaterThanOrEqual=" + DEFAULT_ENTERED_BY);

		// Get all the tCountryCodeList where enteredBy is greater than or equal to UPDATED_ENTERED_BY
		defaultTCountryCodeShouldNotBeFound("enteredBy.greaterThanOrEqual=" + UPDATED_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByEnteredByIsLessThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where enteredBy is less than or equal to DEFAULT_ENTERED_BY
		defaultTCountryCodeShouldBeFound("enteredBy.lessThanOrEqual=" + DEFAULT_ENTERED_BY);

		// Get all the tCountryCodeList where enteredBy is less than or equal to SMALLER_ENTERED_BY
		defaultTCountryCodeShouldNotBeFound("enteredBy.lessThanOrEqual=" + SMALLER_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByEnteredByIsLessThanSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where enteredBy is less than DEFAULT_ENTERED_BY
		defaultTCountryCodeShouldNotBeFound("enteredBy.lessThan=" + DEFAULT_ENTERED_BY);

		// Get all the tCountryCodeList where enteredBy is less than UPDATED_ENTERED_BY
		defaultTCountryCodeShouldBeFound("enteredBy.lessThan=" + UPDATED_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByEnteredByIsGreaterThanSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where enteredBy is greater than DEFAULT_ENTERED_BY
		defaultTCountryCodeShouldNotBeFound("enteredBy.greaterThan=" + DEFAULT_ENTERED_BY);

		// Get all the tCountryCodeList where enteredBy is greater than SMALLER_ENTERED_BY
		defaultTCountryCodeShouldBeFound("enteredBy.greaterThan=" + SMALLER_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByEnteredDateIsEqualToSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where enteredDate equals to DEFAULT_ENTERED_DATE
		defaultTCountryCodeShouldBeFound("enteredDate.equals=" + DEFAULT_ENTERED_DATE);

		// Get all the tCountryCodeList where enteredDate equals to UPDATED_ENTERED_DATE
		defaultTCountryCodeShouldNotBeFound("enteredDate.equals=" + UPDATED_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByEnteredDateIsInShouldWork() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where enteredDate in DEFAULT_ENTERED_DATE or UPDATED_ENTERED_DATE
		defaultTCountryCodeShouldBeFound("enteredDate.in=" + DEFAULT_ENTERED_DATE + "," + UPDATED_ENTERED_DATE);

		// Get all the tCountryCodeList where enteredDate equals to UPDATED_ENTERED_DATE
		defaultTCountryCodeShouldNotBeFound("enteredDate.in=" + UPDATED_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByEnteredDateIsNullOrNotNull() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where enteredDate is not null
		defaultTCountryCodeShouldBeFound("enteredDate.specified=true");

		// Get all the tCountryCodeList where enteredDate is null
		defaultTCountryCodeShouldNotBeFound("enteredDate.specified=false");
	}

	@Test
	@Transactional
	void getAllTCountryCodesByEnteredDateIsGreaterThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where enteredDate is greater than or equal to DEFAULT_ENTERED_DATE
		defaultTCountryCodeShouldBeFound("enteredDate.greaterThanOrEqual=" + DEFAULT_ENTERED_DATE);

		// Get all the tCountryCodeList where enteredDate is greater than or equal to UPDATED_ENTERED_DATE
		defaultTCountryCodeShouldNotBeFound("enteredDate.greaterThanOrEqual=" + UPDATED_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByEnteredDateIsLessThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where enteredDate is less than or equal to DEFAULT_ENTERED_DATE
		defaultTCountryCodeShouldBeFound("enteredDate.lessThanOrEqual=" + DEFAULT_ENTERED_DATE);

		// Get all the tCountryCodeList where enteredDate is less than or equal to SMALLER_ENTERED_DATE
		defaultTCountryCodeShouldNotBeFound("enteredDate.lessThanOrEqual=" + SMALLER_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByEnteredDateIsLessThanSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where enteredDate is less than DEFAULT_ENTERED_DATE
		defaultTCountryCodeShouldNotBeFound("enteredDate.lessThan=" + DEFAULT_ENTERED_DATE);

		// Get all the tCountryCodeList where enteredDate is less than UPDATED_ENTERED_DATE
		defaultTCountryCodeShouldBeFound("enteredDate.lessThan=" + UPDATED_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByEnteredDateIsGreaterThanSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where enteredDate is greater than DEFAULT_ENTERED_DATE
		defaultTCountryCodeShouldNotBeFound("enteredDate.greaterThan=" + DEFAULT_ENTERED_DATE);

		// Get all the tCountryCodeList where enteredDate is greater than SMALLER_ENTERED_DATE
		defaultTCountryCodeShouldBeFound("enteredDate.greaterThan=" + SMALLER_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByModifiedByIsEqualToSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where modifiedBy equals to DEFAULT_MODIFIED_BY
		defaultTCountryCodeShouldBeFound("modifiedBy.equals=" + DEFAULT_MODIFIED_BY);

		// Get all the tCountryCodeList where modifiedBy equals to UPDATED_MODIFIED_BY
		defaultTCountryCodeShouldNotBeFound("modifiedBy.equals=" + UPDATED_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByModifiedByIsInShouldWork() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where modifiedBy in DEFAULT_MODIFIED_BY or UPDATED_MODIFIED_BY
		defaultTCountryCodeShouldBeFound("modifiedBy.in=" + DEFAULT_MODIFIED_BY + "," + UPDATED_MODIFIED_BY);

		// Get all the tCountryCodeList where modifiedBy equals to UPDATED_MODIFIED_BY
		defaultTCountryCodeShouldNotBeFound("modifiedBy.in=" + UPDATED_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByModifiedByIsNullOrNotNull() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where modifiedBy is not null
		defaultTCountryCodeShouldBeFound("modifiedBy.specified=true");

		// Get all the tCountryCodeList where modifiedBy is null
		defaultTCountryCodeShouldNotBeFound("modifiedBy.specified=false");
	}

	@Test
	@Transactional
	void getAllTCountryCodesByModifiedByIsGreaterThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where modifiedBy is greater than or equal to DEFAULT_MODIFIED_BY
		defaultTCountryCodeShouldBeFound("modifiedBy.greaterThanOrEqual=" + DEFAULT_MODIFIED_BY);

		// Get all the tCountryCodeList where modifiedBy is greater than or equal to UPDATED_MODIFIED_BY
		defaultTCountryCodeShouldNotBeFound("modifiedBy.greaterThanOrEqual=" + UPDATED_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByModifiedByIsLessThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where modifiedBy is less than or equal to DEFAULT_MODIFIED_BY
		defaultTCountryCodeShouldBeFound("modifiedBy.lessThanOrEqual=" + DEFAULT_MODIFIED_BY);

		// Get all the tCountryCodeList where modifiedBy is less than or equal to SMALLER_MODIFIED_BY
		defaultTCountryCodeShouldNotBeFound("modifiedBy.lessThanOrEqual=" + SMALLER_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByModifiedByIsLessThanSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where modifiedBy is less than DEFAULT_MODIFIED_BY
		defaultTCountryCodeShouldNotBeFound("modifiedBy.lessThan=" + DEFAULT_MODIFIED_BY);

		// Get all the tCountryCodeList where modifiedBy is less than UPDATED_MODIFIED_BY
		defaultTCountryCodeShouldBeFound("modifiedBy.lessThan=" + UPDATED_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByModifiedByIsGreaterThanSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where modifiedBy is greater than DEFAULT_MODIFIED_BY
		defaultTCountryCodeShouldNotBeFound("modifiedBy.greaterThan=" + DEFAULT_MODIFIED_BY);

		// Get all the tCountryCodeList where modifiedBy is greater than SMALLER_MODIFIED_BY
		defaultTCountryCodeShouldBeFound("modifiedBy.greaterThan=" + SMALLER_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByModifiedDateIsEqualToSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where modifiedDate equals to DEFAULT_MODIFIED_DATE
		defaultTCountryCodeShouldBeFound("modifiedDate.equals=" + DEFAULT_MODIFIED_DATE);

		// Get all the tCountryCodeList where modifiedDate equals to UPDATED_MODIFIED_DATE
		defaultTCountryCodeShouldNotBeFound("modifiedDate.equals=" + UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByModifiedDateIsInShouldWork() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where modifiedDate in DEFAULT_MODIFIED_DATE or UPDATED_MODIFIED_DATE
		defaultTCountryCodeShouldBeFound("modifiedDate.in=" + DEFAULT_MODIFIED_DATE + "," + UPDATED_MODIFIED_DATE);

		// Get all the tCountryCodeList where modifiedDate equals to UPDATED_MODIFIED_DATE
		defaultTCountryCodeShouldNotBeFound("modifiedDate.in=" + UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByModifiedDateIsNullOrNotNull() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where modifiedDate is not null
		defaultTCountryCodeShouldBeFound("modifiedDate.specified=true");

		// Get all the tCountryCodeList where modifiedDate is null
		defaultTCountryCodeShouldNotBeFound("modifiedDate.specified=false");
	}

	@Test
	@Transactional
	void getAllTCountryCodesByModifiedDateIsGreaterThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where modifiedDate is greater than or equal to DEFAULT_MODIFIED_DATE
		defaultTCountryCodeShouldBeFound("modifiedDate.greaterThanOrEqual=" + DEFAULT_MODIFIED_DATE);

		// Get all the tCountryCodeList where modifiedDate is greater than or equal to UPDATED_MODIFIED_DATE
		defaultTCountryCodeShouldNotBeFound("modifiedDate.greaterThanOrEqual=" + UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByModifiedDateIsLessThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where modifiedDate is less than or equal to DEFAULT_MODIFIED_DATE
		defaultTCountryCodeShouldBeFound("modifiedDate.lessThanOrEqual=" + DEFAULT_MODIFIED_DATE);

		// Get all the tCountryCodeList where modifiedDate is less than or equal to SMALLER_MODIFIED_DATE
		defaultTCountryCodeShouldNotBeFound("modifiedDate.lessThanOrEqual=" + SMALLER_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByModifiedDateIsLessThanSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where modifiedDate is less than DEFAULT_MODIFIED_DATE
		defaultTCountryCodeShouldNotBeFound("modifiedDate.lessThan=" + DEFAULT_MODIFIED_DATE);

		// Get all the tCountryCodeList where modifiedDate is less than UPDATED_MODIFIED_DATE
		defaultTCountryCodeShouldBeFound("modifiedDate.lessThan=" + UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByModifiedDateIsGreaterThanSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where modifiedDate is greater than DEFAULT_MODIFIED_DATE
		defaultTCountryCodeShouldNotBeFound("modifiedDate.greaterThan=" + DEFAULT_MODIFIED_DATE);

		// Get all the tCountryCodeList where modifiedDate is greater than SMALLER_MODIFIED_DATE
		defaultTCountryCodeShouldBeFound("modifiedDate.greaterThan=" + SMALLER_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByOrgCustomerTypeIsEqualToSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where orgCustomerType equals to DEFAULT_ORG_CUSTOMER_TYPE
		defaultTCountryCodeShouldBeFound("orgCustomerType.equals=" + DEFAULT_ORG_CUSTOMER_TYPE);

		// Get all the tCountryCodeList where orgCustomerType equals to UPDATED_ORG_CUSTOMER_TYPE
		defaultTCountryCodeShouldNotBeFound("orgCustomerType.equals=" + UPDATED_ORG_CUSTOMER_TYPE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByOrgCustomerTypeIsInShouldWork() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where orgCustomerType in DEFAULT_ORG_CUSTOMER_TYPE or UPDATED_ORG_CUSTOMER_TYPE
		defaultTCountryCodeShouldBeFound("orgCustomerType.in=" + DEFAULT_ORG_CUSTOMER_TYPE + "," + UPDATED_ORG_CUSTOMER_TYPE);

		// Get all the tCountryCodeList where orgCustomerType equals to UPDATED_ORG_CUSTOMER_TYPE
		defaultTCountryCodeShouldNotBeFound("orgCustomerType.in=" + UPDATED_ORG_CUSTOMER_TYPE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByOrgCustomerTypeIsNullOrNotNull() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where orgCustomerType is not null
		defaultTCountryCodeShouldBeFound("orgCustomerType.specified=true");

		// Get all the tCountryCodeList where orgCustomerType is null
		defaultTCountryCodeShouldNotBeFound("orgCustomerType.specified=false");
	}

	@Test
	@Transactional
	void getAllTCountryCodesByOrgCustomerTypeContainsSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where orgCustomerType contains DEFAULT_ORG_CUSTOMER_TYPE
		defaultTCountryCodeShouldBeFound("orgCustomerType.contains=" + DEFAULT_ORG_CUSTOMER_TYPE);

		// Get all the tCountryCodeList where orgCustomerType contains UPDATED_ORG_CUSTOMER_TYPE
		defaultTCountryCodeShouldNotBeFound("orgCustomerType.contains=" + UPDATED_ORG_CUSTOMER_TYPE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByOrgCustomerTypeNotContainsSomething() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		// Get all the tCountryCodeList where orgCustomerType does not contain DEFAULT_ORG_CUSTOMER_TYPE
		defaultTCountryCodeShouldNotBeFound("orgCustomerType.doesNotContain=" + DEFAULT_ORG_CUSTOMER_TYPE);

		// Get all the tCountryCodeList where orgCustomerType does not contain UPDATED_ORG_CUSTOMER_TYPE
		defaultTCountryCodeShouldBeFound("orgCustomerType.doesNotContain=" + UPDATED_ORG_CUSTOMER_TYPE);
	}

	@Test
	@Transactional
	void getAllTCountryCodesByTStateCodeIsEqualToSomething() throws Exception {
		TStateCode tStateCode;
		if (TestUtil.findAll(em, TStateCode.class).isEmpty()) {
			tCountryCodeRepository.saveAndFlush(tCountryCode);
			tStateCode = TStateCodeResourceIT.createEntity(em);
		} else {
			tStateCode = TestUtil.findAll(em, TStateCode.class).get(0);
		}
		em.persist(tStateCode);
		em.flush();
		tCountryCode.addTStateCode(tStateCode);
		tCountryCodeRepository.saveAndFlush(tCountryCode);
		Long tStateCodeId = tStateCode.getId();

		// Get all the tCountryCodeList where tStateCode equals to tStateCodeId
		defaultTCountryCodeShouldBeFound("tStateCodeId.equals=" + tStateCodeId);

		// Get all the tCountryCodeList where tStateCode equals to (tStateCodeId + 1)
		defaultTCountryCodeShouldNotBeFound("tStateCodeId.equals=" + (tStateCodeId + 1));
	}

	/**
	 * Executes the search, and checks that the default entity is returned.
	 */
	private void defaultTCountryCodeShouldBeFound(String filter) throws Exception {
		restTCountryCodeMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.[*].id").value(hasItem(tCountryCode.getId().intValue())))
			.andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
			.andExpect(jsonPath("$.[*].countryName").value(hasItem(DEFAULT_COUNTRY_NAME)))
			.andExpect(jsonPath("$.[*].countryNationality").value(hasItem(DEFAULT_COUNTRY_NATIONALITY)))
			.andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
			.andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
			.andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
			.andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))))
			.andExpect(jsonPath("$.[*].orgCustomerType").value(hasItem(DEFAULT_ORG_CUSTOMER_TYPE)));

		// Check, that the count call also returns 1
		restTCountryCodeMockMvc
			.perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().string("1"));
	}

	/**
	 * Executes the search, and checks that the default entity is not returned.
	 */
	private void defaultTCountryCodeShouldNotBeFound(String filter) throws Exception {
		restTCountryCodeMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$").isEmpty());

		// Check, that the count call also returns 0
		restTCountryCodeMockMvc
			.perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().string("0"));
	}

	@Test
	@Transactional
	void getNonExistingTCountryCode() throws Exception {
		// Get the tCountryCode
		restTCountryCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	void putExistingTCountryCode() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();

		// Update the tCountryCode
		TCountryCode updatedTCountryCode = tCountryCodeRepository.findById(tCountryCode.getId()).get();
		// Disconnect from session so that the updates on updatedTCountryCode are not directly saved in db
		em.detach(updatedTCountryCode);
		updatedTCountryCode
			.countryCode(UPDATED_COUNTRY_CODE)
			.countryName(UPDATED_COUNTRY_NAME)
			.countryNationality(UPDATED_COUNTRY_NATIONALITY)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE)
			.orgCustomerType(UPDATED_ORG_CUSTOMER_TYPE);

		restTCountryCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, updatedTCountryCode.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(updatedTCountryCode))
			)
			.andExpect(status().isOk());

		// Validate the TCountryCode in the database
		List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
		assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
		TCountryCode testTCountryCode = tCountryCodeList.get(tCountryCodeList.size() - 1);
		assertThat(testTCountryCode.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
		assertThat(testTCountryCode.getCountryName()).isEqualTo(UPDATED_COUNTRY_NAME);
		assertThat(testTCountryCode.getCountryNationality()).isEqualTo(UPDATED_COUNTRY_NATIONALITY);
		assertThat(testTCountryCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTCountryCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTCountryCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTCountryCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
		assertThat(testTCountryCode.getOrgCustomerType()).isEqualTo(UPDATED_ORG_CUSTOMER_TYPE);
	}

	@Test
	@Transactional
	void putNonExistingTCountryCode() throws Exception {
		int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();
		tCountryCode.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTCountryCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, tCountryCode.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tCountryCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TCountryCode in the database
		List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
		assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithIdMismatchTCountryCode() throws Exception {
		int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();
		tCountryCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTCountryCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tCountryCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TCountryCode in the database
		List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
		assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithMissingIdPathParamTCountryCode() throws Exception {
		int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();
		tCountryCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTCountryCodeMockMvc
			.perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCountryCode)))
			.andExpect(status().isMethodNotAllowed());

		// Validate the TCountryCode in the database
		List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
		assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void partialUpdateTCountryCodeWithPatch() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();

		// Update the tCountryCode using partial update
		TCountryCode partialUpdatedTCountryCode = new TCountryCode();
		partialUpdatedTCountryCode.setId(tCountryCode.getId());

		partialUpdatedTCountryCode.enteredBy(UPDATED_ENTERED_BY).modifiedBy(UPDATED_MODIFIED_BY).orgCustomerType(UPDATED_ORG_CUSTOMER_TYPE);

		restTCountryCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTCountryCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCountryCode))
			)
			.andExpect(status().isOk());

		// Validate the TCountryCode in the database
		List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
		assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
		TCountryCode testTCountryCode = tCountryCodeList.get(tCountryCodeList.size() - 1);
		assertThat(testTCountryCode.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
		assertThat(testTCountryCode.getCountryName()).isEqualTo(DEFAULT_COUNTRY_NAME);
		assertThat(testTCountryCode.getCountryNationality()).isEqualTo(DEFAULT_COUNTRY_NATIONALITY);
		assertThat(testTCountryCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTCountryCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
		assertThat(testTCountryCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTCountryCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
		assertThat(testTCountryCode.getOrgCustomerType()).isEqualTo(UPDATED_ORG_CUSTOMER_TYPE);
	}

	@Test
	@Transactional
	void fullUpdateTCountryCodeWithPatch() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();

		// Update the tCountryCode using partial update
		TCountryCode partialUpdatedTCountryCode = new TCountryCode();
		partialUpdatedTCountryCode.setId(tCountryCode.getId());

		partialUpdatedTCountryCode
			.countryCode(UPDATED_COUNTRY_CODE)
			.countryName(UPDATED_COUNTRY_NAME)
			.countryNationality(UPDATED_COUNTRY_NATIONALITY)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE)
			.orgCustomerType(UPDATED_ORG_CUSTOMER_TYPE);

		restTCountryCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTCountryCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCountryCode))
			)
			.andExpect(status().isOk());

		// Validate the TCountryCode in the database
		List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
		assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
		TCountryCode testTCountryCode = tCountryCodeList.get(tCountryCodeList.size() - 1);
		assertThat(testTCountryCode.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
		assertThat(testTCountryCode.getCountryName()).isEqualTo(UPDATED_COUNTRY_NAME);
		assertThat(testTCountryCode.getCountryNationality()).isEqualTo(UPDATED_COUNTRY_NATIONALITY);
		assertThat(testTCountryCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTCountryCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTCountryCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTCountryCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
		assertThat(testTCountryCode.getOrgCustomerType()).isEqualTo(UPDATED_ORG_CUSTOMER_TYPE);
	}

	@Test
	@Transactional
	void patchNonExistingTCountryCode() throws Exception {
		int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();
		tCountryCode.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTCountryCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, tCountryCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tCountryCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TCountryCode in the database
		List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
		assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithIdMismatchTCountryCode() throws Exception {
		int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();
		tCountryCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTCountryCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tCountryCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TCountryCode in the database
		List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
		assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithMissingIdPathParamTCountryCode() throws Exception {
		int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();
		tCountryCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTCountryCodeMockMvc
			.perform(
				patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCountryCode))
			)
			.andExpect(status().isMethodNotAllowed());

		// Validate the TCountryCode in the database
		List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
		assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void deleteTCountryCode() throws Exception {
		// Initialize the database
		tCountryCodeRepository.saveAndFlush(tCountryCode);

		int databaseSizeBeforeDelete = tCountryCodeRepository.findAll().size();

		// Delete the tCountryCode
		restTCountryCodeMockMvc
			.perform(delete(ENTITY_API_URL_ID, tCountryCode.getId()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());

		// Validate the database contains one less item
		List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
		assertThat(tCountryCodeList).hasSize(databaseSizeBeforeDelete - 1);
	}
}
