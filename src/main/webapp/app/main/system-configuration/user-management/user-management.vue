<template>
	<div class="custom-container">
		<div class="header">
			<h3 v-text="$t('userManagement.home.title')"></h3>
			<div class="d-flex justify-content-end">
				<button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isLoading">
					<font-awesome-icon icon="sync" :spin="isLoading"></font-awesome-icon>
				</button>
				<router-link custom v-slot="{ navigate }" :to="{ name: 'UserManagementCreateDetail' }">
					<button @click="navigate" class="btn btn-primary jh-create-entity">
						<font-awesome-icon icon="plus"></font-awesome-icon>
						<span v-text="$t('userManagement.home.createLabel')">Create a new User</span>
					</button>
				</router-link>
			</div>
		</div>

		<div class="table-container">
			<div class="table-responsive" v-if="users">
				<table>
					<thead>
						<tr class="table-header">
							<th scope="col" v-on:click="changeOrder('id')">
								<span v-text="$t('global.field.id')">ID</span>
								<jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
							</th>
							<th scope="col">Username</th>
							<th scope="row" v-on:click="changeOrder('ic')">
								<span v-text="$t('sainsApp.applicationUser.ic')">Ic</span>
								<jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ic'"></jhi-sort-indicator>
							</th>
							<th scope="col">Division</th>
							<th scope="col">Section</th>
							<th scope="col">Unit</th>
							<th scope="col">Office</th>
							<th scope="row" v-on:click="changeOrder('status')">
								<span v-text="$t('sainsApp.applicationUser.status')">Status</span>
								<jhi-sort-indicator
									:current-order="propOrder"
									:reverse="reverse"
									:field-name="'status'"
								></jhi-sort-indicator>
							</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody v-if="users">
						<tr v-for="user in applicationUsers" :key="user.internalUser.id" :id="user.internalUser.login" class="content-row">
							<td>
								<router-link :to="{ name: 'JhiUserView', params: { userId: user.internalUser.login } }">{{
									user.id
								}}</router-link>
							</td>
							<td>{{ user.internalUser.firstName + ' ' + user.internalUser.lastName }}</td>
							<td>{{ user.ic }}</td>
							<td>{{ user.division.sectName }}</td>
							<td>{{ user.section.untUnit }}</td>
							<td>{{ user.unit.subuntSubunit }}</td>
							<td>{{ user.office.offName }}</td>
							<td>{{ user.status }}</td>
							<td class="text-right">
								<div class="btn-group">
									<router-link
										:to="{ name: 'UserManagementView', params: { userId: user.id } }"
										custom
										v-slot="{ navigate }"
									>
										<button @click="navigate" class="btn btn-info btn-sm details">
											<font-awesome-icon icon="eye"></font-awesome-icon>
											<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
										</button>
									</router-link>
									<router-link
										:to="{ name: 'UserManagementEditDetail', params: { userId: user.id } }"
										custom
										v-slot="{ navigate }"
									>
										<button @click="navigate" class="btn btn-primary btn-sm edit">
											<font-awesome-icon icon="pencil-alt"></font-awesome-icon>
											<span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
										</button>
									</router-link>
									<b-button
										v-on:click="prepareRemove(user)"
										variant="danger"
										class="btn btn-sm delete"
										:disabled="username === user.internalUser.login"
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
		</div>

		<div v-show="applicationUsers && applicationUsers.length > 0">
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

<style scoped>
.custom-container {
	border-radius: 10px;
	background-color: white;
	padding: 2rem;
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
