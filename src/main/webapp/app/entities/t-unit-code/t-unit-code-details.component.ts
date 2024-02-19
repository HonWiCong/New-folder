import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITUnitCode } from '@/shared/model/t-unit-code.model';
import TUnitCodeService from './t-unit-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TUnitCodeDetails extends Vue {
	@Inject('tUnitCodeService') private tUnitCodeService: () => TUnitCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	public tUnitCode: ITUnitCode = {};

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tUnitCodeId) {
				vm.retrieveTUnitCode(to.params.tUnitCodeId);
			}
		});
	}

	public retrieveTUnitCode(tUnitCodeId) {
		this.tUnitCodeService()
			.find(tUnitCodeId)
			.then(res => {
				this.tUnitCode = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState() {
		this.$router.go(-1);
	}
}
