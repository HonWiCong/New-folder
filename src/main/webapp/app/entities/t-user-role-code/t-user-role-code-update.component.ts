import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import TUserRoleService from '@/entities/t-user-role/t-user-role.service';
import { ITUserRole } from '@/shared/model/t-user-role.model';

import { ITUserRoleCode, TUserRoleCode } from '@/shared/model/t-user-role-code.model';
import TUserRoleCodeService from './t-user-role-code.service';

const validations: any = {
	tUserRoleCode: {
		roleName: {},
		roleHead: {},
		actApprover1: {},
		actApprover2: {},
		enteredBy: {},
		enteredDate: {},
		modifiedBy: {},
		modifiedDate: {},
	},
};

@Component({
	validations,
})
export default class TUserRoleCodeUpdate extends Vue {
	@Inject('tUserRoleCodeService') private tUserRoleCodeService: () => TUserRoleCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	public tUserRoleCode: ITUserRoleCode = new TUserRoleCode();

	@Inject('tUserRoleService') private tUserRoleService: () => TUserRoleService;

	public tUserRoles: ITUserRole[] = [];
	public isSaving = false;
	public currentLanguage = '';

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tUserRoleCodeId) {
				vm.retrieveTUserRoleCode(to.params.tUserRoleCodeId);
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
		if (this.tUserRoleCode.id) {
			this.tUserRoleCodeService()
				.update(this.tUserRoleCode)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.tUserRoleCode.updated', { param: param.id });
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
			this.tUserRoleCodeService()
				.create(this.tUserRoleCode)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.tUserRoleCode.created', { param: param.id });
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
			this.tUserRoleCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
		} else {
			this.tUserRoleCode[field] = null;
		}
	}

	public updateZonedDateTimeField(field, event) {
		if (event.target.value) {
			this.tUserRoleCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
		} else {
			this.tUserRoleCode[field] = null;
		}
	}

	public retrieveTUserRoleCode(tUserRoleCodeId): void {
		this.tUserRoleCodeService()
			.find(tUserRoleCodeId)
			.then(res => {
				res.enteredDate = new Date(res.enteredDate);
				res.modifiedDate = new Date(res.modifiedDate);
				this.tUserRoleCode = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState(): void {
		this.$router.go(-1);
	}

	public initRelationships(): void {
		this.tUserRoleService()
			.retrieve()
			.then(res => {
				this.tUserRoles = res.data;
			});
	}
}
