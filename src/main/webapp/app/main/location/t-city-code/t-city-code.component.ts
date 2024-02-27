import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITCityCode } from '@/shared/model/t-city-code.model';

import TCityCodeService from './t-city-code.service';
import AlertService from '@/shared/alert/alert.service';
import buildPaginationQueryOpts from '@/shared/sort/sorts';
import { Search } from 'lucide-vue';

@Component({
	mixins: [Vue2Filters.mixin],
	components: {
		Search,
	},
})
export default class TCityCode extends Vue {
	@Inject('tCityCodeService') private tCityCodeService: () => TCityCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	private removeId: number = null;
	public itemsPerPage = 20;
	public queryCount: number = null;
	public page = 1;
	public previousPage = 1;
	public propOrder = 'id';
	public reverse = false;
	public totalItems = 0;
	public search = '';

	public tCityCodes: ITCityCode[] = [];

	public isFetching = false;

	public mounted(): void {
		this.retrieveAllTCityCodes();
	}

	public clear(): void {
		this.page = 1;
		this.retrieveAllTCityCodes();
	}

	public retrieveAllTCityCodes(): void {
		this.isFetching = true;
		const paginationQuery = {
			page: this.page - 1,
			size: this.itemsPerPage,
			sort: this.sort(),
		};
		let searchQuery = buildPaginationQueryOpts(paginationQuery);
		if (this.search) {
			searchQuery += `&cityName.contains=${this.search}`;
		}
		this.tCityCodeService()
			.retrieve(searchQuery)
			.then(
				res => {
					this.tCityCodes = res.data;
					this.totalItems = Number(res.headers['x-total-count']);
					this.queryCount = this.totalItems;
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
		this.retrieveAllTCityCodes();
	}

	public changeOrder(propOrder): void {
		this.propOrder = propOrder;
		this.reverse = !this.reverse;
		this.transition();
	}

	public closeDialog(): void {
		(<any>this.$refs.removeEntity).hide();
	}
}
