<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="tAuditTrail">
        <h2 class="jh-entity-heading" data-cy="tAuditTrailDetailsHeading">
          <span v-text="$t('sainsApp.tAuditTrail.detail.title')">TAuditTrail</span> {{ tAuditTrail.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('sainsApp.tAuditTrail.userId')">User Id</span>
          </dt>
          <dd>
            <span>{{ tAuditTrail.userId }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tAuditTrail.dateTime')">Date Time</span>
          </dt>
          <dd>
            <span v-if="tAuditTrail.dateTime">{{ $d(Date.parse(tAuditTrail.dateTime), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tAuditTrail.tableName')">Table Name</span>
          </dt>
          <dd>
            <span>{{ tAuditTrail.tableName }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tAuditTrail.auditAction')">Audit Action</span>
          </dt>
          <dd>
            <span>{{ tAuditTrail.auditAction }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tAuditTrail.recordId')">Record Id</span>
          </dt>
          <dd>
            <span>{{ tAuditTrail.recordId }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tAuditTrail.fieldDesc')">Field Desc</span>
          </dt>
          <dd>
            <span>{{ tAuditTrail.fieldDesc }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tAuditTrail.atStatus')">At Status</span>
          </dt>
          <dd>
            <span>{{ tAuditTrail.atStatus }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tAuditTrail.stFullDesc')">St Full Desc</span>
          </dt>
          <dd>
            <span>{{ tAuditTrail.stFullDesc }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="tAuditTrail.id"
          :to="{ name: 'TAuditTrailEdit', params: { tAuditTrailId: tAuditTrail.id } }"
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

<script lang="ts" src="./t-audit-trail-details.component.ts"></script>
