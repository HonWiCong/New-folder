<!-- eslint-disable prettier/prettier -->
<template>
	<div>
		<h3>Entities page</h3>
		<input type="text" v-model="search" placeholder="Search" />
		<select name="" id="" v-model="type">
			<option value="">All</option>
			<option value="code">Code</option>
		</select>
		<div v-for="link in filteredLinks" :key="link.name">
			<router-link :to="{ name: link.name }">{{ link.label }}</router-link>
		</div>
	</div>
</template>
<!-- eslint-disable prettier/prettier -->
<script lang="ts">
type Entity = {
	label: string;
	path?: string; // Removed path since it's not used
	type?: string;
	name: string;
};

export default {
	name: 'Entities',
	data() {
		return {
			type: '',
			search: '',
			links: [
				{ label: 'Country Code', name: 'LocationCountry', type: 'code' },
				{ label: 'State Code', name: 'LocationState', type: 'code' },
				{ label: 'Division Code', name: 'LocationDivision', type: 'code' },
				{ label: 'District Code', name: 'TDistrictCode', type: 'code' },
				{ label: 'City Code', name: 'LocationCity', type: 'code' },
				{ label: 'Industry Code', name: 'TIndustryCode', type: 'code' },
				{ label: 'Sector Code', name: 'TSectorCode', type: 'code' },
				{ label: 'Brand Code', name: 'TBrandCode', type: 'code' },
				{ label: 'Audit Trail', name: 'TAuditTrail' },
				{ label: 'Supplier Category', name: 'TSupplierCategory' },
				{ label: 'Sm Tax', name: 'TSmTax' },
				{ label: 'Org Contact Person', name: 'TOrgContactPerson' },
				{ label: 'Title Code', name: 'TTitleCode', type: 'code' },
				{ label: 'Organization', name: 'TOrganization' },
				{ label: 'Section Code', name: 'TSectionCode', type: 'code' },
				{ label: 'User Role Code', name: 'TUserRoleCode', type: 'code' },
				{ label: 'Unit Code', name: 'TUnitCode', type: 'code' },
				{ label: 'Subunit Code', name: 'TSubunitCode', type: 'code' },
				{ label: 'Office Code', name: 'TOfficeCode', type: 'code' },
				{ label: 'Application User', name: 'ApplicationUser' },
				{ label: 'User Role', name: 'TUserRole' },
			] as Entity[],
			filteredLinks: [] as Entity[],
		};
	},
	mounted() {
		this.filteredLinks = [...this.links]; // Initialize filteredLinks with all links
	},
	watch: {
		search() {
			this.filterLinks();
		},
		type() {
			this.filterLinks();
		},
	},
	methods: {
		filterLinks() {
			this.filteredLinks = this.links.filter(link => {
				const labelMatches = link.label.toLowerCase().includes(this.search.toLowerCase());
				const typeMatches = !this.type || link.type === this.type;
				return labelMatches && typeMatches;
			});
		},
	},
};
</script>
