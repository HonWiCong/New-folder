import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import TCountryCodeService from '@/entities/t-country-code/t-country-code.service';
import { ITCountryCode } from '@/shared/model/t-country-code.model';

import TCityCodeService from '@/entities/t-city-code/t-city-code.service';
import { ITCityCode } from '@/shared/model/t-city-code.model';

import { ITStateCode, TStateCode } from '@/shared/model/t-state-code.model';
import TStateCodeService from './t-state-code.service';

const validations: any = {
  tStateCode: {
    stateName: {},
    stateCode: {},
    enteredBy: {},
    enteredDate: {},
    modifiedBy: {},
    modifiedDate: {},
  },
};

@Component({
  validations,
})
export default class TStateCodeUpdate extends Vue {
  @Inject('tStateCodeService') private tStateCodeService: () => TStateCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  public tStateCode: ITStateCode = new TStateCode();

  @Inject('tCountryCodeService') private tCountryCodeService: () => TCountryCodeService;

  public tCountryCodes: ITCountryCode[] = [];

  @Inject('tCityCodeService') private tCityCodeService: () => TCityCodeService;

  public tCityCodes: ITCityCode[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tStateCodeId) {
        vm.retrieveTStateCode(to.params.tStateCodeId);
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
    if (this.tStateCode.id) {
      this.tStateCodeService()
        .update(this.tStateCode)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tStateCode.updated', { param: param.id });
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
      this.tStateCodeService()
        .create(this.tStateCode)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tStateCode.created', { param: param.id });
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
      this.tStateCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tStateCode[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.tStateCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tStateCode[field] = null;
    }
  }

  public retrieveTStateCode(tStateCodeId): void {
    this.tStateCodeService()
      .find(tStateCodeId)
      .then(res => {
        res.enteredDate = new Date(res.enteredDate);
        res.modifiedDate = new Date(res.modifiedDate);
        this.tStateCode = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.tCountryCodeService()
      .retrieve()
      .then(res => {
        this.tCountryCodes = res.data;
      });
    this.tCityCodeService()
      .retrieve()
      .then(res => {
        this.tCityCodes = res.data;
      });
  }
}
