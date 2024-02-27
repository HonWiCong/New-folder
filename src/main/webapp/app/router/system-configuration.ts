import { Authority } from '@/shared/security/authority';

const TOrganization = () => import('@/main/system-configuration/supplier/t-organization/t-organization.vue');
const TOrganizationUpdate = () => import('@/main/system-configuration/supplier/t-organization/t-organization-update.vue');
const TOrganizationDetails = () => import('@/main/system-configuration/supplier/t-organization/t-organization-details.vue');

const TOrgContactPerson = () => import('@/main/system-configuration/supplier/t-org-contact-person/t-org-contact-person.vue');
const TOrgContactPersonUpdate = () => import('@/main/system-configuration/supplier/t-org-contact-person/t-org-contact-person-update.vue');
const TOrgContactPersonDetails = () => import('@/main/system-configuration/supplier/t-org-contact-person/t-org-contact-person-details.vue');

const UserManagement = () => import('@/main/system-configuration/user-management/user-management.vue');
const UserManagementUpdate = () => import('@/main/system-configuration/user-management/user-management-edit.vue');
const UserManagementView = () => import('@/main/system-configuration/user-management/user-management-view.vue');
const UserManagementAccessRight = () => import('@/main/system-configuration/user-management/user-management-access-right.vue');
const UserManagementUserDetail = () => import('@/main/system-configuration/user-management/user-management-user-detail.vue');

const Supplier = () => import('@/main/system-configuration/supplier/supplier.vue');
const SystemConfiguration = () => import('@/main/system-configuration/system-configuration.vue');

export default [
	{
		path: '/system-configuration',
		name: 'SystemConfiguration',
		component: SystemConfiguration,
		meta: { authorities: [Authority.ADMIN] },
		children: [
			{
				path: 'supplier',
				name: 'Supplier',
				component: Supplier,
				children: [
					{
						path: 't-organization',
						name: 'SupplierOrganization',
						component: TOrganization,
					},
					{
						path: 't-organization/:tOrganizationId/view',
						name: 'SupplierOrganizationView',
						component: TOrganizationDetails,
					},
					{
						path: 't-organization/new',
						name: 'SupplierOrganizationNew',
						component: TOrganizationUpdate,
					},
					{
						path: 't-organization/:tOrganizationId/edit',
						name: 'SupplierOrganizationEdit',
						component: TOrganizationUpdate,
					},
					{
						path: 't-org-contact-person',
						name: 'SupplierOrgContactPerson',
						component: TOrgContactPerson,
					},
					{
						path: 't-org-contact-person/:tOrgContactPersonId/view',
						name: 'SupplierOrgContactPersonView',
						component: TOrgContactPersonDetails,
					},
					{
						path: 't-org-contact-person/new',
						name: 'SupplierOrgContactPersonNew',
						component: TOrgContactPersonUpdate,
					},
					{
						path: 't-org-contact-person/:tOrgContactPersonId/edit',
						name: 'SupplierOrgContactPersonEdit',
						component: TOrgContactPersonUpdate,
					},
				],
			},
			{
				path: 'user-management',
				name: 'UserManagement',
				component: UserManagement,
			},
			{
				path: 'user-management/new',
				name: 'UserManagementCreate',
				component: UserManagementUpdate,
				children: [
					{
						path: 'user-detail',
						name: 'UserManagementCreateDetail',
						component: UserManagementUserDetail,
					},
					{
						path: 'access-right',
						name: 'UserManagementCreateAccessRight',
						component: UserManagementAccessRight,
					},
				],
			},
			{
				path: 'user-management/:userId/edit',
				name: 'UserManagementEdit',
				component: UserManagementUpdate,
				children: [
					{
						path: 'user-detail',
						name: 'UserManagementEditDetail',
						component: UserManagementUserDetail,
					},
					{
						path: 'access-right',
						name: 'UserManagementEditAccessRight',
						component: UserManagementAccessRight,
					},
				],
			},
			{
				path: 'user-management/:userId/view',
				name: 'UserManagementView',
				component: UserManagementView,
			},
		],
	},
];
