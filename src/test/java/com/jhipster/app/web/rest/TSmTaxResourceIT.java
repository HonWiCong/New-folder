package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static com.jhipster.app.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TSmTax;
import com.jhipster.app.domain.enumeration.TaxStatus;
import com.jhipster.app.repository.TSmTaxRepository;
import java.math.BigDecimal;
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
 * Integration tests for the {@link TSmTaxResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TSmTaxResourceIT {

    private static final String DEFAULT_SM_TAX_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SM_TAX_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SM_TAX_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SM_TAX_DESCRIPTION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SM_TAX_PERCENTAGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SM_TAX_PERCENTAGE = new BigDecimal(2);

    private static final String DEFAULT_SM_TAX_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SM_TAX_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SM_TAX_GST_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SM_TAX_GST_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SM_TAX_GST_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SM_TAX_GST_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SM_TAX_COLLECTED_GL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SM_TAX_COLLECTED_GL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SM_TAX_COLLECTED_GL_DESC = "AAAAAAAAAA";
    private static final String UPDATED_SM_TAX_COLLECTED_GL_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_SM_TAX_COLLECTED_COST_CENTER = "AAAAAAAAAA";
    private static final String UPDATED_SM_TAX_COLLECTED_COST_CENTER = "BBBBBBBBBB";

    private static final String DEFAULT_SM_TAX_PAID_GL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SM_TAX_PAID_GL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SM_TAX_PAID_GL_DESC = "AAAAAAAAAA";
    private static final String UPDATED_SM_TAX_PAID_GL_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_SM_TAX_PAID_COST_CENTER = "AAAAAAAAAA";
    private static final String UPDATED_SM_TAX_PAID_COST_CENTER = "BBBBBBBBBB";

    private static final String DEFAULT_SM_TAX_TAX_AUTHORITY = "AAAAAAAAAA";
    private static final String UPDATED_SM_TAX_TAX_AUTHORITY = "BBBBBBBBBB";

    private static final TaxStatus DEFAULT_SM_TAX_STATUS = TaxStatus.Y;
    private static final TaxStatus UPDATED_SM_TAX_STATUS = TaxStatus.N;

    private static final Integer DEFAULT_SM_TAX_ENTERED_BY = 1;
    private static final Integer UPDATED_SM_TAX_ENTERED_BY = 2;

    private static final ZonedDateTime DEFAULT_SM_TAX_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SM_TAX_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_SM_TAX_MODIFIED_BY = 1;
    private static final Integer UPDATED_SM_TAX_MODIFIED_BY = 2;

    private static final ZonedDateTime DEFAULT_SM_TAX_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SM_TAX_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_SM_TAX_CONFIRMED_BY = 1;
    private static final Integer UPDATED_SM_TAX_CONFIRMED_BY = 2;

    private static final ZonedDateTime DEFAULT_SM_TAX_CONFIRMED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SM_TAX_CONFIRMED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_SM_TAX_NARRATION = "AAAAAAAAAA";
    private static final String UPDATED_SM_TAX_NARRATION = "BBBBBBBBBB";

    private static final String DEFAULT_SM_TAX_DISPLAY = "AAAAAAAAAA";
    private static final String UPDATED_SM_TAX_DISPLAY = "BBBBBBBBBB";

    private static final String DEFAULT_SM_TAX_RCM_FLAG = "AAAAAAAAAA";
    private static final String UPDATED_SM_TAX_RCM_FLAG = "BBBBBBBBBB";

    private static final String DEFAULT_SM_TAX_CGA = "AAAAAAAAAA";
    private static final String UPDATED_SM_TAX_CGA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/t-sm-taxes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TSmTaxRepository tSmTaxRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTSmTaxMockMvc;

    private TSmTax tSmTax;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TSmTax createEntity(EntityManager em) {
        TSmTax tSmTax = new TSmTax()
            .smTaxCode(DEFAULT_SM_TAX_CODE)
            .smTaxDescription(DEFAULT_SM_TAX_DESCRIPTION)
            .smTaxPercentage(DEFAULT_SM_TAX_PERCENTAGE)
            .smTaxType(DEFAULT_SM_TAX_TYPE)
            .smTaxGstCode(DEFAULT_SM_TAX_GST_CODE)
            .smTaxGstType(DEFAULT_SM_TAX_GST_TYPE)
            .smTaxCollectedGlCode(DEFAULT_SM_TAX_COLLECTED_GL_CODE)
            .smTaxCollectedGlDesc(DEFAULT_SM_TAX_COLLECTED_GL_DESC)
            .smTaxCollectedCostCenter(DEFAULT_SM_TAX_COLLECTED_COST_CENTER)
            .smTaxPaidGlCode(DEFAULT_SM_TAX_PAID_GL_CODE)
            .smTaxPaidGlDesc(DEFAULT_SM_TAX_PAID_GL_DESC)
            .smTaxPaidCostCenter(DEFAULT_SM_TAX_PAID_COST_CENTER)
            .smTaxTaxAuthority(DEFAULT_SM_TAX_TAX_AUTHORITY)
            .smTaxStatus(DEFAULT_SM_TAX_STATUS)
            .smTaxEnteredBy(DEFAULT_SM_TAX_ENTERED_BY)
            .smTaxEnteredDate(DEFAULT_SM_TAX_ENTERED_DATE)
            .smTaxModifiedBy(DEFAULT_SM_TAX_MODIFIED_BY)
            .smTaxModifiedDate(DEFAULT_SM_TAX_MODIFIED_DATE)
            .smTaxConfirmedBy(DEFAULT_SM_TAX_CONFIRMED_BY)
            .smTaxConfirmedDate(DEFAULT_SM_TAX_CONFIRMED_DATE)
            .smTaxNarration(DEFAULT_SM_TAX_NARRATION)
            .smTaxDisplay(DEFAULT_SM_TAX_DISPLAY)
            .smTaxRcmFlag(DEFAULT_SM_TAX_RCM_FLAG)
            .smTaxCga(DEFAULT_SM_TAX_CGA);
        return tSmTax;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TSmTax createUpdatedEntity(EntityManager em) {
        TSmTax tSmTax = new TSmTax()
            .smTaxCode(UPDATED_SM_TAX_CODE)
            .smTaxDescription(UPDATED_SM_TAX_DESCRIPTION)
            .smTaxPercentage(UPDATED_SM_TAX_PERCENTAGE)
            .smTaxType(UPDATED_SM_TAX_TYPE)
            .smTaxGstCode(UPDATED_SM_TAX_GST_CODE)
            .smTaxGstType(UPDATED_SM_TAX_GST_TYPE)
            .smTaxCollectedGlCode(UPDATED_SM_TAX_COLLECTED_GL_CODE)
            .smTaxCollectedGlDesc(UPDATED_SM_TAX_COLLECTED_GL_DESC)
            .smTaxCollectedCostCenter(UPDATED_SM_TAX_COLLECTED_COST_CENTER)
            .smTaxPaidGlCode(UPDATED_SM_TAX_PAID_GL_CODE)
            .smTaxPaidGlDesc(UPDATED_SM_TAX_PAID_GL_DESC)
            .smTaxPaidCostCenter(UPDATED_SM_TAX_PAID_COST_CENTER)
            .smTaxTaxAuthority(UPDATED_SM_TAX_TAX_AUTHORITY)
            .smTaxStatus(UPDATED_SM_TAX_STATUS)
            .smTaxEnteredBy(UPDATED_SM_TAX_ENTERED_BY)
            .smTaxEnteredDate(UPDATED_SM_TAX_ENTERED_DATE)
            .smTaxModifiedBy(UPDATED_SM_TAX_MODIFIED_BY)
            .smTaxModifiedDate(UPDATED_SM_TAX_MODIFIED_DATE)
            .smTaxConfirmedBy(UPDATED_SM_TAX_CONFIRMED_BY)
            .smTaxConfirmedDate(UPDATED_SM_TAX_CONFIRMED_DATE)
            .smTaxNarration(UPDATED_SM_TAX_NARRATION)
            .smTaxDisplay(UPDATED_SM_TAX_DISPLAY)
            .smTaxRcmFlag(UPDATED_SM_TAX_RCM_FLAG)
            .smTaxCga(UPDATED_SM_TAX_CGA);
        return tSmTax;
    }

    @BeforeEach
    public void initTest() {
        tSmTax = createEntity(em);
    }

    @Test
    @Transactional
    void createTSmTax() throws Exception {
        int databaseSizeBeforeCreate = tSmTaxRepository.findAll().size();
        // Create the TSmTax
        restTSmTaxMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tSmTax)))
            .andExpect(status().isCreated());

        // Validate the TSmTax in the database
        List<TSmTax> tSmTaxList = tSmTaxRepository.findAll();
        assertThat(tSmTaxList).hasSize(databaseSizeBeforeCreate + 1);
        TSmTax testTSmTax = tSmTaxList.get(tSmTaxList.size() - 1);
        assertThat(testTSmTax.getSmTaxCode()).isEqualTo(DEFAULT_SM_TAX_CODE);
        assertThat(testTSmTax.getSmTaxDescription()).isEqualTo(DEFAULT_SM_TAX_DESCRIPTION);
        assertThat(testTSmTax.getSmTaxPercentage()).isEqualByComparingTo(DEFAULT_SM_TAX_PERCENTAGE);
        assertThat(testTSmTax.getSmTaxType()).isEqualTo(DEFAULT_SM_TAX_TYPE);
        assertThat(testTSmTax.getSmTaxGstCode()).isEqualTo(DEFAULT_SM_TAX_GST_CODE);
        assertThat(testTSmTax.getSmTaxGstType()).isEqualTo(DEFAULT_SM_TAX_GST_TYPE);
        assertThat(testTSmTax.getSmTaxCollectedGlCode()).isEqualTo(DEFAULT_SM_TAX_COLLECTED_GL_CODE);
        assertThat(testTSmTax.getSmTaxCollectedGlDesc()).isEqualTo(DEFAULT_SM_TAX_COLLECTED_GL_DESC);
        assertThat(testTSmTax.getSmTaxCollectedCostCenter()).isEqualTo(DEFAULT_SM_TAX_COLLECTED_COST_CENTER);
        assertThat(testTSmTax.getSmTaxPaidGlCode()).isEqualTo(DEFAULT_SM_TAX_PAID_GL_CODE);
        assertThat(testTSmTax.getSmTaxPaidGlDesc()).isEqualTo(DEFAULT_SM_TAX_PAID_GL_DESC);
        assertThat(testTSmTax.getSmTaxPaidCostCenter()).isEqualTo(DEFAULT_SM_TAX_PAID_COST_CENTER);
        assertThat(testTSmTax.getSmTaxTaxAuthority()).isEqualTo(DEFAULT_SM_TAX_TAX_AUTHORITY);
        assertThat(testTSmTax.getSmTaxStatus()).isEqualTo(DEFAULT_SM_TAX_STATUS);
        assertThat(testTSmTax.getSmTaxEnteredBy()).isEqualTo(DEFAULT_SM_TAX_ENTERED_BY);
        assertThat(testTSmTax.getSmTaxEnteredDate()).isEqualTo(DEFAULT_SM_TAX_ENTERED_DATE);
        assertThat(testTSmTax.getSmTaxModifiedBy()).isEqualTo(DEFAULT_SM_TAX_MODIFIED_BY);
        assertThat(testTSmTax.getSmTaxModifiedDate()).isEqualTo(DEFAULT_SM_TAX_MODIFIED_DATE);
        assertThat(testTSmTax.getSmTaxConfirmedBy()).isEqualTo(DEFAULT_SM_TAX_CONFIRMED_BY);
        assertThat(testTSmTax.getSmTaxConfirmedDate()).isEqualTo(DEFAULT_SM_TAX_CONFIRMED_DATE);
        assertThat(testTSmTax.getSmTaxNarration()).isEqualTo(DEFAULT_SM_TAX_NARRATION);
        assertThat(testTSmTax.getSmTaxDisplay()).isEqualTo(DEFAULT_SM_TAX_DISPLAY);
        assertThat(testTSmTax.getSmTaxRcmFlag()).isEqualTo(DEFAULT_SM_TAX_RCM_FLAG);
        assertThat(testTSmTax.getSmTaxCga()).isEqualTo(DEFAULT_SM_TAX_CGA);
    }

    @Test
    @Transactional
    void createTSmTaxWithExistingId() throws Exception {
        // Create the TSmTax with an existing ID
        tSmTax.setId(1L);

        int databaseSizeBeforeCreate = tSmTaxRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTSmTaxMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tSmTax)))
            .andExpect(status().isBadRequest());

        // Validate the TSmTax in the database
        List<TSmTax> tSmTaxList = tSmTaxRepository.findAll();
        assertThat(tSmTaxList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTSmTaxes() throws Exception {
        // Initialize the database
        tSmTaxRepository.saveAndFlush(tSmTax);

        // Get all the tSmTaxList
        restTSmTaxMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tSmTax.getId().intValue())))
            .andExpect(jsonPath("$.[*].smTaxCode").value(hasItem(DEFAULT_SM_TAX_CODE)))
            .andExpect(jsonPath("$.[*].smTaxDescription").value(hasItem(DEFAULT_SM_TAX_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].smTaxPercentage").value(hasItem(sameNumber(DEFAULT_SM_TAX_PERCENTAGE))))
            .andExpect(jsonPath("$.[*].smTaxType").value(hasItem(DEFAULT_SM_TAX_TYPE)))
            .andExpect(jsonPath("$.[*].smTaxGstCode").value(hasItem(DEFAULT_SM_TAX_GST_CODE)))
            .andExpect(jsonPath("$.[*].smTaxGstType").value(hasItem(DEFAULT_SM_TAX_GST_TYPE)))
            .andExpect(jsonPath("$.[*].smTaxCollectedGlCode").value(hasItem(DEFAULT_SM_TAX_COLLECTED_GL_CODE)))
            .andExpect(jsonPath("$.[*].smTaxCollectedGlDesc").value(hasItem(DEFAULT_SM_TAX_COLLECTED_GL_DESC)))
            .andExpect(jsonPath("$.[*].smTaxCollectedCostCenter").value(hasItem(DEFAULT_SM_TAX_COLLECTED_COST_CENTER)))
            .andExpect(jsonPath("$.[*].smTaxPaidGlCode").value(hasItem(DEFAULT_SM_TAX_PAID_GL_CODE)))
            .andExpect(jsonPath("$.[*].smTaxPaidGlDesc").value(hasItem(DEFAULT_SM_TAX_PAID_GL_DESC)))
            .andExpect(jsonPath("$.[*].smTaxPaidCostCenter").value(hasItem(DEFAULT_SM_TAX_PAID_COST_CENTER)))
            .andExpect(jsonPath("$.[*].smTaxTaxAuthority").value(hasItem(DEFAULT_SM_TAX_TAX_AUTHORITY)))
            .andExpect(jsonPath("$.[*].smTaxStatus").value(hasItem(DEFAULT_SM_TAX_STATUS.toString())))
            .andExpect(jsonPath("$.[*].smTaxEnteredBy").value(hasItem(DEFAULT_SM_TAX_ENTERED_BY)))
            .andExpect(jsonPath("$.[*].smTaxEnteredDate").value(hasItem(sameInstant(DEFAULT_SM_TAX_ENTERED_DATE))))
            .andExpect(jsonPath("$.[*].smTaxModifiedBy").value(hasItem(DEFAULT_SM_TAX_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].smTaxModifiedDate").value(hasItem(sameInstant(DEFAULT_SM_TAX_MODIFIED_DATE))))
            .andExpect(jsonPath("$.[*].smTaxConfirmedBy").value(hasItem(DEFAULT_SM_TAX_CONFIRMED_BY)))
            .andExpect(jsonPath("$.[*].smTaxConfirmedDate").value(hasItem(sameInstant(DEFAULT_SM_TAX_CONFIRMED_DATE))))
            .andExpect(jsonPath("$.[*].smTaxNarration").value(hasItem(DEFAULT_SM_TAX_NARRATION)))
            .andExpect(jsonPath("$.[*].smTaxDisplay").value(hasItem(DEFAULT_SM_TAX_DISPLAY)))
            .andExpect(jsonPath("$.[*].smTaxRcmFlag").value(hasItem(DEFAULT_SM_TAX_RCM_FLAG)))
            .andExpect(jsonPath("$.[*].smTaxCga").value(hasItem(DEFAULT_SM_TAX_CGA)));
    }

    @Test
    @Transactional
    void getTSmTax() throws Exception {
        // Initialize the database
        tSmTaxRepository.saveAndFlush(tSmTax);

        // Get the tSmTax
        restTSmTaxMockMvc
            .perform(get(ENTITY_API_URL_ID, tSmTax.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tSmTax.getId().intValue()))
            .andExpect(jsonPath("$.smTaxCode").value(DEFAULT_SM_TAX_CODE))
            .andExpect(jsonPath("$.smTaxDescription").value(DEFAULT_SM_TAX_DESCRIPTION))
            .andExpect(jsonPath("$.smTaxPercentage").value(sameNumber(DEFAULT_SM_TAX_PERCENTAGE)))
            .andExpect(jsonPath("$.smTaxType").value(DEFAULT_SM_TAX_TYPE))
            .andExpect(jsonPath("$.smTaxGstCode").value(DEFAULT_SM_TAX_GST_CODE))
            .andExpect(jsonPath("$.smTaxGstType").value(DEFAULT_SM_TAX_GST_TYPE))
            .andExpect(jsonPath("$.smTaxCollectedGlCode").value(DEFAULT_SM_TAX_COLLECTED_GL_CODE))
            .andExpect(jsonPath("$.smTaxCollectedGlDesc").value(DEFAULT_SM_TAX_COLLECTED_GL_DESC))
            .andExpect(jsonPath("$.smTaxCollectedCostCenter").value(DEFAULT_SM_TAX_COLLECTED_COST_CENTER))
            .andExpect(jsonPath("$.smTaxPaidGlCode").value(DEFAULT_SM_TAX_PAID_GL_CODE))
            .andExpect(jsonPath("$.smTaxPaidGlDesc").value(DEFAULT_SM_TAX_PAID_GL_DESC))
            .andExpect(jsonPath("$.smTaxPaidCostCenter").value(DEFAULT_SM_TAX_PAID_COST_CENTER))
            .andExpect(jsonPath("$.smTaxTaxAuthority").value(DEFAULT_SM_TAX_TAX_AUTHORITY))
            .andExpect(jsonPath("$.smTaxStatus").value(DEFAULT_SM_TAX_STATUS.toString()))
            .andExpect(jsonPath("$.smTaxEnteredBy").value(DEFAULT_SM_TAX_ENTERED_BY))
            .andExpect(jsonPath("$.smTaxEnteredDate").value(sameInstant(DEFAULT_SM_TAX_ENTERED_DATE)))
            .andExpect(jsonPath("$.smTaxModifiedBy").value(DEFAULT_SM_TAX_MODIFIED_BY))
            .andExpect(jsonPath("$.smTaxModifiedDate").value(sameInstant(DEFAULT_SM_TAX_MODIFIED_DATE)))
            .andExpect(jsonPath("$.smTaxConfirmedBy").value(DEFAULT_SM_TAX_CONFIRMED_BY))
            .andExpect(jsonPath("$.smTaxConfirmedDate").value(sameInstant(DEFAULT_SM_TAX_CONFIRMED_DATE)))
            .andExpect(jsonPath("$.smTaxNarration").value(DEFAULT_SM_TAX_NARRATION))
            .andExpect(jsonPath("$.smTaxDisplay").value(DEFAULT_SM_TAX_DISPLAY))
            .andExpect(jsonPath("$.smTaxRcmFlag").value(DEFAULT_SM_TAX_RCM_FLAG))
            .andExpect(jsonPath("$.smTaxCga").value(DEFAULT_SM_TAX_CGA));
    }

    @Test
    @Transactional
    void getNonExistingTSmTax() throws Exception {
        // Get the tSmTax
        restTSmTaxMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTSmTax() throws Exception {
        // Initialize the database
        tSmTaxRepository.saveAndFlush(tSmTax);

        int databaseSizeBeforeUpdate = tSmTaxRepository.findAll().size();

        // Update the tSmTax
        TSmTax updatedTSmTax = tSmTaxRepository.findById(tSmTax.getId()).get();
        // Disconnect from session so that the updates on updatedTSmTax are not directly saved in db
        em.detach(updatedTSmTax);
        updatedTSmTax
            .smTaxCode(UPDATED_SM_TAX_CODE)
            .smTaxDescription(UPDATED_SM_TAX_DESCRIPTION)
            .smTaxPercentage(UPDATED_SM_TAX_PERCENTAGE)
            .smTaxType(UPDATED_SM_TAX_TYPE)
            .smTaxGstCode(UPDATED_SM_TAX_GST_CODE)
            .smTaxGstType(UPDATED_SM_TAX_GST_TYPE)
            .smTaxCollectedGlCode(UPDATED_SM_TAX_COLLECTED_GL_CODE)
            .smTaxCollectedGlDesc(UPDATED_SM_TAX_COLLECTED_GL_DESC)
            .smTaxCollectedCostCenter(UPDATED_SM_TAX_COLLECTED_COST_CENTER)
            .smTaxPaidGlCode(UPDATED_SM_TAX_PAID_GL_CODE)
            .smTaxPaidGlDesc(UPDATED_SM_TAX_PAID_GL_DESC)
            .smTaxPaidCostCenter(UPDATED_SM_TAX_PAID_COST_CENTER)
            .smTaxTaxAuthority(UPDATED_SM_TAX_TAX_AUTHORITY)
            .smTaxStatus(UPDATED_SM_TAX_STATUS)
            .smTaxEnteredBy(UPDATED_SM_TAX_ENTERED_BY)
            .smTaxEnteredDate(UPDATED_SM_TAX_ENTERED_DATE)
            .smTaxModifiedBy(UPDATED_SM_TAX_MODIFIED_BY)
            .smTaxModifiedDate(UPDATED_SM_TAX_MODIFIED_DATE)
            .smTaxConfirmedBy(UPDATED_SM_TAX_CONFIRMED_BY)
            .smTaxConfirmedDate(UPDATED_SM_TAX_CONFIRMED_DATE)
            .smTaxNarration(UPDATED_SM_TAX_NARRATION)
            .smTaxDisplay(UPDATED_SM_TAX_DISPLAY)
            .smTaxRcmFlag(UPDATED_SM_TAX_RCM_FLAG)
            .smTaxCga(UPDATED_SM_TAX_CGA);

        restTSmTaxMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTSmTax.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTSmTax))
            )
            .andExpect(status().isOk());

        // Validate the TSmTax in the database
        List<TSmTax> tSmTaxList = tSmTaxRepository.findAll();
        assertThat(tSmTaxList).hasSize(databaseSizeBeforeUpdate);
        TSmTax testTSmTax = tSmTaxList.get(tSmTaxList.size() - 1);
        assertThat(testTSmTax.getSmTaxCode()).isEqualTo(UPDATED_SM_TAX_CODE);
        assertThat(testTSmTax.getSmTaxDescription()).isEqualTo(UPDATED_SM_TAX_DESCRIPTION);
        assertThat(testTSmTax.getSmTaxPercentage()).isEqualByComparingTo(UPDATED_SM_TAX_PERCENTAGE);
        assertThat(testTSmTax.getSmTaxType()).isEqualTo(UPDATED_SM_TAX_TYPE);
        assertThat(testTSmTax.getSmTaxGstCode()).isEqualTo(UPDATED_SM_TAX_GST_CODE);
        assertThat(testTSmTax.getSmTaxGstType()).isEqualTo(UPDATED_SM_TAX_GST_TYPE);
        assertThat(testTSmTax.getSmTaxCollectedGlCode()).isEqualTo(UPDATED_SM_TAX_COLLECTED_GL_CODE);
        assertThat(testTSmTax.getSmTaxCollectedGlDesc()).isEqualTo(UPDATED_SM_TAX_COLLECTED_GL_DESC);
        assertThat(testTSmTax.getSmTaxCollectedCostCenter()).isEqualTo(UPDATED_SM_TAX_COLLECTED_COST_CENTER);
        assertThat(testTSmTax.getSmTaxPaidGlCode()).isEqualTo(UPDATED_SM_TAX_PAID_GL_CODE);
        assertThat(testTSmTax.getSmTaxPaidGlDesc()).isEqualTo(UPDATED_SM_TAX_PAID_GL_DESC);
        assertThat(testTSmTax.getSmTaxPaidCostCenter()).isEqualTo(UPDATED_SM_TAX_PAID_COST_CENTER);
        assertThat(testTSmTax.getSmTaxTaxAuthority()).isEqualTo(UPDATED_SM_TAX_TAX_AUTHORITY);
        assertThat(testTSmTax.getSmTaxStatus()).isEqualTo(UPDATED_SM_TAX_STATUS);
        assertThat(testTSmTax.getSmTaxEnteredBy()).isEqualTo(UPDATED_SM_TAX_ENTERED_BY);
        assertThat(testTSmTax.getSmTaxEnteredDate()).isEqualTo(UPDATED_SM_TAX_ENTERED_DATE);
        assertThat(testTSmTax.getSmTaxModifiedBy()).isEqualTo(UPDATED_SM_TAX_MODIFIED_BY);
        assertThat(testTSmTax.getSmTaxModifiedDate()).isEqualTo(UPDATED_SM_TAX_MODIFIED_DATE);
        assertThat(testTSmTax.getSmTaxConfirmedBy()).isEqualTo(UPDATED_SM_TAX_CONFIRMED_BY);
        assertThat(testTSmTax.getSmTaxConfirmedDate()).isEqualTo(UPDATED_SM_TAX_CONFIRMED_DATE);
        assertThat(testTSmTax.getSmTaxNarration()).isEqualTo(UPDATED_SM_TAX_NARRATION);
        assertThat(testTSmTax.getSmTaxDisplay()).isEqualTo(UPDATED_SM_TAX_DISPLAY);
        assertThat(testTSmTax.getSmTaxRcmFlag()).isEqualTo(UPDATED_SM_TAX_RCM_FLAG);
        assertThat(testTSmTax.getSmTaxCga()).isEqualTo(UPDATED_SM_TAX_CGA);
    }

    @Test
    @Transactional
    void putNonExistingTSmTax() throws Exception {
        int databaseSizeBeforeUpdate = tSmTaxRepository.findAll().size();
        tSmTax.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTSmTaxMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tSmTax.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tSmTax))
            )
            .andExpect(status().isBadRequest());

        // Validate the TSmTax in the database
        List<TSmTax> tSmTaxList = tSmTaxRepository.findAll();
        assertThat(tSmTaxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTSmTax() throws Exception {
        int databaseSizeBeforeUpdate = tSmTaxRepository.findAll().size();
        tSmTax.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTSmTaxMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tSmTax))
            )
            .andExpect(status().isBadRequest());

        // Validate the TSmTax in the database
        List<TSmTax> tSmTaxList = tSmTaxRepository.findAll();
        assertThat(tSmTaxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTSmTax() throws Exception {
        int databaseSizeBeforeUpdate = tSmTaxRepository.findAll().size();
        tSmTax.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTSmTaxMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tSmTax)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TSmTax in the database
        List<TSmTax> tSmTaxList = tSmTaxRepository.findAll();
        assertThat(tSmTaxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTSmTaxWithPatch() throws Exception {
        // Initialize the database
        tSmTaxRepository.saveAndFlush(tSmTax);

        int databaseSizeBeforeUpdate = tSmTaxRepository.findAll().size();

        // Update the tSmTax using partial update
        TSmTax partialUpdatedTSmTax = new TSmTax();
        partialUpdatedTSmTax.setId(tSmTax.getId());

        partialUpdatedTSmTax
            .smTaxCode(UPDATED_SM_TAX_CODE)
            .smTaxCollectedGlDesc(UPDATED_SM_TAX_COLLECTED_GL_DESC)
            .smTaxCollectedCostCenter(UPDATED_SM_TAX_COLLECTED_COST_CENTER)
            .smTaxPaidGlCode(UPDATED_SM_TAX_PAID_GL_CODE)
            .smTaxPaidGlDesc(UPDATED_SM_TAX_PAID_GL_DESC)
            .smTaxTaxAuthority(UPDATED_SM_TAX_TAX_AUTHORITY)
            .smTaxEnteredBy(UPDATED_SM_TAX_ENTERED_BY)
            .smTaxEnteredDate(UPDATED_SM_TAX_ENTERED_DATE)
            .smTaxModifiedBy(UPDATED_SM_TAX_MODIFIED_BY)
            .smTaxModifiedDate(UPDATED_SM_TAX_MODIFIED_DATE)
            .smTaxNarration(UPDATED_SM_TAX_NARRATION)
            .smTaxDisplay(UPDATED_SM_TAX_DISPLAY)
            .smTaxRcmFlag(UPDATED_SM_TAX_RCM_FLAG);

        restTSmTaxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTSmTax.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTSmTax))
            )
            .andExpect(status().isOk());

        // Validate the TSmTax in the database
        List<TSmTax> tSmTaxList = tSmTaxRepository.findAll();
        assertThat(tSmTaxList).hasSize(databaseSizeBeforeUpdate);
        TSmTax testTSmTax = tSmTaxList.get(tSmTaxList.size() - 1);
        assertThat(testTSmTax.getSmTaxCode()).isEqualTo(UPDATED_SM_TAX_CODE);
        assertThat(testTSmTax.getSmTaxDescription()).isEqualTo(DEFAULT_SM_TAX_DESCRIPTION);
        assertThat(testTSmTax.getSmTaxPercentage()).isEqualByComparingTo(DEFAULT_SM_TAX_PERCENTAGE);
        assertThat(testTSmTax.getSmTaxType()).isEqualTo(DEFAULT_SM_TAX_TYPE);
        assertThat(testTSmTax.getSmTaxGstCode()).isEqualTo(DEFAULT_SM_TAX_GST_CODE);
        assertThat(testTSmTax.getSmTaxGstType()).isEqualTo(DEFAULT_SM_TAX_GST_TYPE);
        assertThat(testTSmTax.getSmTaxCollectedGlCode()).isEqualTo(DEFAULT_SM_TAX_COLLECTED_GL_CODE);
        assertThat(testTSmTax.getSmTaxCollectedGlDesc()).isEqualTo(UPDATED_SM_TAX_COLLECTED_GL_DESC);
        assertThat(testTSmTax.getSmTaxCollectedCostCenter()).isEqualTo(UPDATED_SM_TAX_COLLECTED_COST_CENTER);
        assertThat(testTSmTax.getSmTaxPaidGlCode()).isEqualTo(UPDATED_SM_TAX_PAID_GL_CODE);
        assertThat(testTSmTax.getSmTaxPaidGlDesc()).isEqualTo(UPDATED_SM_TAX_PAID_GL_DESC);
        assertThat(testTSmTax.getSmTaxPaidCostCenter()).isEqualTo(DEFAULT_SM_TAX_PAID_COST_CENTER);
        assertThat(testTSmTax.getSmTaxTaxAuthority()).isEqualTo(UPDATED_SM_TAX_TAX_AUTHORITY);
        assertThat(testTSmTax.getSmTaxStatus()).isEqualTo(DEFAULT_SM_TAX_STATUS);
        assertThat(testTSmTax.getSmTaxEnteredBy()).isEqualTo(UPDATED_SM_TAX_ENTERED_BY);
        assertThat(testTSmTax.getSmTaxEnteredDate()).isEqualTo(UPDATED_SM_TAX_ENTERED_DATE);
        assertThat(testTSmTax.getSmTaxModifiedBy()).isEqualTo(UPDATED_SM_TAX_MODIFIED_BY);
        assertThat(testTSmTax.getSmTaxModifiedDate()).isEqualTo(UPDATED_SM_TAX_MODIFIED_DATE);
        assertThat(testTSmTax.getSmTaxConfirmedBy()).isEqualTo(DEFAULT_SM_TAX_CONFIRMED_BY);
        assertThat(testTSmTax.getSmTaxConfirmedDate()).isEqualTo(DEFAULT_SM_TAX_CONFIRMED_DATE);
        assertThat(testTSmTax.getSmTaxNarration()).isEqualTo(UPDATED_SM_TAX_NARRATION);
        assertThat(testTSmTax.getSmTaxDisplay()).isEqualTo(UPDATED_SM_TAX_DISPLAY);
        assertThat(testTSmTax.getSmTaxRcmFlag()).isEqualTo(UPDATED_SM_TAX_RCM_FLAG);
        assertThat(testTSmTax.getSmTaxCga()).isEqualTo(DEFAULT_SM_TAX_CGA);
    }

    @Test
    @Transactional
    void fullUpdateTSmTaxWithPatch() throws Exception {
        // Initialize the database
        tSmTaxRepository.saveAndFlush(tSmTax);

        int databaseSizeBeforeUpdate = tSmTaxRepository.findAll().size();

        // Update the tSmTax using partial update
        TSmTax partialUpdatedTSmTax = new TSmTax();
        partialUpdatedTSmTax.setId(tSmTax.getId());

        partialUpdatedTSmTax
            .smTaxCode(UPDATED_SM_TAX_CODE)
            .smTaxDescription(UPDATED_SM_TAX_DESCRIPTION)
            .smTaxPercentage(UPDATED_SM_TAX_PERCENTAGE)
            .smTaxType(UPDATED_SM_TAX_TYPE)
            .smTaxGstCode(UPDATED_SM_TAX_GST_CODE)
            .smTaxGstType(UPDATED_SM_TAX_GST_TYPE)
            .smTaxCollectedGlCode(UPDATED_SM_TAX_COLLECTED_GL_CODE)
            .smTaxCollectedGlDesc(UPDATED_SM_TAX_COLLECTED_GL_DESC)
            .smTaxCollectedCostCenter(UPDATED_SM_TAX_COLLECTED_COST_CENTER)
            .smTaxPaidGlCode(UPDATED_SM_TAX_PAID_GL_CODE)
            .smTaxPaidGlDesc(UPDATED_SM_TAX_PAID_GL_DESC)
            .smTaxPaidCostCenter(UPDATED_SM_TAX_PAID_COST_CENTER)
            .smTaxTaxAuthority(UPDATED_SM_TAX_TAX_AUTHORITY)
            .smTaxStatus(UPDATED_SM_TAX_STATUS)
            .smTaxEnteredBy(UPDATED_SM_TAX_ENTERED_BY)
            .smTaxEnteredDate(UPDATED_SM_TAX_ENTERED_DATE)
            .smTaxModifiedBy(UPDATED_SM_TAX_MODIFIED_BY)
            .smTaxModifiedDate(UPDATED_SM_TAX_MODIFIED_DATE)
            .smTaxConfirmedBy(UPDATED_SM_TAX_CONFIRMED_BY)
            .smTaxConfirmedDate(UPDATED_SM_TAX_CONFIRMED_DATE)
            .smTaxNarration(UPDATED_SM_TAX_NARRATION)
            .smTaxDisplay(UPDATED_SM_TAX_DISPLAY)
            .smTaxRcmFlag(UPDATED_SM_TAX_RCM_FLAG)
            .smTaxCga(UPDATED_SM_TAX_CGA);

        restTSmTaxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTSmTax.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTSmTax))
            )
            .andExpect(status().isOk());

        // Validate the TSmTax in the database
        List<TSmTax> tSmTaxList = tSmTaxRepository.findAll();
        assertThat(tSmTaxList).hasSize(databaseSizeBeforeUpdate);
        TSmTax testTSmTax = tSmTaxList.get(tSmTaxList.size() - 1);
        assertThat(testTSmTax.getSmTaxCode()).isEqualTo(UPDATED_SM_TAX_CODE);
        assertThat(testTSmTax.getSmTaxDescription()).isEqualTo(UPDATED_SM_TAX_DESCRIPTION);
        assertThat(testTSmTax.getSmTaxPercentage()).isEqualByComparingTo(UPDATED_SM_TAX_PERCENTAGE);
        assertThat(testTSmTax.getSmTaxType()).isEqualTo(UPDATED_SM_TAX_TYPE);
        assertThat(testTSmTax.getSmTaxGstCode()).isEqualTo(UPDATED_SM_TAX_GST_CODE);
        assertThat(testTSmTax.getSmTaxGstType()).isEqualTo(UPDATED_SM_TAX_GST_TYPE);
        assertThat(testTSmTax.getSmTaxCollectedGlCode()).isEqualTo(UPDATED_SM_TAX_COLLECTED_GL_CODE);
        assertThat(testTSmTax.getSmTaxCollectedGlDesc()).isEqualTo(UPDATED_SM_TAX_COLLECTED_GL_DESC);
        assertThat(testTSmTax.getSmTaxCollectedCostCenter()).isEqualTo(UPDATED_SM_TAX_COLLECTED_COST_CENTER);
        assertThat(testTSmTax.getSmTaxPaidGlCode()).isEqualTo(UPDATED_SM_TAX_PAID_GL_CODE);
        assertThat(testTSmTax.getSmTaxPaidGlDesc()).isEqualTo(UPDATED_SM_TAX_PAID_GL_DESC);
        assertThat(testTSmTax.getSmTaxPaidCostCenter()).isEqualTo(UPDATED_SM_TAX_PAID_COST_CENTER);
        assertThat(testTSmTax.getSmTaxTaxAuthority()).isEqualTo(UPDATED_SM_TAX_TAX_AUTHORITY);
        assertThat(testTSmTax.getSmTaxStatus()).isEqualTo(UPDATED_SM_TAX_STATUS);
        assertThat(testTSmTax.getSmTaxEnteredBy()).isEqualTo(UPDATED_SM_TAX_ENTERED_BY);
        assertThat(testTSmTax.getSmTaxEnteredDate()).isEqualTo(UPDATED_SM_TAX_ENTERED_DATE);
        assertThat(testTSmTax.getSmTaxModifiedBy()).isEqualTo(UPDATED_SM_TAX_MODIFIED_BY);
        assertThat(testTSmTax.getSmTaxModifiedDate()).isEqualTo(UPDATED_SM_TAX_MODIFIED_DATE);
        assertThat(testTSmTax.getSmTaxConfirmedBy()).isEqualTo(UPDATED_SM_TAX_CONFIRMED_BY);
        assertThat(testTSmTax.getSmTaxConfirmedDate()).isEqualTo(UPDATED_SM_TAX_CONFIRMED_DATE);
        assertThat(testTSmTax.getSmTaxNarration()).isEqualTo(UPDATED_SM_TAX_NARRATION);
        assertThat(testTSmTax.getSmTaxDisplay()).isEqualTo(UPDATED_SM_TAX_DISPLAY);
        assertThat(testTSmTax.getSmTaxRcmFlag()).isEqualTo(UPDATED_SM_TAX_RCM_FLAG);
        assertThat(testTSmTax.getSmTaxCga()).isEqualTo(UPDATED_SM_TAX_CGA);
    }

    @Test
    @Transactional
    void patchNonExistingTSmTax() throws Exception {
        int databaseSizeBeforeUpdate = tSmTaxRepository.findAll().size();
        tSmTax.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTSmTaxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tSmTax.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tSmTax))
            )
            .andExpect(status().isBadRequest());

        // Validate the TSmTax in the database
        List<TSmTax> tSmTaxList = tSmTaxRepository.findAll();
        assertThat(tSmTaxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTSmTax() throws Exception {
        int databaseSizeBeforeUpdate = tSmTaxRepository.findAll().size();
        tSmTax.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTSmTaxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tSmTax))
            )
            .andExpect(status().isBadRequest());

        // Validate the TSmTax in the database
        List<TSmTax> tSmTaxList = tSmTaxRepository.findAll();
        assertThat(tSmTaxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTSmTax() throws Exception {
        int databaseSizeBeforeUpdate = tSmTaxRepository.findAll().size();
        tSmTax.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTSmTaxMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tSmTax)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TSmTax in the database
        List<TSmTax> tSmTaxList = tSmTaxRepository.findAll();
        assertThat(tSmTaxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTSmTax() throws Exception {
        // Initialize the database
        tSmTaxRepository.saveAndFlush(tSmTax);

        int databaseSizeBeforeDelete = tSmTaxRepository.findAll().size();

        // Delete the tSmTax
        restTSmTaxMockMvc
            .perform(delete(ENTITY_API_URL_ID, tSmTax.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TSmTax> tSmTaxList = tSmTaxRepository.findAll();
        assertThat(tSmTaxList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
