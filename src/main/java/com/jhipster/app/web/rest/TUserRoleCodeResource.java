package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TUserRoleCode;
import com.jhipster.app.repository.TUserRoleCodeRepository;
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
 * REST controller for managing {@link com.jhipster.app.domain.TUserRoleCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TUserRoleCodeResource {

	private final Logger log = LoggerFactory.getLogger(TUserRoleCodeResource.class);

	private static final String ENTITY_NAME = "tUserRoleCode";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final TUserRoleCodeRepository tUserRoleCodeRepository;

	public TUserRoleCodeResource(TUserRoleCodeRepository tUserRoleCodeRepository) {
		this.tUserRoleCodeRepository = tUserRoleCodeRepository;
	}

	/**
	 * {@code POST  /t-user-role-codes} : Create a new tUserRoleCode.
	 *
	 * @param tUserRoleCode the tUserRoleCode to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tUserRoleCode, or with status {@code 400 (Bad Request)} if the tUserRoleCode has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/t-user-role-codes")
	public ResponseEntity<TUserRoleCode> createTUserRoleCode(@RequestBody TUserRoleCode tUserRoleCode) throws URISyntaxException {
		log.debug("REST request to save TUserRoleCode : {}", tUserRoleCode);
		if (tUserRoleCode.getId() != null) {
			throw new BadRequestAlertException("A new tUserRoleCode cannot already have an ID", ENTITY_NAME, "idexists");
		}
		TUserRoleCode result = tUserRoleCodeRepository.save(tUserRoleCode);
		return ResponseEntity
			.created(new URI("/api/t-user-role-codes/" + result.getId()))
			.headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
			.body(result);
	}

	/**
	 * {@code PUT  /t-user-role-codes/:id} : Updates an existing tUserRoleCode.
	 *
	 * @param id the id of the tUserRoleCode to save.
	 * @param tUserRoleCode the tUserRoleCode to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tUserRoleCode,
	 * or with status {@code 400 (Bad Request)} if the tUserRoleCode is not valid,
	 * or with status {@code 500 (Internal Server Error)} if the tUserRoleCode couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/t-user-role-codes/{id}")
	public ResponseEntity<TUserRoleCode> updateTUserRoleCode(
		@PathVariable(value = "id", required = false) final Long id,
		@RequestBody TUserRoleCode tUserRoleCode
	) throws URISyntaxException {
		log.debug("REST request to update TUserRoleCode : {}, {}", id, tUserRoleCode);
		if (tUserRoleCode.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, tUserRoleCode.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!tUserRoleCodeRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		TUserRoleCode result = tUserRoleCodeRepository.save(tUserRoleCode);
		return ResponseEntity
			.ok()
			.headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tUserRoleCode.getId().toString()))
			.body(result);
	}

	/**
	 * {@code PATCH  /t-user-role-codes/:id} : Partial updates given fields of an existing tUserRoleCode, field will ignore if it is null
	 *
	 * @param id the id of the tUserRoleCode to save.
	 * @param tUserRoleCode the tUserRoleCode to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tUserRoleCode,
	 * or with status {@code 400 (Bad Request)} if the tUserRoleCode is not valid,
	 * or with status {@code 404 (Not Found)} if the tUserRoleCode is not found,
	 * or with status {@code 500 (Internal Server Error)} if the tUserRoleCode couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PatchMapping(value = "/t-user-role-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
	public ResponseEntity<TUserRoleCode> partialUpdateTUserRoleCode(
		@PathVariable(value = "id", required = false) final Long id,
		@RequestBody TUserRoleCode tUserRoleCode
	) throws URISyntaxException {
		log.debug("REST request to partial update TUserRoleCode partially : {}, {}", id, tUserRoleCode);
		if (tUserRoleCode.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, tUserRoleCode.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!tUserRoleCodeRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		Optional<TUserRoleCode> result = tUserRoleCodeRepository
			.findById(tUserRoleCode.getId())
			.map(existingTUserRoleCode -> {
				if (tUserRoleCode.getRoleName() != null) {
					existingTUserRoleCode.setRoleName(tUserRoleCode.getRoleName());
				}
				if (tUserRoleCode.getRoleHead() != null) {
					existingTUserRoleCode.setRoleHead(tUserRoleCode.getRoleHead());
				}
				if (tUserRoleCode.getActApprover1() != null) {
					existingTUserRoleCode.setActApprover1(tUserRoleCode.getActApprover1());
				}
				if (tUserRoleCode.getActApprover2() != null) {
					existingTUserRoleCode.setActApprover2(tUserRoleCode.getActApprover2());
				}
				if (tUserRoleCode.getEnteredBy() != null) {
					existingTUserRoleCode.setEnteredBy(tUserRoleCode.getEnteredBy());
				}
				if (tUserRoleCode.getEnteredDate() != null) {
					existingTUserRoleCode.setEnteredDate(tUserRoleCode.getEnteredDate());
				}
				if (tUserRoleCode.getModifiedBy() != null) {
					existingTUserRoleCode.setModifiedBy(tUserRoleCode.getModifiedBy());
				}
				if (tUserRoleCode.getModifiedDate() != null) {
					existingTUserRoleCode.setModifiedDate(tUserRoleCode.getModifiedDate());
				}

				return existingTUserRoleCode;
			})
			.map(tUserRoleCodeRepository::save);

		return ResponseUtil.wrapOrNotFound(
			result,
			HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tUserRoleCode.getId().toString())
		);
	}

	/**
	 * {@code GET  /t-user-role-codes} : get all the tUserRoleCodes.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tUserRoleCodes in body.
	 */
	@GetMapping("/t-user-role-codes")
	public List<TUserRoleCode> getAllTUserRoleCodes() {
		log.debug("REST request to get all TUserRoleCodes");
		return tUserRoleCodeRepository.findAll();
	}

	/**
	 * {@code GET  /t-user-role-codes/:id} : get the "id" tUserRoleCode.
	 *
	 * @param id the id of the tUserRoleCode to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tUserRoleCode, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/t-user-role-codes/{id}")
	public ResponseEntity<TUserRoleCode> getTUserRoleCode(@PathVariable Long id) {
		log.debug("REST request to get TUserRoleCode : {}", id);
		Optional<TUserRoleCode> tUserRoleCode = tUserRoleCodeRepository.findById(id);
		return ResponseUtil.wrapOrNotFound(tUserRoleCode);
	}

	/**
	 * {@code DELETE  /t-user-role-codes/:id} : delete the "id" tUserRoleCode.
	 *
	 * @param id the id of the tUserRoleCode to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/t-user-role-codes/{id}")
	public ResponseEntity<Void> deleteTUserRoleCode(@PathVariable Long id) {
		log.debug("REST request to delete TUserRoleCode : {}", id);
		tUserRoleCodeRepository.deleteById(id);
		return ResponseEntity
			.noContent()
			.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
			.build();
	}
}
