<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="tCityCode">
        <h2 class="jh-entity-heading" data-cy="tCityCodeDetailsHeading">
          <span v-text="$t('sainsApp.tCityCode.detail.title')">TCityCode</span> {{ tCityCode.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('sainsApp.tCityCode.cityCode')">City Code</span>
          </dt>
          <dd>
            <span>{{ tCityCode.cityCode }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tCityCode.cityName')">City Name</span>
          </dt>
          <dd>
            <span>{{ tCityCode.cityName }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tCityCode.enteredBy')">Entered By</span>
          </dt>
          <dd>
            <span>{{ tCityCode.enteredBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tCityCode.enteredDate')">Entered Date</span>
          </dt>
          <dd>
            <span v-if="tCityCode.enteredDate">{{ $d(Date.parse(tCityCode.enteredDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tCityCode.modifyBy')">Modify By</span>
          </dt>
          <dd>
            <span>{{ tCityCode.modifyBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tCityCode.modifiedDate')">Modified Date</span>
          </dt>
          <dd>
            <span v-if="tCityCode.modifiedDate">{{ $d(Date.parse(tCityCode.modifiedDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tCityCode.tDivisionCode')">T Division Code</span>
          </dt>
          <dd>
            <div v-if="tCityCode.tDivisionCode">
              <router-link :to="{ name: 'TDivisionCodeView', params: { tDivisionCodeId: tCityCode.tDivisionCode.id } }">{{
                tCityCode.tDivisionCode.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="tCityCode.id"
          :to="{ name: 'TCityCodeEdit', params: { tCityCodeId: tCityCode.id } }"
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

<script lang="ts" src="./t-city-code-details.component.ts"></script>
