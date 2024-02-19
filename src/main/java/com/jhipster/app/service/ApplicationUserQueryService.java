package com.jhipster.app.service;

import com.jhipster.app.domain.*; // for static metamodels
import com.jhipster.app.domain.ApplicationUser;
import com.jhipster.app.repository.ApplicationUserRepository;
import com.jhipster.app.service.criteria.ApplicationUserCriteria;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link ApplicationUser} entities in the database.
 * The main input is a {@link ApplicationUserCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApplicationUser} or a {@link Page} of {@link ApplicationUser} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApplicationUserQueryService extends QueryService<ApplicationUser> {

	private final Logger log = LoggerFactory.getLogger(ApplicationUserQueryService.class);

	private final ApplicationUserRepository applicationUserRepository;

	public ApplicationUserQueryService(ApplicationUserRepository applicationUserRepository) {
		this.applicationUserRepository = applicationUserRepository;
	}

	/**
	 * Return a {@link List} of {@link ApplicationUser} which matches the criteria from the database.
	 * @param criteria The object which holds all the filters, which the entities should match.
	 * @return the matching entities.
	 */
	@Transactional(readOnly = true)
	public List<ApplicationUser> findByCriteria(ApplicationUserCriteria criteria) {
		log.debug("find by criteria : {}", criteria);
		final Specification<ApplicationUser> specification = createSpecification(criteria);
		return applicationUserRepository.findAll(specification);
	}

	/**
	 * Return a {@link Page} of {@link ApplicationUser} which matches the criteria from the database.
	 * @param criteria The object which holds all the filters, which the entities should match.
	 * @param page The page, which should be returned.
	 * @return the matching entities.
	 */
	@Transactional(readOnly = true)
	public Page<ApplicationUser> findByCriteria(ApplicationUserCriteria criteria, Pageable page) {
		log.debug("find by criteria : {}, page: {}", criteria, page);
		final Specification<ApplicationUser> specification = createSpecification(criteria);
		for (ApplicationUser applicationUser : applicationUserRepository.findAll(specification, page)) {
			log.debug("applicationUser: {}", applicationUser.getInternalUser().getAuthorities());
		}
		return applicationUserRepository.findAll(specification, page);
	}

	/**
	 * Return the number of matching entities in the database.
	 * @param criteria The object which holds all the filters, which the entities should match.
	 * @return the number of matching entities.
	 */
	@Transactional(readOnly = true)
	public long countByCriteria(ApplicationUserCriteria criteria) {
		log.debug("count by criteria : {}", criteria);
		final Specification<ApplicationUser> specification = createSpecification(criteria);
		return applicationUserRepository.count(specification);
	}

	/**
	 * Function to convert {@link ApplicationUserCriteria} to a {@link Specification}
	 * @param criteria The object which holds all the filters, which the entities should match.
	 * @return the matching {@link Specification} of the entity.
	 */
	protected Specification<ApplicationUser> createSpecification(ApplicationUserCriteria criteria) {
		Specification<ApplicationUser> specification = Specification.where(null);
		if (criteria != null) {
			// This has to be called first, because the distinct method returns null
			if (criteria.getDistinct() != null) {
				specification = specification.and(distinct(criteria.getDistinct()));
			}
			if (criteria.getId() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getId(), ApplicationUser_.id));
			}
			if (criteria.getIc() != null) {
				specification = specification.and(buildStringSpecification(criteria.getIc(), ApplicationUser_.ic));
			}
			if (criteria.getStatus() != null) {
				specification = specification.and(buildStringSpecification(criteria.getStatus(), ApplicationUser_.status));
			}
			if (criteria.getInternalUserId() != null) {
				specification =
					specification.and(
						buildSpecification(
							criteria.getInternalUserId(),
							root -> root.join(ApplicationUser_.internalUser, JoinType.LEFT).get(User_.id)
						)
					);
			}
			if (criteria.getUserRoleId() != null) {
				specification =
					specification.and(
						buildSpecification(
							criteria.getUserRoleId(),
							root -> root.join(ApplicationUser_.userRoles, JoinType.LEFT).get(TUserRole_.id)
						)
					);
			}
			if (criteria.getDivisionId() != null) {
				specification =
					specification.and(
						buildSpecification(
							criteria.getDivisionId(),
							root -> root.join(ApplicationUser_.division, JoinType.LEFT).get(TSectionCode_.id)
						)
					);
			}
			if (criteria.getSectionId() != null) {
				specification =
					specification.and(
						buildSpecification(
							criteria.getSectionId(),
							root -> root.join(ApplicationUser_.section, JoinType.LEFT).get(TUnitCode_.id)
						)
					);
			}
			if (criteria.getUnitId() != null) {
				specification =
					specification.and(
						buildSpecification(
							criteria.getUnitId(),
							root -> root.join(ApplicationUser_.unit, JoinType.LEFT).get(TSubunitCode_.id)
						)
					);
			}
			if (criteria.getOfficeId() != null) {
				specification =
					specification.and(
						buildSpecification(
							criteria.getOfficeId(),
							root -> root.join(ApplicationUser_.office, JoinType.LEFT).get(TOfficeCode_.id)
						)
					);
			}
		}
		return specification;
	}
}
