<template>
	<div>
		<div class="header">
			<h3>
				<span v-text="$t('sainsApp.tCityCode.home.title')" id="t-country-code-heading">T City Codes</span>
			</h3>
			<div>
				<button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
					<font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
				</button>
				<router-link :to="{ name: 'TCityCodeCreate' }" custom v-slot="{ navigate }">
					<button
						@click="navigate"
						id="jh-create-entity"
						data-cy="entityCreateButton"
						class="btn btn-primary jh-create-entity create-t-city-code"
					>
						<font-awesome-icon icon="plus"></font-awesome-icon>
						<span v-text="$t('sainsApp.tCityCode.home.createLabel')"> Create a new T City Code </span>
					</button>
				</router-link>
			</div>
		</div>
		<div class="table-container">
			<div class="search-box">
				<Search class="search-icon" />
				<input v-model="search" type="text" placeholder="Search by name" class="search-input" @change="retrieveAllTCityCodes" />
			</div>
			<div class="alert alert-warning" v-if="!isFetching && tCityCodes && tCityCodes.length === 0">
				<span v-text="$t('sainsApp.tCityCode.home.notFound')">No tCityCodes found</span>
			</div>
			<div class="table-responsive" v-if="tCityCodes && tCityCodes.length > 0">
				<table>
					<thead>
						<tr class="table-header">
							<th scope="row" v-on:click="changeOrder('id')">
								<span v-text="$t('global.field.id')">ID</span>
								<jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
							</th>
							<th scope="row" v-on:click="changeOrder('cityCode')">
								<span v-text="$t('sainsApp.tCityCode.cityCode')">City Code</span>
								<jhi-sort-indicator
									:current-order="propOrder"
									:reverse="reverse"
									:field-name="'cityCode'"
								></jhi-sort-indicator>
							</th>
							<th scope="row" v-on:click="changeOrder('cityName')">
								<span v-text="$t('sainsApp.tCityCode.cityName')">City Name</span>
								<jhi-sort-indicator
									:current-order="propOrder"
									:reverse="reverse"
									:field-name="'cityName'"
								></jhi-sort-indicator>
							</th>
							<th scope="row" v-on:click="changeOrder('tStateCode.id')">
								<span v-text="$t('sainsApp.tCityCode.tStateCode')">T State Code</span>
								<jhi-sort-indicator
									:current-order="propOrder"
									:reverse="reverse"
									:field-name="'tStateCode.id'"
								></jhi-sort-indicator>
							</th>
							<th scope="row"></th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="tCityCode in tCityCodes" :key="tCityCode.id" class="content-row">
							<td>
								<router-link :to="{ name: 'TCityCodeView', params: { tCityCodeId: tCityCode.id } }">{{
									tCityCode.id
								}}</router-link>
							</td>
							<td>{{ tCityCode.cityCode }}</td>
							<td>{{ tCityCode.cityName }}</td>
							<td>
								<div v-if="tCityCode.tstateCode">
									<router-link :to="{ name: 'TStateCodeView', params: { tStateCodeId: tCityCode.tstateCode.id } }">{{
										tCityCode.tstateCode.stateName
									}}</router-link>
								</div>
							</td>
							<td class="text-right">
								<div class="btn-group">
									<router-link
										:to="{ name: 'TCityCodeView', params: { tCityCodeId: tCityCode.id } }"
										custom
										v-slot="{ navigate }"
									>
										<button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
											<font-awesome-icon icon="eye"></font-awesome-icon>
											<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
										</button>
									</router-link>
									<router-link
										:to="{ name: 'TCityCodeEdit', params: { tCityCodeId: tCityCode.id } }"
										custom
										v-slot="{ navigate }"
									>
										<button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
											<font-awesome-icon icon="pencil-alt"></font-awesome-icon>
											<span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
										</button>
									</router-link>
									<b-button
										v-on:click="prepareRemove(tCityCode)"
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
			<b-modal ref="removeEntity" id="removeEntity">
				<span slot="modal-title"
					><span id="sainsApp.tCityCode.delete.question" data-cy="tCityCodeDeleteDialogHeading" v-text="$t('entity.delete.title')"
						>Confirm delete operation</span
					></span
				>
				<div class="modal-body">
					<p id="jhi-delete-tCityCode-heading" v-text="$t('sainsApp.tCityCode.delete.question', { id: removeId })">
						Are you sure you want to delete this T City Code?
					</p>
				</div>
				<div slot="modal-footer">
					<button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">
						Cancel
					</button>
					<button
						type="button"
						class="btn btn-primary"
						id="jhi-confirm-delete-tCityCode"
						data-cy="entityConfirmDeleteButton"
						v-text="$t('entity.action.delete')"
						v-on:click="removeTCityCode()"
					>
						Delete
					</button>
				</div>
			</b-modal>
			<div v-show="tCityCodes && tCityCodes.length > 0">
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
	</div>
</template>

<script lang="ts" src="./t-city-code.component.ts"></script>
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
