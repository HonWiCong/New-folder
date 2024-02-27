import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

// prettier-ignore
const TCountryCode = () => import('@/entities/t-country-code/t-country-code.vue');
// prettier-ignore
const TCountryCodeUpdate = () => import('@/entities/t-country-code/t-country-code-update.vue');
// prettier-ignore
const TCountryCodeDetails = () => import('@/entities/t-country-code/t-country-code-details.vue');
// prettier-ignore
const TStateCode = () => import('@/entities/t-state-code/t-state-code.vue');
// prettier-ignore
const TStateCodeUpdate = () => import('@/entities/t-state-code/t-state-code-update.vue');
// prettier-ignore
const TStateCodeDetails = () => import('@/entities/t-state-code/t-state-code-details.vue');
// prettier-ignore
const TDivisionCode = () => import('@/entities/t-division-code/t-division-code.vue');
// prettier-ignore
const TDivisionCodeUpdate = () => import('@/entities/t-division-code/t-division-code-update.vue');
// prettier-ignore
const TDivisionCodeDetails = () => import('@/entities/t-division-code/t-division-code-details.vue');
// prettier-ignore
const TDistrictCode = () => import('@/entities/t-district-code/t-district-code.vue');
// prettier-ignore
const TDistrictCodeUpdate = () => import('@/entities/t-district-code/t-district-code-update.vue');
// prettier-ignore
const TDistrictCodeDetails = () => import('@/entities/t-district-code/t-district-code-details.vue');
// prettier-ignore
const TCityCode = () => import('@/entities/t-city-code/t-city-code.vue');
// prettier-ignore
const TCityCodeUpdate = () => import('@/entities/t-city-code/t-city-code-update.vue');
// prettier-ignore
const TCityCodeDetails = () => import('@/entities/t-city-code/t-city-code-details.vue');
// prettier-ignore
const TIndustryCode = () => import('@/entities/t-industry-code/t-industry-code.vue');
// prettier-ignore
const TIndustryCodeUpdate = () => import('@/entities/t-industry-code/t-industry-code-update.vue');
// prettier-ignore
const TIndustryCodeDetails = () => import('@/entities/t-industry-code/t-industry-code-details.vue');
// prettier-ignore
const TSectorCode = () => import('@/entities/t-sector-code/t-sector-code.vue');
// prettier-ignore
const TSectorCodeUpdate = () => import('@/entities/t-sector-code/t-sector-code-update.vue');
// prettier-ignore
const TSectorCodeDetails = () => import('@/entities/t-sector-code/t-sector-code-details.vue');
// prettier-ignore
const TBrandCode = () => import('@/entities/t-brand-code/t-brand-code.vue');
// prettier-ignore
const TBrandCodeUpdate = () => import('@/entities/t-brand-code/t-brand-code-update.vue');
// prettier-ignore
const TBrandCodeDetails = () => import('@/entities/t-brand-code/t-brand-code-details.vue');
// prettier-ignore
const TAuditTrail = () => import('@/entities/t-audit-trail/t-audit-trail.vue');
// prettier-ignore
const TAuditTrailUpdate = () => import('@/entities/t-audit-trail/t-audit-trail-update.vue');
// prettier-ignore
const TAuditTrailDetails = () => import('@/entities/t-audit-trail/t-audit-trail-details.vue');
// prettier-ignore
const TSupplierCategory = () => import('@/entities/t-supplier-category/t-supplier-category.vue');
// prettier-ignore
const TSupplierCategoryUpdate = () => import('@/entities/t-supplier-category/t-supplier-category-update.vue');
// prettier-ignore
const TSupplierCategoryDetails = () => import('@/entities/t-supplier-category/t-supplier-category-details.vue');
// prettier-ignore
const TSmTax = () => import('@/entities/t-sm-tax/t-sm-tax.vue');
// prettier-ignore
const TSmTaxUpdate = () => import('@/entities/t-sm-tax/t-sm-tax-update.vue');
// prettier-ignore
const TSmTaxDetails = () => import('@/entities/t-sm-tax/t-sm-tax-details.vue');
// prettier-ignore
const TOrgContactPerson = () => import('@/entities/t-org-contact-person/t-org-contact-person.vue');
// prettier-ignore
const TOrgContactPersonUpdate = () => import('@/entities/t-org-contact-person/t-org-contact-person-update.vue');
// prettier-ignore
const TOrgContactPersonDetails = () => import('@/entities/t-org-contact-person/t-org-contact-person-details.vue');
// prettier-ignore
const TTitleCode = () => import('@/entities/t-title-code/t-title-code.vue');
// prettier-ignore
const TTitleCodeUpdate = () => import('@/entities/t-title-code/t-title-code-update.vue');
// prettier-ignore
const TTitleCodeDetails = () => import('@/entities/t-title-code/t-title-code-details.vue');
// prettier-ignore
const TOrganization = () => import('@/entities/t-organization/t-organization.vue');
// prettier-ignore
const TOrganizationUpdate = () => import('@/entities/t-organization/t-organization-update.vue');
// prettier-ignore
const TOrganizationDetails = () => import('@/entities/t-organization/t-organization-details.vue');
// prettier-ignore
const TSectionCode = () => import('@/entities/t-section-code/t-section-code.vue');
// prettier-ignore
const TSectionCodeUpdate = () => import('@/entities/t-section-code/t-section-code-update.vue');
// prettier-ignore
const TSectionCodeDetails = () => import('@/entities/t-section-code/t-section-code-details.vue');
// prettier-ignore
const TUserRoleCode = () => import('@/entities/t-user-role-code/t-user-role-code.vue');
// prettier-ignore
const TUserRoleCodeUpdate = () => import('@/entities/t-user-role-code/t-user-role-code-update.vue');
// prettier-ignore
const TUserRoleCodeDetails = () => import('@/entities/t-user-role-code/t-user-role-code-details.vue');
// prettier-ignore
const TUnitCode = () => import('@/entities/t-unit-code/t-unit-code.vue');
// prettier-ignore
const TUnitCodeUpdate = () => import('@/entities/t-unit-code/t-unit-code-update.vue');
// prettier-ignore
const TUnitCodeDetails = () => import('@/entities/t-unit-code/t-unit-code-details.vue');
// prettier-ignore
const TSubunitCode = () => import('@/entities/t-subunit-code/t-subunit-code.vue');
// prettier-ignore
const TSubunitCodeUpdate = () => import('@/entities/t-subunit-code/t-subunit-code-update.vue');
// prettier-ignore
const TSubunitCodeDetails = () => import('@/entities/t-subunit-code/t-subunit-code-details.vue');
// prettier-ignore
const TOfficeCode = () => import('@/entities/t-office-code/t-office-code.vue');
// prettier-ignore
const TOfficeCodeUpdate = () => import('@/entities/t-office-code/t-office-code-update.vue');
// prettier-ignore
const TOfficeCodeDetails = () => import('@/entities/t-office-code/t-office-code-details.vue');
// prettier-ignore
const ApplicationUser = () => import('@/entities/application-user/application-user.vue');
// prettier-ignore
const ApplicationUserUpdate = () => import('@/entities/application-user/application-user-update.vue');
// prettier-ignore
const ApplicationUserDetails = () => import('@/entities/application-user/application-user-details.vue');
// prettier-ignore
const TUserRole = () => import('@/entities/t-user-role/t-user-role.vue');
// prettier-ignore
const TUserRoleUpdate = () => import('@/entities/t-user-role/t-user-role-update.vue');
// prettier-ignore
const TUserRoleDetails = () => import('@/entities/t-user-role/t-user-role-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
	path: '/entities',
	component: Entities,
	children: [
		{
			path: 't-country-code',
			name: 'TCountryCode',
			component: TCountryCode,
			meta: { authorities: [Authority.USER] },
		},
		// {
		// 	path: 't-country-code/new',
		// 	name: 'TCountryCodeCreate',
		// 	component: TCountryCodeUpdate,
		// 	meta: { authorities: [Authority.USER] },
		// },
		// {
		// 	path: 't-country-code/:tCountryCodeId/edit',
		// 	name: 'TCountryCodeEdit',
		// 	component: TCountryCodeUpdate,
		// 	meta: { authorities: [Authority.USER] },
		// },
		// {
		// 	path: 't-country-code/:tCountryCodeId/view',
		// 	name: 'TCountryCodeView',
		// 	component: TCountryCodeDetails,
		// 	meta: { authorities: [Authority.USER] },
		// },
		{
			path: 't-state-code',
			name: 'TStateCode',
			component: TStateCode,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-state-code/new',
			name: 'TStateCodeCreate',
			component: TStateCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-state-code/:tStateCodeId/edit',
			name: 'TStateCodeEdit',
			component: TStateCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-state-code/:tStateCodeId/view',
			name: 'TStateCodeView',
			component: TStateCodeDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-division-code',
			name: 'TDivisionCode',
			component: TDivisionCode,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-division-code/new',
			name: 'TDivisionCodeCreate',
			component: TDivisionCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-division-code/:tDivisionCodeId/edit',
			name: 'TDivisionCodeEdit',
			component: TDivisionCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-division-code/:tDivisionCodeId/view',
			name: 'TDivisionCodeView',
			component: TDivisionCodeDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-district-code',
			name: 'TDistrictCode',
			component: TDistrictCode,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-district-code/new',
			name: 'TDistrictCodeCreate',
			component: TDistrictCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-district-code/:tDistrictCodeId/edit',
			name: 'TDistrictCodeEdit',
			component: TDistrictCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-district-code/:tDistrictCodeId/view',
			name: 'TDistrictCodeView',
			component: TDistrictCodeDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-city-code',
			name: 'TCityCode',
			component: TCityCode,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-city-code/new',
			name: 'TCityCodeCreate',
			component: TCityCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-city-code/:tCityCodeId/edit',
			name: 'TCityCodeEdit',
			component: TCityCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-city-code/:tCityCodeId/view',
			name: 'TCityCodeView',
			component: TCityCodeDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-industry-code',
			name: 'TIndustryCode',
			component: TIndustryCode,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-industry-code/new',
			name: 'TIndustryCodeCreate',
			component: TIndustryCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-industry-code/:tIndustryCodeId/edit',
			name: 'TIndustryCodeEdit',
			component: TIndustryCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-industry-code/:tIndustryCodeId/view',
			name: 'TIndustryCodeView',
			component: TIndustryCodeDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-sector-code',
			name: 'TSectorCode',
			component: TSectorCode,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-sector-code/new',
			name: 'TSectorCodeCreate',
			component: TSectorCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-sector-code/:tSectorCodeId/edit',
			name: 'TSectorCodeEdit',
			component: TSectorCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-sector-code/:tSectorCodeId/view',
			name: 'TSectorCodeView',
			component: TSectorCodeDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-brand-code',
			name: 'TBrandCode',
			component: TBrandCode,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-brand-code/new',
			name: 'TBrandCodeCreate',
			component: TBrandCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-brand-code/:tBrandCodeId/edit',
			name: 'TBrandCodeEdit',
			component: TBrandCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-brand-code/:tBrandCodeId/view',
			name: 'TBrandCodeView',
			component: TBrandCodeDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-audit-trail',
			name: 'TAuditTrail',
			component: TAuditTrail,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-audit-trail/new',
			name: 'TAuditTrailCreate',
			component: TAuditTrailUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-audit-trail/:tAuditTrailId/edit',
			name: 'TAuditTrailEdit',
			component: TAuditTrailUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-audit-trail/:tAuditTrailId/view',
			name: 'TAuditTrailView',
			component: TAuditTrailDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-supplier-category',
			name: 'TSupplierCategory',
			component: TSupplierCategory,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-supplier-category/new',
			name: 'TSupplierCategoryCreate',
			component: TSupplierCategoryUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-supplier-category/:tSupplierCategoryId/edit',
			name: 'TSupplierCategoryEdit',
			component: TSupplierCategoryUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-supplier-category/:tSupplierCategoryId/view',
			name: 'TSupplierCategoryView',
			component: TSupplierCategoryDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-sm-tax',
			name: 'TSmTax',
			component: TSmTax,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-sm-tax/new',
			name: 'TSmTaxCreate',
			component: TSmTaxUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-sm-tax/:tSmTaxId/edit',
			name: 'TSmTaxEdit',
			component: TSmTaxUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-sm-tax/:tSmTaxId/view',
			name: 'TSmTaxView',
			component: TSmTaxDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-org-contact-person',
			name: 'TOrgContactPerson',
			component: TOrgContactPerson,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-org-contact-person/new',
			name: 'TOrgContactPersonCreate',
			component: TOrgContactPersonUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-org-contact-person/:tOrgContactPersonId/edit',
			name: 'TOrgContactPersonEdit',
			component: TOrgContactPersonUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-org-contact-person/:tOrgContactPersonId/view',
			name: 'TOrgContactPersonView',
			component: TOrgContactPersonDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-title-code',
			name: 'TTitleCode',
			component: TTitleCode,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-title-code/new',
			name: 'TTitleCodeCreate',
			component: TTitleCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-title-code/:tTitleCodeId/edit',
			name: 'TTitleCodeEdit',
			component: TTitleCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-title-code/:tTitleCodeId/view',
			name: 'TTitleCodeView',
			component: TTitleCodeDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-organization',
			name: 'TOrganization',
			component: TOrganization,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-organization/new',
			name: 'TOrganizationCreate',
			component: TOrganizationUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-organization/:tOrganizationId/edit',
			name: 'TOrganizationEdit',
			component: TOrganizationUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-organization/:tOrganizationId/view',
			name: 'TOrganizationView',
			component: TOrganizationDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-section-code',
			name: 'TSectionCode',
			component: TSectionCode,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-section-code/new',
			name: 'TSectionCodeCreate',
			component: TSectionCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-section-code/:tSectionCodeId/edit',
			name: 'TSectionCodeEdit',
			component: TSectionCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-section-code/:tSectionCodeId/view',
			name: 'TSectionCodeView',
			component: TSectionCodeDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-user-role-code',
			name: 'TUserRoleCode',
			component: TUserRoleCode,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-user-role-code/new',
			name: 'TUserRoleCodeCreate',
			component: TUserRoleCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-user-role-code/:tUserRoleCodeId/edit',
			name: 'TUserRoleCodeEdit',
			component: TUserRoleCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-user-role-code/:tUserRoleCodeId/view',
			name: 'TUserRoleCodeView',
			component: TUserRoleCodeDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-unit-code',
			name: 'TUnitCode',
			component: TUnitCode,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-unit-code/new',
			name: 'TUnitCodeCreate',
			component: TUnitCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-unit-code/:tUnitCodeId/edit',
			name: 'TUnitCodeEdit',
			component: TUnitCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-unit-code/:tUnitCodeId/view',
			name: 'TUnitCodeView',
			component: TUnitCodeDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-subunit-code',
			name: 'TSubunitCode',
			component: TSubunitCode,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-subunit-code/new',
			name: 'TSubunitCodeCreate',
			component: TSubunitCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-subunit-code/:tSubunitCodeId/edit',
			name: 'TSubunitCodeEdit',
			component: TSubunitCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-subunit-code/:tSubunitCodeId/view',
			name: 'TSubunitCodeView',
			component: TSubunitCodeDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-office-code',
			name: 'TOfficeCode',
			component: TOfficeCode,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-office-code/new',
			name: 'TOfficeCodeCreate',
			component: TOfficeCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-office-code/:tOfficeCodeId/edit',
			name: 'TOfficeCodeEdit',
			component: TOfficeCodeUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-office-code/:tOfficeCodeId/view',
			name: 'TOfficeCodeView',
			component: TOfficeCodeDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 'application-user',
			name: 'ApplicationUser',
			component: ApplicationUser,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 'application-user/new',
			name: 'ApplicationUserCreate',
			component: ApplicationUserUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 'application-user/:applicationUserId/edit',
			name: 'ApplicationUserEdit',
			component: ApplicationUserUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 'application-user/:applicationUserId/view',
			name: 'ApplicationUserView',
			component: ApplicationUserDetails,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-user-role',
			name: 'TUserRole',
			component: TUserRole,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-user-role/new',
			name: 'TUserRoleCreate',
			component: TUserRoleUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-user-role/:tUserRoleId/edit',
			name: 'TUserRoleEdit',
			component: TUserRoleUpdate,
			meta: { authorities: [Authority.USER] },
		},
		{
			path: 't-user-role/:tUserRoleId/view',
			name: 'TUserRoleView',
			component: TUserRoleDetails,
			meta: { authorities: [Authority.USER] },
		},
		// jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
	],
};
