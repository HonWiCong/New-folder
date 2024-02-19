import { ITUserRoleCode } from '@/shared/model/t-user-role-code.model';
import { IApplicationUser } from '@/shared/model/application-user.model';

export interface ITUserRole {
	id?: number;
	sysuserId?: number | null;
	usrRoleid?: number | null;
	enteredBy?: number | null;
	enteredDate?: Date | null;
	modifiedBy?: number | null;
	modifiedDate?: Date | null;
	role?: ITUserRoleCode | null;
	user?: IApplicationUser | null;
}

export class TUserRole implements ITUserRole {
	constructor(
		public id?: number,
		public sysuserId?: number | null,
		public usrRoleid?: number | null,
		public enteredBy?: number | null,
		public enteredDate?: Date | null,
		public modifiedBy?: number | null,
		public modifiedDate?: Date | null,
		public role?: ITUserRoleCode | null,
		public user?: IApplicationUser | null
	) {}
}
