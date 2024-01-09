<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="tDistrictCode">
        <h2 class="jh-entity-heading" data-cy="tDistrictCodeDetailsHeading">
          <span v-text="$t('sainsApp.tDistrictCode.detail.title')">TDistrictCode</span> {{ tDistrictCode.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('sainsApp.tDistrictCode.disName')">Dis Name</span>
          </dt>
          <dd>
            <span>{{ tDistrictCode.disName }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tDistrictCode.enteredBy')">Entered By</span>
          </dt>
          <dd>
            <span>{{ tDistrictCode.enteredBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tDistrictCode.enteredDate')">Entered Date</span>
          </dt>
          <dd>
            <span v-if="tDistrictCode.enteredDate">{{ $d(Date.parse(tDistrictCode.enteredDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tDistrictCode.modifiedBy')">Modified By</span>
          </dt>
          <dd>
            <span>{{ tDistrictCode.modifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tDistrictCode.modifiedDate')">Modified Date</span>
          </dt>
          <dd>
            <span v-if="tDistrictCode.modifiedDate">{{ $d(Date.parse(tDistrictCode.modifiedDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tDistrictCode.tDivisionCode')">T Division Code</span>
          </dt>
          <dd>
            <div v-if="tDistrictCode.tDivisionCode">
              <router-link :to="{ name: 'TDivisionCodeView', params: { tDivisionCodeId: tDistrictCode.tDivisionCode.id } }">{{
                tDistrictCode.tDivisionCode.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="tDistrictCode.id"
          :to="{ name: 'TDistrictCodeEdit', params: { tDistrictCodeId: tDistrictCode.id } }"
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

<script lang="ts" src="./t-district-code-details.component.ts"></script>
