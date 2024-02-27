<template>
	<div>
		<div class="header">
			<h3>
				<span v-text="$t('sainsApp.tDivisionCode.home.title')">T Division Codes</span>
			</h3>
			<div class="d-flex justify-content-end">
				<button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
					<font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
				</button>
				<router-link :to="{ name: 'LocationDivisionNew' }" custom v-slot="{ navigate }">
					<button
						@click="navigate"
						id="jh-create-entity"
						data-cy="entityCreateButton"
						class="btn btn-primary jh-create-entity create-t-division-code"
					>
						<font-awesome-icon icon="plus"></font-awesome-icon>
						<span v-text="$t('sainsApp.tDivisionCode.home.createLabel')"> Create a new T Division Code </span>
					</button>
				</router-link>
			</div>
		</div>

		<div class="table-container">
			<div class="alert alert-warning" v-if="!isFetching && tDivisionCodes && tDivisionCodes.length === 0">
				<span v-text="$t('sainsApp.tDivisionCode.home.notFound')">No tDivisionCodes found</span>
			</div>
			<div class="table-responsive" v-if="tDivisionCodes && tDivisionCodes.length > 0">
				<table>
					<thead>
						<tr class="table-header">
							<th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
							<th scope="row"><span v-text="$t('sainsApp.tDivisionCode.divName')">Div Name</span></th>
							<th scope="row"></th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="tDivisionCode in tDivisionCodes" :key="tDivisionCode.id" class="content-row">
							<td>
								<router-link :to="{ name: 'LocationDivisionView', params: { tDivisionCodeId: tDivisionCode.id } }">{{
									tDivisionCode.id
								}}</router-link>
							</td>
							<td>{{ tDivisionCode.divName }}</td>
							<td class="text-right">
								<div class="btn-group">
									<router-link
										:to="{ name: 'LocationDivisionView', params: { tDivisionCodeId: tDivisionCode.id } }"
										custom
										v-slot="{ navigate }"
									>
										<button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
											<font-awesome-icon icon="eye"></font-awesome-icon>
											<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
										</button>
									</router-link>
									<router-link
										:to="{ name: 'LocationDivisionEdit', params: { tDivisionCodeId: tDivisionCode.id } }"
										custom
										v-slot="{ navigate }"
									>
										<button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
											<font-awesome-icon icon="pencil-alt"></font-awesome-icon>
											<span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
										</button>
									</router-link>
									<b-button
										v-on:click="prepareRemove(tDivisionCode)"
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
					id="sainsApp.tDivisionCode.delete.question"
					data-cy="tDivisionCodeDeleteDialogHeading"
					v-text="$t('entity.delete.title')"
					>Confirm delete operation</span
				></span
			>
			<div class="modal-body">
				<p id="jhi-delete-tDivisionCode-heading" v-text="$t('sainsApp.tDivisionCode.delete.question', { id: removeId })">
					Are you sure you want to delete this T Division Code?
				</p>
			</div>
			<div slot="modal-footer">
				<button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">
					Cancel
				</button>
				<button
					type="button"
					class="btn btn-primary"
					id="jhi-confirm-delete-tDivisionCode"
					data-cy="entityConfirmDeleteButton"
					v-text="$t('entity.action.delete')"
					v-on:click="removeTDivisionCode()"
				>
					Delete
				</button>
			</div>
		</b-modal>
	</div>
</template>

<script lang="ts" src="./t-division-code.component.ts"></script>
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
