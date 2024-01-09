package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TSupplierCategory;
import com.jhipster.app.repository.TSupplierCategoryRepository;
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
 * Integration tests for the {@link TSupplierCategoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TSupplierCategoryResourceIT {

    private static final String DEFAULT_SPC_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_SPC_CATEGORY = "BBBBBBBBBB";

    private static final Integer DEFAULT_ENTERED_BY = 1;
    private static final Integer UPDATED_ENTERED_BY = 2;

    private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_MODIFY_BY = 1;
    private static final Integer UPDATED_MODIFY_BY = 2;

    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/t-supplier-categories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TSupplierCategoryRepository tSupplierCategoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTSupplierCategoryMockMvc;

    private TSupplierCategory tSupplierCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TSupplierCategory createEntity(EntityManager em) {
        TSupplierCategory tSupplierCategory = new TSupplierCategory()
            .spcCategory(DEFAULT_SPC_CATEGORY)
            .enteredBy(DEFAULT_ENTERED_BY)
            .enteredDate(DEFAULT_ENTERED_DATE)
            .modifyBy(DEFAULT_MODIFY_BY)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return tSupplierCategory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TSupplierCategory createUpdatedEntity(EntityManager em) {
        TSupplierCategory tSupplierCategory = new TSupplierCategory()
            .spcCategory(UPDATED_SPC_CATEGORY)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifyBy(UPDATED_MODIFY_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return tSupplierCategory;
    }

    @BeforeEach
    public void initTest() {
        tSupplierCategory = createEntity(em);
    }

    @Test
    @Transactional
    void createTSupplierCategory() throws Exception {
        int databaseSizeBeforeCreate = tSupplierCategoryRepository.findAll().size();
        // Create the TSupplierCategory
        restTSupplierCategoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tSupplierCategory))
            )
            .andExpect(status().isCreated());

        // Validate the TSupplierCategory in the database
        List<TSupplierCategory> tSupplierCategoryList = tSupplierCategoryRepository.findAll();
        assertThat(tSupplierCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        TSupplierCategory testTSupplierCategory = tSupplierCategoryList.get(tSupplierCategoryList.size() - 1);
        assertThat(testTSupplierCategory.getSpcCategory()).isEqualTo(DEFAULT_SPC_CATEGORY);
        assertThat(testTSupplierCategory.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
        assertThat(testTSupplierCategory.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
        assertThat(testTSupplierCategory.getModifyBy()).isEqualTo(DEFAULT_MODIFY_BY);
        assertThat(testTSupplierCategory.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createTSupplierCategoryWithExistingId() throws Exception {
        // Create the TSupplierCategory with an existing ID
        tSupplierCategory.setId(1L);

        int databaseSizeBeforeCreate = tSupplierCategoryRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTSupplierCategoryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tSupplierCategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the TSupplierCategory in the database
        List<TSupplierCategory> tSupplierCategoryList = tSupplierCategoryRepository.findAll();
        assertThat(tSupplierCategoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTSupplierCategories() throws Exception {
        // Initialize the database
        tSupplierCategoryRepository.saveAndFlush(tSupplierCategory);

        // Get all the tSupplierCategoryList
        restTSupplierCategoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tSupplierCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].spcCategory").value(hasItem(DEFAULT_SPC_CATEGORY)))
            .andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
            .andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
            .andExpect(jsonPath("$.[*].modifyBy").value(hasItem(DEFAULT_MODIFY_BY)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
    }

    @Test
    @Transactional
    void getTSupplierCategory() throws Exception {
        // Initialize the database
        tSupplierCategoryRepository.saveAndFlush(tSupplierCategory);

        // Get the tSupplierCategory
        restTSupplierCategoryMockMvc
            .perform(get(ENTITY_API_URL_ID, tSupplierCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tSupplierCategory.getId().intValue()))
            .andExpect(jsonPath("$.spcCategory").value(DEFAULT_SPC_CATEGORY))
            .andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
            .andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
            .andExpect(jsonPath("$.modifyBy").value(DEFAULT_MODIFY_BY))
            .andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
    }

    @Test
    @Transactional
    void getNonExistingTSupplierCategory() throws Exception {
        // Get the tSupplierCategory
        restTSupplierCategoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTSupplierCategory() throws Exception {
        // Initialize the database
        tSupplierCategoryRepository.saveAndFlush(tSupplierCategory);

        int databaseSizeBeforeUpdate = tSupplierCategoryRepository.findAll().size();

        // Update the tSupplierCategory
        TSupplierCategory updatedTSupplierCategory = tSupplierCategoryRepository.findById(tSupplierCategory.getId()).get();
        // Disconnect from session so that the updates on updatedTSupplierCategory are not directly saved in db
        em.detach(updatedTSupplierCategory);
        updatedTSupplierCategory
            .spcCategory(UPDATED_SPC_CATEGORY)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifyBy(UPDATED_MODIFY_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTSupplierCategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTSupplierCategory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTSupplierCategory))
            )
            .andExpect(status().isOk());

        // Validate the TSupplierCategory in the database
        List<TSupplierCategory> tSupplierCategoryList = tSupplierCategoryRepository.findAll();
        assertThat(tSupplierCategoryList).hasSize(databaseSizeBeforeUpdate);
        TSupplierCategory testTSupplierCategory = tSupplierCategoryList.get(tSupplierCategoryList.size() - 1);
        assertThat(testTSupplierCategory.getSpcCategory()).isEqualTo(UPDATED_SPC_CATEGORY);
        assertThat(testTSupplierCategory.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTSupplierCategory.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTSupplierCategory.getModifyBy()).isEqualTo(UPDATED_MODIFY_BY);
        assertThat(testTSupplierCategory.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingTSupplierCategory() throws Exception {
        int databaseSizeBeforeUpdate = tSupplierCategoryRepository.findAll().size();
        tSupplierCategory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTSupplierCategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tSupplierCategory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tSupplierCategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the TSupplierCategory in the database
        List<TSupplierCategory> tSupplierCategoryList = tSupplierCategoryRepository.findAll();
        assertThat(tSupplierCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTSupplierCategory() throws Exception {
        int databaseSizeBeforeUpdate = tSupplierCategoryRepository.findAll().size();
        tSupplierCategory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTSupplierCategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tSupplierCategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the TSupplierCategory in the database
        List<TSupplierCategory> tSupplierCategoryList = tSupplierCategoryRepository.findAll();
        assertThat(tSupplierCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTSupplierCategory() throws Exception {
        int databaseSizeBeforeUpdate = tSupplierCategoryRepository.findAll().size();
        tSupplierCategory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTSupplierCategoryMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tSupplierCategory))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TSupplierCategory in the database
        List<TSupplierCategory> tSupplierCategoryList = tSupplierCategoryRepository.findAll();
        assertThat(tSupplierCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTSupplierCategoryWithPatch() throws Exception {
        // Initialize the database
        tSupplierCategoryRepository.saveAndFlush(tSupplierCategory);

        int databaseSizeBeforeUpdate = tSupplierCategoryRepository.findAll().size();

        // Update the tSupplierCategory using partial update
        TSupplierCategory partialUpdatedTSupplierCategory = new TSupplierCategory();
        partialUpdatedTSupplierCategory.setId(tSupplierCategory.getId());

        partialUpdatedTSupplierCategory.spcCategory(UPDATED_SPC_CATEGORY).enteredDate(UPDATED_ENTERED_DATE).modifyBy(UPDATED_MODIFY_BY);

        restTSupplierCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTSupplierCategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTSupplierCategory))
            )
            .andExpect(status().isOk());

        // Validate the TSupplierCategory in the database
        List<TSupplierCategory> tSupplierCategoryList = tSupplierCategoryRepository.findAll();
        assertThat(tSupplierCategoryList).hasSize(databaseSizeBeforeUpdate);
        TSupplierCategory testTSupplierCategory = tSupplierCategoryList.get(tSupplierCategoryList.size() - 1);
        assertThat(testTSupplierCategory.getSpcCategory()).isEqualTo(UPDATED_SPC_CATEGORY);
        assertThat(testTSupplierCategory.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
        assertThat(testTSupplierCategory.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTSupplierCategory.getModifyBy()).isEqualTo(UPDATED_MODIFY_BY);
        assertThat(testTSupplierCategory.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateTSupplierCategoryWithPatch() throws Exception {
        // Initialize the database
        tSupplierCategoryRepository.saveAndFlush(tSupplierCategory);

        int databaseSizeBeforeUpdate = tSupplierCategoryRepository.findAll().size();

        // Update the tSupplierCategory using partial update
        TSupplierCategory partialUpdatedTSupplierCategory = new TSupplierCategory();
        partialUpdatedTSupplierCategory.setId(tSupplierCategory.getId());

        partialUpdatedTSupplierCategory
            .spcCategory(UPDATED_SPC_CATEGORY)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifyBy(UPDATED_MODIFY_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTSupplierCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTSupplierCategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTSupplierCategory))
            )
            .andExpect(status().isOk());

        // Validate the TSupplierCategory in the database
        List<TSupplierCategory> tSupplierCategoryList = tSupplierCategoryRepository.findAll();
        assertThat(tSupplierCategoryList).hasSize(databaseSizeBeforeUpdate);
        TSupplierCategory testTSupplierCategory = tSupplierCategoryList.get(tSupplierCategoryList.size() - 1);
        assertThat(testTSupplierCategory.getSpcCategory()).isEqualTo(UPDATED_SPC_CATEGORY);
        assertThat(testTSupplierCategory.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTSupplierCategory.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTSupplierCategory.getModifyBy()).isEqualTo(UPDATED_MODIFY_BY);
        assertThat(testTSupplierCategory.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingTSupplierCategory() throws Exception {
        int databaseSizeBeforeUpdate = tSupplierCategoryRepository.findAll().size();
        tSupplierCategory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTSupplierCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tSupplierCategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tSupplierCategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the TSupplierCategory in the database
        List<TSupplierCategory> tSupplierCategoryList = tSupplierCategoryRepository.findAll();
        assertThat(tSupplierCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTSupplierCategory() throws Exception {
        int databaseSizeBeforeUpdate = tSupplierCategoryRepository.findAll().size();
        tSupplierCategory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTSupplierCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tSupplierCategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the TSupplierCategory in the database
        List<TSupplierCategory> tSupplierCategoryList = tSupplierCategoryRepository.findAll();
        assertThat(tSupplierCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTSupplierCategory() throws Exception {
        int databaseSizeBeforeUpdate = tSupplierCategoryRepository.findAll().size();
        tSupplierCategory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTSupplierCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tSupplierCategory))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TSupplierCategory in the database
        List<TSupplierCategory> tSupplierCategoryList = tSupplierCategoryRepository.findAll();
        assertThat(tSupplierCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTSupplierCategory() throws Exception {
        // Initialize the database
        tSupplierCategoryRepository.saveAndFlush(tSupplierCategory);

        int databaseSizeBeforeDelete = tSupplierCategoryRepository.findAll().size();

        // Delete the tSupplierCategory
        restTSupplierCategoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, tSupplierCategory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TSupplierCategory> tSupplierCategoryList = tSupplierCategoryRepository.findAll();
        assertThat(tSupplierCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
