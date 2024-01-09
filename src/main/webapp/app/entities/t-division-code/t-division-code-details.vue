<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="tDivisionCode">
        <h2 class="jh-entity-heading" data-cy="tDivisionCodeDetailsHeading">
          <span v-text="$t('sainsApp.tDivisionCode.detail.title')">TDivisionCode</span> {{ tDivisionCode.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('sainsApp.tDivisionCode.divName')">Div Name</span>
          </dt>
          <dd>
            <span>{{ tDivisionCode.divName }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tDivisionCode.enteredBy')">Entered By</span>
          </dt>
          <dd>
            <span>{{ tDivisionCode.enteredBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tDivisionCode.enteredDate')">Entered Date</span>
          </dt>
          <dd>
            <span v-if="tDivisionCode.enteredDate">{{ $d(Date.parse(tDivisionCode.enteredDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tDivisionCode.modifyBy')">Modify By</span>
          </dt>
          <dd>
            <span>{{ tDivisionCode.modifyBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tDivisionCode.modifiedDate')">Modified Date</span>
          </dt>
          <dd>
            <span v-if="tDivisionCode.modifiedDate">{{ $d(Date.parse(tDivisionCode.modifiedDate), 'long') }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="tDivisionCode.id"
          :to="{ name: 'TDivisionCodeEdit', params: { tDivisionCodeId: tDivisionCode.id } }"
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

<script lang="ts" src="./t-division-code-details.component.ts"></script>
