import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITUserRole } from '@/shared/model/t-user-role.model';
import TUserRoleService from './t-user-role.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TUserRoleDetails extends Vue {
	@Inject('tUserRoleService') private tUserRoleService: () => TUserRoleService;
	@Inject('alertService') private alertService: () => AlertService;

	public tUserRole: ITUserRole = {};

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tUserRoleId) {
				vm.retrieveTUserRole(to.params.tUserRoleId);
			}
		});
	}

	public retrieveTUserRole(tUserRoleId) {
		this.tUserRoleService()
			.find(tUserRoleId)
			.then(res => {
				this.tUserRole = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState() {
		this.$router.go(-1);
	}
}
