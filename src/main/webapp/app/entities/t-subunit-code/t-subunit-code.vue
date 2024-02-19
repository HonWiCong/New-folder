<template>
	<div>
		<h2 id="page-heading" data-cy="TSubunitCodeHeading">
			<span v-text="$t('sainsApp.tSubunitCode.home.title')" id="t-subunit-code-heading">T Subunit Codes</span>
			<div class="d-flex justify-content-end">
				<button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
					<font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
					<span v-text="$t('sainsApp.tSubunitCode.home.refreshListLabel')">Refresh List</span>
				</button>
				<router-link :to="{ name: 'TSubunitCodeCreate' }" custom v-slot="{ navigate }">
					<button
						@click="navigate"
						id="jh-create-entity"
						data-cy="entityCreateButton"
						class="btn btn-primary jh-create-entity create-t-subunit-code"
					>
						<font-awesome-icon icon="plus"></font-awesome-icon>
						<span v-text="$t('sainsApp.tSubunitCode.home.createLabel')"> Create a new T Subunit Code </span>
					</button>
				</router-link>
			</div>
		</h2>
		<br />
		<div class="alert alert-warning" v-if="!isFetching && tSubunitCodes && tSubunitCodes.length === 0">
			<span v-text="$t('sainsApp.tSubunitCode.home.notFound')">No tSubunitCodes found</span>
		</div>
		<div class="table-responsive" v-if="tSubunitCodes && tSubunitCodes.length > 0">
			<table class="table table-striped" aria-describedby="tSubunitCodes">
				<thead>
					<tr>
						<th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSubunitCode.subuntSubunit')">Subunt Subunit</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSubunitCode.subuntHeadid')">Subunt Headid</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSubunitCode.subuntActingHeadid')">Subunt Acting Headid</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSubunitCode.subuntHrpayId')">Subunt Hrpay Id</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSubunitCode.subuntPapId')">Subunt Pap Id</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSubunitCode.subuntPapCode')">Subunt Pap Code</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSubunitCode.subuntPapActive')">Subunt Pap Active</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSubunitCode.subunyUnitid')">Subuny Unitid</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSubunitCode.unit')">Unit</span></th>
						<th scope="row"></th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="tSubunitCode in tSubunitCodes" :key="tSubunitCode.id" data-cy="entityTable">
						<td>
							<router-link :to="{ name: 'TSubunitCodeView', params: { tSubunitCodeId: tSubunitCode.id } }">{{
								tSubunitCode.id
							}}</router-link>
						</td>
						<td>{{ tSubunitCode.subuntSubunit }}</td>
						<td>{{ tSubunitCode.subuntHeadid }}</td>
						<td>{{ tSubunitCode.subuntActingHeadid }}</td>
						<td>{{ tSubunitCode.subuntHrpayId }}</td>
						<td>{{ tSubunitCode.subuntPapId }}</td>
						<td>{{ tSubunitCode.subuntPapCode }}</td>
						<td>{{ tSubunitCode.subuntPapActive }}</td>
						<td>{{ tSubunitCode.subunyUnitid }}</td>
						<td>
							<div v-if="tSubunitCode.unit">
								<router-link :to="{ name: 'TUnitCodeView', params: { tUnitCodeId: tSubunitCode.unit.id } }">{{
									tSubunitCode.unit.id
								}}</router-link>
							</div>
						</td>
						<td class="text-right">
							<div class="btn-group">
								<router-link
									:to="{ name: 'TSubunitCodeView', params: { tSubunitCodeId: tSubunitCode.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
										<font-awesome-icon icon="eye"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
									</button>
								</router-link>
								<router-link
									:to="{ name: 'TSubunitCodeEdit', params: { tSubunitCodeId: tSubunitCode.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
										<font-awesome-icon icon="pencil-alt"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
									</button>
								</router-link>
								<b-button
									v-on:click="prepareRemove(tSubunitCode)"
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
					id="sainsApp.tSubunitCode.delete.question"
					data-cy="tSubunitCodeDeleteDialogHeading"
					v-text="$t('entity.delete.title')"
					>Confirm delete operation</span
				></span
			>
			<div class="modal-body">
				<p id="jhi-delete-tSubunitCode-heading" v-text="$t('sainsApp.tSubunitCode.delete.question', { id: removeId })">
					Are you sure you want to delete this T Subunit Code?
				</p>
			</div>
			<div slot="modal-footer">
				<button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">
					Cancel
				</button>
				<button
					type="button"
					class="btn btn-primary"
					id="jhi-confirm-delete-tSubunitCode"
					data-cy="entityConfirmDeleteButton"
					v-text="$t('entity.action.delete')"
					v-on:click="removeTSubunitCode()"
				>
					Delete
				</button>
			</div>
		</b-modal>
	</div>
</template>

<script lang="ts" src="./t-subunit-code.component.ts"></script>
