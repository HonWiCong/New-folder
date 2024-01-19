package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TSectionCode;
import com.jhipster.app.repository.TSectionCodeRepository;
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
 * REST controller for managing {@link com.jhipster.app.domain.TSectionCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TSectionCodeResource {

	private final Logger log = LoggerFactory.getLogger(TSectionCodeResource.class);

	private static final String ENTITY_NAME = "tSectionCode";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final TSectionCodeRepository tSectionCodeRepository;

	public TSectionCodeResource(TSectionCodeRepository tSectionCodeRepository) {
		this.tSectionCodeRepository = tSectionCodeRepository;
	}

	/**
	 * {@code POST  /t-section-codes} : Create a new tSectionCode.
	 *
	 * @param tSectionCode the tSectionCode to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tSectionCode, or with status {@code 400 (Bad Request)} if the tSectionCode has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/t-section-codes")
	public ResponseEntity<TSectionCode> createTSectionCode(@RequestBody TSectionCode tSectionCode) throws URISyntaxException {
		log.debug("REST request to save TSectionCode : {}", tSectionCode);
		if (tSectionCode.getId() != null) {
			throw new BadRequestAlertException("A new tSectionCode cannot already have an ID", ENTITY_NAME, "idexists");
		}
		TSectionCode result = tSectionCodeRepository.save(tSectionCode);
		return ResponseEntity
			.created(new URI("/api/t-section-codes/" + result.getId()))
			.headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
			.body(result);
	}

	/**
	 * {@code PUT  /t-section-codes/:id} : Updates an existing tSectionCode.
	 *
	 * @param id the id of the tSectionCode to save.
	 * @param tSectionCode the tSectionCode to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tSectionCode,
	 * or with status {@code 400 (Bad Request)} if the tSectionCode is not valid,
	 * or with status {@code 500 (Internal Server Error)} if the tSectionCode couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/t-section-codes/{id}")
	public ResponseEntity<TSectionCode> updateTSectionCode(
		@PathVariable(value = "id", required = false) final Long id,
		@RequestBody TSectionCode tSectionCode
	) throws URISyntaxException {
		log.debug("REST request to update TSectionCode : {}, {}", id, tSectionCode);
		if (tSectionCode.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, tSectionCode.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!tSectionCodeRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		TSectionCode result = tSectionCodeRepository.save(tSectionCode);
		return ResponseEntity
			.ok()
			.headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tSectionCode.getId().toString()))
			.body(result);
	}

	/**
	 * {@code PATCH  /t-section-codes/:id} : Partial updates given fields of an existing tSectionCode, field will ignore if it is null
	 *
	 * @param id the id of the tSectionCode to save.
	 * @param tSectionCode the tSectionCode to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tSectionCode,
	 * or with status {@code 400 (Bad Request)} if the tSectionCode is not valid,
	 * or with status {@code 404 (Not Found)} if the tSectionCode is not found,
	 * or with status {@code 500 (Internal Server Error)} if the tSectionCode couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PatchMapping(value = "/t-section-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
	public ResponseEntity<TSectionCode> partialUpdateTSectionCode(
		@PathVariable(value = "id", required = false) final Long id,
		@RequestBody TSectionCode tSectionCode
	) throws URISyntaxException {
		log.debug("REST request to partial update TSectionCode partially : {}, {}", id, tSectionCode);
		if (tSectionCode.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, tSectionCode.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!tSectionCodeRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		Optional<TSectionCode> result = tSectionCodeRepository
			.findById(tSectionCode.getId())
			.map(existingTSectionCode -> {
				if (tSectionCode.getSectName() != null) {
					existingTSectionCode.setSectName(tSectionCode.getSectName());
				}
				if (tSectionCode.getSectHeadid() != null) {
					existingTSectionCode.setSectHeadid(tSectionCode.getSectHeadid());
				}
				if (tSectionCode.getSectActingHeadid() != null) {
					existingTSectionCode.setSectActingHeadid(tSectionCode.getSectActingHeadid());
				}
				if (tSectionCode.getSectSainsSubsidiary() != null) {
					existingTSectionCode.setSectSainsSubsidiary(tSectionCode.getSectSainsSubsidiary());
				}
				if (tSectionCode.getSectHrpayId() != null) {
					existingTSectionCode.setSectHrpayId(tSectionCode.getSectHrpayId());
				}
				if (tSectionCode.getSectPapId() != null) {
					existingTSectionCode.setSectPapId(tSectionCode.getSectPapId());
				}
				if (tSectionCode.getSectPapCode() != null) {
					existingTSectionCode.setSectPapCode(tSectionCode.getSectPapCode());
				}
				if (tSectionCode.getSectPapCompany() != null) {
					existingTSectionCode.setSectPapCompany(tSectionCode.getSectPapCompany());
				}
				if (tSectionCode.getSectPapActive() != null) {
					existingTSectionCode.setSectPapActive(tSectionCode.getSectPapActive());
				}
				if (tSectionCode.getSectCcCode() != null) {
					existingTSectionCode.setSectCcCode(tSectionCode.getSectCcCode());
				}
				if (tSectionCode.getEnteredBy() != null) {
					existingTSectionCode.setEnteredBy(tSectionCode.getEnteredBy());
				}
				if (tSectionCode.getEnteredDate() != null) {
					existingTSectionCode.setEnteredDate(tSectionCode.getEnteredDate());
				}
				if (tSectionCode.getModifiedBy() != null) {
					existingTSectionCode.setModifiedBy(tSectionCode.getModifiedBy());
				}
				if (tSectionCode.getModifiedDate() != null) {
					existingTSectionCode.setModifiedDate(tSectionCode.getModifiedDate());
				}

				return existingTSectionCode;
			})
			.map(tSectionCodeRepository::save);

		return ResponseUtil.wrapOrNotFound(
			result,
			HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tSectionCode.getId().toString())
		);
	}

	/**
	 * {@code GET  /t-section-codes} : get all the tSectionCodes.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tSectionCodes in body.
	 */
	@GetMapping("/t-section-codes")
	public List<TSectionCode> getAllTSectionCodes() {
		log.debug("REST request to get all TSectionCodes");
		return tSectionCodeRepository.findAll();
	}

	/**
	 * {@code GET  /t-section-codes/:id} : get the "id" tSectionCode.
	 *
	 * @param id the id of the tSectionCode to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tSectionCode, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/t-section-codes/{id}")
	public ResponseEntity<TSectionCode> getTSectionCode(@PathVariable Long id) {
		log.debug("REST request to get TSectionCode : {}", id);
		Optional<TSectionCode> tSectionCode = tSectionCodeRepository.findById(id);
		return ResponseUtil.wrapOrNotFound(tSectionCode);
	}

	/**
	 * {@code DELETE  /t-section-codes/:id} : delete the "id" tSectionCode.
	 *
	 * @param id the id of the tSectionCode to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/t-section-codes/{id}")
	public ResponseEntity<Void> deleteTSectionCode(@PathVariable Long id) {
		log.debug("REST request to delete TSectionCode : {}", id);
		tSectionCodeRepository.deleteById(id);
		return ResponseEntity
			.noContent()
			.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
			.build();
	}
}
