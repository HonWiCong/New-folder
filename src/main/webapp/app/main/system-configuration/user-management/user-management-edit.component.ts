import { Component, Inject, Vue } from 'vue-property-decorator';

@Component
export default class JhiUserManagementEdit extends Vue {
	mounted() {
		console.log(this.$route);
	}
}
