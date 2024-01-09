import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import { ITAuditTrail, TAuditTrail } from '@/shared/model/t-audit-trail.model';
import TAuditTrailService from './t-audit-trail.service';

const validations: any = {
  tAuditTrail: {
    userId: {},
    dateTime: {},
    tableName: {},
    auditAction: {},
    recordId: {},
    fieldDesc: {},
    atStatus: {},
    stFullDesc: {},
  },
};

@Component({
  validations,
})
export default class TAuditTrailUpdate extends Vue {
  @Inject('tAuditTrailService') private tAuditTrailService: () => TAuditTrailService;
  @Inject('alertService') private alertService: () => AlertService;

  public tAuditTrail: ITAuditTrail = new TAuditTrail();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tAuditTrailId) {
        vm.retrieveTAuditTrail(to.params.tAuditTrailId);
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
    if (this.tAuditTrail.id) {
      this.tAuditTrailService()
        .update(this.tAuditTrail)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tAuditTrail.updated', { param: param.id });
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
      this.tAuditTrailService()
        .create(this.tAuditTrail)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tAuditTrail.created', { param: param.id });
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
      this.tAuditTrail[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tAuditTrail[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.tAuditTrail[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tAuditTrail[field] = null;
    }
  }

  public retrieveTAuditTrail(tAuditTrailId): void {
    this.tAuditTrailService()
      .find(tAuditTrailId)
      .then(res => {
        res.dateTime = new Date(res.dateTime);
        this.tAuditTrail = res;
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
