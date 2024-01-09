import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import { ITBrandCode, TBrandCode } from '@/shared/model/t-brand-code.model';
import TBrandCodeService from './t-brand-code.service';

const validations: any = {
  tBrandCode: {
    brandName: {},
    enteredBy: {},
    enteredDate: {},
    modifyBy: {},
    modifiedDate: {},
  },
};

@Component({
  validations,
})
export default class TBrandCodeUpdate extends Vue {
  @Inject('tBrandCodeService') private tBrandCodeService: () => TBrandCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  public tBrandCode: ITBrandCode = new TBrandCode();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tBrandCodeId) {
        vm.retrieveTBrandCode(to.params.tBrandCodeId);
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
    if (this.tBrandCode.id) {
      this.tBrandCodeService()
        .update(this.tBrandCode)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tBrandCode.updated', { param: param.id });
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
      this.tBrandCodeService()
        .create(this.tBrandCode)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tBrandCode.created', { param: param.id });
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
      this.tBrandCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tBrandCode[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.tBrandCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tBrandCode[field] = null;
    }
  }

  public retrieveTBrandCode(tBrandCodeId): void {
    this.tBrandCodeService()
      .find(tBrandCodeId)
      .then(res => {
        res.enteredDate = new Date(res.enteredDate);
        res.modifiedDate = new Date(res.modifiedDate);
        this.tBrandCode = res;
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
