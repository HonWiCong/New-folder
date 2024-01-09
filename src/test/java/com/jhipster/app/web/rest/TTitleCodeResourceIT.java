package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TTitleCode;
import com.jhipster.app.repository.TTitleCodeRepository;
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
 * Integration tests for the {@link TTitleCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TTitleCodeResourceIT {

    private static final String DEFAULT_TT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TT_TITLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ENTERED_BY = 1;
    private static final Integer UPDATED_ENTERED_BY = 2;

    private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_MODIFIED_BY = 1;
    private static final Integer UPDATED_MODIFIED_BY = 2;

    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/t-title-codes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TTitleCodeRepository tTitleCodeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTTitleCodeMockMvc;

    private TTitleCode tTitleCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TTitleCode createEntity(EntityManager em) {
        TTitleCode tTitleCode = new TTitleCode()
            .ttTitle(DEFAULT_TT_TITLE)
            .enteredBy(DEFAULT_ENTERED_BY)
            .enteredDate(DEFAULT_ENTERED_DATE)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return tTitleCode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TTitleCode createUpdatedEntity(EntityManager em) {
        TTitleCode tTitleCode = new TTitleCode()
            .ttTitle(UPDATED_TT_TITLE)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return tTitleCode;
    }

    @BeforeEach
    public void initTest() {
        tTitleCode = createEntity(em);
    }

    @Test
    @Transactional
    void createTTitleCode() throws Exception {
        int databaseSizeBeforeCreate = tTitleCodeRepository.findAll().size();
        // Create the TTitleCode
        restTTitleCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tTitleCode)))
            .andExpect(status().isCreated());

        // Validate the TTitleCode in the database
        List<TTitleCode> tTitleCodeList = tTitleCodeRepository.findAll();
        assertThat(tTitleCodeList).hasSize(databaseSizeBeforeCreate + 1);
        TTitleCode testTTitleCode = tTitleCodeList.get(tTitleCodeList.size() - 1);
        assertThat(testTTitleCode.getTtTitle()).isEqualTo(DEFAULT_TT_TITLE);
        assertThat(testTTitleCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
        assertThat(testTTitleCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
        assertThat(testTTitleCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testTTitleCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createTTitleCodeWithExistingId() throws Exception {
        // Create the TTitleCode with an existing ID
        tTitleCode.setId(1L);

        int databaseSizeBeforeCreate = tTitleCodeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTTitleCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tTitleCode)))
            .andExpect(status().isBadRequest());

        // Validate the TTitleCode in the database
        List<TTitleCode> tTitleCodeList = tTitleCodeRepository.findAll();
        assertThat(tTitleCodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTTitleCodes() throws Exception {
        // Initialize the database
        tTitleCodeRepository.saveAndFlush(tTitleCode);

        // Get all the tTitleCodeList
        restTTitleCodeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tTitleCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].ttTitle").value(hasItem(DEFAULT_TT_TITLE)))
            .andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
            .andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
    }

    @Test
    @Transactional
    void getTTitleCode() throws Exception {
        // Initialize the database
        tTitleCodeRepository.saveAndFlush(tTitleCode);

        // Get the tTitleCode
        restTTitleCodeMockMvc
            .perform(get(ENTITY_API_URL_ID, tTitleCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tTitleCode.getId().intValue()))
            .andExpect(jsonPath("$.ttTitle").value(DEFAULT_TT_TITLE))
            .andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
            .andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
            .andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
    }

    @Test
    @Transactional
    void getNonExistingTTitleCode() throws Exception {
        // Get the tTitleCode
        restTTitleCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTTitleCode() throws Exception {
        // Initialize the database
        tTitleCodeRepository.saveAndFlush(tTitleCode);

        int databaseSizeBeforeUpdate = tTitleCodeRepository.findAll().size();

        // Update the tTitleCode
        TTitleCode updatedTTitleCode = tTitleCodeRepository.findById(tTitleCode.getId()).get();
        // Disconnect from session so that the updates on updatedTTitleCode are not directly saved in db
        em.detach(updatedTTitleCode);
        updatedTTitleCode
            .ttTitle(UPDATED_TT_TITLE)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTTitleCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTTitleCode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTTitleCode))
            )
            .andExpect(status().isOk());

        // Validate the TTitleCode in the database
        List<TTitleCode> tTitleCodeList = tTitleCodeRepository.findAll();
        assertThat(tTitleCodeList).hasSize(databaseSizeBeforeUpdate);
        TTitleCode testTTitleCode = tTitleCodeList.get(tTitleCodeList.size() - 1);
        assertThat(testTTitleCode.getTtTitle()).isEqualTo(UPDATED_TT_TITLE);
        assertThat(testTTitleCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTTitleCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTTitleCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTTitleCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingTTitleCode() throws Exception {
        int databaseSizeBeforeUpdate = tTitleCodeRepository.findAll().size();
        tTitleCode.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTTitleCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tTitleCode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tTitleCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TTitleCode in the database
        List<TTitleCode> tTitleCodeList = tTitleCodeRepository.findAll();
        assertThat(tTitleCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTTitleCode() throws Exception {
        int databaseSizeBeforeUpdate = tTitleCodeRepository.findAll().size();
        tTitleCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTTitleCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tTitleCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TTitleCode in the database
        List<TTitleCode> tTitleCodeList = tTitleCodeRepository.findAll();
        assertThat(tTitleCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTTitleCode() throws Exception {
        int databaseSizeBeforeUpdate = tTitleCodeRepository.findAll().size();
        tTitleCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTTitleCodeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tTitleCode)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TTitleCode in the database
        List<TTitleCode> tTitleCodeList = tTitleCodeRepository.findAll();
        assertThat(tTitleCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTTitleCodeWithPatch() throws Exception {
        // Initialize the database
        tTitleCodeRepository.saveAndFlush(tTitleCode);

        int databaseSizeBeforeUpdate = tTitleCodeRepository.findAll().size();

        // Update the tTitleCode using partial update
        TTitleCode partialUpdatedTTitleCode = new TTitleCode();
        partialUpdatedTTitleCode.setId(tTitleCode.getId());

        partialUpdatedTTitleCode.enteredDate(UPDATED_ENTERED_DATE).modifiedBy(UPDATED_MODIFIED_BY).modifiedDate(UPDATED_MODIFIED_DATE);

        restTTitleCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTTitleCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTTitleCode))
            )
            .andExpect(status().isOk());

        // Validate the TTitleCode in the database
        List<TTitleCode> tTitleCodeList = tTitleCodeRepository.findAll();
        assertThat(tTitleCodeList).hasSize(databaseSizeBeforeUpdate);
        TTitleCode testTTitleCode = tTitleCodeList.get(tTitleCodeList.size() - 1);
        assertThat(testTTitleCode.getTtTitle()).isEqualTo(DEFAULT_TT_TITLE);
        assertThat(testTTitleCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
        assertThat(testTTitleCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTTitleCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTTitleCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateTTitleCodeWithPatch() throws Exception {
        // Initialize the database
        tTitleCodeRepository.saveAndFlush(tTitleCode);

        int databaseSizeBeforeUpdate = tTitleCodeRepository.findAll().size();

        // Update the tTitleCode using partial update
        TTitleCode partialUpdatedTTitleCode = new TTitleCode();
        partialUpdatedTTitleCode.setId(tTitleCode.getId());

        partialUpdatedTTitleCode
            .ttTitle(UPDATED_TT_TITLE)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTTitleCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTTitleCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTTitleCode))
            )
            .andExpect(status().isOk());

        // Validate the TTitleCode in the database
        List<TTitleCode> tTitleCodeList = tTitleCodeRepository.findAll();
        assertThat(tTitleCodeList).hasSize(databaseSizeBeforeUpdate);
        TTitleCode testTTitleCode = tTitleCodeList.get(tTitleCodeList.size() - 1);
        assertThat(testTTitleCode.getTtTitle()).isEqualTo(UPDATED_TT_TITLE);
        assertThat(testTTitleCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTTitleCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTTitleCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTTitleCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingTTitleCode() throws Exception {
        int databaseSizeBeforeUpdate = tTitleCodeRepository.findAll().size();
        tTitleCode.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTTitleCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tTitleCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tTitleCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TTitleCode in the database
        List<TTitleCode> tTitleCodeList = tTitleCodeRepository.findAll();
        assertThat(tTitleCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTTitleCode() throws Exception {
        int databaseSizeBeforeUpdate = tTitleCodeRepository.findAll().size();
        tTitleCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTTitleCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tTitleCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TTitleCode in the database
        List<TTitleCode> tTitleCodeList = tTitleCodeRepository.findAll();
        assertThat(tTitleCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTTitleCode() throws Exception {
        int databaseSizeBeforeUpdate = tTitleCodeRepository.findAll().size();
        tTitleCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTTitleCodeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tTitleCode))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TTitleCode in the database
        List<TTitleCode> tTitleCodeList = tTitleCodeRepository.findAll();
        assertThat(tTitleCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTTitleCode() throws Exception {
        // Initialize the database
        tTitleCodeRepository.saveAndFlush(tTitleCode);

        int databaseSizeBeforeDelete = tTitleCodeRepository.findAll().size();

        // Delete the tTitleCode
        restTTitleCodeMockMvc
            .perform(delete(ENTITY_API_URL_ID, tTitleCode.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TTitleCode> tTitleCodeList = tTitleCodeRepository.findAll();
        assertThat(tTitleCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
