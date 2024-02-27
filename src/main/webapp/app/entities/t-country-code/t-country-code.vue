<template>
	<div>
		<div class="header">
			<h3 id="page-heading" data-cy="TCountryCodeHeading">
				<span v-text="$t('sainsApp.tCountryCode.home.title')" id="t-country-code-heading">T Country Codes</span>
			</h3>
			<div>
				<button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
					<font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
				</button>
				<router-link :to="{ name: 'LocationCountryNew' }" custom v-slot="{ navigate }">
					<button
						@click="navigate"
						id="jh-create-entity"
						data-cy="entityCreateButton"
						class="btn btn-primary jh-create-entity create-t-country-code"
					>
						<font-awesome-icon icon="plus"></font-awesome-icon>
						<span> Add new country </span>
					</button>
				</router-link>
			</div>
		</div>
		<div class="table-container">
			<div class="search-box">
				<Search class="search-icon" />
				<input v-model="search" type="text" placeholder="Search by name" class="search-input" @change="retrieveAllTCountryCodes" />
			</div>
			<div class="alert alert-warning" v-if="!isFetching && tCountryCodes && tCountryCodes.length === 0">
				<span v-text="$t('sainsApp.tCountryCode.home.notFound')">No tCountryCodes found</span>
			</div>
			<div class="table-responsive" v-if="tCountryCodes && tCountryCodes.length > 0">
				<table aria-describedby="tCountryCodes">
					<thead>
						<tr class="table-header">
							<th scope="row" v-on:click="changeOrder('id')">
								<span v-text="$t('global.field.id')">ID</span>
								<jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
							</th>
							<th scope="row" v-on:click="changeOrder('countryCode')">
								<span v-text="$t('sainsApp.tCountryCode.countryCode')">Country Code</span>
								<jhi-sort-indicator
									:current-order="propOrder"
									:reverse="reverse"
									:field-name="'countryCode'"
								></jhi-sort-indicator>
							</th>
							<th scope="row" v-on:click="changeOrder('countryName')">
								<span v-text="$t('sainsApp.tCountryCode.countryName')">Country Name</span>
								<jhi-sort-indicator
									:current-order="propOrder"
									:reverse="reverse"
									:field-name="'countryName'"
								></jhi-sort-indicator>
							</th>
							<th scope="row" v-on:click="changeOrder('countryNationality')">
								<span v-text="$t('sainsApp.tCountryCode.countryNationality')">Country Nationality</span>
								<jhi-sort-indicator
									:current-order="propOrder"
									:reverse="reverse"
									:field-name="'countryNationality'"
								></jhi-sort-indicator>
							</th>
							<th scope="row"></th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="tCountryCode in tCountryCodes" :key="tCountryCode.id" class="content-row">
							<td>
								<router-link :to="{ name: 'TCountryCodeView', params: { tCountryCodeId: tCountryCode.id } }">{{
									tCountryCode.id
								}}</router-link>
							</td>
							<td>{{ tCountryCode.countryCode }}</td>
							<td>{{ tCountryCode.countryName }}</td>
							<td>{{ tCountryCode.countryNationality }}</td>
							<td class="text-right">
								<div class="btn-group">
									<router-link
										:to="{ name: 'TCountryCodeView', params: { tCountryCodeId: tCountryCode.id } }"
										custom
										v-slot="{ navigate }"
									>
										<button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
											<font-awesome-icon icon="eye"></font-awesome-icon>
											<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
										</button>
									</router-link>
									<router-link
										:to="{ name: 'LocationCountryEdit', params: { tCountryCodeId: tCountryCode.id } }"
										custom
										v-slot="{ navigate }"
									>
										<button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
											<font-awesome-icon icon="pencil-alt"></font-awesome-icon>
											<span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
										</button>
									</router-link>
									<b-button
										v-on:click="prepareRemove(tCountryCode)"
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
					><span
						id="sainsApp.tCountryCode.delete.question"
						data-cy="tCountryCodeDeleteDialogHeading"
						v-text="$t('entity.delete.title')"
						>Confirm delete operation</span
					></span
				>
				<div class="modal-body">
					<p id="jhi-delete-tCountryCode-heading" v-text="$t('sainsApp.tCountryCode.delete.question', { id: removeId })">
						Are you sure you want to delete this T Country Code?
					</p>
				</div>
				<div slot="modal-footer">
					<button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">
						Cancel
					</button>
					<button
						type="button"
						class="btn btn-primary"
						id="jhi-confirm-delete-tCountryCode"
						data-cy="entityConfirmDeleteButton"
						v-text="$t('entity.action.delete')"
						v-on:click="removeTCountryCode()"
					>
						Delete
					</button>
				</div>
			</b-modal>
			<div v-show="tCountryCodes && tCountryCodes.length > 0">
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

<script lang="ts" src="./t-country-code.component.ts"></script>

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
