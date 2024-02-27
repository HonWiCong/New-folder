import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITOrganization } from '@/shared/model/t-organization.model';
import TOrganizationService from './t-organization.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TOrganizationDetails extends Vue {
	@Inject('tOrganizationService') private tOrganizationService: () => TOrganizationService;
	@Inject('alertService') private alertService: () => AlertService;

	public tOrganization: ITOrganization = {};

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tOrganizationId) {
				vm.retrieveTOrganization(to.params.tOrganizationId);
			}
		});
	}

	public retrieveTOrganization(tOrganizationId) {
		this.tOrganizationService()
			.find(tOrganizationId)
			.then(res => {
				this.tOrganization = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState() {
		this.$router.go(-1);
	}
}
