import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITDistrictCode } from '@/shared/model/t-district-code.model';

import TDistrictCodeService from './t-district-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TDistrictCode extends Vue {
  @Inject('tDistrictCodeService') private tDistrictCodeService: () => TDistrictCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public tDistrictCodes: ITDistrictCode[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTDistrictCodes();
  }

  public clear(): void {
    this.retrieveAllTDistrictCodes();
  }

  public retrieveAllTDistrictCodes(): void {
    this.isFetching = true;
    this.tDistrictCodeService()
      .retrieve()
      .then(
        res => {
          this.tDistrictCodes = res.data;
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

  public prepareRemove(instance: ITDistrictCode): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTDistrictCode(): void {
    this.tDistrictCodeService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('sainsApp.tDistrictCode.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllTDistrictCodes();
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
