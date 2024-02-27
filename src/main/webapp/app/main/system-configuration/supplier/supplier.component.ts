import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import TOrgContactPersonService from './t-org-contact-person/t-org-contact-person.service';
import TOrganizationService from './t-organization/t-organization.service';
import TCountryCodeService from '@/main/location/t-country-code/t-country-code.service';
import TStateCodeService from '@/main/location/t-state-code/t-state-code.service';
import TDivisionCodeService from '@/main/location/t-division-code/t-division-code.service';
import TCityCodeService from '@/main/location/t-city-code/t-city-code.service';
import TDistrictCodeService from '@/entities/t-district-code/t-district-code.service';
import TTitleCodeService from '@/entities/t-title-code/t-title-code.service';
import TSmTaxService from '@/entities/t-sm-tax/t-sm-tax.service';
import TSectionCodeService from '@/entities/t-section-code/t-section-code.service';
import TIndustryCodeService from '@/entities/t-industry-code/t-industry-code.service';
import TSectorCodeService from '@/entities/t-sector-code/t-sector-code.service';

@Component
export default class Entities extends Vue {
	@Provide('userService') private userService = () => new UserService();
	@Provide('tOrgContactPersonService') private tOrgContactPersonService = () => new TOrgContactPersonService();
	@Provide('tOrganizationService') private tOrganizationService = () => new TOrganizationService();
	@Provide('tCountryCodeService') private tCountryCodeService = () => new TCountryCodeService();
	@Provide('tStateCodeService') private tStateCodeService = () => new TStateCodeService();
	@Provide('tDivisionCodeService') private tDivisionCodeService = () => new TDivisionCodeService();
	@Provide('tCityCodeService') private tCityCodeService = () => new TCityCodeService();
	@Provide('tDistrictCodeService') private tDistrictCodeService = () => new TDistrictCodeService();
	@Provide('tTitleCodeService') private tTitleCodeService = () => new TTitleCodeService();
	@Provide('tSmTaxService') private tSmTaxService = () => new TSmTaxService();
	@Provide('tSectionCodeService') private tSectionCodeService = () => new TSectionCodeService();
	@Provide('tIndustryCodeService') private tIndustryCodeService = () => new TIndustryCodeService();
	@Provide('tSectorCodeService') private tSectorCodeService = () => new TSectorCodeService();
}
