import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITOfficeCode } from '@/shared/model/t-office-code.model';
import TOfficeCodeService from './t-office-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TOfficeCodeDetails extends Vue {
	@Inject('tOfficeCodeService') private tOfficeCodeService: () => TOfficeCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	public tOfficeCode: ITOfficeCode = {};

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tOfficeCodeId) {
				vm.retrieveTOfficeCode(to.params.tOfficeCodeId);
			}
		});
	}

	public retrieveTOfficeCode(tOfficeCodeId) {
		this.tOfficeCodeService()
			.find(tOfficeCodeId)
			.then(res => {
				this.tOfficeCode = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState() {
		this.$router.go(-1);
	}
}
