import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import TDistrictCodeService from '@/entities/t-district-code/t-district-code.service';
import { ITDistrictCode } from '@/shared/model/t-district-code.model';

import { ITDivisionCode, TDivisionCode } from '@/shared/model/t-division-code.model';
import TDivisionCodeService from './t-division-code.service';

const validations: any = {
	tDivisionCode: {
		divName: {},
		enteredBy: {},
		enteredDate: {},
		modifiedBy: {},
		modifiedDate: {},
	},
};

@Component({
	validations,
})
export default class TDivisionCodeUpdate extends Vue {
	@Inject('tDivisionCodeService') private tDivisionCodeService: () => TDivisionCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	public tDivisionCode: ITDivisionCode = new TDivisionCode();

	@Inject('tDistrictCodeService') private tDistrictCodeService: () => TDistrictCodeService;

	public tDistrictCodes: ITDistrictCode[] = [];
	public isSaving = false;
	public currentLanguage = '';

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tDivisionCodeId) {
				vm.retrieveTDivisionCode(to.params.tDivisionCodeId);
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
		if (this.tDivisionCode.id) {
			this.tDivisionCodeService()
				.update(this.tDivisionCode)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.tDivisionCode.updated', { param: param.id });
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
			this.tDivisionCodeService()
				.create(this.tDivisionCode)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.tDivisionCode.created', { param: param.id });
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
			this.tDivisionCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
		} else {
			this.tDivisionCode[field] = null;
		}
	}

	public updateZonedDateTimeField(field, event) {
		if (event.target.value) {
			this.tDivisionCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
		} else {
			this.tDivisionCode[field] = null;
		}
	}

	public retrieveTDivisionCode(tDivisionCodeId): void {
		this.tDivisionCodeService()
			.find(tDivisionCodeId)
			.then(res => {
				res.enteredDate = new Date(res.enteredDate);
				res.modifiedDate = new Date(res.modifiedDate);
				this.tDivisionCode = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState(): void {
		this.$router.go(-1);
	}

	public initRelationships(): void {
		this.tDistrictCodeService()
			.retrieve()
			.then(res => {
				this.tDistrictCodes = res.data;
			});
	}
}
