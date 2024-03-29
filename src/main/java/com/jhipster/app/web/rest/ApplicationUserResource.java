package com.jhipster.app.web.rest;

import com.jhipster.app.domain.ApplicationUser;
import com.jhipster.app.domain.TUserRole;
import com.jhipster.app.domain.TUserRoleCode;
import com.jhipster.app.domain.User;
import com.jhipster.app.repository.ApplicationUserRepository;
import com.jhipster.app.service.ApplicationUserQueryService;
import com.jhipster.app.service.ApplicationUserService;
import com.jhipster.app.service.UserService;
import com.jhipster.app.service.criteria.ApplicationUserCriteria;
import com.jhipster.app.service.dto.ApplicationUserDTO;
import com.jhipster.app.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.jhipster.app.domain.ApplicationUser}.
 */
@RestController
@RequestMapping("/api")
public class ApplicationUserResource {

	private final Logger log = LoggerFactory.getLogger(ApplicationUserResource.class);

	private static final String ENTITY_NAME = "applicationUser";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final UserService userService;

	private final ApplicationUserService applicationUserService;

	private final ApplicationUserRepository applicationUserRepository;

	private final ApplicationUserQueryService applicationUserQueryService;

	public ApplicationUserResource(
		ApplicationUserService applicationUserService,
		ApplicationUserRepository applicationUserRepository,
		ApplicationUserQueryService applicationUserQueryService,
		UserService userService
	) {
		this.applicationUserService = applicationUserService;
		this.applicationUserRepository = applicationUserRepository;
		this.applicationUserQueryService = applicationUserQueryService;
		this.userService = userService;
	}

	/**
	 * {@code POST  /application-users} : Create a new applicationUser.
	 *
	 * @param applicationUser the applicationUser to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new applicationUser, or with status {@code 400 (Bad Request)} if the applicationUser has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	// @PostMapping("/application-users")
	// public ResponseEntity<User> createApplicationUser(@Valid @RequestBody ApplicationUserDTO applicationUser)
	// 	throws URISyntaxException {
	// 	log.debug("REST request to save ApplicationUser : {}", applicationUser);
	// 	if (applicationUser.getId() != null) {
	// 		throw new BadRequestAlertException("A new applicationUser cannot already have an ID", ENTITY_NAME, "idexists");
	// 	}
	// 	System.out.println("Saving User: " + applicationUser);

	// 	User newUser = userService.createUser(applicationUser.getInternalUser(), applicationUser);
	// 	// ApplicationUser result = applicationUserService.save(applicationUser);
	// 	return ResponseEntity
	// 		.created(new URI("/api/application-users/" + newUser.getId()))
	// 		.headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, newUser.getId().toString()))
	// 		.body(newUser);
	// }

	@PostMapping("/application-users")
	public ResponseEntity<ApplicationUser> createApplicationUser(@Valid @RequestBody ApplicationUserDTO applicationUser)
		throws URISyntaxException {
		log.debug("REST request to save ApplicationUser : {}", applicationUser);
		if (applicationUser.getId() != null) {
			throw new BadRequestAlertException("A new applicationUser cannot already have an ID", ENTITY_NAME, "idexists");
		}

		ApplicationUser newUser = userService.createApplicationUser(applicationUser.getInternalUser(), applicationUser);
		return ResponseEntity
			.created(new URI("/api/application-users/" + newUser.getId()))
			.headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, newUser.getId().toString()))
			.body(newUser);
	}

	/**
	 * {@code PUT  /application-users/:id} : Updates an existing applicationUser.
	 *
	 * @param id the id of the applicationUser to save.
	 * @param applicationUser the applicationUser to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated applicationUser,
	 * or with status {@code 400 (Bad Request)} if the applicationUser is not valid,
	 * or with status {@code 500 (Internal Server Error)} if the applicationUser couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/application-users/{id}")
	public ResponseEntity<ApplicationUser> updateApplicationUser(
		@PathVariable(value = "id", required = false) final Long id,
		@Valid @RequestBody ApplicationUser applicationUser
	) throws URISyntaxException {
		log.debug("REST request to update ApplicationUser : {}, {}", id, applicationUser);
		if (applicationUser.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, applicationUser.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!applicationUserRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		ApplicationUser result = applicationUserService.update(applicationUser);
		return ResponseEntity
			.ok()
			.headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, applicationUser.getId().toString()))
			.body(result);
	}

	/**
	 * {@code PATCH  /application-users/:id} : Partial updates given fields of an existing applicationUser, field will ignore if it is null
	 *
	 * @param id the id of the applicationUser to save.
	 * @param applicationUser the applicationUser to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated applicationUser,
	 * or with status {@code 400 (Bad Request)} if the applicationUser is not valid,
	 * or with status {@code 404 (Not Found)} if the applicationUser is not found,
	 * or with status {@code 500 (Internal Server Error)} if the applicationUser couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PatchMapping(value = "/application-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
	public ResponseEntity<ApplicationUser> partialUpdateApplicationUser(
		@PathVariable(value = "id", required = false) final Long id,
		@NotNull @RequestBody ApplicationUser applicationUser
	) throws URISyntaxException {
		log.debug("REST request to partial update ApplicationUser partially : {}, {}", id, applicationUser);
		if (applicationUser.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, applicationUser.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!applicationUserRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		Optional<ApplicationUser> result = applicationUserService.partialUpdate(applicationUser);

		return ResponseUtil.wrapOrNotFound(
			result,
			HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, applicationUser.getId().toString())
		);
	}

	/**
	 * {@code GET  /application-users} : get all the applicationUsers.
	 *
	 * @param pageable the pagination information.
	 * @param criteria the criteria which the requested entities should match.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of applicationUsers in body.
	 */
	@GetMapping("/application-users")
	public ResponseEntity<List<ApplicationUser>> getAllApplicationUsers(
		ApplicationUserCriteria criteria,
		@org.springdoc.api.annotations.ParameterObject Pageable pageable
	) {
		log.debug("REST request to get ApplicationUsers by criteria: {}", criteria);
		Page<ApplicationUser> page = applicationUserQueryService.findByCriteria(criteria, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * {@code GET  /application-users/count} : count all the applicationUsers.
	 *
	 * @param criteria the criteria which the requested entities should match.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
	 */
	@GetMapping("/application-users/count")
	public ResponseEntity<Long> countApplicationUsers(ApplicationUserCriteria criteria) {
		log.debug("REST request to count ApplicationUsers by criteria: {}", criteria);
		return ResponseEntity.ok().body(applicationUserQueryService.countByCriteria(criteria));
	}

	/**
	 * {@code GET  /application-users/:id} : get the "id" applicationUser.
	 *
	 * @param id the id of the applicationUser to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the applicationUser, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/application-users/{id}")
	public ResponseEntity<ApplicationUser> getApplicationUser(@PathVariable Long id) {
		log.debug("REST request to get ApplicationUser : {}", id);
		Optional<ApplicationUser> applicationUser = applicationUserService.findOne(id);
		return ResponseUtil.wrapOrNotFound(applicationUser);
	}

	/**
	 * {@code DELETE  /application-users/:id} : delete the "id" applicationUser.
	 *
	 * @param id the id of the applicationUser to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/application-users/{id}")
	public ResponseEntity<Void> deleteApplicationUser(@PathVariable Long id) {
		log.debug("REST request to delete ApplicationUser : {}", id);
		applicationUserService.delete(id);
		userService.deleteUserWithId(id);
		return ResponseEntity
			.noContent()
			.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
			.build();
	}
}
