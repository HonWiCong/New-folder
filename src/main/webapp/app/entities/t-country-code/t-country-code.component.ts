import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITCountryCode } from '@/shared/model/t-country-code.model';

import TCountryCodeService from './t-country-code.service';
import AlertService from '@/shared/alert/alert.service';
import buildPaginationQueryOpts from '@/shared/sort/sorts';
import { Search } from 'lucide-vue';

@Component({
	mixins: [Vue2Filters.mixin],
	components: {
		Search,
	},
})
export default class TCountryCode extends Vue {
	@Inject('tCountryCodeService') private tCountryCodeService: () => TCountryCodeService;
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

	public tCountryCodes: ITCountryCode[] = [];

	public isFetching = false;

	public mounted(): void {
		this.retrieveAllTCountryCodes();
	}

	public clear(): void {
		this.page = 1;
		this.retrieveAllTCountryCodes();
	}

	public retrieveAllTCountryCodes(): void {
		this.isFetching = true;
		const paginationQuery = {
			page: this.page - 1,
			size: this.itemsPerPage,
			sort: this.sort(),
		};
		let searchQuery = buildPaginationQueryOpts(paginationQuery);
		if (this.search) {
			searchQuery += `&countryName.contains=${this.search}`;
		}
		console.log('searchQuery', searchQuery);
		this.tCountryCodeService()
			.retrieve(searchQuery)
			.then(
				res => {
					this.tCountryCodes = res.data;
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

	public prepareRemove(instance: ITCountryCode): void {
		this.removeId = instance.id;
		if (<any>this.$refs.removeEntity) {
			(<any>this.$refs.removeEntity).show();
		}
	}

	public removeTCountryCode(): void {
		this.tCountryCodeService()
			.delete(this.removeId)
			.then(() => {
				const message = this.$t('sainsApp.tCountryCode.deleted', { param: this.removeId });
				this.$bvToast.toast(message.toString(), {
					toaster: 'b-toaster-top-center',
					title: 'Info',
					variant: 'danger',
					solid: true,
					autoHideDelay: 5000,
				});
				this.removeId = null;
				this.retrieveAllTCountryCodes();
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
		this.retrieveAllTCountryCodes();
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
