import { ITStateCode } from '@/shared/model/t-state-code.model';

export interface ITCountryCode {
  id?: number;
  countryCode?: string | null;
  countryName?: string | null;
  countryNationality?: string | null;
  enteredBy?: number | null;
  enteredDate?: Date | null;
  modifyBy?: number | null;
  modifiedDate?: Date | null;
  orgCustomerType?: string | null;
  tStateCodes?: ITStateCode[] | null;
}

export class TCountryCode implements ITCountryCode {
  constructor(
    public id?: number,
    public countryCode?: string | null,
    public countryName?: string | null,
    public countryNationality?: string | null,
    public enteredBy?: number | null,
    public enteredDate?: Date | null,
    public modifyBy?: number | null,
    public modifiedDate?: Date | null,
    public orgCustomerType?: string | null,
    public tStateCodes?: ITStateCode[] | null
  ) {}
}
