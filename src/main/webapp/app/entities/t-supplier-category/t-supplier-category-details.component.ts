import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITSupplierCategory } from '@/shared/model/t-supplier-category.model';
import TSupplierCategoryService from './t-supplier-category.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TSupplierCategoryDetails extends Vue {
  @Inject('tSupplierCategoryService') private tSupplierCategoryService: () => TSupplierCategoryService;
  @Inject('alertService') private alertService: () => AlertService;

  public tSupplierCategory: ITSupplierCategory = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tSupplierCategoryId) {
        vm.retrieveTSupplierCategory(to.params.tSupplierCategoryId);
      }
    });
  }

  public retrieveTSupplierCategory(tSupplierCategoryId) {
    this.tSupplierCategoryService()
      .find(tSupplierCategoryId)
      .then(res => {
        this.tSupplierCategory = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
