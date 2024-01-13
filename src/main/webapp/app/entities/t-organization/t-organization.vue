<template>
	<div>
		<h2 id="page-heading" data-cy="TOrganizationHeading">
			<span v-text="$t('sainsApp.tOrganization.home.title')" id="t-organization-heading">T Organizations</span>
			<div class="d-flex justify-content-end">
				<button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
					<font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
					<span v-text="$t('sainsApp.tOrganization.home.refreshListLabel')">Refresh List</span>
				</button>
				<router-link :to="{ name: 'TOrganizationCreate' }" custom v-slot="{ navigate }">
					<button
						@click="navigate"
						id="jh-create-entity"
						data-cy="entityCreateButton"
						class="btn btn-primary jh-create-entity create-t-organization"
					>
						<font-awesome-icon icon="plus"></font-awesome-icon>
						<span v-text="$t('sainsApp.tOrganization.home.createLabel')"> Create a new T Organization </span>
					</button>
				</router-link>
			</div>
		</h2>
		<br />
		<div class="alert alert-warning" v-if="!isFetching && tOrganizations && tOrganizations.length === 0">
			<span v-text="$t('sainsApp.tOrganization.home.notFound')">No tOrganizations found</span>
		</div>
		<div class="table-responsive" v-if="tOrganizations && tOrganizations.length > 0">
			<table class="table table-striped" aria-describedby="tOrganizations">
				<thead>
					<tr>
						<th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrganization.orgCode')">Code</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrganization.orgName')">Name</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrganization.orgEmail')">Email</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrganization.orgContperson')">Contperson</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrganization.orgDirectline')">Directline</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrganization.orgContpEmail')">Contp Email</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrganization.orgContpHp')">Contp Hp</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tOrganization.orgActiveStatus')">Status</span></th>
						<th scope="row"></th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="tOrganization in tOrganizations" :key="tOrganization.id" data-cy="entityTable">
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
						<td>{{ tOrganization.orgContpEmail }}</td>
						<td>{{ tOrganization.orgContpHp }}</td>
						<td>{{ tOrganization.orgActiveStatus }}</td>
						<td class="text-right">
							<div class="btn-group">
								<router-link
									:to="{ name: 'TOrganizationView', params: { tOrganizationId: tOrganization.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
										<font-awesome-icon icon="eye"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
									</button>
								</router-link>
								<router-link
									:to="{ name: 'TOrganizationEdit', params: { tOrganizationId: tOrganization.id } }"
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
