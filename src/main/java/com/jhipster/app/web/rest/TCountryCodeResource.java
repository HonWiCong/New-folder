package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TCountryCode;
import com.jhipster.app.repository.TCountryCodeRepository;
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
 * REST controller for managing {@link com.jhipster.app.domain.TCountryCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCountryCodeResource {

    private final Logger log = LoggerFactory.getLogger(TCountryCodeResource.class);

    private static final String ENTITY_NAME = "tCountryCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCountryCodeRepository tCountryCodeRepository;

    public TCountryCodeResource(TCountryCodeRepository tCountryCodeRepository) {
        this.tCountryCodeRepository = tCountryCodeRepository;
    }

    /**
     * {@code POST  /t-country-codes} : Create a new tCountryCode.
     *
     * @param tCountryCode the tCountryCode to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCountryCode, or with status {@code 400 (Bad Request)} if the tCountryCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/t-country-codes")
    public ResponseEntity<TCountryCode> createTCountryCode(@RequestBody TCountryCode tCountryCode) throws URISyntaxException {
        log.debug("REST request to save TCountryCode : {}", tCountryCode);
        if (tCountryCode.getId() != null) {
            throw new BadRequestAlertException("A new tCountryCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCountryCode result = tCountryCodeRepository.save(tCountryCode);
        return ResponseEntity
            .created(new URI("/api/t-country-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /t-country-codes/:id} : Updates an existing tCountryCode.
     *
     * @param id the id of the tCountryCode to save.
     * @param tCountryCode the tCountryCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCountryCode,
     * or with status {@code 400 (Bad Request)} if the tCountryCode is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCountryCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/t-country-codes/{id}")
    public ResponseEntity<TCountryCode> updateTCountryCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TCountryCode tCountryCode
    ) throws URISyntaxException {
        log.debug("REST request to update TCountryCode : {}, {}", id, tCountryCode);
        if (tCountryCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tCountryCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCountryCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCountryCode result = tCountryCodeRepository.save(tCountryCode);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCountryCode.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /t-country-codes/:id} : Partial updates given fields of an existing tCountryCode, field will ignore if it is null
     *
     * @param id the id of the tCountryCode to save.
     * @param tCountryCode the tCountryCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCountryCode,
     * or with status {@code 400 (Bad Request)} if the tCountryCode is not valid,
     * or with status {@code 404 (Not Found)} if the tCountryCode is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCountryCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/t-country-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TCountryCode> partialUpdateTCountryCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TCountryCode tCountryCode
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCountryCode partially : {}, {}", id, tCountryCode);
        if (tCountryCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tCountryCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCountryCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCountryCode> result = tCountryCodeRepository
            .findById(tCountryCode.getId())
            .map(existingTCountryCode -> {
                if (tCountryCode.getCountryCode() != null) {
                    existingTCountryCode.setCountryCode(tCountryCode.getCountryCode());
                }
                if (tCountryCode.getCountryName() != null) {
                    existingTCountryCode.setCountryName(tCountryCode.getCountryName());
                }
                if (tCountryCode.getCountryNationality() != null) {
                    existingTCountryCode.setCountryNationality(tCountryCode.getCountryNationality());
                }
                if (tCountryCode.getEnteredBy() != null) {
                    existingTCountryCode.setEnteredBy(tCountryCode.getEnteredBy());
                }
                if (tCountryCode.getEnteredDate() != null) {
                    existingTCountryCode.setEnteredDate(tCountryCode.getEnteredDate());
                }
                if (tCountryCode.getModifyBy() != null) {
                    existingTCountryCode.setModifyBy(tCountryCode.getModifyBy());
                }
                if (tCountryCode.getModifiedDate() != null) {
                    existingTCountryCode.setModifiedDate(tCountryCode.getModifiedDate());
                }
                if (tCountryCode.getOrgCustomerType() != null) {
                    existingTCountryCode.setOrgCustomerType(tCountryCode.getOrgCustomerType());
                }

                return existingTCountryCode;
            })
            .map(tCountryCodeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCountryCode.getId().toString())
        );
    }

    /**
     * {@code GET  /t-country-codes} : get all the tCountryCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCountryCodes in body.
     */
    @GetMapping("/t-country-codes")
    public List<TCountryCode> getAllTCountryCodes() {
        log.debug("REST request to get all TCountryCodes");
        return tCountryCodeRepository.findAll();
    }

    /**
     * {@code GET  /t-country-codes/:id} : get the "id" tCountryCode.
     *
     * @param id the id of the tCountryCode to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCountryCode, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/t-country-codes/{id}")
    public ResponseEntity<TCountryCode> getTCountryCode(@PathVariable Long id) {
        log.debug("REST request to get TCountryCode : {}", id);
        Optional<TCountryCode> tCountryCode = tCountryCodeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tCountryCode);
    }

    /**
     * {@code DELETE  /t-country-codes/:id} : delete the "id" tCountryCode.
     *
     * @param id the id of the tCountryCode to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/t-country-codes/{id}")
    public ResponseEntity<Void> deleteTCountryCode(@PathVariable Long id) {
        log.debug("REST request to delete TCountryCode : {}", id);
        tCountryCodeRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
