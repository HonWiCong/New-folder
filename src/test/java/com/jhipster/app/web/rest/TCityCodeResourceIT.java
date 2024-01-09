package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TCityCode;
import com.jhipster.app.repository.TCityCodeRepository;
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
 * Integration tests for the {@link TCityCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCityCodeResourceIT {

    private static final String DEFAULT_CITY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CITY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CITY_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ENTERED_BY = 1;
    private static final Integer UPDATED_ENTERED_BY = 2;

    private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_MODIFY_BY = 1;
    private static final Integer UPDATED_MODIFY_BY = 2;

    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/t-city-codes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCityCodeRepository tCityCodeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCityCodeMockMvc;

    private TCityCode tCityCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCityCode createEntity(EntityManager em) {
        TCityCode tCityCode = new TCityCode()
            .cityCode(DEFAULT_CITY_CODE)
            .cityName(DEFAULT_CITY_NAME)
            .enteredBy(DEFAULT_ENTERED_BY)
            .enteredDate(DEFAULT_ENTERED_DATE)
            .modifyBy(DEFAULT_MODIFY_BY)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return tCityCode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCityCode createUpdatedEntity(EntityManager em) {
        TCityCode tCityCode = new TCityCode()
            .cityCode(UPDATED_CITY_CODE)
            .cityName(UPDATED_CITY_NAME)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifyBy(UPDATED_MODIFY_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return tCityCode;
    }

    @BeforeEach
    public void initTest() {
        tCityCode = createEntity(em);
    }

    @Test
    @Transactional
    void createTCityCode() throws Exception {
        int databaseSizeBeforeCreate = tCityCodeRepository.findAll().size();
        // Create the TCityCode
        restTCityCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCityCode)))
            .andExpect(status().isCreated());

        // Validate the TCityCode in the database
        List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
        assertThat(tCityCodeList).hasSize(databaseSizeBeforeCreate + 1);
        TCityCode testTCityCode = tCityCodeList.get(tCityCodeList.size() - 1);
        assertThat(testTCityCode.getCityCode()).isEqualTo(DEFAULT_CITY_CODE);
        assertThat(testTCityCode.getCityName()).isEqualTo(DEFAULT_CITY_NAME);
        assertThat(testTCityCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
        assertThat(testTCityCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
        assertThat(testTCityCode.getModifyBy()).isEqualTo(DEFAULT_MODIFY_BY);
        assertThat(testTCityCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createTCityCodeWithExistingId() throws Exception {
        // Create the TCityCode with an existing ID
        tCityCode.setId(1L);

        int databaseSizeBeforeCreate = tCityCodeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCityCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCityCode)))
            .andExpect(status().isBadRequest());

        // Validate the TCityCode in the database
        List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
        assertThat(tCityCodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTCityCodes() throws Exception {
        // Initialize the database
        tCityCodeRepository.saveAndFlush(tCityCode);

        // Get all the tCityCodeList
        restTCityCodeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tCityCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].cityCode").value(hasItem(DEFAULT_CITY_CODE)))
            .andExpect(jsonPath("$.[*].cityName").value(hasItem(DEFAULT_CITY_NAME)))
            .andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
            .andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
            .andExpect(jsonPath("$.[*].modifyBy").value(hasItem(DEFAULT_MODIFY_BY)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
    }

    @Test
    @Transactional
    void getTCityCode() throws Exception {
        // Initialize the database
        tCityCodeRepository.saveAndFlush(tCityCode);

        // Get the tCityCode
        restTCityCodeMockMvc
            .perform(get(ENTITY_API_URL_ID, tCityCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tCityCode.getId().intValue()))
            .andExpect(jsonPath("$.cityCode").value(DEFAULT_CITY_CODE))
            .andExpect(jsonPath("$.cityName").value(DEFAULT_CITY_NAME))
            .andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
            .andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
            .andExpect(jsonPath("$.modifyBy").value(DEFAULT_MODIFY_BY))
            .andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
    }

    @Test
    @Transactional
    void getNonExistingTCityCode() throws Exception {
        // Get the tCityCode
        restTCityCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTCityCode() throws Exception {
        // Initialize the database
        tCityCodeRepository.saveAndFlush(tCityCode);

        int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();

        // Update the tCityCode
        TCityCode updatedTCityCode = tCityCodeRepository.findById(tCityCode.getId()).get();
        // Disconnect from session so that the updates on updatedTCityCode are not directly saved in db
        em.detach(updatedTCityCode);
        updatedTCityCode
            .cityCode(UPDATED_CITY_CODE)
            .cityName(UPDATED_CITY_NAME)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifyBy(UPDATED_MODIFY_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTCityCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCityCode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCityCode))
            )
            .andExpect(status().isOk());

        // Validate the TCityCode in the database
        List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
        assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
        TCityCode testTCityCode = tCityCodeList.get(tCityCodeList.size() - 1);
        assertThat(testTCityCode.getCityCode()).isEqualTo(UPDATED_CITY_CODE);
        assertThat(testTCityCode.getCityName()).isEqualTo(UPDATED_CITY_NAME);
        assertThat(testTCityCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTCityCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTCityCode.getModifyBy()).isEqualTo(UPDATED_MODIFY_BY);
        assertThat(testTCityCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingTCityCode() throws Exception {
        int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();
        tCityCode.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCityCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCityCode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCityCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCityCode in the database
        List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
        assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCityCode() throws Exception {
        int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();
        tCityCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCityCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCityCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCityCode in the database
        List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
        assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCityCode() throws Exception {
        int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();
        tCityCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCityCodeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCityCode)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCityCode in the database
        List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
        assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCityCodeWithPatch() throws Exception {
        // Initialize the database
        tCityCodeRepository.saveAndFlush(tCityCode);

        int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();

        // Update the tCityCode using partial update
        TCityCode partialUpdatedTCityCode = new TCityCode();
        partialUpdatedTCityCode.setId(tCityCode.getId());

        partialUpdatedTCityCode.cityCode(UPDATED_CITY_CODE).enteredBy(UPDATED_ENTERED_BY).enteredDate(UPDATED_ENTERED_DATE);

        restTCityCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCityCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCityCode))
            )
            .andExpect(status().isOk());

        // Validate the TCityCode in the database
        List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
        assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
        TCityCode testTCityCode = tCityCodeList.get(tCityCodeList.size() - 1);
        assertThat(testTCityCode.getCityCode()).isEqualTo(UPDATED_CITY_CODE);
        assertThat(testTCityCode.getCityName()).isEqualTo(DEFAULT_CITY_NAME);
        assertThat(testTCityCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTCityCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTCityCode.getModifyBy()).isEqualTo(DEFAULT_MODIFY_BY);
        assertThat(testTCityCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateTCityCodeWithPatch() throws Exception {
        // Initialize the database
        tCityCodeRepository.saveAndFlush(tCityCode);

        int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();

        // Update the tCityCode using partial update
        TCityCode partialUpdatedTCityCode = new TCityCode();
        partialUpdatedTCityCode.setId(tCityCode.getId());

        partialUpdatedTCityCode
            .cityCode(UPDATED_CITY_CODE)
            .cityName(UPDATED_CITY_NAME)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifyBy(UPDATED_MODIFY_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTCityCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCityCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCityCode))
            )
            .andExpect(status().isOk());

        // Validate the TCityCode in the database
        List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
        assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
        TCityCode testTCityCode = tCityCodeList.get(tCityCodeList.size() - 1);
        assertThat(testTCityCode.getCityCode()).isEqualTo(UPDATED_CITY_CODE);
        assertThat(testTCityCode.getCityName()).isEqualTo(UPDATED_CITY_NAME);
        assertThat(testTCityCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTCityCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTCityCode.getModifyBy()).isEqualTo(UPDATED_MODIFY_BY);
        assertThat(testTCityCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingTCityCode() throws Exception {
        int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();
        tCityCode.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCityCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCityCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCityCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCityCode in the database
        List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
        assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCityCode() throws Exception {
        int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();
        tCityCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCityCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCityCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCityCode in the database
        List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
        assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCityCode() throws Exception {
        int databaseSizeBeforeUpdate = tCityCodeRepository.findAll().size();
        tCityCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCityCodeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCityCode))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCityCode in the database
        List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
        assertThat(tCityCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCityCode() throws Exception {
        // Initialize the database
        tCityCodeRepository.saveAndFlush(tCityCode);

        int databaseSizeBeforeDelete = tCityCodeRepository.findAll().size();

        // Delete the tCityCode
        restTCityCodeMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCityCode.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCityCode> tCityCodeList = tCityCodeRepository.findAll();
        assertThat(tCityCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
