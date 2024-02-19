import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import TUserRoleCodeService from '@/entities/t-user-role-code/t-user-role-code.service';
import { ITUserRoleCode } from '@/shared/model/t-user-role-code.model';

import ApplicationUserService from '@/entities/application-user/application-user.service';
import { IApplicationUser } from '@/shared/model/application-user.model';

import { ITUserRole, TUserRole } from '@/shared/model/t-user-role.model';
import TUserRoleService from './t-user-role.service';

const validations: any = {
	tUserRole: {
		sysuserId: {},
		usrRoleid: {},
		enteredBy: {},
		enteredDate: {},
		modifiedBy: {},
		modifiedDate: {},
	},
};

@Component({
	validations,
})
export default class TUserRoleUpdate extends Vue {
	@Inject('tUserRoleService') private tUserRoleService: () => TUserRoleService;
	@Inject('alertService') private alertService: () => AlertService;

	public tUserRole: ITUserRole = new TUserRole();

	@Inject('tUserRoleCodeService') private tUserRoleCodeService: () => TUserRoleCodeService;

	public tUserRoleCodes: ITUserRoleCode[] = [];

	@Inject('applicationUserService') private applicationUserService: () => ApplicationUserService;

	public applicationUsers: IApplicationUser[] = [];
	public isSaving = false;
	public currentLanguage = '';

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tUserRoleId) {
				vm.retrieveTUserRole(to.params.tUserRoleId);
			}
			vm.initRelationships();
		});
	}

	created(): void {
		this.currentLanguage = this.$store.getters.currentLanguage;
		this.$store.watch(
			() => this.$store.getters.currentLanguage,
			() => {
				this.currentLanguage = this.$store.getters.currentLanguage;
			}
		);
	}

	public save(): void {
		this.isSaving = true;
		if (this.tUserRole.id) {
			this.tUserRoleService()
				.update(this.tUserRole)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.tUserRole.updated', { param: param.id });
					return (this.$root as any).$bvToast.toast(message.toString(), {
						toaster: 'b-toaster-top-center',
						title: 'Info',
						variant: 'info',
						solid: true,
						autoHideDelay: 5000,
					});
				})
				.catch(error => {
					this.isSaving = false;
					this.alertService().showHttpError(this, error.response);
				});
		} else {
			this.tUserRoleService()
				.create(this.tUserRole)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.tUserRole.created', { param: param.id });
					(this.$root as any).$bvToast.toast(message.toString(), {
						toaster: 'b-toaster-top-center',
						title: 'Success',
						variant: 'success',
						solid: true,
						autoHideDelay: 5000,
					});
				})
				.catch(error => {
					this.isSaving = false;
					this.alertService().showHttpError(this, error.response);
				});
		}
	}

	public convertDateTimeFromServer(date: Date): string {
		if (date && dayjs(date).isValid()) {
			return dayjs(date).format(DATE_TIME_LONG_FORMAT);
		}
		return null;
	}

	public updateInstantField(field, event) {
		if (event.target.value) {
			this.tUserRole[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
		} else {
			this.tUserRole[field] = null;
		}
	}

	public updateZonedDateTimeField(field, event) {
		if (event.target.value) {
			this.tUserRole[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
		} else {
			this.tUserRole[field] = null;
		}
	}

	public retrieveTUserRole(tUserRoleId): void {
		this.tUserRoleService()
			.find(tUserRoleId)
			.then(res => {
				res.enteredDate = new Date(res.enteredDate);
				res.modifiedDate = new Date(res.modifiedDate);
				this.tUserRole = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState(): void {
		this.$router.go(-1);
	}

	public initRelationships(): void {
		this.tUserRoleCodeService()
			.retrieve()
			.then(res => {
				this.tUserRoleCodes = res.data;
			});
		this.applicationUserService()
			.retrieve()
			.then(res => {
				this.applicationUsers = res.data;
			});
	}
}
