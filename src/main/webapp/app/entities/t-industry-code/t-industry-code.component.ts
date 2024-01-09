import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITIndustryCode } from '@/shared/model/t-industry-code.model';

import TIndustryCodeService from './t-industry-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TIndustryCode extends Vue {
  @Inject('tIndustryCodeService') private tIndustryCodeService: () => TIndustryCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public tIndustryCodes: ITIndustryCode[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTIndustryCodes();
  }

  public clear(): void {
    this.retrieveAllTIndustryCodes();
  }

  public retrieveAllTIndustryCodes(): void {
    this.isFetching = true;
    this.tIndustryCodeService()
      .retrieve()
      .then(
        res => {
          this.tIndustryCodes = res.data;
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

  public prepareRemove(instance: ITIndustryCode): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTIndustryCode(): void {
    this.tIndustryCodeService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('sainsApp.tIndustryCode.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllTIndustryCodes();
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
