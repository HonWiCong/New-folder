import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITOrgContactPerson } from '@/shared/model/t-org-contact-person.model';
import TOrgContactPersonService from './t-org-contact-person.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TOrgContactPersonDetails extends Vue {
  @Inject('tOrgContactPersonService') private tOrgContactPersonService: () => TOrgContactPersonService;
  @Inject('alertService') private alertService: () => AlertService;

  public tOrgContactPerson: ITOrgContactPerson = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.tOrgContactPersonId) {
        vm.retrieveTOrgContactPerson(to.params.tOrgContactPersonId);
      }
    });
  }

  public retrieveTOrgContactPerson(tOrgContactPersonId) {
    this.tOrgContactPersonService()
      .find(tOrgContactPersonId)
      .then(res => {
        this.tOrgContactPerson = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
