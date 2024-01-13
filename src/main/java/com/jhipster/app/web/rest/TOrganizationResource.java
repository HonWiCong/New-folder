package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TOrgContactPerson;
import com.jhipster.app.domain.TOrganization;
import com.jhipster.app.domain.dto.TOrganizationDTO;
import com.jhipster.app.domain.mapper.TOrganizationMapper;
import com.jhipster.app.repository.TOrgContactPersonRepository;
import com.jhipster.app.repository.TOrganizationRepository;
import com.jhipster.app.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.jhipster.app.domain.TOrganization}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TOrganizationResource {

	private final Logger log = LoggerFactory.getLogger(TOrganizationResource.class);

	private static final String ENTITY_NAME = "tOrganization";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final TOrganizationRepository tOrganizationRepository;
	private final TOrgContactPersonRepository tOrgContactPersonRepository;

	public TOrganizationResource(TOrganizationRepository tOrganizationRepository, TOrgContactPersonRepository tOrgContactPersonRepository) {
		this.tOrganizationRepository = tOrganizationRepository;
		this.tOrgContactPersonRepository = tOrgContactPersonRepository;
	}

	/**
	 * {@code POST  /t-organizations} : Create a new tOrganization.
	 *
	 * @param tOrganization the tOrganization to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tOrganization, or with status {@code 400 (Bad Request)} if the tOrganization has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/t-organizations")
	public void createTOrganization(@RequestBody TOrganization tOrganization) throws URISyntaxException {
		if (tOrganization.getTOrgContactPeople() != null) {
			for (TOrgContactPerson tOrgContactPerson : tOrganization.getTOrgContactPeople()) {
				System.out.println("Printed");
				tOrgContactPersonRepository.save(tOrgContactPerson);
			}
		} else {
			System.out.println("No contact person");
		}

		// log.debug("REST request to save TOrganization : {}", tOrganization);
		if (tOrganization.getId() != null) {
			throw new BadRequestAlertException("A new tOrganization cannot already have an ID", ENTITY_NAME, "idexists");
		}
		TOrganization result = tOrganizationRepository.save(tOrganization);
		System.out.println("Repository: " + result);
		// return ResponseEntity
		//     .created(new URI("/api/t-organizations/" + result.getId()))
		//     .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
		//     .body(result);
	}

	/**
	 * {@code PUT  /t-organizations/:id} : Updates an existing tOrganization.
	 *
	 * @param id the id of the tOrganization to save.
	 * @param tOrganization the tOrganization to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tOrganization,
	 * or with status {@code 400 (Bad Request)} if the tOrganization is not valid,
	 * or with status {@code 500 (Internal Server Error)} if the tOrganization couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/t-organizations/{id}")
	public void updateTOrganization(
		@PathVariable(value = "id", required = false) final Long id,
		@RequestBody TOrganizationDTO tOrganizationDTO
	) throws URISyntaxException {
		TOrganizationMapper mapper = Mappers.getMapper(TOrganizationMapper.class);
		TOrganization tOrganization = mapper.dtoToEntity(tOrganizationDTO);
		System.out.println("Object Converted: " + tOrganization.toString());

		// log.debug("REST request to update TOrganization : {}, {}", id, tOrganization);
		if (tOrganization.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, tOrganization.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!tOrganizationRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		System.out.println("Contact People: " + tOrganization.getTOrgContactPeople());
		System.out.println("Deleted ID: " + tOrganization.deletedId);

		if (tOrganization.getTOrgContactPeople() != null) {
			for (TOrgContactPerson tOrgContactPerson : tOrganization.getTOrgContactPeople()) {
				tOrgContactPersonRepository.save(tOrgContactPerson);
			}
		} else {
			System.out.println("No contact person");
		}

		if (tOrganization.deletedId != null) {
			System.out.println(" deletedId: " + tOrganization.deletedId);
			for (Long delete_id : tOrganization.deletedId) {
				tOrgContactPersonRepository.deleteById(delete_id);
				System.out.println("Delete Complete: " + delete_id);
			}
		} else {
			System.out.println("No contact person");
		}

		TOrganization result = tOrganizationRepository.save(tOrganization);
		// return ResponseEntity
		//     .ok()
		//     .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tOrganization.getId().toString()))
		//     .body(result);
	}

	/**
	 * {@code PATCH  /t-organizations/:id} : Partial updates given fields of an existing tOrganization, field will ignore if it is null
	 *
	 * @param id the id of the tOrganization to save.
	 * @param tOrganization the tOrganization to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tOrganization,
	 * or with status {@code 400 (Bad Request)} if the tOrganization is not valid,
	 * or with status {@code 404 (Not Found)} if the tOrganization is not found,
	 * or with status {@code 500 (Internal Server Error)} if the tOrganization couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PatchMapping(value = "/t-organizations/{id}", consumes = { "application/json", "application/merge-patch+json" })
	public ResponseEntity<TOrganization> partialUpdateTOrganization(
		@PathVariable(value = "id", required = false) final Long id,
		@RequestBody TOrganization tOrganization
	) throws URISyntaxException {
		log.debug("REST request to partial update TOrganization partially : {}, {}", id, tOrganization);
		if (tOrganization.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, tOrganization.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!tOrganizationRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		Optional<TOrganization> result = tOrganizationRepository
			.findById(tOrganization.getId())
			.map(existingTOrganization -> {
				if (tOrganization.getOrgHqid() != null) {
					existingTOrganization.setOrgHqid(tOrganization.getOrgHqid());
				}
				if (tOrganization.getOrgHqBr() != null) {
					existingTOrganization.setOrgHqBr(tOrganization.getOrgHqBr());
				}
				if (tOrganization.getOrgCode() != null) {
					existingTOrganization.setOrgCode(tOrganization.getOrgCode());
				}
				if (tOrganization.getOrgBrn() != null) {
					existingTOrganization.setOrgBrn(tOrganization.getOrgBrn());
				}
				if (tOrganization.getOrgPtaxid() != null) {
					existingTOrganization.setOrgPtaxid(tOrganization.getOrgPtaxid());
				}
				if (tOrganization.getOrgDefaultTaxCode() != null) {
					existingTOrganization.setOrgDefaultTaxCode(tOrganization.getOrgDefaultTaxCode());
				}
				if (tOrganization.getOrgCompanyGstNo() != null) {
					existingTOrganization.setOrgCompanyGstNo(tOrganization.getOrgCompanyGstNo());
				}
				if (tOrganization.getOrgCompanyGstRegDate() != null) {
					existingTOrganization.setOrgCompanyGstRegDate(tOrganization.getOrgCompanyGstRegDate());
				}
				if (tOrganization.getOrgCompanyGstDeregDate() != null) {
					existingTOrganization.setOrgCompanyGstDeregDate(tOrganization.getOrgCompanyGstDeregDate());
				}
				if (tOrganization.getOrgPoTaxInclusive() != null) {
					existingTOrganization.setOrgPoTaxInclusive(tOrganization.getOrgPoTaxInclusive());
				}
				if (tOrganization.getOrgName() != null) {
					existingTOrganization.setOrgName(tOrganization.getOrgName());
				}
				if (tOrganization.getOrgNameOther() != null) {
					existingTOrganization.setOrgNameOther(tOrganization.getOrgNameOther());
				}
				if (tOrganization.getOrgShortname() != null) {
					existingTOrganization.setOrgShortname(tOrganization.getOrgShortname());
				}
				if (tOrganization.getOrgAddress() != null) {
					existingTOrganization.setOrgAddress(tOrganization.getOrgAddress());
				}
				if (tOrganization.getOrgShippingAddress() != null) {
					existingTOrganization.setOrgShippingAddress(tOrganization.getOrgShippingAddress());
				}
				if (tOrganization.getOrgBillingAddress() != null) {
					existingTOrganization.setOrgBillingAddress(tOrganization.getOrgBillingAddress());
				}
				if (tOrganization.getOrgPostcode() != null) {
					existingTOrganization.setOrgPostcode(tOrganization.getOrgPostcode());
				}
				if (tOrganization.getOrgCity() != null) {
					existingTOrganization.setOrgCity(tOrganization.getOrgCity());
				}
				if (tOrganization.getOrgState() != null) {
					existingTOrganization.setOrgState(tOrganization.getOrgState());
				}
				if (tOrganization.getOrgCountry() != null) {
					existingTOrganization.setOrgCountry(tOrganization.getOrgCountry());
				}
				if (tOrganization.getOrgOffPhone1() != null) {
					existingTOrganization.setOrgOffPhone1(tOrganization.getOrgOffPhone1());
				}
				if (tOrganization.getOrgOffPhone2() != null) {
					existingTOrganization.setOrgOffPhone2(tOrganization.getOrgOffPhone2());
				}
				if (tOrganization.getOrgOffPhone3() != null) {
					existingTOrganization.setOrgOffPhone3(tOrganization.getOrgOffPhone3());
				}
				if (tOrganization.getOrgOffFax1() != null) {
					existingTOrganization.setOrgOffFax1(tOrganization.getOrgOffFax1());
				}
				if (tOrganization.getOrgOffFax2() != null) {
					existingTOrganization.setOrgOffFax2(tOrganization.getOrgOffFax2());
				}
				if (tOrganization.getOrgCredittermid() != null) {
					existingTOrganization.setOrgCredittermid(tOrganization.getOrgCredittermid());
				}
				if (tOrganization.getOrgCreditLimit() != null) {
					existingTOrganization.setOrgCreditLimit(tOrganization.getOrgCreditLimit());
				}
				if (tOrganization.getOrgAgencyid() != null) {
					existingTOrganization.setOrgAgencyid(tOrganization.getOrgAgencyid());
				}
				if (tOrganization.getOrgDivision() != null) {
					existingTOrganization.setOrgDivision(tOrganization.getOrgDivision());
				}
				if (tOrganization.getOrgDistrict() != null) {
					existingTOrganization.setOrgDistrict(tOrganization.getOrgDistrict());
				}
				if (tOrganization.getOrgWebsite() != null) {
					existingTOrganization.setOrgWebsite(tOrganization.getOrgWebsite());
				}
				if (tOrganization.getOrgEmail() != null) {
					existingTOrganization.setOrgEmail(tOrganization.getOrgEmail());
				}
				if (tOrganization.getOrgSupplierCategory() != null) {
					existingTOrganization.setOrgSupplierCategory(tOrganization.getOrgSupplierCategory());
				}
				if (tOrganization.getOrgCurrencyCode() != null) {
					existingTOrganization.setOrgCurrencyCode(tOrganization.getOrgCurrencyCode());
				}
				if (tOrganization.getOrgType() != null) {
					existingTOrganization.setOrgType(tOrganization.getOrgType());
				}
				if (tOrganization.getOrgSectorid() != null) {
					existingTOrganization.setOrgSectorid(tOrganization.getOrgSectorid());
				}
				if (tOrganization.getOrgSector() != null) {
					existingTOrganization.setOrgSector(tOrganization.getOrgSector());
				}
				if (tOrganization.getOrgIndustry() != null) {
					existingTOrganization.setOrgIndustry(tOrganization.getOrgIndustry());
				}
				if (tOrganization.getOrgSainsGroup() != null) {
					existingTOrganization.setOrgSainsGroup(tOrganization.getOrgSainsGroup());
				}
				if (tOrganization.getOrgBumiputra() != null) {
					existingTOrganization.setOrgBumiputra(tOrganization.getOrgBumiputra());
				}
				if (tOrganization.getOrgUpkReg() != null) {
					existingTOrganization.setOrgUpkReg(tOrganization.getOrgUpkReg());
				}
				if (tOrganization.getOrgMofReg() != null) {
					existingTOrganization.setOrgMofReg(tOrganization.getOrgMofReg());
				}
				if (tOrganization.getOrgDesignation() != null) {
					existingTOrganization.setOrgDesignation(tOrganization.getOrgDesignation());
				}
				if (tOrganization.getOrgContpersonTitle() != null) {
					existingTOrganization.setOrgContpersonTitle(tOrganization.getOrgContpersonTitle());
				}
				if (tOrganization.getOrgContperson() != null) {
					existingTOrganization.setOrgContperson(tOrganization.getOrgContperson());
				}
				if (tOrganization.getOrgDirectline() != null) {
					existingTOrganization.setOrgDirectline(tOrganization.getOrgDirectline());
				}
				if (tOrganization.getOrgContpEmail() != null) {
					existingTOrganization.setOrgContpEmail(tOrganization.getOrgContpEmail());
				}
				if (tOrganization.getOrgContpHp() != null) {
					existingTOrganization.setOrgContpHp(tOrganization.getOrgContpHp());
				}
				if (tOrganization.getOrgRemarks() != null) {
					existingTOrganization.setOrgRemarks(tOrganization.getOrgRemarks());
				}
				if (tOrganization.getOrgActiveStatus() != null) {
					existingTOrganization.setOrgActiveStatus(tOrganization.getOrgActiveStatus());
				}
				if (tOrganization.getOrgCcGc() != null) {
					existingTOrganization.setOrgCcGc(tOrganization.getOrgCcGc());
				}
				if (tOrganization.getOrgCustomerCateCode() != null) {
					existingTOrganization.setOrgCustomerCateCode(tOrganization.getOrgCustomerCateCode());
				}
				if (tOrganization.getOrgVendorCateCode() != null) {
					existingTOrganization.setOrgVendorCateCode(tOrganization.getOrgVendorCateCode());
				}
				if (tOrganization.getOrgSalesCateCode() != null) {
					existingTOrganization.setOrgSalesCateCode(tOrganization.getOrgSalesCateCode());
				}
				if (tOrganization.getOrgOutstandingBalance() != null) {
					existingTOrganization.setOrgOutstandingBalance(tOrganization.getOrgOutstandingBalance());
				}
				if (tOrganization.getOrgOutstandingBalanceEx() != null) {
					existingTOrganization.setOrgOutstandingBalanceEx(tOrganization.getOrgOutstandingBalanceEx());
				}
				if (tOrganization.getOrgCompanyCode() != null) {
					existingTOrganization.setOrgCompanyCode(tOrganization.getOrgCompanyCode());
				}
				if (tOrganization.getOrgDcrownPostStatus() != null) {
					existingTOrganization.setOrgDcrownPostStatus(tOrganization.getOrgDcrownPostStatus());
				}
				if (tOrganization.getConfirmedBy() != null) {
					existingTOrganization.setConfirmedBy(tOrganization.getConfirmedBy());
				}
				if (tOrganization.getConfirmedDate() != null) {
					existingTOrganization.setConfirmedDate(tOrganization.getConfirmedDate());
				}
				if (tOrganization.getEnteredBy() != null) {
					existingTOrganization.setEnteredBy(tOrganization.getEnteredBy());
				}
				if (tOrganization.getEnteredDate() != null) {
					existingTOrganization.setEnteredDate(tOrganization.getEnteredDate());
				}
				if (tOrganization.getModifiedBy() != null) {
					existingTOrganization.setModifiedBy(tOrganization.getModifiedBy());
				}
				if (tOrganization.getModifiedDate() != null) {
					existingTOrganization.setModifiedDate(tOrganization.getModifiedDate());
				}

				return existingTOrganization;
			})
			.map(tOrganizationRepository::save);

		return ResponseUtil.wrapOrNotFound(
			result,
			HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tOrganization.getId().toString())
		);
	}

	/**
	 * {@code GET  /t-organizations} : get all the tOrganizations.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tOrganizations in body.
	 */
	@GetMapping("/t-organizations")
	public List<TOrganization> getAllTOrganizations() {
		// log.debug("REST request to get all TOrganizations");
		return tOrganizationRepository.findAll();
	}

	/**
	 * {@code GET  /t-organizations/:id} : get the "id" tOrganization.
	 *
	 * @param id the id of the tOrganization to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tOrganization, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/t-organizations/{id}")
	public ResponseEntity<TOrganization> getTOrganization(@PathVariable Long id) {
		// log.debug("REST request to get TOrganization : {}", id);
		Optional<TOrganization> tOrganization = tOrganizationRepository.findById(id);
		return ResponseUtil.wrapOrNotFound(tOrganization);
	}

	/**
	 * {@code DELETE  /t-organizations/:id} : delete the "id" tOrganization.
	 *
	 * @param id the id of the tOrganization to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/t-organizations/{id}")
	public ResponseEntity<Void> deleteTOrganization(@PathVariable Long id) {
		log.debug("REST request to delete TOrganization : {}", id);
		tOrganizationRepository.deleteById(id);
		return ResponseEntity
			.noContent()
			.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
			.build();
	}
}
