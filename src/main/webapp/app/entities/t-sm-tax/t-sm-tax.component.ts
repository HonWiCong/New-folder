import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITSmTax } from '@/shared/model/t-sm-tax.model';

import TSmTaxService from './t-sm-tax.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TSmTax extends Vue {
  @Inject('tSmTaxService') private tSmTaxService: () => TSmTaxService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public tSmTaxes: ITSmTax[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTSmTaxs();
  }

  public clear(): void {
    this.retrieveAllTSmTaxs();
  }

  public retrieveAllTSmTaxs(): void {
    this.isFetching = true;
    this.tSmTaxService()
      .retrieve()
      .then(
        res => {
          this.tSmTaxes = res.data;
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

  public prepareRemove(instance: ITSmTax): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTSmTax(): void {
    this.tSmTaxService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('sainsApp.tSmTax.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllTSmTaxs();
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
