<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="tSupplierCategory">
        <h2 class="jh-entity-heading" data-cy="tSupplierCategoryDetailsHeading">
          <span v-text="$t('sainsApp.tSupplierCategory.detail.title')">TSupplierCategory</span> {{ tSupplierCategory.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('sainsApp.tSupplierCategory.spcCategory')">Spc Category</span>
          </dt>
          <dd>
            <span>{{ tSupplierCategory.spcCategory }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSupplierCategory.enteredBy')">Entered By</span>
          </dt>
          <dd>
            <span>{{ tSupplierCategory.enteredBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSupplierCategory.enteredDate')">Entered Date</span>
          </dt>
          <dd>
            <span v-if="tSupplierCategory.enteredDate">{{ $d(Date.parse(tSupplierCategory.enteredDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSupplierCategory.modifiedBy')">Modified By</span>
          </dt>
          <dd>
            <span>{{ tSupplierCategory.modifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSupplierCategory.modifiedDate')">Modified Date</span>
          </dt>
          <dd>
            <span v-if="tSupplierCategory.modifiedDate">{{ $d(Date.parse(tSupplierCategory.modifiedDate), 'long') }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="tSupplierCategory.id"
          :to="{ name: 'TSupplierCategoryEdit', params: { tSupplierCategoryId: tSupplierCategory.id } }"
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

<script lang="ts" src="./t-supplier-category-details.component.ts"></script>
