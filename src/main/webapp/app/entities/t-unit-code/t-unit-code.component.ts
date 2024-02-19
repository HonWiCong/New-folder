import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITUnitCode } from '@/shared/model/t-unit-code.model';

import TUnitCodeService from './t-unit-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
	mixins: [Vue2Filters.mixin],
})
export default class TUnitCode extends Vue {
	@Inject('tUnitCodeService') private tUnitCodeService: () => TUnitCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	private removeId: number = null;

	public tUnitCodes: ITUnitCode[] = [];

	public isFetching = false;

	public mounted(): void {
		this.retrieveAllTUnitCodes();
	}

	public clear(): void {
		this.retrieveAllTUnitCodes();
	}

	public retrieveAllTUnitCodes(): void {
		this.isFetching = true;
		this.tUnitCodeService()
			.retrieve()
			.then(
				res => {
					this.tUnitCodes = res.data;
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

	public prepareRemove(instance: ITUnitCode): void {
		this.removeId = instance.id;
		if (<any>this.$refs.removeEntity) {
			(<any>this.$refs.removeEntity).show();
		}
	}

	public removeTUnitCode(): void {
		this.tUnitCodeService()
			.delete(this.removeId)
			.then(() => {
				const message = this.$t('sainsApp.tUnitCode.deleted', { param: this.removeId });
				this.$bvToast.toast(message.toString(), {
					toaster: 'b-toaster-top-center',
					title: 'Info',
					variant: 'danger',
					solid: true,
					autoHideDelay: 5000,
				});
				this.removeId = null;
				this.retrieveAllTUnitCodes();
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
