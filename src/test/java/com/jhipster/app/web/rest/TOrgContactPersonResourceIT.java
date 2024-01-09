package com.jhipster.app.web.rest;

import static com.jhipster.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.app.IntegrationTest;
import com.jhipster.app.domain.TOrgContactPerson;
import com.jhipster.app.repository.TOrgContactPersonRepository;
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
 * Integration tests for the {@link TOrgContactPersonResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TOrgContactPersonResourceIT {

    private static final Long DEFAULT_OCP_ORGCODEID = 1L;
    private static final Long UPDATED_OCP_ORGCODEID = 2L;

    private static final String DEFAULT_OCP_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_OCP_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_OCP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OCP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OCP_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_OCP_DESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_OCP_TELEPHONE_1 = "AAAAAAAAAA";
    private static final String UPDATED_OCP_TELEPHONE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_OCP_HANDPHONE = "AAAAAAAAAA";
    private static final String UPDATED_OCP_HANDPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_OCP_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_OCP_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_OCP_SECTOR = "AAAAAAAAAA";
    private static final String UPDATED_OCP_SECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_OCP_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_OCP_STATUS = "BBBBBBBBBB";

    private static final Integer DEFAULT_ENTERED_BY = 1;
    private static final Integer UPDATED_ENTERED_BY = 2;

    private static final ZonedDateTime DEFAULT_ENTERED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ENTERED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_MODIFIED_BY = 1;
    private static final Integer UPDATED_MODIFIED_BY = 2;

    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/t-org-contact-people";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TOrgContactPersonRepository tOrgContactPersonRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTOrgContactPersonMockMvc;

    private TOrgContactPerson tOrgContactPerson;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TOrgContactPerson createEntity(EntityManager em) {
        TOrgContactPerson tOrgContactPerson = new TOrgContactPerson()
            .ocpOrgcodeid(DEFAULT_OCP_ORGCODEID)
            .ocpTitle(DEFAULT_OCP_TITLE)
            .ocpName(DEFAULT_OCP_NAME)
            .ocpDesignation(DEFAULT_OCP_DESIGNATION)
            .ocpTelephone1(DEFAULT_OCP_TELEPHONE_1)
            .ocpHandphone(DEFAULT_OCP_HANDPHONE)
            .ocpMail(DEFAULT_OCP_MAIL)
            .ocpSector(DEFAULT_OCP_SECTOR)
            .ocpStatus(DEFAULT_OCP_STATUS)
            .enteredBy(DEFAULT_ENTERED_BY)
            .enteredDate(DEFAULT_ENTERED_DATE)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return tOrgContactPerson;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TOrgContactPerson createUpdatedEntity(EntityManager em) {
        TOrgContactPerson tOrgContactPerson = new TOrgContactPerson()
            .ocpOrgcodeid(UPDATED_OCP_ORGCODEID)
            .ocpTitle(UPDATED_OCP_TITLE)
            .ocpName(UPDATED_OCP_NAME)
            .ocpDesignation(UPDATED_OCP_DESIGNATION)
            .ocpTelephone1(UPDATED_OCP_TELEPHONE_1)
            .ocpHandphone(UPDATED_OCP_HANDPHONE)
            .ocpMail(UPDATED_OCP_MAIL)
            .ocpSector(UPDATED_OCP_SECTOR)
            .ocpStatus(UPDATED_OCP_STATUS)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return tOrgContactPerson;
    }

    @BeforeEach
    public void initTest() {
        tOrgContactPerson = createEntity(em);
    }

    @Test
    @Transactional
    void createTOrgContactPerson() throws Exception {
        int databaseSizeBeforeCreate = tOrgContactPersonRepository.findAll().size();
        // Create the TOrgContactPerson
        restTOrgContactPersonMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tOrgContactPerson))
            )
            .andExpect(status().isCreated());

        // Validate the TOrgContactPerson in the database
        List<TOrgContactPerson> tOrgContactPersonList = tOrgContactPersonRepository.findAll();
        assertThat(tOrgContactPersonList).hasSize(databaseSizeBeforeCreate + 1);
        TOrgContactPerson testTOrgContactPerson = tOrgContactPersonList.get(tOrgContactPersonList.size() - 1);
        assertThat(testTOrgContactPerson.getOcpOrgcodeid()).isEqualTo(DEFAULT_OCP_ORGCODEID);
        assertThat(testTOrgContactPerson.getOcpTitle()).isEqualTo(DEFAULT_OCP_TITLE);
        assertThat(testTOrgContactPerson.getOcpName()).isEqualTo(DEFAULT_OCP_NAME);
        assertThat(testTOrgContactPerson.getOcpDesignation()).isEqualTo(DEFAULT_OCP_DESIGNATION);
        assertThat(testTOrgContactPerson.getOcpTelephone1()).isEqualTo(DEFAULT_OCP_TELEPHONE_1);
        assertThat(testTOrgContactPerson.getOcpHandphone()).isEqualTo(DEFAULT_OCP_HANDPHONE);
        assertThat(testTOrgContactPerson.getOcpMail()).isEqualTo(DEFAULT_OCP_MAIL);
        assertThat(testTOrgContactPerson.getOcpSector()).isEqualTo(DEFAULT_OCP_SECTOR);
        assertThat(testTOrgContactPerson.getOcpStatus()).isEqualTo(DEFAULT_OCP_STATUS);
        assertThat(testTOrgContactPerson.getEnteredBy()).isEqualTo(DEFAULT_ENTERED_BY);
        assertThat(testTOrgContactPerson.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
        assertThat(testTOrgContactPerson.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testTOrgContactPerson.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createTOrgContactPersonWithExistingId() throws Exception {
        // Create the TOrgContactPerson with an existing ID
        tOrgContactPerson.setId(1L);

        int databaseSizeBeforeCreate = tOrgContactPersonRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTOrgContactPersonMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tOrgContactPerson))
            )
            .andExpect(status().isBadRequest());

        // Validate the TOrgContactPerson in the database
        List<TOrgContactPerson> tOrgContactPersonList = tOrgContactPersonRepository.findAll();
        assertThat(tOrgContactPersonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTOrgContactPeople() throws Exception {
        // Initialize the database
        tOrgContactPersonRepository.saveAndFlush(tOrgContactPerson);

        // Get all the tOrgContactPersonList
        restTOrgContactPersonMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tOrgContactPerson.getId().intValue())))
            .andExpect(jsonPath("$.[*].ocpOrgcodeid").value(hasItem(DEFAULT_OCP_ORGCODEID.intValue())))
            .andExpect(jsonPath("$.[*].ocpTitle").value(hasItem(DEFAULT_OCP_TITLE)))
            .andExpect(jsonPath("$.[*].ocpName").value(hasItem(DEFAULT_OCP_NAME)))
            .andExpect(jsonPath("$.[*].ocpDesignation").value(hasItem(DEFAULT_OCP_DESIGNATION)))
            .andExpect(jsonPath("$.[*].ocpTelephone1").value(hasItem(DEFAULT_OCP_TELEPHONE_1)))
            .andExpect(jsonPath("$.[*].ocpHandphone").value(hasItem(DEFAULT_OCP_HANDPHONE)))
            .andExpect(jsonPath("$.[*].ocpMail").value(hasItem(DEFAULT_OCP_MAIL)))
            .andExpect(jsonPath("$.[*].ocpSector").value(hasItem(DEFAULT_OCP_SECTOR)))
            .andExpect(jsonPath("$.[*].ocpStatus").value(hasItem(DEFAULT_OCP_STATUS)))
            .andExpect(jsonPath("$.[*].enteredBy").value(hasItem(DEFAULT_ENTERED_BY)))
            .andExpect(jsonPath("$.[*].enteredDate").value(hasItem(sameInstant(DEFAULT_ENTERED_DATE))))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(sameInstant(DEFAULT_MODIFIED_DATE))));
    }

    @Test
    @Transactional
    void getTOrgContactPerson() throws Exception {
        // Initialize the database
        tOrgContactPersonRepository.saveAndFlush(tOrgContactPerson);

        // Get the tOrgContactPerson
        restTOrgContactPersonMockMvc
            .perform(get(ENTITY_API_URL_ID, tOrgContactPerson.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tOrgContactPerson.getId().intValue()))
            .andExpect(jsonPath("$.ocpOrgcodeid").value(DEFAULT_OCP_ORGCODEID.intValue()))
            .andExpect(jsonPath("$.ocpTitle").value(DEFAULT_OCP_TITLE))
            .andExpect(jsonPath("$.ocpName").value(DEFAULT_OCP_NAME))
            .andExpect(jsonPath("$.ocpDesignation").value(DEFAULT_OCP_DESIGNATION))
            .andExpect(jsonPath("$.ocpTelephone1").value(DEFAULT_OCP_TELEPHONE_1))
            .andExpect(jsonPath("$.ocpHandphone").value(DEFAULT_OCP_HANDPHONE))
            .andExpect(jsonPath("$.ocpMail").value(DEFAULT_OCP_MAIL))
            .andExpect(jsonPath("$.ocpSector").value(DEFAULT_OCP_SECTOR))
            .andExpect(jsonPath("$.ocpStatus").value(DEFAULT_OCP_STATUS))
            .andExpect(jsonPath("$.enteredBy").value(DEFAULT_ENTERED_BY))
            .andExpect(jsonPath("$.enteredDate").value(sameInstant(DEFAULT_ENTERED_DATE)))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
            .andExpect(jsonPath("$.modifiedDate").value(sameInstant(DEFAULT_MODIFIED_DATE)));
    }

    @Test
    @Transactional
    void getNonExistingTOrgContactPerson() throws Exception {
        // Get the tOrgContactPerson
        restTOrgContactPersonMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTOrgContactPerson() throws Exception {
        // Initialize the database
        tOrgContactPersonRepository.saveAndFlush(tOrgContactPerson);

        int databaseSizeBeforeUpdate = tOrgContactPersonRepository.findAll().size();

        // Update the tOrgContactPerson
        TOrgContactPerson updatedTOrgContactPerson = tOrgContactPersonRepository.findById(tOrgContactPerson.getId()).get();
        // Disconnect from session so that the updates on updatedTOrgContactPerson are not directly saved in db
        em.detach(updatedTOrgContactPerson);
        updatedTOrgContactPerson
            .ocpOrgcodeid(UPDATED_OCP_ORGCODEID)
            .ocpTitle(UPDATED_OCP_TITLE)
            .ocpName(UPDATED_OCP_NAME)
            .ocpDesignation(UPDATED_OCP_DESIGNATION)
            .ocpTelephone1(UPDATED_OCP_TELEPHONE_1)
            .ocpHandphone(UPDATED_OCP_HANDPHONE)
            .ocpMail(UPDATED_OCP_MAIL)
            .ocpSector(UPDATED_OCP_SECTOR)
            .ocpStatus(UPDATED_OCP_STATUS)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTOrgContactPersonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTOrgContactPerson.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTOrgContactPerson))
            )
            .andExpect(status().isOk());

        // Validate the TOrgContactPerson in the database
        List<TOrgContactPerson> tOrgContactPersonList = tOrgContactPersonRepository.findAll();
        assertThat(tOrgContactPersonList).hasSize(databaseSizeBeforeUpdate);
        TOrgContactPerson testTOrgContactPerson = tOrgContactPersonList.get(tOrgContactPersonList.size() - 1);
        assertThat(testTOrgContactPerson.getOcpOrgcodeid()).isEqualTo(UPDATED_OCP_ORGCODEID);
        assertThat(testTOrgContactPerson.getOcpTitle()).isEqualTo(UPDATED_OCP_TITLE);
        assertThat(testTOrgContactPerson.getOcpName()).isEqualTo(UPDATED_OCP_NAME);
        assertThat(testTOrgContactPerson.getOcpDesignation()).isEqualTo(UPDATED_OCP_DESIGNATION);
        assertThat(testTOrgContactPerson.getOcpTelephone1()).isEqualTo(UPDATED_OCP_TELEPHONE_1);
        assertThat(testTOrgContactPerson.getOcpHandphone()).isEqualTo(UPDATED_OCP_HANDPHONE);
        assertThat(testTOrgContactPerson.getOcpMail()).isEqualTo(UPDATED_OCP_MAIL);
        assertThat(testTOrgContactPerson.getOcpSector()).isEqualTo(UPDATED_OCP_SECTOR);
        assertThat(testTOrgContactPerson.getOcpStatus()).isEqualTo(UPDATED_OCP_STATUS);
        assertThat(testTOrgContactPerson.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTOrgContactPerson.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTOrgContactPerson.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTOrgContactPerson.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingTOrgContactPerson() throws Exception {
        int databaseSizeBeforeUpdate = tOrgContactPersonRepository.findAll().size();
        tOrgContactPerson.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTOrgContactPersonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tOrgContactPerson.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tOrgContactPerson))
            )
            .andExpect(status().isBadRequest());

        // Validate the TOrgContactPerson in the database
        List<TOrgContactPerson> tOrgContactPersonList = tOrgContactPersonRepository.findAll();
        assertThat(tOrgContactPersonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTOrgContactPerson() throws Exception {
        int databaseSizeBeforeUpdate = tOrgContactPersonRepository.findAll().size();
        tOrgContactPerson.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTOrgContactPersonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tOrgContactPerson))
            )
            .andExpect(status().isBadRequest());

        // Validate the TOrgContactPerson in the database
        List<TOrgContactPerson> tOrgContactPersonList = tOrgContactPersonRepository.findAll();
        assertThat(tOrgContactPersonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTOrgContactPerson() throws Exception {
        int databaseSizeBeforeUpdate = tOrgContactPersonRepository.findAll().size();
        tOrgContactPerson.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTOrgContactPersonMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tOrgContactPerson))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TOrgContactPerson in the database
        List<TOrgContactPerson> tOrgContactPersonList = tOrgContactPersonRepository.findAll();
        assertThat(tOrgContactPersonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTOrgContactPersonWithPatch() throws Exception {
        // Initialize the database
        tOrgContactPersonRepository.saveAndFlush(tOrgContactPerson);

        int databaseSizeBeforeUpdate = tOrgContactPersonRepository.findAll().size();

        // Update the tOrgContactPerson using partial update
        TOrgContactPerson partialUpdatedTOrgContactPerson = new TOrgContactPerson();
        partialUpdatedTOrgContactPerson.setId(tOrgContactPerson.getId());

        partialUpdatedTOrgContactPerson
            .ocpOrgcodeid(UPDATED_OCP_ORGCODEID)
            .ocpName(UPDATED_OCP_NAME)
            .ocpHandphone(UPDATED_OCP_HANDPHONE)
            .ocpStatus(UPDATED_OCP_STATUS)
            .enteredBy(UPDATED_ENTERED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTOrgContactPersonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTOrgContactPerson.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTOrgContactPerson))
            )
            .andExpect(status().isOk());

        // Validate the TOrgContactPerson in the database
        List<TOrgContactPerson> tOrgContactPersonList = tOrgContactPersonRepository.findAll();
        assertThat(tOrgContactPersonList).hasSize(databaseSizeBeforeUpdate);
        TOrgContactPerson testTOrgContactPerson = tOrgContactPersonList.get(tOrgContactPersonList.size() - 1);
        assertThat(testTOrgContactPerson.getOcpOrgcodeid()).isEqualTo(UPDATED_OCP_ORGCODEID);
        assertThat(testTOrgContactPerson.getOcpTitle()).isEqualTo(DEFAULT_OCP_TITLE);
        assertThat(testTOrgContactPerson.getOcpName()).isEqualTo(UPDATED_OCP_NAME);
        assertThat(testTOrgContactPerson.getOcpDesignation()).isEqualTo(DEFAULT_OCP_DESIGNATION);
        assertThat(testTOrgContactPerson.getOcpTelephone1()).isEqualTo(DEFAULT_OCP_TELEPHONE_1);
        assertThat(testTOrgContactPerson.getOcpHandphone()).isEqualTo(UPDATED_OCP_HANDPHONE);
        assertThat(testTOrgContactPerson.getOcpMail()).isEqualTo(DEFAULT_OCP_MAIL);
        assertThat(testTOrgContactPerson.getOcpSector()).isEqualTo(DEFAULT_OCP_SECTOR);
        assertThat(testTOrgContactPerson.getOcpStatus()).isEqualTo(UPDATED_OCP_STATUS);
        assertThat(testTOrgContactPerson.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTOrgContactPerson.getEnteredDate()).isEqualTo(DEFAULT_ENTERED_DATE);
        assertThat(testTOrgContactPerson.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTOrgContactPerson.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateTOrgContactPersonWithPatch() throws Exception {
        // Initialize the database
        tOrgContactPersonRepository.saveAndFlush(tOrgContactPerson);

        int databaseSizeBeforeUpdate = tOrgContactPersonRepository.findAll().size();

        // Update the tOrgContactPerson using partial update
        TOrgContactPerson partialUpdatedTOrgContactPerson = new TOrgContactPerson();
        partialUpdatedTOrgContactPerson.setId(tOrgContactPerson.getId());

        partialUpdatedTOrgContactPerson
            .ocpOrgcodeid(UPDATED_OCP_ORGCODEID)
            .ocpTitle(UPDATED_OCP_TITLE)
            .ocpName(UPDATED_OCP_NAME)
            .ocpDesignation(UPDATED_OCP_DESIGNATION)
            .ocpTelephone1(UPDATED_OCP_TELEPHONE_1)
            .ocpHandphone(UPDATED_OCP_HANDPHONE)
            .ocpMail(UPDATED_OCP_MAIL)
            .ocpSector(UPDATED_OCP_SECTOR)
            .ocpStatus(UPDATED_OCP_STATUS)
            .enteredBy(UPDATED_ENTERED_BY)
            .enteredDate(UPDATED_ENTERED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restTOrgContactPersonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTOrgContactPerson.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTOrgContactPerson))
            )
            .andExpect(status().isOk());

        // Validate the TOrgContactPerson in the database
        List<TOrgContactPerson> tOrgContactPersonList = tOrgContactPersonRepository.findAll();
        assertThat(tOrgContactPersonList).hasSize(databaseSizeBeforeUpdate);
        TOrgContactPerson testTOrgContactPerson = tOrgContactPersonList.get(tOrgContactPersonList.size() - 1);
        assertThat(testTOrgContactPerson.getOcpOrgcodeid()).isEqualTo(UPDATED_OCP_ORGCODEID);
        assertThat(testTOrgContactPerson.getOcpTitle()).isEqualTo(UPDATED_OCP_TITLE);
        assertThat(testTOrgContactPerson.getOcpName()).isEqualTo(UPDATED_OCP_NAME);
        assertThat(testTOrgContactPerson.getOcpDesignation()).isEqualTo(UPDATED_OCP_DESIGNATION);
        assertThat(testTOrgContactPerson.getOcpTelephone1()).isEqualTo(UPDATED_OCP_TELEPHONE_1);
        assertThat(testTOrgContactPerson.getOcpHandphone()).isEqualTo(UPDATED_OCP_HANDPHONE);
        assertThat(testTOrgContactPerson.getOcpMail()).isEqualTo(UPDATED_OCP_MAIL);
        assertThat(testTOrgContactPerson.getOcpSector()).isEqualTo(UPDATED_OCP_SECTOR);
        assertThat(testTOrgContactPerson.getOcpStatus()).isEqualTo(UPDATED_OCP_STATUS);
        assertThat(testTOrgContactPerson.getEnteredBy()).isEqualTo(UPDATED_ENTERED_BY);
        assertThat(testTOrgContactPerson.getEnteredDate()).isEqualTo(UPDATED_ENTERED_DATE);
        assertThat(testTOrgContactPerson.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTOrgContactPerson.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingTOrgContactPerson() throws Exception {
        int databaseSizeBeforeUpdate = tOrgContactPersonRepository.findAll().size();
        tOrgContactPerson.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTOrgContactPersonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tOrgContactPerson.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tOrgContactPerson))
            )
            .andExpect(status().isBadRequest());

        // Validate the TOrgContactPerson in the database
        List<TOrgContactPerson> tOrgContactPersonList = tOrgContactPersonRepository.findAll();
        assertThat(tOrgContactPersonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTOrgContactPerson() throws Exception {
        int databaseSizeBeforeUpdate = tOrgContactPersonRepository.findAll().size();
        tOrgContactPerson.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTOrgContactPersonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tOrgContactPerson))
            )
            .andExpect(status().isBadRequest());

        // Validate the TOrgContactPerson in the database
        List<TOrgContactPerson> tOrgContactPersonList = tOrgContactPersonRepository.findAll();
        assertThat(tOrgContactPersonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTOrgContactPerson() throws Exception {
        int databaseSizeBeforeUpdate = tOrgContactPersonRepository.findAll().size();
        tOrgContactPerson.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTOrgContactPersonMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tOrgContactPerson))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TOrgContactPerson in the database
        List<TOrgContactPerson> tOrgContactPersonList = tOrgContactPersonRepository.findAll();
        assertThat(tOrgContactPersonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTOrgContactPerson() throws Exception {
        // Initialize the database
        tOrgContactPersonRepository.saveAndFlush(tOrgContactPerson);

        int databaseSizeBeforeDelete = tOrgContactPersonRepository.findAll().size();

        // Delete the tOrgContactPerson
        restTOrgContactPersonMockMvc
            .perform(delete(ENTITY_API_URL_ID, tOrgContactPerson.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TOrgContactPerson> tOrgContactPersonList = tOrgContactPersonRepository.findAll();
        assertThat(tOrgContactPersonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
