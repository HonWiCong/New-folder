import { ITStateCode } from '@/shared/model/t-state-code.model';

export interface ITCityCode {
  id?: number;
  cityCode?: string | null;
  cityName?: string | null;
  enteredBy?: number | null;
  enteredDate?: Date | null;
  modifiedBy?: number | null;
  modifiedDate?: Date | null;
  tStateCode?: ITStateCode | null;
}

export class TCityCode implements ITCityCode {
  constructor(
    public id?: number,
    public cityCode?: string | null,
    public cityName?: string | null,
    public enteredBy?: number | null,
    public enteredDate?: Date | null,
    public modifiedBy?: number | null,
    public modifiedDate?: Date | null,
    public tStateCode?: ITStateCode | null
  ) {}
}
