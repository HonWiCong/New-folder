import Vue from 'vue';
import { Component, Inject } from 'vue-property-decorator';
import UserManagementService from './user-management.service';
import AlertService from '@/shared/alert/alert.service';
import { IUser, User } from '@/shared/model/user.model';

import ApplicationUserService from '@/entities/application-user/application-user.service';
import { IApplicationUser, ApplicationUser } from '@/shared/model/application-user.model';

@Component
export default class JhiUserManagementView extends Vue {
	@Inject('userManagementService') private userManagementService: () => UserManagementService;
	@Inject('alertService') private alertService: () => AlertService;

	private applicationUserService: ApplicationUserService = new ApplicationUserService();
	public user: IApplicationUser = new ApplicationUser();

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.userId) {
				vm.init(to.params.userId);
			}
		});
	}
	public init(userId: number): void {
		this.applicationUserService
			.find(userId)
			.then(res => {
				this.user = res;
				console.log(this.user);
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}
}
