package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TUserRole;
import com.jhipster.app.repository.TUserRoleRepository;
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
 * REST controller for managing {@link com.jhipster.app.domain.TUserRole}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TUserRoleResource {

	private final Logger log = LoggerFactory.getLogger(TUserRoleResource.class);

	private static final String ENTITY_NAME = "tUserRole";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final TUserRoleRepository tUserRoleRepository;

	public TUserRoleResource(TUserRoleRepository tUserRoleRepository) {
		this.tUserRoleRepository = tUserRoleRepository;
	}

	/**
	 * {@code POST  /t-user-roles} : Create a new tUserRole.
	 *
	 * @param tUserRole the tUserRole to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tUserRole, or with status {@code 400 (Bad Request)} if the tUserRole has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/t-user-roles")
	public ResponseEntity<TUserRole> createTUserRole(@RequestBody TUserRole tUserRole) throws URISyntaxException {
		log.debug("REST request to save TUserRole : {}", tUserRole);
		if (tUserRole.getId() != null) {
			throw new BadRequestAlertException("A new tUserRole cannot already have an ID", ENTITY_NAME, "idexists");
		}
		TUserRole result = tUserRoleRepository.save(tUserRole);
		return ResponseEntity
			.created(new URI("/api/t-user-roles/" + result.getId()))
			.headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
			.body(result);
	}

	/**
	 * {@code PUT  /t-user-roles/:id} : Updates an existing tUserRole.
	 *
	 * @param id the id of the tUserRole to save.
	 * @param tUserRole the tUserRole to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tUserRole,
	 * or with status {@code 400 (Bad Request)} if the tUserRole is not valid,
	 * or with status {@code 500 (Internal Server Error)} if the tUserRole couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/t-user-roles/{id}")
	public ResponseEntity<TUserRole> updateTUserRole(
		@PathVariable(value = "id", required = false) final Long id,
		@RequestBody TUserRole tUserRole
	) throws URISyntaxException {
		log.debug("REST request to update TUserRole : {}, {}", id, tUserRole);
		if (tUserRole.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, tUserRole.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!tUserRoleRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		TUserRole result = tUserRoleRepository.save(tUserRole);
		return ResponseEntity
			.ok()
			.headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tUserRole.getId().toString()))
			.body(result);
	}

	/**
	 * {@code PATCH  /t-user-roles/:id} : Partial updates given fields of an existing tUserRole, field will ignore if it is null
	 *
	 * @param id the id of the tUserRole to save.
	 * @param tUserRole the tUserRole to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tUserRole,
	 * or with status {@code 400 (Bad Request)} if the tUserRole is not valid,
	 * or with status {@code 404 (Not Found)} if the tUserRole is not found,
	 * or with status {@code 500 (Internal Server Error)} if the tUserRole couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PatchMapping(value = "/t-user-roles/{id}", consumes = { "application/json", "application/merge-patch+json" })
	public ResponseEntity<TUserRole> partialUpdateTUserRole(
		@PathVariable(value = "id", required = false) final Long id,
		@RequestBody TUserRole tUserRole
	) throws URISyntaxException {
		log.debug("REST request to partial update TUserRole partially : {}, {}", id, tUserRole);
		if (tUserRole.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, tUserRole.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!tUserRoleRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		Optional<TUserRole> result = tUserRoleRepository
			.findById(tUserRole.getId())
			.map(existingTUserRole -> {
				if (tUserRole.getSysuserId() != null) {
					existingTUserRole.setSysuserId(tUserRole.getSysuserId());
				}
				if (tUserRole.getUsrRoleid() != null) {
					existingTUserRole.setUsrRoleid(tUserRole.getUsrRoleid());
				}
				if (tUserRole.getEnteredBy() != null) {
					existingTUserRole.setEnteredBy(tUserRole.getEnteredBy());
				}
				if (tUserRole.getEnteredDate() != null) {
					existingTUserRole.setEnteredDate(tUserRole.getEnteredDate());
				}
				if (tUserRole.getModifiedBy() != null) {
					existingTUserRole.setModifiedBy(tUserRole.getModifiedBy());
				}
				if (tUserRole.getModifiedDate() != null) {
					existingTUserRole.setModifiedDate(tUserRole.getModifiedDate());
				}

				return existingTUserRole;
			})
			.map(tUserRoleRepository::save);

		return ResponseUtil.wrapOrNotFound(
			result,
			HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tUserRole.getId().toString())
		);
	}

	/**
	 * {@code GET  /t-user-roles} : get all the tUserRoles.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tUserRoles in body.
	 */
	@GetMapping("/t-user-roles")
	public List<TUserRole> getAllTUserRoles() {
		log.debug("REST request to get all TUserRoles");
		return tUserRoleRepository.findAll();
	}

	/**
	 * {@code GET  /t-user-roles/:id} : get the "id" tUserRole.
	 *
	 * @param id the id of the tUserRole to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tUserRole, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/t-user-roles/{id}")
	public ResponseEntity<TUserRole> getTUserRole(@PathVariable Long id) {
		log.debug("REST request to get TUserRole : {}", id);
		Optional<TUserRole> tUserRole = tUserRoleRepository.findById(id);
		return ResponseUtil.wrapOrNotFound(tUserRole);
	}

	/**
	 * {@code DELETE  /t-user-roles/:id} : delete the "id" tUserRole.
	 *
	 * @param id the id of the tUserRole to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/t-user-roles/{id}")
	public ResponseEntity<Void> deleteTUserRole(@PathVariable Long id) {
		log.debug("REST request to delete TUserRole : {}", id);
		tUserRoleRepository.deleteById(id);
		return ResponseEntity
			.noContent()
			.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
			.build();
	}
}
