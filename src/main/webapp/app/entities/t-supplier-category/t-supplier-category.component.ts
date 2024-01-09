import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITSupplierCategory } from '@/shared/model/t-supplier-category.model';

import TSupplierCategoryService from './t-supplier-category.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class TSupplierCategory extends Vue {
  @Inject('tSupplierCategoryService') private tSupplierCategoryService: () => TSupplierCategoryService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public tSupplierCategories: ITSupplierCategory[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTSupplierCategorys();
  }

  public clear(): void {
    this.retrieveAllTSupplierCategorys();
  }

  public retrieveAllTSupplierCategorys(): void {
    this.isFetching = true;
    this.tSupplierCategoryService()
      .retrieve()
      .then(
        res => {
          this.tSupplierCategories = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: ITSupplierCategory): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTSupplierCategory(): void {
    this.tSupplierCategoryService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('sainsApp.tSupplierCategory.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllTSupplierCategorys();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
