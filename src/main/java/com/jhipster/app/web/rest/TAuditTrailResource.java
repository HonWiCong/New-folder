package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TAuditTrail;
import com.jhipster.app.repository.TAuditTrailRepository;
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
 * REST controller for managing {@link com.jhipster.app.domain.TAuditTrail}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TAuditTrailResource {

    private final Logger log = LoggerFactory.getLogger(TAuditTrailResource.class);

    private static final String ENTITY_NAME = "tAuditTrail";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TAuditTrailRepository tAuditTrailRepository;

    public TAuditTrailResource(TAuditTrailRepository tAuditTrailRepository) {
        this.tAuditTrailRepository = tAuditTrailRepository;
    }

    /**
     * {@code POST  /t-audit-trails} : Create a new tAuditTrail.
     *
     * @param tAuditTrail the tAuditTrail to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tAuditTrail, or with status {@code 400 (Bad Request)} if the tAuditTrail has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/t-audit-trails")
    public ResponseEntity<TAuditTrail> createTAuditTrail(@RequestBody TAuditTrail tAuditTrail) throws URISyntaxException {
        log.debug("REST request to save TAuditTrail : {}", tAuditTrail);
        if (tAuditTrail.getId() != null) {
            throw new BadRequestAlertException("A new tAuditTrail cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TAuditTrail result = tAuditTrailRepository.save(tAuditTrail);
        return ResponseEntity
            .created(new URI("/api/t-audit-trails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /t-audit-trails/:id} : Updates an existing tAuditTrail.
     *
     * @param id the id of the tAuditTrail to save.
     * @param tAuditTrail the tAuditTrail to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tAuditTrail,
     * or with status {@code 400 (Bad Request)} if the tAuditTrail is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tAuditTrail couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/t-audit-trails/{id}")
    public ResponseEntity<TAuditTrail> updateTAuditTrail(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TAuditTrail tAuditTrail
    ) throws URISyntaxException {
        log.debug("REST request to update TAuditTrail : {}, {}", id, tAuditTrail);
        if (tAuditTrail.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tAuditTrail.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tAuditTrailRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TAuditTrail result = tAuditTrailRepository.save(tAuditTrail);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tAuditTrail.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /t-audit-trails/:id} : Partial updates given fields of an existing tAuditTrail, field will ignore if it is null
     *
     * @param id the id of the tAuditTrail to save.
     * @param tAuditTrail the tAuditTrail to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tAuditTrail,
     * or with status {@code 400 (Bad Request)} if the tAuditTrail is not valid,
     * or with status {@code 404 (Not Found)} if the tAuditTrail is not found,
     * or with status {@code 500 (Internal Server Error)} if the tAuditTrail couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/t-audit-trails/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TAuditTrail> partialUpdateTAuditTrail(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TAuditTrail tAuditTrail
    ) throws URISyntaxException {
        log.debug("REST request to partial update TAuditTrail partially : {}, {}", id, tAuditTrail);
        if (tAuditTrail.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tAuditTrail.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tAuditTrailRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TAuditTrail> result = tAuditTrailRepository
            .findById(tAuditTrail.getId())
            .map(existingTAuditTrail -> {
                if (tAuditTrail.getUserId() != null) {
                    existingTAuditTrail.setUserId(tAuditTrail.getUserId());
                }
                if (tAuditTrail.getDateTime() != null) {
                    existingTAuditTrail.setDateTime(tAuditTrail.getDateTime());
                }
                if (tAuditTrail.getTableName() != null) {
                    existingTAuditTrail.setTableName(tAuditTrail.getTableName());
                }
                if (tAuditTrail.getAuditAction() != null) {
                    existingTAuditTrail.setAuditAction(tAuditTrail.getAuditAction());
                }
                if (tAuditTrail.getRecordId() != null) {
                    existingTAuditTrail.setRecordId(tAuditTrail.getRecordId());
                }
                if (tAuditTrail.getFieldDesc() != null) {
                    existingTAuditTrail.setFieldDesc(tAuditTrail.getFieldDesc());
                }
                if (tAuditTrail.getAtStatus() != null) {
                    existingTAuditTrail.setAtStatus(tAuditTrail.getAtStatus());
                }
                if (tAuditTrail.getStFullDesc() != null) {
                    existingTAuditTrail.setStFullDesc(tAuditTrail.getStFullDesc());
                }

                return existingTAuditTrail;
            })
            .map(tAuditTrailRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tAuditTrail.getId().toString())
        );
    }

    /**
     * {@code GET  /t-audit-trails} : get all the tAuditTrails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tAuditTrails in body.
     */
    @GetMapping("/t-audit-trails")
    public List<TAuditTrail> getAllTAuditTrails() {
        log.debug("REST request to get all TAuditTrails");
        return tAuditTrailRepository.findAll();
    }

    /**
     * {@code GET  /t-audit-trails/:id} : get the "id" tAuditTrail.
     *
     * @param id the id of the tAuditTrail to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tAuditTrail, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/t-audit-trails/{id}")
    public ResponseEntity<TAuditTrail> getTAuditTrail(@PathVariable Long id) {
        log.debug("REST request to get TAuditTrail : {}", id);
        Optional<TAuditTrail> tAuditTrail = tAuditTrailRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tAuditTrail);
    }

    /**
     * {@code DELETE  /t-audit-trails/:id} : delete the "id" tAuditTrail.
     *
     * @param id the id of the tAuditTrail to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/t-audit-trails/{id}")
    public ResponseEntity<Void> deleteTAuditTrail(@PathVariable Long id) {
        log.debug("REST request to delete TAuditTrail : {}", id);
        tAuditTrailRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
