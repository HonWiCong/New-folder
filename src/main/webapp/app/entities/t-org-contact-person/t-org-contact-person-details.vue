<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="tOrgContactPerson">
        <h2 class="jh-entity-heading" data-cy="tOrgContactPersonDetailsHeading">
          <span v-text="$t('sainsApp.tOrgContactPerson.detail.title')">TOrgContactPerson</span> {{ tOrgContactPerson.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('sainsApp.tOrgContactPerson.ocpOrgcodeid')">Ocp Orgcodeid</span>
          </dt>
          <dd>
            <span>{{ tOrgContactPerson.ocpOrgcodeid }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tOrgContactPerson.ocpTitle')">Ocp Title</span>
          </dt>
          <dd>
            <span>{{ tOrgContactPerson.ocpTitle }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tOrgContactPerson.ocpName')">Ocp Name</span>
          </dt>
          <dd>
            <span>{{ tOrgContactPerson.ocpName }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tOrgContactPerson.ocpDesignation')">Ocp Designation</span>
          </dt>
          <dd>
            <span>{{ tOrgContactPerson.ocpDesignation }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tOrgContactPerson.ocpTelephone1')">Ocp Telephone 1</span>
          </dt>
          <dd>
            <span>{{ tOrgContactPerson.ocpTelephone1 }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tOrgContactPerson.ocpHandphone')">Ocp Handphone</span>
          </dt>
          <dd>
            <span>{{ tOrgContactPerson.ocpHandphone }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tOrgContactPerson.ocpMail')">Ocp Mail</span>
          </dt>
          <dd>
            <span>{{ tOrgContactPerson.ocpMail }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tOrgContactPerson.ocpSector')">Ocp Sector</span>
          </dt>
          <dd>
            <span>{{ tOrgContactPerson.ocpSector }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tOrgContactPerson.ocpStatus')">Ocp Status</span>
          </dt>
          <dd>
            <span>{{ tOrgContactPerson.ocpStatus }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tOrgContactPerson.enteredBy')">Entered By</span>
          </dt>
          <dd>
            <span>{{ tOrgContactPerson.enteredBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tOrgContactPerson.enteredDate')">Entered Date</span>
          </dt>
          <dd>
            <span v-if="tOrgContactPerson.enteredDate">{{ $d(Date.parse(tOrgContactPerson.enteredDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tOrgContactPerson.modifiedBy')">Modified By</span>
          </dt>
          <dd>
            <span>{{ tOrgContactPerson.modifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tOrgContactPerson.modifiedDate')">Modified Date</span>
          </dt>
          <dd>
            <span v-if="tOrgContactPerson.modifiedDate">{{ $d(Date.parse(tOrgContactPerson.modifiedDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tOrgContactPerson.tOrganization')">T Organization</span>
          </dt>
          <dd>
            <div v-if="tOrgContactPerson.tOrganization">
              <router-link :to="{ name: 'TOrganizationView', params: { tOrganizationId: tOrgContactPerson.tOrganization.id } }">{{
                tOrgContactPerson.tOrganization.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="tOrgContactPerson.id"
          :to="{ name: 'TOrgContactPersonEdit', params: { tOrgContactPersonId: tOrgContactPerson.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./t-org-contact-person-details.component.ts"></script>
