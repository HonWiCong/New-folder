import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import TDivisionCodeService from '@/entities/t-division-code/t-division-code.service';
import { ITDivisionCode } from '@/shared/model/t-division-code.model';

import { ITDistrictCode, TDistrictCode } from '@/shared/model/t-district-code.model';
import TDistrictCodeService from './t-district-code.service';

const validations: any = {
  tDistrictCode: {
    disName: {},
    enteredBy: {},
    enteredDate: {},
    modifiedBy: {},
    modifiedDate: {},
  },
};

@Component({
  validations,
})
export default class TDistrictCodeUpdate extends Vue {
  @Inject('tDistrictCodeService') private tDistrictCodeService: () => TDistrictCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  public tDistrictCode: ITDistrictCode = new TDistrictCode();

  @Inject('tDivisionCodeService') private tDivisionCodeService: () => TDivisionCodeService;

  public tDivisionCodes: ITDivisionCode[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tDistrictCodeId) {
        vm.retrieveTDistrictCode(to.params.tDistrictCodeId);
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
    if (this.tDistrictCode.id) {
      this.tDistrictCodeService()
        .update(this.tDistrictCode)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tDistrictCode.updated', { param: param.id });
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
      this.tDistrictCodeService()
        .create(this.tDistrictCode)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tDistrictCode.created', { param: param.id });
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
      this.tDistrictCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tDistrictCode[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.tDistrictCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tDistrictCode[field] = null;
    }
  }

  public retrieveTDistrictCode(tDistrictCodeId): void {
    this.tDistrictCodeService()
      .find(tDistrictCodeId)
      .then(res => {
        res.enteredDate = new Date(res.enteredDate);
        res.modifiedDate = new Date(res.modifiedDate);
        this.tDistrictCode = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.tDivisionCodeService()
      .retrieve()
      .then(res => {
        this.tDivisionCodes = res.data;
      });
  }
}
