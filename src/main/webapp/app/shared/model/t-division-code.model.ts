import { ITCityCode } from '@/shared/model/t-city-code.model';
import { ITDistrictCode } from '@/shared/model/t-district-code.model';

export interface ITDivisionCode {
  id?: number;
  divName?: string | null;
  enteredBy?: number | null;
  enteredDate?: Date | null;
  modifyBy?: number | null;
  modifiedDate?: Date | null;
  tCityCodes?: ITCityCode[] | null;
  tDistrictCodes?: ITDistrictCode[] | null;
}

export class TDivisionCode implements ITDivisionCode {
  constructor(
    public id?: number,
    public divName?: string | null,
    public enteredBy?: number | null,
    public enteredDate?: Date | null,
    public modifyBy?: number | null,
    public modifiedDate?: Date | null,
    public tCityCodes?: ITCityCode[] | null,
    public tDistrictCodes?: ITDistrictCode[] | null
  ) {}
}
