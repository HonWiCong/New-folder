<!-- eslint-disable prettier/prettier -->
<template>
	<div>
		<h1>Entities page</h1>
		<div v-for="link in links" :key="link.path">
			<router-link :to="link.path">{{ link.name }}</router-link>
		</div>
	</div>
</template>
<!-- eslint-disable prettier/prettier -->
<script lang="ts">
type Entity = {
	name: string;
	path: string;
	type?: string;
};

export default {
	name: 'Entities',
	data() {
		return {
			links: [
				{ name: 'Country Code', path: '/t-country-code', type: 'code' },
				{ name: 'State Code', path: '/t-state-code', type: 'code' },
				{ name: 'Division Code', path: '/t-division-code', type: 'code' },
				{ name: 'District Code', path: '/t-district-code', type: 'code' },
				{ name: 'City Code', path: '/t-city-code', type: 'code' },
				{ name: 'Industry Code', path: '/t-industry-code', type: 'code' },
				{ name: 'Sector Code', path: '/t-sector-code', type: 'code' },
				{ name: 'Brand Code', path: '/t-brand-code', type: 'code' },
				{ name: 'Audit Trail', path: '/t-audit-trail' },
				{ name: 'Supplier Category', path: '/t-supplier-category' },
				{ name: 'Sm Tax', path: '/t-sm-tax' },
				{ name: 'Org Contact Person', path: '/t-org-contact-person' },
				{ name: 'Title Code', path: '/t-title-code', type: 'code' },
				{ name: 'Organization', path: '/t-organization' },
				{ name: 'Section Code', path: '/t-section-code', type: 'code' },
				{ name: 'User Role Code', path: '/t-user-role-code', type: 'code' },
				{ name: 'Unit Code', path: '/t-unit-code', type: 'code' },
				{ name: 'Subunit Code', path: '/t-subunit-code', type: 'code' },
				{ name: 'Office Code', path: '/t-office-code', type: 'code' },
				{ name: 'Application User', path: '/application-user' },
				{ name: 'User Role', path: '/t-user-role' },
			] as Entity[],
		};
	},
};
</script>
