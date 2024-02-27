import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITCountryCode } from '@/shared/model/t-country-code.model';
import TCountryCodeService from './t-country-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TCountryCodeDetails extends Vue {
	@Inject('tCountryCodeService') private tCountryCodeService: () => TCountryCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	public tCountryCode: ITCountryCode = {};

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tCountryCodeId) {
				vm.retrieveTCountryCode(to.params.tCountryCodeId);
			}
		});
	}

	public retrieveTCountryCode(tCountryCodeId) {
		this.tCountryCodeService()
			.find(tCountryCodeId)
			.then(res => {
				this.tCountryCode = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState() {
		this.$router.go(-1);
	}
}
