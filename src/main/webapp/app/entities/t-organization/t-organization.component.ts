import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITOrganization } from '@/shared/model/t-organization.model';

import TOrganizationService from './t-organization.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TOrganization extends Vue {
  @Inject('tOrganizationService') private tOrganizationService: () => TOrganizationService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public tOrganizations: ITOrganization[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTOrganizations();
  }

  public clear(): void {
    this.retrieveAllTOrganizations();
  }

  public retrieveAllTOrganizations(): void {
    this.isFetching = true;
    this.tOrganizationService()
      .retrieve()
      .then(
        res => {
          this.tOrganizations = res.data;
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

  public prepareRemove(instance: ITOrganization): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTOrganization(): void {
    this.tOrganizationService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('sainsApp.tOrganization.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllTOrganizations();
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
