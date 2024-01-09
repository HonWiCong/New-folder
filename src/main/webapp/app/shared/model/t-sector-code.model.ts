export interface ITSectorCode {
  id?: number;
  sectorName?: string | null;
  sectorDescription?: string | null;
  enteredBy?: number | null;
  enteredDate?: Date | null;
  modifiedBy?: number | null;
  modifiedDate?: Date | null;
}

export class TSectorCode implements ITSectorCode {
  constructor(
    public id?: number,
    public sectorName?: string | null,
    public sectorDescription?: string | null,
    public enteredBy?: number | null,
    public enteredDate?: Date | null,
    public modifiedBy?: number | null,
    public modifiedDate?: Date | null
  ) {}
}
