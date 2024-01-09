import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import TCountryCodeService from './t-country-code/t-country-code.service';
import TStateCodeService from './t-state-code/t-state-code.service';
import TDivisionCodeService from './t-division-code/t-division-code.service';
import TDistrictCodeService from './t-district-code/t-district-code.service';
import TCityCodeService from './t-city-code/t-city-code.service';
import TIndustryCodeService from './t-industry-code/t-industry-code.service';
import TSectorCodeService from './t-sector-code/t-sector-code.service';
import TBrandCodeService from './t-brand-code/t-brand-code.service';
import TAuditTrailService from './t-audit-trail/t-audit-trail.service';
import TSupplierCategoryService from './t-supplier-category/t-supplier-category.service';
import TSmTaxService from './t-sm-tax/t-sm-tax.service';
import TOrgContactPersonService from './t-org-contact-person/t-org-contact-person.service';
import TTitleCodeService from './t-title-code/t-title-code.service';
import TOrganizationService from './t-organization/t-organization.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('tCountryCodeService') private tCountryCodeService = () => new TCountryCodeService();
  @Provide('tStateCodeService') private tStateCodeService = () => new TStateCodeService();
  @Provide('tDivisionCodeService') private tDivisionCodeService = () => new TDivisionCodeService();
  @Provide('tDistrictCodeService') private tDistrictCodeService = () => new TDistrictCodeService();
  @Provide('tCityCodeService') private tCityCodeService = () => new TCityCodeService();
  @Provide('tIndustryCodeService') private tIndustryCodeService = () => new TIndustryCodeService();
  @Provide('tSectorCodeService') private tSectorCodeService = () => new TSectorCodeService();
  @Provide('tBrandCodeService') private tBrandCodeService = () => new TBrandCodeService();
  @Provide('tAuditTrailService') private tAuditTrailService = () => new TAuditTrailService();
  @Provide('tSupplierCategoryService') private tSupplierCategoryService = () => new TSupplierCategoryService();
  @Provide('tSmTaxService') private tSmTaxService = () => new TSmTaxService();
  @Provide('tOrgContactPersonService') private tOrgContactPersonService = () => new TOrgContactPersonService();
  @Provide('tTitleCodeService') private tTitleCodeService = () => new TTitleCodeService();
  @Provide('tOrganizationService') private tOrganizationService = () => new TOrganizationService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
