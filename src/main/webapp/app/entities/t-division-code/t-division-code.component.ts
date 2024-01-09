import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITDivisionCode } from '@/shared/model/t-division-code.model';

import TDivisionCodeService from './t-division-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TDivisionCode extends Vue {
  @Inject('tDivisionCodeService') private tDivisionCodeService: () => TDivisionCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public tDivisionCodes: ITDivisionCode[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTDivisionCodes();
  }

  public clear(): void {
    this.retrieveAllTDivisionCodes();
  }

  public retrieveAllTDivisionCodes(): void {
    this.isFetching = true;
    this.tDivisionCodeService()
      .retrieve()
      .then(
        res => {
          this.tDivisionCodes = res.data;
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

  public prepareRemove(instance: ITDivisionCode): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTDivisionCode(): void {
    this.tDivisionCodeService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('sainsApp.tDivisionCode.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllTDivisionCodes();
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
