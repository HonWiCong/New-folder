import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITStateCode } from '@/shared/model/t-state-code.model';

import TStateCodeService from './t-state-code.service';
import AlertService from '@/shared/alert/alert.service';
import buildPaginationQueryOpts from '@/shared/sort/sorts';
import { Search } from 'lucide-vue';

@Component({
	mixins: [Vue2Filters.mixin],
	components: {
		Search,
	},
})
export default class TStateCode extends Vue {
	@Inject('tStateCodeService') private tStateCodeService: () => TStateCodeService;
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

	public tStateCodes: ITStateCode[] = [];

	public isFetching = false;

	public mounted(): void {
		this.retrieveAllTStateCodes();
	}

	public clear(): void {
		this.page = 1;
		this.retrieveAllTStateCodes();
	}

	public retrieveAllTStateCodes(): void {
		this.isFetching = true;
		const paginationQuery = {
			page: this.page - 1,
			size: this.itemsPerPage,
			sort: this.sort(),
		};
		let searchQuery = buildPaginationQueryOpts(paginationQuery);
		if (this.search) {
			searchQuery += `&stateName.contains=${this.search}`;
			searchQuery += `&countryName.contains=${this.search}`;
		}
		this.tStateCodeService()
			.retrieve(searchQuery)
			.then(
				res => {
					this.tStateCodes = res.data;
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

	public prepareRemove(instance: ITStateCode): void {
		this.removeId = instance.id;
		if (<any>this.$refs.removeEntity) {
			(<any>this.$refs.removeEntity).show();
		}
	}

	public removeTStateCode(): void {
		this.tStateCodeService()
			.delete(this.removeId)
			.then(() => {
				const message = this.$t('sainsApp.tStateCode.deleted', { param: this.removeId });
				this.$bvToast.toast(message.toString(), {
					toaster: 'b-toaster-top-center',
					title: 'Info',
					variant: 'danger',
					solid: true,
					autoHideDelay: 5000,
				});
				this.removeId = null;
				this.retrieveAllTStateCodes();
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
		this.retrieveAllTStateCodes();
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
