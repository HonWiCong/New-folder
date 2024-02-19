import { ITUnitCode } from '@/shared/model/t-unit-code.model';

export interface ITSubunitCode {
	id?: number;
	subuntSubunit?: string | null;
	subuntHeadid?: number | null;
	subuntActingHeadid?: number | null;
	subuntHrpayId?: string | null;
	subuntPapId?: string | null;
	subuntPapCode?: string | null;
	subuntPapActive?: string | null;
	subunyUnitid?: string | null;
	unit?: ITUnitCode | null;
}

export class TSubunitCode implements ITSubunitCode {
	constructor(
		public id?: number,
		public subuntSubunit?: string | null,
		public subuntHeadid?: number | null,
		public subuntActingHeadid?: number | null,
		public subuntHrpayId?: string | null,
		public subuntPapId?: string | null,
		public subuntPapCode?: string | null,
		public subuntPapActive?: string | null,
		public subunyUnitid?: string | null,
		public unit?: ITUnitCode | null
	) {}
}
