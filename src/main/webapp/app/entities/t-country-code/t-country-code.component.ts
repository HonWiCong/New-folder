import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITCountryCode } from '@/shared/model/t-country-code.model';

import TCountryCodeService from './t-country-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TCountryCode extends Vue {
  @Inject('tCountryCodeService') private tCountryCodeService: () => TCountryCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public tCountryCodes: ITCountryCode[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTCountryCodes();
  }

  public clear(): void {
    this.retrieveAllTCountryCodes();
  }

  public retrieveAllTCountryCodes(): void {
    this.isFetching = true;
    this.tCountryCodeService()
      .retrieve()
      .then(
        res => {
          this.tCountryCodes = res.data;
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

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
