export interface ITAuditTrail {
  id?: number;
  userId?: number | null;
  dateTime?: Date | null;
  tableName?: string | null;
  auditAction?: string | null;
  recordId?: number | null;
  fieldDesc?: string | null;
  atStatus?: string | null;
  stFullDesc?: string | null;
}

export class TAuditTrail implements ITAuditTrail {
  constructor(
    public id?: number,
    public userId?: number | null,
    public dateTime?: Date | null,
    public tableName?: string | null,
    public auditAction?: string | null,
    public recordId?: number | null,
    public fieldDesc?: string | null,
    public atStatus?: string | null,
    public stFullDesc?: string | null
  ) {}
}
