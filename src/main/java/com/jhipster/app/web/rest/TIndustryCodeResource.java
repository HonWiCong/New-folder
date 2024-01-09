package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TIndustryCode;
import com.jhipster.app.repository.TIndustryCodeRepository;
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
 * REST controller for managing {@link com.jhipster.app.domain.TIndustryCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TIndustryCodeResource {

    private final Logger log = LoggerFactory.getLogger(TIndustryCodeResource.class);

    private static final String ENTITY_NAME = "tIndustryCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TIndustryCodeRepository tIndustryCodeRepository;

    public TIndustryCodeResource(TIndustryCodeRepository tIndustryCodeRepository) {
        this.tIndustryCodeRepository = tIndustryCodeRepository;
    }

    /**
     * {@code POST  /t-industry-codes} : Create a new tIndustryCode.
     *
     * @param tIndustryCode the tIndustryCode to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tIndustryCode, or with status {@code 400 (Bad Request)} if the tIndustryCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/t-industry-codes")
    public ResponseEntity<TIndustryCode> createTIndustryCode(@RequestBody TIndustryCode tIndustryCode) throws URISyntaxException {
        log.debug("REST request to save TIndustryCode : {}", tIndustryCode);
        if (tIndustryCode.getId() != null) {
            throw new BadRequestAlertException("A new tIndustryCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TIndustryCode result = tIndustryCodeRepository.save(tIndustryCode);
        return ResponseEntity
            .created(new URI("/api/t-industry-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /t-industry-codes/:id} : Updates an existing tIndustryCode.
     *
     * @param id the id of the tIndustryCode to save.
     * @param tIndustryCode the tIndustryCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tIndustryCode,
     * or with status {@code 400 (Bad Request)} if the tIndustryCode is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tIndustryCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/t-industry-codes/{id}")
    public ResponseEntity<TIndustryCode> updateTIndustryCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TIndustryCode tIndustryCode
    ) throws URISyntaxException {
        log.debug("REST request to update TIndustryCode : {}, {}", id, tIndustryCode);
        if (tIndustryCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tIndustryCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tIndustryCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TIndustryCode result = tIndustryCodeRepository.save(tIndustryCode);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tIndustryCode.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /t-industry-codes/:id} : Partial updates given fields of an existing tIndustryCode, field will ignore if it is null
     *
     * @param id the id of the tIndustryCode to save.
     * @param tIndustryCode the tIndustryCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tIndustryCode,
     * or with status {@code 400 (Bad Request)} if the tIndustryCode is not valid,
     * or with status {@code 404 (Not Found)} if the tIndustryCode is not found,
     * or with status {@code 500 (Internal Server Error)} if the tIndustryCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/t-industry-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TIndustryCode> partialUpdateTIndustryCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TIndustryCode tIndustryCode
    ) throws URISyntaxException {
        log.debug("REST request to partial update TIndustryCode partially : {}, {}", id, tIndustryCode);
        if (tIndustryCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tIndustryCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tIndustryCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TIndustryCode> result = tIndustryCodeRepository
            .findById(tIndustryCode.getId())
            .map(existingTIndustryCode -> {
                if (tIndustryCode.getIndustryName() != null) {
                    existingTIndustryCode.setIndustryName(tIndustryCode.getIndustryName());
                }
                if (tIndustryCode.getEnteredBy() != null) {
                    existingTIndustryCode.setEnteredBy(tIndustryCode.getEnteredBy());
                }
                if (tIndustryCode.getEnteredDate() != null) {
                    existingTIndustryCode.setEnteredDate(tIndustryCode.getEnteredDate());
                }
                if (tIndustryCode.getModifiedBy() != null) {
                    existingTIndustryCode.setModifiedBy(tIndustryCode.getModifiedBy());
                }
                if (tIndustryCode.getModifiedDate() != null) {
                    existingTIndustryCode.setModifiedDate(tIndustryCode.getModifiedDate());
                }

                return existingTIndustryCode;
            })
            .map(tIndustryCodeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tIndustryCode.getId().toString())
        );
    }

    /**
     * {@code GET  /t-industry-codes} : get all the tIndustryCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tIndustryCodes in body.
     */
    @GetMapping("/t-industry-codes")
    public List<TIndustryCode> getAllTIndustryCodes() {
        log.debug("REST request to get all TIndustryCodes");
        return tIndustryCodeRepository.findAll();
    }

    /**
     * {@code GET  /t-industry-codes/:id} : get the "id" tIndustryCode.
     *
     * @param id the id of the tIndustryCode to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tIndustryCode, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/t-industry-codes/{id}")
    public ResponseEntity<TIndustryCode> getTIndustryCode(@PathVariable Long id) {
        log.debug("REST request to get TIndustryCode : {}", id);
        Optional<TIndustryCode> tIndustryCode = tIndustryCodeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tIndustryCode);
    }

    /**
     * {@code DELETE  /t-industry-codes/:id} : delete the "id" tIndustryCode.
     *
     * @param id the id of the tIndustryCode to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/t-industry-codes/{id}")
    public ResponseEntity<Void> deleteTIndustryCode(@PathVariable Long id) {
        log.debug("REST request to delete TIndustryCode : {}", id);
        tIndustryCodeRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
