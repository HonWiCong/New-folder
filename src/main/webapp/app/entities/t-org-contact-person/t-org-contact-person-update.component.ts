import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import TOrganizationService from '@/entities/t-organization/t-organization.service';
import { ITOrganization } from '@/shared/model/t-organization.model';

import { ITOrgContactPerson, TOrgContactPerson } from '@/shared/model/t-org-contact-person.model';
import TOrgContactPersonService from './t-org-contact-person.service';

const validations: any = {
  tOrgContactPerson: {
    ocpOrgcodeid: {},
    ocpTitle: {},
    ocpName: {},
    ocpDesignation: {},
    ocpTelephone1: {},
    ocpHandphone: {},
    ocpMail: {},
    ocpSector: {},
    ocpStatus: {},
    enteredBy: {},
    enteredDate: {},
    modifiedBy: {},
    modifiedDate: {},
  },
};

@Component({
  validations,
})
export default class TOrgContactPersonUpdate extends Vue {
  @Inject('tOrgContactPersonService') private tOrgContactPersonService: () => TOrgContactPersonService;
  @Inject('alertService') private alertService: () => AlertService;

  public tOrgContactPerson: ITOrgContactPerson = new TOrgContactPerson();

  @Inject('tOrganizationService') private tOrganizationService: () => TOrganizationService;

  public tOrganizations: ITOrganization[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tOrgContactPersonId) {
        vm.retrieveTOrgContactPerson(to.params.tOrgContactPersonId);
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
    if (this.tOrgContactPerson.id) {
      this.tOrgContactPersonService()
        .update(this.tOrgContactPerson)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tOrgContactPerson.updated', { param: param.id });
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
      this.tOrgContactPersonService()
        .create(this.tOrgContactPerson)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tOrgContactPerson.created', { param: param.id });
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
      this.tOrgContactPerson[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tOrgContactPerson[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.tOrgContactPerson[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tOrgContactPerson[field] = null;
    }
  }

  public retrieveTOrgContactPerson(tOrgContactPersonId): void {
    this.tOrgContactPersonService()
      .find(tOrgContactPersonId)
      .then(res => {
        res.enteredDate = new Date(res.enteredDate);
        res.modifiedDate = new Date(res.modifiedDate);
        this.tOrgContactPerson = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.tOrganizationService()
      .retrieve()
      .then(res => {
        this.tOrganizations = res.data;
      });
  }
}
