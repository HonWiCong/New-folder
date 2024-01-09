import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import { ITSmTax, TSmTax } from '@/shared/model/t-sm-tax.model';
import TSmTaxService from './t-sm-tax.service';
import { TaxStatus } from '@/shared/model/enumerations/tax-status.model';

const validations: any = {
  tSmTax: {
    smTaxCode: {},
    smTaxDescription: {},
    smTaxPercentage: {},
    smTaxType: {},
    smTaxGstCode: {},
    smTaxGstType: {},
    smTaxCollectedGlCode: {},
    smTaxCollectedGlDesc: {},
    smTaxCollectedCostCenter: {},
    smTaxPaidGlCode: {},
    smTaxPaidGlDesc: {},
    smTaxPaidCostCenter: {},
    smTaxTaxAuthority: {},
    smTaxStatus: {},
    smTaxEnteredBy: {},
    smTaxEnteredDate: {},
    smTaxModifiedBy: {},
    smTaxModifiedDate: {},
    smTaxConfirmedBy: {},
    smTaxConfirmedDate: {},
    smTaxNarration: {},
    smTaxDisplay: {},
    smTaxRcmFlag: {},
    smTaxCga: {},
  },
};

@Component({
  validations,
})
export default class TSmTaxUpdate extends Vue {
  @Inject('tSmTaxService') private tSmTaxService: () => TSmTaxService;
  @Inject('alertService') private alertService: () => AlertService;

  public tSmTax: ITSmTax = new TSmTax();
  public taxStatusValues: string[] = Object.keys(TaxStatus);
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tSmTaxId) {
        vm.retrieveTSmTax(to.params.tSmTaxId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.tSmTax.id) {
      this.tSmTaxService()
        .update(this.tSmTax)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tSmTax.updated', { param: param.id });
          return (this.$root as any).$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.tSmTaxService()
        .create(this.tSmTax)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tSmTax.created', { param: param.id });
          (this.$root as any).$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public convertDateTimeFromServer(date: Date): string {
    if (date && dayjs(date).isValid()) {
      return dayjs(date).format(DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.tSmTax[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tSmTax[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.tSmTax[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tSmTax[field] = null;
    }
  }

  public retrieveTSmTax(tSmTaxId): void {
    this.tSmTaxService()
      .find(tSmTaxId)
      .then(res => {
        res.smTaxEnteredDate = new Date(res.smTaxEnteredDate);
        res.smTaxModifiedDate = new Date(res.smTaxModifiedDate);
        res.smTaxConfirmedDate = new Date(res.smTaxConfirmedDate);
        this.tSmTax = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
