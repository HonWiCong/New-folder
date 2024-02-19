import { IUser } from '@/shared/model/user.model';
import { ITUserRole } from '@/shared/model/t-user-role.model';
import { ITSectionCode } from '@/shared/model/t-section-code.model';
import { ITUnitCode } from '@/shared/model/t-unit-code.model';
import { ITSubunitCode } from '@/shared/model/t-subunit-code.model';
import { ITOfficeCode } from '@/shared/model/t-office-code.model';

export interface IApplicationUser {
	id?: number;
	ic?: string | null;
	status?: string | null;
	internalUser?: IUser | null;
	userRoles?: ITUserRole[] | null;
	division?: ITSectionCode | null;
	section?: ITUnitCode | null;
	unit?: ITSubunitCode | null;
	office?: ITOfficeCode | null;
	newUserRoles?: ITUserRole[] | null;
}

export class ApplicationUser implements IApplicationUser {
	constructor(
		public id?: number,
		public ic?: string | null,
		public status?: string | null,
		public internalUser?: IUser | null,
		public userRoles?: ITUserRole[] | null,
		public division?: ITSectionCode | null,
		public section?: ITUnitCode | null,
		public unit?: ITSubunitCode | null,
		public office?: ITOfficeCode | null,
		public newUserRoles?: ITUserRole[] | null
	) {}
}
