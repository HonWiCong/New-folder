import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITAuditTrail } from '@/shared/model/t-audit-trail.model';

import TAuditTrailService from './t-audit-trail.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TAuditTrail extends Vue {
  @Inject('tAuditTrailService') private tAuditTrailService: () => TAuditTrailService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public tAuditTrails: ITAuditTrail[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTAuditTrails();
  }

  public clear(): void {
    this.retrieveAllTAuditTrails();
  }

  public retrieveAllTAuditTrails(): void {
    this.isFetching = true;
    this.tAuditTrailService()
      .retrieve()
      .then(
        res => {
          this.tAuditTrails = res.data;
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

  public prepareRemove(instance: ITAuditTrail): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTAuditTrail(): void {
    this.tAuditTrailService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('sainsApp.tAuditTrail.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllTAuditTrails();
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
