package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TOfficeCode;
import com.jhipster.app.repository.TOfficeCodeRepository;
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
 * REST controller for managing {@link com.jhipster.app.domain.TOfficeCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TOfficeCodeResource {

	private final Logger log = LoggerFactory.getLogger(TOfficeCodeResource.class);

	private static final String ENTITY_NAME = "tOfficeCode";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final TOfficeCodeRepository tOfficeCodeRepository;

	public TOfficeCodeResource(TOfficeCodeRepository tOfficeCodeRepository) {
		this.tOfficeCodeRepository = tOfficeCodeRepository;
	}

	/**
	 * {@code POST  /t-office-codes} : Create a new tOfficeCode.
	 *
	 * @param tOfficeCode the tOfficeCode to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tOfficeCode, or with status {@code 400 (Bad Request)} if the tOfficeCode has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/t-office-codes")
	public ResponseEntity<TOfficeCode> createTOfficeCode(@RequestBody TOfficeCode tOfficeCode) throws URISyntaxException {
		log.debug("REST request to save TOfficeCode : {}", tOfficeCode);
		if (tOfficeCode.getId() != null) {
			throw new BadRequestAlertException("A new tOfficeCode cannot already have an ID", ENTITY_NAME, "idexists");
		}
		TOfficeCode result = tOfficeCodeRepository.save(tOfficeCode);
		return ResponseEntity
			.created(new URI("/api/t-office-codes/" + result.getId()))
			.headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
			.body(result);
	}

	/**
	 * {@code PUT  /t-office-codes/:id} : Updates an existing tOfficeCode.
	 *
	 * @param id the id of the tOfficeCode to save.
	 * @param tOfficeCode the tOfficeCode to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tOfficeCode,
	 * or with status {@code 400 (Bad Request)} if the tOfficeCode is not valid,
	 * or with status {@code 500 (Internal Server Error)} if the tOfficeCode couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/t-office-codes/{id}")
	public ResponseEntity<TOfficeCode> updateTOfficeCode(
		@PathVariable(value = "id", required = false) final Long id,
		@RequestBody TOfficeCode tOfficeCode
	) throws URISyntaxException {
		log.debug("REST request to update TOfficeCode : {}, {}", id, tOfficeCode);
		if (tOfficeCode.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, tOfficeCode.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!tOfficeCodeRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		TOfficeCode result = tOfficeCodeRepository.save(tOfficeCode);
		return ResponseEntity
			.ok()
			.headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tOfficeCode.getId().toString()))
			.body(result);
	}

	/**
	 * {@code PATCH  /t-office-codes/:id} : Partial updates given fields of an existing tOfficeCode, field will ignore if it is null
	 *
	 * @param id the id of the tOfficeCode to save.
	 * @param tOfficeCode the tOfficeCode to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tOfficeCode,
	 * or with status {@code 400 (Bad Request)} if the tOfficeCode is not valid,
	 * or with status {@code 404 (Not Found)} if the tOfficeCode is not found,
	 * or with status {@code 500 (Internal Server Error)} if the tOfficeCode couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PatchMapping(value = "/t-office-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
	public ResponseEntity<TOfficeCode> partialUpdateTOfficeCode(
		@PathVariable(value = "id", required = false) final Long id,
		@RequestBody TOfficeCode tOfficeCode
	) throws URISyntaxException {
		log.debug("REST request to partial update TOfficeCode partially : {}, {}", id, tOfficeCode);
		if (tOfficeCode.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, tOfficeCode.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!tOfficeCodeRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		Optional<TOfficeCode> result = tOfficeCodeRepository
			.findById(tOfficeCode.getId())
			.map(existingTOfficeCode -> {
				if (tOfficeCode.getOffName() != null) {
					existingTOfficeCode.setOffName(tOfficeCode.getOffName());
				}
				if (tOfficeCode.getOffAddress() != null) {
					existingTOfficeCode.setOffAddress(tOfficeCode.getOffAddress());
				}
				if (tOfficeCode.getOffPostcode() != null) {
					existingTOfficeCode.setOffPostcode(tOfficeCode.getOffPostcode());
				}
				if (tOfficeCode.getOffCity() != null) {
					existingTOfficeCode.setOffCity(tOfficeCode.getOffCity());
				}
				if (tOfficeCode.getOffState() != null) {
					existingTOfficeCode.setOffState(tOfficeCode.getOffState());
				}
				if (tOfficeCode.getOffOffphone() != null) {
					existingTOfficeCode.setOffOffphone(tOfficeCode.getOffOffphone());
				}
				if (tOfficeCode.getOffOfffax() != null) {
					existingTOfficeCode.setOffOfffax(tOfficeCode.getOffOfffax());
				}
				if (tOfficeCode.getEnteredBy() != null) {
					existingTOfficeCode.setEnteredBy(tOfficeCode.getEnteredBy());
				}
				if (tOfficeCode.getEnteredDate() != null) {
					existingTOfficeCode.setEnteredDate(tOfficeCode.getEnteredDate());
				}
				if (tOfficeCode.getModifiedBy() != null) {
					existingTOfficeCode.setModifiedBy(tOfficeCode.getModifiedBy());
				}
				if (tOfficeCode.getModifiedDate() != null) {
					existingTOfficeCode.setModifiedDate(tOfficeCode.getModifiedDate());
				}

				return existingTOfficeCode;
			})
			.map(tOfficeCodeRepository::save);

		return ResponseUtil.wrapOrNotFound(
			result,
			HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tOfficeCode.getId().toString())
		);
	}

	/**
	 * {@code GET  /t-office-codes} : get all the tOfficeCodes.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tOfficeCodes in body.
	 */
	@GetMapping("/t-office-codes")
	public List<TOfficeCode> getAllTOfficeCodes() {
		log.debug("REST request to get all TOfficeCodes");
		return tOfficeCodeRepository.findAll();
	}

	/**
	 * {@code GET  /t-office-codes/:id} : get the "id" tOfficeCode.
	 *
	 * @param id the id of the tOfficeCode to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tOfficeCode, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/t-office-codes/{id}")
	public ResponseEntity<TOfficeCode> getTOfficeCode(@PathVariable Long id) {
		log.debug("REST request to get TOfficeCode : {}", id);
		Optional<TOfficeCode> tOfficeCode = tOfficeCodeRepository.findById(id);
		return ResponseUtil.wrapOrNotFound(tOfficeCode);
	}

	/**
	 * {@code DELETE  /t-office-codes/:id} : delete the "id" tOfficeCode.
	 *
	 * @param id the id of the tOfficeCode to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/t-office-codes/{id}")
	public ResponseEntity<Void> deleteTOfficeCode(@PathVariable Long id) {
		log.debug("REST request to delete TOfficeCode : {}", id);
		tOfficeCodeRepository.deleteById(id);
		return ResponseEntity
			.noContent()
			.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
			.build();
	}
}
