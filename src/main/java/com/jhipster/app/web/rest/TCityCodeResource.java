package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TCityCode;
import com.jhipster.app.repository.TCityCodeRepository;
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
 * REST controller for managing {@link com.jhipster.app.domain.TCityCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCityCodeResource {

    private final Logger log = LoggerFactory.getLogger(TCityCodeResource.class);

    private static final String ENTITY_NAME = "tCityCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCityCodeRepository tCityCodeRepository;

    public TCityCodeResource(TCityCodeRepository tCityCodeRepository) {
        this.tCityCodeRepository = tCityCodeRepository;
    }

    /**
     * {@code POST  /t-city-codes} : Create a new tCityCode.
     *
     * @param tCityCode the tCityCode to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCityCode, or with status {@code 400 (Bad Request)} if the tCityCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/t-city-codes")
    public ResponseEntity<TCityCode> createTCityCode(@RequestBody TCityCode tCityCode) throws URISyntaxException {
        log.debug("REST request to save TCityCode : {}", tCityCode);
        if (tCityCode.getId() != null) {
            throw new BadRequestAlertException("A new tCityCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCityCode result = tCityCodeRepository.save(tCityCode);
        return ResponseEntity
            .created(new URI("/api/t-city-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /t-city-codes/:id} : Updates an existing tCityCode.
     *
     * @param id the id of the tCityCode to save.
     * @param tCityCode the tCityCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCityCode,
     * or with status {@code 400 (Bad Request)} if the tCityCode is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCityCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/t-city-codes/{id}")
    public ResponseEntity<TCityCode> updateTCityCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TCityCode tCityCode
    ) throws URISyntaxException {
        log.debug("REST request to update TCityCode : {}, {}", id, tCityCode);
        if (tCityCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tCityCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCityCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCityCode result = tCityCodeRepository.save(tCityCode);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCityCode.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /t-city-codes/:id} : Partial updates given fields of an existing tCityCode, field will ignore if it is null
     *
     * @param id the id of the tCityCode to save.
     * @param tCityCode the tCityCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCityCode,
     * or with status {@code 400 (Bad Request)} if the tCityCode is not valid,
     * or with status {@code 404 (Not Found)} if the tCityCode is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCityCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/t-city-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TCityCode> partialUpdateTCityCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TCityCode tCityCode
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCityCode partially : {}, {}", id, tCityCode);
        if (tCityCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tCityCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCityCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCityCode> result = tCityCodeRepository
            .findById(tCityCode.getId())
            .map(existingTCityCode -> {
                if (tCityCode.getCityCode() != null) {
                    existingTCityCode.setCityCode(tCityCode.getCityCode());
                }
                if (tCityCode.getCityName() != null) {
                    existingTCityCode.setCityName(tCityCode.getCityName());
                }
                if (tCityCode.getEnteredBy() != null) {
                    existingTCityCode.setEnteredBy(tCityCode.getEnteredBy());
                }
                if (tCityCode.getEnteredDate() != null) {
                    existingTCityCode.setEnteredDate(tCityCode.getEnteredDate());
                }
                if (tCityCode.getModifiedBy() != null) {
                    existingTCityCode.setModifiedBy(tCityCode.getModifiedBy());
                }
                if (tCityCode.getModifiedDate() != null) {
                    existingTCityCode.setModifiedDate(tCityCode.getModifiedDate());
                }

                return existingTCityCode;
            })
            .map(tCityCodeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCityCode.getId().toString())
        );
    }

    /**
     * {@code GET  /t-city-codes} : get all the tCityCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCityCodes in body.
     */
    @GetMapping("/t-city-codes")
    public List<TCityCode> getAllTCityCodes() {
        log.debug("REST request to get all TCityCodes");
        return tCityCodeRepository.findAll();
    }

    /**
     * {@code GET  /t-city-codes/:id} : get the "id" tCityCode.
     *
     * @param id the id of the tCityCode to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCityCode, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/t-city-codes/{id}")
    public ResponseEntity<TCityCode> getTCityCode(@PathVariable Long id) {
        log.debug("REST request to get TCityCode : {}", id);
        Optional<TCityCode> tCityCode = tCityCodeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tCityCode);
    }

    /**
     * {@code DELETE  /t-city-codes/:id} : delete the "id" tCityCode.
     *
     * @param id the id of the tCityCode to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/t-city-codes/{id}")
    public ResponseEntity<Void> deleteTCityCode(@PathVariable Long id) {
        log.debug("REST request to delete TCityCode : {}", id);
        tCityCodeRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
