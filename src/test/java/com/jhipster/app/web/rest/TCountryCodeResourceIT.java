package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TCountryCode;
import com.jhipster.app.repository.TCountryCodeRepository;
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
 * Integration tests for the {@link TCountryCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCountryCodeResourceIT {

    private static final String DEFAULT_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_NATIONALITY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_NATIONALITY = "BBBBBBBBBB";

    private static final Integer DEFAULT_ENTERED_BY = 1;
    private static final Integer UPDATED_ENTERED_BY = 2;

    private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_MODIFIED_BY = 1;
    private static final Integer UPDATED_MODIFIED_BY = 2;

    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_ORG_CUSTOMER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ORG_CUSTOMER_TYPE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/t-country-codes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCountryCodeRepository tCountryCodeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCountryCodeMockMvc;

    private TCountryCode tCountryCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCountryCode createEntity(EntityManager em) {
        TCountryCode tCountryCode = new TCountryCode()
            .countryCode(DEFAULT_COUNTRY_CODE)
            .countryName(DEFAULT_COUNTRY_NAME)
            .countryNationality(DEFAULT_COUNTRY_NATIONALITY)
            .enteredBy(DEFAULT_ENTERED_BY)
            .enteredDate(DEFAULT_ENTERED_DATE)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .modifiedDate(DEFAULT_MODIFIED_DATE)
            .orgCustomerType(DEFAULT_ORG_CUSTOMER_TYPE);
        return tCountryCode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCountryCode createUpdatedEntity(EntityManager em) {
        TCountryCode tCountryCode = new TCountryCode()
            .countryCode(UPDATED_COUNTRY_CODE)
            .countryName(UPDATED_COUNTRY_NAME)
            .countryNationality(UPDATED_COUNTRY_NATIONALITY)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .orgCustomerType(UPDATED_ORG_CUSTOMER_TYPE);
        return tCountryCode;
    }

    @BeforeEach
    public void initTest() {
        tCountryCode = createEntity(em);
    }

    @Test
    @Transactional
    void createTCountryCode() throws Exception {
        int databaseSizeBeforeCreate = tCountryCodeRepository.findAll().size();
        // Create the TCountryCode
        restTCountryCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCountryCode)))
            .andExpect(status().isCreated());

        // Validate the TCountryCode in the database
        List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
        assertThat(tCountryCodeList).hasSize(databaseSizeBeforeCreate + 1);
        TCountryCode testTCountryCode = tCountryCodeList.get(tCountryCodeList.size() - 1);
        assertThat(testTCountryCode.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
        assertThat(testTCountryCode.getCountryName()).isEqualTo(DEFAULT_COUNTRY_NAME);
        assertThat(testTCountryCode.getCountryNationality()).isEqualTo(DEFAULT_COUNTRY_NATIONALITY);
        assertThat(testTCountryCode.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
        assertThat(testTCountryCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
        assertThat(testTCountryCode.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testTCountryCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testTCountryCode.getOrgCustomerType()).isEqualTo(DEFAULT_ORG_CUSTOMER_TYPE);
    }

    @Test
    @Transactional
    void createTCountryCodeWithExistingId() throws Exception {
        // Create the TCountryCode with an existing ID
        tCountryCode.setId(1L);

        int databaseSizeBeforeCreate = tCountryCodeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCountryCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCountryCode)))
            .andExpect(status().isBadRequest());

        // Validate the TCountryCode in the database
        List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
        assertThat(tCountryCodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTCountryCodes() throws Exception {
        // Initialize the database
        tCountryCodeRepository.saveAndFlush(tCountryCode);

        // Get all the tCountryCodeList
        restTCountryCodeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tCountryCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].countryName").value(hasItem(DEFAULT_COUNTRY_NAME)))
            .andExpect(jsonPath("$.[*].countryNationality").value(hasItem(DEFAULT_COUNTRY_NATIONALITY)))
            .andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
            .andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))))
            .andExpect(jsonPath("$.[*].orgCustomerType").value(hasItem(DEFAULT_ORG_CUSTOMER_TYPE)));
    }

    @Test
    @Transactional
    void getTCountryCode() throws Exception {
        // Initialize the database
        tCountryCodeRepository.saveAndFlush(tCountryCode);

        // Get the tCountryCode
        restTCountryCodeMockMvc
            .perform(get(ENTITY_API_URL_ID, tCountryCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tCountryCode.getId().intValue()))
            .andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE))
            .andExpect(jsonPath("$.countryName").value(DEFAULT_COUNTRY_NAME))
            .andExpect(jsonPath("$.countryNationality").value(DEFAULT_COUNTRY_NATIONALITY))
            .andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
            .andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
            .andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)))
            .andExpect(jsonPath("$.orgCustomerType").value(DEFAULT_ORG_CUSTOMER_TYPE));
    }

    @Test
    @Transactional
    void getNonExistingTCountryCode() throws Exception {
        // Get the tCountryCode
        restTCountryCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTCountryCode() throws Exception {
        // Initialize the database
        tCountryCodeRepository.saveAndFlush(tCountryCode);

        int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();

        // Update the tCountryCode
        TCountryCode updatedTCountryCode = tCountryCodeRepository.findById(tCountryCode.getId()).get();
        // Disconnect from session so that the updates on updatedTCountryCode are not directly saved in db
        em.detach(updatedTCountryCode);
        updatedTCountryCode
            .countryCode(UPDATED_COUNTRY_CODE)
            .countryName(UPDATED_COUNTRY_NAME)
            .countryNationality(UPDATED_COUNTRY_NATIONALITY)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .orgCustomerType(UPDATED_ORG_CUSTOMER_TYPE);

        restTCountryCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCountryCode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCountryCode))
            )
            .andExpect(status().isOk());

        // Validate the TCountryCode in the database
        List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
        assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
        TCountryCode testTCountryCode = tCountryCodeList.get(tCountryCodeList.size() - 1);
        assertThat(testTCountryCode.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testTCountryCode.getCountryName()).isEqualTo(UPDATED_COUNTRY_NAME);
        assertThat(testTCountryCode.getCountryNationality()).isEqualTo(UPDATED_COUNTRY_NATIONALITY);
        assertThat(testTCountryCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTCountryCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTCountryCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTCountryCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testTCountryCode.getOrgCustomerType()).isEqualTo(UPDATED_ORG_CUSTOMER_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingTCountryCode() throws Exception {
        int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();
        tCountryCode.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCountryCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCountryCode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCountryCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCountryCode in the database
        List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
        assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCountryCode() throws Exception {
        int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();
        tCountryCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCountryCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCountryCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCountryCode in the database
        List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
        assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCountryCode() throws Exception {
        int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();
        tCountryCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCountryCodeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCountryCode)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCountryCode in the database
        List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
        assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCountryCodeWithPatch() throws Exception {
        // Initialize the database
        tCountryCodeRepository.saveAndFlush(tCountryCode);

        int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();

        // Update the tCountryCode using partial update
        TCountryCode partialUpdatedTCountryCode = new TCountryCode();
        partialUpdatedTCountryCode.setId(tCountryCode.getId());

        partialUpdatedTCountryCode.enteredBy(UPDATED_ENTERED_BY).modifiedBy(UPDATED_MODIFIED_BY).orgCustomerType(UPDATED_ORG_CUSTOMER_TYPE);

        restTCountryCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCountryCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCountryCode))
            )
            .andExpect(status().isOk());

        // Validate the TCountryCode in the database
        List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
        assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
        TCountryCode testTCountryCode = tCountryCodeList.get(tCountryCodeList.size() - 1);
        assertThat(testTCountryCode.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
        assertThat(testTCountryCode.getCountryName()).isEqualTo(DEFAULT_COUNTRY_NAME);
        assertThat(testTCountryCode.getCountryNationality()).isEqualTo(DEFAULT_COUNTRY_NATIONALITY);
        assertThat(testTCountryCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTCountryCode.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
        assertThat(testTCountryCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTCountryCode.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testTCountryCode.getOrgCustomerType()).isEqualTo(UPDATED_ORG_CUSTOMER_TYPE);
    }

    @Test
    @Transactional
    void fullUpdateTCountryCodeWithPatch() throws Exception {
        // Initialize the database
        tCountryCodeRepository.saveAndFlush(tCountryCode);

        int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();

        // Update the tCountryCode using partial update
        TCountryCode partialUpdatedTCountryCode = new TCountryCode();
        partialUpdatedTCountryCode.setId(tCountryCode.getId());

        partialUpdatedTCountryCode
            .countryCode(UPDATED_COUNTRY_CODE)
            .countryName(UPDATED_COUNTRY_NAME)
            .countryNationality(UPDATED_COUNTRY_NATIONALITY)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .orgCustomerType(UPDATED_ORG_CUSTOMER_TYPE);

        restTCountryCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCountryCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCountryCode))
            )
            .andExpect(status().isOk());

        // Validate the TCountryCode in the database
        List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
        assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
        TCountryCode testTCountryCode = tCountryCodeList.get(tCountryCodeList.size() - 1);
        assertThat(testTCountryCode.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testTCountryCode.getCountryName()).isEqualTo(UPDATED_COUNTRY_NAME);
        assertThat(testTCountryCode.getCountryNationality()).isEqualTo(UPDATED_COUNTRY_NATIONALITY);
        assertThat(testTCountryCode.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTCountryCode.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTCountryCode.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTCountryCode.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testTCountryCode.getOrgCustomerType()).isEqualTo(UPDATED_ORG_CUSTOMER_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingTCountryCode() throws Exception {
        int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();
        tCountryCode.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCountryCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCountryCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCountryCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCountryCode in the database
        List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
        assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCountryCode() throws Exception {
        int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();
        tCountryCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCountryCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCountryCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCountryCode in the database
        List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
        assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCountryCode() throws Exception {
        int databaseSizeBeforeUpdate = tCountryCodeRepository.findAll().size();
        tCountryCode.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCountryCodeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCountryCode))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCountryCode in the database
        List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
        assertThat(tCountryCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCountryCode() throws Exception {
        // Initialize the database
        tCountryCodeRepository.saveAndFlush(tCountryCode);

        int databaseSizeBeforeDelete = tCountryCodeRepository.findAll().size();

        // Delete the tCountryCode
        restTCountryCodeMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCountryCode.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCountryCode> tCountryCodeList = tCountryCodeRepository.findAll();
        assertThat(tCountryCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
