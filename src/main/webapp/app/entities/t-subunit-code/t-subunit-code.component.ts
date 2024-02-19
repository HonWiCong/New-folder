import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITSubunitCode } from '@/shared/model/t-subunit-code.model';

import TSubunitCodeService from './t-subunit-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
	mixins: [Vue2Filters.mixin],
})
export default class TSubunitCode extends Vue {
	@Inject('tSubunitCodeService') private tSubunitCodeService: () => TSubunitCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	private removeId: number = null;

	public tSubunitCodes: ITSubunitCode[] = [];

	public isFetching = false;

	public mounted(): void {
		this.retrieveAllTSubunitCodes();
	}

	public clear(): void {
		this.retrieveAllTSubunitCodes();
	}

	public retrieveAllTSubunitCodes(): void {
		this.isFetching = true;
		this.tSubunitCodeService()
			.retrieve()
			.then(
				res => {
					this.tSubunitCodes = res.data;
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

	public prepareRemove(instance: ITSubunitCode): void {
		this.removeId = instance.id;
		if (<any>this.$refs.removeEntity) {
			(<any>this.$refs.removeEntity).show();
		}
	}

	public removeTSubunitCode(): void {
		this.tSubunitCodeService()
			.delete(this.removeId)
			.then(() => {
				const message = this.$t('sainsApp.tSubunitCode.deleted', { param: this.removeId });
				this.$bvToast.toast(message.toString(), {
					toaster: 'b-toaster-top-center',
					title: 'Info',
					variant: 'danger',
					solid: true,
					autoHideDelay: 5000,
				});
				this.removeId = null;
				this.retrieveAllTSubunitCodes();
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
