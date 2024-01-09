import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITTitleCode } from '@/shared/model/t-title-code.model';

import TTitleCodeService from './t-title-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TTitleCode extends Vue {
  @Inject('tTitleCodeService') private tTitleCodeService: () => TTitleCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public tTitleCodes: ITTitleCode[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTTitleCodes();
  }

  public clear(): void {
    this.retrieveAllTTitleCodes();
  }

  public retrieveAllTTitleCodes(): void {
    this.isFetching = true;
    this.tTitleCodeService()
      .retrieve()
      .then(
        res => {
          this.tTitleCodes = res.data;
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

  public prepareRemove(instance: ITTitleCode): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTTitleCode(): void {
    this.tTitleCodeService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('sainsApp.tTitleCode.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllTTitleCodes();
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
