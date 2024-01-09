package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TSectorCode;
import com.jhipster.app.repository.TSectorCodeRepository;
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
 * Integration tests for the {@link TSectorCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TSectorCodeResourceIT {

    private static final String DEFAULT_SECTOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SECTOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SECTOR_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SECTOR_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_ENTERED_BY = 1;
    private static final Integer UPDATED_ENTERED_BY = 2;

    private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_MODIFIED_BY = 1;
    private static final Integer UPDATED_MODIFIED_BY = 2;

    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/t-sector-codes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TSectorCodeRepository tSectorCodeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTSectorCodeMockMvc;

    private TSectorCode tSectorCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TSectorCode createEntity(EntityManager em) {
        TSectorCode tSectorCode = new TSectorCode()
            .sectorName(DEFAULT_SECTOR_NAME)
            .sectorDescription(DEFAULT_SECTOR_DESCRIPTION)
            .enteredBy(DEFAULT_ENTERED_BY)
            .enteredDate(DEFAULT_ENTERED_DATE)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return tSectorCode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TSectorCode createUpdatedEntity(EntityManager em) {
        TSectorCode tSectorCode = new TSectorCode()
            .sectorName(UPDATED_SECTOR_NAME)
            .sectorDescription(UPDATED_SECTOR_DESCRIPTION)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return tSectorCode;
    }

    @BeforeEach
    public void initTest() {
        tSectorCode = createEntity(em);
    }

    @Test
    @Transactional
    void createTSectorCode() throws Exception {
        int databaseSizeBeforeCreate = tSectorCodeRepository.findAll().size();
        // Create the TSectorCode
        restTSectorCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tSectorCode)))
            .andExpect(status().isCreated());

        // Validate the TSectorCode in the database
        List<TSectorCode> tSectorCodeList = tSectorCodeRepository.findAll();
        assertThat(tSectorCodeList).hasSize(databaseSizeBeforeCreate + 1);
        TSectorCode testTSectorCode = tSectorCodeList.get(tSectorCodeList.size() - 1);
        assertThat(testTSectorCode.getSectorName()).isEqualTo(DEFAULT_SECTOR_NAME);
        assertThat(testTSectorCode.getSectorDescription()).isEqualTo(DEFAULT_SECTOR_DESCRIPTION);
        assertThat(testTSectorCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
        assertThat(testTSectorCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
        assertThat(testTSectorCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testTSectorCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createTSectorCodeWithExistingId() throws Exception {
        // Create the TSectorCode with an existing ID
        tSectorCode.setId(1L);

        int databaseSizeBeforeCreate = tSectorCodeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTSectorCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tSectorCode)))
            .andExpect(status().isBadRequest());

        // Validate the TSectorCode in the database
        List<TSectorCode> tSectorCodeList = tSectorCodeRepository.findAll();
        assertThat(tSectorCodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTSectorCodes() throws Exception {
        // Initialize the database
        tSectorCodeRepository.saveAndFlush(tSectorCode);

        // Get all the tSectorCodeList
        restTSectorCodeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tSectorCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].sectorName").value(hasItem(DEFAULT_SECTOR_NAME)))
            .andExpect(jsonPath("$.[*].sectorDescription").value(hasItem(DEFAULT_SECTOR_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
            .andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
    }

    @Test
    @Transactional
    void getTSectorCode() throws Exception {
        // Initialize the database
        tSectorCodeRepository.saveAndFlush(tSectorCode);

        // Get the tSectorCode
        restTSectorCodeMockMvc
            .perform(get(ENTITY_API_URL_ID, tSectorCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tSectorCode.getId().intValue()))
            .andExpect(jsonPath("$.sectorName").value(DEFAULT_SECTOR_NAME))
            .andExpect(jsonPath("$.sectorDescription").value(DEFAULT_SECTOR_DESCRIPTION))
            .andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
            .andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
            .andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
    }

    @Test
    @Transactional
    void getNonExistingTSectorCode() throws Exception {
        // Get the tSectorCode
        restTSectorCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTSectorCode() throws Exception {
        // Initialize the database
        tSectorCodeRepository.saveAndFlush(tSectorCode);

        int databaseSizeBeforeUpdate = tSectorCodeRepository.findAll().size();

        // Update the tSectorCode
        TSectorCode updatedTSectorCode = tSectorCodeRepository.findById(tSectorCode.getId()).get();
        // Disconnect from session so that the updates on updatedTSectorCode are not directly saved in db
        em.detach(updatedTSectorCode);
        updatedTSectorCode
            .sectorName(UPDATED_SECTOR_NAME)
            .sectorDescription(UPDATED_SECTOR_DESCRIPTION)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTSectorCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTSectorCode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTSectorCode))
            )
            .andExpect(status().isOk());

        // Validate the TSectorCode in the database
        List<TSectorCode> tSectorCodeList = tSectorCodeRepository.findAll();
        assertThat(tSectorCodeList).hasSize(databaseSizeBeforeUpdate);
        TSectorCode testTSectorCode = tSectorCodeList.get(tSectorCodeList.size() - 1);
        assertThat(testTSectorCode.getSectorName()).isEqualTo(UPDATED_SECTOR_NAME);
        assertThat(testTSectorCode.getSectorDescription()).isEqualTo(UPDATED_SECTOR_DESCRIPTION);
        assertThat(testTSectorCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTSectorCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTSectorCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTSectorCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingTSectorCode() throws Exception {
        int databaseSizeBeforeUpdate = tSectorCodeRepository.findAll().size();
        tSectorCode.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTSectorCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tSectorCode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tSectorCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TSectorCode in the database
        List<TSectorCode> tSectorCodeList = tSectorCodeRepository.findAll();
        assertThat(tSectorCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTSectorCode() throws Exception {
        int databaseSizeBeforeUpdate = tSectorCodeRepository.findAll().size();
        tSectorCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTSectorCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tSectorCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TSectorCode in the database
        List<TSectorCode> tSectorCodeList = tSectorCodeRepository.findAll();
        assertThat(tSectorCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTSectorCode() throws Exception {
        int databaseSizeBeforeUpdate = tSectorCodeRepository.findAll().size();
        tSectorCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTSectorCodeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tSectorCode)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TSectorCode in the database
        List<TSectorCode> tSectorCodeList = tSectorCodeRepository.findAll();
        assertThat(tSectorCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTSectorCodeWithPatch() throws Exception {
        // Initialize the database
        tSectorCodeRepository.saveAndFlush(tSectorCode);

        int databaseSizeBeforeUpdate = tSectorCodeRepository.findAll().size();

        // Update the tSectorCode using partial update
        TSectorCode partialUpdatedTSectorCode = new TSectorCode();
        partialUpdatedTSectorCode.setId(tSectorCode.getId());

        partialUpdatedTSectorCode.sectorDescription(UPDATED_SECTOR_DESCRIPTION).enteredDate(UPDATED_ENTERED_DATE);

        restTSectorCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTSectorCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTSectorCode))
            )
            .andExpect(status().isOk());

        // Validate the TSectorCode in the database
        List<TSectorCode> tSectorCodeList = tSectorCodeRepository.findAll();
        assertThat(tSectorCodeList).hasSize(databaseSizeBeforeUpdate);
        TSectorCode testTSectorCode = tSectorCodeList.get(tSectorCodeList.size() - 1);
        assertThat(testTSectorCode.getSectorName()).isEqualTo(DEFAULT_SECTOR_NAME);
        assertThat(testTSectorCode.getSectorDescription()).isEqualTo(UPDATED_SECTOR_DESCRIPTION);
        assertThat(testTSectorCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
        assertThat(testTSectorCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTSectorCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testTSectorCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateTSectorCodeWithPatch() throws Exception {
        // Initialize the database
        tSectorCodeRepository.saveAndFlush(tSectorCode);

        int databaseSizeBeforeUpdate = tSectorCodeRepository.findAll().size();

        // Update the tSectorCode using partial update
        TSectorCode partialUpdatedTSectorCode = new TSectorCode();
        partialUpdatedTSectorCode.setId(tSectorCode.getId());

        partialUpdatedTSectorCode
            .sectorName(UPDATED_SECTOR_NAME)
            .sectorDescription(UPDATED_SECTOR_DESCRIPTION)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTSectorCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTSectorCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTSectorCode))
            )
            .andExpect(status().isOk());

        // Validate the TSectorCode in the database
        List<TSectorCode> tSectorCodeList = tSectorCodeRepository.findAll();
        assertThat(tSectorCodeList).hasSize(databaseSizeBeforeUpdate);
        TSectorCode testTSectorCode = tSectorCodeList.get(tSectorCodeList.size() - 1);
        assertThat(testTSectorCode.getSectorName()).isEqualTo(UPDATED_SECTOR_NAME);
        assertThat(testTSectorCode.getSectorDescription()).isEqualTo(UPDATED_SECTOR_DESCRIPTION);
        assertThat(testTSectorCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTSectorCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTSectorCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTSectorCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingTSectorCode() throws Exception {
        int databaseSizeBeforeUpdate = tSectorCodeRepository.findAll().size();
        tSectorCode.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTSectorCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tSectorCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tSectorCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TSectorCode in the database
        List<TSectorCode> tSectorCodeList = tSectorCodeRepository.findAll();
        assertThat(tSectorCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTSectorCode() throws Exception {
        int databaseSizeBeforeUpdate = tSectorCodeRepository.findAll().size();
        tSectorCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTSectorCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tSectorCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TSectorCode in the database
        List<TSectorCode> tSectorCodeList = tSectorCodeRepository.findAll();
        assertThat(tSectorCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTSectorCode() throws Exception {
        int databaseSizeBeforeUpdate = tSectorCodeRepository.findAll().size();
        tSectorCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTSectorCodeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tSectorCode))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TSectorCode in the database
        List<TSectorCode> tSectorCodeList = tSectorCodeRepository.findAll();
        assertThat(tSectorCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTSectorCode() throws Exception {
        // Initialize the database
        tSectorCodeRepository.saveAndFlush(tSectorCode);

        int databaseSizeBeforeDelete = tSectorCodeRepository.findAll().size();

        // Delete the tSectorCode
        restTSectorCodeMockMvc
            .perform(delete(ENTITY_API_URL_ID, tSectorCode.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TSectorCode> tSectorCodeList = tSectorCodeRepository.findAll();
        assertThat(tSectorCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
