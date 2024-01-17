import { ITOrganization } from '@/shared/model/t-organization.model';

export interface ITOrgContactPerson {
	id?: number;
	ocpOrgcodeid?: number | null;
	ocpTitle?: string | null;
	ocpName?: string | null;
	ocpDesignation?: string | null;
	ocpTelephone1?: string | null;
	ocpHandphone?: string | null;
	ocpMail?: string | null;
	ocpSector?: string | null;
	ocpStatus?: string | null;
	enteredBy?: number | null;
	enteredDate?: Date | null;
	modifiedBy?: number | null;
	modifiedDate?: Date | null;
	organization?: ITOrganization | null;
}

export class TOrgContactPerson implements ITOrgContactPerson {
	constructor(
		public id?: number,
		public ocpOrgcodeid?: number | null,
		public ocpTitle?: string | null,
		public ocpName?: string | null,
		public ocpDesignation?: string | null,
		public ocpTelephone1?: string | null,
		public ocpHandphone?: string | null,
		public ocpMail?: string | null,
		public ocpSector?: string | null,
		public ocpStatus?: string | null,
		public enteredBy?: number | null,
		public enteredDate?: Date | null,
		public modifiedBy?: number | null,
		public modifiedDate?: Date | null,
		public organization?: ITOrganization | null
	) {}
}
