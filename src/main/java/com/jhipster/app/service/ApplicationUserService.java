package com.jhipster.app.service;

import com.jhipster.app.domain.ApplicationUser;
import com.jhipster.app.domain.Authority;
import com.jhipster.app.domain.TUserRole;
import com.jhipster.app.repository.ApplicationUserRepository;
import com.jhipster.app.repository.AuthorityRepository;
import com.jhipster.app.repository.TUserRoleRepository;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ApplicationUser}.
 */
@Service
@Transactional
public class ApplicationUserService {

	private final Logger log = LoggerFactory.getLogger(ApplicationUserService.class);

	private final TUserRoleRepository tUserRoleRepository;

	private final ApplicationUserRepository applicationUserRepository;

	private final AuthorityRepository authorityRepository;

	public ApplicationUserService(
		ApplicationUserRepository applicationUserRepository,
		TUserRoleRepository tUserRoleRepository,
		AuthorityRepository authorityRepository
	) {
		this.applicationUserRepository = applicationUserRepository;
		this.tUserRoleRepository = tUserRoleRepository;
		this.authorityRepository = authorityRepository;
	}

	/**
	 * Save a applicationUser.
	 *
	 * @param applicationUser the entity to save.
	 * @return the persisted entity.
	 */
	public ApplicationUser save(ApplicationUser applicationUser) {
		log.debug("Request to save ApplicationUser : {}", applicationUser);
		if (applicationUser.getInternalUser().getAuthorities() != null) {
			Set<Authority> authorities = applicationUser
				.getInternalUser()
				.getAuthorities()
				.stream()
				.map(authorityName -> {
					Authority authority = new Authority();
					authority.setName(authorityName.getName());
					return authority;
				})
				.collect(Collectors.toSet());
			applicationUser.getInternalUser().setAuthorities(authorities);
		}
		ApplicationUser savedUser = applicationUserRepository.save(applicationUser);
		tUserRoleRepository.saveAll(savedUser.getUserRoles());
		return savedUser;
	}

	/**
	 * Update a applicationUser.
	 *
	 * @param applicationUser the entity to save.
	 * @return the persisted entity.
	 */
	public ApplicationUser update(ApplicationUser applicationUser) {
		log.debug("Request to update ApplicationUser : {}", applicationUser);

		// find the difference and update the user roles
		// if a role is found in the new user roles but not in the old user roles, add it
		// if a role is found in the old user roles but not in the new user roles, remove it
		// if a role is found in both, do nothing
		Set<TUserRole> newUserRoles = applicationUser.getNewUserRoles();
		Set<TUserRole> oldUserRoles = applicationUser.getUserRoles();

		// System.out.println("New user roles: " + newUserRoles);
		// System.out.println("Old user roles: " + oldUserRoles);

		if (oldUserRoles.size() == 0 && newUserRoles.size() > 0) {
			System.out.println("Adding all roles");
			for (TUserRole newUserRole : newUserRoles) {
				System.out.println("Adding role: " + newUserRole.getRole());
				newUserRole.setUser(applicationUser);
				tUserRoleRepository.save(newUserRole);
			}
			return applicationUserRepository.save(applicationUser);
		}

		for (TUserRole newUserRole : newUserRoles) {
			if (!oldUserRoles.contains(newUserRole)) {
				System.out.println("Adding role: " + newUserRole.getRole());
				newUserRole.setUser(applicationUser);
				tUserRoleRepository.save(newUserRole);
			}
		}
		for (TUserRole oldUserRole : oldUserRoles) {
			if (!newUserRoles.contains(oldUserRole)) {
				System.out.println("Removing role: " + oldUserRole.getRole());

				// SET NULL TO FOREIGN KEY
				// oldUserRole.setUser(null);
				// tUserRoleRepository.save(oldUserRole);

				// DELETE THE ROLE
				tUserRoleRepository.delete(oldUserRole);
			}
		}

		return applicationUserRepository.save(applicationUser);
	}

	/**
	 * Partially update a applicationUser.
	 *
	 * @param applicationUser the entity to update partially.
	 * @return the persisted entity.
	 */
	public Optional<ApplicationUser> partialUpdate(ApplicationUser applicationUser) {
		log.debug("Request to partially update ApplicationUser : {}", applicationUser);

		return applicationUserRepository
			.findById(applicationUser.getId())
			.map(existingApplicationUser -> {
				if (applicationUser.getIc() != null) {
					existingApplicationUser.setIc(applicationUser.getIc());
				}
				if (applicationUser.getStatus() != null) {
					existingApplicationUser.setStatus(applicationUser.getStatus());
				}

				return existingApplicationUser;
			})
			.map(applicationUserRepository::save);
	}

	/**
	 * Get all the applicationUsers.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Transactional(readOnly = true)
	public Page<ApplicationUser> findAll(Pageable pageable) {
		log.debug("Request to get all ApplicationUsers");
		return applicationUserRepository.findAll(pageable);
	}

	/**
	 * Get one applicationUser by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Transactional(readOnly = true)
	public Optional<ApplicationUser> findOne(Long id) {
		log.debug("Request to get ApplicationUser : {}", id);
		System.out.println("Auth: " + applicationUserRepository.findById(id).get().getInternalUser().getAuthorities());
		for (TUserRole role : applicationUserRepository.findById(id).get().getUserRoles()) {
			System.out.println("Role: " + role.getRole());
		}
		System.out.println("UserRoles: " + applicationUserRepository.findById(id).get().getUserRoles());
		System.out.println("Result: " + applicationUserRepository.findById(id).get());
		return applicationUserRepository.findById(id);
	}

	/**
	 * Delete the applicationUser by id.
	 *
	 * @param id the id of the entity.
	 */
	public void delete(Long id) {
		log.debug("Request to delete ApplicationUser : {}", id);
		applicationUserRepository.deleteById(id);
	}
}
