import { ITOrgContactPerson } from '@/shared/model/t-org-contact-person.model';

export interface ITOrganization {
	id?: number;
	orgHqid?: number | null;
	orgHqBr?: string | null;
	orgCode?: string | null;
	orgBrn?: string | null;
	orgPtaxid?: number | null;
	orgDefaultTaxCode?: string | null;
	orgCompanyGstNo?: string | null;
	orgCompanyGstRegDate?: Date | null;
	orgCompanyGstDeregDate?: Date | null;
	orgPoTaxInclusive?: string | null;
	orgName?: string | null;
	orgNameOther?: string | null;
	orgShortname?: string | null;
	orgAddress?: string | null;
	orgShippingAddress?: string | null;
	orgBillingAddress?: string | null;
	orgPostcode?: string | null;
	orgCity?: string | null;
	orgState?: string | null;
	orgCountry?: string | null;
	orgOffPhone1?: string | null;
	orgOffPhone2?: string | null;
	orgOffPhone3?: string | null;
	orgOffFax1?: string | null;
	orgOffFax2?: string | null;
	orgCredittermid?: number | null;
	orgCreditLimit?: number | null;
	orgAgencyid?: number | null;
	orgDivision?: string | null;
	orgDistrict?: string | null;
	orgWebsite?: string | null;
	orgEmail?: string | null;
	orgSupplierCategory?: string | null;
	orgCurrencyCode?: string | null;
	orgType?: string | null;
	orgSectorid?: number | null;
	orgSector?: string | null;
	orgIndustry?: string | null;
	orgSainsGroup?: string | null;
	orgBumiputra?: string | null;
	orgUpkReg?: string | null;
	orgMofReg?: string | null;
	orgDesignation?: string | null;
	orgContpersonTitle?: string | null;
	orgContperson?: string | null;
	orgDirectline?: string | null;
	orgContpEmail?: string | null;
	orgContpHp?: string | null;
	orgRemarks?: string | null;
	orgActiveStatus?: string | null;
	orgCcGc?: string | null;
	orgCustomerCateCode?: string | null;
	orgVendorCateCode?: string | null;
	orgSalesCateCode?: string | null;
	orgOutstandingBalance?: number | null;
	orgOutstandingBalanceEx?: number | null;
	orgCompanyCode?: string | null;
	orgDcrownPostStatus?: string | null;
	confirmedBy?: number | null;
	confirmedDate?: Date | null;
	enteredBy?: number | null;
	enteredDate?: Date | null;
	modifiedBy?: number | null;
	modifiedDate?: Date | null;
	contactPersons?: ITOrgContactPerson[] | null;

	// custom
	deletedId?: number[] | null;
}

export class TOrganization implements ITOrganization {
	constructor(
		public id?: number,
		public orgHqid?: number | null,
		public orgHqBr?: string | null,
		public orgCode?: string | null,
		public orgBrn?: string | null,
		public orgPtaxid?: number | null,
		public orgDefaultTaxCode?: string | null,
		public orgCompanyGstNo?: string | null,
		public orgCompanyGstRegDate?: Date | null,
		public orgCompanyGstDeregDate?: Date | null,
		public orgPoTaxInclusive?: string | null,
		public orgName?: string | null,
		public orgNameOther?: string | null,
		public orgShortname?: string | null,
		public orgAddress?: string | null,
		public orgShippingAddress?: string | null,
		public orgBillingAddress?: string | null,
		public orgPostcode?: string | null,
		public orgCity?: string | null,
		public orgState?: string | null,
		public orgCountry?: string | null,
		public orgOffPhone1?: string | null,
		public orgOffPhone2?: string | null,
		public orgOffPhone3?: string | null,
		public orgOffFax1?: string | null,
		public orgOffFax2?: string | null,
		public orgCredittermid?: number | null,
		public orgCreditLimit?: number | null,
		public orgAgencyid?: number | null,
		public orgDivision?: string | null,
		public orgDistrict?: string | null,
		public orgWebsite?: string | null,
		public orgEmail?: string | null,
		public orgSupplierCategory?: string | null,
		public orgCurrencyCode?: string | null,
		public orgType?: string | null,
		public orgSectorid?: number | null,
		public orgSector?: string | null,
		public orgIndustry?: string | null,
		public orgSainsGroup?: string | null,
		public orgBumiputra?: string | null,
		public orgUpkReg?: string | null,
		public orgMofReg?: string | null,
		public orgDesignation?: string | null,
		public orgContpersonTitle?: string | null,
		public orgContperson?: string | null,
		public orgDirectline?: string | null,
		public orgContpEmail?: string | null,
		public orgContpHp?: string | null,
		public orgRemarks?: string | null,
		public orgActiveStatus?: string | null,
		public orgCcGc?: string | null,
		public orgCustomerCateCode?: string | null,
		public orgVendorCateCode?: string | null,
		public orgSalesCateCode?: string | null,
		public orgOutstandingBalance?: number | null,
		public orgOutstandingBalanceEx?: number | null,
		public orgCompanyCode?: string | null,
		public orgDcrownPostStatus?: string | null,
		public confirmedBy?: number | null,
		public confirmedDate?: Date | null,
		public enteredBy?: number | null,
		public enteredDate?: Date | null,
		public modifiedBy?: number | null,
		public modifiedDate?: Date | null,
		public contactPersons?: ITOrgContactPerson[] | null,

		// custom
		public deletedId?: number[] | null
	) {}
}
