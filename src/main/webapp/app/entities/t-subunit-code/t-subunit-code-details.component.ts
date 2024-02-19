import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITSubunitCode } from '@/shared/model/t-subunit-code.model';
import TSubunitCodeService from './t-subunit-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TSubunitCodeDetails extends Vue {
	@Inject('tSubunitCodeService') private tSubunitCodeService: () => TSubunitCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	public tSubunitCode: ITSubunitCode = {};

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tSubunitCodeId) {
				vm.retrieveTSubunitCode(to.params.tSubunitCodeId);
			}
		});
	}

	public retrieveTSubunitCode(tSubunitCodeId) {
		this.tSubunitCodeService()
			.find(tSubunitCodeId)
			.then(res => {
				this.tSubunitCode = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState() {
		this.$router.go(-1);
	}
}
