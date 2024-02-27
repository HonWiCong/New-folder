<template>
	<div class="custom-container">
		<div class="header">
			<h3 v-text="$t('sainsApp.tOrganization.home.title')"></h3>
			<div class="d-flex justify-content-end">
				<button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
					<font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
				</button>
				<router-link :to="{ name: 'SupplierOrganizationNew' }" custom v-slot="{ navigate }">
					<button @click="navigate" class="btn btn-primary jh-create-entity create-t-organization">
						<font-awesome-icon icon="plus"></font-awesome-icon>
						<span v-text="$t('sainsApp.tOrganization.home.createLabel')"> Create a new T Organization </span>
					</button>
				</router-link>
			</div>
		</div>
		<div class="table-container">
			<div class="alert alert-warning" v-if="!isFetching && tOrganizations && tOrganizations.length === 0">
				<span v-text="$t('sainsApp.tOrganization.home.notFound')">No tOrganizations found</span>
			</div>
			<div class="table-responsive" v-if="tOrganizations && tOrganizations.length > 0">
				<table>
					<thead>
						<tr class="table-header">
							<th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
							<th scope="row"><span v-text="$t('sainsApp.tOrganization.orgCode')">Code</span></th>
							<th scope="row"><span v-text="$t('sainsApp.tOrganization.orgName')">Name</span></th>
							<th scope="row"><span v-text="$t('sainsApp.tOrganization.orgEmail')">Email</span></th>
							<th scope="row"><span v-text="$t('sainsApp.tOrganization.orgContperson')">Contperson</span></th>
							<th scope="row"><span v-text="$t('sainsApp.tOrganization.orgDirectline')">Directline</span></th>
							<th scope="row"><span v-text="$t('sainsApp.tOrganization.orgActiveStatus')">Status</span></th>
							<th scope="row"></th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="tOrganization in tOrganizations" :key="tOrganization.id" class="content-row">
							<td>
								<router-link :to="{ name: 'TOrganizationView', params: { tOrganizationId: tOrganization.id } }">{{
									tOrganization.id
								}}</router-link>
							</td>
							<td>{{ tOrganization.orgCode }}</td>
							<td>{{ tOrganization.orgName }}</td>
							<td>{{ tOrganization.orgEmail }}</td>
							<td>{{ tOrganization.orgContperson }}</td>
							<td>{{ tOrganization.orgDirectline }}</td>
							<td>{{ tOrganization.orgActiveStatus }}</td>
							<td class="text-right">
								<div class="btn-group">
									<router-link
										:to="{ name: 'SupplierOrganizationView', params: { tOrganizationId: tOrganization.id } }"
										custom
										v-slot="{ navigate }"
									>
										<button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
											<font-awesome-icon icon="eye"></font-awesome-icon>
											<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
										</button>
									</router-link>
									<router-link
										:to="{ name: 'SupplierOrganizationEdit', params: { tOrganizationId: tOrganization.id } }"
										custom
										v-slot="{ navigate }"
									>
										<button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
											<font-awesome-icon icon="pencil-alt"></font-awesome-icon>
											<span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
										</button>
									</router-link>
									<b-button
										v-on:click="prepareRemove(tOrganization)"
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
				><span
					id="sainsApp.tOrganization.delete.question"
					data-cy="tOrganizationDeleteDialogHeading"
					v-text="$t('entity.delete.title')"
					>Confirm delete operation</span
				></span
			>
			<div class="modal-body">
				<p id="jhi-delete-tOrganization-heading" v-text="$t('sainsApp.tOrganization.delete.question', { id: removeId })">
					Are you sure you want to delete this T Organization?
				</p>
			</div>
			<div slot="modal-footer">
				<button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">
					Cancel
				</button>
				<button
					type="button"
					class="btn btn-primary"
					id="jhi-confirm-delete-tOrganization"
					data-cy="entityConfirmDeleteButton"
					v-text="$t('entity.action.delete')"
					v-on:click="removeTOrganization()"
				>
					Delete
				</button>
			</div>
		</b-modal>
	</div>
</template>

<script lang="ts" src="./t-organization.component.ts"></script>
<style scoped>
.custom-container {
	border-radius: 10px;
	background-color: white;
	padding: 2rem;
	flex: 1 1 auto;
	height: 100%;
}
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
