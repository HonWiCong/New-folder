<template>
	<span v-if="currentOrder === fieldName && reverse">
		<ArrowDown :size="20" />
	</span>
	<span v-else-if="currentOrder === fieldName && !reverse">
		<ArrowUp :size="20" />
	</span>
	<span v-else>
		<ArrowUpDown :size="20" />
	</span>
</template>

<script lang="ts" src="./jhi-sort-indicator.component.ts"></script>
