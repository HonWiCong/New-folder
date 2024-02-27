package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TCityCode;
import com.jhipster.app.domain.TStateCode;
import com.jhipster.app.repository.TCityCodeRepository;
import com.jhipster.app.service.criteria.TCityCodeCriteria;
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
 * Integration tests for the {@link TCityCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCityCodeResourceIT {

	private static final String DEFAULT_CITY_CODE = "AAAAAAAAAA";
	private static final String UPDATED_CITY_CODE = "BBBBBBBBBB";

	private static final String DEFAULT_CITY_NAME = "AAAAAAAAAA";
	private static final String UPDATED_CITY_NAME = "BBBBBBBBBB";

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

	private static final String ENTITY_API_URL = "/api/t-city-codes";
	private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

	private static Random random = new Random();
	private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

	@Autowired
	private TCityCodeRepository tCityCodeRepository;

	@Autowired
	private EntityManager em;

	@Autowired
	private MockMvc restTCityCodeMockMvc;

	private TCityCode tCityCode;

	/**
	 * Create an entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TCityCode createEntity(EntityManager em) {
		TCityCode tCityCode = new TCityCode()
			.cityCode(DEFAULT_CITY_CODE)
			.cityName(DEFAULT_CITY_NAME)
			.enteredBy(DEFAULT_ENTERED_BY)
			.enteredDate(DEFAULT_ENTERED_DATE)
			.modifiedBy(DEFAULT_MODIFIED_BY)
			.modifiedDate(DEFAULT_MODIFIED_DATE);
		return tCityCode;
	}

	/**
	 * Create an updated entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TCityCode createUpdatedEntity(EntityManager em) {
		TCityCode tCityCode = new TCityCode()
			.cityCode(UPDATED_CITY_CODE)
			.cityName(UPDATED_CITY_NAME)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);
		return tCityCode;
	}

	@BeforeEach
	public void initTest() {
		tCityCode = createEntity(em);
	}

	@Test
	@Transactional
	void createTCityCode() throws Exception {
		int databaseSizeBeforeCreate = tCityCodeRepository.findAll().size();
		// Create the TCityCode
		restTCityCodeMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCityCode)))
			.andExpect(status().isCreated());

		// Validate the TCityCode in the database
		List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
		assertThat(tCityCodeList).hasSize(databaseSizeBeforeCreate + 1);
		TCityCode testTCityCode = tCityCodeList.get(tCityCodeList.size() - 1);
		assertThat(testTCityCode.getCityCode()).isEqualTo(DEFAULT_CITY_CODE);
		assertThat(testTCityCode.getCityName()).isEqualTo(DEFAULT_CITY_NAME);
		assertThat(testTCityCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
		assertThat(testTCityCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
		assertThat(testTCityCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
		assertThat(testTCityCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void createTCityCodeWithExistingId() throws Exception {
		// Create the TCityCode with an existing ID
		tCityCode.setId(1L);

		int databaseSizeBeforeCreate = tCityCodeRepository.findAll().size();

		// An entity with an existing ID cannot be created, so this API call must fail
		restTCityCodeMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCityCode)))
			.andExpect(status().isBadRequest());

		// Validate the TCityCode in the database
		List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
		assertThat(tCityCodeList).hasSize(databaseSizeBeforeCreate);
	}

	@Test
	@Transactional
	void getAllTCityCodes() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList
		restTCityCodeMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.[*].id").value(hasItem(tCityCode.getId().intValue())))
			.andExpect(jsonPath("$.[*].cityCode").value(hasItem(DEFAULT_CITY_CODE)))
			.andExpect(jsonPath("$.[*].cityName").value(hasItem(DEFAULT_CITY_NAME)))
			.andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
			.andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
			.andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
			.andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
	}

	@Test
	@Transactional
	void getTCityCode() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get the tCityCode
		restTCityCodeMockMvc
			.perform(get(ENTITY_API_URL_ID, tCityCode.getId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.id").value(tCityCode.getId().intValue()))
			.andExpect(jsonPath("$.cityCode").value(DEFAULT_CITY_CODE))
			.andExpect(jsonPath("$.cityName").value(DEFAULT_CITY_NAME))
			.andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
			.andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
			.andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
			.andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
	}

	@Test
	@Transactional
	void getTCityCodesByIdFiltering() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		Long id = tCityCode.getId();

		defaultTCityCodeShouldBeFound("id.equals=" + id);
		defaultTCityCodeShouldNotBeFound("id.notEquals=" + id);

		defaultTCityCodeShouldBeFound("id.greaterThanOrEqual=" + id);
		defaultTCityCodeShouldNotBeFound("id.greaterThan=" + id);

		defaultTCityCodeShouldBeFound("id.lessThanOrEqual=" + id);
		defaultTCityCodeShouldNotBeFound("id.lessThan=" + id);
	}

	@Test
	@Transactional
	void getAllTCityCodesByCityCodeIsEqualToSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where cityCode equals to DEFAULT_CITY_CODE
		defaultTCityCodeShouldBeFound("cityCode.equals=" + DEFAULT_CITY_CODE);

		// Get all the tCityCodeList where cityCode equals to UPDATED_CITY_CODE
		defaultTCityCodeShouldNotBeFound("cityCode.equals=" + UPDATED_CITY_CODE);
	}

	@Test
	@Transactional
	void getAllTCityCodesByCityCodeIsInShouldWork() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where cityCode in DEFAULT_CITY_CODE or UPDATED_CITY_CODE
		defaultTCityCodeShouldBeFound("cityCode.in=" + DEFAULT_CITY_CODE + "," + UPDATED_CITY_CODE);

		// Get all the tCityCodeList where cityCode equals to UPDATED_CITY_CODE
		defaultTCityCodeShouldNotBeFound("cityCode.in=" + UPDATED_CITY_CODE);
	}

	@Test
	@Transactional
	void getAllTCityCodesByCityCodeIsNullOrNotNull() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where cityCode is not null
		defaultTCityCodeShouldBeFound("cityCode.specified=true");

		// Get all the tCityCodeList where cityCode is null
		defaultTCityCodeShouldNotBeFound("cityCode.specified=false");
	}

	@Test
	@Transactional
	void getAllTCityCodesByCityCodeContainsSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where cityCode contains DEFAULT_CITY_CODE
		defaultTCityCodeShouldBeFound("cityCode.contains=" + DEFAULT_CITY_CODE);

		// Get all the tCityCodeList where cityCode contains UPDATED_CITY_CODE
		defaultTCityCodeShouldNotBeFound("cityCode.contains=" + UPDATED_CITY_CODE);
	}

	@Test
	@Transactional
	void getAllTCityCodesByCityCodeNotContainsSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where cityCode does not contain DEFAULT_CITY_CODE
		defaultTCityCodeShouldNotBeFound("cityCode.doesNotContain=" + DEFAULT_CITY_CODE);

		// Get all the tCityCodeList where cityCode does not contain UPDATED_CITY_CODE
		defaultTCityCodeShouldBeFound("cityCode.doesNotContain=" + UPDATED_CITY_CODE);
	}

	@Test
	@Transactional
	void getAllTCityCodesByCityNameIsEqualToSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where cityName equals to DEFAULT_CITY_NAME
		defaultTCityCodeShouldBeFound("cityName.equals=" + DEFAULT_CITY_NAME);

		// Get all the tCityCodeList where cityName equals to UPDATED_CITY_NAME
		defaultTCityCodeShouldNotBeFound("cityName.equals=" + UPDATED_CITY_NAME);
	}

	@Test
	@Transactional
	void getAllTCityCodesByCityNameIsInShouldWork() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where cityName in DEFAULT_CITY_NAME or UPDATED_CITY_NAME
		defaultTCityCodeShouldBeFound("cityName.in=" + DEFAULT_CITY_NAME + "," + UPDATED_CITY_NAME);

		// Get all the tCityCodeList where cityName equals to UPDATED_CITY_NAME
		defaultTCityCodeShouldNotBeFound("cityName.in=" + UPDATED_CITY_NAME);
	}

	@Test
	@Transactional
	void getAllTCityCodesByCityNameIsNullOrNotNull() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where cityName is not null
		defaultTCityCodeShouldBeFound("cityName.specified=true");

		// Get all the tCityCodeList where cityName is null
		defaultTCityCodeShouldNotBeFound("cityName.specified=false");
	}

	@Test
	@Transactional
	void getAllTCityCodesByCityNameContainsSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where cityName contains DEFAULT_CITY_NAME
		defaultTCityCodeShouldBeFound("cityName.contains=" + DEFAULT_CITY_NAME);

		// Get all the tCityCodeList where cityName contains UPDATED_CITY_NAME
		defaultTCityCodeShouldNotBeFound("cityName.contains=" + UPDATED_CITY_NAME);
	}

	@Test
	@Transactional
	void getAllTCityCodesByCityNameNotContainsSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where cityName does not contain DEFAULT_CITY_NAME
		defaultTCityCodeShouldNotBeFound("cityName.doesNotContain=" + DEFAULT_CITY_NAME);

		// Get all the tCityCodeList where cityName does not contain UPDATED_CITY_NAME
		defaultTCityCodeShouldBeFound("cityName.doesNotContain=" + UPDATED_CITY_NAME);
	}

	@Test
	@Transactional
	void getAllTCityCodesByEnteredByIsEqualToSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where enteredBy equals to DEFAULT_ENTERED_BY
		defaultTCityCodeShouldBeFound("enteredBy.equals=" + DEFAULT_ENTERED_BY);

		// Get all the tCityCodeList where enteredBy equals to UPDATED_ENTERED_BY
		defaultTCityCodeShouldNotBeFound("enteredBy.equals=" + UPDATED_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTCityCodesByEnteredByIsInShouldWork() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where enteredBy in DEFAULT_ENTERED_BY or UPDATED_ENTERED_BY
		defaultTCityCodeShouldBeFound("enteredBy.in=" + DEFAULT_ENTERED_BY + "," + UPDATED_ENTERED_BY);

		// Get all the tCityCodeList where enteredBy equals to UPDATED_ENTERED_BY
		defaultTCityCodeShouldNotBeFound("enteredBy.in=" + UPDATED_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTCityCodesByEnteredByIsNullOrNotNull() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where enteredBy is not null
		defaultTCityCodeShouldBeFound("enteredBy.specified=true");

		// Get all the tCityCodeList where enteredBy is null
		defaultTCityCodeShouldNotBeFound("enteredBy.specified=false");
	}

	@Test
	@Transactional
	void getAllTCityCodesByEnteredByIsGreaterThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where enteredBy is greater than or equal to DEFAULT_ENTERED_BY
		defaultTCityCodeShouldBeFound("enteredBy.greaterThanOrEqual=" + DEFAULT_ENTERED_BY);

		// Get all the tCityCodeList where enteredBy is greater than or equal to UPDATED_ENTERED_BY
		defaultTCityCodeShouldNotBeFound("enteredBy.greaterThanOrEqual=" + UPDATED_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTCityCodesByEnteredByIsLessThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where enteredBy is less than or equal to DEFAULT_ENTERED_BY
		defaultTCityCodeShouldBeFound("enteredBy.lessThanOrEqual=" + DEFAULT_ENTERED_BY);

		// Get all the tCityCodeList where enteredBy is less than or equal to SMALLER_ENTERED_BY
		defaultTCityCodeShouldNotBeFound("enteredBy.lessThanOrEqual=" + SMALLER_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTCityCodesByEnteredByIsLessThanSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where enteredBy is less than DEFAULT_ENTERED_BY
		defaultTCityCodeShouldNotBeFound("enteredBy.lessThan=" + DEFAULT_ENTERED_BY);

		// Get all the tCityCodeList where enteredBy is less than UPDATED_ENTERED_BY
		defaultTCityCodeShouldBeFound("enteredBy.lessThan=" + UPDATED_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTCityCodesByEnteredByIsGreaterThanSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where enteredBy is greater than DEFAULT_ENTERED_BY
		defaultTCityCodeShouldNotBeFound("enteredBy.greaterThan=" + DEFAULT_ENTERED_BY);

		// Get all the tCityCodeList where enteredBy is greater than SMALLER_ENTERED_BY
		defaultTCityCodeShouldBeFound("enteredBy.greaterThan=" + SMALLER_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTCityCodesByEnteredDateIsEqualToSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where enteredDate equals to DEFAULT_ENTERED_DATE
		defaultTCityCodeShouldBeFound("enteredDate.equals=" + DEFAULT_ENTERED_DATE);

		// Get all the tCityCodeList where enteredDate equals to UPDATED_ENTERED_DATE
		defaultTCityCodeShouldNotBeFound("enteredDate.equals=" + UPDATED_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTCityCodesByEnteredDateIsInShouldWork() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where enteredDate in DEFAULT_ENTERED_DATE or UPDATED_ENTERED_DATE
		defaultTCityCodeShouldBeFound("enteredDate.in=" + DEFAULT_ENTERED_DATE + "," + UPDATED_ENTERED_DATE);

		// Get all the tCityCodeList where enteredDate equals to UPDATED_ENTERED_DATE
		defaultTCityCodeShouldNotBeFound("enteredDate.in=" + UPDATED_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTCityCodesByEnteredDateIsNullOrNotNull() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where enteredDate is not null
		defaultTCityCodeShouldBeFound("enteredDate.specified=true");

		// Get all the tCityCodeList where enteredDate is null
		defaultTCityCodeShouldNotBeFound("enteredDate.specified=false");
	}

	@Test
	@Transactional
	void getAllTCityCodesByEnteredDateIsGreaterThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where enteredDate is greater than or equal to DEFAULT_ENTERED_DATE
		defaultTCityCodeShouldBeFound("enteredDate.greaterThanOrEqual=" + DEFAULT_ENTERED_DATE);

		// Get all the tCityCodeList where enteredDate is greater than or equal to UPDATED_ENTERED_DATE
		defaultTCityCodeShouldNotBeFound("enteredDate.greaterThanOrEqual=" + UPDATED_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTCityCodesByEnteredDateIsLessThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where enteredDate is less than or equal to DEFAULT_ENTERED_DATE
		defaultTCityCodeShouldBeFound("enteredDate.lessThanOrEqual=" + DEFAULT_ENTERED_DATE);

		// Get all the tCityCodeList where enteredDate is less than or equal to SMALLER_ENTERED_DATE
		defaultTCityCodeShouldNotBeFound("enteredDate.lessThanOrEqual=" + SMALLER_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTCityCodesByEnteredDateIsLessThanSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where enteredDate is less than DEFAULT_ENTERED_DATE
		defaultTCityCodeShouldNotBeFound("enteredDate.lessThan=" + DEFAULT_ENTERED_DATE);

		// Get all the tCityCodeList where enteredDate is less than UPDATED_ENTERED_DATE
		defaultTCityCodeShouldBeFound("enteredDate.lessThan=" + UPDATED_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTCityCodesByEnteredDateIsGreaterThanSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where enteredDate is greater than DEFAULT_ENTERED_DATE
		defaultTCityCodeShouldNotBeFound("enteredDate.greaterThan=" + DEFAULT_ENTERED_DATE);

		// Get all the tCityCodeList where enteredDate is greater than SMALLER_ENTERED_DATE
		defaultTCityCodeShouldBeFound("enteredDate.greaterThan=" + SMALLER_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTCityCodesByModifiedByIsEqualToSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where modifiedBy equals to DEFAULT_MODIFIED_BY
		defaultTCityCodeShouldBeFound("modifiedBy.equals=" + DEFAULT_MODIFIED_BY);

		// Get all the tCityCodeList where modifiedBy equals to UPDATED_MODIFIED_BY
		defaultTCityCodeShouldNotBeFound("modifiedBy.equals=" + UPDATED_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTCityCodesByModifiedByIsInShouldWork() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where modifiedBy in DEFAULT_MODIFIED_BY or UPDATED_MODIFIED_BY
		defaultTCityCodeShouldBeFound("modifiedBy.in=" + DEFAULT_MODIFIED_BY + "," + UPDATED_MODIFIED_BY);

		// Get all the tCityCodeList where modifiedBy equals to UPDATED_MODIFIED_BY
		defaultTCityCodeShouldNotBeFound("modifiedBy.in=" + UPDATED_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTCityCodesByModifiedByIsNullOrNotNull() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where modifiedBy is not null
		defaultTCityCodeShouldBeFound("modifiedBy.specified=true");

		// Get all the tCityCodeList where modifiedBy is null
		defaultTCityCodeShouldNotBeFound("modifiedBy.specified=false");
	}

	@Test
	@Transactional
	void getAllTCityCodesByModifiedByIsGreaterThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where modifiedBy is greater than or equal to DEFAULT_MODIFIED_BY
		defaultTCityCodeShouldBeFound("modifiedBy.greaterThanOrEqual=" + DEFAULT_MODIFIED_BY);

		// Get all the tCityCodeList where modifiedBy is greater than or equal to UPDATED_MODIFIED_BY
		defaultTCityCodeShouldNotBeFound("modifiedBy.greaterThanOrEqual=" + UPDATED_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTCityCodesByModifiedByIsLessThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where modifiedBy is less than or equal to DEFAULT_MODIFIED_BY
		defaultTCityCodeShouldBeFound("modifiedBy.lessThanOrEqual=" + DEFAULT_MODIFIED_BY);

		// Get all the tCityCodeList where modifiedBy is less than or equal to SMALLER_MODIFIED_BY
		defaultTCityCodeShouldNotBeFound("modifiedBy.lessThanOrEqual=" + SMALLER_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTCityCodesByModifiedByIsLessThanSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where modifiedBy is less than DEFAULT_MODIFIED_BY
		defaultTCityCodeShouldNotBeFound("modifiedBy.lessThan=" + DEFAULT_MODIFIED_BY);

		// Get all the tCityCodeList where modifiedBy is less than UPDATED_MODIFIED_BY
		defaultTCityCodeShouldBeFound("modifiedBy.lessThan=" + UPDATED_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTCityCodesByModifiedByIsGreaterThanSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where modifiedBy is greater than DEFAULT_MODIFIED_BY
		defaultTCityCodeShouldNotBeFound("modifiedBy.greaterThan=" + DEFAULT_MODIFIED_BY);

		// Get all the tCityCodeList where modifiedBy is greater than SMALLER_MODIFIED_BY
		defaultTCityCodeShouldBeFound("modifiedBy.greaterThan=" + SMALLER_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTCityCodesByModifiedDateIsEqualToSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where modifiedDate equals to DEFAULT_MODIFIED_DATE
		defaultTCityCodeShouldBeFound("modifiedDate.equals=" + DEFAULT_MODIFIED_DATE);

		// Get all the tCityCodeList where modifiedDate equals to UPDATED_MODIFIED_DATE
		defaultTCityCodeShouldNotBeFound("modifiedDate.equals=" + UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTCityCodesByModifiedDateIsInShouldWork() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where modifiedDate in DEFAULT_MODIFIED_DATE or UPDATED_MODIFIED_DATE
		defaultTCityCodeShouldBeFound("modifiedDate.in=" + DEFAULT_MODIFIED_DATE + "," + UPDATED_MODIFIED_DATE);

		// Get all the tCityCodeList where modifiedDate equals to UPDATED_MODIFIED_DATE
		defaultTCityCodeShouldNotBeFound("modifiedDate.in=" + UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTCityCodesByModifiedDateIsNullOrNotNull() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where modifiedDate is not null
		defaultTCityCodeShouldBeFound("modifiedDate.specified=true");

		// Get all the tCityCodeList where modifiedDate is null
		defaultTCityCodeShouldNotBeFound("modifiedDate.specified=false");
	}

	@Test
	@Transactional
	void getAllTCityCodesByModifiedDateIsGreaterThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where modifiedDate is greater than or equal to DEFAULT_MODIFIED_DATE
		defaultTCityCodeShouldBeFound("modifiedDate.greaterThanOrEqual=" + DEFAULT_MODIFIED_DATE);

		// Get all the tCityCodeList where modifiedDate is greater than or equal to UPDATED_MODIFIED_DATE
		defaultTCityCodeShouldNotBeFound("modifiedDate.greaterThanOrEqual=" + UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTCityCodesByModifiedDateIsLessThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where modifiedDate is less than or equal to DEFAULT_MODIFIED_DATE
		defaultTCityCodeShouldBeFound("modifiedDate.lessThanOrEqual=" + DEFAULT_MODIFIED_DATE);

		// Get all the tCityCodeList where modifiedDate is less than or equal to SMALLER_MODIFIED_DATE
		defaultTCityCodeShouldNotBeFound("modifiedDate.lessThanOrEqual=" + SMALLER_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTCityCodesByModifiedDateIsLessThanSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where modifiedDate is less than DEFAULT_MODIFIED_DATE
		defaultTCityCodeShouldNotBeFound("modifiedDate.lessThan=" + DEFAULT_MODIFIED_DATE);

		// Get all the tCityCodeList where modifiedDate is less than UPDATED_MODIFIED_DATE
		defaultTCityCodeShouldBeFound("modifiedDate.lessThan=" + UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTCityCodesByModifiedDateIsGreaterThanSomething() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		// Get all the tCityCodeList where modifiedDate is greater than DEFAULT_MODIFIED_DATE
		defaultTCityCodeShouldNotBeFound("modifiedDate.greaterThan=" + DEFAULT_MODIFIED_DATE);

		// Get all the tCityCodeList where modifiedDate is greater than SMALLER_MODIFIED_DATE
		defaultTCityCodeShouldBeFound("modifiedDate.greaterThan=" + SMALLER_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTCityCodesByTStateCodeIsEqualToSomething() throws Exception {
		TStateCode tStateCode;
		if (TestUtil.findAll(em, TStateCode.class).isEmpty()) {
			tCityCodeRepository.saveAndFlush(tCityCode);
			tStateCode = TStateCodeResourceIT.createEntity(em);
		} else {
			tStateCode = TestUtil.findAll(em, TStateCode.class).get(0);
		}
		em.persist(tStateCode);
		em.flush();
		tCityCode.setTStateCode(tStateCode);
		tCityCodeRepository.saveAndFlush(tCityCode);
		Long tStateCodeId = tStateCode.getId();

		// Get all the tCityCodeList where tStateCode equals to tStateCodeId
		defaultTCityCodeShouldBeFound("tStateCodeId.equals=" + tStateCodeId);

		// Get all the tCityCodeList where tStateCode equals to (tStateCodeId + 1)
		defaultTCityCodeShouldNotBeFound("tStateCodeId.equals=" + (tStateCodeId + 1));
	}

	/**
	 * Executes the search, and checks that the default entity is returned.
	 */
	private void defaultTCityCodeShouldBeFound(String filter) throws Exception {
		restTCityCodeMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.[*].id").value(hasItem(tCityCode.getId().intValue())))
			.andExpect(jsonPath("$.[*].cityCode").value(hasItem(DEFAULT_CITY_CODE)))
			.andExpect(jsonPath("$.[*].cityName").value(hasItem(DEFAULT_CITY_NAME)))
			.andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
			.andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
			.andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
			.andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));

		// Check, that the count call also returns 1
		restTCityCodeMockMvc
			.perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().string("1"));
	}

	/**
	 * Executes the search, and checks that the default entity is not returned.
	 */
	private void defaultTCityCodeShouldNotBeFound(String filter) throws Exception {
		restTCityCodeMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$").isEmpty());

		// Check, that the count call also returns 0
		restTCityCodeMockMvc
			.perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().string("0"));
	}

	@Test
	@Transactional
	void getNonExistingTCityCode() throws Exception {
		// Get the tCityCode
		restTCityCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	void putExistingTCityCode() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();

		// Update the tCityCode
		TCityCode updatedTCityCode = tCityCodeRepository.findById(tCityCode.getId()).get();
		// Disconnect from session so that the updates on updatedTCityCode are not directly saved in db
		em.detach(updatedTCityCode);
		updatedTCityCode
			.cityCode(UPDATED_CITY_CODE)
			.cityName(UPDATED_CITY_NAME)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTCityCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, updatedTCityCode.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(updatedTCityCode))
			)
			.andExpect(status().isOk());

		// Validate the TCityCode in the database
		List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
		assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
		TCityCode testTCityCode = tCityCodeList.get(tCityCodeList.size() - 1);
		assertThat(testTCityCode.getCityCode()).isEqualTo(UPDATED_CITY_CODE);
		assertThat(testTCityCode.getCityName()).isEqualTo(UPDATED_CITY_NAME);
		assertThat(testTCityCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTCityCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTCityCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTCityCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void putNonExistingTCityCode() throws Exception {
		int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();
		tCityCode.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTCityCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, tCityCode.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tCityCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TCityCode in the database
		List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
		assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithIdMismatchTCityCode() throws Exception {
		int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();
		tCityCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTCityCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tCityCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TCityCode in the database
		List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
		assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithMissingIdPathParamTCityCode() throws Exception {
		int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();
		tCityCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTCityCodeMockMvc
			.perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCityCode)))
			.andExpect(status().isMethodNotAllowed());

		// Validate the TCityCode in the database
		List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
		assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void partialUpdateTCityCodeWithPatch() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();

		// Update the tCityCode using partial update
		TCityCode partialUpdatedTCityCode = new TCityCode();
		partialUpdatedTCityCode.setId(tCityCode.getId());

		partialUpdatedTCityCode.cityCode(UPDATED_CITY_CODE).enteredBy(UPDATED_ENTERED_BY).enteredDate(UPDATED_ENTERED_DATE);

		restTCityCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTCityCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCityCode))
			)
			.andExpect(status().isOk());

		// Validate the TCityCode in the database
		List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
		assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
		TCityCode testTCityCode = tCityCodeList.get(tCityCodeList.size() - 1);
		assertThat(testTCityCode.getCityCode()).isEqualTo(UPDATED_CITY_CODE);
		assertThat(testTCityCode.getCityName()).isEqualTo(DEFAULT_CITY_NAME);
		assertThat(testTCityCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTCityCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTCityCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
		assertThat(testTCityCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void fullUpdateTCityCodeWithPatch() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();

		// Update the tCityCode using partial update
		TCityCode partialUpdatedTCityCode = new TCityCode();
		partialUpdatedTCityCode.setId(tCityCode.getId());

		partialUpdatedTCityCode
			.cityCode(UPDATED_CITY_CODE)
			.cityName(UPDATED_CITY_NAME)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTCityCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTCityCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCityCode))
			)
			.andExpect(status().isOk());

		// Validate the TCityCode in the database
		List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
		assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
		TCityCode testTCityCode = tCityCodeList.get(tCityCodeList.size() - 1);
		assertThat(testTCityCode.getCityCode()).isEqualTo(UPDATED_CITY_CODE);
		assertThat(testTCityCode.getCityName()).isEqualTo(UPDATED_CITY_NAME);
		assertThat(testTCityCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTCityCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTCityCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTCityCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void patchNonExistingTCityCode() throws Exception {
		int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();
		tCityCode.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTCityCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, tCityCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tCityCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TCityCode in the database
		List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
		assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithIdMismatchTCityCode() throws Exception {
		int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();
		tCityCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTCityCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tCityCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TCityCode in the database
		List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
		assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithMissingIdPathParamTCityCode() throws Exception {
		int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();
		tCityCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTCityCodeMockMvc
			.perform(
				patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCityCode))
			)
			.andExpect(status().isMethodNotAllowed());

		// Validate the TCityCode in the database
		List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
		assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void deleteTCityCode() throws Exception {
		// Initialize the database
		tCityCodeRepository.saveAndFlush(tCityCode);

		int databaseSizeBeforeDelete = tCityCodeRepository.findAll().size();

		// Delete the tCityCode
		restTCityCodeMockMvc
			.perform(delete(ENTITY_API_URL_ID, tCityCode.getId()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());

		// Validate the database contains one less item
		List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
		assertThat(tCityCodeList).hasSize(databaseSizeBeforeDelete - 1);
	}
}
