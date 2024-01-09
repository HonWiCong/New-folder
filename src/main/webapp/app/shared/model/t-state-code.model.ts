import { ITCountryCode } from '@/shared/model/t-country-code.model';

export interface ITStateCode {
  id?: number;
  stateName?: string | null;
  stateCode?: string | null;
  enteredBy?: number | null;
  enteredDate?: Date | null;
  modifyBy?: number | null;
  modifiedDate?: Date | null;
  tCountryCode?: ITCountryCode | null;
}

export class TStateCode implements ITStateCode {
  constructor(
    public id?: number,
    public stateName?: string | null,
    public stateCode?: string | null,
    public enteredBy?: number | null,
    public enteredDate?: Date | null,
    public modifyBy?: number | null,
    public modifiedDate?: Date | null,
    public tCountryCode?: ITCountryCode | null
  ) {}
}
