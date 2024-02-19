package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TUnitCode;
import com.jhipster.app.repository.TUnitCodeRepository;
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
 * Integration tests for the {@link TUnitCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TUnitCodeResourceIT {

	private static final String DEFAULT_UNT_UNIT = "AAAAAAAAAA";
	private static final String UPDATED_UNT_UNIT = "BBBBBBBBBB";

	private static final Integer DEFAULT_UNT_HEADID = 1;
	private static final Integer UPDATED_UNT_HEADID = 2;

	private static final Integer DEFAULT_UNT_ACTING_HEADID = 1;
	private static final Integer UPDATED_UNT_ACTING_HEADID = 2;

	private static final String DEFAULT_UNT_HRPAY_ID = "AAAAAAAAAA";
	private static final String UPDATED_UNT_HRPAY_ID = "BBBBBBBBBB";

	private static final String DEFAULT_UNT_PAP_ID = "AAAAAAAAAA";
	private static final String UPDATED_UNT_PAP_ID = "BBBBBBBBBB";

	private static final String DEFAULT_UNT_PAP_CODE = "AAAAAAAAAA";
	private static final String UPDATED_UNT_PAP_CODE = "BBBBBBBBBB";

	private static final String DEFAULT_UNT_PAP_ACTIVE = "A";
	private static final String UPDATED_UNT_PAP_ACTIVE = "B";

	private static final String DEFAULT_UNT_PAP_DEPARTMENTID = "AAAAAAAAAA";
	private static final String UPDATED_UNT_PAP_DEPARTMENTID = "BBBBBBBBBB";

	private static final Integer DEFAULT_ENTERED_BY = 1;
	private static final Integer UPDATED_ENTERED_BY = 2;

	private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
	private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

	private static final Integer DEFAULT_MODIFIED_BY = 1;
	private static final Integer UPDATED_MODIFIED_BY = 2;

	private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
	private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

	private static final String ENTITY_API_URL = "/api/t-unit-codes";
	private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

	private static Random random = new Random();
	private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

	@Autowired
	private TUnitCodeRepository tUnitCodeRepository;

	@Autowired
	private EntityManager em;

	@Autowired
	private MockMvc restTUnitCodeMockMvc;

	private TUnitCode tUnitCode;

	/**
	 * Create an entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TUnitCode createEntity(EntityManager em) {
		TUnitCode tUnitCode = new TUnitCode()
			.untUnit(DEFAULT_UNT_UNIT)
			.untHeadid(DEFAULT_UNT_HEADID)
			.untActingHeadid(DEFAULT_UNT_ACTING_HEADID)
			.untHrpayId(DEFAULT_UNT_HRPAY_ID)
			.untPapId(DEFAULT_UNT_PAP_ID)
			.untPapCode(DEFAULT_UNT_PAP_CODE)
			.untPapActive(DEFAULT_UNT_PAP_ACTIVE)
			.untPapDepartmentid(DEFAULT_UNT_PAP_DEPARTMENTID)
			.enteredBy(DEFAULT_ENTERED_BY)
			.enteredDate(DEFAULT_ENTERED_DATE)
			.modifiedBy(DEFAULT_MODIFIED_BY)
			.modifiedDate(DEFAULT_MODIFIED_DATE);
		return tUnitCode;
	}

	/**
	 * Create an updated entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TUnitCode createUpdatedEntity(EntityManager em) {
		TUnitCode tUnitCode = new TUnitCode()
			.untUnit(UPDATED_UNT_UNIT)
			.untHeadid(UPDATED_UNT_HEADID)
			.untActingHeadid(UPDATED_UNT_ACTING_HEADID)
			.untHrpayId(UPDATED_UNT_HRPAY_ID)
			.untPapId(UPDATED_UNT_PAP_ID)
			.untPapCode(UPDATED_UNT_PAP_CODE)
			.untPapActive(UPDATED_UNT_PAP_ACTIVE)
			.untPapDepartmentid(UPDATED_UNT_PAP_DEPARTMENTID)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);
		return tUnitCode;
	}

	@BeforeEach
	public void initTest() {
		tUnitCode = createEntity(em);
	}

	@Test
	@Transactional
	void createTUnitCode() throws Exception {
		int databaseSizeBeforeCreate = tUnitCodeRepository.findAll().size();
		// Create the TUnitCode
		restTUnitCodeMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tUnitCode)))
			.andExpect(status().isCreated());

		// Validate the TUnitCode in the database
		List<TUnitCode> tUnitCodeList = tUnitCodeRepository.findAll();
		assertThat(tUnitCodeList).hasSize(databaseSizeBeforeCreate + 1);
		TUnitCode testTUnitCode = tUnitCodeList.get(tUnitCodeList.size() - 1);
		assertThat(testTUnitCode.getUntUnit()).isEqualTo(DEFAULT_UNT_UNIT);
		assertThat(testTUnitCode.getUntHeadid()).isEqualTo(DEFAULT_UNT_HEADID);
		assertThat(testTUnitCode.getUntActingHeadid()).isEqualTo(DEFAULT_UNT_ACTING_HEADID);
		assertThat(testTUnitCode.getUntHrpayId()).isEqualTo(DEFAULT_UNT_HRPAY_ID);
		assertThat(testTUnitCode.getUntPapId()).isEqualTo(DEFAULT_UNT_PAP_ID);
		assertThat(testTUnitCode.getUntPapCode()).isEqualTo(DEFAULT_UNT_PAP_CODE);
		assertThat(testTUnitCode.getUntPapActive()).isEqualTo(DEFAULT_UNT_PAP_ACTIVE);
		assertThat(testTUnitCode.getUntPapDepartmentid()).isEqualTo(DEFAULT_UNT_PAP_DEPARTMENTID);
		assertThat(testTUnitCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
		assertThat(testTUnitCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
		assertThat(testTUnitCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
		assertThat(testTUnitCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void createTUnitCodeWithExistingId() throws Exception {
		// Create the TUnitCode with an existing ID
		tUnitCode.setId(1L);

		int databaseSizeBeforeCreate = tUnitCodeRepository.findAll().size();

		// An entity with an existing ID cannot be created, so this API call must fail
		restTUnitCodeMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tUnitCode)))
			.andExpect(status().isBadRequest());

		// Validate the TUnitCode in the database
		List<TUnitCode> tUnitCodeList = tUnitCodeRepository.findAll();
		assertThat(tUnitCodeList).hasSize(databaseSizeBeforeCreate);
	}

	@Test
	@Transactional
	void getAllTUnitCodes() throws Exception {
		// Initialize the database
		tUnitCodeRepository.saveAndFlush(tUnitCode);

		// Get all the tUnitCodeList
		restTUnitCodeMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.[*].id").value(hasItem(tUnitCode.getId().intValue())))
			.andExpect(jsonPath("$.[*].untUnit").value(hasItem(DEFAULT_UNT_UNIT)))
			.andExpect(jsonPath("$.[*].untHeadid").value(hasItem(DEFAULT_UNT_HEADID)))
			.andExpect(jsonPath("$.[*].untActingHeadid").value(hasItem(DEFAULT_UNT_ACTING_HEADID)))
			.andExpect(jsonPath("$.[*].untHrpayId").value(hasItem(DEFAULT_UNT_HRPAY_ID)))
			.andExpect(jsonPath("$.[*].untPapId").value(hasItem(DEFAULT_UNT_PAP_ID)))
			.andExpect(jsonPath("$.[*].untPapCode").value(hasItem(DEFAULT_UNT_PAP_CODE)))
			.andExpect(jsonPath("$.[*].untPapActive").value(hasItem(DEFAULT_UNT_PAP_ACTIVE)))
			.andExpect(jsonPath("$.[*].untPapDepartmentid").value(hasItem(DEFAULT_UNT_PAP_DEPARTMENTID)))
			.andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
			.andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
			.andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
			.andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
	}

	@Test
	@Transactional
	void getTUnitCode() throws Exception {
		// Initialize the database
		tUnitCodeRepository.saveAndFlush(tUnitCode);

		// Get the tUnitCode
		restTUnitCodeMockMvc
			.perform(get(ENTITY_API_URL_ID, tUnitCode.getId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.id").value(tUnitCode.getId().intValue()))
			.andExpect(jsonPath("$.untUnit").value(DEFAULT_UNT_UNIT))
			.andExpect(jsonPath("$.untHeadid").value(DEFAULT_UNT_HEADID))
			.andExpect(jsonPath("$.untActingHeadid").value(DEFAULT_UNT_ACTING_HEADID))
			.andExpect(jsonPath("$.untHrpayId").value(DEFAULT_UNT_HRPAY_ID))
			.andExpect(jsonPath("$.untPapId").value(DEFAULT_UNT_PAP_ID))
			.andExpect(jsonPath("$.untPapCode").value(DEFAULT_UNT_PAP_CODE))
			.andExpect(jsonPath("$.untPapActive").value(DEFAULT_UNT_PAP_ACTIVE))
			.andExpect(jsonPath("$.untPapDepartmentid").value(DEFAULT_UNT_PAP_DEPARTMENTID))
			.andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
			.andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
			.andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
			.andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
	}

	@Test
	@Transactional
	void getNonExistingTUnitCode() throws Exception {
		// Get the tUnitCode
		restTUnitCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	void putExistingTUnitCode() throws Exception {
		// Initialize the database
		tUnitCodeRepository.saveAndFlush(tUnitCode);

		int databaseSizeBeforeUpdate = tUnitCodeRepository.findAll().size();

		// Update the tUnitCode
		TUnitCode updatedTUnitCode = tUnitCodeRepository.findById(tUnitCode.getId()).get();
		// Disconnect from session so that the updates on updatedTUnitCode are not directly saved in db
		em.detach(updatedTUnitCode);
		updatedTUnitCode
			.untUnit(UPDATED_UNT_UNIT)
			.untHeadid(UPDATED_UNT_HEADID)
			.untActingHeadid(UPDATED_UNT_ACTING_HEADID)
			.untHrpayId(UPDATED_UNT_HRPAY_ID)
			.untPapId(UPDATED_UNT_PAP_ID)
			.untPapCode(UPDATED_UNT_PAP_CODE)
			.untPapActive(UPDATED_UNT_PAP_ACTIVE)
			.untPapDepartmentid(UPDATED_UNT_PAP_DEPARTMENTID)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTUnitCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, updatedTUnitCode.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(updatedTUnitCode))
			)
			.andExpect(status().isOk());

		// Validate the TUnitCode in the database
		List<TUnitCode> tUnitCodeList = tUnitCodeRepository.findAll();
		assertThat(tUnitCodeList).hasSize(databaseSizeBeforeUpdate);
		TUnitCode testTUnitCode = tUnitCodeList.get(tUnitCodeList.size() - 1);
		assertThat(testTUnitCode.getUntUnit()).isEqualTo(UPDATED_UNT_UNIT);
		assertThat(testTUnitCode.getUntHeadid()).isEqualTo(UPDATED_UNT_HEADID);
		assertThat(testTUnitCode.getUntActingHeadid()).isEqualTo(UPDATED_UNT_ACTING_HEADID);
		assertThat(testTUnitCode.getUntHrpayId()).isEqualTo(UPDATED_UNT_HRPAY_ID);
		assertThat(testTUnitCode.getUntPapId()).isEqualTo(UPDATED_UNT_PAP_ID);
		assertThat(testTUnitCode.getUntPapCode()).isEqualTo(UPDATED_UNT_PAP_CODE);
		assertThat(testTUnitCode.getUntPapActive()).isEqualTo(UPDATED_UNT_PAP_ACTIVE);
		assertThat(testTUnitCode.getUntPapDepartmentid()).isEqualTo(UPDATED_UNT_PAP_DEPARTMENTID);
		assertThat(testTUnitCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTUnitCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTUnitCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTUnitCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void putNonExistingTUnitCode() throws Exception {
		int databaseSizeBeforeUpdate = tUnitCodeRepository.findAll().size();
		tUnitCode.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTUnitCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, tUnitCode.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tUnitCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TUnitCode in the database
		List<TUnitCode> tUnitCodeList = tUnitCodeRepository.findAll();
		assertThat(tUnitCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithIdMismatchTUnitCode() throws Exception {
		int databaseSizeBeforeUpdate = tUnitCodeRepository.findAll().size();
		tUnitCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTUnitCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tUnitCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TUnitCode in the database
		List<TUnitCode> tUnitCodeList = tUnitCodeRepository.findAll();
		assertThat(tUnitCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithMissingIdPathParamTUnitCode() throws Exception {
		int databaseSizeBeforeUpdate = tUnitCodeRepository.findAll().size();
		tUnitCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTUnitCodeMockMvc
			.perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tUnitCode)))
			.andExpect(status().isMethodNotAllowed());

		// Validate the TUnitCode in the database
		List<TUnitCode> tUnitCodeList = tUnitCodeRepository.findAll();
		assertThat(tUnitCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void partialUpdateTUnitCodeWithPatch() throws Exception {
		// Initialize the database
		tUnitCodeRepository.saveAndFlush(tUnitCode);

		int databaseSizeBeforeUpdate = tUnitCodeRepository.findAll().size();

		// Update the tUnitCode using partial update
		TUnitCode partialUpdatedTUnitCode = new TUnitCode();
		partialUpdatedTUnitCode.setId(tUnitCode.getId());

		partialUpdatedTUnitCode
			.untPapId(UPDATED_UNT_PAP_ID)
			.untPapActive(UPDATED_UNT_PAP_ACTIVE)
			.untPapDepartmentid(UPDATED_UNT_PAP_DEPARTMENTID)
			.enteredBy(UPDATED_ENTERED_BY);

		restTUnitCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTUnitCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTUnitCode))
			)
			.andExpect(status().isOk());

		// Validate the TUnitCode in the database
		List<TUnitCode> tUnitCodeList = tUnitCodeRepository.findAll();
		assertThat(tUnitCodeList).hasSize(databaseSizeBeforeUpdate);
		TUnitCode testTUnitCode = tUnitCodeList.get(tUnitCodeList.size() - 1);
		assertThat(testTUnitCode.getUntUnit()).isEqualTo(DEFAULT_UNT_UNIT);
		assertThat(testTUnitCode.getUntHeadid()).isEqualTo(DEFAULT_UNT_HEADID);
		assertThat(testTUnitCode.getUntActingHeadid()).isEqualTo(DEFAULT_UNT_ACTING_HEADID);
		assertThat(testTUnitCode.getUntHrpayId()).isEqualTo(DEFAULT_UNT_HRPAY_ID);
		assertThat(testTUnitCode.getUntPapId()).isEqualTo(UPDATED_UNT_PAP_ID);
		assertThat(testTUnitCode.getUntPapCode()).isEqualTo(DEFAULT_UNT_PAP_CODE);
		assertThat(testTUnitCode.getUntPapActive()).isEqualTo(UPDATED_UNT_PAP_ACTIVE);
		assertThat(testTUnitCode.getUntPapDepartmentid()).isEqualTo(UPDATED_UNT_PAP_DEPARTMENTID);
		assertThat(testTUnitCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTUnitCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
		assertThat(testTUnitCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
		assertThat(testTUnitCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void fullUpdateTUnitCodeWithPatch() throws Exception {
		// Initialize the database
		tUnitCodeRepository.saveAndFlush(tUnitCode);

		int databaseSizeBeforeUpdate = tUnitCodeRepository.findAll().size();

		// Update the tUnitCode using partial update
		TUnitCode partialUpdatedTUnitCode = new TUnitCode();
		partialUpdatedTUnitCode.setId(tUnitCode.getId());

		partialUpdatedTUnitCode
			.untUnit(UPDATED_UNT_UNIT)
			.untHeadid(UPDATED_UNT_HEADID)
			.untActingHeadid(UPDATED_UNT_ACTING_HEADID)
			.untHrpayId(UPDATED_UNT_HRPAY_ID)
			.untPapId(UPDATED_UNT_PAP_ID)
			.untPapCode(UPDATED_UNT_PAP_CODE)
			.untPapActive(UPDATED_UNT_PAP_ACTIVE)
			.untPapDepartmentid(UPDATED_UNT_PAP_DEPARTMENTID)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTUnitCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTUnitCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTUnitCode))
			)
			.andExpect(status().isOk());

		// Validate the TUnitCode in the database
		List<TUnitCode> tUnitCodeList = tUnitCodeRepository.findAll();
		assertThat(tUnitCodeList).hasSize(databaseSizeBeforeUpdate);
		TUnitCode testTUnitCode = tUnitCodeList.get(tUnitCodeList.size() - 1);
		assertThat(testTUnitCode.getUntUnit()).isEqualTo(UPDATED_UNT_UNIT);
		assertThat(testTUnitCode.getUntHeadid()).isEqualTo(UPDATED_UNT_HEADID);
		assertThat(testTUnitCode.getUntActingHeadid()).isEqualTo(UPDATED_UNT_ACTING_HEADID);
		assertThat(testTUnitCode.getUntHrpayId()).isEqualTo(UPDATED_UNT_HRPAY_ID);
		assertThat(testTUnitCode.getUntPapId()).isEqualTo(UPDATED_UNT_PAP_ID);
		assertThat(testTUnitCode.getUntPapCode()).isEqualTo(UPDATED_UNT_PAP_CODE);
		assertThat(testTUnitCode.getUntPapActive()).isEqualTo(UPDATED_UNT_PAP_ACTIVE);
		assertThat(testTUnitCode.getUntPapDepartmentid()).isEqualTo(UPDATED_UNT_PAP_DEPARTMENTID);
		assertThat(testTUnitCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTUnitCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTUnitCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTUnitCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void patchNonExistingTUnitCode() throws Exception {
		int databaseSizeBeforeUpdate = tUnitCodeRepository.findAll().size();
		tUnitCode.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTUnitCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, tUnitCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tUnitCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TUnitCode in the database
		List<TUnitCode> tUnitCodeList = tUnitCodeRepository.findAll();
		assertThat(tUnitCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithIdMismatchTUnitCode() throws Exception {
		int databaseSizeBeforeUpdate = tUnitCodeRepository.findAll().size();
		tUnitCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTUnitCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tUnitCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TUnitCode in the database
		List<TUnitCode> tUnitCodeList = tUnitCodeRepository.findAll();
		assertThat(tUnitCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithMissingIdPathParamTUnitCode() throws Exception {
		int databaseSizeBeforeUpdate = tUnitCodeRepository.findAll().size();
		tUnitCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTUnitCodeMockMvc
			.perform(
				patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tUnitCode))
			)
			.andExpect(status().isMethodNotAllowed());

		// Validate the TUnitCode in the database
		List<TUnitCode> tUnitCodeList = tUnitCodeRepository.findAll();
		assertThat(tUnitCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void deleteTUnitCode() throws Exception {
		// Initialize the database
		tUnitCodeRepository.saveAndFlush(tUnitCode);

		int databaseSizeBeforeDelete = tUnitCodeRepository.findAll().size();

		// Delete the tUnitCode
		restTUnitCodeMockMvc
			.perform(delete(ENTITY_API_URL_ID, tUnitCode.getId()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());

		// Validate the database contains one less item
		List<TUnitCode> tUnitCodeList = tUnitCodeRepository.findAll();
		assertThat(tUnitCodeList).hasSize(databaseSizeBeforeDelete - 1);
	}
}
