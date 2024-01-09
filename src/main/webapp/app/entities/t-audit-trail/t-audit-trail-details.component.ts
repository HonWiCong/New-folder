import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITAuditTrail } from '@/shared/model/t-audit-trail.model';
import TAuditTrailService from './t-audit-trail.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TAuditTrailDetails extends Vue {
  @Inject('tAuditTrailService') private tAuditTrailService: () => TAuditTrailService;
  @Inject('alertService') private alertService: () => AlertService;

  public tAuditTrail: ITAuditTrail = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tAuditTrailId) {
        vm.retrieveTAuditTrail(to.params.tAuditTrailId);
      }
    });
  }

  public retrieveTAuditTrail(tAuditTrailId) {
    this.tAuditTrailService()
      .find(tAuditTrailId)
      .then(res => {
        this.tAuditTrail = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
