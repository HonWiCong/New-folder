import { ITDistrictCode } from '@/shared/model/t-district-code.model';

export interface ITDivisionCode {
  id?: number;
  divName?: string | null;
  enteredBy?: number | null;
  enteredDate?: Date | null;
  modifiedBy?: number | null;
  modifiedDate?: Date | null;
  tDistrictCodes?: ITDistrictCode[] | null;
}

export class TDivisionCode implements ITDivisionCode {
  constructor(
    public id?: number,
    public divName?: string | null,
    public enteredBy?: number | null,
    public enteredDate?: Date | null,
    public modifiedBy?: number | null,
    public modifiedDate?: Date | null,
    public tDistrictCodes?: ITDistrictCode[] | null
  ) {}
}
