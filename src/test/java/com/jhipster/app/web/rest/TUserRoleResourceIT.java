package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TUserRole;
import com.jhipster.app.repository.TUserRoleRepository;
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
 * Integration tests for the {@link TUserRoleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TUserRoleResourceIT {

	private static final Integer DEFAULT_SYSUSER_ID = 1;
	private static final Integer UPDATED_SYSUSER_ID = 2;

	private static final Integer DEFAULT_USR_ROLEID = 1;
	private static final Integer UPDATED_USR_ROLEID = 2;

	private static final Integer DEFAULT_ENTERED_BY = 1;
	private static final Integer UPDATED_ENTERED_BY = 2;

	private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
	private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

	private static final Integer DEFAULT_MODIFIED_BY = 1;
	private static final Integer UPDATED_MODIFIED_BY = 2;

	private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
	private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

	private static final String ENTITY_API_URL = "/api/t-user-roles";
	private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

	private static Random random = new Random();
	private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

	@Autowired
	private TUserRoleRepository tUserRoleRepository;

	@Autowired
	private EntityManager em;

	@Autowired
	private MockMvc restTUserRoleMockMvc;

	private TUserRole tUserRole;

	/**
	 * Create an entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TUserRole createEntity(EntityManager em) {
		TUserRole tUserRole = new TUserRole()
			.sysuserId(DEFAULT_SYSUSER_ID)
			.usrRoleid(DEFAULT_USR_ROLEID)
			.enteredBy(DEFAULT_ENTERED_BY)
			.enteredDate(DEFAULT_ENTERED_DATE)
			.modifiedBy(DEFAULT_MODIFIED_BY)
			.modifiedDate(DEFAULT_MODIFIED_DATE);
		return tUserRole;
	}

	/**
	 * Create an updated entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TUserRole createUpdatedEntity(EntityManager em) {
		TUserRole tUserRole = new TUserRole()
			.sysuserId(UPDATED_SYSUSER_ID)
			.usrRoleid(UPDATED_USR_ROLEID)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);
		return tUserRole;
	}

	@BeforeEach
	public void initTest() {
		tUserRole = createEntity(em);
	}

	@Test
	@Transactional
	void createTUserRole() throws Exception {
		int databaseSizeBeforeCreate = tUserRoleRepository.findAll().size();
		// Create the TUserRole
		restTUserRoleMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tUserRole)))
			.andExpect(status().isCreated());

		// Validate the TUserRole in the database
		List<TUserRole> tUserRoleList = tUserRoleRepository.findAll();
		assertThat(tUserRoleList).hasSize(databaseSizeBeforeCreate + 1);
		TUserRole testTUserRole = tUserRoleList.get(tUserRoleList.size() - 1);
		assertThat(testTUserRole.getSysuserId()).isEqualTo(DEFAULT_SYSUSER_ID);
		assertThat(testTUserRole.getUsrRoleid()).isEqualTo(DEFAULT_USR_ROLEID);
		assertThat(testTUserRole.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
		assertThat(testTUserRole.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
		assertThat(testTUserRole.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
		assertThat(testTUserRole.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void createTUserRoleWithExistingId() throws Exception {
		// Create the TUserRole with an existing ID
		tUserRole.setId(1L);

		int databaseSizeBeforeCreate = tUserRoleRepository.findAll().size();

		// An entity with an existing ID cannot be created, so this API call must fail
		restTUserRoleMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tUserRole)))
			.andExpect(status().isBadRequest());

		// Validate the TUserRole in the database
		List<TUserRole> tUserRoleList = tUserRoleRepository.findAll();
		assertThat(tUserRoleList).hasSize(databaseSizeBeforeCreate);
	}

	@Test
	@Transactional
	void getAllTUserRoles() throws Exception {
		// Initialize the database
		tUserRoleRepository.saveAndFlush(tUserRole);

		// Get all the tUserRoleList
		restTUserRoleMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.[*].id").value(hasItem(tUserRole.getId().intValue())))
			.andExpect(jsonPath("$.[*].sysuserId").value(hasItem(DEFAULT_SYSUSER_ID)))
			.andExpect(jsonPath("$.[*].usrRoleid").value(hasItem(DEFAULT_USR_ROLEID)))
			.andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
			.andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
			.andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
			.andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
	}

	@Test
	@Transactional
	void getTUserRole() throws Exception {
		// Initialize the database
		tUserRoleRepository.saveAndFlush(tUserRole);

		// Get the tUserRole
		restTUserRoleMockMvc
			.perform(get(ENTITY_API_URL_ID, tUserRole.getId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.id").value(tUserRole.getId().intValue()))
			.andExpect(jsonPath("$.sysuserId").value(DEFAULT_SYSUSER_ID))
			.andExpect(jsonPath("$.usrRoleid").value(DEFAULT_USR_ROLEID))
			.andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
			.andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
			.andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
			.andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
	}

	@Test
	@Transactional
	void getNonExistingTUserRole() throws Exception {
		// Get the tUserRole
		restTUserRoleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	void putExistingTUserRole() throws Exception {
		// Initialize the database
		tUserRoleRepository.saveAndFlush(tUserRole);

		int databaseSizeBeforeUpdate = tUserRoleRepository.findAll().size();

		// Update the tUserRole
		TUserRole updatedTUserRole = tUserRoleRepository.findById(tUserRole.getId()).get();
		// Disconnect from session so that the updates on updatedTUserRole are not directly saved in db
		em.detach(updatedTUserRole);
		updatedTUserRole
			.sysuserId(UPDATED_SYSUSER_ID)
			.usrRoleid(UPDATED_USR_ROLEID)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTUserRoleMockMvc
			.perform(
				put(ENTITY_API_URL_ID, updatedTUserRole.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(updatedTUserRole))
			)
			.andExpect(status().isOk());

		// Validate the TUserRole in the database
		List<TUserRole> tUserRoleList = tUserRoleRepository.findAll();
		assertThat(tUserRoleList).hasSize(databaseSizeBeforeUpdate);
		TUserRole testTUserRole = tUserRoleList.get(tUserRoleList.size() - 1);
		assertThat(testTUserRole.getSysuserId()).isEqualTo(UPDATED_SYSUSER_ID);
		assertThat(testTUserRole.getUsrRoleid()).isEqualTo(UPDATED_USR_ROLEID);
		assertThat(testTUserRole.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTUserRole.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTUserRole.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTUserRole.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void putNonExistingTUserRole() throws Exception {
		int databaseSizeBeforeUpdate = tUserRoleRepository.findAll().size();
		tUserRole.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTUserRoleMockMvc
			.perform(
				put(ENTITY_API_URL_ID, tUserRole.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tUserRole))
			)
			.andExpect(status().isBadRequest());

		// Validate the TUserRole in the database
		List<TUserRole> tUserRoleList = tUserRoleRepository.findAll();
		assertThat(tUserRoleList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithIdMismatchTUserRole() throws Exception {
		int databaseSizeBeforeUpdate = tUserRoleRepository.findAll().size();
		tUserRole.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTUserRoleMockMvc
			.perform(
				put(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tUserRole))
			)
			.andExpect(status().isBadRequest());

		// Validate the TUserRole in the database
		List<TUserRole> tUserRoleList = tUserRoleRepository.findAll();
		assertThat(tUserRoleList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithMissingIdPathParamTUserRole() throws Exception {
		int databaseSizeBeforeUpdate = tUserRoleRepository.findAll().size();
		tUserRole.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTUserRoleMockMvc
			.perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tUserRole)))
			.andExpect(status().isMethodNotAllowed());

		// Validate the TUserRole in the database
		List<TUserRole> tUserRoleList = tUserRoleRepository.findAll();
		assertThat(tUserRoleList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void partialUpdateTUserRoleWithPatch() throws Exception {
		// Initialize the database
		tUserRoleRepository.saveAndFlush(tUserRole);

		int databaseSizeBeforeUpdate = tUserRoleRepository.findAll().size();

		// Update the tUserRole using partial update
		TUserRole partialUpdatedTUserRole = new TUserRole();
		partialUpdatedTUserRole.setId(tUserRole.getId());

		partialUpdatedTUserRole.usrRoleid(UPDATED_USR_ROLEID).enteredDate(UPDATED_ENTERED_DATE).modifiedBy(UPDATED_MODIFIED_BY);

		restTUserRoleMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTUserRole.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTUserRole))
			)
			.andExpect(status().isOk());

		// Validate the TUserRole in the database
		List<TUserRole> tUserRoleList = tUserRoleRepository.findAll();
		assertThat(tUserRoleList).hasSize(databaseSizeBeforeUpdate);
		TUserRole testTUserRole = tUserRoleList.get(tUserRoleList.size() - 1);
		assertThat(testTUserRole.getSysuserId()).isEqualTo(DEFAULT_SYSUSER_ID);
		assertThat(testTUserRole.getUsrRoleid()).isEqualTo(UPDATED_USR_ROLEID);
		assertThat(testTUserRole.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
		assertThat(testTUserRole.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTUserRole.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTUserRole.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void fullUpdateTUserRoleWithPatch() throws Exception {
		// Initialize the database
		tUserRoleRepository.saveAndFlush(tUserRole);

		int databaseSizeBeforeUpdate = tUserRoleRepository.findAll().size();

		// Update the tUserRole using partial update
		TUserRole partialUpdatedTUserRole = new TUserRole();
		partialUpdatedTUserRole.setId(tUserRole.getId());

		partialUpdatedTUserRole
			.sysuserId(UPDATED_SYSUSER_ID)
			.usrRoleid(UPDATED_USR_ROLEID)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTUserRoleMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTUserRole.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTUserRole))
			)
			.andExpect(status().isOk());

		// Validate the TUserRole in the database
		List<TUserRole> tUserRoleList = tUserRoleRepository.findAll();
		assertThat(tUserRoleList).hasSize(databaseSizeBeforeUpdate);
		TUserRole testTUserRole = tUserRoleList.get(tUserRoleList.size() - 1);
		assertThat(testTUserRole.getSysuserId()).isEqualTo(UPDATED_SYSUSER_ID);
		assertThat(testTUserRole.getUsrRoleid()).isEqualTo(UPDATED_USR_ROLEID);
		assertThat(testTUserRole.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTUserRole.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTUserRole.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTUserRole.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void patchNonExistingTUserRole() throws Exception {
		int databaseSizeBeforeUpdate = tUserRoleRepository.findAll().size();
		tUserRole.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTUserRoleMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, tUserRole.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tUserRole))
			)
			.andExpect(status().isBadRequest());

		// Validate the TUserRole in the database
		List<TUserRole> tUserRoleList = tUserRoleRepository.findAll();
		assertThat(tUserRoleList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithIdMismatchTUserRole() throws Exception {
		int databaseSizeBeforeUpdate = tUserRoleRepository.findAll().size();
		tUserRole.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTUserRoleMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tUserRole))
			)
			.andExpect(status().isBadRequest());

		// Validate the TUserRole in the database
		List<TUserRole> tUserRoleList = tUserRoleRepository.findAll();
		assertThat(tUserRoleList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithMissingIdPathParamTUserRole() throws Exception {
		int databaseSizeBeforeUpdate = tUserRoleRepository.findAll().size();
		tUserRole.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTUserRoleMockMvc
			.perform(
				patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tUserRole))
			)
			.andExpect(status().isMethodNotAllowed());

		// Validate the TUserRole in the database
		List<TUserRole> tUserRoleList = tUserRoleRepository.findAll();
		assertThat(tUserRoleList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void deleteTUserRole() throws Exception {
		// Initialize the database
		tUserRoleRepository.saveAndFlush(tUserRole);

		int databaseSizeBeforeDelete = tUserRoleRepository.findAll().size();

		// Delete the tUserRole
		restTUserRoleMockMvc
			.perform(delete(ENTITY_API_URL_ID, tUserRole.getId()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());

		// Validate the database contains one less item
		List<TUserRole> tUserRoleList = tUserRoleRepository.findAll();
		assertThat(tUserRoleList).hasSize(databaseSizeBeforeDelete - 1);
	}
}
