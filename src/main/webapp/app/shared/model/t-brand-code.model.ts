export interface ITBrandCode {
  id?: number;
  brandName?: string | null;
  enteredBy?: number | null;
  enteredDate?: Date | null;
  modifiedBy?: number | null;
  modifiedDate?: Date | null;
}

export class TBrandCode implements ITBrandCode {
  constructor(
    public id?: number,
    public brandName?: string | null,
    public enteredBy?: number | null,
    public enteredDate?: Date | null,
    public modifiedBy?: number | null,
    public modifiedDate?: Date | null
  ) {}
}
