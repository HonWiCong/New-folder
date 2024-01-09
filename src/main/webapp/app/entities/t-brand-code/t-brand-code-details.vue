<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="tBrandCode">
        <h2 class="jh-entity-heading" data-cy="tBrandCodeDetailsHeading">
          <span v-text="$t('sainsApp.tBrandCode.detail.title')">TBrandCode</span> {{ tBrandCode.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('sainsApp.tBrandCode.brandName')">Brand Name</span>
          </dt>
          <dd>
            <span>{{ tBrandCode.brandName }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tBrandCode.enteredBy')">Entered By</span>
          </dt>
          <dd>
            <span>{{ tBrandCode.enteredBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tBrandCode.enteredDate')">Entered Date</span>
          </dt>
          <dd>
            <span v-if="tBrandCode.enteredDate">{{ $d(Date.parse(tBrandCode.enteredDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tBrandCode.modifyBy')">Modify By</span>
          </dt>
          <dd>
            <span>{{ tBrandCode.modifyBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tBrandCode.modifiedDate')">Modified Date</span>
          </dt>
          <dd>
            <span v-if="tBrandCode.modifiedDate">{{ $d(Date.parse(tBrandCode.modifiedDate), 'long') }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="tBrandCode.id"
          :to="{ name: 'TBrandCodeEdit', params: { tBrandCodeId: tBrandCode.id } }"
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

<script lang="ts" src="./t-brand-code-details.component.ts"></script>
