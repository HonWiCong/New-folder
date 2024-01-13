import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITOrgContactPerson } from '@/shared/model/t-org-contact-person.model';

import TOrgContactPersonService from './t-org-contact-person.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
	mixins: [Vue2Filters.mixin],
})
export default class TOrgContactPerson extends Vue {
	@Inject('tOrgContactPersonService') private tOrgContactPersonService: () => TOrgContactPersonService;
	@Inject('alertService') private alertService: () => AlertService;

	private removeId: number = null;

	public tOrgContactPeople: ITOrgContactPerson[] = [];

	public isFetching = false;

	public mounted(): void {
		this.retrieveAllTOrgContactPersons();
	}

	public clear(): void {
		this.retrieveAllTOrgContactPersons();
	}

	public retrieveAllTOrgContactPersons(): void {
		this.isFetching = true;
		this.tOrgContactPersonService()
			.retrieve()
			.then(
				res => {
					this.tOrgContactPeople = res.data;
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

	public prepareRemove(instance: ITOrgContactPerson): void {
		this.removeId = instance.id;
		if (<any>this.$refs.removeEntity) {
			(<any>this.$refs.removeEntity).show();
		}
	}

	public removeTOrgContactPerson(): void {
		this.tOrgContactPersonService()
			.delete(this.removeId)
			.then(() => {
				const message = this.$t('sainsApp.tOrgContactPerson.deleted', { param: this.removeId });
				this.$bvToast.toast(message.toString(), {
					toaster: 'b-toaster-top-center',
					title: 'Info',
					variant: 'danger',
					solid: true,
					autoHideDelay: 5000,
				});
				this.removeId = null;
				this.retrieveAllTOrgContactPersons();
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
