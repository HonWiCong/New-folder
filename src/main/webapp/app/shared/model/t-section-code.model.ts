import { SectSainsSubsidiary } from '@/shared/model/enumerations/sect-sains-subsidiary.model';
import { SectPapActive } from '@/shared/model/enumerations/sect-pap-active.model';
export interface ITSectionCode {
	id?: number;
	sectName?: string | null;
	sectHeadid?: number | null;
	sectActingHeadid?: number | null;
	sectSainsSubsidiary?: SectSainsSubsidiary | null;
	sectHrpayId?: string | null;
	sectPapId?: string | null;
	sectPapCode?: string | null;
	sectPapCompany?: string | null;
	sectPapActive?: SectPapActive | null;
	sectCcCode?: string | null;
	enteredBy?: number | null;
	enteredDate?: Date | null;
	modifiedBy?: number | null;
	modifiedDate?: Date | null;
}

export class TSectionCode implements ITSectionCode {
	constructor(
		public id?: number,
		public sectName?: string | null,
		public sectHeadid?: number | null,
		public sectActingHeadid?: number | null,
		public sectSainsSubsidiary?: SectSainsSubsidiary | null,
		public sectHrpayId?: string | null,
		public sectPapId?: string | null,
		public sectPapCode?: string | null,
		public sectPapCompany?: string | null,
		public sectPapActive?: SectPapActive | null,
		public sectCcCode?: string | null,
		public enteredBy?: number | null,
		public enteredDate?: Date | null,
		public modifiedBy?: number | null,
		public modifiedDate?: Date | null
	) {}
}
