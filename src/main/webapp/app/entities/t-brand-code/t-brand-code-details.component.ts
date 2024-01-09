import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITBrandCode } from '@/shared/model/t-brand-code.model';
import TBrandCodeService from './t-brand-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TBrandCodeDetails extends Vue {
  @Inject('tBrandCodeService') private tBrandCodeService: () => TBrandCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  public tBrandCode: ITBrandCode = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tBrandCodeId) {
        vm.retrieveTBrandCode(to.params.tBrandCodeId);
      }
    });
  }

  public retrieveTBrandCode(tBrandCodeId) {
    this.tBrandCodeService()
      .find(tBrandCodeId)
      .then(res => {
        this.tBrandCode = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
