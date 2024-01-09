package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TDivisionCode;
import com.jhipster.app.repository.TDivisionCodeRepository;
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
 * Integration tests for the {@link TDivisionCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TDivisionCodeResourceIT {

    private static final String DEFAULT_DIV_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DIV_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ENTERED_BY = 1;
    private static final Integer UPDATED_ENTERED_BY = 2;

    private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_MODIFY_BY = 1;
    private static final Integer UPDATED_MODIFY_BY = 2;

    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/t-division-codes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TDivisionCodeRepository tDivisionCodeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTDivisionCodeMockMvc;

    private TDivisionCode tDivisionCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TDivisionCode createEntity(EntityManager em) {
        TDivisionCode tDivisionCode = new TDivisionCode()
            .divName(DEFAULT_DIV_NAME)
            .enteredBy(DEFAULT_ENTERED_BY)
            .enteredDate(DEFAULT_ENTERED_DATE)
            .modifyBy(DEFAULT_MODIFY_BY)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return tDivisionCode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TDivisionCode createUpdatedEntity(EntityManager em) {
        TDivisionCode tDivisionCode = new TDivisionCode()
            .divName(UPDATED_DIV_NAME)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifyBy(UPDATED_MODIFY_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return tDivisionCode;
    }

    @BeforeEach
    public void initTest() {
        tDivisionCode = createEntity(em);
    }

    @Test
    @Transactional
    void createTDivisionCode() throws Exception {
        int databaseSizeBeforeCreate = tDivisionCodeRepository.findAll().size();
        // Create the TDivisionCode
        restTDivisionCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tDivisionCode)))
            .andExpect(status().isCreated());

        // Validate the TDivisionCode in the database
        List<TDivisionCode> tDivisionCodeList = tDivisionCodeRepository.findAll();
        assertThat(tDivisionCodeList).hasSize(databaseSizeBeforeCreate + 1);
        TDivisionCode testTDivisionCode = tDivisionCodeList.get(tDivisionCodeList.size() - 1);
        assertThat(testTDivisionCode.getDivName()).isEqualTo(DEFAULT_DIV_NAME);
        assertThat(testTDivisionCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
        assertThat(testTDivisionCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
        assertThat(testTDivisionCode.getModifyBy()).isEqualTo(DEFAULT_MODIFY_BY);
        assertThat(testTDivisionCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createTDivisionCodeWithExistingId() throws Exception {
        // Create the TDivisionCode with an existing ID
        tDivisionCode.setId(1L);

        int databaseSizeBeforeCreate = tDivisionCodeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTDivisionCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tDivisionCode)))
            .andExpect(status().isBadRequest());

        // Validate the TDivisionCode in the database
        List<TDivisionCode> tDivisionCodeList = tDivisionCodeRepository.findAll();
        assertThat(tDivisionCodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTDivisionCodes() throws Exception {
        // Initialize the database
        tDivisionCodeRepository.saveAndFlush(tDivisionCode);

        // Get all the tDivisionCodeList
        restTDivisionCodeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tDivisionCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].divName").value(hasItem(DEFAULT_DIV_NAME)))
            .andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
            .andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
            .andExpect(jsonPath("$.[*].modifyBy").value(hasItem(DEFAULT_MODIFY_BY)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
    }

    @Test
    @Transactional
    void getTDivisionCode() throws Exception {
        // Initialize the database
        tDivisionCodeRepository.saveAndFlush(tDivisionCode);

        // Get the tDivisionCode
        restTDivisionCodeMockMvc
            .perform(get(ENTITY_API_URL_ID, tDivisionCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tDivisionCode.getId().intValue()))
            .andExpect(jsonPath("$.divName").value(DEFAULT_DIV_NAME))
            .andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
            .andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
            .andExpect(jsonPath("$.modifyBy").value(DEFAULT_MODIFY_BY))
            .andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
    }

    @Test
    @Transactional
    void getNonExistingTDivisionCode() throws Exception {
        // Get the tDivisionCode
        restTDivisionCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTDivisionCode() throws Exception {
        // Initialize the database
        tDivisionCodeRepository.saveAndFlush(tDivisionCode);

        int databaseSizeBeforeUpdate = tDivisionCodeRepository.findAll().size();

        // Update the tDivisionCode
        TDivisionCode updatedTDivisionCode = tDivisionCodeRepository.findById(tDivisionCode.getId()).get();
        // Disconnect from session so that the updates on updatedTDivisionCode are not directly saved in db
        em.detach(updatedTDivisionCode);
        updatedTDivisionCode
            .divName(UPDATED_DIV_NAME)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifyBy(UPDATED_MODIFY_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTDivisionCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTDivisionCode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTDivisionCode))
            )
            .andExpect(status().isOk());

        // Validate the TDivisionCode in the database
        List<TDivisionCode> tDivisionCodeList = tDivisionCodeRepository.findAll();
        assertThat(tDivisionCodeList).hasSize(databaseSizeBeforeUpdate);
        TDivisionCode testTDivisionCode = tDivisionCodeList.get(tDivisionCodeList.size() - 1);
        assertThat(testTDivisionCode.getDivName()).isEqualTo(UPDATED_DIV_NAME);
        assertThat(testTDivisionCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTDivisionCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTDivisionCode.getModifyBy()).isEqualTo(UPDATED_MODIFY_BY);
        assertThat(testTDivisionCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingTDivisionCode() throws Exception {
        int databaseSizeBeforeUpdate = tDivisionCodeRepository.findAll().size();
        tDivisionCode.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTDivisionCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tDivisionCode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tDivisionCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TDivisionCode in the database
        List<TDivisionCode> tDivisionCodeList = tDivisionCodeRepository.findAll();
        assertThat(tDivisionCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTDivisionCode() throws Exception {
        int databaseSizeBeforeUpdate = tDivisionCodeRepository.findAll().size();
        tDivisionCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTDivisionCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tDivisionCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TDivisionCode in the database
        List<TDivisionCode> tDivisionCodeList = tDivisionCodeRepository.findAll();
        assertThat(tDivisionCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTDivisionCode() throws Exception {
        int databaseSizeBeforeUpdate = tDivisionCodeRepository.findAll().size();
        tDivisionCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTDivisionCodeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tDivisionCode)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TDivisionCode in the database
        List<TDivisionCode> tDivisionCodeList = tDivisionCodeRepository.findAll();
        assertThat(tDivisionCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTDivisionCodeWithPatch() throws Exception {
        // Initialize the database
        tDivisionCodeRepository.saveAndFlush(tDivisionCode);

        int databaseSizeBeforeUpdate = tDivisionCodeRepository.findAll().size();

        // Update the tDivisionCode using partial update
        TDivisionCode partialUpdatedTDivisionCode = new TDivisionCode();
        partialUpdatedTDivisionCode.setId(tDivisionCode.getId());

        partialUpdatedTDivisionCode.enteredBy(UPDATED_ENTERED_BY).enteredDate(UPDATED_ENTERED_DATE).modifyBy(UPDATED_MODIFY_BY);

        restTDivisionCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTDivisionCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTDivisionCode))
            )
            .andExpect(status().isOk());

        // Validate the TDivisionCode in the database
        List<TDivisionCode> tDivisionCodeList = tDivisionCodeRepository.findAll();
        assertThat(tDivisionCodeList).hasSize(databaseSizeBeforeUpdate);
        TDivisionCode testTDivisionCode = tDivisionCodeList.get(tDivisionCodeList.size() - 1);
        assertThat(testTDivisionCode.getDivName()).isEqualTo(DEFAULT_DIV_NAME);
        assertThat(testTDivisionCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTDivisionCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTDivisionCode.getModifyBy()).isEqualTo(UPDATED_MODIFY_BY);
        assertThat(testTDivisionCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateTDivisionCodeWithPatch() throws Exception {
        // Initialize the database
        tDivisionCodeRepository.saveAndFlush(tDivisionCode);

        int databaseSizeBeforeUpdate = tDivisionCodeRepository.findAll().size();

        // Update the tDivisionCode using partial update
        TDivisionCode partialUpdatedTDivisionCode = new TDivisionCode();
        partialUpdatedTDivisionCode.setId(tDivisionCode.getId());

        partialUpdatedTDivisionCode
            .divName(UPDATED_DIV_NAME)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifyBy(UPDATED_MODIFY_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTDivisionCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTDivisionCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTDivisionCode))
            )
            .andExpect(status().isOk());

        // Validate the TDivisionCode in the database
        List<TDivisionCode> tDivisionCodeList = tDivisionCodeRepository.findAll();
        assertThat(tDivisionCodeList).hasSize(databaseSizeBeforeUpdate);
        TDivisionCode testTDivisionCode = tDivisionCodeList.get(tDivisionCodeList.size() - 1);
        assertThat(testTDivisionCode.getDivName()).isEqualTo(UPDATED_DIV_NAME);
        assertThat(testTDivisionCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTDivisionCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTDivisionCode.getModifyBy()).isEqualTo(UPDATED_MODIFY_BY);
        assertThat(testTDivisionCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingTDivisionCode() throws Exception {
        int databaseSizeBeforeUpdate = tDivisionCodeRepository.findAll().size();
        tDivisionCode.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTDivisionCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tDivisionCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tDivisionCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TDivisionCode in the database
        List<TDivisionCode> tDivisionCodeList = tDivisionCodeRepository.findAll();
        assertThat(tDivisionCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTDivisionCode() throws Exception {
        int databaseSizeBeforeUpdate = tDivisionCodeRepository.findAll().size();
        tDivisionCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTDivisionCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tDivisionCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TDivisionCode in the database
        List<TDivisionCode> tDivisionCodeList = tDivisionCodeRepository.findAll();
        assertThat(tDivisionCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTDivisionCode() throws Exception {
        int databaseSizeBeforeUpdate = tDivisionCodeRepository.findAll().size();
        tDivisionCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTDivisionCodeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tDivisionCode))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TDivisionCode in the database
        List<TDivisionCode> tDivisionCodeList = tDivisionCodeRepository.findAll();
        assertThat(tDivisionCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTDivisionCode() throws Exception {
        // Initialize the database
        tDivisionCodeRepository.saveAndFlush(tDivisionCode);

        int databaseSizeBeforeDelete = tDivisionCodeRepository.findAll().size();

        // Delete the tDivisionCode
        restTDivisionCodeMockMvc
            .perform(delete(ENTITY_API_URL_ID, tDivisionCode.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TDivisionCode> tDivisionCodeList = tDivisionCodeRepository.findAll();
        assertThat(tDivisionCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
