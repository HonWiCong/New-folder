import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import { ITSectionCode, TSectionCode } from '@/shared/model/t-section-code.model';
import TSectionCodeService from './t-section-code.service';
import { SectSainsSubsidiary } from '@/shared/model/enumerations/sect-sains-subsidiary.model';
import { SectPapActive } from '@/shared/model/enumerations/sect-pap-active.model';

const validations: any = {
	tSectionCode: {
		sectName: {},
		sectHeadid: {},
		sectActingHeadid: {},
		sectSainsSubsidiary: {},
		sectHrpayId: {},
		sectPapId: {},
		sectPapCode: {},
		sectPapCompany: {},
		sectPapActive: {},
		sectCcCode: {},
		enteredBy: {},
		enteredDate: {},
		modifiedBy: {},
		modifiedDate: {},
	},
};

@Component({
	validations,
})
export default class TSectionCodeUpdate extends Vue {
	@Inject('tSectionCodeService') private tSectionCodeService: () => TSectionCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	public tSectionCode: ITSectionCode = new TSectionCode();
	public sectSainsSubsidiaryValues: string[] = Object.keys(SectSainsSubsidiary);
	public sectPapActiveValues: string[] = Object.keys(SectPapActive);
	public isSaving = false;
	public currentLanguage = '';

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tSectionCodeId) {
				vm.retrieveTSectionCode(to.params.tSectionCodeId);
			}
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
		if (this.tSectionCode.id) {
			this.tSectionCodeService()
				.update(this.tSectionCode)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.tSectionCode.updated', { param: param.id });
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
			this.tSectionCodeService()
				.create(this.tSectionCode)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.tSectionCode.created', { param: param.id });
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
			this.tSectionCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
		} else {
			this.tSectionCode[field] = null;
		}
	}

	public updateZonedDateTimeField(field, event) {
		if (event.target.value) {
			this.tSectionCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
		} else {
			this.tSectionCode[field] = null;
		}
	}

	public retrieveTSectionCode(tSectionCodeId): void {
		this.tSectionCodeService()
			.find(tSectionCodeId)
			.then(res => {
				res.enteredDate = new Date(res.enteredDate);
				res.modifiedDate = new Date(res.modifiedDate);
				this.tSectionCode = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState(): void {
		this.$router.go(-1);
	}

	public initRelationships(): void {}
}
