import { Component, Vue, Inject } from 'vue-property-decorator';

import { maxLength } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import TUnitCodeService from '@/entities/t-unit-code/t-unit-code.service';
import { ITUnitCode } from '@/shared/model/t-unit-code.model';

import { ITSubunitCode, TSubunitCode } from '@/shared/model/t-subunit-code.model';
import TSubunitCodeService from './t-subunit-code.service';

const validations: any = {
	tSubunitCode: {
		subuntSubunit: {
			maxLength: maxLength(100),
		},
		subuntHeadid: {},
		subuntActingHeadid: {},
		subuntHrpayId: {},
		subuntPapId: {
			maxLength: maxLength(10),
		},
		subuntPapCode: {
			maxLength: maxLength(10),
		},
		subuntPapActive: {
			maxLength: maxLength(1),
		},
		subunyUnitid: {
			maxLength: maxLength(10),
		},
	},
};

@Component({
	validations,
})
export default class TSubunitCodeUpdate extends Vue {
	@Inject('tSubunitCodeService') private tSubunitCodeService: () => TSubunitCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	public tSubunitCode: ITSubunitCode = new TSubunitCode();

	@Inject('tUnitCodeService') private tUnitCodeService: () => TUnitCodeService;

	public tUnitCodes: ITUnitCode[] = [];
	public isSaving = false;
	public currentLanguage = '';

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tSubunitCodeId) {
				vm.retrieveTSubunitCode(to.params.tSubunitCodeId);
			}
			vm.initRelationships();
		});
	}

	created(): void {
		this.currentLanguage = this.$store.getters.currentLanguage;
		this.$store.watch(
			() => this.$store.getters.currentLanguage,
			() => {
				this.currentLanguage = this.$store.getters.currentLanguage;
			}
		);
	}

	public save(): void {
		this.isSaving = true;
		if (this.tSubunitCode.id) {
			this.tSubunitCodeService()
				.update(this.tSubunitCode)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.tSubunitCode.updated', { param: param.id });
					return (this.$root as any).$bvToast.toast(message.toString(), {
						toaster: 'b-toaster-top-center',
						title: 'Info',
						variant: 'info',
						solid: true,
						autoHideDelay: 5000,
					});
				})
				.catch(error => {
					this.isSaving = false;
					this.alertService().showHttpError(this, error.response);
				});
		} else {
			this.tSubunitCodeService()
				.create(this.tSubunitCode)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.tSubunitCode.created', { param: param.id });
					(this.$root as any).$bvToast.toast(message.toString(), {
						toaster: 'b-toaster-top-center',
						title: 'Success',
						variant: 'success',
						solid: true,
						autoHideDelay: 5000,
					});
				})
				.catch(error => {
					this.isSaving = false;
					this.alertService().showHttpError(this, error.response);
				});
		}
	}

	public retrieveTSubunitCode(tSubunitCodeId): void {
		this.tSubunitCodeService()
			.find(tSubunitCodeId)
			.then(res => {
				this.tSubunitCode = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState(): void {
		this.$router.go(-1);
	}

	public initRelationships(): void {
		this.tUnitCodeService()
			.retrieve()
			.then(res => {
				this.tUnitCodes = res.data;
			});
	}
}
