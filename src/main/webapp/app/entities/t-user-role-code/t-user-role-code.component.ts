import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITUserRoleCode } from '@/shared/model/t-user-role-code.model';

import TUserRoleCodeService from './t-user-role-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
	mixins: [Vue2Filters.mixin],
})
export default class TUserRoleCode extends Vue {
	@Inject('tUserRoleCodeService') private tUserRoleCodeService: () => TUserRoleCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	private removeId: number = null;

	public tUserRoleCodes: ITUserRoleCode[] = [];

	public isFetching = false;

	public mounted(): void {
		this.retrieveAllTUserRoleCodes();
	}

	public clear(): void {
		this.retrieveAllTUserRoleCodes();
	}

	public retrieveAllTUserRoleCodes(): void {
		this.isFetching = true;
		this.tUserRoleCodeService()
			.retrieve()
			.then(
				res => {
					this.tUserRoleCodes = res.data;
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

	public prepareRemove(instance: ITUserRoleCode): void {
		this.removeId = instance.id;
		if (<any>this.$refs.removeEntity) {
			(<any>this.$refs.removeEntity).show();
		}
	}

	public removeTUserRoleCode(): void {
		this.tUserRoleCodeService()
			.delete(this.removeId)
			.then(() => {
				const message = this.$t('sainsApp.tUserRoleCode.deleted', { param: this.removeId });
				this.$bvToast.toast(message.toString(), {
					toaster: 'b-toaster-top-center',
					title: 'Info',
					variant: 'danger',
					solid: true,
					autoHideDelay: 5000,
				});
				this.removeId = null;
				this.retrieveAllTUserRoleCodes();
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
