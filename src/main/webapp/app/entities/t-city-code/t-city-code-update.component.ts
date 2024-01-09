import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import TStateCodeService from '@/entities/t-state-code/t-state-code.service';
import { ITStateCode } from '@/shared/model/t-state-code.model';

import { ITCityCode, TCityCode } from '@/shared/model/t-city-code.model';
import TCityCodeService from './t-city-code.service';

const validations: any = {
  tCityCode: {
    cityCode: {},
    cityName: {},
    enteredBy: {},
    enteredDate: {},
    modifiedBy: {},
    modifiedDate: {},
  },
};

@Component({
  validations,
})
export default class TCityCodeUpdate extends Vue {
  @Inject('tCityCodeService') private tCityCodeService: () => TCityCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  public tCityCode: ITCityCode = new TCityCode();

  @Inject('tStateCodeService') private tStateCodeService: () => TStateCodeService;

  public tStateCodes: ITStateCode[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tCityCodeId) {
        vm.retrieveTCityCode(to.params.tCityCodeId);
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
    if (this.tCityCode.id) {
      this.tCityCodeService()
        .update(this.tCityCode)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tCityCode.updated', { param: param.id });
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
      this.tCityCodeService()
        .create(this.tCityCode)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tCityCode.created', { param: param.id });
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
      this.tCityCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tCityCode[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.tCityCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tCityCode[field] = null;
    }
  }

  public retrieveTCityCode(tCityCodeId): void {
    this.tCityCodeService()
      .find(tCityCodeId)
      .then(res => {
        res.enteredDate = new Date(res.enteredDate);
        res.modifiedDate = new Date(res.modifiedDate);
        this.tCityCode = res;
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
