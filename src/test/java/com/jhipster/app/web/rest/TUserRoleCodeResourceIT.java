package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TUserRoleCode;
import com.jhipster.app.repository.TUserRoleCodeRepository;
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
 * Integration tests for the {@link TUserRoleCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TUserRoleCodeResourceIT {

	private static final String DEFAULT_ROLE_NAME = "AAAAAAAAAA";
	private static final String UPDATED_ROLE_NAME = "BBBBBBBBBB";

	private static final String DEFAULT_ROLE_HEAD = "AAAAAAAAAA";
	private static final String UPDATED_ROLE_HEAD = "BBBBBBBBBB";

	private static final Integer DEFAULT_ACT_APPROVER_1 = 1;
	private static final Integer UPDATED_ACT_APPROVER_1 = 2;

	private static final Integer DEFAULT_ACT_APPROVER_2 = 1;
	private static final Integer UPDATED_ACT_APPROVER_2 = 2;

	private static final Integer DEFAULT_ENTERED_BY = 1;
	private static final Integer UPDATED_ENTERED_BY = 2;

	private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
	private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

	private static final Integer DEFAULT_MODIFIED_BY = 1;
	private static final Integer UPDATED_MODIFIED_BY = 2;

	private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
	private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

	private static final String ENTITY_API_URL = "/api/t-user-role-codes";
	private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

	private static Random random = new Random();
	private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

	@Autowired
	private TUserRoleCodeRepository tUserRoleCodeRepository;

	@Autowired
	private EntityManager em;

	@Autowired
	private MockMvc restTUserRoleCodeMockMvc;

	private TUserRoleCode tUserRoleCode;

	/**
	 * Create an entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TUserRoleCode createEntity(EntityManager em) {
		TUserRoleCode tUserRoleCode = new TUserRoleCode()
			.roleName(DEFAULT_ROLE_NAME)
			.roleHead(DEFAULT_ROLE_HEAD)
			.actApprover1(DEFAULT_ACT_APPROVER_1)
			.actApprover2(DEFAULT_ACT_APPROVER_2)
			.enteredBy(DEFAULT_ENTERED_BY)
			.enteredDate(DEFAULT_ENTERED_DATE)
			.modifiedBy(DEFAULT_MODIFIED_BY)
			.modifiedDate(DEFAULT_MODIFIED_DATE);
		return tUserRoleCode;
	}

	/**
	 * Create an updated entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TUserRoleCode createUpdatedEntity(EntityManager em) {
		TUserRoleCode tUserRoleCode = new TUserRoleCode()
			.roleName(UPDATED_ROLE_NAME)
			.roleHead(UPDATED_ROLE_HEAD)
			.actApprover1(UPDATED_ACT_APPROVER_1)
			.actApprover2(UPDATED_ACT_APPROVER_2)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);
		return tUserRoleCode;
	}

	@BeforeEach
	public void initTest() {
		tUserRoleCode = createEntity(em);
	}

	@Test
	@Transactional
	void createTUserRoleCode() throws Exception {
		int databaseSizeBeforeCreate = tUserRoleCodeRepository.findAll().size();
		// Create the TUserRoleCode
		restTUserRoleCodeMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tUserRoleCode)))
			.andExpect(status().isCreated());

		// Validate the TUserRoleCode in the database
		List<TUserRoleCode> tUserRoleCodeList = tUserRoleCodeRepository.findAll();
		assertThat(tUserRoleCodeList).hasSize(databaseSizeBeforeCreate + 1);
		TUserRoleCode testTUserRoleCode = tUserRoleCodeList.get(tUserRoleCodeList.size() - 1);
		assertThat(testTUserRoleCode.getRoleName()).isEqualTo(DEFAULT_ROLE_NAME);
		assertThat(testTUserRoleCode.getRoleHead()).isEqualTo(DEFAULT_ROLE_HEAD);
		assertThat(testTUserRoleCode.getActApprover1()).isEqualTo(DEFAULT_ACT_APPROVER_1);
		assertThat(testTUserRoleCode.getActApprover2()).isEqualTo(DEFAULT_ACT_APPROVER_2);
		assertThat(testTUserRoleCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
		assertThat(testTUserRoleCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
		assertThat(testTUserRoleCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
		assertThat(testTUserRoleCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void createTUserRoleCodeWithExistingId() throws Exception {
		// Create the TUserRoleCode with an existing ID
		tUserRoleCode.setId(1L);

		int databaseSizeBeforeCreate = tUserRoleCodeRepository.findAll().size();

		// An entity with an existing ID cannot be created, so this API call must fail
		restTUserRoleCodeMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tUserRoleCode)))
			.andExpect(status().isBadRequest());

		// Validate the TUserRoleCode in the database
		List<TUserRoleCode> tUserRoleCodeList = tUserRoleCodeRepository.findAll();
		assertThat(tUserRoleCodeList).hasSize(databaseSizeBeforeCreate);
	}

	@Test
	@Transactional
	void getAllTUserRoleCodes() throws Exception {
		// Initialize the database
		tUserRoleCodeRepository.saveAndFlush(tUserRoleCode);

		// Get all the tUserRoleCodeList
		restTUserRoleCodeMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.[*].id").value(hasItem(tUserRoleCode.getId().intValue())))
			.andExpect(jsonPath("$.[*].roleName").value(hasItem(DEFAULT_ROLE_NAME)))
			.andExpect(jsonPath("$.[*].roleHead").value(hasItem(DEFAULT_ROLE_HEAD)))
			.andExpect(jsonPath("$.[*].actApprover1").value(hasItem(DEFAULT_ACT_APPROVER_1)))
			.andExpect(jsonPath("$.[*].actApprover2").value(hasItem(DEFAULT_ACT_APPROVER_2)))
			.andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
			.andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
			.andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
			.andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
	}

	@Test
	@Transactional
	void getTUserRoleCode() throws Exception {
		// Initialize the database
		tUserRoleCodeRepository.saveAndFlush(tUserRoleCode);

		// Get the tUserRoleCode
		restTUserRoleCodeMockMvc
			.perform(get(ENTITY_API_URL_ID, tUserRoleCode.getId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.id").value(tUserRoleCode.getId().intValue()))
			.andExpect(jsonPath("$.roleName").value(DEFAULT_ROLE_NAME))
			.andExpect(jsonPath("$.roleHead").value(DEFAULT_ROLE_HEAD))
			.andExpect(jsonPath("$.actApprover1").value(DEFAULT_ACT_APPROVER_1))
			.andExpect(jsonPath("$.actApprover2").value(DEFAULT_ACT_APPROVER_2))
			.andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
			.andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
			.andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
			.andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
	}

	@Test
	@Transactional
	void getNonExistingTUserRoleCode() throws Exception {
		// Get the tUserRoleCode
		restTUserRoleCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	void putExistingTUserRoleCode() throws Exception {
		// Initialize the database
		tUserRoleCodeRepository.saveAndFlush(tUserRoleCode);

		int databaseSizeBeforeUpdate = tUserRoleCodeRepository.findAll().size();

		// Update the tUserRoleCode
		TUserRoleCode updatedTUserRoleCode = tUserRoleCodeRepository.findById(tUserRoleCode.getId()).get();
		// Disconnect from session so that the updates on updatedTUserRoleCode are not directly saved in db
		em.detach(updatedTUserRoleCode);
		updatedTUserRoleCode
			.roleName(UPDATED_ROLE_NAME)
			.roleHead(UPDATED_ROLE_HEAD)
			.actApprover1(UPDATED_ACT_APPROVER_1)
			.actApprover2(UPDATED_ACT_APPROVER_2)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTUserRoleCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, updatedTUserRoleCode.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(updatedTUserRoleCode))
			)
			.andExpect(status().isOk());

		// Validate the TUserRoleCode in the database
		List<TUserRoleCode> tUserRoleCodeList = tUserRoleCodeRepository.findAll();
		assertThat(tUserRoleCodeList).hasSize(databaseSizeBeforeUpdate);
		TUserRoleCode testTUserRoleCode = tUserRoleCodeList.get(tUserRoleCodeList.size() - 1);
		assertThat(testTUserRoleCode.getRoleName()).isEqualTo(UPDATED_ROLE_NAME);
		assertThat(testTUserRoleCode.getRoleHead()).isEqualTo(UPDATED_ROLE_HEAD);
		assertThat(testTUserRoleCode.getActApprover1()).isEqualTo(UPDATED_ACT_APPROVER_1);
		assertThat(testTUserRoleCode.getActApprover2()).isEqualTo(UPDATED_ACT_APPROVER_2);
		assertThat(testTUserRoleCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTUserRoleCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTUserRoleCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTUserRoleCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void putNonExistingTUserRoleCode() throws Exception {
		int databaseSizeBeforeUpdate = tUserRoleCodeRepository.findAll().size();
		tUserRoleCode.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTUserRoleCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, tUserRoleCode.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tUserRoleCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TUserRoleCode in the database
		List<TUserRoleCode> tUserRoleCodeList = tUserRoleCodeRepository.findAll();
		assertThat(tUserRoleCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithIdMismatchTUserRoleCode() throws Exception {
		int databaseSizeBeforeUpdate = tUserRoleCodeRepository.findAll().size();
		tUserRoleCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTUserRoleCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tUserRoleCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TUserRoleCode in the database
		List<TUserRoleCode> tUserRoleCodeList = tUserRoleCodeRepository.findAll();
		assertThat(tUserRoleCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithMissingIdPathParamTUserRoleCode() throws Exception {
		int databaseSizeBeforeUpdate = tUserRoleCodeRepository.findAll().size();
		tUserRoleCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTUserRoleCodeMockMvc
			.perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tUserRoleCode)))
			.andExpect(status().isMethodNotAllowed());

		// Validate the TUserRoleCode in the database
		List<TUserRoleCode> tUserRoleCodeList = tUserRoleCodeRepository.findAll();
		assertThat(tUserRoleCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void partialUpdateTUserRoleCodeWithPatch() throws Exception {
		// Initialize the database
		tUserRoleCodeRepository.saveAndFlush(tUserRoleCode);

		int databaseSizeBeforeUpdate = tUserRoleCodeRepository.findAll().size();

		// Update the tUserRoleCode using partial update
		TUserRoleCode partialUpdatedTUserRoleCode = new TUserRoleCode();
		partialUpdatedTUserRoleCode.setId(tUserRoleCode.getId());

		partialUpdatedTUserRoleCode
			.actApprover1(UPDATED_ACT_APPROVER_1)
			.actApprover2(UPDATED_ACT_APPROVER_2)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTUserRoleCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTUserRoleCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTUserRoleCode))
			)
			.andExpect(status().isOk());

		// Validate the TUserRoleCode in the database
		List<TUserRoleCode> tUserRoleCodeList = tUserRoleCodeRepository.findAll();
		assertThat(tUserRoleCodeList).hasSize(databaseSizeBeforeUpdate);
		TUserRoleCode testTUserRoleCode = tUserRoleCodeList.get(tUserRoleCodeList.size() - 1);
		assertThat(testTUserRoleCode.getRoleName()).isEqualTo(DEFAULT_ROLE_NAME);
		assertThat(testTUserRoleCode.getRoleHead()).isEqualTo(DEFAULT_ROLE_HEAD);
		assertThat(testTUserRoleCode.getActApprover1()).isEqualTo(UPDATED_ACT_APPROVER_1);
		assertThat(testTUserRoleCode.getActApprover2()).isEqualTo(UPDATED_ACT_APPROVER_2);
		assertThat(testTUserRoleCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
		assertThat(testTUserRoleCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
		assertThat(testTUserRoleCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTUserRoleCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void fullUpdateTUserRoleCodeWithPatch() throws Exception {
		// Initialize the database
		tUserRoleCodeRepository.saveAndFlush(tUserRoleCode);

		int databaseSizeBeforeUpdate = tUserRoleCodeRepository.findAll().size();

		// Update the tUserRoleCode using partial update
		TUserRoleCode partialUpdatedTUserRoleCode = new TUserRoleCode();
		partialUpdatedTUserRoleCode.setId(tUserRoleCode.getId());

		partialUpdatedTUserRoleCode
			.roleName(UPDATED_ROLE_NAME)
			.roleHead(UPDATED_ROLE_HEAD)
			.actApprover1(UPDATED_ACT_APPROVER_1)
			.actApprover2(UPDATED_ACT_APPROVER_2)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTUserRoleCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTUserRoleCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTUserRoleCode))
			)
			.andExpect(status().isOk());

		// Validate the TUserRoleCode in the database
		List<TUserRoleCode> tUserRoleCodeList = tUserRoleCodeRepository.findAll();
		assertThat(tUserRoleCodeList).hasSize(databaseSizeBeforeUpdate);
		TUserRoleCode testTUserRoleCode = tUserRoleCodeList.get(tUserRoleCodeList.size() - 1);
		assertThat(testTUserRoleCode.getRoleName()).isEqualTo(UPDATED_ROLE_NAME);
		assertThat(testTUserRoleCode.getRoleHead()).isEqualTo(UPDATED_ROLE_HEAD);
		assertThat(testTUserRoleCode.getActApprover1()).isEqualTo(UPDATED_ACT_APPROVER_1);
		assertThat(testTUserRoleCode.getActApprover2()).isEqualTo(UPDATED_ACT_APPROVER_2);
		assertThat(testTUserRoleCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTUserRoleCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTUserRoleCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTUserRoleCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void patchNonExistingTUserRoleCode() throws Exception {
		int databaseSizeBeforeUpdate = tUserRoleCodeRepository.findAll().size();
		tUserRoleCode.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTUserRoleCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, tUserRoleCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tUserRoleCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TUserRoleCode in the database
		List<TUserRoleCode> tUserRoleCodeList = tUserRoleCodeRepository.findAll();
		assertThat(tUserRoleCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithIdMismatchTUserRoleCode() throws Exception {
		int databaseSizeBeforeUpdate = tUserRoleCodeRepository.findAll().size();
		tUserRoleCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTUserRoleCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tUserRoleCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TUserRoleCode in the database
		List<TUserRoleCode> tUserRoleCodeList = tUserRoleCodeRepository.findAll();
		assertThat(tUserRoleCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithMissingIdPathParamTUserRoleCode() throws Exception {
		int databaseSizeBeforeUpdate = tUserRoleCodeRepository.findAll().size();
		tUserRoleCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTUserRoleCodeMockMvc
			.perform(
				patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tUserRoleCode))
			)
			.andExpect(status().isMethodNotAllowed());

		// Validate the TUserRoleCode in the database
		List<TUserRoleCode> tUserRoleCodeList = tUserRoleCodeRepository.findAll();
		assertThat(tUserRoleCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void deleteTUserRoleCode() throws Exception {
		// Initialize the database
		tUserRoleCodeRepository.saveAndFlush(tUserRoleCode);

		int databaseSizeBeforeDelete = tUserRoleCodeRepository.findAll().size();

		// Delete the tUserRoleCode
		restTUserRoleCodeMockMvc
			.perform(delete(ENTITY_API_URL_ID, tUserRoleCode.getId()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());

		// Validate the database contains one less item
		List<TUserRoleCode> tUserRoleCodeList = tUserRoleCodeRepository.findAll();
		assertThat(tUserRoleCodeList).hasSize(databaseSizeBeforeDelete - 1);
	}
}
