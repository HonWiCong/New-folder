<!-- eslint-disable prettier/prettier -->
<template>
	<form class="custom-container">
		<div class="table-container">
			<section class="mt-3">
				<div class="table-title bg-primary">Travel Request System</div>
				<b-table hover outlined :items="items.travel_request" :fields="columns" thead-tr-class="d-none">
					<template #cell(all)="data">
						<input type="checkbox" @change="onCheckAllChange(data, 'travel_request')" />
					</template>

					<template #cell(checkbox-section)="data">
						<div class="checkbox-section">
							<div>
								<input
									type="checkbox"
									:id="'Travel Request System add:' + data.index"
									@change="onCheckChange(data, 'travel_request', 'add')"
									v-model="data.item.add"
								/>
								<label :for="'Travel Request System add:' + data.index" class="checkbox-label">Add</label>
							</div>
							<div>
								<input
									type="checkbox"
									:id="'Travel Request System update:' + data.index"
									@change="onCheckChange(data, 'travel_request', 'update')"
									v-model="data.item.update"
								/>
								<label :for="'Travel Request System update:' + data.index" class="checkbox-label">Update</label>
							</div>
							<div>
								<input
									type="checkbox"
									:id="'Travel Request System read:' + data.index"
									@change="onCheckChange(data, 'travel_request', 'read')"
									v-model="data.item.read"
								/>
								<label :for="'Travel Request System read:' + data.index" class="checkbox-label">Read</label>
							</div>
						</div>
					</template>
				</b-table>
			</section>
			<section>
				<div class="table-title bg-primary">Requisition</div>
				<b-table hover outlined :items="items.requisition" :fields="columns" thead-tr-class="d-none" class="table">
					<template #cell(all)="data">
						<input type="checkbox" @change="onCheckAllChange(data, 'requisition')" />
					</template>

					<template #cell(checkbox-section)="data">
						<div class="checkbox-section">
							<div>
								<input
									type="checkbox"
									:id="'Requisition add:' + data.index"
									@change="onCheckChange(data, 'requisition', 'add')"
									v-model="data.item.add"
								/>
								<label :for="'Requisition add:' + data.index" class="checkbox-label">Add</label>
							</div>
							<div>
								<input
									type="checkbox"
									:id="'Requisition update:' + data.index"
									@change="onCheckChange(data, 'requisition', 'update')"
									v-model="data.item.update"
								/>
								<label :for="'Requisition update:' + data.index" class="checkbox-label">Update</label>
							</div>
							<div>
								<input
									type="checkbox"
									:id="'Requisition read:' + data.index"
									@change="onCheckChange(data, 'requisition', 'read')"
									v-model="data.item.read"
								/>
								<label :for="'Requisition read:' + data.index" class="checkbox-label">Read</label>
							</div>
						</div>
					</template>
				</b-table>
			</section>
			<section>
				<div class="table-title bg-primary">Procurement</div>
				<b-table hover outlined :items="items.procurement" :fields="columns" thead-tr-class="d-none" class="table">
					<template #cell(all)="data">
						<input type="checkbox" @change="onCheckAllChange(data, 'procurement')" />
					</template>

					<template #cell(checkbox-section)="data">
						<div class="checkbox-section">
							<div>
								<input
									type="checkbox"
									:id="'Procurement add:' + data.index"
									@change="onCheckChange(data, 'procurement', 'add')"
									v-model="data.item.add"
								/>
								<label :for="'Procurement add:' + data.index" class="checkbox-label">Add</label>
							</div>
							<div>
								<input
									type="checkbox"
									:id="'Procurement update:' + data.index"
									@change="onCheckChange(data, 'procurement', 'update')"
									v-model="data.item.update"
								/>
								<label :for="'Procurement update:' + data.index" class="checkbox-label">Update</label>
							</div>
							<div>
								<input
									type="checkbox"
									:id="'Procurement read:' + data.index"
									@change="onCheckChange(data, 'procurement', 'read')"
									v-model="data.item.read"
								/>
								<label :for="'Procurement read:' + data.index" class="checkbox-label">Read</label>
							</div>
						</div>
					</template>
				</b-table>
			</section>
			<section>
				<div class="table-title bg-primary">Delivery & Receiving</div>
				<b-table hover outlined :items="items.delivery_receiving" :fields="columns" thead-tr-class="d-none" class="table">
					<template #cell(all)="data">
						<input type="checkbox" @change="onCheckAllChange(data, 'delivery_receiving')" />
					</template>

					<template #cell(checkbox-section)="data">
						<div class="checkbox-section">
							<div>
								<input
									type="checkbox"
									:id="'Delivery & Receiving add:' + data.index"
									@change="onCheckChange(data, 'delivery_receiving', 'add')"
									v-model="data.item.add"
								/>
								<label :for="'Delivery & Receiving add:' + data.index" class="checkbox-label">Add</label>
							</div>
							<div>
								<input
									type="checkbox"
									:id="'Delivery & Receiving update:' + data.index"
									@change="onCheckChange(data, 'delivery_receiving', 'update')"
									v-model="data.item.update"
								/>
								<label :for="'Delivery & Receiving update:' + data.index" class="checkbox-label">Update</label>
							</div>
							<div>
								<input
									type="checkbox"
									:id="'Delivery & Receiving read:' + data.index"
									@change="onCheckChange(data, 'delivery_receiving', 'read')"
									v-model="data.item.read"
								/>
								<label :for="'Delivery & Receiving read:' + data.index" class="checkbox-label">Read</label>
							</div>
						</div>
					</template>
				</b-table>
			</section>
			<section>
				<div class="table-title bg-primary">Inventory</div>
				<b-table hover outlined :items="items.inventory" :fields="columns" thead-tr-class="d-none" class="table">
					<template #cell(all)="data">
						<input type="checkbox" @change="onCheckAllChange(data, 'inventory')" />
					</template>

					<template #cell(checkbox-section)="data">
						<div class="checkbox-section">
							<div>
								<input
									type="checkbox"
									:id="'Inventory add:' + data.index"
									@change="onCheckChange(data, 'inventory', 'add')"
									v-model="data.item.add"
								/>
								<label :for="'Inventory add:' + data.index" class="checkbox-label">Add</label>
							</div>
							<div>
								<input
									type="checkbox"
									:id="'Inventory update:' + data.index"
									@change="onCheckChange(data, 'inventory', 'update')"
									v-model="data.item.update"
								/>
								<label :for="'Inventory update:' + data.index" class="checkbox-label">Update</label>
							</div>
							<div>
								<input
									type="checkbox"
									:id="'Inventory read:' + data.index"
									@change="onCheckChange(data, 'inventory', 'read')"
									v-model="data.item.read"
								/>
								<label :for="'Inventory read:' + data.index" class="checkbox-label">Read</label>
							</div>
						</div>
					</template>
				</b-table>
			</section>
			<section>
				<div class="table-title bg-primary">Asset</div>
				<b-table hover outlined :items="items.asset" :fields="columns" thead-tr-class="d-none" class="table">
					<template #cell(all)="data">
						<input type="checkbox" @change="onCheckAllChange(data, 'asset')" />
					</template>

					<template #cell(checkbox-section)="data">
						<div class="checkbox-section">
							<div>
								<input
									type="checkbox"
									:id="'Asset add:' + data.index"
									@change="onCheckChange(data, 'asset', 'add')"
									v-model="data.item.add"
								/>
								<label :for="'Asset add:' + data.index" class="checkbox-label">Add</label>
							</div>
							<div>
								<input
									type="checkbox"
									:id="'Asset update:' + data.index"
									@change="onCheckChange(data, 'asset', 'update')"
									v-model="data.item.update"
								/>
								<label :for="'Asset update:' + data.index" class="checkbox-label">Update</label>
							</div>
							<div>
								<input
									type="checkbox"
									:id="'Asset read:' + data.index"
									@change="onCheckChange(data, 'asset', 'read')"
									v-model="data.item.read"
								/>
								<label :for="'Asset read:' + data.index" class="checkbox-label">Read</label>
							</div>
						</div>
					</template>
				</b-table>
			</section>
			<section>
				<div class="table-title bg-primary">FAS Posting</div>
				<b-table hover outlined :items="items.fas_posting" :fields="columns" thead-tr-class="d-none" class="table">
					<template #cell(all)="data">
						<input type="checkbox" @change="onCheckAllChange(data, 'fas_posting')" />
					</template>

					<template #cell(checkbox-section)="data">
						<div class="checkbox-section">
							<div>
								<input
									type="checkbox"
									:id="'FAS Posting add:' + data.index"
									@change="onCheckChange(data, 'fas_posting', 'add')"
									v-model="data.item.add"
								/>
								<label :for="'FAS Posting add:' + data.index" class="checkbox-label">Add</label>
							</div>
							<div>
								<input
									type="checkbox"
									:id="'FAS Posting update:' + data.index"
									@change="onCheckChange(data, 'fas_posting', 'update')"
									v-model="data.item.update"
								/>
								<label :for="'FAS Posting update:' + data.index" class="checkbox-label">Update</label>
							</div>
							<div>
								<input
									type="checkbox"
									:id="'FAS Posting read:' + data.index"
									@change="onCheckChange(data, 'fas_posting', 'read')"
									v-model="data.item.read"
								/>
								<label :for="'FAS Posting read:' + data.index" class="checkbox-label">Read</label>
							</div>
						</div>
					</template>
				</b-table>
			</section>
			<section>
				<div class="table-title bg-primary">System Configuration</div>
				<b-table hover outlined :items="items.system_configuration" :fields="columns" thead-tr-class="d-none" class="table">
					<template #cell(all)="data">
						<input type="checkbox" @change="onCheckAllChange(data, 'system_configuration')" />
					</template>

					<template #cell(label)="data">
						<div class="test">
							{{ data.value }}
						</div>
					</template>

					<template #cell(checkbox-section)="data">
						<div class="checkbox-section">
							<div>
								<input
									type="checkbox"
									:id="'System Configuration add:' + data.index"
									@change="onCheckChange(data, 'system_configuration', 'add')"
									v-model="data.item.add"
								/>
								<label :for="'System Configuration add:' + data.index" class="checkbox-label">Add</label>
							</div>
							<div>
								<input
									type="checkbox"
									:id="'System Configuration update:' + data.index"
									@change="onCheckChange(data, 'system_configuration', 'update')"
									v-model="data.item.update"
								/>
								<label :for="'System Configuration update:' + data.index" class="checkbox-label">Update</label>
							</div>
							<div>
								<input
									type="checkbox"
									:id="'System Configuration read:' + data.index"
									@change="onCheckChange(data, 'system_configuration', 'read')"
									v-model="data.item.read"
								/>
								<label :for="'System Configuration read:' + data.index" class="checkbox-label">Read</label>
							</div>
						</div>
					</template>
				</b-table>
			</section>
		</div>
		<button type="submit">Save</button>
		<button type="reset">Reset</button>
		<button type="button" @click="check">Check</button>
	</form>
</template>
<!-- eslint-disable prettier/prettier -->

<script lang="ts" src="./user-management-access-right.component.ts"></script>
<!-- eslint-disable prettier/prettier -->

<style scoped>
form {
	padding: 0;
}
.table-container {
	background-color: white;
}
.table {
	margin: 0;
}
.table-title {
	color: white;
	padding: 0.5rem;
}
.checkbox-label {
	user-select: none;
}
.checkbox-section {
	display: flex;
	gap: 2rem;
}
</style>
<!-- eslint-disable prettier/prettier -->

<style>
.all-checkbox-column {
	width: 2rem;
}
.label-column {
	width: 20rem;
}
</style>
