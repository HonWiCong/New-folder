package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TBrandCode;
import com.jhipster.app.repository.TBrandCodeRepository;
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
 * REST controller for managing {@link com.jhipster.app.domain.TBrandCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TBrandCodeResource {

    private final Logger log = LoggerFactory.getLogger(TBrandCodeResource.class);

    private static final String ENTITY_NAME = "tBrandCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TBrandCodeRepository tBrandCodeRepository;

    public TBrandCodeResource(TBrandCodeRepository tBrandCodeRepository) {
        this.tBrandCodeRepository = tBrandCodeRepository;
    }

    /**
     * {@code POST  /t-brand-codes} : Create a new tBrandCode.
     *
     * @param tBrandCode the tBrandCode to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tBrandCode, or with status {@code 400 (Bad Request)} if the tBrandCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/t-brand-codes")
    public ResponseEntity<TBrandCode> createTBrandCode(@RequestBody TBrandCode tBrandCode) throws URISyntaxException {
        log.debug("REST request to save TBrandCode : {}", tBrandCode);
        if (tBrandCode.getId() != null) {
            throw new BadRequestAlertException("A new tBrandCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TBrandCode result = tBrandCodeRepository.save(tBrandCode);
        return ResponseEntity
            .created(new URI("/api/t-brand-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /t-brand-codes/:id} : Updates an existing tBrandCode.
     *
     * @param id the id of the tBrandCode to save.
     * @param tBrandCode the tBrandCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tBrandCode,
     * or with status {@code 400 (Bad Request)} if the tBrandCode is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tBrandCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/t-brand-codes/{id}")
    public ResponseEntity<TBrandCode> updateTBrandCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TBrandCode tBrandCode
    ) throws URISyntaxException {
        log.debug("REST request to update TBrandCode : {}, {}", id, tBrandCode);
        if (tBrandCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tBrandCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tBrandCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TBrandCode result = tBrandCodeRepository.save(tBrandCode);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tBrandCode.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /t-brand-codes/:id} : Partial updates given fields of an existing tBrandCode, field will ignore if it is null
     *
     * @param id the id of the tBrandCode to save.
     * @param tBrandCode the tBrandCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tBrandCode,
     * or with status {@code 400 (Bad Request)} if the tBrandCode is not valid,
     * or with status {@code 404 (Not Found)} if the tBrandCode is not found,
     * or with status {@code 500 (Internal Server Error)} if the tBrandCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/t-brand-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TBrandCode> partialUpdateTBrandCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TBrandCode tBrandCode
    ) throws URISyntaxException {
        log.debug("REST request to partial update TBrandCode partially : {}, {}", id, tBrandCode);
        if (tBrandCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tBrandCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tBrandCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TBrandCode> result = tBrandCodeRepository
            .findById(tBrandCode.getId())
            .map(existingTBrandCode -> {
                if (tBrandCode.getBrandName() != null) {
                    existingTBrandCode.setBrandName(tBrandCode.getBrandName());
                }
                if (tBrandCode.getEnteredBy() != null) {
                    existingTBrandCode.setEnteredBy(tBrandCode.getEnteredBy());
                }
                if (tBrandCode.getEnteredDate() != null) {
                    existingTBrandCode.setEnteredDate(tBrandCode.getEnteredDate());
                }
                if (tBrandCode.getModifyBy() != null) {
                    existingTBrandCode.setModifyBy(tBrandCode.getModifyBy());
                }
                if (tBrandCode.getModifiedDate() != null) {
                    existingTBrandCode.setModifiedDate(tBrandCode.getModifiedDate());
                }

                return existingTBrandCode;
            })
            .map(tBrandCodeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tBrandCode.getId().toString())
        );
    }

    /**
     * {@code GET  /t-brand-codes} : get all the tBrandCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tBrandCodes in body.
     */
    @GetMapping("/t-brand-codes")
    public List<TBrandCode> getAllTBrandCodes() {
        log.debug("REST request to get all TBrandCodes");
        return tBrandCodeRepository.findAll();
    }

    /**
     * {@code GET  /t-brand-codes/:id} : get the "id" tBrandCode.
     *
     * @param id the id of the tBrandCode to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tBrandCode, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/t-brand-codes/{id}")
    public ResponseEntity<TBrandCode> getTBrandCode(@PathVariable Long id) {
        log.debug("REST request to get TBrandCode : {}", id);
        Optional<TBrandCode> tBrandCode = tBrandCodeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tBrandCode);
    }

    /**
     * {@code DELETE  /t-brand-codes/:id} : delete the "id" tBrandCode.
     *
     * @param id the id of the tBrandCode to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/t-brand-codes/{id}")
    public ResponseEntity<Void> deleteTBrandCode(@PathVariable Long id) {
        log.debug("REST request to delete TBrandCode : {}", id);
        tBrandCodeRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
