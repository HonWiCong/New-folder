package com.jhipster.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TSubunitCode;
import com.jhipster.app.repository.TSubunitCodeRepository;
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
 * Integration tests for the {@link TSubunitCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TSubunitCodeResourceIT {

	private static final String DEFAULT_SUBUNT_SUBUNIT = "AAAAAAAAAA";
	private static final String UPDATED_SUBUNT_SUBUNIT = "BBBBBBBBBB";

	private static final Integer DEFAULT_SUBUNT_HEADID = 1;
	private static final Integer UPDATED_SUBUNT_HEADID = 2;

	private static final Integer DEFAULT_SUBUNT_ACTING_HEADID = 1;
	private static final Integer UPDATED_SUBUNT_ACTING_HEADID = 2;

	private static final String DEFAULT_SUBUNT_HRPAY_ID = "AAAAAAAAAA";
	private static final String UPDATED_SUBUNT_HRPAY_ID = "BBBBBBBBBB";

	private static final String DEFAULT_SUBUNT_PAP_ID = "AAAAAAAAAA";
	private static final String UPDATED_SUBUNT_PAP_ID = "BBBBBBBBBB";

	private static final String DEFAULT_SUBUNT_PAP_CODE = "AAAAAAAAAA";
	private static final String UPDATED_SUBUNT_PAP_CODE = "BBBBBBBBBB";

	private static final String DEFAULT_SUBUNT_PAP_ACTIVE = "A";
	private static final String UPDATED_SUBUNT_PAP_ACTIVE = "B";

	private static final String DEFAULT_SUBUNY_UNITID = "AAAAAAAAAA";
	private static final String UPDATED_SUBUNY_UNITID = "BBBBBBBBBB";

	private static final String ENTITY_API_URL = "/api/t-subunit-codes";
	private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

	private static Random random = new Random();
	private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

	@Autowired
	private TSubunitCodeRepository tSubunitCodeRepository;

	@Autowired
	private EntityManager em;

	@Autowired
	private MockMvc restTSubunitCodeMockMvc;

	private TSubunitCode tSubunitCode;

	/**
	 * Create an entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TSubunitCode createEntity(EntityManager em) {
		TSubunitCode tSubunitCode = new TSubunitCode()
			.subuntSubunit(DEFAULT_SUBUNT_SUBUNIT)
			.subuntHeadid(DEFAULT_SUBUNT_HEADID)
			.subuntActingHeadid(DEFAULT_SUBUNT_ACTING_HEADID)
			.subuntHrpayId(DEFAULT_SUBUNT_HRPAY_ID)
			.subuntPapId(DEFAULT_SUBUNT_PAP_ID)
			.subuntPapCode(DEFAULT_SUBUNT_PAP_CODE)
			.subuntPapActive(DEFAULT_SUBUNT_PAP_ACTIVE)
			.subunyUnitid(DEFAULT_SUBUNY_UNITID);
		return tSubunitCode;
	}

	/**
	 * Create an updated entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TSubunitCode createUpdatedEntity(EntityManager em) {
		TSubunitCode tSubunitCode = new TSubunitCode()
			.subuntSubunit(UPDATED_SUBUNT_SUBUNIT)
			.subuntHeadid(UPDATED_SUBUNT_HEADID)
			.subuntActingHeadid(UPDATED_SUBUNT_ACTING_HEADID)
			.subuntHrpayId(UPDATED_SUBUNT_HRPAY_ID)
			.subuntPapId(UPDATED_SUBUNT_PAP_ID)
			.subuntPapCode(UPDATED_SUBUNT_PAP_CODE)
			.subuntPapActive(UPDATED_SUBUNT_PAP_ACTIVE)
			.subunyUnitid(UPDATED_SUBUNY_UNITID);
		return tSubunitCode;
	}

	@BeforeEach
	public void initTest() {
		tSubunitCode = createEntity(em);
	}

	@Test
	@Transactional
	void createTSubunitCode() throws Exception {
		int databaseSizeBeforeCreate = tSubunitCodeRepository.findAll().size();
		// Create the TSubunitCode
		restTSubunitCodeMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tSubunitCode)))
			.andExpect(status().isCreated());

		// Validate the TSubunitCode in the database
		List<TSubunitCode> tSubunitCodeList = tSubunitCodeRepository.findAll();
		assertThat(tSubunitCodeList).hasSize(databaseSizeBeforeCreate + 1);
		TSubunitCode testTSubunitCode = tSubunitCodeList.get(tSubunitCodeList.size() - 1);
		assertThat(testTSubunitCode.getSubuntSubunit()).isEqualTo(DEFAULT_SUBUNT_SUBUNIT);
		assertThat(testTSubunitCode.getSubuntHeadid()).isEqualTo(DEFAULT_SUBUNT_HEADID);
		assertThat(testTSubunitCode.getSubuntActingHeadid()).isEqualTo(DEFAULT_SUBUNT_ACTING_HEADID);
		assertThat(testTSubunitCode.getSubuntHrpayId()).isEqualTo(DEFAULT_SUBUNT_HRPAY_ID);
		assertThat(testTSubunitCode.getSubuntPapId()).isEqualTo(DEFAULT_SUBUNT_PAP_ID);
		assertThat(testTSubunitCode.getSubuntPapCode()).isEqualTo(DEFAULT_SUBUNT_PAP_CODE);
		assertThat(testTSubunitCode.getSubuntPapActive()).isEqualTo(DEFAULT_SUBUNT_PAP_ACTIVE);
		assertThat(testTSubunitCode.getSubunyUnitid()).isEqualTo(DEFAULT_SUBUNY_UNITID);
	}

	@Test
	@Transactional
	void createTSubunitCodeWithExistingId() throws Exception {
		// Create the TSubunitCode with an existing ID
		tSubunitCode.setId(1L);

		int databaseSizeBeforeCreate = tSubunitCodeRepository.findAll().size();

		// An entity with an existing ID cannot be created, so this API call must fail
		restTSubunitCodeMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tSubunitCode)))
			.andExpect(status().isBadRequest());

		// Validate the TSubunitCode in the database
		List<TSubunitCode> tSubunitCodeList = tSubunitCodeRepository.findAll();
		assertThat(tSubunitCodeList).hasSize(databaseSizeBeforeCreate);
	}

	@Test
	@Transactional
	void getAllTSubunitCodes() throws Exception {
		// Initialize the database
		tSubunitCodeRepository.saveAndFlush(tSubunitCode);

		// Get all the tSubunitCodeList
		restTSubunitCodeMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.[*].id").value(hasItem(tSubunitCode.getId().intValue())))
			.andExpect(jsonPath("$.[*].subuntSubunit").value(hasItem(DEFAULT_SUBUNT_SUBUNIT)))
			.andExpect(jsonPath("$.[*].subuntHeadid").value(hasItem(DEFAULT_SUBUNT_HEADID)))
			.andExpect(jsonPath("$.[*].subuntActingHeadid").value(hasItem(DEFAULT_SUBUNT_ACTING_HEADID)))
			.andExpect(jsonPath("$.[*].subuntHrpayId").value(hasItem(DEFAULT_SUBUNT_HRPAY_ID)))
			.andExpect(jsonPath("$.[*].subuntPapId").value(hasItem(DEFAULT_SUBUNT_PAP_ID)))
			.andExpect(jsonPath("$.[*].subuntPapCode").value(hasItem(DEFAULT_SUBUNT_PAP_CODE)))
			.andExpect(jsonPath("$.[*].subuntPapActive").value(hasItem(DEFAULT_SUBUNT_PAP_ACTIVE)))
			.andExpect(jsonPath("$.[*].subunyUnitid").value(hasItem(DEFAULT_SUBUNY_UNITID)));
	}

	@Test
	@Transactional
	void getTSubunitCode() throws Exception {
		// Initialize the database
		tSubunitCodeRepository.saveAndFlush(tSubunitCode);

		// Get the tSubunitCode
		restTSubunitCodeMockMvc
			.perform(get(ENTITY_API_URL_ID, tSubunitCode.getId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.id").value(tSubunitCode.getId().intValue()))
			.andExpect(jsonPath("$.subuntSubunit").value(DEFAULT_SUBUNT_SUBUNIT))
			.andExpect(jsonPath("$.subuntHeadid").value(DEFAULT_SUBUNT_HEADID))
			.andExpect(jsonPath("$.subuntActingHeadid").value(DEFAULT_SUBUNT_ACTING_HEADID))
			.andExpect(jsonPath("$.subuntHrpayId").value(DEFAULT_SUBUNT_HRPAY_ID))
			.andExpect(jsonPath("$.subuntPapId").value(DEFAULT_SUBUNT_PAP_ID))
			.andExpect(jsonPath("$.subuntPapCode").value(DEFAULT_SUBUNT_PAP_CODE))
			.andExpect(jsonPath("$.subuntPapActive").value(DEFAULT_SUBUNT_PAP_ACTIVE))
			.andExpect(jsonPath("$.subunyUnitid").value(DEFAULT_SUBUNY_UNITID));
	}

	@Test
	@Transactional
	void getNonExistingTSubunitCode() throws Exception {
		// Get the tSubunitCode
		restTSubunitCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	void putExistingTSubunitCode() throws Exception {
		// Initialize the database
		tSubunitCodeRepository.saveAndFlush(tSubunitCode);

		int databaseSizeBeforeUpdate = tSubunitCodeRepository.findAll().size();

		// Update the tSubunitCode
		TSubunitCode updatedTSubunitCode = tSubunitCodeRepository.findById(tSubunitCode.getId()).get();
		// Disconnect from session so that the updates on updatedTSubunitCode are not directly saved in db
		em.detach(updatedTSubunitCode);
		updatedTSubunitCode
			.subuntSubunit(UPDATED_SUBUNT_SUBUNIT)
			.subuntHeadid(UPDATED_SUBUNT_HEADID)
			.subuntActingHeadid(UPDATED_SUBUNT_ACTING_HEADID)
			.subuntHrpayId(UPDATED_SUBUNT_HRPAY_ID)
			.subuntPapId(UPDATED_SUBUNT_PAP_ID)
			.subuntPapCode(UPDATED_SUBUNT_PAP_CODE)
			.subuntPapActive(UPDATED_SUBUNT_PAP_ACTIVE)
			.subunyUnitid(UPDATED_SUBUNY_UNITID);

		restTSubunitCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, updatedTSubunitCode.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(updatedTSubunitCode))
			)
			.andExpect(status().isOk());

		// Validate the TSubunitCode in the database
		List<TSubunitCode> tSubunitCodeList = tSubunitCodeRepository.findAll();
		assertThat(tSubunitCodeList).hasSize(databaseSizeBeforeUpdate);
		TSubunitCode testTSubunitCode = tSubunitCodeList.get(tSubunitCodeList.size() - 1);
		assertThat(testTSubunitCode.getSubuntSubunit()).isEqualTo(UPDATED_SUBUNT_SUBUNIT);
		assertThat(testTSubunitCode.getSubuntHeadid()).isEqualTo(UPDATED_SUBUNT_HEADID);
		assertThat(testTSubunitCode.getSubuntActingHeadid()).isEqualTo(UPDATED_SUBUNT_ACTING_HEADID);
		assertThat(testTSubunitCode.getSubuntHrpayId()).isEqualTo(UPDATED_SUBUNT_HRPAY_ID);
		assertThat(testTSubunitCode.getSubuntPapId()).isEqualTo(UPDATED_SUBUNT_PAP_ID);
		assertThat(testTSubunitCode.getSubuntPapCode()).isEqualTo(UPDATED_SUBUNT_PAP_CODE);
		assertThat(testTSubunitCode.getSubuntPapActive()).isEqualTo(UPDATED_SUBUNT_PAP_ACTIVE);
		assertThat(testTSubunitCode.getSubunyUnitid()).isEqualTo(UPDATED_SUBUNY_UNITID);
	}

	@Test
	@Transactional
	void putNonExistingTSubunitCode() throws Exception {
		int databaseSizeBeforeUpdate = tSubunitCodeRepository.findAll().size();
		tSubunitCode.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTSubunitCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, tSubunitCode.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tSubunitCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TSubunitCode in the database
		List<TSubunitCode> tSubunitCodeList = tSubunitCodeRepository.findAll();
		assertThat(tSubunitCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithIdMismatchTSubunitCode() throws Exception {
		int databaseSizeBeforeUpdate = tSubunitCodeRepository.findAll().size();
		tSubunitCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTSubunitCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tSubunitCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TSubunitCode in the database
		List<TSubunitCode> tSubunitCodeList = tSubunitCodeRepository.findAll();
		assertThat(tSubunitCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithMissingIdPathParamTSubunitCode() throws Exception {
		int databaseSizeBeforeUpdate = tSubunitCodeRepository.findAll().size();
		tSubunitCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTSubunitCodeMockMvc
			.perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tSubunitCode)))
			.andExpect(status().isMethodNotAllowed());

		// Validate the TSubunitCode in the database
		List<TSubunitCode> tSubunitCodeList = tSubunitCodeRepository.findAll();
		assertThat(tSubunitCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void partialUpdateTSubunitCodeWithPatch() throws Exception {
		// Initialize the database
		tSubunitCodeRepository.saveAndFlush(tSubunitCode);

		int databaseSizeBeforeUpdate = tSubunitCodeRepository.findAll().size();

		// Update the tSubunitCode using partial update
		TSubunitCode partialUpdatedTSubunitCode = new TSubunitCode();
		partialUpdatedTSubunitCode.setId(tSubunitCode.getId());

		partialUpdatedTSubunitCode
			.subuntSubunit(UPDATED_SUBUNT_SUBUNIT)
			.subuntActingHeadid(UPDATED_SUBUNT_ACTING_HEADID)
			.subuntPapId(UPDATED_SUBUNT_PAP_ID)
			.subuntPapCode(UPDATED_SUBUNT_PAP_CODE);

		restTSubunitCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTSubunitCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTSubunitCode))
			)
			.andExpect(status().isOk());

		// Validate the TSubunitCode in the database
		List<TSubunitCode> tSubunitCodeList = tSubunitCodeRepository.findAll();
		assertThat(tSubunitCodeList).hasSize(databaseSizeBeforeUpdate);
		TSubunitCode testTSubunitCode = tSubunitCodeList.get(tSubunitCodeList.size() - 1);
		assertThat(testTSubunitCode.getSubuntSubunit()).isEqualTo(UPDATED_SUBUNT_SUBUNIT);
		assertThat(testTSubunitCode.getSubuntHeadid()).isEqualTo(DEFAULT_SUBUNT_HEADID);
		assertThat(testTSubunitCode.getSubuntActingHeadid()).isEqualTo(UPDATED_SUBUNT_ACTING_HEADID);
		assertThat(testTSubunitCode.getSubuntHrpayId()).isEqualTo(DEFAULT_SUBUNT_HRPAY_ID);
		assertThat(testTSubunitCode.getSubuntPapId()).isEqualTo(UPDATED_SUBUNT_PAP_ID);
		assertThat(testTSubunitCode.getSubuntPapCode()).isEqualTo(UPDATED_SUBUNT_PAP_CODE);
		assertThat(testTSubunitCode.getSubuntPapActive()).isEqualTo(DEFAULT_SUBUNT_PAP_ACTIVE);
		assertThat(testTSubunitCode.getSubunyUnitid()).isEqualTo(DEFAULT_SUBUNY_UNITID);
	}

	@Test
	@Transactional
	void fullUpdateTSubunitCodeWithPatch() throws Exception {
		// Initialize the database
		tSubunitCodeRepository.saveAndFlush(tSubunitCode);

		int databaseSizeBeforeUpdate = tSubunitCodeRepository.findAll().size();

		// Update the tSubunitCode using partial update
		TSubunitCode partialUpdatedTSubunitCode = new TSubunitCode();
		partialUpdatedTSubunitCode.setId(tSubunitCode.getId());

		partialUpdatedTSubunitCode
			.subuntSubunit(UPDATED_SUBUNT_SUBUNIT)
			.subuntHeadid(UPDATED_SUBUNT_HEADID)
			.subuntActingHeadid(UPDATED_SUBUNT_ACTING_HEADID)
			.subuntHrpayId(UPDATED_SUBUNT_HRPAY_ID)
			.subuntPapId(UPDATED_SUBUNT_PAP_ID)
			.subuntPapCode(UPDATED_SUBUNT_PAP_CODE)
			.subuntPapActive(UPDATED_SUBUNT_PAP_ACTIVE)
			.subunyUnitid(UPDATED_SUBUNY_UNITID);

		restTSubunitCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTSubunitCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTSubunitCode))
			)
			.andExpect(status().isOk());

		// Validate the TSubunitCode in the database
		List<TSubunitCode> tSubunitCodeList = tSubunitCodeRepository.findAll();
		assertThat(tSubunitCodeList).hasSize(databaseSizeBeforeUpdate);
		TSubunitCode testTSubunitCode = tSubunitCodeList.get(tSubunitCodeList.size() - 1);
		assertThat(testTSubunitCode.getSubuntSubunit()).isEqualTo(UPDATED_SUBUNT_SUBUNIT);
		assertThat(testTSubunitCode.getSubuntHeadid()).isEqualTo(UPDATED_SUBUNT_HEADID);
		assertThat(testTSubunitCode.getSubuntActingHeadid()).isEqualTo(UPDATED_SUBUNT_ACTING_HEADID);
		assertThat(testTSubunitCode.getSubuntHrpayId()).isEqualTo(UPDATED_SUBUNT_HRPAY_ID);
		assertThat(testTSubunitCode.getSubuntPapId()).isEqualTo(UPDATED_SUBUNT_PAP_ID);
		assertThat(testTSubunitCode.getSubuntPapCode()).isEqualTo(UPDATED_SUBUNT_PAP_CODE);
		assertThat(testTSubunitCode.getSubuntPapActive()).isEqualTo(UPDATED_SUBUNT_PAP_ACTIVE);
		assertThat(testTSubunitCode.getSubunyUnitid()).isEqualTo(UPDATED_SUBUNY_UNITID);
	}

	@Test
	@Transactional
	void patchNonExistingTSubunitCode() throws Exception {
		int databaseSizeBeforeUpdate = tSubunitCodeRepository.findAll().size();
		tSubunitCode.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTSubunitCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, tSubunitCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tSubunitCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TSubunitCode in the database
		List<TSubunitCode> tSubunitCodeList = tSubunitCodeRepository.findAll();
		assertThat(tSubunitCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithIdMismatchTSubunitCode() throws Exception {
		int databaseSizeBeforeUpdate = tSubunitCodeRepository.findAll().size();
		tSubunitCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTSubunitCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tSubunitCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TSubunitCode in the database
		List<TSubunitCode> tSubunitCodeList = tSubunitCodeRepository.findAll();
		assertThat(tSubunitCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithMissingIdPathParamTSubunitCode() throws Exception {
		int databaseSizeBeforeUpdate = tSubunitCodeRepository.findAll().size();
		tSubunitCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTSubunitCodeMockMvc
			.perform(
				patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tSubunitCode))
			)
			.andExpect(status().isMethodNotAllowed());

		// Validate the TSubunitCode in the database
		List<TSubunitCode> tSubunitCodeList = tSubunitCodeRepository.findAll();
		assertThat(tSubunitCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void deleteTSubunitCode() throws Exception {
		// Initialize the database
		tSubunitCodeRepository.saveAndFlush(tSubunitCode);

		int databaseSizeBeforeDelete = tSubunitCodeRepository.findAll().size();

		// Delete the tSubunitCode
		restTSubunitCodeMockMvc
			.perform(delete(ENTITY_API_URL_ID, tSubunitCode.getId()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());

		// Validate the database contains one less item
		List<TSubunitCode> tSubunitCodeList = tSubunitCodeRepository.findAll();
		assertThat(tSubunitCodeList).hasSize(databaseSizeBeforeDelete - 1);
	}
}
