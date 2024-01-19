<template>
	<div>
		<h2 id="page-heading" data-cy="TSectionCodeHeading">
			<span v-text="$t('sainsApp.tSectionCode.home.title')" id="t-section-code-heading">T Section Codes</span>
			<div class="d-flex justify-content-end">
				<button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
					<font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
					<span v-text="$t('sainsApp.tSectionCode.home.refreshListLabel')">Refresh List</span>
				</button>
				<router-link :to="{ name: 'TSectionCodeCreate' }" custom v-slot="{ navigate }">
					<button
						@click="navigate"
						id="jh-create-entity"
						data-cy="entityCreateButton"
						class="btn btn-primary jh-create-entity create-t-section-code"
					>
						<font-awesome-icon icon="plus"></font-awesome-icon>
						<span v-text="$t('sainsApp.tSectionCode.home.createLabel')"> Create a new T Section Code </span>
					</button>
				</router-link>
			</div>
		</h2>
		<br />
		<div class="alert alert-warning" v-if="!isFetching && tSectionCodes && tSectionCodes.length === 0">
			<span v-text="$t('sainsApp.tSectionCode.home.notFound')">No tSectionCodes found</span>
		</div>
		<div class="table-responsive" v-if="tSectionCodes && tSectionCodes.length > 0">
			<table class="table table-striped" aria-describedby="tSectionCodes">
				<thead>
					<tr>
						<th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSectionCode.sectName')">Sect Name</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSectionCode.sectHeadid')">Sect Headid</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSectionCode.sectActingHeadid')">Sect Acting Headid</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSectionCode.sectSainsSubsidiary')">Sect Sains Subsidiary</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSectionCode.sectHrpayId')">Sect Hrpay Id</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSectionCode.sectPapId')">Sect Pap Id</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSectionCode.sectPapCode')">Sect Pap Code</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSectionCode.sectPapCompany')">Sect Pap Company</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSectionCode.sectPapActive')">Sect Pap Active</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSectionCode.sectCcCode')">Sect Cc Code</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSectionCode.enteredBy')">Entered By</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSectionCode.enteredDate')">Entered Date</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSectionCode.modifiedBy')">Modified By</span></th>
						<th scope="row"><span v-text="$t('sainsApp.tSectionCode.modifiedDate')">Modified Date</span></th>
						<th scope="row"></th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="tSectionCode in tSectionCodes" :key="tSectionCode.id" data-cy="entityTable">
						<td>
							<router-link :to="{ name: 'TSectionCodeView', params: { tSectionCodeId: tSectionCode.id } }">{{
								tSectionCode.id
							}}</router-link>
						</td>
						<td>{{ tSectionCode.sectName }}</td>
						<td>{{ tSectionCode.sectHeadid }}</td>
						<td>{{ tSectionCode.sectActingHeadid }}</td>
						<td v-text="$t('sainsApp.SectSainsSubsidiary.' + tSectionCode.sectSainsSubsidiary)">
							{{ tSectionCode.sectSainsSubsidiary }}
						</td>
						<td>{{ tSectionCode.sectHrpayId }}</td>
						<td>{{ tSectionCode.sectPapId }}</td>
						<td>{{ tSectionCode.sectPapCode }}</td>
						<td>{{ tSectionCode.sectPapCompany }}</td>
						<td v-text="$t('sainsApp.SectPapActive.' + tSectionCode.sectPapActive)">{{ tSectionCode.sectPapActive }}</td>
						<td>{{ tSectionCode.sectCcCode }}</td>
						<td>{{ tSectionCode.enteredBy }}</td>
						<td>{{ tSectionCode.enteredDate ? $d(Date.parse(tSectionCode.enteredDate), 'short') : '' }}</td>
						<td>{{ tSectionCode.modifiedBy }}</td>
						<td>{{ tSectionCode.modifiedDate ? $d(Date.parse(tSectionCode.modifiedDate), 'short') : '' }}</td>
						<td class="text-right">
							<div class="btn-group">
								<router-link
									:to="{ name: 'TSectionCodeView', params: { tSectionCodeId: tSectionCode.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
										<font-awesome-icon icon="eye"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
									</button>
								</router-link>
								<router-link
									:to="{ name: 'TSectionCodeEdit', params: { tSectionCodeId: tSectionCode.id } }"
									custom
									v-slot="{ navigate }"
								>
									<button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
										<font-awesome-icon icon="pencil-alt"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
									</button>
								</router-link>
								<b-button
									v-on:click="prepareRemove(tSectionCode)"
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
					id="sainsApp.tSectionCode.delete.question"
					data-cy="tSectionCodeDeleteDialogHeading"
					v-text="$t('entity.delete.title')"
					>Confirm delete operation</span
				></span
			>
			<div class="modal-body">
				<p id="jhi-delete-tSectionCode-heading" v-text="$t('sainsApp.tSectionCode.delete.question', { id: removeId })">
					Are you sure you want to delete this T Section Code?
				</p>
			</div>
			<div slot="modal-footer">
				<button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">
					Cancel
				</button>
				<button
					type="button"
					class="btn btn-primary"
					id="jhi-confirm-delete-tSectionCode"
					data-cy="entityConfirmDeleteButton"
					v-text="$t('entity.action.delete')"
					v-on:click="removeTSectionCode()"
				>
					Delete
				</button>
			</div>
		</b-modal>
	</div>
</template>

<script lang="ts" src="./t-section-code.component.ts"></script>
