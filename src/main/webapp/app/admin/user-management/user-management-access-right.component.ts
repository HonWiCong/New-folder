import Vue from 'vue';
import { Component, Inject } from 'vue-property-decorator';

type AccessRightItem = {
	label: string;
	add: boolean;
	read: boolean;
	update: boolean;
};

@Component
export default class JhiUserManagementEditAccessRight extends Vue {
	public columns = [
		{ key: 'all', label: '', variant: 'secondary', class: 'all-checkbox-column' },
		{ key: 'label', label: '', class: 'label-column' },
		{ key: 'checkbox-section', label: '', class: 'checkbox-section-column' },
	];

	public travel_request_items: AccessRightItem[] = [
		{ label: 'Create TRS', add: false, read: false, update: false },
		{ label: 'Search TRS', add: false, read: false, update: false },
		{ label: 'My TRS', add: false, read: false, update: false },
		{ label: 'Travel Request Extension or Change', add: false, read: false, update: false },
		{ label: 'Finance', add: false, read: false, update: false },
	];

	public requisition_items: AccessRightItem[] = [
		{ label: 'Create Requisition', add: false, read: false, update: false },
		{ label: 'My Requisition', add: false, read: false, update: false },
		{ label: 'Search Requisition', add: false, read: false, update: false },
		{ label: 'Create Work Order', add: false, read: false, update: false },
		{ label: 'My Work Order', add: false, read: false, update: false },
		{ label: 'Search Work Order', add: false, read: false, update: false },
		{ label: 'Create Loan Extension', add: false, read: false, update: false },
		{ label: 'Search Loan Extension', add: false, read: false, update: false },
	];

	public procurement_items: AccessRightItem[] = [
		{ label: 'Procurement Process', add: false, read: false, update: false },
		{ label: 'Create Quotation Request', add: false, read: false, update: false },
		{ label: 'Search Quotation Request', add: false, read: false, update: false },
		{ label: 'Create Purchase Order', add: false, read: false, update: false },
		{ label: 'Search Purchase Order', add: false, read: false, update: false },
		{ label: 'Create Service Order', add: false, read: false, update: false },
		{ label: 'Search Service Order', add: false, read: false, update: false },
		{ label: 'Petty Cash', add: false, read: false, update: false },
		{ label: 'Finance', add: false, read: false, update: false },
		{ label: 'Report', add: false, read: false, update: false },
	];

	public delivery_receiving_items: AccessRightItem[] = [
		{ label: 'Receiving', add: false, read: false, update: false },
		{ label: 'Create Delivery Order / Store Delivery Note', add: false, read: false, update: false },
		{ label: 'Manual Delivery Order', add: false, read: false, update: false },
		{ label: 'Create Store Collection Note', add: false, read: false, update: false },
		{ label: 'Goods Return & Replacement', add: false, read: false, update: false },
		{ label: 'Search', add: false, read: false, update: false },
		{ label: 'Report', add: false, read: false, update: false },
	];

	public inventory_items: AccessRightItem[] = [
		{ label: 'Opening Balance', add: false, read: false, update: false },
		{ label: 'Adjustment', add: false, read: false, update: false },
		{ label: 'Year End Adjustment', add: false, read: false, update: false },
		{ label: 'Inventory', add: false, read: false, update: false },
		{ label: 'Product Catalog', add: false, read: false, update: false },
		{ label: 'Service Catalog', add: false, read: false, update: false },
		{ label: 'Report', add: false, read: false, update: false },
		{ label: 'Stock Take', add: false, read: false, update: false },
		{ label: 'Ad Hoc Stock Transfer', add: false, read: false, update: false },
	];

	public asset_items: AccessRightItem[] = [
		{ label: 'Create Asset', add: false, read: false, update: false },
		{ label: 'Registration / Assignment', add: false, read: false, update: false },
		{ label: 'Asset Movement Application', add: false, read: false, update: false },
		{ label: 'My Asset', add: false, read: false, update: false },
		{ label: 'Search', add: false, read: false, update: false },
		{ label: 'Create BOS', add: false, read: false, update: false },
		{ label: 'Generate BOS Report', add: false, read: false, update: false },
		{ label: 'Report', add: false, read: false, update: false },
	];

	public fas_posting_items: AccessRightItem[] = [
		{ label: 'GL Mapping', add: false, read: false, update: false },
		{ label: 'GL Summary', add: false, read: false, update: false },
		{ label: 'GL Daily Posting', add: false, read: false, update: false },
	];

	public system_configuration_items: AccessRightItem[] = [
		{ label: 'Process Flow', add: false, read: false, update: false },
		{ label: 'User Management', add: false, read: false, update: false },
		{ label: 'Customer Profile', add: false, read: false, update: false },
		{ label: 'Supplier Profile', add: false, read: false, update: false },
		{ label: 'SAINS Profile', add: false, read: false, update: false },
		{ label: 'Audit Trail', add: false, read: false, update: false },
		{ label: 'Reference Codes', add: false, read: false, update: false },
	];

	public items = {
		travel_request: this.travel_request_items,
		requisition: this.requisition_items,
		procurement: this.procurement_items,
		delivery_receiving: this.delivery_receiving_items,
		inventory: this.inventory_items,
		asset: this.asset_items,
		fas_posting: this.fas_posting_items,
		system_configuration: this.system_configuration_items,
	};

	// public onCheckChange(data) {
	// 	// Find the corresponding item within the items object
	// 	for (const key in this.items) {
	// 		if (Object.prototype.hasOwnProperty.call(this.items, key)) {
	// 			const itemList = this.items[key];
	// 			const foundItem = itemList.find(element => element.label === data.item.label);
	// 			if (foundItem) {
	// 				// Modify the add, update, and read boolean values
	// 				console.log(data);
	// 				foundItem.add = data.item.add;
	// 				foundItem.update = data.item.update;
	// 				foundItem.read = data.item.read;
	// 				break;
	// 			}
	// 		}
	// 	}
	// }

	public onCheckChange(data, attribute: string, type: string) {
		const targetItem = this.items[attribute].find(element => element.label === data.item.label);

		if (targetItem) {
			if (type === 'add') {
				targetItem.add = !data.item.add;
			}
			if (type === 'update') {
				targetItem.update = !data.item.update;
			}
			if (type === 'read') {
				targetItem.read = !data.item.read;
			}
			console.log(targetItem);
		}
	}

	public onCheckAllChange(data, attribute: string) {
		const targetItem = this.items[attribute].find(element => element.label === data.item.label);
		if (targetItem) {
			targetItem.add = !data.item.add;
			targetItem.update = !data.item.update;
			targetItem.read = !data.item.read;
		}
	}

	public check() {
		console.log(this.items);
	}
}
