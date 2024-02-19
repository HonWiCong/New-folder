package com.jhipster.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.ApplicationUser;
import com.jhipster.app.domain.TOfficeCode;
import com.jhipster.app.domain.TSectionCode;
import com.jhipster.app.domain.TSubunitCode;
import com.jhipster.app.domain.TUnitCode;
import com.jhipster.app.domain.TUserRole;
import com.jhipster.app.domain.User;
import com.jhipster.app.repository.ApplicationUserRepository;
import com.jhipster.app.service.criteria.ApplicationUserCriteria;
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
 * Integration tests for the {@link ApplicationUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ApplicationUserResourceIT {

	private static final String DEFAULT_IC = "AAAAAAAAAA";
	private static final String UPDATED_IC = "BBBBBBBBBB";

	private static final String DEFAULT_STATUS = "A";
	private static final String UPDATED_STATUS = "B";

	private static final String ENTITY_API_URL = "/api/application-users";
	private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

	private static Random random = new Random();
	private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Autowired
	private EntityManager em;

	@Autowired
	private MockMvc restApplicationUserMockMvc;

	private ApplicationUser applicationUser;

	/**
	 * Create an entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static ApplicationUser createEntity(EntityManager em) {
		ApplicationUser applicationUser = new ApplicationUser().ic(DEFAULT_IC).status(DEFAULT_STATUS);
		return applicationUser;
	}

	/**
	 * Create an updated entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static ApplicationUser createUpdatedEntity(EntityManager em) {
		ApplicationUser applicationUser = new ApplicationUser().ic(UPDATED_IC).status(UPDATED_STATUS);
		return applicationUser;
	}

	@BeforeEach
	public void initTest() {
		applicationUser = createEntity(em);
	}

	@Test
	@Transactional
	void createApplicationUser() throws Exception {
		int databaseSizeBeforeCreate = applicationUserRepository.findAll().size();
		// Create the ApplicationUser
		restApplicationUserMockMvc
			.perform(
				post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(applicationUser))
			)
			.andExpect(status().isCreated());

		// Validate the ApplicationUser in the database
		List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
		assertThat(applicationUserList).hasSize(databaseSizeBeforeCreate + 1);
		ApplicationUser testApplicationUser = applicationUserList.get(applicationUserList.size() - 1);
		assertThat(testApplicationUser.getIc()).isEqualTo(DEFAULT_IC);
		assertThat(testApplicationUser.getStatus()).isEqualTo(DEFAULT_STATUS);
	}

	@Test
	@Transactional
	void createApplicationUserWithExistingId() throws Exception {
		// Create the ApplicationUser with an existing ID
		applicationUser.setId(1L);

		int databaseSizeBeforeCreate = applicationUserRepository.findAll().size();

		// An entity with an existing ID cannot be created, so this API call must fail
		restApplicationUserMockMvc
			.perform(
				post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(applicationUser))
			)
			.andExpect(status().isBadRequest());

		// Validate the ApplicationUser in the database
		List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
		assertThat(applicationUserList).hasSize(databaseSizeBeforeCreate);
	}

	@Test
	@Transactional
	void getAllApplicationUsers() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		// Get all the applicationUserList
		restApplicationUserMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.[*].id").value(hasItem(applicationUser.getId().intValue())))
			.andExpect(jsonPath("$.[*].ic").value(hasItem(DEFAULT_IC)))
			.andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
	}

	@Test
	@Transactional
	void getApplicationUser() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		// Get the applicationUser
		restApplicationUserMockMvc
			.perform(get(ENTITY_API_URL_ID, applicationUser.getId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.id").value(applicationUser.getId().intValue()))
			.andExpect(jsonPath("$.ic").value(DEFAULT_IC))
			.andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
	}

	@Test
	@Transactional
	void getApplicationUsersByIdFiltering() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		Long id = applicationUser.getId();

		defaultApplicationUserShouldBeFound("id.equals=" + id);
		defaultApplicationUserShouldNotBeFound("id.notEquals=" + id);

		defaultApplicationUserShouldBeFound("id.greaterThanOrEqual=" + id);
		defaultApplicationUserShouldNotBeFound("id.greaterThan=" + id);

		defaultApplicationUserShouldBeFound("id.lessThanOrEqual=" + id);
		defaultApplicationUserShouldNotBeFound("id.lessThan=" + id);
	}

	@Test
	@Transactional
	void getAllApplicationUsersByIcIsEqualToSomething() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		// Get all the applicationUserList where ic equals to DEFAULT_IC
		defaultApplicationUserShouldBeFound("ic.equals=" + DEFAULT_IC);

		// Get all the applicationUserList where ic equals to UPDATED_IC
		defaultApplicationUserShouldNotBeFound("ic.equals=" + UPDATED_IC);
	}

	@Test
	@Transactional
	void getAllApplicationUsersByIcIsInShouldWork() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		// Get all the applicationUserList where ic in DEFAULT_IC or UPDATED_IC
		defaultApplicationUserShouldBeFound("ic.in=" + DEFAULT_IC + "," + UPDATED_IC);

		// Get all the applicationUserList where ic equals to UPDATED_IC
		defaultApplicationUserShouldNotBeFound("ic.in=" + UPDATED_IC);
	}

	@Test
	@Transactional
	void getAllApplicationUsersByIcIsNullOrNotNull() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		// Get all the applicationUserList where ic is not null
		defaultApplicationUserShouldBeFound("ic.specified=true");

		// Get all the applicationUserList where ic is null
		defaultApplicationUserShouldNotBeFound("ic.specified=false");
	}

	@Test
	@Transactional
	void getAllApplicationUsersByIcContainsSomething() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		// Get all the applicationUserList where ic contains DEFAULT_IC
		defaultApplicationUserShouldBeFound("ic.contains=" + DEFAULT_IC);

		// Get all the applicationUserList where ic contains UPDATED_IC
		defaultApplicationUserShouldNotBeFound("ic.contains=" + UPDATED_IC);
	}

	@Test
	@Transactional
	void getAllApplicationUsersByIcNotContainsSomething() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		// Get all the applicationUserList where ic does not contain DEFAULT_IC
		defaultApplicationUserShouldNotBeFound("ic.doesNotContain=" + DEFAULT_IC);

		// Get all the applicationUserList where ic does not contain UPDATED_IC
		defaultApplicationUserShouldBeFound("ic.doesNotContain=" + UPDATED_IC);
	}

	@Test
	@Transactional
	void getAllApplicationUsersByStatusIsEqualToSomething() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		// Get all the applicationUserList where status equals to DEFAULT_STATUS
		defaultApplicationUserShouldBeFound("status.equals=" + DEFAULT_STATUS);

		// Get all the applicationUserList where status equals to UPDATED_STATUS
		defaultApplicationUserShouldNotBeFound("status.equals=" + UPDATED_STATUS);
	}

	@Test
	@Transactional
	void getAllApplicationUsersByStatusIsInShouldWork() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		// Get all the applicationUserList where status in DEFAULT_STATUS or UPDATED_STATUS
		defaultApplicationUserShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

		// Get all the applicationUserList where status equals to UPDATED_STATUS
		defaultApplicationUserShouldNotBeFound("status.in=" + UPDATED_STATUS);
	}

	@Test
	@Transactional
	void getAllApplicationUsersByStatusIsNullOrNotNull() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		// Get all the applicationUserList where status is not null
		defaultApplicationUserShouldBeFound("status.specified=true");

		// Get all the applicationUserList where status is null
		defaultApplicationUserShouldNotBeFound("status.specified=false");
	}

	@Test
	@Transactional
	void getAllApplicationUsersByStatusContainsSomething() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		// Get all the applicationUserList where status contains DEFAULT_STATUS
		defaultApplicationUserShouldBeFound("status.contains=" + DEFAULT_STATUS);

		// Get all the applicationUserList where status contains UPDATED_STATUS
		defaultApplicationUserShouldNotBeFound("status.contains=" + UPDATED_STATUS);
	}

	@Test
	@Transactional
	void getAllApplicationUsersByStatusNotContainsSomething() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		// Get all the applicationUserList where status does not contain DEFAULT_STATUS
		defaultApplicationUserShouldNotBeFound("status.doesNotContain=" + DEFAULT_STATUS);

		// Get all the applicationUserList where status does not contain UPDATED_STATUS
		defaultApplicationUserShouldBeFound("status.doesNotContain=" + UPDATED_STATUS);
	}

	@Test
	@Transactional
	void getAllApplicationUsersByInternalUserIsEqualToSomething() throws Exception {
		User internalUser;
		if (TestUtil.findAll(em, User.class).isEmpty()) {
			applicationUserRepository.saveAndFlush(applicationUser);
			internalUser = UserResourceIT.createEntity(em);
		} else {
			internalUser = TestUtil.findAll(em, User.class).get(0);
		}
		em.persist(internalUser);
		em.flush();
		applicationUser.setInternalUser(internalUser);
		applicationUserRepository.saveAndFlush(applicationUser);
		Long internalUserId = internalUser.getId();

		// Get all the applicationUserList where internalUser equals to internalUserId
		defaultApplicationUserShouldBeFound("internalUserId.equals=" + internalUserId);

		// Get all the applicationUserList where internalUser equals to (internalUserId + 1)
		defaultApplicationUserShouldNotBeFound("internalUserId.equals=" + (internalUserId + 1));
	}

	@Test
	@Transactional
	void getAllApplicationUsersByUserRoleIsEqualToSomething() throws Exception {
		TUserRole userRole;
		if (TestUtil.findAll(em, TUserRole.class).isEmpty()) {
			applicationUserRepository.saveAndFlush(applicationUser);
			userRole = TUserRoleResourceIT.createEntity(em);
		} else {
			userRole = TestUtil.findAll(em, TUserRole.class).get(0);
		}
		em.persist(userRole);
		em.flush();
		applicationUser.addUserRole(userRole);
		applicationUserRepository.saveAndFlush(applicationUser);
		Long userRoleId = userRole.getId();

		// Get all the applicationUserList where userRole equals to userRoleId
		defaultApplicationUserShouldBeFound("userRoleId.equals=" + userRoleId);

		// Get all the applicationUserList where userRole equals to (userRoleId + 1)
		defaultApplicationUserShouldNotBeFound("userRoleId.equals=" + (userRoleId + 1));
	}

	@Test
	@Transactional
	void getAllApplicationUsersByDivisionIsEqualToSomething() throws Exception {
		TSectionCode division;
		if (TestUtil.findAll(em, TSectionCode.class).isEmpty()) {
			applicationUserRepository.saveAndFlush(applicationUser);
			division = TSectionCodeResourceIT.createEntity(em);
		} else {
			division = TestUtil.findAll(em, TSectionCode.class).get(0);
		}
		em.persist(division);
		em.flush();
		applicationUser.setDivision(division);
		applicationUserRepository.saveAndFlush(applicationUser);
		Long divisionId = division.getId();

		// Get all the applicationUserList where division equals to divisionId
		defaultApplicationUserShouldBeFound("divisionId.equals=" + divisionId);

		// Get all the applicationUserList where division equals to (divisionId + 1)
		defaultApplicationUserShouldNotBeFound("divisionId.equals=" + (divisionId + 1));
	}

	@Test
	@Transactional
	void getAllApplicationUsersBySectionIsEqualToSomething() throws Exception {
		TUnitCode section;
		if (TestUtil.findAll(em, TUnitCode.class).isEmpty()) {
			applicationUserRepository.saveAndFlush(applicationUser);
			section = TUnitCodeResourceIT.createEntity(em);
		} else {
			section = TestUtil.findAll(em, TUnitCode.class).get(0);
		}
		em.persist(section);
		em.flush();
		applicationUser.setSection(section);
		applicationUserRepository.saveAndFlush(applicationUser);
		Long sectionId = section.getId();

		// Get all the applicationUserList where section equals to sectionId
		defaultApplicationUserShouldBeFound("sectionId.equals=" + sectionId);

		// Get all the applicationUserList where section equals to (sectionId + 1)
		defaultApplicationUserShouldNotBeFound("sectionId.equals=" + (sectionId + 1));
	}

	@Test
	@Transactional
	void getAllApplicationUsersByUnitIsEqualToSomething() throws Exception {
		TSubunitCode unit;
		if (TestUtil.findAll(em, TSubunitCode.class).isEmpty()) {
			applicationUserRepository.saveAndFlush(applicationUser);
			unit = TSubunitCodeResourceIT.createEntity(em);
		} else {
			unit = TestUtil.findAll(em, TSubunitCode.class).get(0);
		}
		em.persist(unit);
		em.flush();
		applicationUser.setUnit(unit);
		applicationUserRepository.saveAndFlush(applicationUser);
		Long unitId = unit.getId();

		// Get all the applicationUserList where unit equals to unitId
		defaultApplicationUserShouldBeFound("unitId.equals=" + unitId);

		// Get all the applicationUserList where unit equals to (unitId + 1)
		defaultApplicationUserShouldNotBeFound("unitId.equals=" + (unitId + 1));
	}

	@Test
	@Transactional
	void getAllApplicationUsersByOfficeIsEqualToSomething() throws Exception {
		TOfficeCode office;
		if (TestUtil.findAll(em, TOfficeCode.class).isEmpty()) {
			applicationUserRepository.saveAndFlush(applicationUser);
			office = TOfficeCodeResourceIT.createEntity(em);
		} else {
			office = TestUtil.findAll(em, TOfficeCode.class).get(0);
		}
		em.persist(office);
		em.flush();
		applicationUser.setOffice(office);
		applicationUserRepository.saveAndFlush(applicationUser);
		Long officeId = office.getId();

		// Get all the applicationUserList where office equals to officeId
		defaultApplicationUserShouldBeFound("officeId.equals=" + officeId);

		// Get all the applicationUserList where office equals to (officeId + 1)
		defaultApplicationUserShouldNotBeFound("officeId.equals=" + (officeId + 1));
	}

	/**
	 * Executes the search, and checks that the default entity is returned.
	 */
	private void defaultApplicationUserShouldBeFound(String filter) throws Exception {
		restApplicationUserMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.[*].id").value(hasItem(applicationUser.getId().intValue())))
			.andExpect(jsonPath("$.[*].ic").value(hasItem(DEFAULT_IC)))
			.andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));

		// Check, that the count call also returns 1
		restApplicationUserMockMvc
			.perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().string("1"));
	}

	/**
	 * Executes the search, and checks that the default entity is not returned.
	 */
	private void defaultApplicationUserShouldNotBeFound(String filter) throws Exception {
		restApplicationUserMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$").isEmpty());

		// Check, that the count call also returns 0
		restApplicationUserMockMvc
			.perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().string("0"));
	}

	@Test
	@Transactional
	void getNonExistingApplicationUser() throws Exception {
		// Get the applicationUser
		restApplicationUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	void putExistingApplicationUser() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		int databaseSizeBeforeUpdate = applicationUserRepository.findAll().size();

		// Update the applicationUser
		ApplicationUser updatedApplicationUser = applicationUserRepository.findById(applicationUser.getId()).get();
		// Disconnect from session so that the updates on updatedApplicationUser are not directly saved in db
		em.detach(updatedApplicationUser);
		updatedApplicationUser.ic(UPDATED_IC).status(UPDATED_STATUS);

		restApplicationUserMockMvc
			.perform(
				put(ENTITY_API_URL_ID, updatedApplicationUser.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(updatedApplicationUser))
			)
			.andExpect(status().isOk());

		// Validate the ApplicationUser in the database
		List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
		assertThat(applicationUserList).hasSize(databaseSizeBeforeUpdate);
		ApplicationUser testApplicationUser = applicationUserList.get(applicationUserList.size() - 1);
		assertThat(testApplicationUser.getIc()).isEqualTo(UPDATED_IC);
		assertThat(testApplicationUser.getStatus()).isEqualTo(UPDATED_STATUS);
	}

	@Test
	@Transactional
	void putNonExistingApplicationUser() throws Exception {
		int databaseSizeBeforeUpdate = applicationUserRepository.findAll().size();
		applicationUser.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restApplicationUserMockMvc
			.perform(
				put(ENTITY_API_URL_ID, applicationUser.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(applicationUser))
			)
			.andExpect(status().isBadRequest());

		// Validate the ApplicationUser in the database
		List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
		assertThat(applicationUserList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithIdMismatchApplicationUser() throws Exception {
		int databaseSizeBeforeUpdate = applicationUserRepository.findAll().size();
		applicationUser.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restApplicationUserMockMvc
			.perform(
				put(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(applicationUser))
			)
			.andExpect(status().isBadRequest());

		// Validate the ApplicationUser in the database
		List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
		assertThat(applicationUserList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithMissingIdPathParamApplicationUser() throws Exception {
		int databaseSizeBeforeUpdate = applicationUserRepository.findAll().size();
		applicationUser.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restApplicationUserMockMvc
			.perform(
				put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(applicationUser))
			)
			.andExpect(status().isMethodNotAllowed());

		// Validate the ApplicationUser in the database
		List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
		assertThat(applicationUserList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void partialUpdateApplicationUserWithPatch() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		int databaseSizeBeforeUpdate = applicationUserRepository.findAll().size();

		// Update the applicationUser using partial update
		ApplicationUser partialUpdatedApplicationUser = new ApplicationUser();
		partialUpdatedApplicationUser.setId(applicationUser.getId());

		partialUpdatedApplicationUser.status(UPDATED_STATUS);

		restApplicationUserMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedApplicationUser.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedApplicationUser))
			)
			.andExpect(status().isOk());

		// Validate the ApplicationUser in the database
		List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
		assertThat(applicationUserList).hasSize(databaseSizeBeforeUpdate);
		ApplicationUser testApplicationUser = applicationUserList.get(applicationUserList.size() - 1);
		assertThat(testApplicationUser.getIc()).isEqualTo(DEFAULT_IC);
		assertThat(testApplicationUser.getStatus()).isEqualTo(UPDATED_STATUS);
	}

	@Test
	@Transactional
	void fullUpdateApplicationUserWithPatch() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		int databaseSizeBeforeUpdate = applicationUserRepository.findAll().size();

		// Update the applicationUser using partial update
		ApplicationUser partialUpdatedApplicationUser = new ApplicationUser();
		partialUpdatedApplicationUser.setId(applicationUser.getId());

		partialUpdatedApplicationUser.ic(UPDATED_IC).status(UPDATED_STATUS);

		restApplicationUserMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedApplicationUser.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedApplicationUser))
			)
			.andExpect(status().isOk());

		// Validate the ApplicationUser in the database
		List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
		assertThat(applicationUserList).hasSize(databaseSizeBeforeUpdate);
		ApplicationUser testApplicationUser = applicationUserList.get(applicationUserList.size() - 1);
		assertThat(testApplicationUser.getIc()).isEqualTo(UPDATED_IC);
		assertThat(testApplicationUser.getStatus()).isEqualTo(UPDATED_STATUS);
	}

	@Test
	@Transactional
	void patchNonExistingApplicationUser() throws Exception {
		int databaseSizeBeforeUpdate = applicationUserRepository.findAll().size();
		applicationUser.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restApplicationUserMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, applicationUser.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(applicationUser))
			)
			.andExpect(status().isBadRequest());

		// Validate the ApplicationUser in the database
		List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
		assertThat(applicationUserList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithIdMismatchApplicationUser() throws Exception {
		int databaseSizeBeforeUpdate = applicationUserRepository.findAll().size();
		applicationUser.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restApplicationUserMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(applicationUser))
			)
			.andExpect(status().isBadRequest());

		// Validate the ApplicationUser in the database
		List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
		assertThat(applicationUserList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithMissingIdPathParamApplicationUser() throws Exception {
		int databaseSizeBeforeUpdate = applicationUserRepository.findAll().size();
		applicationUser.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restApplicationUserMockMvc
			.perform(
				patch(ENTITY_API_URL)
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(applicationUser))
			)
			.andExpect(status().isMethodNotAllowed());

		// Validate the ApplicationUser in the database
		List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
		assertThat(applicationUserList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void deleteApplicationUser() throws Exception {
		// Initialize the database
		applicationUserRepository.saveAndFlush(applicationUser);

		int databaseSizeBeforeDelete = applicationUserRepository.findAll().size();

		// Delete the applicationUser
		restApplicationUserMockMvc
			.perform(delete(ENTITY_API_URL_ID, applicationUser.getId()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());

		// Validate the database contains one less item
		List<ApplicationUser> applicationUserList = applicationUserRepository.findAll();
		assertThat(applicationUserList).hasSize(databaseSizeBeforeDelete - 1);
	}
}
