package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TStateCode;
import com.jhipster.app.repository.TStateCodeRepository;
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

    private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_MODIFIED_BY = 1;
    private static final Integer UPDATED_MODIFIED_BY = 2;

    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

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
