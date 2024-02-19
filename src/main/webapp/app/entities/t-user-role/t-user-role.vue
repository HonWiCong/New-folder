<template>
	<div>
		<h2 id="page-heading" data-cy="TUserRoleHeading">
			<span v-text="$t('sainsApp.tUserRole.home.title')" id="t-user-role-heading">T User Roles</span>
			<div class="d-flex justify-content-end">
				<button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
					<font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
					<span v-text="$t('sainsApp.tUserRole.home.refreshListLabel')">Refresh List</span>
				</button>
				<router-link :to="{ name: 'TUserRoleCreate' }" custom v-slot="{ navigate }">
					<button
						@click="navigate"
						id="jh-create-entity"
						data-cy="entityCreateButton"
						class="btn btn-primary jh-create-entity create-t-user-role"
					>
						<font-awesome-icon icon="plus"></font-awesome-icon>
						<span v-text="$t('sainsApp.tUserRole.home.createLabel')"> Create a new T User Role </span>
					</button>
				</router-link>
			</div>
		</h2>
		<br />
		<div class="alert alert-warning" v-if="!isFetching && tUserRoles && tUserRoles.length === 0">
			<span v-text="$t('sainsApp.tUserRole.home.notFound')">No tUserRoles found</span>
		</div>
		<div class="table-responsive" v-if="tUserRoles && tUserRoles.length > 0">
			<table class="table table-striped" aria-describedby="tUserRoles">
				<thead>
					<tr>
						<th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUserRole.sysuserId')">Sysuser Id</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUserRole.usrRoleid')">Usr Roleid</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUserRole.enteredBy')">Entered By</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUserRole.enteredDate')">Entered Date</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUserRole.modifiedBy')">Modified By</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUserRole.modifiedDate')">Modified Date</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUserRole.role')">Role</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUserRole.user')">User</span></th>
						<th scope="row"></th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="tUserRole in tUserRoles" :key="tUserRole.id" data-cy="entityTable">
						<td>
							<router-link :to="{ name: 'TUserRoleView', params: { tUserRoleId: tUserRole.id } }">{{
								tUserRole.id
							}}</router-link>
						</td>
						<td>{{ tUserRole.sysuserId }}</td>
						<td>{{ tUserRole.usrRoleid }}</td>
						<td>{{ tUserRole.enteredBy }}</td>
						<td>{{ tUserRole.enteredDate ? $d(Date.parse(tUserRole.enteredDate), 'short') : '' }}</td>
						<td>{{ tUserRole.modifiedBy }}</td>
						<td>{{ tUserRole.modifiedDate ? $d(Date.parse(tUserRole.modifiedDate), 'short') : '' }}</td>
						<td>
							<div v-if="tUserRole.role">
								<router-link :to="{ name: 'TUserRoleCodeView', params: { tUserRoleCodeId: tUserRole.role.id } }">{{
									tUserRole.role.id
								}}</router-link>
							</div>
						</td>
						<td>
							<div v-if="tUserRole.user">
								<router-link :to="{ name: 'ApplicationUserView', params: { applicationUserId: tUserRole.user.id } }">{{
									tUserRole.user.id
								}}</router-link>
							</div>
						</td>
						<td class="text-right">
							<div class="btn-group">
								<router-link
									:to="{ name: 'TUserRoleView', params: { tUserRoleId: tUserRole.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
										<font-awesome-icon icon="eye"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
									</button>
								</router-link>
								<router-link
									:to="{ name: 'TUserRoleEdit', params: { tUserRoleId: tUserRole.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
										<font-awesome-icon icon="pencil-alt"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
									</button>
								</router-link>
								<b-button
									v-on:click="prepareRemove(tUserRole)"
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
				><span id="sainsApp.tUserRole.delete.question" data-cy="tUserRoleDeleteDialogHeading" v-text="$t('entity.delete.title')"
					>Confirm delete operation</span
				></span
			>
			<div class="modal-body">
				<p id="jhi-delete-tUserRole-heading" v-text="$t('sainsApp.tUserRole.delete.question', { id: removeId })">
					Are you sure you want to delete this T User Role?
				</p>
			</div>
			<div slot="modal-footer">
				<button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">
					Cancel
				</button>
				<button
					type="button"
					class="btn btn-primary"
					id="jhi-confirm-delete-tUserRole"
					data-cy="entityConfirmDeleteButton"
					v-text="$t('entity.action.delete')"
					v-on:click="removeTUserRole()"
				>
					Delete
				</button>
			</div>
		</b-modal>
	</div>
</template>

<script lang="ts" src="./t-user-role.component.ts"></script>
