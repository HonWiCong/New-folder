<template>
	<div>
		<h2 id="page-heading" data-cy="ApplicationUserHeading">
			<span v-text="$t('sainsApp.applicationUser.home.title')" id="application-user-heading">Application Users</span>
			<div class="d-flex justify-content-end">
				<button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
					<font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
					<span v-text="$t('sainsApp.applicationUser.home.refreshListLabel')">Refresh List</span>
				</button>
				<router-link :to="{ name: 'ApplicationUserCreate' }" custom v-slot="{ navigate }">
					<button
						@click="navigate"
						id="jh-create-entity"
						data-cy="entityCreateButton"
						class="btn btn-primary jh-create-entity create-application-user"
					>
						<font-awesome-icon icon="plus"></font-awesome-icon>
						<span v-text="$t('sainsApp.applicationUser.home.createLabel')"> Create a new Application User </span>
					</button>
				</router-link>
			</div>
		</h2>
		<br />
		<div class="alert alert-warning" v-if="!isFetching && applicationUsers && applicationUsers.length === 0">
			<span v-text="$t('sainsApp.applicationUser.home.notFound')">No applicationUsers found</span>
		</div>
		<div class="table-responsive" v-if="applicationUsers && applicationUsers.length > 0">
			<table class="table table-striped" aria-describedby="applicationUsers">
				<thead>
					<tr>
						<th scope="row" v-on:click="changeOrder('id')">
							<span v-text="$t('global.field.id')">ID</span>
							<jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
						</th>
						<th scope="row" v-on:click="changeOrder('ic')">
							<span v-text="$t('sainsApp.applicationUser.ic')">Ic</span>
							<jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ic'"></jhi-sort-indicator>
						</th>
						<th scope="row" v-on:click="changeOrder('status')">
							<span v-text="$t('sainsApp.applicationUser.status')">Status</span>
							<jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'status'"></jhi-sort-indicator>
						</th>
						<th scope="row" v-on:click="changeOrder('internalUser.id')">
							<span v-text="$t('sainsApp.applicationUser.internalUser')">Internal User</span>
							<jhi-sort-indicator
								:current-order="propOrder"
								:reverse="reverse"
								:field-name="'internalUser.id'"
							></jhi-sort-indicator>
						</th>
						<th scope="row" v-on:click="changeOrder('division.id')">
							<span v-text="$t('sainsApp.applicationUser.division')">Division</span>
							<jhi-sort-indicator
								:current-order="propOrder"
								:reverse="reverse"
								:field-name="'division.id'"
							></jhi-sort-indicator>
						</th>
						<th scope="row" v-on:click="changeOrder('section.id')">
							<span v-text="$t('sainsApp.applicationUser.section')">Section</span>
							<jhi-sort-indicator
								:current-order="propOrder"
								:reverse="reverse"
								:field-name="'section.id'"
							></jhi-sort-indicator>
						</th>
						<th scope="row" v-on:click="changeOrder('unit.id')">
							<span v-text="$t('sainsApp.applicationUser.unit')">Unit</span>
							<jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'unit.id'"></jhi-sort-indicator>
						</th>
						<th scope="row" v-on:click="changeOrder('office.id')">
							<span v-text="$t('sainsApp.applicationUser.office')">Office</span>
							<jhi-sort-indicator
								:current-order="propOrder"
								:reverse="reverse"
								:field-name="'office.id'"
							></jhi-sort-indicator>
						</th>
						<th scope="row"></th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="applicationUser in applicationUsers" :key="applicationUser.id" data-cy="entityTable">
						<td>
							<router-link :to="{ name: 'ApplicationUserView', params: { applicationUserId: applicationUser.id } }">{{
								applicationUser.id
							}}</router-link>
						</td>
						<td>{{ applicationUser.ic }}</td>
						<td>{{ applicationUser.status }}</td>
						<td>
							{{ applicationUser.internalUser ? applicationUser.internalUser.id : '' }}
						</td>
						<td>
							<div v-if="applicationUser.division">
								<router-link :to="{ name: 'TSectionCodeView', params: { tSectionCodeId: applicationUser.division.id } }">{{
									applicationUser.division.id
								}}</router-link>
							</div>
						</td>
						<td>
							<div v-if="applicationUser.section">
								<router-link :to="{ name: 'TUnitCodeView', params: { tUnitCodeId: applicationUser.section.id } }">{{
									applicationUser.section.id
								}}</router-link>
							</div>
						</td>
						<td>
							<div v-if="applicationUser.unit">
								<router-link :to="{ name: 'TSubunitCodeView', params: { tSubunitCodeId: applicationUser.unit.id } }">{{
									applicationUser.unit.id
								}}</router-link>
							</div>
						</td>
						<td>
							<div v-if="applicationUser.office">
								<router-link :to="{ name: 'TOfficeCodeView', params: { tOfficeCodeId: applicationUser.office.id } }">{{
									applicationUser.office.id
								}}</router-link>
							</div>
						</td>
						<td class="text-right">
							<div class="btn-group">
								<router-link
									:to="{ name: 'ApplicationUserView', params: { applicationUserId: applicationUser.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
										<font-awesome-icon icon="eye"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
									</button>
								</router-link>
								<router-link
									:to="{ name: 'ApplicationUserEdit', params: { applicationUserId: applicationUser.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
										<font-awesome-icon icon="pencil-alt"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
									</button>
								</router-link>
								<b-button
									v-on:click="prepareRemove(applicationUser)"
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
					id="sainsApp.applicationUser.delete.question"
					data-cy="applicationUserDeleteDialogHeading"
					v-text="$t('entity.delete.title')"
					>Confirm delete operation</span
				></span
			>
			<div class="modal-body">
				<p id="jhi-delete-applicationUser-heading" v-text="$t('sainsApp.applicationUser.delete.question', { id: removeId })">
					Are you sure you want to delete this Application User?
				</p>
			</div>
			<div slot="modal-footer">
				<button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">
					Cancel
				</button>
				<button
					type="button"
					class="btn btn-primary"
					id="jhi-confirm-delete-applicationUser"
					data-cy="entityConfirmDeleteButton"
					v-text="$t('entity.action.delete')"
					v-on:click="removeApplicationUser()"
				>
					Delete
				</button>
			</div>
		</b-modal>
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

<script lang="ts" src="./application-user.component.ts"></script>
