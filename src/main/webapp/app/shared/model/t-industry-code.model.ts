export interface ITIndustryCode {
  id?: number;
  industryName?: string | null;
  enteredBy?: number | null;
  enteredDate?: Date | null;
  modifiedBy?: number | null;
  modifiedDate?: Date | null;
}

export class TIndustryCode implements ITIndustryCode {
  constructor(
    public id?: number,
    public industryName?: string | null,
    public enteredBy?: number | null,
    public enteredDate?: Date | null,
    public modifiedBy?: number | null,
    public modifiedDate?: Date | null
  ) {}
}
