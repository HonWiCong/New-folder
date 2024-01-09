package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TOrgContactPerson;
import com.jhipster.app.repository.TOrgContactPersonRepository;
import com.jhipster.app.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.jhipster.app.domain.TOrgContactPerson}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TOrgContactPersonResource {

    private final Logger log = LoggerFactory.getLogger(TOrgContactPersonResource.class);

    private static final String ENTITY_NAME = "tOrgContactPerson";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TOrgContactPersonRepository tOrgContactPersonRepository;

    public TOrgContactPersonResource(TOrgContactPersonRepository tOrgContactPersonRepository) {
        this.tOrgContactPersonRepository = tOrgContactPersonRepository;
    }

    /**
     * {@code POST  /t-org-contact-people} : Create a new tOrgContactPerson.
     *
     * @param tOrgContactPerson the tOrgContactPerson to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tOrgContactPerson, or with status {@code 400 (Bad Request)} if the tOrgContactPerson has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/t-org-contact-people")
    public ResponseEntity<TOrgContactPerson> createTOrgContactPerson(@RequestBody TOrgContactPerson tOrgContactPerson)
        throws URISyntaxException {
        log.debug("REST request to save TOrgContactPerson : {}", tOrgContactPerson);
        if (tOrgContactPerson.getId() != null) {
            throw new BadRequestAlertException("A new tOrgContactPerson cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TOrgContactPerson result = tOrgContactPersonRepository.save(tOrgContactPerson);
        return ResponseEntity
            .created(new URI("/api/t-org-contact-people/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /t-org-contact-people/:id} : Updates an existing tOrgContactPerson.
     *
     * @param id the id of the tOrgContactPerson to save.
     * @param tOrgContactPerson the tOrgContactPerson to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tOrgContactPerson,
     * or with status {@code 400 (Bad Request)} if the tOrgContactPerson is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tOrgContactPerson couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/t-org-contact-people/{id}")
    public ResponseEntity<TOrgContactPerson> updateTOrgContactPerson(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TOrgContactPerson tOrgContactPerson
    ) throws URISyntaxException {
        log.debug("REST request to update TOrgContactPerson : {}, {}", id, tOrgContactPerson);
        if (tOrgContactPerson.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tOrgContactPerson.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tOrgContactPersonRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TOrgContactPerson result = tOrgContactPersonRepository.save(tOrgContactPerson);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tOrgContactPerson.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /t-org-contact-people/:id} : Partial updates given fields of an existing tOrgContactPerson, field will ignore if it is null
     *
     * @param id the id of the tOrgContactPerson to save.
     * @param tOrgContactPerson the tOrgContactPerson to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tOrgContactPerson,
     * or with status {@code 400 (Bad Request)} if the tOrgContactPerson is not valid,
     * or with status {@code 404 (Not Found)} if the tOrgContactPerson is not found,
     * or with status {@code 500 (Internal Server Error)} if the tOrgContactPerson couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/t-org-contact-people/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TOrgContactPerson> partialUpdateTOrgContactPerson(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TOrgContactPerson tOrgContactPerson
    ) throws URISyntaxException {
        log.debug("REST request to partial update TOrgContactPerson partially : {}, {}", id, tOrgContactPerson);
        if (tOrgContactPerson.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tOrgContactPerson.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tOrgContactPersonRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TOrgContactPerson> result = tOrgContactPersonRepository
            .findById(tOrgContactPerson.getId())
            .map(existingTOrgContactPerson -> {
                if (tOrgContactPerson.getOcpOrgcodeid() != null) {
                    existingTOrgContactPerson.setOcpOrgcodeid(tOrgContactPerson.getOcpOrgcodeid());
                }
                if (tOrgContactPerson.getOcpTitle() != null) {
                    existingTOrgContactPerson.setOcpTitle(tOrgContactPerson.getOcpTitle());
                }
                if (tOrgContactPerson.getOcpName() != null) {
                    existingTOrgContactPerson.setOcpName(tOrgContactPerson.getOcpName());
                }
                if (tOrgContactPerson.getOcpDesignation() != null) {
                    existingTOrgContactPerson.setOcpDesignation(tOrgContactPerson.getOcpDesignation());
                }
                if (tOrgContactPerson.getOcpTelephone1() != null) {
                    existingTOrgContactPerson.setOcpTelephone1(tOrgContactPerson.getOcpTelephone1());
                }
                if (tOrgContactPerson.getOcpHandphone() != null) {
                    existingTOrgContactPerson.setOcpHandphone(tOrgContactPerson.getOcpHandphone());
                }
                if (tOrgContactPerson.getOcpMail() != null) {
                    existingTOrgContactPerson.setOcpMail(tOrgContactPerson.getOcpMail());
                }
                if (tOrgContactPerson.getOcpSector() != null) {
                    existingTOrgContactPerson.setOcpSector(tOrgContactPerson.getOcpSector());
                }
                if (tOrgContactPerson.getOcpStatus() != null) {
                    existingTOrgContactPerson.setOcpStatus(tOrgContactPerson.getOcpStatus());
                }
                if (tOrgContactPerson.getEnteredBy() != null) {
                    existingTOrgContactPerson.setEnteredBy(tOrgContactPerson.getEnteredBy());
                }
                if (tOrgContactPerson.getEnteredDate() != null) {
                    existingTOrgContactPerson.setEnteredDate(tOrgContactPerson.getEnteredDate());
                }
                if (tOrgContactPerson.getModifiedBy() != null) {
                    existingTOrgContactPerson.setModifiedBy(tOrgContactPerson.getModifiedBy());
                }
                if (tOrgContactPerson.getModifiedDate() != null) {
                    existingTOrgContactPerson.setModifiedDate(tOrgContactPerson.getModifiedDate());
                }

                return existingTOrgContactPerson;
            })
            .map(tOrgContactPersonRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tOrgContactPerson.getId().toString())
        );
    }

    /**
     * {@code GET  /t-org-contact-people} : get all the tOrgContactPeople.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tOrgContactPeople in body.
     */
    @GetMapping("/t-org-contact-people")
    public List<TOrgContactPerson> getAllTOrgContactPeople() {
        log.debug("REST request to get all TOrgContactPeople");
        return tOrgContactPersonRepository.findAll();
    }

    /**
     * {@code GET  /t-org-contact-people/:id} : get the "id" tOrgContactPerson.
     *
     * @param id the id of the tOrgContactPerson to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tOrgContactPerson, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/t-org-contact-people/{id}")
    public ResponseEntity<TOrgContactPerson> getTOrgContactPerson(@PathVariable Long id) {
        log.debug("REST request to get TOrgContactPerson : {}", id);
        Optional<TOrgContactPerson> tOrgContactPerson = tOrgContactPersonRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tOrgContactPerson);
    }

    /**
     * {@code DELETE  /t-org-contact-people/:id} : delete the "id" tOrgContactPerson.
     *
     * @param id the id of the tOrgContactPerson to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/t-org-contact-people/{id}")
    public ResponseEntity<Void> deleteTOrgContactPerson(@PathVariable Long id) {
        log.debug("REST request to delete TOrgContactPerson : {}", id);
        tOrgContactPersonRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
