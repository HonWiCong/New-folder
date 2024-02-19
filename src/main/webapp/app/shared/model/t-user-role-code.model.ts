import { ITUserRole } from '@/shared/model/t-user-role.model';

export interface ITUserRoleCode {
	id?: number;
	roleName?: string | null;
	roleHead?: string | null;
	actApprover1?: number | null;
	actApprover2?: number | null;
	enteredBy?: number | null;
	enteredDate?: Date | null;
	modifiedBy?: number | null;
	modifiedDate?: Date | null;
	userRoles?: ITUserRole[] | null;
}

export class TUserRoleCode implements ITUserRoleCode {
	constructor(
		public id?: number,
		public roleName?: string | null,
		public roleHead?: string | null,
		public actApprover1?: number | null,
		public actApprover2?: number | null,
		public enteredBy?: number | null,
		public enteredDate?: Date | null,
		public modifiedBy?: number | null,
		public modifiedDate?: Date | null,
		public userRoles?: ITUserRole[] | null
	) {}
}
