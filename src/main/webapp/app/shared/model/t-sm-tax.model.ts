import { TaxStatus } from '@/shared/model/enumerations/tax-status.model';
export interface ITSmTax {
  id?: number;
  smTaxCode?: string | null;
  smTaxDescription?: string | null;
  smTaxPercentage?: number | null;
  smTaxType?: string | null;
  smTaxGstCode?: string | null;
  smTaxGstType?: string | null;
  smTaxCollectedGlCode?: string | null;
  smTaxCollectedGlDesc?: string | null;
  smTaxCollectedCostCenter?: string | null;
  smTaxPaidGlCode?: string | null;
  smTaxPaidGlDesc?: string | null;
  smTaxPaidCostCenter?: string | null;
  smTaxTaxAuthority?: string | null;
  smTaxStatus?: TaxStatus | null;
  smTaxEnteredBy?: number | null;
  smTaxEnteredDate?: Date | null;
  smTaxModifiedBy?: number | null;
  smTaxModifiedDate?: Date | null;
  smTaxConfirmedBy?: number | null;
  smTaxConfirmedDate?: Date | null;
  smTaxNarration?: string | null;
  smTaxDisplay?: string | null;
  smTaxRcmFlag?: string | null;
  smTaxCga?: string | null;
}

export class TSmTax implements ITSmTax {
  constructor(
    public id?: number,
    public smTaxCode?: string | null,
    public smTaxDescription?: string | null,
    public smTaxPercentage?: number | null,
    public smTaxType?: string | null,
    public smTaxGstCode?: string | null,
    public smTaxGstType?: string | null,
    public smTaxCollectedGlCode?: string | null,
    public smTaxCollectedGlDesc?: string | null,
    public smTaxCollectedCostCenter?: string | null,
    public smTaxPaidGlCode?: string | null,
    public smTaxPaidGlDesc?: string | null,
    public smTaxPaidCostCenter?: string | null,
    public smTaxTaxAuthority?: string | null,
    public smTaxStatus?: TaxStatus | null,
    public smTaxEnteredBy?: number | null,
    public smTaxEnteredDate?: Date | null,
    public smTaxModifiedBy?: number | null,
    public smTaxModifiedDate?: Date | null,
    public smTaxConfirmedBy?: number | null,
    public smTaxConfirmedDate?: Date | null,
    public smTaxNarration?: string | null,
    public smTaxDisplay?: string | null,
    public smTaxRcmFlag?: string | null,
    public smTaxCga?: string | null
  ) {}
}
