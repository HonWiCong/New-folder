import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITSectorCode } from '@/shared/model/t-sector-code.model';

import TSectorCodeService from './t-sector-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TSectorCode extends Vue {
  @Inject('tSectorCodeService') private tSectorCodeService: () => TSectorCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public tSectorCodes: ITSectorCode[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTSectorCodes();
  }

  public clear(): void {
    this.retrieveAllTSectorCodes();
  }

  public retrieveAllTSectorCodes(): void {
    this.isFetching = true;
    this.tSectorCodeService()
      .retrieve()
      .then(
        res => {
          this.tSectorCodes = res.data;
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

  public prepareRemove(instance: ITSectorCode): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTSectorCode(): void {
    this.tSectorCodeService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('sainsApp.tSectorCode.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllTSectorCodes();
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
