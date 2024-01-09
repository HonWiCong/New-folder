package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TBrandCode;
import com.jhipster.app.repository.TBrandCodeRepository;
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
 * Integration tests for the {@link TBrandCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TBrandCodeResourceIT {

    private static final String DEFAULT_BRAND_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRAND_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ENTERED_BY = 1;
    private static final Integer UPDATED_ENTERED_BY = 2;

    private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_MODIFIED_BY = 1;
    private static final Integer UPDATED_MODIFIED_BY = 2;

    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/t-brand-codes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TBrandCodeRepository tBrandCodeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTBrandCodeMockMvc;

    private TBrandCode tBrandCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TBrandCode createEntity(EntityManager em) {
        TBrandCode tBrandCode = new TBrandCode()
            .brandName(DEFAULT_BRAND_NAME)
            .enteredBy(DEFAULT_ENTERED_BY)
            .enteredDate(DEFAULT_ENTERED_DATE)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return tBrandCode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TBrandCode createUpdatedEntity(EntityManager em) {
        TBrandCode tBrandCode = new TBrandCode()
            .brandName(UPDATED_BRAND_NAME)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return tBrandCode;
    }

    @BeforeEach
    public void initTest() {
        tBrandCode = createEntity(em);
    }

    @Test
    @Transactional
    void createTBrandCode() throws Exception {
        int databaseSizeBeforeCreate = tBrandCodeRepository.findAll().size();
        // Create the TBrandCode
        restTBrandCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tBrandCode)))
            .andExpect(status().isCreated());

        // Validate the TBrandCode in the database
        List<TBrandCode> tBrandCodeList = tBrandCodeRepository.findAll();
        assertThat(tBrandCodeList).hasSize(databaseSizeBeforeCreate + 1);
        TBrandCode testTBrandCode = tBrandCodeList.get(tBrandCodeList.size() - 1);
        assertThat(testTBrandCode.getBrandName()).isEqualTo(DEFAULT_BRAND_NAME);
        assertThat(testTBrandCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
        assertThat(testTBrandCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
        assertThat(testTBrandCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testTBrandCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createTBrandCodeWithExistingId() throws Exception {
        // Create the TBrandCode with an existing ID
        tBrandCode.setId(1L);

        int databaseSizeBeforeCreate = tBrandCodeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTBrandCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tBrandCode)))
            .andExpect(status().isBadRequest());

        // Validate the TBrandCode in the database
        List<TBrandCode> tBrandCodeList = tBrandCodeRepository.findAll();
        assertThat(tBrandCodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTBrandCodes() throws Exception {
        // Initialize the database
        tBrandCodeRepository.saveAndFlush(tBrandCode);

        // Get all the tBrandCodeList
        restTBrandCodeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tBrandCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].brandName").value(hasItem(DEFAULT_BRAND_NAME)))
            .andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
            .andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
    }

    @Test
    @Transactional
    void getTBrandCode() throws Exception {
        // Initialize the database
        tBrandCodeRepository.saveAndFlush(tBrandCode);

        // Get the tBrandCode
        restTBrandCodeMockMvc
            .perform(get(ENTITY_API_URL_ID, tBrandCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tBrandCode.getId().intValue()))
            .andExpect(jsonPath("$.brandName").value(DEFAULT_BRAND_NAME))
            .andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
            .andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
            .andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
    }

    @Test
    @Transactional
    void getNonExistingTBrandCode() throws Exception {
        // Get the tBrandCode
        restTBrandCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTBrandCode() throws Exception {
        // Initialize the database
        tBrandCodeRepository.saveAndFlush(tBrandCode);

        int databaseSizeBeforeUpdate = tBrandCodeRepository.findAll().size();

        // Update the tBrandCode
        TBrandCode updatedTBrandCode = tBrandCodeRepository.findById(tBrandCode.getId()).get();
        // Disconnect from session so that the updates on updatedTBrandCode are not directly saved in db
        em.detach(updatedTBrandCode);
        updatedTBrandCode
            .brandName(UPDATED_BRAND_NAME)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTBrandCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTBrandCode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTBrandCode))
            )
            .andExpect(status().isOk());

        // Validate the TBrandCode in the database
        List<TBrandCode> tBrandCodeList = tBrandCodeRepository.findAll();
        assertThat(tBrandCodeList).hasSize(databaseSizeBeforeUpdate);
        TBrandCode testTBrandCode = tBrandCodeList.get(tBrandCodeList.size() - 1);
        assertThat(testTBrandCode.getBrandName()).isEqualTo(UPDATED_BRAND_NAME);
        assertThat(testTBrandCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTBrandCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTBrandCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTBrandCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingTBrandCode() throws Exception {
        int databaseSizeBeforeUpdate = tBrandCodeRepository.findAll().size();
        tBrandCode.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTBrandCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tBrandCode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tBrandCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TBrandCode in the database
        List<TBrandCode> tBrandCodeList = tBrandCodeRepository.findAll();
        assertThat(tBrandCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTBrandCode() throws Exception {
        int databaseSizeBeforeUpdate = tBrandCodeRepository.findAll().size();
        tBrandCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTBrandCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tBrandCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TBrandCode in the database
        List<TBrandCode> tBrandCodeList = tBrandCodeRepository.findAll();
        assertThat(tBrandCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTBrandCode() throws Exception {
        int databaseSizeBeforeUpdate = tBrandCodeRepository.findAll().size();
        tBrandCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTBrandCodeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tBrandCode)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TBrandCode in the database
        List<TBrandCode> tBrandCodeList = tBrandCodeRepository.findAll();
        assertThat(tBrandCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTBrandCodeWithPatch() throws Exception {
        // Initialize the database
        tBrandCodeRepository.saveAndFlush(tBrandCode);

        int databaseSizeBeforeUpdate = tBrandCodeRepository.findAll().size();

        // Update the tBrandCode using partial update
        TBrandCode partialUpdatedTBrandCode = new TBrandCode();
        partialUpdatedTBrandCode.setId(tBrandCode.getId());

        partialUpdatedTBrandCode.modifiedBy(UPDATED_MODIFIED_BY);

        restTBrandCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTBrandCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTBrandCode))
            )
            .andExpect(status().isOk());

        // Validate the TBrandCode in the database
        List<TBrandCode> tBrandCodeList = tBrandCodeRepository.findAll();
        assertThat(tBrandCodeList).hasSize(databaseSizeBeforeUpdate);
        TBrandCode testTBrandCode = tBrandCodeList.get(tBrandCodeList.size() - 1);
        assertThat(testTBrandCode.getBrandName()).isEqualTo(DEFAULT_BRAND_NAME);
        assertThat(testTBrandCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
        assertThat(testTBrandCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
        assertThat(testTBrandCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTBrandCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateTBrandCodeWithPatch() throws Exception {
        // Initialize the database
        tBrandCodeRepository.saveAndFlush(tBrandCode);

        int databaseSizeBeforeUpdate = tBrandCodeRepository.findAll().size();

        // Update the tBrandCode using partial update
        TBrandCode partialUpdatedTBrandCode = new TBrandCode();
        partialUpdatedTBrandCode.setId(tBrandCode.getId());

        partialUpdatedTBrandCode
            .brandName(UPDATED_BRAND_NAME)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTBrandCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTBrandCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTBrandCode))
            )
            .andExpect(status().isOk());

        // Validate the TBrandCode in the database
        List<TBrandCode> tBrandCodeList = tBrandCodeRepository.findAll();
        assertThat(tBrandCodeList).hasSize(databaseSizeBeforeUpdate);
        TBrandCode testTBrandCode = tBrandCodeList.get(tBrandCodeList.size() - 1);
        assertThat(testTBrandCode.getBrandName()).isEqualTo(UPDATED_BRAND_NAME);
        assertThat(testTBrandCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTBrandCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTBrandCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTBrandCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingTBrandCode() throws Exception {
        int databaseSizeBeforeUpdate = tBrandCodeRepository.findAll().size();
        tBrandCode.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTBrandCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tBrandCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tBrandCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TBrandCode in the database
        List<TBrandCode> tBrandCodeList = tBrandCodeRepository.findAll();
        assertThat(tBrandCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTBrandCode() throws Exception {
        int databaseSizeBeforeUpdate = tBrandCodeRepository.findAll().size();
        tBrandCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTBrandCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tBrandCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TBrandCode in the database
        List<TBrandCode> tBrandCodeList = tBrandCodeRepository.findAll();
        assertThat(tBrandCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTBrandCode() throws Exception {
        int databaseSizeBeforeUpdate = tBrandCodeRepository.findAll().size();
        tBrandCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTBrandCodeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tBrandCode))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TBrandCode in the database
        List<TBrandCode> tBrandCodeList = tBrandCodeRepository.findAll();
        assertThat(tBrandCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTBrandCode() throws Exception {
        // Initialize the database
        tBrandCodeRepository.saveAndFlush(tBrandCode);

        int databaseSizeBeforeDelete = tBrandCodeRepository.findAll().size();

        // Delete the tBrandCode
        restTBrandCodeMockMvc
            .perform(delete(ENTITY_API_URL_ID, tBrandCode.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TBrandCode> tBrandCodeList = tBrandCodeRepository.findAll();
        assertThat(tBrandCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
