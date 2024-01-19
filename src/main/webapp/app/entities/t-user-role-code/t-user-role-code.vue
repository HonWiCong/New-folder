<template>
	<div>
		<h2 id="page-heading" data-cy="TUserRoleCodeHeading">
			<span v-text="$t('sainsApp.tUserRoleCode.home.title')" id="t-user-role-code-heading">T User Role Codes</span>
			<div class="d-flex justify-content-end">
				<button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
					<font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
					<span v-text="$t('sainsApp.tUserRoleCode.home.refreshListLabel')">Refresh List</span>
				</button>
				<router-link :to="{ name: 'TUserRoleCodeCreate' }" custom v-slot="{ navigate }">
					<button
						@click="navigate"
						id="jh-create-entity"
						data-cy="entityCreateButton"
						class="btn btn-primary jh-create-entity create-t-user-role-code"
					>
						<font-awesome-icon icon="plus"></font-awesome-icon>
						<span v-text="$t('sainsApp.tUserRoleCode.home.createLabel')"> Create a new T User Role Code </span>
					</button>
				</router-link>
			</div>
		</h2>
		<br />
		<div class="alert alert-warning" v-if="!isFetching && tUserRoleCodes && tUserRoleCodes.length === 0">
			<span v-text="$t('sainsApp.tUserRoleCode.home.notFound')">No tUserRoleCodes found</span>
		</div>
		<div class="table-responsive" v-if="tUserRoleCodes && tUserRoleCodes.length > 0">
			<table class="table table-striped" aria-describedby="tUserRoleCodes">
				<thead>
					<tr>
						<th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUserRoleCode.roleName')">Role Name</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUserRoleCode.roleHead')">Role Head</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUserRoleCode.actApprover1')">Act Approver 1</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUserRoleCode.actApprover2')">Act Approver 2</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUserRoleCode.enteredBy')">Entered By</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUserRoleCode.enteredDate')">Entered Date</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUserRoleCode.modifiedBy')">Modified By</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUserRoleCode.modifiedDate')">Modified Date</span></th>
						<th scope="row"></th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="tUserRoleCode in tUserRoleCodes" :key="tUserRoleCode.id" data-cy="entityTable">
						<td>
							<router-link :to="{ name: 'TUserRoleCodeView', params: { tUserRoleCodeId: tUserRoleCode.id } }">{{
								tUserRoleCode.id
							}}</router-link>
						</td>
						<td>{{ tUserRoleCode.roleName }}</td>
						<td>{{ tUserRoleCode.roleHead }}</td>
						<td>{{ tUserRoleCode.actApprover1 }}</td>
						<td>{{ tUserRoleCode.actApprover2 }}</td>
						<td>{{ tUserRoleCode.enteredBy }}</td>
						<td>{{ tUserRoleCode.enteredDate ? $d(Date.parse(tUserRoleCode.enteredDate), 'short') : '' }}</td>
						<td>{{ tUserRoleCode.modifiedBy }}</td>
						<td>{{ tUserRoleCode.modifiedDate ? $d(Date.parse(tUserRoleCode.modifiedDate), 'short') : '' }}</td>
						<td class="text-right">
							<div class="btn-group">
								<router-link
									:to="{ name: 'TUserRoleCodeView', params: { tUserRoleCodeId: tUserRoleCode.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
										<font-awesome-icon icon="eye"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
									</button>
								</router-link>
								<router-link
									:to="{ name: 'TUserRoleCodeEdit', params: { tUserRoleCodeId: tUserRoleCode.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
										<font-awesome-icon icon="pencil-alt"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
									</button>
								</router-link>
								<b-button
									v-on:click="prepareRemove(tUserRoleCode)"
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
					id="sainsApp.tUserRoleCode.delete.question"
					data-cy="tUserRoleCodeDeleteDialogHeading"
					v-text="$t('entity.delete.title')"
					>Confirm delete operation</span
				></span
			>
			<div class="modal-body">
				<p id="jhi-delete-tUserRoleCode-heading" v-text="$t('sainsApp.tUserRoleCode.delete.question', { id: removeId })">
					Are you sure you want to delete this T User Role Code?
				</p>
			</div>
			<div slot="modal-footer">
				<button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">
					Cancel
				</button>
				<button
					type="button"
					class="btn btn-primary"
					id="jhi-confirm-delete-tUserRoleCode"
					data-cy="entityConfirmDeleteButton"
					v-text="$t('entity.action.delete')"
					v-on:click="removeTUserRoleCode()"
				>
					Delete
				</button>
			</div>
		</b-modal>
	</div>
</template>

<script lang="ts" src="./t-user-role-code.component.ts"></script>
