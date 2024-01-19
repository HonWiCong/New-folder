package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TSectionCode;
import com.jhipster.app.domain.enumeration.SectPapActive;
import com.jhipster.app.domain.enumeration.SectSainsSubsidiary;
import com.jhipster.app.repository.TSectionCodeRepository;
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
 * Integration tests for the {@link TSectionCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TSectionCodeResourceIT {

	private static final String DEFAULT_SECT_NAME = "AAAAAAAAAA";
	private static final String UPDATED_SECT_NAME = "BBBBBBBBBB";

	private static final Integer DEFAULT_SECT_HEADID = 1;
	private static final Integer UPDATED_SECT_HEADID = 2;

	private static final Integer DEFAULT_SECT_ACTING_HEADID = 1;
	private static final Integer UPDATED_SECT_ACTING_HEADID = 2;

	private static final SectSainsSubsidiary DEFAULT_SECT_SAINS_SUBSIDIARY = SectSainsSubsidiary.S;
	private static final SectSainsSubsidiary UPDATED_SECT_SAINS_SUBSIDIARY = SectSainsSubsidiary.D;

	private static final String DEFAULT_SECT_HRPAY_ID = "AAAAAAAAAA";
	private static final String UPDATED_SECT_HRPAY_ID = "BBBBBBBBBB";

	private static final String DEFAULT_SECT_PAP_ID = "AAAAAAAAAA";
	private static final String UPDATED_SECT_PAP_ID = "BBBBBBBBBB";

	private static final String DEFAULT_SECT_PAP_CODE = "AAAAAAAAAA";
	private static final String UPDATED_SECT_PAP_CODE = "BBBBBBBBBB";

	private static final String DEFAULT_SECT_PAP_COMPANY = "AAAAAAAAAA";
	private static final String UPDATED_SECT_PAP_COMPANY = "BBBBBBBBBB";

	private static final SectPapActive DEFAULT_SECT_PAP_ACTIVE = SectPapActive.A;
	private static final SectPapActive UPDATED_SECT_PAP_ACTIVE = SectPapActive.I;

	private static final String DEFAULT_SECT_CC_CODE = "AAAAAAAAAA";
	private static final String UPDATED_SECT_CC_CODE = "BBBBBBBBBB";

	private static final Integer DEFAULT_ENTERED_BY = 1;
	private static final Integer UPDATED_ENTERED_BY = 2;

	private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
	private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

	private static final Integer DEFAULT_MODIFIED_BY = 1;
	private static final Integer UPDATED_MODIFIED_BY = 2;

	private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
	private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

	private static final String ENTITY_API_URL = "/api/t-section-codes";
	private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

	private static Random random = new Random();
	private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

	@Autowired
	private TSectionCodeRepository tSectionCodeRepository;

	@Autowired
	private EntityManager em;

	@Autowired
	private MockMvc restTSectionCodeMockMvc;

	private TSectionCode tSectionCode;

	/**
	 * Create an entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TSectionCode createEntity(EntityManager em) {
		TSectionCode tSectionCode = new TSectionCode()
			.sectName(DEFAULT_SECT_NAME)
			.sectHeadid(DEFAULT_SECT_HEADID)
			.sectActingHeadid(DEFAULT_SECT_ACTING_HEADID)
			.sectSainsSubsidiary(DEFAULT_SECT_SAINS_SUBSIDIARY)
			.sectHrpayId(DEFAULT_SECT_HRPAY_ID)
			.sectPapId(DEFAULT_SECT_PAP_ID)
			.sectPapCode(DEFAULT_SECT_PAP_CODE)
			.sectPapCompany(DEFAULT_SECT_PAP_COMPANY)
			.sectPapActive(DEFAULT_SECT_PAP_ACTIVE)
			.sectCcCode(DEFAULT_SECT_CC_CODE)
			.enteredBy(DEFAULT_ENTERED_BY)
			.enteredDate(DEFAULT_ENTERED_DATE)
			.modifiedBy(DEFAULT_MODIFIED_BY)
			.modifiedDate(DEFAULT_MODIFIED_DATE);
		return tSectionCode;
	}

	/**
	 * Create an updated entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TSectionCode createUpdatedEntity(EntityManager em) {
		TSectionCode tSectionCode = new TSectionCode()
			.sectName(UPDATED_SECT_NAME)
			.sectHeadid(UPDATED_SECT_HEADID)
			.sectActingHeadid(UPDATED_SECT_ACTING_HEADID)
			.sectSainsSubsidiary(UPDATED_SECT_SAINS_SUBSIDIARY)
			.sectHrpayId(UPDATED_SECT_HRPAY_ID)
			.sectPapId(UPDATED_SECT_PAP_ID)
			.sectPapCode(UPDATED_SECT_PAP_CODE)
			.sectPapCompany(UPDATED_SECT_PAP_COMPANY)
			.sectPapActive(UPDATED_SECT_PAP_ACTIVE)
			.sectCcCode(UPDATED_SECT_CC_CODE)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);
		return tSectionCode;
	}

	@BeforeEach
	public void initTest() {
		tSectionCode = createEntity(em);
	}

	@Test
	@Transactional
	void createTSectionCode() throws Exception {
		int databaseSizeBeforeCreate = tSectionCodeRepository.findAll().size();
		// Create the TSectionCode
		restTSectionCodeMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tSectionCode)))
			.andExpect(status().isCreated());

		// Validate the TSectionCode in the database
		List<TSectionCode> tSectionCodeList = tSectionCodeRepository.findAll();
		assertThat(tSectionCodeList).hasSize(databaseSizeBeforeCreate + 1);
		TSectionCode testTSectionCode = tSectionCodeList.get(tSectionCodeList.size() - 1);
		assertThat(testTSectionCode.getSectName()).isEqualTo(DEFAULT_SECT_NAME);
		assertThat(testTSectionCode.getSectHeadid()).isEqualTo(DEFAULT_SECT_HEADID);
		assertThat(testTSectionCode.getSectActingHeadid()).isEqualTo(DEFAULT_SECT_ACTING_HEADID);
		assertThat(testTSectionCode.getSectSainsSubsidiary()).isEqualTo(DEFAULT_SECT_SAINS_SUBSIDIARY);
		assertThat(testTSectionCode.getSectHrpayId()).isEqualTo(DEFAULT_SECT_HRPAY_ID);
		assertThat(testTSectionCode.getSectPapId()).isEqualTo(DEFAULT_SECT_PAP_ID);
		assertThat(testTSectionCode.getSectPapCode()).isEqualTo(DEFAULT_SECT_PAP_CODE);
		assertThat(testTSectionCode.getSectPapCompany()).isEqualTo(DEFAULT_SECT_PAP_COMPANY);
		assertThat(testTSectionCode.getSectPapActive()).isEqualTo(DEFAULT_SECT_PAP_ACTIVE);
		assertThat(testTSectionCode.getSectCcCode()).isEqualTo(DEFAULT_SECT_CC_CODE);
		assertThat(testTSectionCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
		assertThat(testTSectionCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
		assertThat(testTSectionCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
		assertThat(testTSectionCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void createTSectionCodeWithExistingId() throws Exception {
		// Create the TSectionCode with an existing ID
		tSectionCode.setId(1L);

		int databaseSizeBeforeCreate = tSectionCodeRepository.findAll().size();

		// An entity with an existing ID cannot be created, so this API call must fail
		restTSectionCodeMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tSectionCode)))
			.andExpect(status().isBadRequest());

		// Validate the TSectionCode in the database
		List<TSectionCode> tSectionCodeList = tSectionCodeRepository.findAll();
		assertThat(tSectionCodeList).hasSize(databaseSizeBeforeCreate);
	}

	@Test
	@Transactional
	void getAllTSectionCodes() throws Exception {
		// Initialize the database
		tSectionCodeRepository.saveAndFlush(tSectionCode);

		// Get all the tSectionCodeList
		restTSectionCodeMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.[*].id").value(hasItem(tSectionCode.getId().intValue())))
			.andExpect(jsonPath("$.[*].sectName").value(hasItem(DEFAULT_SECT_NAME)))
			.andExpect(jsonPath("$.[*].sectHeadid").value(hasItem(DEFAULT_SECT_HEADID)))
			.andExpect(jsonPath("$.[*].sectActingHeadid").value(hasItem(DEFAULT_SECT_ACTING_HEADID)))
			.andExpect(jsonPath("$.[*].sectSainsSubsidiary").value(hasItem(DEFAULT_SECT_SAINS_SUBSIDIARY.toString())))
			.andExpect(jsonPath("$.[*].sectHrpayId").value(hasItem(DEFAULT_SECT_HRPAY_ID)))
			.andExpect(jsonPath("$.[*].sectPapId").value(hasItem(DEFAULT_SECT_PAP_ID)))
			.andExpect(jsonPath("$.[*].sectPapCode").value(hasItem(DEFAULT_SECT_PAP_CODE)))
			.andExpect(jsonPath("$.[*].sectPapCompany").value(hasItem(DEFAULT_SECT_PAP_COMPANY)))
			.andExpect(jsonPath("$.[*].sectPapActive").value(hasItem(DEFAULT_SECT_PAP_ACTIVE.toString())))
			.andExpect(jsonPath("$.[*].sectCcCode").value(hasItem(DEFAULT_SECT_CC_CODE)))
			.andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
			.andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
			.andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
			.andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
	}

	@Test
	@Transactional
	void getTSectionCode() throws Exception {
		// Initialize the database
		tSectionCodeRepository.saveAndFlush(tSectionCode);

		// Get the tSectionCode
		restTSectionCodeMockMvc
			.perform(get(ENTITY_API_URL_ID, tSectionCode.getId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.id").value(tSectionCode.getId().intValue()))
			.andExpect(jsonPath("$.sectName").value(DEFAULT_SECT_NAME))
			.andExpect(jsonPath("$.sectHeadid").value(DEFAULT_SECT_HEADID))
			.andExpect(jsonPath("$.sectActingHeadid").value(DEFAULT_SECT_ACTING_HEADID))
			.andExpect(jsonPath("$.sectSainsSubsidiary").value(DEFAULT_SECT_SAINS_SUBSIDIARY.toString()))
			.andExpect(jsonPath("$.sectHrpayId").value(DEFAULT_SECT_HRPAY_ID))
			.andExpect(jsonPath("$.sectPapId").value(DEFAULT_SECT_PAP_ID))
			.andExpect(jsonPath("$.sectPapCode").value(DEFAULT_SECT_PAP_CODE))
			.andExpect(jsonPath("$.sectPapCompany").value(DEFAULT_SECT_PAP_COMPANY))
			.andExpect(jsonPath("$.sectPapActive").value(DEFAULT_SECT_PAP_ACTIVE.toString()))
			.andExpect(jsonPath("$.sectCcCode").value(DEFAULT_SECT_CC_CODE))
			.andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
			.andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
			.andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
			.andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
	}

	@Test
	@Transactional
	void getNonExistingTSectionCode() throws Exception {
		// Get the tSectionCode
		restTSectionCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	void putExistingTSectionCode() throws Exception {
		// Initialize the database
		tSectionCodeRepository.saveAndFlush(tSectionCode);

		int databaseSizeBeforeUpdate = tSectionCodeRepository.findAll().size();

		// Update the tSectionCode
		TSectionCode updatedTSectionCode = tSectionCodeRepository.findById(tSectionCode.getId()).get();
		// Disconnect from session so that the updates on updatedTSectionCode are not directly saved in db
		em.detach(updatedTSectionCode);
		updatedTSectionCode
			.sectName(UPDATED_SECT_NAME)
			.sectHeadid(UPDATED_SECT_HEADID)
			.sectActingHeadid(UPDATED_SECT_ACTING_HEADID)
			.sectSainsSubsidiary(UPDATED_SECT_SAINS_SUBSIDIARY)
			.sectHrpayId(UPDATED_SECT_HRPAY_ID)
			.sectPapId(UPDATED_SECT_PAP_ID)
			.sectPapCode(UPDATED_SECT_PAP_CODE)
			.sectPapCompany(UPDATED_SECT_PAP_COMPANY)
			.sectPapActive(UPDATED_SECT_PAP_ACTIVE)
			.sectCcCode(UPDATED_SECT_CC_CODE)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTSectionCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, updatedTSectionCode.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(updatedTSectionCode))
			)
			.andExpect(status().isOk());

		// Validate the TSectionCode in the database
		List<TSectionCode> tSectionCodeList = tSectionCodeRepository.findAll();
		assertThat(tSectionCodeList).hasSize(databaseSizeBeforeUpdate);
		TSectionCode testTSectionCode = tSectionCodeList.get(tSectionCodeList.size() - 1);
		assertThat(testTSectionCode.getSectName()).isEqualTo(UPDATED_SECT_NAME);
		assertThat(testTSectionCode.getSectHeadid()).isEqualTo(UPDATED_SECT_HEADID);
		assertThat(testTSectionCode.getSectActingHeadid()).isEqualTo(UPDATED_SECT_ACTING_HEADID);
		assertThat(testTSectionCode.getSectSainsSubsidiary()).isEqualTo(UPDATED_SECT_SAINS_SUBSIDIARY);
		assertThat(testTSectionCode.getSectHrpayId()).isEqualTo(UPDATED_SECT_HRPAY_ID);
		assertThat(testTSectionCode.getSectPapId()).isEqualTo(UPDATED_SECT_PAP_ID);
		assertThat(testTSectionCode.getSectPapCode()).isEqualTo(UPDATED_SECT_PAP_CODE);
		assertThat(testTSectionCode.getSectPapCompany()).isEqualTo(UPDATED_SECT_PAP_COMPANY);
		assertThat(testTSectionCode.getSectPapActive()).isEqualTo(UPDATED_SECT_PAP_ACTIVE);
		assertThat(testTSectionCode.getSectCcCode()).isEqualTo(UPDATED_SECT_CC_CODE);
		assertThat(testTSectionCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTSectionCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTSectionCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTSectionCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void putNonExistingTSectionCode() throws Exception {
		int databaseSizeBeforeUpdate = tSectionCodeRepository.findAll().size();
		tSectionCode.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTSectionCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, tSectionCode.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tSectionCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TSectionCode in the database
		List<TSectionCode> tSectionCodeList = tSectionCodeRepository.findAll();
		assertThat(tSectionCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithIdMismatchTSectionCode() throws Exception {
		int databaseSizeBeforeUpdate = tSectionCodeRepository.findAll().size();
		tSectionCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTSectionCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tSectionCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TSectionCode in the database
		List<TSectionCode> tSectionCodeList = tSectionCodeRepository.findAll();
		assertThat(tSectionCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithMissingIdPathParamTSectionCode() throws Exception {
		int databaseSizeBeforeUpdate = tSectionCodeRepository.findAll().size();
		tSectionCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTSectionCodeMockMvc
			.perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tSectionCode)))
			.andExpect(status().isMethodNotAllowed());

		// Validate the TSectionCode in the database
		List<TSectionCode> tSectionCodeList = tSectionCodeRepository.findAll();
		assertThat(tSectionCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void partialUpdateTSectionCodeWithPatch() throws Exception {
		// Initialize the database
		tSectionCodeRepository.saveAndFlush(tSectionCode);

		int databaseSizeBeforeUpdate = tSectionCodeRepository.findAll().size();

		// Update the tSectionCode using partial update
		TSectionCode partialUpdatedTSectionCode = new TSectionCode();
		partialUpdatedTSectionCode.setId(tSectionCode.getId());

		partialUpdatedTSectionCode
			.sectName(UPDATED_SECT_NAME)
			.sectHeadid(UPDATED_SECT_HEADID)
			.sectSainsSubsidiary(UPDATED_SECT_SAINS_SUBSIDIARY)
			.sectPapId(UPDATED_SECT_PAP_ID)
			.sectPapCompany(UPDATED_SECT_PAP_COMPANY)
			.sectPapActive(UPDATED_SECT_PAP_ACTIVE)
			.enteredBy(UPDATED_ENTERED_BY);

		restTSectionCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTSectionCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTSectionCode))
			)
			.andExpect(status().isOk());

		// Validate the TSectionCode in the database
		List<TSectionCode> tSectionCodeList = tSectionCodeRepository.findAll();
		assertThat(tSectionCodeList).hasSize(databaseSizeBeforeUpdate);
		TSectionCode testTSectionCode = tSectionCodeList.get(tSectionCodeList.size() - 1);
		assertThat(testTSectionCode.getSectName()).isEqualTo(UPDATED_SECT_NAME);
		assertThat(testTSectionCode.getSectHeadid()).isEqualTo(UPDATED_SECT_HEADID);
		assertThat(testTSectionCode.getSectActingHeadid()).isEqualTo(DEFAULT_SECT_ACTING_HEADID);
		assertThat(testTSectionCode.getSectSainsSubsidiary()).isEqualTo(UPDATED_SECT_SAINS_SUBSIDIARY);
		assertThat(testTSectionCode.getSectHrpayId()).isEqualTo(DEFAULT_SECT_HRPAY_ID);
		assertThat(testTSectionCode.getSectPapId()).isEqualTo(UPDATED_SECT_PAP_ID);
		assertThat(testTSectionCode.getSectPapCode()).isEqualTo(DEFAULT_SECT_PAP_CODE);
		assertThat(testTSectionCode.getSectPapCompany()).isEqualTo(UPDATED_SECT_PAP_COMPANY);
		assertThat(testTSectionCode.getSectPapActive()).isEqualTo(UPDATED_SECT_PAP_ACTIVE);
		assertThat(testTSectionCode.getSectCcCode()).isEqualTo(DEFAULT_SECT_CC_CODE);
		assertThat(testTSectionCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTSectionCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
		assertThat(testTSectionCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
		assertThat(testTSectionCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void fullUpdateTSectionCodeWithPatch() throws Exception {
		// Initialize the database
		tSectionCodeRepository.saveAndFlush(tSectionCode);

		int databaseSizeBeforeUpdate = tSectionCodeRepository.findAll().size();

		// Update the tSectionCode using partial update
		TSectionCode partialUpdatedTSectionCode = new TSectionCode();
		partialUpdatedTSectionCode.setId(tSectionCode.getId());

		partialUpdatedTSectionCode
			.sectName(UPDATED_SECT_NAME)
			.sectHeadid(UPDATED_SECT_HEADID)
			.sectActingHeadid(UPDATED_SECT_ACTING_HEADID)
			.sectSainsSubsidiary(UPDATED_SECT_SAINS_SUBSIDIARY)
			.sectHrpayId(UPDATED_SECT_HRPAY_ID)
			.sectPapId(UPDATED_SECT_PAP_ID)
			.sectPapCode(UPDATED_SECT_PAP_CODE)
			.sectPapCompany(UPDATED_SECT_PAP_COMPANY)
			.sectPapActive(UPDATED_SECT_PAP_ACTIVE)
			.sectCcCode(UPDATED_SECT_CC_CODE)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTSectionCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTSectionCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTSectionCode))
			)
			.andExpect(status().isOk());

		// Validate the TSectionCode in the database
		List<TSectionCode> tSectionCodeList = tSectionCodeRepository.findAll();
		assertThat(tSectionCodeList).hasSize(databaseSizeBeforeUpdate);
		TSectionCode testTSectionCode = tSectionCodeList.get(tSectionCodeList.size() - 1);
		assertThat(testTSectionCode.getSectName()).isEqualTo(UPDATED_SECT_NAME);
		assertThat(testTSectionCode.getSectHeadid()).isEqualTo(UPDATED_SECT_HEADID);
		assertThat(testTSectionCode.getSectActingHeadid()).isEqualTo(UPDATED_SECT_ACTING_HEADID);
		assertThat(testTSectionCode.getSectSainsSubsidiary()).isEqualTo(UPDATED_SECT_SAINS_SUBSIDIARY);
		assertThat(testTSectionCode.getSectHrpayId()).isEqualTo(UPDATED_SECT_HRPAY_ID);
		assertThat(testTSectionCode.getSectPapId()).isEqualTo(UPDATED_SECT_PAP_ID);
		assertThat(testTSectionCode.getSectPapCode()).isEqualTo(UPDATED_SECT_PAP_CODE);
		assertThat(testTSectionCode.getSectPapCompany()).isEqualTo(UPDATED_SECT_PAP_COMPANY);
		assertThat(testTSectionCode.getSectPapActive()).isEqualTo(UPDATED_SECT_PAP_ACTIVE);
		assertThat(testTSectionCode.getSectCcCode()).isEqualTo(UPDATED_SECT_CC_CODE);
		assertThat(testTSectionCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTSectionCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTSectionCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTSectionCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void patchNonExistingTSectionCode() throws Exception {
		int databaseSizeBeforeUpdate = tSectionCodeRepository.findAll().size();
		tSectionCode.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTSectionCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, tSectionCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tSectionCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TSectionCode in the database
		List<TSectionCode> tSectionCodeList = tSectionCodeRepository.findAll();
		assertThat(tSectionCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithIdMismatchTSectionCode() throws Exception {
		int databaseSizeBeforeUpdate = tSectionCodeRepository.findAll().size();
		tSectionCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTSectionCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tSectionCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TSectionCode in the database
		List<TSectionCode> tSectionCodeList = tSectionCodeRepository.findAll();
		assertThat(tSectionCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithMissingIdPathParamTSectionCode() throws Exception {
		int databaseSizeBeforeUpdate = tSectionCodeRepository.findAll().size();
		tSectionCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTSectionCodeMockMvc
			.perform(
				patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tSectionCode))
			)
			.andExpect(status().isMethodNotAllowed());

		// Validate the TSectionCode in the database
		List<TSectionCode> tSectionCodeList = tSectionCodeRepository.findAll();
		assertThat(tSectionCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void deleteTSectionCode() throws Exception {
		// Initialize the database
		tSectionCodeRepository.saveAndFlush(tSectionCode);

		int databaseSizeBeforeDelete = tSectionCodeRepository.findAll().size();

		// Delete the tSectionCode
		restTSectionCodeMockMvc
			.perform(delete(ENTITY_API_URL_ID, tSectionCode.getId()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());

		// Validate the database contains one less item
		List<TSectionCode> tSectionCodeList = tSectionCodeRepository.findAll();
		assertThat(tSectionCodeList).hasSize(databaseSizeBeforeDelete - 1);
	}
}
