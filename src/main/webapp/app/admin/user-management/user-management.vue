<template>
	<div>
		<h2>
			<span id="user-management-page-heading" v-text="$t('userManagement.home.title')" data-cy="userManagementPageHeading"
				>Users</span
			>

			<div class="d-flex justify-content-end">
				<button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isLoading">
					<font-awesome-icon icon="sync" :spin="isLoading"></font-awesome-icon>
					<span v-text="$t('userManagement.home.refreshListLabel')">Refresh List</span>
				</button>
				<router-link custom v-slot="{ navigate }" :to="{ name: 'JhiUserCreate' }">
					<button @click="navigate" class="btn btn-primary jh-create-entity">
						<font-awesome-icon icon="plus"></font-awesome-icon>
						<span v-text="$t('userManagement.home.createLabel')">Create a new User</span>
					</button>
				</router-link>
			</div>
		</h2>
		<div class="table-responsive" v-if="users">
			<table class="table table-striped" aria-describedby="Users">
				<thead>
					<tr>
						<th scope="col" v-on:click="changeOrder('id')">
							<span v-text="$t('global.field.id')">ID</span>
							<jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
						</th>
						<th scope="col" v-on:click="changeOrder('firstName')">
							<span>Username</span>
							<jhi-sort-indicator
								:current-order="propOrder"
								:reverse="reverse"
								:field-name="'firstName'"
							></jhi-sort-indicator>
						</th>
						<th scope="col">IC No.</th>
						<th scope="col">Division</th>
						<th scope="col">Section</th>
						<th scope="col">Unit</th>
						<th scope="col">Office</th>
						<th scope="col">Status</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody v-if="users">
					<tr v-for="user in users" :key="user.id" :id="user.login">
						<td>
							<router-link :to="{ name: 'JhiUserView', params: { userId: user.login } }">{{ user.id }}</router-link>
						</td>
						<td>{{ user.firstName + ' ' + user.lastName }}</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td class="text-right">
							<div class="btn-group">
								<router-link :to="{ name: 'JhiUserView', params: { userId: user.login } }" custom v-slot="{ navigate }">
									<button @click="navigate" class="btn btn-info btn-sm details">
										<font-awesome-icon icon="eye"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
									</button>
								</router-link>
								<router-link :to="{ name: 'JhiUserEdit', params: { userId: user.login } }" custom v-slot="{ navigate }">
									<button @click="navigate" class="btn btn-primary btn-sm edit">
										<font-awesome-icon icon="pencil-alt"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
									</button>
								</router-link>
								<b-button
									v-on:click="prepareRemove(user)"
									variant="danger"
									class="btn btn-sm delete"
									:disabled="username === user.login"
								>
									<font-awesome-icon icon="times"></font-awesome-icon>
									<span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
								</b-button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			<b-modal ref="removeUser" id="removeUser" v-bind:title="$t('entity.delete.title')" @ok="deleteUser()">
				<div class="modal-body">
					<p id="jhi-delete-user-heading" v-text="$t('userManagement.delete.question', { login: removeId })">
						Are you sure you want to delete this user?
					</p>
				</div>
				<div slot="modal-footer">
					<button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">
						Cancel
					</button>
					<button
						type="button"
						class="btn btn-primary"
						id="confirm-delete-user"
						v-text="$t('entity.action.delete')"
						v-on:click="deleteUser()"
					>
						Delete
					</button>
				</div>
			</b-modal>
		</div>
		<div v-show="users && users.length > 0">
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

<script lang="ts" src="./user-management.component.ts"></script>
