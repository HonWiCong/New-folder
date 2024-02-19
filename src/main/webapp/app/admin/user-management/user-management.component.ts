import { Component, Inject, Vue } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import UserManagementService from './user-management.service';
import AlertService from '@/shared/alert/alert.service';
import { IUser } from '@/shared/model/user.model';

import ApplicationUserService from '@/entities/application-user/application-user.service';
import { IApplicationUser, ApplicationUser } from '@/shared/model/application-user.model';
@Component({
	mixins: [Vue2Filters.mixin],
})
export default class JhiUserManagementComponent extends Vue {
	@Inject('userManagementService') private userManagementService: () => UserManagementService;
	@Inject('alertService') private alertService: () => AlertService;

	private applicationUserService: ApplicationUserService = new ApplicationUserService();
	public applicationUsers: IApplicationUser[] = [];
	public sortedApplicationUsers: IApplicationUser[] = [];

	public error = '';
	public success = '';
	public users: IUser[] = [];
	public itemsPerPage = 20;
	public queryCount: number = null;
	public page = 1;
	public previousPage = 1;
	public propOrder = 'id';
	public reverse = false;
	public totalItems = 0;
	public isLoading = false;
	public removeId: number = null;

	public mounted(): void {
		this.loadAll();
	}

	public setActive(user, isActivated): void {
		user.activated = isActivated;
		this.userManagementService()
			.update(user)
			.then(() => {
				this.error = null;
				this.success = 'OK';
				this.loadAll();
			})
			.catch(() => {
				this.success = null;
				this.error = 'ERROR';
				user.activated = false;
			});
	}

	public loadAll(): void {
		this.isLoading = true;
		const paginationQuery = {
			page: this.page - 1,
			size: this.itemsPerPage,
			sort: this.sort(),
		};
		this.applicationUserService
			.retrieve(paginationQuery)
			.then(res => {
				console.log(res.data);
				this.applicationUsers = res.data;
				this.totalItems = Number(res.headers['x-total-count']);
				this.queryCount = this.totalItems;
				this.isLoading = false;
			})
			.catch(() => {
				this.isLoading = false;
			});
	}

	public handleSyncList(): void {
		this.loadAll();
	}

	public sort(): Array<any> {
		const result = [this.propOrder + ',' + (this.reverse ? 'desc' : 'asc')];
		if (this.propOrder !== 'id') {
			result.push('id');
		}
		return result;
	}

	public loadPage(page: number): void {
		if (page !== this.previousPage) {
			this.previousPage = page;
			this.transition();
		}
	}

	public transition(): void {
		this.loadAll();
	}

	public changeOrder(propOrder: string): void {
		this.propOrder = propOrder;
		this.reverse = !this.reverse;
		this.transition();
	}

	public deleteUser(): void {
		this.applicationUserService
			.delete(this.removeId)
			.then(res => {
				const message = this.$t(res.headers['x-sainsapp-alert'], {
					param: decodeURIComponent(res.headers['x-sainsapp-params'].replace(/\+/g, ' ')),
				});
				this.$bvToast.toast(message.toString(), {
					toaster: 'b-toaster-top-center',
					title: 'Info',
					variant: 'danger',
					solid: true,
					autoHideDelay: 5000,
				});
				this.removeId = null;
				this.loadAll();
				this.closeDialog();
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public prepareRemove(instance): void {
		this.removeId = instance.id;
		if (<any>this.$refs.removeUser) {
			(<any>this.$refs.removeUser).show();
		}
	}

	public closeDialog(): void {
		if (<any>this.$refs.removeUser) {
			(<any>this.$refs.removeUser).hide();
		}
	}

	public get username(): string {
		return this.$store.getters.account?.login ?? '';
	}
}
