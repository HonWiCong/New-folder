import { ITDivisionCode } from '@/shared/model/t-division-code.model';

export interface ITCityCode {
  id?: number;
  cityCode?: string | null;
  cityName?: string | null;
  enteredBy?: number | null;
  enteredDate?: Date | null;
  modifyBy?: number | null;
  modifiedDate?: Date | null;
  tDivisionCode?: ITDivisionCode | null;
}

export class TCityCode implements ITCityCode {
  constructor(
    public id?: number,
    public cityCode?: string | null,
    public cityName?: string | null,
    public enteredBy?: number | null,
    public enteredDate?: Date | null,
    public modifyBy?: number | null,
    public modifiedDate?: Date | null,
    public tDivisionCode?: ITDivisionCode | null
  ) {}
}
