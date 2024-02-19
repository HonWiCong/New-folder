import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import { ITOfficeCode, TOfficeCode } from '@/shared/model/t-office-code.model';
import TOfficeCodeService from './t-office-code.service';

const validations: any = {
	tOfficeCode: {
		offName: {},
		offAddress: {},
		offPostcode: {},
		offCity: {},
		offState: {},
		offOffphone: {},
		offOfffax: {},
		enteredBy: {},
		enteredDate: {},
		modifiedBy: {},
		modifiedDate: {},
	},
};

@Component({
	validations,
})
export default class TOfficeCodeUpdate extends Vue {
	@Inject('tOfficeCodeService') private tOfficeCodeService: () => TOfficeCodeService;
	@Inject('alertService') private alertService: () => AlertService;

	public tOfficeCode: ITOfficeCode = new TOfficeCode();
	public isSaving = false;
	public currentLanguage = '';

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tOfficeCodeId) {
				vm.retrieveTOfficeCode(to.params.tOfficeCodeId);
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
		if (this.tOfficeCode.id) {
			this.tOfficeCodeService()
				.update(this.tOfficeCode)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.tOfficeCode.updated', { param: param.id });
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
			this.tOfficeCodeService()
				.create(this.tOfficeCode)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.tOfficeCode.created', { param: param.id });
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
			this.tOfficeCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
		} else {
			this.tOfficeCode[field] = null;
		}
	}

	public updateZonedDateTimeField(field, event) {
		if (event.target.value) {
			this.tOfficeCode[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
		} else {
			this.tOfficeCode[field] = null;
		}
	}

	public retrieveTOfficeCode(tOfficeCodeId): void {
		this.tOfficeCodeService()
			.find(tOfficeCodeId)
			.then(res => {
				res.enteredDate = new Date(res.enteredDate);
				res.modifiedDate = new Date(res.modifiedDate);
				this.tOfficeCode = res;
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
