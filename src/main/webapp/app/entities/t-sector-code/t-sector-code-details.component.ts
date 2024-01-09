import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITSectorCode } from '@/shared/model/t-sector-code.model';
import TSectorCodeService from './t-sector-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TSectorCodeDetails extends Vue {
  @Inject('tSectorCodeService') private tSectorCodeService: () => TSectorCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  public tSectorCode: ITSectorCode = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tSectorCodeId) {
        vm.retrieveTSectorCode(to.params.tSectorCodeId);
      }
    });
  }

  public retrieveTSectorCode(tSectorCodeId) {
    this.tSectorCodeService()
      .find(tSectorCodeId)
      .then(res => {
        this.tSectorCode = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
