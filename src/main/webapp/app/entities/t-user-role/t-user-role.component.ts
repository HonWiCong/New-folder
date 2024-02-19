import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITUserRole } from '@/shared/model/t-user-role.model';

import TUserRoleService from './t-user-role.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
	mixins: [Vue2Filters.mixin],
})
export default class TUserRole extends Vue {
	@Inject('tUserRoleService') private tUserRoleService: () => TUserRoleService;
	@Inject('alertService') private alertService: () => AlertService;

	private removeId: number = null;

	public tUserRoles: ITUserRole[] = [];

	public isFetching = false;

	public mounted(): void {
		this.retrieveAllTUserRoles();
	}

	public clear(): void {
		this.retrieveAllTUserRoles();
	}

	public retrieveAllTUserRoles(): void {
		this.isFetching = true;
		this.tUserRoleService()
			.retrieve()
			.then(
				res => {
					this.tUserRoles = res.data;
					this.isFetching = false;
				},
				err => {
					this.isFetching = false;
					this.alertService().showHttpError(this, err.response);
				}
			);
	}

	public handleSyncList(): void {
		this.clear();
	}

	public prepareRemove(instance: ITUserRole): void {
		this.removeId = instance.id;
		if (<any>this.$refs.removeEntity) {
			(<any>this.$refs.removeEntity).show();
		}
	}

	public removeTUserRole(): void {
		this.tUserRoleService()
			.delete(this.removeId)
			.then(() => {
				const message = this.$t('sainsApp.tUserRole.deleted', { param: this.removeId });
				this.$bvToast.toast(message.toString(), {
					toaster: 'b-toaster-top-center',
					title: 'Info',
					variant: 'danger',
					solid: true,
					autoHideDelay: 5000,
				});
				this.removeId = null;
				this.retrieveAllTUserRoles();
				this.closeDialog();
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public closeDialog(): void {
		(<any>this.$refs.removeEntity).hide();
	}
}
