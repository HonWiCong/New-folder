import { ITCountryCode } from '@/shared/model/t-country-code.model';
import { ITCityCode } from '@/shared/model/t-city-code.model';

export interface ITStateCode {
	id?: number;
	stateName?: string | null;
	stateCode?: string | null;
	enteredBy?: number | null;
	enteredDate?: Date | null;
	modifiedBy?: number | null;
	modifiedDate?: Date | null;
	tcountryCode?: ITCountryCode | null;
	tcityCodes?: ITCityCode[] | null;
}

export class TStateCode implements ITStateCode {
	constructor(
		public id?: number,
		public stateName?: string | null,
		public stateCode?: string | null,
		public enteredBy?: number | null,
		public enteredDate?: Date | null,
		public modifiedBy?: number | null,
		public modifiedDate?: Date | null,
		public tcountryCode?: ITCountryCode | null,
		public tcityCodes?: ITCityCode[] | null
	) {}
}
