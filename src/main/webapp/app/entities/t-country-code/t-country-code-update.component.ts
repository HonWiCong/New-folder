import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import TStateCodeService from '@/entities/t-state-code/t-state-code.service';
import { ITStateCode } from '@/shared/model/t-state-code.model';

import { ITCountryCode, TCountryCode } from '@/shared/model/t-country-code.model';
import TCountryCodeService from './t-country-code.service';

const validations: any = {
  tCountryCode: {
    countryCode: {},
    countryName: {},
    countryNationality: {},
    enteredBy: {},
    enteredDate: {},
    modifyBy: {},
    modifiedDate: {},
    orgCustomerType: {},
  },
};

@Component({
  validations,
})
export default class TCountryCodeUpdate extends Vue {
  @Inject('tCountryCodeService') private tCountryCodeService: () => TCountryCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  public tCountryCode: ITCountryCode = new TCountryCode();

  @Inject('tStateCodeService') private tStateCodeService: () => TStateCodeService;

  public tStateCodes: ITStateCode[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tCountryCodeId) {
        vm.retrieveTCountryCode(to.params.tCountryCodeId);
      }
      vm.initRelationships();
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
    if (this.tCountryCode.id) {
      this.tCountryCodeService()
        .update(this.tCountryCode)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tCountryCode.updated', { param: param.id });
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
      this.tCountryCodeService()
        .create(this.tCountryCode)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tCountryCode.created', { param: param.id });
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
      this.tCountryCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tCountryCode[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.tCountryCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tCountryCode[field] = null;
    }
  }

  public retrieveTCountryCode(tCountryCodeId): void {
    this.tCountryCodeService()
      .find(tCountryCodeId)
      .then(res => {
        res.enteredDate = new Date(res.enteredDate);
        res.modifiedDate = new Date(res.modifiedDate);
        this.tCountryCode = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.tStateCodeService()
      .retrieve()
      .then(res => {
        this.tStateCodes = res.data;
      });
  }
}
