<template>
	<div>
		<div class="header">
			<h2>
				<span v-text="$t('sainsApp.tStateCode.home.title')">T State Codes</span>
			</h2>
			<div class="d-flex justify-content-end">
				<button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
					<font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
				</button>
				<router-link :to="{ name: 'TStateCodeCreate' }" custom v-slot="{ navigate }">
					<button @click="navigate" class="btn btn-primary jh-create-entity create-t-state-code">
						<font-awesome-icon icon="plus"></font-awesome-icon>
						<span v-text="$t('sainsApp.tStateCode.home.createLabel')"> Create a new T State Code </span>
					</button>
				</router-link>
			</div>
		</div>
		<div class="table-container">
			<div>
				<span>This searchbox can search <b>State</b> and <b>Country</b> name</span>
				<div class="search-box">
					<Search class="search-icon" />
					<input v-model="search" placeholder="Search by name" class="search-input" @change="retrieveAllTStateCodes" />
				</div>
			</div>

			<div class="alert alert-warning" v-if="!isFetching && tStateCodes && tStateCodes.length === 0">
				<span v-text="$t('sainsApp.tStateCode.home.notFound')">No tStateCodes found</span>
			</div>
			<div class="table-responsive" v-if="tStateCodes && tStateCodes.length > 0">
				<table>
					<thead>
						<tr class="table-header">
							<th scope="row" v-on:click="changeOrder('id')">
								<span v-text="$t('global.field.id')">ID</span>
								<jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
							</th>
							<th scope="row" v-on:click="changeOrder('stateName')">
								<span v-text="$t('sainsApp.tStateCode.stateName')">State Name</span>
								<jhi-sort-indicator
									:current-order="propOrder"
									:reverse="reverse"
									:field-name="'stateName'"
								></jhi-sort-indicator>
							</th>
							<th scope="row" v-on:click="changeOrder('stateCode')">
								<span v-text="$t('sainsApp.tStateCode.stateCode')">State Code</span>
								<jhi-sort-indicator
									:current-order="propOrder"
									:reverse="reverse"
									:field-name="'stateCode'"
								></jhi-sort-indicator>
							</th>
							<th scope="row" v-on:click="changeOrder('tCountryCode.id')">
								<span v-text="$t('sainsApp.tStateCode.tCountryCode')">T Country Code</span>
								<jhi-sort-indicator
									:current-order="propOrder"
									:reverse="reverse"
									:field-name="'tCountryCode.id'"
								></jhi-sort-indicator>
							</th>
							<th scope="row"></th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="tStateCode in tStateCodes" :key="tStateCode.id" class="content-row">
							<td>
								<router-link :to="{ name: 'LocationStateView', params: { tStateCodeId: tStateCode.id } }">{{
									tStateCode.id
								}}</router-link>
							</td>
							<td>{{ tStateCode.stateName }}</td>
							<td>{{ tStateCode.stateCode }}</td>
							<td>
								<div v-if="tStateCode.tcountryCode">
									<router-link
										:to="{ name: 'TCountryCodeView', params: { tCountryCodeId: tStateCode.tcountryCode.id } }"
										>{{ tStateCode.tcountryCode.countryName }}</router-link
									>
								</div>
							</td>
							<td class="text-right">
								<div class="btn-group">
									<router-link
										:to="{ name: 'LocationStateView', params: { tStateCodeId: tStateCode.id } }"
										custom
										v-slot="{ navigate }"
									>
										<button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
											<font-awesome-icon icon="eye"></font-awesome-icon>
											<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
										</button>
									</router-link>
									<router-link
										:to="{ name: 'LocationStateEdit', params: { tStateCodeId: tStateCode.id } }"
										custom
										v-slot="{ navigate }"
									>
										<button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
											<font-awesome-icon icon="pencil-alt"></font-awesome-icon>
											<span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
										</button>
									</router-link>
									<b-button
										v-on:click="prepareRemove(tStateCode)"
										variant="danger"
										class="btn btn-sm"
										data-cy="entityDeleteButton"
										v-b-modal.removeEntity
									>
										<font-awesome-icon icon="times"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
									</b-button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<b-modal ref="removeEntity" id="removeEntity">
			<span slot="modal-title"
				><span id="sainsApp.tStateCode.delete.question" data-cy="tStateCodeDeleteDialogHeading" v-text="$t('entity.delete.title')"
					>Confirm delete operation</span
				></span
			>
			<div class="modal-body">
				<p id="jhi-delete-tStateCode-heading" v-text="$t('sainsApp.tStateCode.delete.question', { id: removeId })">
					Are you sure you want to delete this T State Code?
				</p>
			</div>
			<div slot="modal-footer">
				<button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">
					Cancel
				</button>
				<button
					type="button"
					class="btn btn-primary"
					id="jhi-confirm-delete-tStateCode"
					data-cy="entityConfirmDeleteButton"
					v-text="$t('entity.action.delete')"
					v-on:click="removeTStateCode()"
				>
					Delete
				</button>
			</div>
		</b-modal>
		<div v-show="tStateCodes && tStateCodes.length > 0">
			<div class="row justify-content-center">
				<jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
			</div>
			<div class="row justify-content-center">
				<b-pagination
					size="md"
					:total-rows="totalItems"
					v-model="page"
					:per-page="itemsPerPage"
					:change="loadPage(page)"
				></b-pagination>
			</div>
		</div>
	</div>
</template>

<script lang="ts" src="./t-state-code.component.ts"></script>
<style scoped>
.header {
	display: flex;
	justify-content: space-between;
	margin-bottom: 1rem;
}
.table-container {
	padding: 1.5rem;
	border-radius: 2rem;
	border: 2px solid #f0f0f0;
}
table {
	width: 100%;
	border-collapse: collapse;
}
.table-header {
	border-radius: 9999px;
	background-color: #f8f8f8;
}
.table-header th:first-child {
	border-top-left-radius: 10px;
	border-bottom-left-radius: 10px;
}
.table-header th:last-child {
	border-top-right-radius: 10px;
	border-bottom-right-radius: 10px;
}
.table-header th {
	padding: 1rem;
}
.content-row {
	border-bottom: 1px solid #f0f0f0;
}
.content-row td {
	padding: 1rem;
}
.search-box {
	display: flex;
	align-items: center;
	border-radius: 9999px;
	background-color: #f8f8f8;
	padding: 0.5rem 1rem;
	width: fit-content;
	margin-bottom: 1rem;
}
.search-icon {
	color: #9da6b5;
	width: 20px;
}
.search-input {
	padding-left: 0.5rem;
	outline: none;
	border: none;
	font-size: 16px;
	background: none;
}
.search-input::placeholder {
	color: #9da6b5;
}
</style>
