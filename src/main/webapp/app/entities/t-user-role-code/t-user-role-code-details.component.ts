import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITUserRoleCode } from '@/shared/model/t-user-role-code.model';
import TUserRoleCodeService from './t-user-role-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TUserRoleCodeDetails extends Vue {
	@Inject('tUserRoleCodeService') private tUserRoleCodeService: () => TUserRoleCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	public tUserRoleCode: ITUserRoleCode = {};

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tUserRoleCodeId) {
				vm.retrieveTUserRoleCode(to.params.tUserRoleCodeId);
			}
		});
	}

	public retrieveTUserRoleCode(tUserRoleCodeId) {
		this.tUserRoleCodeService()
			.find(tUserRoleCodeId)
			.then(res => {
				this.tUserRoleCode = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState() {
		this.$router.go(-1);
	}
}
