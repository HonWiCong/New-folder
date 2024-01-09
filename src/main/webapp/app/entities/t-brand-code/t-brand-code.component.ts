import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITBrandCode } from '@/shared/model/t-brand-code.model';

import TBrandCodeService from './t-brand-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TBrandCode extends Vue {
  @Inject('tBrandCodeService') private tBrandCodeService: () => TBrandCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public tBrandCodes: ITBrandCode[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTBrandCodes();
  }

  public clear(): void {
    this.retrieveAllTBrandCodes();
  }

  public retrieveAllTBrandCodes(): void {
    this.isFetching = true;
    this.tBrandCodeService()
      .retrieve()
      .then(
        res => {
          this.tBrandCodes = res.data;
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

  public prepareRemove(instance: ITBrandCode): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTBrandCode(): void {
    this.tBrandCodeService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('sainsApp.tBrandCode.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllTBrandCodes();
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
