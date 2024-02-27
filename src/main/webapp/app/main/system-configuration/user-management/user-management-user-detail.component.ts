/* eslint-disable prettier/prettier */
import { email, maxLength, minLength, required } from 'vuelidate/lib/validators';
import { Component, Inject, Vue } from 'vue-property-decorator';
import UserManagementService from './user-management.service';
import { IUser, User } from '@/shared/model/user.model';
import AlertService from '@/shared/alert/alert.service';

import TSectionCodeService from '@/entities/t-section-code/t-section-code.service';
import { ITSectionCode, TSectionCode } from '@/shared/model/t-section-code.model';

import TUnitCodeService from '@/entities/t-unit-code/t-unit-code.service';
import { ITUnitCode } from '@/shared/model/t-unit-code.model';

import TSubunitCodeService from '@/entities/t-subunit-code/t-subunit-code.service';
import { ITSubunitCode } from '@/shared/model/t-subunit-code.model';

import TOfficeCodeService from '@/entities/t-office-code/t-office-code.service';
import { ITOfficeCode } from '@/shared/model/t-office-code.model';

import TUserRoleCodeService from '@/entities/t-user-role-code/t-user-role-code.service';
import { ITUserRoleCode, TUserRoleCode } from '@/shared/model/t-user-role-code.model';

import ApplicationUserService from '@/entities/application-user/application-user.service';
import { IApplicationUser, ApplicationUser } from '@/shared/model/application-user.model';

import { TUserRole, ITUserRole } from '@/shared/model/t-user-role.model';

const loginValidator = (value: string) => {
	if (!value) {
		return true;
	}
	return /^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@A-Za-z0-9-]+$/.test(value);
};

const validations: any = {
	userAccount: {
		login: {
			required,
			maxLength: maxLength(254),
			pattern: loginValidator,
		},
		firstName: {
			maxLength: maxLength(50),
		},
		lastName: {
			maxLength: maxLength(50),
		},
		email: {
			required,
			email,
			minLength: minLength(5),
			maxLength: maxLength(50),
		},
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
export default class JhiUserManagementUserDetail extends Vue {
	@Inject('userManagementService') private userManagementService: () => UserManagementService;
	@Inject('alertService') private alertService: () => AlertService;

	private applicationUserService: ApplicationUserService = new ApplicationUserService();
	public applicationUserAccount: IApplicationUser = new ApplicationUser();

	private tSectionCodeService: TSectionCodeService = new TSectionCodeService();
	public tSectionCodes: ITSectionCode[] = [];

	private tUnitCodeService: TUnitCodeService = new TUnitCodeService();
	public tUnitCodes: ITUnitCode[] = [];

	private tSubunitCodeService: TSubunitCodeService = new TSubunitCodeService();
	public tSubunitCodes: ITSubunitCode[] = [];

	private tOfficeCodeService: TOfficeCodeService = new TOfficeCodeService();
	public tOfficeCodes: ITOfficeCode[] = [];

	private tUserRoleCodeService: TUserRoleCodeService = new TUserRoleCodeService();
	public tUserRoleCodes: ITUserRoleCode[] = [];

	public userAccount: IUser;
	public isSaving = false;
	public authorities: any[] = [];
	public languages: any = this.$store.getters.languages;

	public preSelectedUserRolesCodes: any[] = [];

	public miscellanous_access_right_items1 = [
		{ label: 'Owner Group', status: null },
		{ label: 'Supervisor', status: null },
		{ label: 'View Inventory Price', status: null },
		{ label: 'PO/SO Cancellation', status: null },
		{ label: 'PO/SO Admin Verifier', status: null },
		{ label: 'Travel Request Verifier', status: null },
		{ label: 'Re-Print Travel Request SO', status: null },
		{ label: 'Force Closed PO', status: null },
		{ label: 'Create All Requisition?', status: null },
		{ label: 'Change Asset Holder?', status: null },
		{ label: 'Stock Take Verification', status: null },
		{ label: 'View Stock Take Quantity?', status: null },
	];

	public miscellanous_access_right_items2 = [
		{ label: 'Stock Take Cancellation', status: null },
		{ label: 'GST Adjustment Type Setting', status: null },
		{ label: 'Petty Cash Reprint', status: null },
		{ label: 'PO/SO Reprint', status: null },
		{ label: 'Delivery Order (DO)/Delivery Note (DN) Cancellation', status: null },
		{ label: 'PO/SO Procurement Verifier', status: null },
		{ label: 'Travel Request Approval', status: null },
		{ label: 'Travel Request Cancellation', status: null },
		{ label: 'Manual DO Verifier', status: null },
		{ label: 'View RequisitionPrice?', status: null },
		{ label: 'Acknowledge Receive Billing Instruction?', status: null },
		{ label: 'Search Inventory Item?', status: null },
	];

	public columns = [
		{ key: 'label', label: 'Label', class: 'label-column' },
		{ key: 'checkbox-section', label: 'Checkbox Section' },
	];

	beforeRouteEnter(to, from, next) {
		next(vm => {
			vm.initAuthorities();
			vm.initData();
			if (to.params.userId) {
				vm.init(to.params.userId);
			}
		});
	}

	public constructor() {
		super();
		this.userAccount = new User();
		this.userAccount.authorities = [];
		this.applicationUserAccount.internalUser = this.userAccount;
		this.applicationUserAccount.userRoles = [];
	}

	public initAuthorities() {
		this.userManagementService()
			.retrieveAuthorities()
			.then(_res => {
				this.authorities = _res.data;
			});
	}

	public initData() {
		this.tSectionCodeService.retrieve().then(res => {
			this.tSectionCodes = res.data;
		});
		this.tUnitCodeService.retrieve().then(res => {
			this.tUnitCodes = res.data;
		});
		this.tSubunitCodeService.retrieve().then(res => {
			this.tSubunitCodes = res.data;
		});
		this.tOfficeCodeService.retrieve().then(res => {
			this.tOfficeCodes = res.data;
		});
		this.tUserRoleCodeService.retrieve().then(res => {
			this.tUserRoleCodes = res.data;
		});
	}

	public init(userId: number): void {
		this.applicationUserService.find(userId).then(res => {
			this.applicationUserAccount = res;

			// prevent undefined error
			if (!this.applicationUserAccount.newUserRoles) {
				this.applicationUserAccount.newUserRoles = [];
			}

			if (this.applicationUserAccount.userRoles.length > 0) {
				this.applicationUserAccount.userRoles.forEach(userRole => {
					this.preSelectedUserRolesCodes.push(userRole.role.id);
					this.applicationUserAccount.newUserRoles.push(userRole);
				});
			}

			const tempArray = [];
			for (const authorities of this.applicationUserAccount.internalUser.authorities) {
				tempArray.push(authorities.name);
			}
			this.applicationUserAccount.internalUser.authorities = tempArray;

			console.log(this.applicationUserAccount);
			return this.userAccount;
		});
	}

	public previousState(): void {
		this.$router.push({ name: 'UserManagement' });
	}

	public save(): void {
		this.isSaving = true;
		if (this.applicationUserAccount.id) {
			this.applicationUserAccount.internalUser.authorities = this.applicationUserAccount.internalUser.authorities.map(authority => {
				return { name: authority };
			});
			console.log(this.applicationUserAccount);
			this.applicationUserService
				.update(this.applicationUserAccount)
				.then(res => {
					this.returnToList();
					(this.$root as any).$bvToast.toast(this.getMessageFromHeader(res).toString(), {
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
			this.applicationUserService
				.create(this.applicationUserAccount)
				.then(res => {
					this.returnToList();
					(this.$root as any).$bvToast.toast(this.getMessageFromHeader(res).toString(), {
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

	private returnToList(): void {
		this.isSaving = false;
		(<any>this).$router.go(-1);
	}

	private getMessageFromHeader(res: any): any {
		return this.$t(res.headers['x-sainsapp-alert'], {
			param: decodeURIComponent(res.headers['x-sainsapp-params'].replace(/\+/g, ' ')),
		});
	}

	public onUserRoleChange(role: ITUserRoleCode, event: any) {
		if (this.applicationUserAccount.id) {
			if (!this.applicationUserAccount.newUserRoles) {
				this.applicationUserAccount.newUserRoles = [];
			}
			if (event.checked) {
				const userRole = new TUserRole();
				userRole.role = role;
				this.applicationUserAccount.newUserRoles.push(userRole);
			} else {
				this.applicationUserAccount.newUserRoles.splice(
					this.applicationUserAccount.newUserRoles.findIndex(item => item.role.id === role.id),
					1
				);
			}
		} else {
			if (!this.applicationUserAccount.userRoles) {
				this.applicationUserAccount.userRoles = [];
			}
			if (event.checked) {
				const userRole = new TUserRole();
				userRole.role = role;
				this.applicationUserAccount.userRoles.push(userRole);
			} else {
				this.applicationUserAccount.userRoles.splice(
					this.applicationUserAccount.userRoles.findIndex(item => item.role.id === role.id),
					1
				);
			}
		}
	}

	public checkData() {
		console.log(this.applicationUserAccount);
	}
}
