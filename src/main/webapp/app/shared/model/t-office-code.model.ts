export interface ITOfficeCode {
	id?: number;
	offName?: string | null;
	offAddress?: string | null;
	offPostcode?: string | null;
	offCity?: string | null;
	offState?: string | null;
	offOffphone?: string | null;
	offOfffax?: string | null;
	enteredBy?: number | null;
	enteredDate?: Date | null;
	modifiedBy?: number | null;
	modifiedDate?: Date | null;
}

export class TOfficeCode implements ITOfficeCode {
	constructor(
		public id?: number,
		public offName?: string | null,
		public offAddress?: string | null,
		public offPostcode?: string | null,
		public offCity?: string | null,
		public offState?: string | null,
		public offOffphone?: string | null,
		public offOfffax?: string | null,
		public enteredBy?: number | null,
		public enteredDate?: Date | null,
		public modifiedBy?: number | null,
		public modifiedDate?: Date | null
	) {}
}
