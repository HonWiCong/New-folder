import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITTitleCode } from '@/shared/model/t-title-code.model';
import TTitleCodeService from './t-title-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TTitleCodeDetails extends Vue {
  @Inject('tTitleCodeService') private tTitleCodeService: () => TTitleCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  public tTitleCode: ITTitleCode = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tTitleCodeId) {
        vm.retrieveTTitleCode(to.params.tTitleCodeId);
      }
    });
  }

  public retrieveTTitleCode(tTitleCodeId) {
    this.tTitleCodeService()
      .find(tTitleCodeId)
      .then(res => {
        this.tTitleCode = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
