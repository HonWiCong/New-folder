export interface ITIndustryCode {
  id?: number;
  industryName?: string | null;
  enteredBy?: number | null;
  enteredDate?: Date | null;
  modifyBy?: number | null;
  modifiedDate?: Date | null;
}

export class TIndustryCode implements ITIndustryCode {
  constructor(
    public id?: number,
    public industryName?: string | null,
    public enteredBy?: number | null,
    public enteredDate?: Date | null,
    public modifyBy?: number | null,
    public modifiedDate?: Date | null
  ) {}
}
