package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TIndustryCode;
import com.jhipster.app.repository.TIndustryCodeRepository;
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
 * Integration tests for the {@link TIndustryCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TIndustryCodeResourceIT {

    private static final String DEFAULT_INDUSTRY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INDUSTRY_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ENTERED_BY = 1;
    private static final Integer UPDATED_ENTERED_BY = 2;

    private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_MODIFIED_BY = 1;
    private static final Integer UPDATED_MODIFIED_BY = 2;

    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/t-industry-codes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TIndustryCodeRepository tIndustryCodeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTIndustryCodeMockMvc;

    private TIndustryCode tIndustryCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TIndustryCode createEntity(EntityManager em) {
        TIndustryCode tIndustryCode = new TIndustryCode()
            .industryName(DEFAULT_INDUSTRY_NAME)
            .enteredBy(DEFAULT_ENTERED_BY)
            .enteredDate(DEFAULT_ENTERED_DATE)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return tIndustryCode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TIndustryCode createUpdatedEntity(EntityManager em) {
        TIndustryCode tIndustryCode = new TIndustryCode()
            .industryName(UPDATED_INDUSTRY_NAME)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return tIndustryCode;
    }

    @BeforeEach
    public void initTest() {
        tIndustryCode = createEntity(em);
    }

    @Test
    @Transactional
    void createTIndustryCode() throws Exception {
        int databaseSizeBeforeCreate = tIndustryCodeRepository.findAll().size();
        // Create the TIndustryCode
        restTIndustryCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tIndustryCode)))
            .andExpect(status().isCreated());

        // Validate the TIndustryCode in the database
        List<TIndustryCode> tIndustryCodeList = tIndustryCodeRepository.findAll();
        assertThat(tIndustryCodeList).hasSize(databaseSizeBeforeCreate + 1);
        TIndustryCode testTIndustryCode = tIndustryCodeList.get(tIndustryCodeList.size() - 1);
        assertThat(testTIndustryCode.getIndustryName()).isEqualTo(DEFAULT_INDUSTRY_NAME);
        assertThat(testTIndustryCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
        assertThat(testTIndustryCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
        assertThat(testTIndustryCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testTIndustryCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createTIndustryCodeWithExistingId() throws Exception {
        // Create the TIndustryCode with an existing ID
        tIndustryCode.setId(1L);

        int databaseSizeBeforeCreate = tIndustryCodeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTIndustryCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tIndustryCode)))
            .andExpect(status().isBadRequest());

        // Validate the TIndustryCode in the database
        List<TIndustryCode> tIndustryCodeList = tIndustryCodeRepository.findAll();
        assertThat(tIndustryCodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTIndustryCodes() throws Exception {
        // Initialize the database
        tIndustryCodeRepository.saveAndFlush(tIndustryCode);

        // Get all the tIndustryCodeList
        restTIndustryCodeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tIndustryCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].industryName").value(hasItem(DEFAULT_INDUSTRY_NAME)))
            .andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
            .andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
    }

    @Test
    @Transactional
    void getTIndustryCode() throws Exception {
        // Initialize the database
        tIndustryCodeRepository.saveAndFlush(tIndustryCode);

        // Get the tIndustryCode
        restTIndustryCodeMockMvc
            .perform(get(ENTITY_API_URL_ID, tIndustryCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tIndustryCode.getId().intValue()))
            .andExpect(jsonPath("$.industryName").value(DEFAULT_INDUSTRY_NAME))
            .andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
            .andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
            .andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
    }

    @Test
    @Transactional
    void getNonExistingTIndustryCode() throws Exception {
        // Get the tIndustryCode
        restTIndustryCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTIndustryCode() throws Exception {
        // Initialize the database
        tIndustryCodeRepository.saveAndFlush(tIndustryCode);

        int databaseSizeBeforeUpdate = tIndustryCodeRepository.findAll().size();

        // Update the tIndustryCode
        TIndustryCode updatedTIndustryCode = tIndustryCodeRepository.findById(tIndustryCode.getId()).get();
        // Disconnect from session so that the updates on updatedTIndustryCode are not directly saved in db
        em.detach(updatedTIndustryCode);
        updatedTIndustryCode
            .industryName(UPDATED_INDUSTRY_NAME)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTIndustryCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTIndustryCode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTIndustryCode))
            )
            .andExpect(status().isOk());

        // Validate the TIndustryCode in the database
        List<TIndustryCode> tIndustryCodeList = tIndustryCodeRepository.findAll();
        assertThat(tIndustryCodeList).hasSize(databaseSizeBeforeUpdate);
        TIndustryCode testTIndustryCode = tIndustryCodeList.get(tIndustryCodeList.size() - 1);
        assertThat(testTIndustryCode.getIndustryName()).isEqualTo(UPDATED_INDUSTRY_NAME);
        assertThat(testTIndustryCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTIndustryCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTIndustryCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTIndustryCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingTIndustryCode() throws Exception {
        int databaseSizeBeforeUpdate = tIndustryCodeRepository.findAll().size();
        tIndustryCode.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTIndustryCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tIndustryCode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tIndustryCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TIndustryCode in the database
        List<TIndustryCode> tIndustryCodeList = tIndustryCodeRepository.findAll();
        assertThat(tIndustryCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTIndustryCode() throws Exception {
        int databaseSizeBeforeUpdate = tIndustryCodeRepository.findAll().size();
        tIndustryCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTIndustryCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tIndustryCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TIndustryCode in the database
        List<TIndustryCode> tIndustryCodeList = tIndustryCodeRepository.findAll();
        assertThat(tIndustryCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTIndustryCode() throws Exception {
        int databaseSizeBeforeUpdate = tIndustryCodeRepository.findAll().size();
        tIndustryCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTIndustryCodeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tIndustryCode)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TIndustryCode in the database
        List<TIndustryCode> tIndustryCodeList = tIndustryCodeRepository.findAll();
        assertThat(tIndustryCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTIndustryCodeWithPatch() throws Exception {
        // Initialize the database
        tIndustryCodeRepository.saveAndFlush(tIndustryCode);

        int databaseSizeBeforeUpdate = tIndustryCodeRepository.findAll().size();

        // Update the tIndustryCode using partial update
        TIndustryCode partialUpdatedTIndustryCode = new TIndustryCode();
        partialUpdatedTIndustryCode.setId(tIndustryCode.getId());

        partialUpdatedTIndustryCode.enteredBy(UPDATED_ENTERED_BY).modifiedDate(UPDATED_MODIFIED_DATE);

        restTIndustryCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTIndustryCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTIndustryCode))
            )
            .andExpect(status().isOk());

        // Validate the TIndustryCode in the database
        List<TIndustryCode> tIndustryCodeList = tIndustryCodeRepository.findAll();
        assertThat(tIndustryCodeList).hasSize(databaseSizeBeforeUpdate);
        TIndustryCode testTIndustryCode = tIndustryCodeList.get(tIndustryCodeList.size() - 1);
        assertThat(testTIndustryCode.getIndustryName()).isEqualTo(DEFAULT_INDUSTRY_NAME);
        assertThat(testTIndustryCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTIndustryCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
        assertThat(testTIndustryCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testTIndustryCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateTIndustryCodeWithPatch() throws Exception {
        // Initialize the database
        tIndustryCodeRepository.saveAndFlush(tIndustryCode);

        int databaseSizeBeforeUpdate = tIndustryCodeRepository.findAll().size();

        // Update the tIndustryCode using partial update
        TIndustryCode partialUpdatedTIndustryCode = new TIndustryCode();
        partialUpdatedTIndustryCode.setId(tIndustryCode.getId());

        partialUpdatedTIndustryCode
            .industryName(UPDATED_INDUSTRY_NAME)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTIndustryCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTIndustryCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTIndustryCode))
            )
            .andExpect(status().isOk());

        // Validate the TIndustryCode in the database
        List<TIndustryCode> tIndustryCodeList = tIndustryCodeRepository.findAll();
        assertThat(tIndustryCodeList).hasSize(databaseSizeBeforeUpdate);
        TIndustryCode testTIndustryCode = tIndustryCodeList.get(tIndustryCodeList.size() - 1);
        assertThat(testTIndustryCode.getIndustryName()).isEqualTo(UPDATED_INDUSTRY_NAME);
        assertThat(testTIndustryCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTIndustryCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTIndustryCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTIndustryCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingTIndustryCode() throws Exception {
        int databaseSizeBeforeUpdate = tIndustryCodeRepository.findAll().size();
        tIndustryCode.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTIndustryCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tIndustryCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tIndustryCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TIndustryCode in the database
        List<TIndustryCode> tIndustryCodeList = tIndustryCodeRepository.findAll();
        assertThat(tIndustryCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTIndustryCode() throws Exception {
        int databaseSizeBeforeUpdate = tIndustryCodeRepository.findAll().size();
        tIndustryCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTIndustryCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tIndustryCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TIndustryCode in the database
        List<TIndustryCode> tIndustryCodeList = tIndustryCodeRepository.findAll();
        assertThat(tIndustryCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTIndustryCode() throws Exception {
        int databaseSizeBeforeUpdate = tIndustryCodeRepository.findAll().size();
        tIndustryCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTIndustryCodeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tIndustryCode))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TIndustryCode in the database
        List<TIndustryCode> tIndustryCodeList = tIndustryCodeRepository.findAll();
        assertThat(tIndustryCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTIndustryCode() throws Exception {
        // Initialize the database
        tIndustryCodeRepository.saveAndFlush(tIndustryCode);

        int databaseSizeBeforeDelete = tIndustryCodeRepository.findAll().size();

        // Delete the tIndustryCode
        restTIndustryCodeMockMvc
            .perform(delete(ENTITY_API_URL_ID, tIndustryCode.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TIndustryCode> tIndustryCodeList = tIndustryCodeRepository.findAll();
        assertThat(tIndustryCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
