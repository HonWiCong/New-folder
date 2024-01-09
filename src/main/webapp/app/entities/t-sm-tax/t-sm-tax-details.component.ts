import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITSmTax } from '@/shared/model/t-sm-tax.model';
import TSmTaxService from './t-sm-tax.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TSmTaxDetails extends Vue {
  @Inject('tSmTaxService') private tSmTaxService: () => TSmTaxService;
  @Inject('alertService') private alertService: () => AlertService;

  public tSmTax: ITSmTax = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tSmTaxId) {
        vm.retrieveTSmTax(to.params.tSmTaxId);
      }
    });
  }

  public retrieveTSmTax(tSmTaxId) {
    this.tSmTaxService()
      .find(tSmTaxId)
      .then(res => {
        this.tSmTax = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
