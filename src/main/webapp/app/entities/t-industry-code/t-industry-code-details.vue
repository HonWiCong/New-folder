<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="tIndustryCode">
        <h2 class="jh-entity-heading" data-cy="tIndustryCodeDetailsHeading">
          <span v-text="$t('sainsApp.tIndustryCode.detail.title')">TIndustryCode</span> {{ tIndustryCode.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('sainsApp.tIndustryCode.industryName')">Industry Name</span>
          </dt>
          <dd>
            <span>{{ tIndustryCode.industryName }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tIndustryCode.enteredBy')">Entered By</span>
          </dt>
          <dd>
            <span>{{ tIndustryCode.enteredBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tIndustryCode.enteredDate')">Entered Date</span>
          </dt>
          <dd>
            <span v-if="tIndustryCode.enteredDate">{{ $d(Date.parse(tIndustryCode.enteredDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tIndustryCode.modifyBy')">Modify By</span>
          </dt>
          <dd>
            <span>{{ tIndustryCode.modifyBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tIndustryCode.modifiedDate')">Modified Date</span>
          </dt>
          <dd>
            <span v-if="tIndustryCode.modifiedDate">{{ $d(Date.parse(tIndustryCode.modifiedDate), 'long') }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="tIndustryCode.id"
          :to="{ name: 'TIndustryCodeEdit', params: { tIndustryCodeId: tIndustryCode.id } }"
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

<script lang="ts" src="./t-industry-code-details.component.ts"></script>
