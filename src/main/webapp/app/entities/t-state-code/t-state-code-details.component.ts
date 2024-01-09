import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITStateCode } from '@/shared/model/t-state-code.model';
import TStateCodeService from './t-state-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TStateCodeDetails extends Vue {
  @Inject('tStateCodeService') private tStateCodeService: () => TStateCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  public tStateCode: ITStateCode = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tStateCodeId) {
        vm.retrieveTStateCode(to.params.tStateCodeId);
      }
    });
  }

  public retrieveTStateCode(tStateCodeId) {
    this.tStateCodeService()
      .find(tStateCodeId)
      .then(res => {
        this.tStateCode = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
