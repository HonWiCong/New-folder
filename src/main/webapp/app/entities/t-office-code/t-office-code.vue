<template>
	<div>
		<h2 id="page-heading" data-cy="TOfficeCodeHeading">
			<span v-text="$t('sainsApp.tOfficeCode.home.title')" id="t-office-code-heading">T Office Codes</span>
			<div class="d-flex justify-content-end">
				<button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
					<font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
					<span v-text="$t('sainsApp.tOfficeCode.home.refreshListLabel')">Refresh List</span>
				</button>
				<router-link :to="{ name: 'TOfficeCodeCreate' }" custom v-slot="{ navigate }">
					<button
						@click="navigate"
						id="jh-create-entity"
						data-cy="entityCreateButton"
						class="btn btn-primary jh-create-entity create-t-office-code"
					>
						<font-awesome-icon icon="plus"></font-awesome-icon>
						<span v-text="$t('sainsApp.tOfficeCode.home.createLabel')"> Create a new T Office Code </span>
					</button>
				</router-link>
			</div>
		</h2>
		<br />
		<div class="alert alert-warning" v-if="!isFetching && tOfficeCodes && tOfficeCodes.length === 0">
			<span v-text="$t('sainsApp.tOfficeCode.home.notFound')">No tOfficeCodes found</span>
		</div>
		<div class="table-responsive" v-if="tOfficeCodes && tOfficeCodes.length > 0">
			<table class="table table-striped" aria-describedby="tOfficeCodes">
				<thead>
					<tr>
						<th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOfficeCode.offName')">Off Name</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOfficeCode.offAddress')">Off Address</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOfficeCode.offPostcode')">Off Postcode</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOfficeCode.offCity')">Off City</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOfficeCode.offState')">Off State</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOfficeCode.offOffphone')">Off Offphone</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOfficeCode.offOfffax')">Off Offfax</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOfficeCode.enteredBy')">Entered By</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOfficeCode.enteredDate')">Entered Date</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOfficeCode.modifiedBy')">Modified By</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOfficeCode.modifiedDate')">Modified Date</span></th>
						<th scope="row"></th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="tOfficeCode in tOfficeCodes" :key="tOfficeCode.id" data-cy="entityTable">
						<td>
							<router-link :to="{ name: 'TOfficeCodeView', params: { tOfficeCodeId: tOfficeCode.id } }">{{
								tOfficeCode.id
							}}</router-link>
						</td>
						<td>{{ tOfficeCode.offName }}</td>
						<td>{{ tOfficeCode.offAddress }}</td>
						<td>{{ tOfficeCode.offPostcode }}</td>
						<td>{{ tOfficeCode.offCity }}</td>
						<td>{{ tOfficeCode.offState }}</td>
						<td>{{ tOfficeCode.offOffphone }}</td>
						<td>{{ tOfficeCode.offOfffax }}</td>
						<td>{{ tOfficeCode.enteredBy }}</td>
						<td>{{ tOfficeCode.enteredDate ? $d(Date.parse(tOfficeCode.enteredDate), 'short') : '' }}</td>
						<td>{{ tOfficeCode.modifiedBy }}</td>
						<td>{{ tOfficeCode.modifiedDate ? $d(Date.parse(tOfficeCode.modifiedDate), 'short') : '' }}</td>
						<td class="text-right">
							<div class="btn-group">
								<router-link
									:to="{ name: 'TOfficeCodeView', params: { tOfficeCodeId: tOfficeCode.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
										<font-awesome-icon icon="eye"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
									</button>
								</router-link>
								<router-link
									:to="{ name: 'TOfficeCodeEdit', params: { tOfficeCodeId: tOfficeCode.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
										<font-awesome-icon icon="pencil-alt"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
									</button>
								</router-link>
								<b-button
									v-on:click="prepareRemove(tOfficeCode)"
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
				><span id="sainsApp.tOfficeCode.delete.question" data-cy="tOfficeCodeDeleteDialogHeading" v-text="$t('entity.delete.title')"
					>Confirm delete operation</span
				></span
			>
			<div class="modal-body">
				<p id="jhi-delete-tOfficeCode-heading" v-text="$t('sainsApp.tOfficeCode.delete.question', { id: removeId })">
					Are you sure you want to delete this T Office Code?
				</p>
			</div>
			<div slot="modal-footer">
				<button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">
					Cancel
				</button>
				<button
					type="button"
					class="btn btn-primary"
					id="jhi-confirm-delete-tOfficeCode"
					data-cy="entityConfirmDeleteButton"
					v-text="$t('entity.action.delete')"
					v-on:click="removeTOfficeCode()"
				>
					Delete
				</button>
			</div>
		</b-modal>
	</div>
</template>

<script lang="ts" src="./t-office-code.component.ts"></script>
