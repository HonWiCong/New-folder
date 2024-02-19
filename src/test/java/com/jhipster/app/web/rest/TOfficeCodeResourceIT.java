package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TOfficeCode;
import com.jhipster.app.repository.TOfficeCodeRepository;
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
 * Integration tests for the {@link TOfficeCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TOfficeCodeResourceIT {

	private static final String DEFAULT_OFF_NAME = "AAAAAAAAAA";
	private static final String UPDATED_OFF_NAME = "BBBBBBBBBB";

	private static final String DEFAULT_OFF_ADDRESS = "AAAAAAAAAA";
	private static final String UPDATED_OFF_ADDRESS = "BBBBBBBBBB";

	private static final String DEFAULT_OFF_POSTCODE = "AAAAAAAAAA";
	private static final String UPDATED_OFF_POSTCODE = "BBBBBBBBBB";

	private static final String DEFAULT_OFF_CITY = "AAAAAAAAAA";
	private static final String UPDATED_OFF_CITY = "BBBBBBBBBB";

	private static final String DEFAULT_OFF_STATE = "AAAAAAAAAA";
	private static final String UPDATED_OFF_STATE = "BBBBBBBBBB";

	private static final String DEFAULT_OFF_OFFPHONE = "AAAAAAAAAA";
	private static final String UPDATED_OFF_OFFPHONE = "BBBBBBBBBB";

	private static final String DEFAULT_OFF_OFFFAX = "AAAAAAAAAA";
	private static final String UPDATED_OFF_OFFFAX = "BBBBBBBBBB";

	private static final Integer DEFAULT_ENTERED_BY = 1;
	private static final Integer UPDATED_ENTERED_BY = 2;

	private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
	private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

	private static final Integer DEFAULT_MODIFIED_BY = 1;
	private static final Integer UPDATED_MODIFIED_BY = 2;

	private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
	private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

	private static final String ENTITY_API_URL = "/api/t-office-codes";
	private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

	private static Random random = new Random();
	private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

	@Autowired
	private TOfficeCodeRepository tOfficeCodeRepository;

	@Autowired
	private EntityManager em;

	@Autowired
	private MockMvc restTOfficeCodeMockMvc;

	private TOfficeCode tOfficeCode;

	/**
	 * Create an entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TOfficeCode createEntity(EntityManager em) {
		TOfficeCode tOfficeCode = new TOfficeCode()
			.offName(DEFAULT_OFF_NAME)
			.offAddress(DEFAULT_OFF_ADDRESS)
			.offPostcode(DEFAULT_OFF_POSTCODE)
			.offCity(DEFAULT_OFF_CITY)
			.offState(DEFAULT_OFF_STATE)
			.offOffphone(DEFAULT_OFF_OFFPHONE)
			.offOfffax(DEFAULT_OFF_OFFFAX)
			.enteredBy(DEFAULT_ENTERED_BY)
			.enteredDate(DEFAULT_ENTERED_DATE)
			.modifiedBy(DEFAULT_MODIFIED_BY)
			.modifiedDate(DEFAULT_MODIFIED_DATE);
		return tOfficeCode;
	}

	/**
	 * Create an updated entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static TOfficeCode createUpdatedEntity(EntityManager em) {
		TOfficeCode tOfficeCode = new TOfficeCode()
			.offName(UPDATED_OFF_NAME)
			.offAddress(UPDATED_OFF_ADDRESS)
			.offPostcode(UPDATED_OFF_POSTCODE)
			.offCity(UPDATED_OFF_CITY)
			.offState(UPDATED_OFF_STATE)
			.offOffphone(UPDATED_OFF_OFFPHONE)
			.offOfffax(UPDATED_OFF_OFFFAX)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);
		return tOfficeCode;
	}

	@BeforeEach
	public void initTest() {
		tOfficeCode = createEntity(em);
	}

	@Test
	@Transactional
	void createTOfficeCode() throws Exception {
		int databaseSizeBeforeCreate = tOfficeCodeRepository.findAll().size();
		// Create the TOfficeCode
		restTOfficeCodeMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tOfficeCode)))
			.andExpect(status().isCreated());

		// Validate the TOfficeCode in the database
		List<TOfficeCode> tOfficeCodeList = tOfficeCodeRepository.findAll();
		assertThat(tOfficeCodeList).hasSize(databaseSizeBeforeCreate + 1);
		TOfficeCode testTOfficeCode = tOfficeCodeList.get(tOfficeCodeList.size() - 1);
		assertThat(testTOfficeCode.getOffName()).isEqualTo(DEFAULT_OFF_NAME);
		assertThat(testTOfficeCode.getOffAddress()).isEqualTo(DEFAULT_OFF_ADDRESS);
		assertThat(testTOfficeCode.getOffPostcode()).isEqualTo(DEFAULT_OFF_POSTCODE);
		assertThat(testTOfficeCode.getOffCity()).isEqualTo(DEFAULT_OFF_CITY);
		assertThat(testTOfficeCode.getOffState()).isEqualTo(DEFAULT_OFF_STATE);
		assertThat(testTOfficeCode.getOffOffphone()).isEqualTo(DEFAULT_OFF_OFFPHONE);
		assertThat(testTOfficeCode.getOffOfffax()).isEqualTo(DEFAULT_OFF_OFFFAX);
		assertThat(testTOfficeCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
		assertThat(testTOfficeCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
		assertThat(testTOfficeCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
		assertThat(testTOfficeCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void createTOfficeCodeWithExistingId() throws Exception {
		// Create the TOfficeCode with an existing ID
		tOfficeCode.setId(1L);

		int databaseSizeBeforeCreate = tOfficeCodeRepository.findAll().size();

		// An entity with an existing ID cannot be created, so this API call must fail
		restTOfficeCodeMockMvc
			.perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tOfficeCode)))
			.andExpect(status().isBadRequest());

		// Validate the TOfficeCode in the database
		List<TOfficeCode> tOfficeCodeList = tOfficeCodeRepository.findAll();
		assertThat(tOfficeCodeList).hasSize(databaseSizeBeforeCreate);
	}

	@Test
	@Transactional
	void getAllTOfficeCodes() throws Exception {
		// Initialize the database
		tOfficeCodeRepository.saveAndFlush(tOfficeCode);

		// Get all the tOfficeCodeList
		restTOfficeCodeMockMvc
			.perform(get(ENTITY_API_URL + "?sort=id,desc"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.[*].id").value(hasItem(tOfficeCode.getId().intValue())))
			.andExpect(jsonPath("$.[*].offName").value(hasItem(DEFAULT_OFF_NAME)))
			.andExpect(jsonPath("$.[*].offAddress").value(hasItem(DEFAULT_OFF_ADDRESS)))
			.andExpect(jsonPath("$.[*].offPostcode").value(hasItem(DEFAULT_OFF_POSTCODE)))
			.andExpect(jsonPath("$.[*].offCity").value(hasItem(DEFAULT_OFF_CITY)))
			.andExpect(jsonPath("$.[*].offState").value(hasItem(DEFAULT_OFF_STATE)))
			.andExpect(jsonPath("$.[*].offOffphone").value(hasItem(DEFAULT_OFF_OFFPHONE)))
			.andExpect(jsonPath("$.[*].offOfffax").value(hasItem(DEFAULT_OFF_OFFFAX)))
			.andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
			.andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
			.andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
			.andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
	}

	@Test
	@Transactional
	void getTOfficeCode() throws Exception {
		// Initialize the database
		tOfficeCodeRepository.saveAndFlush(tOfficeCode);

		// Get the tOfficeCode
		restTOfficeCodeMockMvc
			.perform(get(ENTITY_API_URL_ID, tOfficeCode.getId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.id").value(tOfficeCode.getId().intValue()))
			.andExpect(jsonPath("$.offName").value(DEFAULT_OFF_NAME))
			.andExpect(jsonPath("$.offAddress").value(DEFAULT_OFF_ADDRESS))
			.andExpect(jsonPath("$.offPostcode").value(DEFAULT_OFF_POSTCODE))
			.andExpect(jsonPath("$.offCity").value(DEFAULT_OFF_CITY))
			.andExpect(jsonPath("$.offState").value(DEFAULT_OFF_STATE))
			.andExpect(jsonPath("$.offOffphone").value(DEFAULT_OFF_OFFPHONE))
			.andExpect(jsonPath("$.offOfffax").value(DEFAULT_OFF_OFFFAX))
			.andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
			.andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
			.andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
			.andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
	}

	@Test
	@Transactional
	void getNonExistingTOfficeCode() throws Exception {
		// Get the tOfficeCode
		restTOfficeCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	void putExistingTOfficeCode() throws Exception {
		// Initialize the database
		tOfficeCodeRepository.saveAndFlush(tOfficeCode);

		int databaseSizeBeforeUpdate = tOfficeCodeRepository.findAll().size();

		// Update the tOfficeCode
		TOfficeCode updatedTOfficeCode = tOfficeCodeRepository.findById(tOfficeCode.getId()).get();
		// Disconnect from session so that the updates on updatedTOfficeCode are not directly saved in db
		em.detach(updatedTOfficeCode);
		updatedTOfficeCode
			.offName(UPDATED_OFF_NAME)
			.offAddress(UPDATED_OFF_ADDRESS)
			.offPostcode(UPDATED_OFF_POSTCODE)
			.offCity(UPDATED_OFF_CITY)
			.offState(UPDATED_OFF_STATE)
			.offOffphone(UPDATED_OFF_OFFPHONE)
			.offOfffax(UPDATED_OFF_OFFFAX)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTOfficeCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, updatedTOfficeCode.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(updatedTOfficeCode))
			)
			.andExpect(status().isOk());

		// Validate the TOfficeCode in the database
		List<TOfficeCode> tOfficeCodeList = tOfficeCodeRepository.findAll();
		assertThat(tOfficeCodeList).hasSize(databaseSizeBeforeUpdate);
		TOfficeCode testTOfficeCode = tOfficeCodeList.get(tOfficeCodeList.size() - 1);
		assertThat(testTOfficeCode.getOffName()).isEqualTo(UPDATED_OFF_NAME);
		assertThat(testTOfficeCode.getOffAddress()).isEqualTo(UPDATED_OFF_ADDRESS);
		assertThat(testTOfficeCode.getOffPostcode()).isEqualTo(UPDATED_OFF_POSTCODE);
		assertThat(testTOfficeCode.getOffCity()).isEqualTo(UPDATED_OFF_CITY);
		assertThat(testTOfficeCode.getOffState()).isEqualTo(UPDATED_OFF_STATE);
		assertThat(testTOfficeCode.getOffOffphone()).isEqualTo(UPDATED_OFF_OFFPHONE);
		assertThat(testTOfficeCode.getOffOfffax()).isEqualTo(UPDATED_OFF_OFFFAX);
		assertThat(testTOfficeCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTOfficeCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTOfficeCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTOfficeCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void putNonExistingTOfficeCode() throws Exception {
		int databaseSizeBeforeUpdate = tOfficeCodeRepository.findAll().size();
		tOfficeCode.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTOfficeCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, tOfficeCode.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tOfficeCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TOfficeCode in the database
		List<TOfficeCode> tOfficeCodeList = tOfficeCodeRepository.findAll();
		assertThat(tOfficeCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithIdMismatchTOfficeCode() throws Exception {
		int databaseSizeBeforeUpdate = tOfficeCodeRepository.findAll().size();
		tOfficeCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTOfficeCodeMockMvc
			.perform(
				put(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(tOfficeCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TOfficeCode in the database
		List<TOfficeCode> tOfficeCodeList = tOfficeCodeRepository.findAll();
		assertThat(tOfficeCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void putWithMissingIdPathParamTOfficeCode() throws Exception {
		int databaseSizeBeforeUpdate = tOfficeCodeRepository.findAll().size();
		tOfficeCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTOfficeCodeMockMvc
			.perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tOfficeCode)))
			.andExpect(status().isMethodNotAllowed());

		// Validate the TOfficeCode in the database
		List<TOfficeCode> tOfficeCodeList = tOfficeCodeRepository.findAll();
		assertThat(tOfficeCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void partialUpdateTOfficeCodeWithPatch() throws Exception {
		// Initialize the database
		tOfficeCodeRepository.saveAndFlush(tOfficeCode);

		int databaseSizeBeforeUpdate = tOfficeCodeRepository.findAll().size();

		// Update the tOfficeCode using partial update
		TOfficeCode partialUpdatedTOfficeCode = new TOfficeCode();
		partialUpdatedTOfficeCode.setId(tOfficeCode.getId());

		partialUpdatedTOfficeCode
			.offAddress(UPDATED_OFF_ADDRESS)
			.offPostcode(UPDATED_OFF_POSTCODE)
			.offCity(UPDATED_OFF_CITY)
			.offState(UPDATED_OFF_STATE)
			.offOffphone(UPDATED_OFF_OFFPHONE)
			.enteredBy(UPDATED_ENTERED_BY)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTOfficeCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTOfficeCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTOfficeCode))
			)
			.andExpect(status().isOk());

		// Validate the TOfficeCode in the database
		List<TOfficeCode> tOfficeCodeList = tOfficeCodeRepository.findAll();
		assertThat(tOfficeCodeList).hasSize(databaseSizeBeforeUpdate);
		TOfficeCode testTOfficeCode = tOfficeCodeList.get(tOfficeCodeList.size() - 1);
		assertThat(testTOfficeCode.getOffName()).isEqualTo(DEFAULT_OFF_NAME);
		assertThat(testTOfficeCode.getOffAddress()).isEqualTo(UPDATED_OFF_ADDRESS);
		assertThat(testTOfficeCode.getOffPostcode()).isEqualTo(UPDATED_OFF_POSTCODE);
		assertThat(testTOfficeCode.getOffCity()).isEqualTo(UPDATED_OFF_CITY);
		assertThat(testTOfficeCode.getOffState()).isEqualTo(UPDATED_OFF_STATE);
		assertThat(testTOfficeCode.getOffOffphone()).isEqualTo(UPDATED_OFF_OFFPHONE);
		assertThat(testTOfficeCode.getOffOfffax()).isEqualTo(DEFAULT_OFF_OFFFAX);
		assertThat(testTOfficeCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTOfficeCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
		assertThat(testTOfficeCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTOfficeCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void fullUpdateTOfficeCodeWithPatch() throws Exception {
		// Initialize the database
		tOfficeCodeRepository.saveAndFlush(tOfficeCode);

		int databaseSizeBeforeUpdate = tOfficeCodeRepository.findAll().size();

		// Update the tOfficeCode using partial update
		TOfficeCode partialUpdatedTOfficeCode = new TOfficeCode();
		partialUpdatedTOfficeCode.setId(tOfficeCode.getId());

		partialUpdatedTOfficeCode
			.offName(UPDATED_OFF_NAME)
			.offAddress(UPDATED_OFF_ADDRESS)
			.offPostcode(UPDATED_OFF_POSTCODE)
			.offCity(UPDATED_OFF_CITY)
			.offState(UPDATED_OFF_STATE)
			.offOffphone(UPDATED_OFF_OFFPHONE)
			.offOfffax(UPDATED_OFF_OFFFAX)
			.enteredBy(UPDATED_ENTERED_BY)
			.enteredDate(UPDATED_ENTERED_DATE)
			.modifiedBy(UPDATED_MODIFIED_BY)
			.modifiedDate(UPDATED_MODIFIED_DATE);

		restTOfficeCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, partialUpdatedTOfficeCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(partialUpdatedTOfficeCode))
			)
			.andExpect(status().isOk());

		// Validate the TOfficeCode in the database
		List<TOfficeCode> tOfficeCodeList = tOfficeCodeRepository.findAll();
		assertThat(tOfficeCodeList).hasSize(databaseSizeBeforeUpdate);
		TOfficeCode testTOfficeCode = tOfficeCodeList.get(tOfficeCodeList.size() - 1);
		assertThat(testTOfficeCode.getOffName()).isEqualTo(UPDATED_OFF_NAME);
		assertThat(testTOfficeCode.getOffAddress()).isEqualTo(UPDATED_OFF_ADDRESS);
		assertThat(testTOfficeCode.getOffPostcode()).isEqualTo(UPDATED_OFF_POSTCODE);
		assertThat(testTOfficeCode.getOffCity()).isEqualTo(UPDATED_OFF_CITY);
		assertThat(testTOfficeCode.getOffState()).isEqualTo(UPDATED_OFF_STATE);
		assertThat(testTOfficeCode.getOffOffphone()).isEqualTo(UPDATED_OFF_OFFPHONE);
		assertThat(testTOfficeCode.getOffOfffax()).isEqualTo(UPDATED_OFF_OFFFAX);
		assertThat(testTOfficeCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
		assertThat(testTOfficeCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
		assertThat(testTOfficeCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
		assertThat(testTOfficeCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
	}

	@Test
	@Transactional
	void patchNonExistingTOfficeCode() throws Exception {
		int databaseSizeBeforeUpdate = tOfficeCodeRepository.findAll().size();
		tOfficeCode.setId(count.incrementAndGet());

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restTOfficeCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, tOfficeCode.getId())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tOfficeCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TOfficeCode in the database
		List<TOfficeCode> tOfficeCodeList = tOfficeCodeRepository.findAll();
		assertThat(tOfficeCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithIdMismatchTOfficeCode() throws Exception {
		int databaseSizeBeforeUpdate = tOfficeCodeRepository.findAll().size();
		tOfficeCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTOfficeCodeMockMvc
			.perform(
				patch(ENTITY_API_URL_ID, count.incrementAndGet())
					.contentType("application/merge-patch+json")
					.content(TestUtil.convertObjectToJsonBytes(tOfficeCode))
			)
			.andExpect(status().isBadRequest());

		// Validate the TOfficeCode in the database
		List<TOfficeCode> tOfficeCodeList = tOfficeCodeRepository.findAll();
		assertThat(tOfficeCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void patchWithMissingIdPathParamTOfficeCode() throws Exception {
		int databaseSizeBeforeUpdate = tOfficeCodeRepository.findAll().size();
		tOfficeCode.setId(count.incrementAndGet());

		// If url ID doesn't match entity ID, it will throw BadRequestAlertException
		restTOfficeCodeMockMvc
			.perform(
				patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tOfficeCode))
			)
			.andExpect(status().isMethodNotAllowed());

		// Validate the TOfficeCode in the database
		List<TOfficeCode> tOfficeCodeList = tOfficeCodeRepository.findAll();
		assertThat(tOfficeCodeList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	void deleteTOfficeCode() throws Exception {
		// Initialize the database
		tOfficeCodeRepository.saveAndFlush(tOfficeCode);

		int databaseSizeBeforeDelete = tOfficeCodeRepository.findAll().size();

		// Delete the tOfficeCode
		restTOfficeCodeMockMvc
			.perform(delete(ENTITY_API_URL_ID, tOfficeCode.getId()).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());

		// Validate the database contains one less item
		List<TOfficeCode> tOfficeCodeList = tOfficeCodeRepository.findAll();
		assertThat(tOfficeCodeList).hasSize(databaseSizeBeforeDelete - 1);
	}
}
