import { IUser } from '@/shared/model/user.model';
import { ITSectionCode } from '@/shared/model/t-section-code.model';
import { ITUnitCode } from '@/shared/model/t-unit-code.model';
import { ITSubunitCode } from '@/shared/model/t-subunit-code.model';
import { ITOfficeCode } from '@/shared/model/t-office-code.model';

export interface IModifiedUser {
	// from User
	userId?: any;
	login?: string;
	firstName?: string;
	lastName?: string;
	email?: string;
	activated?: boolean;
	langKey?: string;
	authorities?: any[];
	createdBy?: string;
	createdDate?: Date;
	lastModifiedBy?: string;
	lastModifiedDate?: Date;
	password?: string;

	// from ApplicationUser
	applicationUserId?: number;
	ic?: string | null;
	status?: string | null;
	internalUser?: IUser | null;
	division?: ITSectionCode | null;
	section?: ITUnitCode | null;
	unit?: ITSubunitCode | null;
	office?: ITOfficeCode | null;
}

export class ModifiedUser implements IModifiedUser {
	constructor(
		public userId?: any,
		public login?: string,
		public firstName?: string,
		public lastName?: string,
		public email?: string,
		public activated?: boolean,
		public langKey?: string,
		public authorities?: any[],
		public createdBy?: string,
		public createdDate?: Date,
		public lastModifiedBy?: string,
		public lastModifiedDate?: Date,
		public password?: string,

		// from ApplicationUser
		public applicationUserId?: number,
		public ic?: string | null,
		public status?: string | null,
		public internalUser?: IUser | null,
		public division?: ITSectionCode | null,
		public section?: ITUnitCode | null,
		public unit?: ITSubunitCode | null,
		public office?: ITOfficeCode | null
	) {}
}
