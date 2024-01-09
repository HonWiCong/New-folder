export interface ITTitleCode {
  id?: number;
  ttTitle?: string | null;
  enteredBy?: number | null;
  enteredDate?: Date | null;
  modifiedBy?: number | null;
  modifiedDate?: Date | null;
}

export class TTitleCode implements ITTitleCode {
  constructor(
    public id?: number,
    public ttTitle?: string | null,
    public enteredBy?: number | null,
    public enteredDate?: Date | null,
    public modifiedBy?: number | null,
    public modifiedDate?: Date | null
  ) {}
}
