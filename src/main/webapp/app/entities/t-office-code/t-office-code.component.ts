import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITOfficeCode } from '@/shared/model/t-office-code.model';

import TOfficeCodeService from './t-office-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
	mixins: [Vue2Filters.mixin],
})
export default class TOfficeCode extends Vue {
	@Inject('tOfficeCodeService') private tOfficeCodeService: () => TOfficeCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	private removeId: number = null;

	public tOfficeCodes: ITOfficeCode[] = [];

	public isFetching = false;

	public mounted(): void {
		this.retrieveAllTOfficeCodes();
	}

	public clear(): void {
		this.retrieveAllTOfficeCodes();
	}

	public retrieveAllTOfficeCodes(): void {
		this.isFetching = true;
		this.tOfficeCodeService()
			.retrieve()
			.then(
				res => {
					this.tOfficeCodes = res.data;
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

	public prepareRemove(instance: ITOfficeCode): void {
		this.removeId = instance.id;
		if (<any>this.$refs.removeEntity) {
			(<any>this.$refs.removeEntity).show();
		}
	}

	public removeTOfficeCode(): void {
		this.tOfficeCodeService()
			.delete(this.removeId)
			.then(() => {
				const message = this.$t('sainsApp.tOfficeCode.deleted', { param: this.removeId });
				this.$bvToast.toast(message.toString(), {
					toaster: 'b-toaster-top-center',
					title: 'Info',
					variant: 'danger',
					solid: true,
					autoHideDelay: 5000,
				});
				this.removeId = null;
				this.retrieveAllTOfficeCodes();
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
