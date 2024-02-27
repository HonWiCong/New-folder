package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TCityCode;
import com.jhipster.app.domain.TCountryCode;
import com.jhipster.app.domain.TStateCode;
import com.jhipster.app.repository.TStateCodeRepository;
import com.jhipster.app.service.criteria.TStateCodeCriteria;
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
 * Integration tests for the {@link TStateCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TStateCodeResourceIT {

	private static final String DEFAULT_STATE_NAME = "AAAAAAAAAA";
	private static final String UPDATED_STATE_NAME = "BBBBBBBBBB";

	private static final String DEFAULT_STATE_CODE = "AAAAAAAAAA";
	private static final String UPDATED_STATE_CODE = "BBBBBBBBBB";

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

	private static final String ENTITY_API_URL = "/api/t-state-codes";
	private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

	private static Random random = new Random();
	private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

	@Autowired
	private TStateCodeRepository tStateCodeRepository;

	@Autowired
	private EntityManager em;

	@Autowired
	private MockMvc restTStateCodeMockMvc;

	private TStateCode tStateCode;

	/**
	 * Create an entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TStateCode createEntity(EntityManager em) {
		TStateCode tStateCode = new TStateCode()
			.stateName(DEFAULT_STATE_NAME)
			.stateCode(DEFAULT_STATE_CODE)
			.enteredBy(DEFAULT_ENTERED_BY)
			.enteredDate(DEFAULT_ENTERED_DATE)
			.modifiedBy(DEFAULT_MODIFIED_BY)
			.modifiedDate(DEFAULT_MODIFIED_DATE);
		return tStateCode;
	}

	/**
	 * Create an updated entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TStateCode createUpdatedEntity(EntityManager em) {
		TStateCode tStateCode = new TStateCode()
			.stateName(UPDATED_STATE_NAME)
			.stateCode(UPDATED_STATE_CODE)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);
		return tStateCode;
	}

	@BeforeEach
	public void initTest() {
		tStateCode = createEntity(em);
	}

	@Test
	@Transactional
	void createTStateCode() throws Exception {
		int databaseSizeBeforeCreate = tStateCodeRepository.findAll().size();
		// Create the TStateCode
		restTStateCodeMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tStateCode)))
			.andExpect(status().isCreated());

		// Validate the TStateCode in the database
		List<TStateCode> tStateCodeList = tStateCodeRepository.findAll();
		assertThat(tStateCodeList).hasSize(databaseSizeBeforeCreate + 1);
		TStateCode testTStateCode = tStateCodeList.get(tStateCodeList.size() - 1);
		assertThat(testTStateCode.getStateName()).isEqualTo(DEFAULT_STATE_NAME);
		assertThat(testTStateCode.getStateCode()).isEqualTo(DEFAULT_STATE_CODE);
		assertThat(testTStateCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
		assertThat(testTStateCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
		assertThat(testTStateCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
		assertThat(testTStateCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void createTStateCodeWithExistingId() throws Exception {
		// Create the TStateCode with an existing ID
		tStateCode.setId(1L);

		int databaseSizeBeforeCreate = tStateCodeRepository.findAll().size();

		// An entity with an existing ID cannot be created, so this API call must fail
		restTStateCodeMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tStateCode)))
			.andExpect(status().isBadRequest());

		// Validate the TStateCode in the database
		List<TStateCode> tStateCodeList = tStateCodeRepository.findAll();
		assertThat(tStateCodeList).hasSize(databaseSizeBeforeCreate);
	}

	@Test
	@Transactional
	void getAllTStateCodes() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList
		restTStateCodeMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.[*].id").value(hasItem(tStateCode.getId().intValue())))
			.andExpect(jsonPath("$.[*].stateName").value(hasItem(DEFAULT_STATE_NAME)))
			.andExpect(jsonPath("$.[*].stateCode").value(hasItem(DEFAULT_STATE_CODE)))
			.andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
			.andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
			.andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
			.andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
	}

	@Test
	@Transactional
	void getTStateCode() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get the tStateCode
		restTStateCodeMockMvc
			.perform(get(ENTITY_API_URL_ID, tStateCode.getId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.id").value(tStateCode.getId().intValue()))
			.andExpect(jsonPath("$.stateName").value(DEFAULT_STATE_NAME))
			.andExpect(jsonPath("$.stateCode").value(DEFAULT_STATE_CODE))
			.andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
			.andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
			.andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
			.andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
	}

	@Test
	@Transactional
	void getTStateCodesByIdFiltering() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		Long id = tStateCode.getId();

		defaultTStateCodeShouldBeFound("id.equals=" + id);
		defaultTStateCodeShouldNotBeFound("id.notEquals=" + id);

		defaultTStateCodeShouldBeFound("id.greaterThanOrEqual=" + id);
		defaultTStateCodeShouldNotBeFound("id.greaterThan=" + id);

		defaultTStateCodeShouldBeFound("id.lessThanOrEqual=" + id);
		defaultTStateCodeShouldNotBeFound("id.lessThan=" + id);
	}

	@Test
	@Transactional
	void getAllTStateCodesByStateNameIsEqualToSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where stateName equals to DEFAULT_STATE_NAME
		defaultTStateCodeShouldBeFound("stateName.equals=" + DEFAULT_STATE_NAME);

		// Get all the tStateCodeList where stateName equals to UPDATED_STATE_NAME
		defaultTStateCodeShouldNotBeFound("stateName.equals=" + UPDATED_STATE_NAME);
	}

	@Test
	@Transactional
	void getAllTStateCodesByStateNameIsInShouldWork() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where stateName in DEFAULT_STATE_NAME or UPDATED_STATE_NAME
		defaultTStateCodeShouldBeFound("stateName.in=" + DEFAULT_STATE_NAME + "," + UPDATED_STATE_NAME);

		// Get all the tStateCodeList where stateName equals to UPDATED_STATE_NAME
		defaultTStateCodeShouldNotBeFound("stateName.in=" + UPDATED_STATE_NAME);
	}

	@Test
	@Transactional
	void getAllTStateCodesByStateNameIsNullOrNotNull() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where stateName is not null
		defaultTStateCodeShouldBeFound("stateName.specified=true");

		// Get all the tStateCodeList where stateName is null
		defaultTStateCodeShouldNotBeFound("stateName.specified=false");
	}

	@Test
	@Transactional
	void getAllTStateCodesByStateNameContainsSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where stateName contains DEFAULT_STATE_NAME
		defaultTStateCodeShouldBeFound("stateName.contains=" + DEFAULT_STATE_NAME);

		// Get all the tStateCodeList where stateName contains UPDATED_STATE_NAME
		defaultTStateCodeShouldNotBeFound("stateName.contains=" + UPDATED_STATE_NAME);
	}

	@Test
	@Transactional
	void getAllTStateCodesByStateNameNotContainsSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where stateName does not contain DEFAULT_STATE_NAME
		defaultTStateCodeShouldNotBeFound("stateName.doesNotContain=" + DEFAULT_STATE_NAME);

		// Get all the tStateCodeList where stateName does not contain UPDATED_STATE_NAME
		defaultTStateCodeShouldBeFound("stateName.doesNotContain=" + UPDATED_STATE_NAME);
	}

	@Test
	@Transactional
	void getAllTStateCodesByStateCodeIsEqualToSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where stateCode equals to DEFAULT_STATE_CODE
		defaultTStateCodeShouldBeFound("stateCode.equals=" + DEFAULT_STATE_CODE);

		// Get all the tStateCodeList where stateCode equals to UPDATED_STATE_CODE
		defaultTStateCodeShouldNotBeFound("stateCode.equals=" + UPDATED_STATE_CODE);
	}

	@Test
	@Transactional
	void getAllTStateCodesByStateCodeIsInShouldWork() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where stateCode in DEFAULT_STATE_CODE or UPDATED_STATE_CODE
		defaultTStateCodeShouldBeFound("stateCode.in=" + DEFAULT_STATE_CODE + "," + UPDATED_STATE_CODE);

		// Get all the tStateCodeList where stateCode equals to UPDATED_STATE_CODE
		defaultTStateCodeShouldNotBeFound("stateCode.in=" + UPDATED_STATE_CODE);
	}

	@Test
	@Transactional
	void getAllTStateCodesByStateCodeIsNullOrNotNull() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where stateCode is not null
		defaultTStateCodeShouldBeFound("stateCode.specified=true");

		// Get all the tStateCodeList where stateCode is null
		defaultTStateCodeShouldNotBeFound("stateCode.specified=false");
	}

	@Test
	@Transactional
	void getAllTStateCodesByStateCodeContainsSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where stateCode contains DEFAULT_STATE_CODE
		defaultTStateCodeShouldBeFound("stateCode.contains=" + DEFAULT_STATE_CODE);

		// Get all the tStateCodeList where stateCode contains UPDATED_STATE_CODE
		defaultTStateCodeShouldNotBeFound("stateCode.contains=" + UPDATED_STATE_CODE);
	}

	@Test
	@Transactional
	void getAllTStateCodesByStateCodeNotContainsSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where stateCode does not contain DEFAULT_STATE_CODE
		defaultTStateCodeShouldNotBeFound("stateCode.doesNotContain=" + DEFAULT_STATE_CODE);

		// Get all the tStateCodeList where stateCode does not contain UPDATED_STATE_CODE
		defaultTStateCodeShouldBeFound("stateCode.doesNotContain=" + UPDATED_STATE_CODE);
	}

	@Test
	@Transactional
	void getAllTStateCodesByEnteredByIsEqualToSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where enteredBy equals to DEFAULT_ENTERED_BY
		defaultTStateCodeShouldBeFound("enteredBy.equals=" + DEFAULT_ENTERED_BY);

		// Get all the tStateCodeList where enteredBy equals to UPDATED_ENTERED_BY
		defaultTStateCodeShouldNotBeFound("enteredBy.equals=" + UPDATED_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTStateCodesByEnteredByIsInShouldWork() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where enteredBy in DEFAULT_ENTERED_BY or UPDATED_ENTERED_BY
		defaultTStateCodeShouldBeFound("enteredBy.in=" + DEFAULT_ENTERED_BY + "," + UPDATED_ENTERED_BY);

		// Get all the tStateCodeList where enteredBy equals to UPDATED_ENTERED_BY
		defaultTStateCodeShouldNotBeFound("enteredBy.in=" + UPDATED_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTStateCodesByEnteredByIsNullOrNotNull() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where enteredBy is not null
		defaultTStateCodeShouldBeFound("enteredBy.specified=true");

		// Get all the tStateCodeList where enteredBy is null
		defaultTStateCodeShouldNotBeFound("enteredBy.specified=false");
	}

	@Test
	@Transactional
	void getAllTStateCodesByEnteredByIsGreaterThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where enteredBy is greater than or equal to DEFAULT_ENTERED_BY
		defaultTStateCodeShouldBeFound("enteredBy.greaterThanOrEqual=" + DEFAULT_ENTERED_BY);

		// Get all the tStateCodeList where enteredBy is greater than or equal to UPDATED_ENTERED_BY
		defaultTStateCodeShouldNotBeFound("enteredBy.greaterThanOrEqual=" + UPDATED_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTStateCodesByEnteredByIsLessThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where enteredBy is less than or equal to DEFAULT_ENTERED_BY
		defaultTStateCodeShouldBeFound("enteredBy.lessThanOrEqual=" + DEFAULT_ENTERED_BY);

		// Get all the tStateCodeList where enteredBy is less than or equal to SMALLER_ENTERED_BY
		defaultTStateCodeShouldNotBeFound("enteredBy.lessThanOrEqual=" + SMALLER_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTStateCodesByEnteredByIsLessThanSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where enteredBy is less than DEFAULT_ENTERED_BY
		defaultTStateCodeShouldNotBeFound("enteredBy.lessThan=" + DEFAULT_ENTERED_BY);

		// Get all the tStateCodeList where enteredBy is less than UPDATED_ENTERED_BY
		defaultTStateCodeShouldBeFound("enteredBy.lessThan=" + UPDATED_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTStateCodesByEnteredByIsGreaterThanSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where enteredBy is greater than DEFAULT_ENTERED_BY
		defaultTStateCodeShouldNotBeFound("enteredBy.greaterThan=" + DEFAULT_ENTERED_BY);

		// Get all the tStateCodeList where enteredBy is greater than SMALLER_ENTERED_BY
		defaultTStateCodeShouldBeFound("enteredBy.greaterThan=" + SMALLER_ENTERED_BY);
	}

	@Test
	@Transactional
	void getAllTStateCodesByEnteredDateIsEqualToSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where enteredDate equals to DEFAULT_ENTERED_DATE
		defaultTStateCodeShouldBeFound("enteredDate.equals=" + DEFAULT_ENTERED_DATE);

		// Get all the tStateCodeList where enteredDate equals to UPDATED_ENTERED_DATE
		defaultTStateCodeShouldNotBeFound("enteredDate.equals=" + UPDATED_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTStateCodesByEnteredDateIsInShouldWork() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where enteredDate in DEFAULT_ENTERED_DATE or UPDATED_ENTERED_DATE
		defaultTStateCodeShouldBeFound("enteredDate.in=" + DEFAULT_ENTERED_DATE + "," + UPDATED_ENTERED_DATE);

		// Get all the tStateCodeList where enteredDate equals to UPDATED_ENTERED_DATE
		defaultTStateCodeShouldNotBeFound("enteredDate.in=" + UPDATED_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTStateCodesByEnteredDateIsNullOrNotNull() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where enteredDate is not null
		defaultTStateCodeShouldBeFound("enteredDate.specified=true");

		// Get all the tStateCodeList where enteredDate is null
		defaultTStateCodeShouldNotBeFound("enteredDate.specified=false");
	}

	@Test
	@Transactional
	void getAllTStateCodesByEnteredDateIsGreaterThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where enteredDate is greater than or equal to DEFAULT_ENTERED_DATE
		defaultTStateCodeShouldBeFound("enteredDate.greaterThanOrEqual=" + DEFAULT_ENTERED_DATE);

		// Get all the tStateCodeList where enteredDate is greater than or equal to UPDATED_ENTERED_DATE
		defaultTStateCodeShouldNotBeFound("enteredDate.greaterThanOrEqual=" + UPDATED_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTStateCodesByEnteredDateIsLessThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where enteredDate is less than or equal to DEFAULT_ENTERED_DATE
		defaultTStateCodeShouldBeFound("enteredDate.lessThanOrEqual=" + DEFAULT_ENTERED_DATE);

		// Get all the tStateCodeList where enteredDate is less than or equal to SMALLER_ENTERED_DATE
		defaultTStateCodeShouldNotBeFound("enteredDate.lessThanOrEqual=" + SMALLER_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTStateCodesByEnteredDateIsLessThanSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where enteredDate is less than DEFAULT_ENTERED_DATE
		defaultTStateCodeShouldNotBeFound("enteredDate.lessThan=" + DEFAULT_ENTERED_DATE);

		// Get all the tStateCodeList where enteredDate is less than UPDATED_ENTERED_DATE
		defaultTStateCodeShouldBeFound("enteredDate.lessThan=" + UPDATED_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTStateCodesByEnteredDateIsGreaterThanSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where enteredDate is greater than DEFAULT_ENTERED_DATE
		defaultTStateCodeShouldNotBeFound("enteredDate.greaterThan=" + DEFAULT_ENTERED_DATE);

		// Get all the tStateCodeList where enteredDate is greater than SMALLER_ENTERED_DATE
		defaultTStateCodeShouldBeFound("enteredDate.greaterThan=" + SMALLER_ENTERED_DATE);
	}

	@Test
	@Transactional
	void getAllTStateCodesByModifiedByIsEqualToSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where modifiedBy equals to DEFAULT_MODIFIED_BY
		defaultTStateCodeShouldBeFound("modifiedBy.equals=" + DEFAULT_MODIFIED_BY);

		// Get all the tStateCodeList where modifiedBy equals to UPDATED_MODIFIED_BY
		defaultTStateCodeShouldNotBeFound("modifiedBy.equals=" + UPDATED_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTStateCodesByModifiedByIsInShouldWork() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where modifiedBy in DEFAULT_MODIFIED_BY or UPDATED_MODIFIED_BY
		defaultTStateCodeShouldBeFound("modifiedBy.in=" + DEFAULT_MODIFIED_BY + "," + UPDATED_MODIFIED_BY);

		// Get all the tStateCodeList where modifiedBy equals to UPDATED_MODIFIED_BY
		defaultTStateCodeShouldNotBeFound("modifiedBy.in=" + UPDATED_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTStateCodesByModifiedByIsNullOrNotNull() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where modifiedBy is not null
		defaultTStateCodeShouldBeFound("modifiedBy.specified=true");

		// Get all the tStateCodeList where modifiedBy is null
		defaultTStateCodeShouldNotBeFound("modifiedBy.specified=false");
	}

	@Test
	@Transactional
	void getAllTStateCodesByModifiedByIsGreaterThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where modifiedBy is greater than or equal to DEFAULT_MODIFIED_BY
		defaultTStateCodeShouldBeFound("modifiedBy.greaterThanOrEqual=" + DEFAULT_MODIFIED_BY);

		// Get all the tStateCodeList where modifiedBy is greater than or equal to UPDATED_MODIFIED_BY
		defaultTStateCodeShouldNotBeFound("modifiedBy.greaterThanOrEqual=" + UPDATED_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTStateCodesByModifiedByIsLessThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where modifiedBy is less than or equal to DEFAULT_MODIFIED_BY
		defaultTStateCodeShouldBeFound("modifiedBy.lessThanOrEqual=" + DEFAULT_MODIFIED_BY);

		// Get all the tStateCodeList where modifiedBy is less than or equal to SMALLER_MODIFIED_BY
		defaultTStateCodeShouldNotBeFound("modifiedBy.lessThanOrEqual=" + SMALLER_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTStateCodesByModifiedByIsLessThanSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where modifiedBy is less than DEFAULT_MODIFIED_BY
		defaultTStateCodeShouldNotBeFound("modifiedBy.lessThan=" + DEFAULT_MODIFIED_BY);

		// Get all the tStateCodeList where modifiedBy is less than UPDATED_MODIFIED_BY
		defaultTStateCodeShouldBeFound("modifiedBy.lessThan=" + UPDATED_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTStateCodesByModifiedByIsGreaterThanSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where modifiedBy is greater than DEFAULT_MODIFIED_BY
		defaultTStateCodeShouldNotBeFound("modifiedBy.greaterThan=" + DEFAULT_MODIFIED_BY);

		// Get all the tStateCodeList where modifiedBy is greater than SMALLER_MODIFIED_BY
		defaultTStateCodeShouldBeFound("modifiedBy.greaterThan=" + SMALLER_MODIFIED_BY);
	}

	@Test
	@Transactional
	void getAllTStateCodesByModifiedDateIsEqualToSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where modifiedDate equals to DEFAULT_MODIFIED_DATE
		defaultTStateCodeShouldBeFound("modifiedDate.equals=" + DEFAULT_MODIFIED_DATE);

		// Get all the tStateCodeList where modifiedDate equals to UPDATED_MODIFIED_DATE
		defaultTStateCodeShouldNotBeFound("modifiedDate.equals=" + UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTStateCodesByModifiedDateIsInShouldWork() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where modifiedDate in DEFAULT_MODIFIED_DATE or UPDATED_MODIFIED_DATE
		defaultTStateCodeShouldBeFound("modifiedDate.in=" + DEFAULT_MODIFIED_DATE + "," + UPDATED_MODIFIED_DATE);

		// Get all the tStateCodeList where modifiedDate equals to UPDATED_MODIFIED_DATE
		defaultTStateCodeShouldNotBeFound("modifiedDate.in=" + UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTStateCodesByModifiedDateIsNullOrNotNull() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where modifiedDate is not null
		defaultTStateCodeShouldBeFound("modifiedDate.specified=true");

		// Get all the tStateCodeList where modifiedDate is null
		defaultTStateCodeShouldNotBeFound("modifiedDate.specified=false");
	}

	@Test
	@Transactional
	void getAllTStateCodesByModifiedDateIsGreaterThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where modifiedDate is greater than or equal to DEFAULT_MODIFIED_DATE
		defaultTStateCodeShouldBeFound("modifiedDate.greaterThanOrEqual=" + DEFAULT_MODIFIED_DATE);

		// Get all the tStateCodeList where modifiedDate is greater than or equal to UPDATED_MODIFIED_DATE
		defaultTStateCodeShouldNotBeFound("modifiedDate.greaterThanOrEqual=" + UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTStateCodesByModifiedDateIsLessThanOrEqualToSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where modifiedDate is less than or equal to DEFAULT_MODIFIED_DATE
		defaultTStateCodeShouldBeFound("modifiedDate.lessThanOrEqual=" + DEFAULT_MODIFIED_DATE);

		// Get all the tStateCodeList where modifiedDate is less than or equal to SMALLER_MODIFIED_DATE
		defaultTStateCodeShouldNotBeFound("modifiedDate.lessThanOrEqual=" + SMALLER_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTStateCodesByModifiedDateIsLessThanSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where modifiedDate is less than DEFAULT_MODIFIED_DATE
		defaultTStateCodeShouldNotBeFound("modifiedDate.lessThan=" + DEFAULT_MODIFIED_DATE);

		// Get all the tStateCodeList where modifiedDate is less than UPDATED_MODIFIED_DATE
		defaultTStateCodeShouldBeFound("modifiedDate.lessThan=" + UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTStateCodesByModifiedDateIsGreaterThanSomething() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		// Get all the tStateCodeList where modifiedDate is greater than DEFAULT_MODIFIED_DATE
		defaultTStateCodeShouldNotBeFound("modifiedDate.greaterThan=" + DEFAULT_MODIFIED_DATE);

		// Get all the tStateCodeList where modifiedDate is greater than SMALLER_MODIFIED_DATE
		defaultTStateCodeShouldBeFound("modifiedDate.greaterThan=" + SMALLER_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void getAllTStateCodesByTCountryCodeIsEqualToSomething() throws Exception {
		TCountryCode tCountryCode;
		if (TestUtil.findAll(em, TCountryCode.class).isEmpty()) {
			tStateCodeRepository.saveAndFlush(tStateCode);
			tCountryCode = TCountryCodeResourceIT.createEntity(em);
		} else {
			tCountryCode = TestUtil.findAll(em, TCountryCode.class).get(0);
		}
		em.persist(tCountryCode);
		em.flush();
		tStateCode.setTCountryCode(tCountryCode);
		tStateCodeRepository.saveAndFlush(tStateCode);
		Long tCountryCodeId = tCountryCode.getId();

		// Get all the tStateCodeList where tCountryCode equals to tCountryCodeId
		defaultTStateCodeShouldBeFound("tCountryCodeId.equals=" + tCountryCodeId);

		// Get all the tStateCodeList where tCountryCode equals to (tCountryCodeId + 1)
		defaultTStateCodeShouldNotBeFound("tCountryCodeId.equals=" + (tCountryCodeId + 1));
	}

	@Test
	@Transactional
	void getAllTStateCodesByTCityCodeIsEqualToSomething() throws Exception {
		TCityCode tCityCode;
		if (TestUtil.findAll(em, TCityCode.class).isEmpty()) {
			tStateCodeRepository.saveAndFlush(tStateCode);
			tCityCode = TCityCodeResourceIT.createEntity(em);
		} else {
			tCityCode = TestUtil.findAll(em, TCityCode.class).get(0);
		}
		em.persist(tCityCode);
		em.flush();
		tStateCode.addTCityCode(tCityCode);
		tStateCodeRepository.saveAndFlush(tStateCode);
		Long tCityCodeId = tCityCode.getId();

		// Get all the tStateCodeList where tCityCode equals to tCityCodeId
		defaultTStateCodeShouldBeFound("tCityCodeId.equals=" + tCityCodeId);

		// Get all the tStateCodeList where tCityCode equals to (tCityCodeId + 1)
		defaultTStateCodeShouldNotBeFound("tCityCodeId.equals=" + (tCityCodeId + 1));
	}

	/**
	 * Executes the search, and checks that the default entity is returned.
	 */
	private void defaultTStateCodeShouldBeFound(String filter) throws Exception {
		restTStateCodeMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.[*].id").value(hasItem(tStateCode.getId().intValue())))
			.andExpect(jsonPath("$.[*].stateName").value(hasItem(DEFAULT_STATE_NAME)))
			.andExpect(jsonPath("$.[*].stateCode").value(hasItem(DEFAULT_STATE_CODE)))
			.andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
			.andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
			.andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
			.andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));

		// Check, that the count call also returns 1
		restTStateCodeMockMvc
			.perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().string("1"));
	}

	/**
	 * Executes the search, and checks that the default entity is not returned.
	 */
	private void defaultTStateCodeShouldNotBeFound(String filter) throws Exception {
		restTStateCodeMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$").isEmpty());

		// Check, that the count call also returns 0
		restTStateCodeMockMvc
			.perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().string("0"));
	}

	@Test
	@Transactional
	void getNonExistingTStateCode() throws Exception {
		// Get the tStateCode
		restTStateCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	void putExistingTStateCode() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		int databaseSizeBeforeUpdate = tStateCodeRepository.findAll().size();

		// Update the tStateCode
		TStateCode updatedTStateCode = tStateCodeRepository.findById(tStateCode.getId()).get();
		// Disconnect from session so that the updates on updatedTStateCode are not directly saved in db
		em.detach(updatedTStateCode);
		updatedTStateCode
			.stateName(UPDATED_STATE_NAME)
			.stateCode(UPDATED_STATE_CODE)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTStateCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, updatedTStateCode.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(updatedTStateCode))
			)
			.andExpect(status().isOk());

		// Validate the TStateCode in the database
		List<TStateCode> tStateCodeList = tStateCodeRepository.findAll();
		assertThat(tStateCodeList).hasSize(databaseSizeBeforeUpdate);
		TStateCode testTStateCode = tStateCodeList.get(tStateCodeList.size() - 1);
		assertThat(testTStateCode.getStateName()).isEqualTo(UPDATED_STATE_NAME);
		assertThat(testTStateCode.getStateCode()).isEqualTo(UPDATED_STATE_CODE);
		assertThat(testTStateCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTStateCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTStateCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTStateCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void putNonExistingTStateCode() throws Exception {
		int databaseSizeBeforeUpdate = tStateCodeRepository.findAll().size();
		tStateCode.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTStateCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, tStateCode.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tStateCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TStateCode in the database
		List<TStateCode> tStateCodeList = tStateCodeRepository.findAll();
		assertThat(tStateCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithIdMismatchTStateCode() throws Exception {
		int databaseSizeBeforeUpdate = tStateCodeRepository.findAll().size();
		tStateCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTStateCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tStateCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TStateCode in the database
		List<TStateCode> tStateCodeList = tStateCodeRepository.findAll();
		assertThat(tStateCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithMissingIdPathParamTStateCode() throws Exception {
		int databaseSizeBeforeUpdate = tStateCodeRepository.findAll().size();
		tStateCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTStateCodeMockMvc
			.perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tStateCode)))
			.andExpect(status().isMethodNotAllowed());

		// Validate the TStateCode in the database
		List<TStateCode> tStateCodeList = tStateCodeRepository.findAll();
		assertThat(tStateCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void partialUpdateTStateCodeWithPatch() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		int databaseSizeBeforeUpdate = tStateCodeRepository.findAll().size();

		// Update the tStateCode using partial update
		TStateCode partialUpdatedTStateCode = new TStateCode();
		partialUpdatedTStateCode.setId(tStateCode.getId());

		partialUpdatedTStateCode
			.stateName(UPDATED_STATE_NAME)
			.stateCode(UPDATED_STATE_CODE)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE);

		restTStateCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTStateCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTStateCode))
			)
			.andExpect(status().isOk());

		// Validate the TStateCode in the database
		List<TStateCode> tStateCodeList = tStateCodeRepository.findAll();
		assertThat(tStateCodeList).hasSize(databaseSizeBeforeUpdate);
		TStateCode testTStateCode = tStateCodeList.get(tStateCodeList.size() - 1);
		assertThat(testTStateCode.getStateName()).isEqualTo(UPDATED_STATE_NAME);
		assertThat(testTStateCode.getStateCode()).isEqualTo(UPDATED_STATE_CODE);
		assertThat(testTStateCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTStateCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTStateCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
		assertThat(testTStateCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void fullUpdateTStateCodeWithPatch() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		int databaseSizeBeforeUpdate = tStateCodeRepository.findAll().size();

		// Update the tStateCode using partial update
		TStateCode partialUpdatedTStateCode = new TStateCode();
		partialUpdatedTStateCode.setId(tStateCode.getId());

		partialUpdatedTStateCode
			.stateName(UPDATED_STATE_NAME)
			.stateCode(UPDATED_STATE_CODE)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTStateCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTStateCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTStateCode))
			)
			.andExpect(status().isOk());

		// Validate the TStateCode in the database
		List<TStateCode> tStateCodeList = tStateCodeRepository.findAll();
		assertThat(tStateCodeList).hasSize(databaseSizeBeforeUpdate);
		TStateCode testTStateCode = tStateCodeList.get(tStateCodeList.size() - 1);
		assertThat(testTStateCode.getStateName()).isEqualTo(UPDATED_STATE_NAME);
		assertThat(testTStateCode.getStateCode()).isEqualTo(UPDATED_STATE_CODE);
		assertThat(testTStateCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTStateCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTStateCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTStateCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void patchNonExistingTStateCode() throws Exception {
		int databaseSizeBeforeUpdate = tStateCodeRepository.findAll().size();
		tStateCode.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTStateCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, tStateCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tStateCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TStateCode in the database
		List<TStateCode> tStateCodeList = tStateCodeRepository.findAll();
		assertThat(tStateCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithIdMismatchTStateCode() throws Exception {
		int databaseSizeBeforeUpdate = tStateCodeRepository.findAll().size();
		tStateCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTStateCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tStateCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TStateCode in the database
		List<TStateCode> tStateCodeList = tStateCodeRepository.findAll();
		assertThat(tStateCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithMissingIdPathParamTStateCode() throws Exception {
		int databaseSizeBeforeUpdate = tStateCodeRepository.findAll().size();
		tStateCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTStateCodeMockMvc
			.perform(
				patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tStateCode))
			)
			.andExpect(status().isMethodNotAllowed());

		// Validate the TStateCode in the database
		List<TStateCode> tStateCodeList = tStateCodeRepository.findAll();
		assertThat(tStateCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void deleteTStateCode() throws Exception {
		// Initialize the database
		tStateCodeRepository.saveAndFlush(tStateCode);

		int databaseSizeBeforeDelete = tStateCodeRepository.findAll().size();

		// Delete the tStateCode
		restTStateCodeMockMvc
			.perform(delete(ENTITY_API_URL_ID, tStateCode.getId()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());

		// Validate the database contains one less item
		List<TStateCode> tStateCodeList = tStateCodeRepository.findAll();
		assertThat(tStateCodeList).hasSize(databaseSizeBeforeDelete - 1);
	}
}
