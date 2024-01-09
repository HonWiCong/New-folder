package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TDistrictCode;
import com.jhipster.app.repository.TDistrictCodeRepository;
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
 * Integration tests for the {@link TDistrictCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TDistrictCodeResourceIT {

    private static final String DEFAULT_DIS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DIS_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ENTERED_BY = 1;
    private static final Integer UPDATED_ENTERED_BY = 2;

    private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_MODIFIED_BY = 1;
    private static final Integer UPDATED_MODIFIED_BY = 2;

    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/t-district-codes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TDistrictCodeRepository tDistrictCodeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTDistrictCodeMockMvc;

    private TDistrictCode tDistrictCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TDistrictCode createEntity(EntityManager em) {
        TDistrictCode tDistrictCode = new TDistrictCode()
            .disName(DEFAULT_DIS_NAME)
            .enteredBy(DEFAULT_ENTERED_BY)
            .enteredDate(DEFAULT_ENTERED_DATE)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return tDistrictCode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TDistrictCode createUpdatedEntity(EntityManager em) {
        TDistrictCode tDistrictCode = new TDistrictCode()
            .disName(UPDATED_DIS_NAME)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return tDistrictCode;
    }

    @BeforeEach
    public void initTest() {
        tDistrictCode = createEntity(em);
    }

    @Test
    @Transactional
    void createTDistrictCode() throws Exception {
        int databaseSizeBeforeCreate = tDistrictCodeRepository.findAll().size();
        // Create the TDistrictCode
        restTDistrictCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tDistrictCode)))
            .andExpect(status().isCreated());

        // Validate the TDistrictCode in the database
        List<TDistrictCode> tDistrictCodeList = tDistrictCodeRepository.findAll();
        assertThat(tDistrictCodeList).hasSize(databaseSizeBeforeCreate + 1);
        TDistrictCode testTDistrictCode = tDistrictCodeList.get(tDistrictCodeList.size() - 1);
        assertThat(testTDistrictCode.getDisName()).isEqualTo(DEFAULT_DIS_NAME);
        assertThat(testTDistrictCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
        assertThat(testTDistrictCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
        assertThat(testTDistrictCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testTDistrictCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createTDistrictCodeWithExistingId() throws Exception {
        // Create the TDistrictCode with an existing ID
        tDistrictCode.setId(1L);

        int databaseSizeBeforeCreate = tDistrictCodeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTDistrictCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tDistrictCode)))
            .andExpect(status().isBadRequest());

        // Validate the TDistrictCode in the database
        List<TDistrictCode> tDistrictCodeList = tDistrictCodeRepository.findAll();
        assertThat(tDistrictCodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTDistrictCodes() throws Exception {
        // Initialize the database
        tDistrictCodeRepository.saveAndFlush(tDistrictCode);

        // Get all the tDistrictCodeList
        restTDistrictCodeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tDistrictCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].disName").value(hasItem(DEFAULT_DIS_NAME)))
            .andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
            .andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
    }

    @Test
    @Transactional
    void getTDistrictCode() throws Exception {
        // Initialize the database
        tDistrictCodeRepository.saveAndFlush(tDistrictCode);

        // Get the tDistrictCode
        restTDistrictCodeMockMvc
            .perform(get(ENTITY_API_URL_ID, tDistrictCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tDistrictCode.getId().intValue()))
            .andExpect(jsonPath("$.disName").value(DEFAULT_DIS_NAME))
            .andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
            .andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
            .andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
    }

    @Test
    @Transactional
    void getNonExistingTDistrictCode() throws Exception {
        // Get the tDistrictCode
        restTDistrictCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTDistrictCode() throws Exception {
        // Initialize the database
        tDistrictCodeRepository.saveAndFlush(tDistrictCode);

        int databaseSizeBeforeUpdate = tDistrictCodeRepository.findAll().size();

        // Update the tDistrictCode
        TDistrictCode updatedTDistrictCode = tDistrictCodeRepository.findById(tDistrictCode.getId()).get();
        // Disconnect from session so that the updates on updatedTDistrictCode are not directly saved in db
        em.detach(updatedTDistrictCode);
        updatedTDistrictCode
            .disName(UPDATED_DIS_NAME)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTDistrictCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTDistrictCode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTDistrictCode))
            )
            .andExpect(status().isOk());

        // Validate the TDistrictCode in the database
        List<TDistrictCode> tDistrictCodeList = tDistrictCodeRepository.findAll();
        assertThat(tDistrictCodeList).hasSize(databaseSizeBeforeUpdate);
        TDistrictCode testTDistrictCode = tDistrictCodeList.get(tDistrictCodeList.size() - 1);
        assertThat(testTDistrictCode.getDisName()).isEqualTo(UPDATED_DIS_NAME);
        assertThat(testTDistrictCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTDistrictCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTDistrictCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTDistrictCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingTDistrictCode() throws Exception {
        int databaseSizeBeforeUpdate = tDistrictCodeRepository.findAll().size();
        tDistrictCode.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTDistrictCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tDistrictCode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tDistrictCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TDistrictCode in the database
        List<TDistrictCode> tDistrictCodeList = tDistrictCodeRepository.findAll();
        assertThat(tDistrictCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTDistrictCode() throws Exception {
        int databaseSizeBeforeUpdate = tDistrictCodeRepository.findAll().size();
        tDistrictCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTDistrictCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tDistrictCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TDistrictCode in the database
        List<TDistrictCode> tDistrictCodeList = tDistrictCodeRepository.findAll();
        assertThat(tDistrictCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTDistrictCode() throws Exception {
        int databaseSizeBeforeUpdate = tDistrictCodeRepository.findAll().size();
        tDistrictCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTDistrictCodeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tDistrictCode)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TDistrictCode in the database
        List<TDistrictCode> tDistrictCodeList = tDistrictCodeRepository.findAll();
        assertThat(tDistrictCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTDistrictCodeWithPatch() throws Exception {
        // Initialize the database
        tDistrictCodeRepository.saveAndFlush(tDistrictCode);

        int databaseSizeBeforeUpdate = tDistrictCodeRepository.findAll().size();

        // Update the tDistrictCode using partial update
        TDistrictCode partialUpdatedTDistrictCode = new TDistrictCode();
        partialUpdatedTDistrictCode.setId(tDistrictCode.getId());

        partialUpdatedTDistrictCode.enteredDate(UPDATED_ENTERED_DATE).modifiedDate(UPDATED_MODIFIED_DATE);

        restTDistrictCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTDistrictCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTDistrictCode))
            )
            .andExpect(status().isOk());

        // Validate the TDistrictCode in the database
        List<TDistrictCode> tDistrictCodeList = tDistrictCodeRepository.findAll();
        assertThat(tDistrictCodeList).hasSize(databaseSizeBeforeUpdate);
        TDistrictCode testTDistrictCode = tDistrictCodeList.get(tDistrictCodeList.size() - 1);
        assertThat(testTDistrictCode.getDisName()).isEqualTo(DEFAULT_DIS_NAME);
        assertThat(testTDistrictCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
        assertThat(testTDistrictCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTDistrictCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testTDistrictCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateTDistrictCodeWithPatch() throws Exception {
        // Initialize the database
        tDistrictCodeRepository.saveAndFlush(tDistrictCode);

        int databaseSizeBeforeUpdate = tDistrictCodeRepository.findAll().size();

        // Update the tDistrictCode using partial update
        TDistrictCode partialUpdatedTDistrictCode = new TDistrictCode();
        partialUpdatedTDistrictCode.setId(tDistrictCode.getId());

        partialUpdatedTDistrictCode
            .disName(UPDATED_DIS_NAME)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTDistrictCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTDistrictCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTDistrictCode))
            )
            .andExpect(status().isOk());

        // Validate the TDistrictCode in the database
        List<TDistrictCode> tDistrictCodeList = tDistrictCodeRepository.findAll();
        assertThat(tDistrictCodeList).hasSize(databaseSizeBeforeUpdate);
        TDistrictCode testTDistrictCode = tDistrictCodeList.get(tDistrictCodeList.size() - 1);
        assertThat(testTDistrictCode.getDisName()).isEqualTo(UPDATED_DIS_NAME);
        assertThat(testTDistrictCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTDistrictCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTDistrictCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTDistrictCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingTDistrictCode() throws Exception {
        int databaseSizeBeforeUpdate = tDistrictCodeRepository.findAll().size();
        tDistrictCode.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTDistrictCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tDistrictCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tDistrictCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TDistrictCode in the database
        List<TDistrictCode> tDistrictCodeList = tDistrictCodeRepository.findAll();
        assertThat(tDistrictCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTDistrictCode() throws Exception {
        int databaseSizeBeforeUpdate = tDistrictCodeRepository.findAll().size();
        tDistrictCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTDistrictCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tDistrictCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TDistrictCode in the database
        List<TDistrictCode> tDistrictCodeList = tDistrictCodeRepository.findAll();
        assertThat(tDistrictCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTDistrictCode() throws Exception {
        int databaseSizeBeforeUpdate = tDistrictCodeRepository.findAll().size();
        tDistrictCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTDistrictCodeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tDistrictCode))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TDistrictCode in the database
        List<TDistrictCode> tDistrictCodeList = tDistrictCodeRepository.findAll();
        assertThat(tDistrictCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTDistrictCode() throws Exception {
        // Initialize the database
        tDistrictCodeRepository.saveAndFlush(tDistrictCode);

        int databaseSizeBeforeDelete = tDistrictCodeRepository.findAll().size();

        // Delete the tDistrictCode
        restTDistrictCodeMockMvc
            .perform(delete(ENTITY_API_URL_ID, tDistrictCode.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TDistrictCode> tDistrictCodeList = tDistrictCodeRepository.findAll();
        assertThat(tDistrictCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
