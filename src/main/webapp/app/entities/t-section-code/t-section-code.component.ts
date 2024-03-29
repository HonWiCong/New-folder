import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITSectionCode } from '@/shared/model/t-section-code.model';

import TSectionCodeService from './t-section-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
	mixins: [Vue2Filters.mixin],
})
export default class TSectionCode extends Vue {
	@Inject('tSectionCodeService') private tSectionCodeService: () => TSectionCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	private removeId: number = null;

	public tSectionCodes: ITSectionCode[] = [];

	public isFetching = false;

	public mounted(): void {
		this.retrieveAllTSectionCodes();
	}

	public clear(): void {
		this.retrieveAllTSectionCodes();
	}

	public retrieveAllTSectionCodes(): void {
		this.isFetching = true;
		this.tSectionCodeService()
			.retrieve()
			.then(
				res => {
					this.tSectionCodes = res.data;
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

	public prepareRemove(instance: ITSectionCode): void {
		this.removeId = instance.id;
		if (<any>this.$refs.removeEntity) {
			(<any>this.$refs.removeEntity).show();
		}
	}

	public removeTSectionCode(): void {
		this.tSectionCodeService()
			.delete(this.removeId)
			.then(() => {
				const message = this.$t('sainsApp.tSectionCode.deleted', { param: this.removeId });
				this.$bvToast.toast(message.toString(), {
					toaster: 'b-toaster-top-center',
					title: 'Info',
					variant: 'danger',
					solid: true,
					autoHideDelay: 5000,
				});
				this.removeId = null;
				this.retrieveAllTSectionCodes();
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
