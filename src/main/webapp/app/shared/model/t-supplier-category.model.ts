export interface ITSupplierCategory {
  id?: number;
  spcCategory?: string | null;
  enteredBy?: number | null;
  enteredDate?: Date | null;
  modifiedBy?: number | null;
  modifiedDate?: Date | null;
}

export class TSupplierCategory implements ITSupplierCategory {
  constructor(
    public id?: number,
    public spcCategory?: string | null,
    public enteredBy?: number | null,
    public enteredDate?: Date | null,
    public modifiedBy?: number | null,
    public modifiedDate?: Date | null
  ) {}
}
