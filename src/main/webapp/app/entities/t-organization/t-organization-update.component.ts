import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import TOrgContactPersonService from '@/entities/t-org-contact-person/t-org-contact-person.service';
import { ITOrgContactPerson } from '@/shared/model/t-org-contact-person.model';

import { ITOrganization, TOrganization } from '@/shared/model/t-organization.model';
import TOrganizationService from './t-organization.service';

const validations: any = {
  tOrganization: {
    orgHqid: {},
    orgHqBr: {},
    orgCode: {},
    orgBrn: {},
    orgPtaxid: {},
    orgDefaultTaxCode: {},
    orgCompanyGstNo: {},
    orgCompanyGstRegDate: {},
    orgCompanyGstDeregDate: {},
    orgPoTaxInclusive: {},
    orgName: {},
    orgNameOther: {},
    orgShortname: {},
    orgAddress: {},
    orgShippingAddress: {},
    orgBillingAddress: {},
    orgPostcode: {},
    orgCity: {},
    orgState: {},
    orgCountry: {},
    orgOffPhone1: {},
    orgOffPhone2: {},
    orgOffPhone3: {},
    orgOffFax1: {},
    orgOffFax2: {},
    orgCredittermid: {},
    orgCreditLimit: {},
    orgAgencyid: {},
    orgDivision: {},
    orgDistrict: {},
    orgWebsite: {},
    orgEmail: {},
    orgSupplierCategory: {},
    orgCurrencyCode: {},
    orgType: {},
    orgSectorid: {},
    orgSector: {},
    orgIndustry: {},
    orgSainsGroup: {},
    orgBumiputra: {},
    orgUpkReg: {},
    orgMofReg: {},
    orgDesignation: {},
    orgContpersonTitle: {},
    orgContperson: {},
    orgDirectline: {},
    orgContpEmail: {},
    orgContpHp: {},
    orgRemarks: {},
    orgActiveStatus: {},
    orgCcGc: {},
    orgCustomerCateCode: {},
    orgVendorCateCode: {},
    orgSalesCateCode: {},
    orgOutstandingBalance: {},
    orgOutstandingBalanceEx: {},
    orgCompanyCode: {},
    orgDcrownPostStatus: {},
    confirmedBy: {},
    confirmedDate: {},
    enteredBy: {},
    enteredDate: {},
    modifiedBy: {},
    modifiedDate: {},
  },
};

@Component({
  validations,
})
export default class TOrganizationUpdate extends Vue {
  @Inject('tOrganizationService') private tOrganizationService: () => TOrganizationService;
  @Inject('alertService') private alertService: () => AlertService;

  public tOrganization: ITOrganization = new TOrganization();

  @Inject('tOrgContactPersonService') private tOrgContactPersonService: () => TOrgContactPersonService;

  public tOrgContactPeople: ITOrgContactPerson[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tOrganizationId) {
        vm.retrieveTOrganization(to.params.tOrganizationId);
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
    if (this.tOrganization.id) {
      this.tOrganizationService()
        .update(this.tOrganization)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tOrganization.updated', { param: param.id });
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
      this.tOrganizationService()
        .create(this.tOrganization)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tOrganization.created', { param: param.id });
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
      this.tOrganization[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tOrganization[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.tOrganization[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tOrganization[field] = null;
    }
  }

  public retrieveTOrganization(tOrganizationId): void {
    this.tOrganizationService()
      .find(tOrganizationId)
      .then(res => {
        res.confirmedDate = new Date(res.confirmedDate);
        res.enteredDate = new Date(res.enteredDate);
        res.modifiedDate = new Date(res.modifiedDate);
        this.tOrganization = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.tOrgContactPersonService()
      .retrieve()
      .then(res => {
        this.tOrgContactPeople = res.data;
      });
  }
}
