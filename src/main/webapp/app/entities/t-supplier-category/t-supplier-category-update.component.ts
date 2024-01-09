import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import { ITSupplierCategory, TSupplierCategory } from '@/shared/model/t-supplier-category.model';
import TSupplierCategoryService from './t-supplier-category.service';

const validations: any = {
  tSupplierCategory: {
    spcCategory: {},
    enteredBy: {},
    enteredDate: {},
    modifiedBy: {},
    modifiedDate: {},
  },
};

@Component({
  validations,
})
export default class TSupplierCategoryUpdate extends Vue {
  @Inject('tSupplierCategoryService') private tSupplierCategoryService: () => TSupplierCategoryService;
  @Inject('alertService') private alertService: () => AlertService;

  public tSupplierCategory: ITSupplierCategory = new TSupplierCategory();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tSupplierCategoryId) {
        vm.retrieveTSupplierCategory(to.params.tSupplierCategoryId);
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
    if (this.tSupplierCategory.id) {
      this.tSupplierCategoryService()
        .update(this.tSupplierCategory)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tSupplierCategory.updated', { param: param.id });
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
      this.tSupplierCategoryService()
        .create(this.tSupplierCategory)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sainsApp.tSupplierCategory.created', { param: param.id });
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
      this.tSupplierCategory[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tSupplierCategory[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.tSupplierCategory[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.tSupplierCategory[field] = null;
    }
  }

  public retrieveTSupplierCategory(tSupplierCategoryId): void {
    this.tSupplierCategoryService()
      .find(tSupplierCategoryId)
      .then(res => {
        res.enteredDate = new Date(res.enteredDate);
        res.modifiedDate = new Date(res.modifiedDate);
        this.tSupplierCategory = res;
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
