import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITDivisionCode } from '@/shared/model/t-division-code.model';
import TDivisionCodeService from './t-division-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TDivisionCodeDetails extends Vue {
	@Inject('tDivisionCodeService') private tDivisionCodeService: () => TDivisionCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	public tDivisionCode: ITDivisionCode = {};

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tDivisionCodeId) {
				vm.retrieveTDivisionCode(to.params.tDivisionCodeId);
			}
		});
	}

	public retrieveTDivisionCode(tDivisionCodeId) {
		this.tDivisionCodeService()
			.find(tDivisionCodeId)
			.then(res => {
				this.tDivisionCode = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState() {
		this.$router.go(-1);
	}
}
