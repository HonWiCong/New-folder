<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="tSectorCode">
        <h2 class="jh-entity-heading" data-cy="tSectorCodeDetailsHeading">
          <span v-text="$t('sainsApp.tSectorCode.detail.title')">TSectorCode</span> {{ tSectorCode.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('sainsApp.tSectorCode.sectorName')">Sector Name</span>
          </dt>
          <dd>
            <span>{{ tSectorCode.sectorName }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSectorCode.sectorDescription')">Sector Description</span>
          </dt>
          <dd>
            <span>{{ tSectorCode.sectorDescription }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSectorCode.enteredBy')">Entered By</span>
          </dt>
          <dd>
            <span>{{ tSectorCode.enteredBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSectorCode.enteredDate')">Entered Date</span>
          </dt>
          <dd>
            <span v-if="tSectorCode.enteredDate">{{ $d(Date.parse(tSectorCode.enteredDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSectorCode.modifiedBy')">Modified By</span>
          </dt>
          <dd>
            <span>{{ tSectorCode.modifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSectorCode.modifiedDate')">Modified Date</span>
          </dt>
          <dd>
            <span v-if="tSectorCode.modifiedDate">{{ $d(Date.parse(tSectorCode.modifiedDate), 'long') }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="tSectorCode.id"
          :to="{ name: 'TSectorCodeEdit', params: { tSectorCodeId: tSectorCode.id } }"
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

<script lang="ts" src="./t-sector-code-details.component.ts"></script>
