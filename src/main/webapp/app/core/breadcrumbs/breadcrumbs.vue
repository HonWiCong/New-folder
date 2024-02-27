<!-- eslint-disable prettier/prettier -->
<template>
	<div class="layout">
		<router-link to="/" class="link">
			<Home class="icon" />
		</router-link>
		<span v-for="(path, index) in paths" :key="index" class="links">
			<router-link :to="combinePath(index)" v-text="path" class="link" :class="{ 'link-active': index === paths.length - 1 }" />
			<span v-if="index < paths.length - 1">
				<ChevronRight class="icon" />
			</span>
		</span>
	</div>
</template>
<!-- eslint-disable prettier/prettier -->
<script lang="ts">
import { Home, ChevronRight } from 'lucide-vue';

export default {
	name: 'Breadcrumb',
	components: {
		Home,
		ChevronRight,
	},
	data() {
		return {
			paths: [],
			currentPath: '',
		};
	},
	watch: {
		'$route.path'(newPath) {
			this.paths = newPath.split('/');
		},
	},
	methods: {
		combinePath(index: number) {
			const cumulativePath = this.paths.slice(0, index + 1).join('/');
			return `${cumulativePath}`;
		},
	},
};
</script>
<!-- eslint-disable prettier/prettier -->
<style scoped>
.layout {
	display: flex;
	align-items: center;
	gap: 0.5rem;
}
.icon {
	width: 20px;
}
.links {
	display: flex;
	align-items: center;
	gap: 0.5rem;
	font-size: 0.875rem;
}
.link {
	text-transform: capitalize;
	text-decoration: none;
	color: black;
}
.link:hover {
	text-decoration: underline;
	text-underline-offset: 4px;
}
.link-active {
	color: var(--primary);
	text-decoration: underline;
	text-underline-offset: 4px;
}
</style>
