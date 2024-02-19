import { Component, Vue, Inject } from 'vue-property-decorator';

import { maxLength } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import TSubunitCodeService from '@/entities/t-subunit-code/t-subunit-code.service';
import { ITSubunitCode } from '@/shared/model/t-subunit-code.model';

import { ITUnitCode, TUnitCode } from '@/shared/model/t-unit-code.model';
import TUnitCodeService from './t-unit-code.service';

const validations: any = {
	tUnitCode: {
		untUnit: {
			maxLength: maxLength(100),
		},
		untHeadid: {},
		untActingHeadid: {},
		untHrpayId: {},
		untPapId: {
			maxLength: maxLength(10),
		},
		untPapCode: {
			maxLength: maxLength(10),
		},
		untPapActive: {
			maxLength: maxLength(1),
		},
		untPapDepartmentid: {},
		enteredBy: {},
		enteredDate: {},
		modifiedBy: {},
		modifiedDate: {},
	},
};

@Component({
	validations,
})
export default class TUnitCodeUpdate extends Vue {
	@Inject('tUnitCodeService') private tUnitCodeService: () => TUnitCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	public tUnitCode: ITUnitCode = new TUnitCode();

	@Inject('tSubunitCodeService') private tSubunitCodeService: () => TSubunitCodeService;

	public tSubunitCodes: ITSubunitCode[] = [];
	public isSaving = false;
	public currentLanguage = '';

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tUnitCodeId) {
				vm.retrieveTUnitCode(to.params.tUnitCodeId);
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
		if (this.tUnitCode.id) {
			this.tUnitCodeService()
				.update(this.tUnitCode)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.tUnitCode.updated', { param: param.id });
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
			this.tUnitCodeService()
				.create(this.tUnitCode)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.tUnitCode.created', { param: param.id });
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

	public convertDateTimeFromServer(date: Date): string {
		if (date && dayjs(date).isValid()) {
			return dayjs(date).format(DATE_TIME_LONG_FORMAT);
		}
		return null;
	}

	public updateInstantField(field, event) {
		if (event.target.value) {
			this.tUnitCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
		} else {
			this.tUnitCode[field] = null;
		}
	}

	public updateZonedDateTimeField(field, event) {
		if (event.target.value) {
			this.tUnitCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
		} else {
			this.tUnitCode[field] = null;
		}
	}

	public retrieveTUnitCode(tUnitCodeId): void {
		this.tUnitCodeService()
			.find(tUnitCodeId)
			.then(res => {
				res.enteredDate = new Date(res.enteredDate);
				res.modifiedDate = new Date(res.modifiedDate);
				this.tUnitCode = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState(): void {
		this.$router.go(-1);
	}

	public initRelationships(): void {
		this.tSubunitCodeService()
			.retrieve()
			.then(res => {
				this.tSubunitCodes = res.data;
			});
	}
}
