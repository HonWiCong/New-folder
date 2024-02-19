<template>
	<div>
		<h2 id="page-heading" data-cy="TUnitCodeHeading">
			<span v-text="$t('sainsApp.tUnitCode.home.title')" id="t-unit-code-heading">T Unit Codes</span>
			<div class="d-flex justify-content-end">
				<button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
					<font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
					<span v-text="$t('sainsApp.tUnitCode.home.refreshListLabel')">Refresh List</span>
				</button>
				<router-link :to="{ name: 'TUnitCodeCreate' }" custom v-slot="{ navigate }">
					<button
						@click="navigate"
						id="jh-create-entity"
						data-cy="entityCreateButton"
						class="btn btn-primary jh-create-entity create-t-unit-code"
					>
						<font-awesome-icon icon="plus"></font-awesome-icon>
						<span v-text="$t('sainsApp.tUnitCode.home.createLabel')"> Create a new T Unit Code </span>
					</button>
				</router-link>
			</div>
		</h2>
		<br />
		<div class="alert alert-warning" v-if="!isFetching && tUnitCodes && tUnitCodes.length === 0">
			<span v-text="$t('sainsApp.tUnitCode.home.notFound')">No tUnitCodes found</span>
		</div>
		<div class="table-responsive" v-if="tUnitCodes && tUnitCodes.length > 0">
			<table class="table table-striped" aria-describedby="tUnitCodes">
				<thead>
					<tr>
						<th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUnitCode.untUnit')">Unt Unit</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUnitCode.untHeadid')">Unt Headid</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUnitCode.untActingHeadid')">Unt Acting Headid</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUnitCode.untHrpayId')">Unt Hrpay Id</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUnitCode.untPapId')">Unt Pap Id</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUnitCode.untPapCode')">Unt Pap Code</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUnitCode.untPapActive')">Unt Pap Active</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUnitCode.untPapDepartmentid')">Unt Pap Departmentid</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUnitCode.enteredBy')">Entered By</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUnitCode.enteredDate')">Entered Date</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUnitCode.modifiedBy')">Modified By</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tUnitCode.modifiedDate')">Modified Date</span></th>
						<th scope="row"></th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="tUnitCode in tUnitCodes" :key="tUnitCode.id" data-cy="entityTable">
						<td>
							<router-link :to="{ name: 'TUnitCodeView', params: { tUnitCodeId: tUnitCode.id } }">{{
								tUnitCode.id
							}}</router-link>
						</td>
						<td>{{ tUnitCode.untUnit }}</td>
						<td>{{ tUnitCode.untHeadid }}</td>
						<td>{{ tUnitCode.untActingHeadid }}</td>
						<td>{{ tUnitCode.untHrpayId }}</td>
						<td>{{ tUnitCode.untPapId }}</td>
						<td>{{ tUnitCode.untPapCode }}</td>
						<td>{{ tUnitCode.untPapActive }}</td>
						<td>{{ tUnitCode.untPapDepartmentid }}</td>
						<td>{{ tUnitCode.enteredBy }}</td>
						<td>{{ tUnitCode.enteredDate ? $d(Date.parse(tUnitCode.enteredDate), 'short') : '' }}</td>
						<td>{{ tUnitCode.modifiedBy }}</td>
						<td>{{ tUnitCode.modifiedDate ? $d(Date.parse(tUnitCode.modifiedDate), 'short') : '' }}</td>
						<td class="text-right">
							<div class="btn-group">
								<router-link
									:to="{ name: 'TUnitCodeView', params: { tUnitCodeId: tUnitCode.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
										<font-awesome-icon icon="eye"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
									</button>
								</router-link>
								<router-link
									:to="{ name: 'TUnitCodeEdit', params: { tUnitCodeId: tUnitCode.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
										<font-awesome-icon icon="pencil-alt"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
									</button>
								</router-link>
								<b-button
									v-on:click="prepareRemove(tUnitCode)"
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
				><span id="sainsApp.tUnitCode.delete.question" data-cy="tUnitCodeDeleteDialogHeading" v-text="$t('entity.delete.title')"
					>Confirm delete operation</span
				></span
			>
			<div class="modal-body">
				<p id="jhi-delete-tUnitCode-heading" v-text="$t('sainsApp.tUnitCode.delete.question', { id: removeId })">
					Are you sure you want to delete this T Unit Code?
				</p>
			</div>
			<div slot="modal-footer">
				<button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">
					Cancel
				</button>
				<button
					type="button"
					class="btn btn-primary"
					id="jhi-confirm-delete-tUnitCode"
					data-cy="entityConfirmDeleteButton"
					v-text="$t('entity.action.delete')"
					v-on:click="removeTUnitCode()"
				>
					Delete
				</button>
			</div>
		</b-modal>
	</div>
</template>

<script lang="ts" src="./t-unit-code.component.ts"></script>
