import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITDistrictCode } from '@/shared/model/t-district-code.model';
import TDistrictCodeService from './t-district-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TDistrictCodeDetails extends Vue {
  @Inject('tDistrictCodeService') private tDistrictCodeService: () => TDistrictCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  public tDistrictCode: ITDistrictCode = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tDistrictCodeId) {
        vm.retrieveTDistrictCode(to.params.tDistrictCodeId);
      }
    });
  }

  public retrieveTDistrictCode(tDistrictCodeId) {
    this.tDistrictCodeService()
      .find(tDistrictCodeId)
      .then(res => {
        this.tDistrictCode = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
