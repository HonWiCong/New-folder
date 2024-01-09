import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITIndustryCode } from '@/shared/model/t-industry-code.model';
import TIndustryCodeService from './t-industry-code.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TIndustryCodeDetails extends Vue {
  @Inject('tIndustryCodeService') private tIndustryCodeService: () => TIndustryCodeService;
  @Inject('alertService') private alertService: () => AlertService;

  public tIndustryCode: ITIndustryCode = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tIndustryCodeId) {
        vm.retrieveTIndustryCode(to.params.tIndustryCodeId);
      }
    });
  }

  public retrieveTIndustryCode(tIndustryCodeId) {
    this.tIndustryCodeService()
      .find(tIndustryCodeId)
      .then(res => {
        this.tIndustryCode = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
