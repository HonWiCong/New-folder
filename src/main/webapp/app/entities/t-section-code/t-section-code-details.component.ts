import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITSectionCode } from '@/shared/model/t-section-code.model';
import TSectionCodeService from './t-section-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TSectionCodeDetails extends Vue {
	@Inject('tSectionCodeService') private tSectionCodeService: () => TSectionCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	public tSectionCode: ITSectionCode = {};

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tSectionCodeId) {
				vm.retrieveTSectionCode(to.params.tSectionCodeId);
			}
		});
	}

	public retrieveTSectionCode(tSectionCodeId) {
		this.tSectionCodeService()
			.find(tSectionCodeId)
			.then(res => {
				this.tSectionCode = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState() {
		this.$router.go(-1);
	}
}
