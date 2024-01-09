package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TSectorCode;
import com.jhipster.app.repository.TSectorCodeRepository;
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
 * REST controller for managing {@link com.jhipster.app.domain.TSectorCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TSectorCodeResource {

    private final Logger log = LoggerFactory.getLogger(TSectorCodeResource.class);

    private static final String ENTITY_NAME = "tSectorCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TSectorCodeRepository tSectorCodeRepository;

    public TSectorCodeResource(TSectorCodeRepository tSectorCodeRepository) {
        this.tSectorCodeRepository = tSectorCodeRepository;
    }

    /**
     * {@code POST  /t-sector-codes} : Create a new tSectorCode.
     *
     * @param tSectorCode the tSectorCode to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tSectorCode, or with status {@code 400 (Bad Request)} if the tSectorCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/t-sector-codes")
    public ResponseEntity<TSectorCode> createTSectorCode(@RequestBody TSectorCode tSectorCode) throws URISyntaxException {
        log.debug("REST request to save TSectorCode : {}", tSectorCode);
        if (tSectorCode.getId() != null) {
            throw new BadRequestAlertException("A new tSectorCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TSectorCode result = tSectorCodeRepository.save(tSectorCode);
        return ResponseEntity
            .created(new URI("/api/t-sector-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /t-sector-codes/:id} : Updates an existing tSectorCode.
     *
     * @param id the id of the tSectorCode to save.
     * @param tSectorCode the tSectorCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tSectorCode,
     * or with status {@code 400 (Bad Request)} if the tSectorCode is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tSectorCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/t-sector-codes/{id}")
    public ResponseEntity<TSectorCode> updateTSectorCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TSectorCode tSectorCode
    ) throws URISyntaxException {
        log.debug("REST request to update TSectorCode : {}, {}", id, tSectorCode);
        if (tSectorCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tSectorCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tSectorCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TSectorCode result = tSectorCodeRepository.save(tSectorCode);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tSectorCode.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /t-sector-codes/:id} : Partial updates given fields of an existing tSectorCode, field will ignore if it is null
     *
     * @param id the id of the tSectorCode to save.
     * @param tSectorCode the tSectorCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tSectorCode,
     * or with status {@code 400 (Bad Request)} if the tSectorCode is not valid,
     * or with status {@code 404 (Not Found)} if the tSectorCode is not found,
     * or with status {@code 500 (Internal Server Error)} if the tSectorCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/t-sector-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TSectorCode> partialUpdateTSectorCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TSectorCode tSectorCode
    ) throws URISyntaxException {
        log.debug("REST request to partial update TSectorCode partially : {}, {}", id, tSectorCode);
        if (tSectorCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tSectorCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tSectorCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TSectorCode> result = tSectorCodeRepository
            .findById(tSectorCode.getId())
            .map(existingTSectorCode -> {
                if (tSectorCode.getSectorName() != null) {
                    existingTSectorCode.setSectorName(tSectorCode.getSectorName());
                }
                if (tSectorCode.getSectorDescription() != null) {
                    existingTSectorCode.setSectorDescription(tSectorCode.getSectorDescription());
                }
                if (tSectorCode.getEnteredBy() != null) {
                    existingTSectorCode.setEnteredBy(tSectorCode.getEnteredBy());
                }
                if (tSectorCode.getEnteredDate() != null) {
                    existingTSectorCode.setEnteredDate(tSectorCode.getEnteredDate());
                }
                if (tSectorCode.getModifiedBy() != null) {
                    existingTSectorCode.setModifiedBy(tSectorCode.getModifiedBy());
                }
                if (tSectorCode.getModifiedDate() != null) {
                    existingTSectorCode.setModifiedDate(tSectorCode.getModifiedDate());
                }

                return existingTSectorCode;
            })
            .map(tSectorCodeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tSectorCode.getId().toString())
        );
    }

    /**
     * {@code GET  /t-sector-codes} : get all the tSectorCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tSectorCodes in body.
     */
    @GetMapping("/t-sector-codes")
    public List<TSectorCode> getAllTSectorCodes() {
        log.debug("REST request to get all TSectorCodes");
        return tSectorCodeRepository.findAll();
    }

    /**
     * {@code GET  /t-sector-codes/:id} : get the "id" tSectorCode.
     *
     * @param id the id of the tSectorCode to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tSectorCode, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/t-sector-codes/{id}")
    public ResponseEntity<TSectorCode> getTSectorCode(@PathVariable Long id) {
        log.debug("REST request to get TSectorCode : {}", id);
        Optional<TSectorCode> tSectorCode = tSectorCodeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tSectorCode);
    }

    /**
     * {@code DELETE  /t-sector-codes/:id} : delete the "id" tSectorCode.
     *
     * @param id the id of the tSectorCode to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/t-sector-codes/{id}")
    public ResponseEntity<Void> deleteTSectorCode(@PathVariable Long id) {
        log.debug("REST request to delete TSectorCode : {}", id);
        tSectorCodeRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
