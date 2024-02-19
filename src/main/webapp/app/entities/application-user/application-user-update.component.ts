import { Component, Vue, Inject } from 'vue-property-decorator';

import { maxLength } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import UserService from '@/entities/user/user.service';

import TUserRoleService from '@/entities/t-user-role/t-user-role.service';
import { ITUserRole } from '@/shared/model/t-user-role.model';

import TSectionCodeService from '@/entities/t-section-code/t-section-code.service';
import { ITSectionCode } from '@/shared/model/t-section-code.model';

import TUnitCodeService from '@/entities/t-unit-code/t-unit-code.service';
import { ITUnitCode } from '@/shared/model/t-unit-code.model';

import TSubunitCodeService from '@/entities/t-subunit-code/t-subunit-code.service';
import { ITSubunitCode } from '@/shared/model/t-subunit-code.model';

import TOfficeCodeService from '@/entities/t-office-code/t-office-code.service';
import { ITOfficeCode } from '@/shared/model/t-office-code.model';

import { IApplicationUser, ApplicationUser } from '@/shared/model/application-user.model';
import ApplicationUserService from './application-user.service';

const validations: any = {
	applicationUser: {
		ic: {
			maxLength: maxLength(15),
		},
		status: {
			maxLength: maxLength(1),
		},
	},
};

@Component({
	validations,
})
export default class ApplicationUserUpdate extends Vue {
	@Inject('applicationUserService') private applicationUserService: () => ApplicationUserService;
	@Inject('alertService') private alertService: () => AlertService;

	public applicationUser: IApplicationUser = new ApplicationUser();

	@Inject('userService') private userService: () => UserService;

	public users: Array<any> = [];

	@Inject('tUserRoleService') private tUserRoleService: () => TUserRoleService;

	public tUserRoles: ITUserRole[] = [];

	@Inject('tSectionCodeService') private tSectionCodeService: () => TSectionCodeService;

	public tSectionCodes: ITSectionCode[] = [];

	@Inject('tUnitCodeService') private tUnitCodeService: () => TUnitCodeService;

	public tUnitCodes: ITUnitCode[] = [];

	@Inject('tSubunitCodeService') private tSubunitCodeService: () => TSubunitCodeService;

	public tSubunitCodes: ITSubunitCode[] = [];

	@Inject('tOfficeCodeService') private tOfficeCodeService: () => TOfficeCodeService;

	public tOfficeCodes: ITOfficeCode[] = [];
	public isSaving = false;
	public currentLanguage = '';

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.applicationUserId) {
				vm.retrieveApplicationUser(to.params.applicationUserId);
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
		if (this.applicationUser.id) {
			this.applicationUserService()
				.update(this.applicationUser)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.applicationUser.updated', { param: param.id });
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
			this.applicationUserService()
				.create(this.applicationUser)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.applicationUser.created', { param: param.id });
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

	public retrieveApplicationUser(applicationUserId): void {
		this.applicationUserService()
			.find(applicationUserId)
			.then(res => {
				this.applicationUser = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState(): void {
		this.$router.go(-1);
	}

	public initRelationships(): void {
		this.userService()
			.retrieve()
			.then(res => {
				this.users = res.data;
			});
		this.tUserRoleService()
			.retrieve()
			.then(res => {
				this.tUserRoles = res.data;
			});
		this.tSectionCodeService()
			.retrieve()
			.then(res => {
				this.tSectionCodes = res.data;
			});
		this.tUnitCodeService()
			.retrieve()
			.then(res => {
				this.tUnitCodes = res.data;
			});
		this.tSubunitCodeService()
			.retrieve()
			.then(res => {
				this.tSubunitCodes = res.data;
			});
		this.tOfficeCodeService()
			.retrieve()
			.then(res => {
				this.tOfficeCodes = res.data;
			});
	}
}
