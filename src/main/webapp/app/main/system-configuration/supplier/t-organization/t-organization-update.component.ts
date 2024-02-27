import { required } from 'vuelidate/lib/validators';
import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import TOrgContactPersonService from '@/entities/t-org-contact-person/t-org-contact-person.service';
import { ITOrgContactPerson, TOrgContactPerson } from '@/shared/model/t-org-contact-person.model';

import { ITOrganization, TOrganization } from '@/shared/model/t-organization.model';
import TOrganizationService from './t-organization.service';

import TCountryCodeService from '../t-country-code/t-country-code.service';
import { ITCountryCode } from '@/shared/model/t-country-code.model';

import TStateCodeService from '../t-state-code/t-state-code.service';
import { ITStateCode } from '@/shared/model/t-state-code.model';

import TCityCodeService from '../t-city-code/t-city-code.service';
import { ITCityCode } from '@/shared/model/t-city-code.model';

import TDistrictCodeService from '../t-district-code/t-district-code.service';
import { ITDistrictCode } from '@/shared/model/t-district-code.model';

import TDivisionCodeService from '../t-division-code/t-division-code.service';
import { ITDivisionCode } from '@/shared/model/t-division-code.model';

import TTitleCodeService from '../t-title-code/t-title-code.service';
import { ITTitleCode } from '@/shared/model/t-title-code.model';

import TSmTaxService from '../t-sm-tax/t-sm-tax.service';
import { ITSmTax } from '@/shared/model/t-sm-tax.model';

import TSectorCodeService from '../t-sector-code/t-sector-code.service';
import { ITSectorCode } from '@/shared/model/t-sector-code.model';

import { ITIndustryCode } from '@/shared/model/t-industry-code.model';
import { X } from 'lucide-vue';

const validations: any = {
	tOrganization: {
		orgHqid: {},
		orgHqBr: {},
		orgCode: {
			required,
		},
		orgBrn: {},
		orgPtaxid: {},
		orgDefaultTaxCode: {},
		orgCompanyGstNo: {},
		orgCompanyGstRegDate: {},
		orgCompanyGstDeregDate: {},
		orgPoTaxInclusive: {
			required,
		},
		orgName: {
			required,
		},
		orgNameOther: {
			required,
		},
		orgShortname: {
			required,
		},
		orgAddress: {
			required,
		},
		orgShippingAddress: {},
		orgBillingAddress: {},
		orgPostcode: {},
		orgCity: {
			required,
		},
		orgState: {
			required,
		},
		orgCountry: {
			required,
		},
		orgOffPhone1: {},
		orgOffPhone2: {},
		orgOffPhone3: {},
		orgOffFax1: {},
		orgOffFax2: {},
		orgCredittermid: {},
		orgCreditLimit: {},
		orgAgencyid: {},
		orgDivision: {
			required,
		},
		orgDistrict: {
			required,
		},
		orgWebsite: {},
		orgEmail: {},
		orgSupplierCategory: {},
		orgCurrencyCode: {},
		orgType: {},
		orgSectorid: {},
		orgSector: {},
		orgIndustry: {
			required,
		},
		orgSainsGroup: {},
		orgBumiputra: {},
		orgUpkReg: {},
		orgMofReg: {},
		orgDesignation: {},
		orgContpersonTitle: {},
		orgContperson: {},
		orgDirectline: {},
		orgContpEmail: {},
		orgContpHp: {},
		orgRemarks: {},
		orgActiveStatus: {},
		orgCcGc: {},
		orgCustomerCateCode: {},
		orgVendorCateCode: {},
		orgSalesCateCode: {},
		orgOutstandingBalance: {},
		orgOutstandingBalanceEx: {},
		orgCompanyCode: {},
		orgDcrownPostStatus: {},
		confirmedBy: {},
		confirmedDate: {},
		enteredBy: {},
		enteredDate: {},
		modifiedBy: {},
		modifiedDate: {},
		contactPersons: {
			$each: {
				ocpOrgcodeid: {},
				ocpTitle: {},
				ocpName: {
					required,
				},
				ocpDesignation: {
					required,
				},
				ocpTelephone1: {},
				ocpHandphone: {},
				ocpMail: {},
				ocpSector: {},
				ocpStatus: {},
				enteredBy: {},
				enteredDate: {},
				modifiedBy: {},
				modifiedDate: {},
			},
		},
	},
};

@Component({
	validations,
	components: {
		X,
	},
})
export default class TOrganizationUpdate extends Vue {
	@Inject('tOrganizationService') private tOrganizationService: () => TOrganizationService;

	@Inject('alertService') private alertService: () => AlertService;
	public tOrganization: ITOrganization = new TOrganization();

	@Inject('tOrgContactPersonService') private tOrgContactPersonService: () => TOrgContactPersonService;
	public tOrgContactPeople: ITOrgContactPerson[] = [];

	@Inject('tCountryCodeService') private tCountryCodeService: () => TCountryCodeService;
	public tCountryCodes: ITCountryCode[] = [];

	@Inject('tStateCodeService') private tStateCodeService: () => TStateCodeService;
	public tStateCodes: ITStateCode[] = [];

	@Inject('tCityCodeService') private tCityCodeService: () => TCityCodeService;
	public tCityCodes: ITCityCode[] = [];

	@Inject('tDivisionCodeService') private tDivisionCodeService: () => TDivisionCodeService;
	public tDivisionCodes: ITDivisionCode[] = [];

	@Inject('tDistrictCodeService') private tDistrictCodeService: () => TDistrictCodeService;
	public tDistrictCodes: ITDistrictCode[] = [];

	@Inject('tTitleCodeService') private tTitleCodeService: () => TTitleCodeService;
	public tTitleCodes: ITTitleCode[] = [];

	@Inject('tSmTaxService') private tSmTaxService: () => TSmTaxService;
	public tSmTaxCodes: ITSmTax[] = [];

	@Inject('tSectorCodeService') private tSectorCodeService: () => TSectorCodeService;
	public tSectorCodes: ITSectorCode[] = [];

	@Inject('tIndustryCodeService') private tIndustryCodeService: () => TIndustryCodeService;
	public tIndustryCodes: ITIndustryCode[] = [];

	public isSaving = false;
	public currentLanguage = '';
	public stateList = [];
	public cityList = [];
	public districtList = [];

	public selectedCountry = '';
	public selectedState = '';
	public selectedDivision = '';

	public deletedId: number[] = [];

	beforeRouteEnter(to, from, next) {
		next(vm => {
			if (to.params.tOrganizationId) {
				vm.retrieveTOrganization(to.params.tOrganizationId);
			} else {
				// prevent undefined error
				vm.tOrganization.contactPersons = [new TOrgContactPerson()];
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
		if (this.tOrganization.id) {
			this.tOrganization.deletedId = this.deletedId;
			console.log(this.tOrganization);
			this.tOrganizationService()
				.update(this.tOrganization)
				.then(param => {
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.tOrganization.updated', { param: param.id });
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
			this.tOrganizationService()
				.create(this.tOrganization)
				.then(param => {
					console.log(param);
					this.isSaving = false;
					this.$router.go(-1);
					const message = this.$t('sainsApp.tOrganization.created', { param: param.id });
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
			this.tOrganization[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
		} else {
			this.tOrganization[field] = null;
		}
	}

	public updateZonedDateTimeField(field, event) {
		if (event.target.value) {
			this.tOrganization[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
		} else {
			this.tOrganization[field] = null;
		}
	}

	public retrieveTOrganization(tOrganizationId): void {
		this.tOrganizationService()
			.find(tOrganizationId)
			.then(res => {
				console.log(res);
				res.confirmedDate = new Date(res.confirmedDate);
				res.enteredDate = new Date(res.enteredDate);
				res.modifiedDate = new Date(res.modifiedDate);
				this.tOrganization = res;
			})
			.catch(error => {
				this.alertService().showHttpError(this, error.response);
			});
	}

	public previousState(): void {
		this.$router.go(-1);
	}

	public initRelationships(): void {
		this.tCountryCodeService()
			.retrieve()
			.then(res => {
				this.tCountryCodes = res.data;
			});
		this.tStateCodeService()
			.retrieve()
			.then(res => {
				this.tStateCodes = res.data;
				this.stateList = res.data;
			});
		this.tCityCodeService()
			.retrieve()
			.then(res => {
				this.tCityCodes = res.data;
				this.cityList = res.data;
			});
		this.tDivisionCodeService()
			.retrieve()
			.then(res => {
				this.tDivisionCodes = res.data;
			});
		this.tDistrictCodeService()
			.retrieve()
			.then(res => {
				this.tDistrictCodes = res.data;
				this.districtList = res.data;
			});
		this.tTitleCodeService()
			.retrieve()
			.then(res => {
				this.tTitleCodes = res.data;
			});
		this.tSmTaxService()
			.retrieve()
			.then(res => {
				this.tSmTaxCodes = res.data;
			});
		this.tSectorCodeService()
			.retrieve()
			.then(res => {
				this.tSectorCodes = res.data;
			});
		this.tIndustryCodeService()
			.retrieve()
			.then(res => {
				this.tIndustryCodes = res.data;
			});
	}

	public addContactPerson(): void {
		this.tOrganization.contactPersons.push(new TOrgContactPerson());
		console.log(this.tOrganization.contactPersons);
	}

	public removeContactPerson(index: number): void {
		if (this.tOrganization.contactPersons !== undefined && this.tOrganization.contactPersons.length > 1) {
			this.deletedId.push(this.tOrganization.contactPersons[index].id);
			this.tOrganization.contactPersons.splice(index, 1);
		}
	}

	public checkInvalid() {
		this.tOrganization.deletedId = this.deletedId;
		console.log(this.$v.tOrganization);
	}
}
