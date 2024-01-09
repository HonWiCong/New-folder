package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TDistrictCode;
import com.jhipster.app.repository.TDistrictCodeRepository;
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
 * REST controller for managing {@link com.jhipster.app.domain.TDistrictCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TDistrictCodeResource {

    private final Logger log = LoggerFactory.getLogger(TDistrictCodeResource.class);

    private static final String ENTITY_NAME = "tDistrictCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TDistrictCodeRepository tDistrictCodeRepository;

    public TDistrictCodeResource(TDistrictCodeRepository tDistrictCodeRepository) {
        this.tDistrictCodeRepository = tDistrictCodeRepository;
    }

    /**
     * {@code POST  /t-district-codes} : Create a new tDistrictCode.
     *
     * @param tDistrictCode the tDistrictCode to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tDistrictCode, or with status {@code 400 (Bad Request)} if the tDistrictCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/t-district-codes")
    public ResponseEntity<TDistrictCode> createTDistrictCode(@RequestBody TDistrictCode tDistrictCode) throws URISyntaxException {
        log.debug("REST request to save TDistrictCode : {}", tDistrictCode);
        if (tDistrictCode.getId() != null) {
            throw new BadRequestAlertException("A new tDistrictCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TDistrictCode result = tDistrictCodeRepository.save(tDistrictCode);
        return ResponseEntity
            .created(new URI("/api/t-district-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /t-district-codes/:id} : Updates an existing tDistrictCode.
     *
     * @param id the id of the tDistrictCode to save.
     * @param tDistrictCode the tDistrictCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tDistrictCode,
     * or with status {@code 400 (Bad Request)} if the tDistrictCode is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tDistrictCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/t-district-codes/{id}")
    public ResponseEntity<TDistrictCode> updateTDistrictCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TDistrictCode tDistrictCode
    ) throws URISyntaxException {
        log.debug("REST request to update TDistrictCode : {}, {}", id, tDistrictCode);
        if (tDistrictCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tDistrictCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tDistrictCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TDistrictCode result = tDistrictCodeRepository.save(tDistrictCode);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tDistrictCode.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /t-district-codes/:id} : Partial updates given fields of an existing tDistrictCode, field will ignore if it is null
     *
     * @param id the id of the tDistrictCode to save.
     * @param tDistrictCode the tDistrictCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tDistrictCode,
     * or with status {@code 400 (Bad Request)} if the tDistrictCode is not valid,
     * or with status {@code 404 (Not Found)} if the tDistrictCode is not found,
     * or with status {@code 500 (Internal Server Error)} if the tDistrictCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/t-district-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TDistrictCode> partialUpdateTDistrictCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TDistrictCode tDistrictCode
    ) throws URISyntaxException {
        log.debug("REST request to partial update TDistrictCode partially : {}, {}", id, tDistrictCode);
        if (tDistrictCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tDistrictCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tDistrictCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TDistrictCode> result = tDistrictCodeRepository
            .findById(tDistrictCode.getId())
            .map(existingTDistrictCode -> {
                if (tDistrictCode.getDisName() != null) {
                    existingTDistrictCode.setDisName(tDistrictCode.getDisName());
                }
                if (tDistrictCode.getEnteredBy() != null) {
                    existingTDistrictCode.setEnteredBy(tDistrictCode.getEnteredBy());
                }
                if (tDistrictCode.getEnteredDate() != null) {
                    existingTDistrictCode.setEnteredDate(tDistrictCode.getEnteredDate());
                }
                if (tDistrictCode.getModifiedBy() != null) {
                    existingTDistrictCode.setModifiedBy(tDistrictCode.getModifiedBy());
                }
                if (tDistrictCode.getModifiedDate() != null) {
                    existingTDistrictCode.setModifiedDate(tDistrictCode.getModifiedDate());
                }

                return existingTDistrictCode;
            })
            .map(tDistrictCodeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tDistrictCode.getId().toString())
        );
    }

    /**
     * {@code GET  /t-district-codes} : get all the tDistrictCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tDistrictCodes in body.
     */
    @GetMapping("/t-district-codes")
    public List<TDistrictCode> getAllTDistrictCodes() {
        log.debug("REST request to get all TDistrictCodes");
        return tDistrictCodeRepository.findAll();
    }

    /**
     * {@code GET  /t-district-codes/:id} : get the "id" tDistrictCode.
     *
     * @param id the id of the tDistrictCode to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tDistrictCode, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/t-district-codes/{id}")
    public ResponseEntity<TDistrictCode> getTDistrictCode(@PathVariable Long id) {
        log.debug("REST request to get TDistrictCode : {}", id);
        Optional<TDistrictCode> tDistrictCode = tDistrictCodeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tDistrictCode);
    }

    /**
     * {@code DELETE  /t-district-codes/:id} : delete the "id" tDistrictCode.
     *
     * @param id the id of the tDistrictCode to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/t-district-codes/{id}")
    public ResponseEntity<Void> deleteTDistrictCode(@PathVariable Long id) {
        log.debug("REST request to delete TDistrictCode : {}", id);
        tDistrictCodeRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
