import { ITDivisionCode } from '@/shared/model/t-division-code.model';

export interface ITDistrictCode {
  id?: number;
  disName?: string | null;
  enteredBy?: number | null;
  enteredDate?: Date | null;
  modifyBy?: number | null;
  modifiedDate?: Date | null;
  tDivisionCode?: ITDivisionCode | null;
}

export class TDistrictCode implements ITDistrictCode {
  constructor(
    public id?: number,
    public disName?: string | null,
    public enteredBy?: number | null,
    public enteredDate?: Date | null,
    public modifyBy?: number | null,
    public modifiedDate?: Date | null,
    public tDivisionCode?: ITDivisionCode | null
  ) {}
}
