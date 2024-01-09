<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="tTitleCode">
        <h2 class="jh-entity-heading" data-cy="tTitleCodeDetailsHeading">
          <span v-text="$t('sainsApp.tTitleCode.detail.title')">TTitleCode</span> {{ tTitleCode.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('sainsApp.tTitleCode.ttTitle')">Tt Title</span>
          </dt>
          <dd>
            <span>{{ tTitleCode.ttTitle }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tTitleCode.enteredBy')">Entered By</span>
          </dt>
          <dd>
            <span>{{ tTitleCode.enteredBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tTitleCode.enteredDate')">Entered Date</span>
          </dt>
          <dd>
            <span v-if="tTitleCode.enteredDate">{{ $d(Date.parse(tTitleCode.enteredDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tTitleCode.modifiedBy')">Modified By</span>
          </dt>
          <dd>
            <span>{{ tTitleCode.modifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tTitleCode.modifiedDate')">Modified Date</span>
          </dt>
          <dd>
            <span v-if="tTitleCode.modifiedDate">{{ $d(Date.parse(tTitleCode.modifiedDate), 'long') }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="tTitleCode.id"
          :to="{ name: 'TTitleCodeEdit', params: { tTitleCodeId: tTitleCode.id } }"
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

<script lang="ts" src="./t-title-code-details.component.ts"></script>
