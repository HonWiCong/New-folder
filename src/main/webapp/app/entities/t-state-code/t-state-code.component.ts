import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITStateCode } from '@/shared/model/t-state-code.model';

import TStateCodeService from './t-state-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TStateCode extends Vue {
  @Inject('tStateCodeService') private tStateCodeService: () => TStateCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public tStateCodes: ITStateCode[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTStateCodes();
  }

  public clear(): void {
    this.retrieveAllTStateCodes();
  }

  public retrieveAllTStateCodes(): void {
    this.isFetching = true;
    this.tStateCodeService()
      .retrieve()
      .then(
        res => {
          this.tStateCodes = res.data;
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

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
