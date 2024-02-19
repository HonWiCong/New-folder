import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITCityCode } from '@/shared/model/t-city-code.model';

import TCityCodeService from './t-city-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
	mixins: [Vue2Filters.mixin],
})
export default class TCityCode extends Vue {
	@Inject('tCityCodeService') private tCityCodeService: () => TCityCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	private removeId: number = null;

	public tCityCodes: ITCityCode[] = [];

	public isFetching = false;

	public mounted(): void {
		this.retrieveAllTCityCodes();
	}

	public clear(): void {
		this.retrieveAllTCityCodes();
	}

	public retrieveAllTCityCodes(): void {
		this.isFetching = true;
		this.tCityCodeService()
			.retrieve()
			.then(
				res => {
					this.tCityCodes = res.data;
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

	public prepareRemove(instance: ITCityCode): void {
		this.removeId = instance.id;
		if (<any>this.$refs.removeEntity) {
			(<any>this.$refs.removeEntity).show();
		}
	}

	public removeTCityCode(): void {
		this.tCityCodeService()
			.delete(this.removeId)
			.then(() => {
				const message = this.$t('sainsApp.tCityCode.deleted', { param: this.removeId });
				this.$bvToast.toast(message.toString(), {
					toaster: 'b-toaster-top-center',
					title: 'Info',
					variant: 'danger',
					solid: true,
					autoHideDelay: 5000,
				});
				this.removeId = null;
				this.retrieveAllTCityCodes();
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
