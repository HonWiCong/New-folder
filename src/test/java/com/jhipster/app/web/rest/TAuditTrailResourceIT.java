package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TAuditTrail;
import com.jhipster.app.repository.TAuditTrailRepository;
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
 * Integration tests for the {@link TAuditTrailResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TAuditTrailResourceIT {

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final ZonedDateTime DEFAULT_DATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TABLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TABLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_AUDIT_ACTION = "AAAAAAAAAA";
    private static final String UPDATED_AUDIT_ACTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_RECORD_ID = 1;
    private static final Integer UPDATED_RECORD_ID = 2;

    private static final String DEFAULT_FIELD_DESC = "AAAAAAAAAA";
    private static final String UPDATED_FIELD_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_AT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_AT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ST_FULL_DESC = "AAAAAAAAAA";
    private static final String UPDATED_ST_FULL_DESC = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/t-audit-trails";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TAuditTrailRepository tAuditTrailRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTAuditTrailMockMvc;

    private TAuditTrail tAuditTrail;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TAuditTrail createEntity(EntityManager em) {
        TAuditTrail tAuditTrail = new TAuditTrail()
            .userId(DEFAULT_USER_ID)
            .dateTime(DEFAULT_DATE_TIME)
            .tableName(DEFAULT_TABLE_NAME)
            .auditAction(DEFAULT_AUDIT_ACTION)
            .recordId(DEFAULT_RECORD_ID)
            .fieldDesc(DEFAULT_FIELD_DESC)
            .atStatus(DEFAULT_AT_STATUS)
            .stFullDesc(DEFAULT_ST_FULL_DESC);
        return tAuditTrail;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TAuditTrail createUpdatedEntity(EntityManager em) {
        TAuditTrail tAuditTrail = new TAuditTrail()
            .userId(UPDATED_USER_ID)
            .dateTime(UPDATED_DATE_TIME)
            .tableName(UPDATED_TABLE_NAME)
            .auditAction(UPDATED_AUDIT_ACTION)
            .recordId(UPDATED_RECORD_ID)
            .fieldDesc(UPDATED_FIELD_DESC)
            .atStatus(UPDATED_AT_STATUS)
            .stFullDesc(UPDATED_ST_FULL_DESC);
        return tAuditTrail;
    }

    @BeforeEach
    public void initTest() {
        tAuditTrail = createEntity(em);
    }

    @Test
    @Transactional
    void createTAuditTrail() throws Exception {
        int databaseSizeBeforeCreate = tAuditTrailRepository.findAll().size();
        // Create the TAuditTrail
        restTAuditTrailMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tAuditTrail)))
            .andExpect(status().isCreated());

        // Validate the TAuditTrail in the database
        List<TAuditTrail> tAuditTrailList = tAuditTrailRepository.findAll();
        assertThat(tAuditTrailList).hasSize(databaseSizeBeforeCreate + 1);
        TAuditTrail testTAuditTrail = tAuditTrailList.get(tAuditTrailList.size() - 1);
        assertThat(testTAuditTrail.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testTAuditTrail.getDateTime()).isEqualTo(DEFAULT_DATE_TIME);
        assertThat(testTAuditTrail.getTableName()).isEqualTo(DEFAULT_TABLE_NAME);
        assertThat(testTAuditTrail.getAuditAction()).isEqualTo(DEFAULT_AUDIT_ACTION);
        assertThat(testTAuditTrail.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testTAuditTrail.getFieldDesc()).isEqualTo(DEFAULT_FIELD_DESC);
        assertThat(testTAuditTrail.getAtStatus()).isEqualTo(DEFAULT_AT_STATUS);
        assertThat(testTAuditTrail.getStFullDesc()).isEqualTo(DEFAULT_ST_FULL_DESC);
    }

    @Test
    @Transactional
    void createTAuditTrailWithExistingId() throws Exception {
        // Create the TAuditTrail with an existing ID
        tAuditTrail.setId(1L);

        int databaseSizeBeforeCreate = tAuditTrailRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTAuditTrailMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tAuditTrail)))
            .andExpect(status().isBadRequest());

        // Validate the TAuditTrail in the database
        List<TAuditTrail> tAuditTrailList = tAuditTrailRepository.findAll();
        assertThat(tAuditTrailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTAuditTrails() throws Exception {
        // Initialize the database
        tAuditTrailRepository.saveAndFlush(tAuditTrail);

        // Get all the tAuditTrailList
        restTAuditTrailMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tAuditTrail.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].dateTime").value(hasItem(sameInstant(DEFAULT_DATE_TIME))))
            .andExpect(jsonPath("$.[*].tableName").value(hasItem(DEFAULT_TABLE_NAME)))
            .andExpect(jsonPath("$.[*].auditAction").value(hasItem(DEFAULT_AUDIT_ACTION)))
            .andExpect(jsonPath("$.[*].recordId").value(hasItem(DEFAULT_RECORD_ID)))
            .andExpect(jsonPath("$.[*].fieldDesc").value(hasItem(DEFAULT_FIELD_DESC)))
            .andExpect(jsonPath("$.[*].atStatus").value(hasItem(DEFAULT_AT_STATUS)))
            .andExpect(jsonPath("$.[*].stFullDesc").value(hasItem(DEFAULT_ST_FULL_DESC)));
    }

    @Test
    @Transactional
    void getTAuditTrail() throws Exception {
        // Initialize the database
        tAuditTrailRepository.saveAndFlush(tAuditTrail);

        // Get the tAuditTrail
        restTAuditTrailMockMvc
            .perform(get(ENTITY_API_URL_ID, tAuditTrail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tAuditTrail.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.dateTime").value(sameInstant(DEFAULT_DATE_TIME)))
            .andExpect(jsonPath("$.tableName").value(DEFAULT_TABLE_NAME))
            .andExpect(jsonPath("$.auditAction").value(DEFAULT_AUDIT_ACTION))
            .andExpect(jsonPath("$.recordId").value(DEFAULT_RECORD_ID))
            .andExpect(jsonPath("$.fieldDesc").value(DEFAULT_FIELD_DESC))
            .andExpect(jsonPath("$.atStatus").value(DEFAULT_AT_STATUS))
            .andExpect(jsonPath("$.stFullDesc").value(DEFAULT_ST_FULL_DESC));
    }

    @Test
    @Transactional
    void getNonExistingTAuditTrail() throws Exception {
        // Get the tAuditTrail
        restTAuditTrailMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTAuditTrail() throws Exception {
        // Initialize the database
        tAuditTrailRepository.saveAndFlush(tAuditTrail);

        int databaseSizeBeforeUpdate = tAuditTrailRepository.findAll().size();

        // Update the tAuditTrail
        TAuditTrail updatedTAuditTrail = tAuditTrailRepository.findById(tAuditTrail.getId()).get();
        // Disconnect from session so that the updates on updatedTAuditTrail are not directly saved in db
        em.detach(updatedTAuditTrail);
        updatedTAuditTrail
            .userId(UPDATED_USER_ID)
            .dateTime(UPDATED_DATE_TIME)
            .tableName(UPDATED_TABLE_NAME)
            .auditAction(UPDATED_AUDIT_ACTION)
            .recordId(UPDATED_RECORD_ID)
            .fieldDesc(UPDATED_FIELD_DESC)
            .atStatus(UPDATED_AT_STATUS)
            .stFullDesc(UPDATED_ST_FULL_DESC);

        restTAuditTrailMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTAuditTrail.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTAuditTrail))
            )
            .andExpect(status().isOk());

        // Validate the TAuditTrail in the database
        List<TAuditTrail> tAuditTrailList = tAuditTrailRepository.findAll();
        assertThat(tAuditTrailList).hasSize(databaseSizeBeforeUpdate);
        TAuditTrail testTAuditTrail = tAuditTrailList.get(tAuditTrailList.size() - 1);
        assertThat(testTAuditTrail.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testTAuditTrail.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testTAuditTrail.getTableName()).isEqualTo(UPDATED_TABLE_NAME);
        assertThat(testTAuditTrail.getAuditAction()).isEqualTo(UPDATED_AUDIT_ACTION);
        assertThat(testTAuditTrail.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testTAuditTrail.getFieldDesc()).isEqualTo(UPDATED_FIELD_DESC);
        assertThat(testTAuditTrail.getAtStatus()).isEqualTo(UPDATED_AT_STATUS);
        assertThat(testTAuditTrail.getStFullDesc()).isEqualTo(UPDATED_ST_FULL_DESC);
    }

    @Test
    @Transactional
    void putNonExistingTAuditTrail() throws Exception {
        int databaseSizeBeforeUpdate = tAuditTrailRepository.findAll().size();
        tAuditTrail.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTAuditTrailMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tAuditTrail.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tAuditTrail))
            )
            .andExpect(status().isBadRequest());

        // Validate the TAuditTrail in the database
        List<TAuditTrail> tAuditTrailList = tAuditTrailRepository.findAll();
        assertThat(tAuditTrailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTAuditTrail() throws Exception {
        int databaseSizeBeforeUpdate = tAuditTrailRepository.findAll().size();
        tAuditTrail.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTAuditTrailMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tAuditTrail))
            )
            .andExpect(status().isBadRequest());

        // Validate the TAuditTrail in the database
        List<TAuditTrail> tAuditTrailList = tAuditTrailRepository.findAll();
        assertThat(tAuditTrailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTAuditTrail() throws Exception {
        int databaseSizeBeforeUpdate = tAuditTrailRepository.findAll().size();
        tAuditTrail.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTAuditTrailMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tAuditTrail)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TAuditTrail in the database
        List<TAuditTrail> tAuditTrailList = tAuditTrailRepository.findAll();
        assertThat(tAuditTrailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTAuditTrailWithPatch() throws Exception {
        // Initialize the database
        tAuditTrailRepository.saveAndFlush(tAuditTrail);

        int databaseSizeBeforeUpdate = tAuditTrailRepository.findAll().size();

        // Update the tAuditTrail using partial update
        TAuditTrail partialUpdatedTAuditTrail = new TAuditTrail();
        partialUpdatedTAuditTrail.setId(tAuditTrail.getId());

        partialUpdatedTAuditTrail
            .dateTime(UPDATED_DATE_TIME)
            .tableName(UPDATED_TABLE_NAME)
            .auditAction(UPDATED_AUDIT_ACTION)
            .fieldDesc(UPDATED_FIELD_DESC)
            .stFullDesc(UPDATED_ST_FULL_DESC);

        restTAuditTrailMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTAuditTrail.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTAuditTrail))
            )
            .andExpect(status().isOk());

        // Validate the TAuditTrail in the database
        List<TAuditTrail> tAuditTrailList = tAuditTrailRepository.findAll();
        assertThat(tAuditTrailList).hasSize(databaseSizeBeforeUpdate);
        TAuditTrail testTAuditTrail = tAuditTrailList.get(tAuditTrailList.size() - 1);
        assertThat(testTAuditTrail.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testTAuditTrail.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testTAuditTrail.getTableName()).isEqualTo(UPDATED_TABLE_NAME);
        assertThat(testTAuditTrail.getAuditAction()).isEqualTo(UPDATED_AUDIT_ACTION);
        assertThat(testTAuditTrail.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testTAuditTrail.getFieldDesc()).isEqualTo(UPDATED_FIELD_DESC);
        assertThat(testTAuditTrail.getAtStatus()).isEqualTo(DEFAULT_AT_STATUS);
        assertThat(testTAuditTrail.getStFullDesc()).isEqualTo(UPDATED_ST_FULL_DESC);
    }

    @Test
    @Transactional
    void fullUpdateTAuditTrailWithPatch() throws Exception {
        // Initialize the database
        tAuditTrailRepository.saveAndFlush(tAuditTrail);

        int databaseSizeBeforeUpdate = tAuditTrailRepository.findAll().size();

        // Update the tAuditTrail using partial update
        TAuditTrail partialUpdatedTAuditTrail = new TAuditTrail();
        partialUpdatedTAuditTrail.setId(tAuditTrail.getId());

        partialUpdatedTAuditTrail
            .userId(UPDATED_USER_ID)
            .dateTime(UPDATED_DATE_TIME)
            .tableName(UPDATED_TABLE_NAME)
            .auditAction(UPDATED_AUDIT_ACTION)
            .recordId(UPDATED_RECORD_ID)
            .fieldDesc(UPDATED_FIELD_DESC)
            .atStatus(UPDATED_AT_STATUS)
            .stFullDesc(UPDATED_ST_FULL_DESC);

        restTAuditTrailMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTAuditTrail.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTAuditTrail))
            )
            .andExpect(status().isOk());

        // Validate the TAuditTrail in the database
        List<TAuditTrail> tAuditTrailList = tAuditTrailRepository.findAll();
        assertThat(tAuditTrailList).hasSize(databaseSizeBeforeUpdate);
        TAuditTrail testTAuditTrail = tAuditTrailList.get(tAuditTrailList.size() - 1);
        assertThat(testTAuditTrail.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testTAuditTrail.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testTAuditTrail.getTableName()).isEqualTo(UPDATED_TABLE_NAME);
        assertThat(testTAuditTrail.getAuditAction()).isEqualTo(UPDATED_AUDIT_ACTION);
        assertThat(testTAuditTrail.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testTAuditTrail.getFieldDesc()).isEqualTo(UPDATED_FIELD_DESC);
        assertThat(testTAuditTrail.getAtStatus()).isEqualTo(UPDATED_AT_STATUS);
        assertThat(testTAuditTrail.getStFullDesc()).isEqualTo(UPDATED_ST_FULL_DESC);
    }

    @Test
    @Transactional
    void patchNonExistingTAuditTrail() throws Exception {
        int databaseSizeBeforeUpdate = tAuditTrailRepository.findAll().size();
        tAuditTrail.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTAuditTrailMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tAuditTrail.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tAuditTrail))
            )
            .andExpect(status().isBadRequest());

        // Validate the TAuditTrail in the database
        List<TAuditTrail> tAuditTrailList = tAuditTrailRepository.findAll();
        assertThat(tAuditTrailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTAuditTrail() throws Exception {
        int databaseSizeBeforeUpdate = tAuditTrailRepository.findAll().size();
        tAuditTrail.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTAuditTrailMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tAuditTrail))
            )
            .andExpect(status().isBadRequest());

        // Validate the TAuditTrail in the database
        List<TAuditTrail> tAuditTrailList = tAuditTrailRepository.findAll();
        assertThat(tAuditTrailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTAuditTrail() throws Exception {
        int databaseSizeBeforeUpdate = tAuditTrailRepository.findAll().size();
        tAuditTrail.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTAuditTrailMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tAuditTrail))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TAuditTrail in the database
        List<TAuditTrail> tAuditTrailList = tAuditTrailRepository.findAll();
        assertThat(tAuditTrailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTAuditTrail() throws Exception {
        // Initialize the database
        tAuditTrailRepository.saveAndFlush(tAuditTrail);

        int databaseSizeBeforeDelete = tAuditTrailRepository.findAll().size();

        // Delete the tAuditTrail
        restTAuditTrailMockMvc
            .perform(delete(ENTITY_API_URL_ID, tAuditTrail.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TAuditTrail> tAuditTrailList = tAuditTrailRepository.findAll();
        assertThat(tAuditTrailList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
