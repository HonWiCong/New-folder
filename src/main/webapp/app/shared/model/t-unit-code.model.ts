import { ITSubunitCode } from '@/shared/model/t-subunit-code.model';

export interface ITUnitCode {
	id?: number;
	untUnit?: string | null;
	untHeadid?: number | null;
	untActingHeadid?: number | null;
	untHrpayId?: string | null;
	untPapId?: string | null;
	untPapCode?: string | null;
	untPapActive?: string | null;
	untPapDepartmentid?: string | null;
	enteredBy?: number | null;
	enteredDate?: Date | null;
	modifiedBy?: number | null;
	modifiedDate?: Date | null;
	subunits?: ITSubunitCode[] | null;
}

export class TUnitCode implements ITUnitCode {
	constructor(
		public id?: number,
		public untUnit?: string | null,
		public untHeadid?: number | null,
		public untActingHeadid?: number | null,
		public untHrpayId?: string | null,
		public untPapId?: string | null,
		public untPapCode?: string | null,
		public untPapActive?: string | null,
		public untPapDepartmentid?: string | null,
		public enteredBy?: number | null,
		public enteredDate?: Date | null,
		public modifiedBy?: number | null,
		public modifiedDate?: Date | null,
		public subunits?: ITSubunitCode[] | null
	) {}
}
