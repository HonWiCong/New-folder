import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITCityCode } from '@/shared/model/t-city-code.model';
import TCityCodeService from './t-city-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TCityCodeDetails extends Vue {
  @Inject('tCityCodeService') private tCityCodeService: () => TCityCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  public tCityCode: ITCityCode = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tCityCodeId) {
        vm.retrieveTCityCode(to.params.tCityCodeId);
      }
    });
  }

  public retrieveTCityCode(tCityCodeId) {
    this.tCityCodeService()
      .find(tCityCodeId)
      .then(res => {
        this.tCityCode = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
