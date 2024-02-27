<template>
	<div>
		<h2 id="page-heading" data-cy="TOrgContactPersonHeading">
			<span v-text="$t('sainsApp.tOrgContactPerson.home.title')" id="t-org-contact-person-heading">T Org Contact People</span>
			<div class="d-flex justify-content-end">
				<button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
					<font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
					<span v-text="$t('sainsApp.tOrgContactPerson.home.refreshListLabel')">Refresh List</span>
				</button>
				<router-link :to="{ name: 'TOrgContactPersonCreate' }" custom v-slot="{ navigate }">
					<button
						@click="navigate"
						id="jh-create-entity"
						data-cy="entityCreateButton"
						class="btn btn-primary jh-create-entity create-t-org-contact-person"
					>
						<font-awesome-icon icon="plus"></font-awesome-icon>
						<span v-text="$t('sainsApp.tOrgContactPerson.home.createLabel')"> Create a new T Org Contact Person </span>
					</button>
				</router-link>
			</div>
		</h2>
		<br />
		<div class="alert alert-warning" v-if="!isFetching && tOrgContactPeople && tOrgContactPeople.length === 0">
			<span v-text="$t('sainsApp.tOrgContactPerson.home.notFound')">No tOrgContactPeople found</span>
		</div>
		<div class="table-responsive" v-if="tOrgContactPeople && tOrgContactPeople.length > 0">
			<table class="table table-striped" aria-describedby="tOrgContactPeople">
				<thead>
					<tr>
						<th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrgContactPerson.ocpOrgcodeid')">Ocp Orgcodeid</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrgContactPerson.ocpTitle')">Ocp Title</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrgContactPerson.ocpName')">Ocp Name</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrgContactPerson.ocpDesignation')">Ocp Designation</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrgContactPerson.ocpTelephone1')">Ocp Telephone 1</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrgContactPerson.ocpHandphone')">Ocp Handphone</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrgContactPerson.ocpMail')">Ocp Mail</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrgContactPerson.ocpSector')">Ocp Sector</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrgContactPerson.ocpStatus')">Ocp Status</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrgContactPerson.enteredBy')">Entered By</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrgContactPerson.enteredDate')">Entered Date</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrgContactPerson.modifiedBy')">Modified By</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrgContactPerson.modifiedDate')">Modified Date</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrgContactPerson.organization')">Organization</span></th>
						<th scope="row"></th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="tOrgContactPerson in tOrgContactPeople" :key="tOrgContactPerson.id" data-cy="entityTable">
						<td>
							<router-link :to="{ name: 'TOrgContactPersonView', params: { tOrgContactPersonId: tOrgContactPerson.id } }">{{
								tOrgContactPerson.id
							}}</router-link>
						</td>
						<td>{{ tOrgContactPerson.ocpOrgcodeid }}</td>
						<td>{{ tOrgContactPerson.ocpTitle }}</td>
						<td>{{ tOrgContactPerson.ocpName }}</td>
						<td>{{ tOrgContactPerson.ocpDesignation }}</td>
						<td>{{ tOrgContactPerson.ocpTelephone1 }}</td>
						<td>{{ tOrgContactPerson.ocpHandphone }}</td>
						<td>{{ tOrgContactPerson.ocpMail }}</td>
						<td>{{ tOrgContactPerson.ocpSector }}</td>
						<td>{{ tOrgContactPerson.ocpStatus }}</td>
						<td>{{ tOrgContactPerson.enteredBy }}</td>
						<td>{{ tOrgContactPerson.enteredDate ? $d(Date.parse(tOrgContactPerson.enteredDate), 'short') : '' }}</td>
						<td>{{ tOrgContactPerson.modifiedBy }}</td>
						<td>{{ tOrgContactPerson.modifiedDate ? $d(Date.parse(tOrgContactPerson.modifiedDate), 'short') : '' }}</td>
						<td>
							<div v-if="tOrgContactPerson.organization">
								<router-link
									:to="{ name: 'TOrganizationView', params: { tOrganizationId: tOrgContactPerson.organization.id } }"
									>{{ tOrgContactPerson.organization.id }}</router-link
								>
							</div>
						</td>
						<td class="text-right">
							<div class="btn-group">
								<router-link
									:to="{ name: 'TOrgContactPersonView', params: { tOrgContactPersonId: tOrgContactPerson.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
										<font-awesome-icon icon="eye"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
									</button>
								</router-link>
								<router-link
									:to="{ name: 'TOrgContactPersonEdit', params: { tOrgContactPersonId: tOrgContactPerson.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
										<font-awesome-icon icon="pencil-alt"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
									</button>
								</router-link>
								<b-button
									v-on:click="prepareRemove(tOrgContactPerson)"
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
					id="sainsApp.tOrgContactPerson.delete.question"
					data-cy="tOrgContactPersonDeleteDialogHeading"
					v-text="$t('entity.delete.title')"
					>Confirm delete operation</span
				></span
			>
			<div class="modal-body">
				<p id="jhi-delete-tOrgContactPerson-heading" v-text="$t('sainsApp.tOrgContactPerson.delete.question', { id: removeId })">
					Are you sure you want to delete this T Org Contact Person?
				</p>
			</div>
			<div slot="modal-footer">
				<button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">
					Cancel
				</button>
				<button
					type="button"
					class="btn btn-primary"
					id="jhi-confirm-delete-tOrgContactPerson"
					data-cy="entityConfirmDeleteButton"
					v-text="$t('entity.action.delete')"
					v-on:click="removeTOrgContactPerson()"
				>
					Delete
				</button>
			</div>
		</b-modal>
	</div>
</template>

<script lang="ts" src="./t-org-contact-person.component.ts"></script>
