import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import TCountryCodeService from './t-country-code/t-country-code.service';
import TStateCodeService from './t-state-code/t-state-code.service';
import TDivisionCodeService from './t-division-code/t-division-code.service';
import TCityCodeService from './t-city-code/t-city-code.service';
import TDistrictCodeService from '@/entities/t-district-code/t-district-code.service';

@Component
export default class Entities extends Vue {
	@Provide('userService') private userService = () => new UserService();
	@Provide('tCountryCodeService') private tCountryCodeService = () => new TCountryCodeService();
	@Provide('tStateCodeService') private tStateCodeService = () => new TStateCodeService();
	@Provide('tDivisionCodeService') private tDivisionCodeService = () => new TDivisionCodeService();
	@Provide('tCityCodeService') private tCityCodeService = () => new TCityCodeService();
	@Provide('tDistrictCodeService') private tDistrictCodeService = () => new TDistrictCodeService();
}
