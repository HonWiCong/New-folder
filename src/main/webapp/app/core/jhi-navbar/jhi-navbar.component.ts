import { Component, Inject, Vue } from 'vue-property-decorator';
import LoginService from '@/account/login.service';
import AccountService from '@/account/account.service';
import TranslationService from '@/locale/translation.service';

import { MapPin, User, Settings } from 'lucide-vue';
import BreadCrumbs from '@/core/breadcrumbs/breadcrumbs.vue';

@Component({
	components: {
		MapPin,
		User,
		Settings,
		Breadcrumb: BreadCrumbs,
	},
})
export default class JhiNavbar extends Vue {
	@Inject('loginService')
	private loginService: () => LoginService;
	@Inject('translationService') private translationService: () => TranslationService;

	@Inject('accountService') private accountService: () => AccountService;
	public version = 'v' + VERSION;
	private currentLanguage = this.$store.getters.currentLanguage;
	public languages: any = this.$store.getters.languages;
	private hasAnyAuthorityValues = {};

	created() {
		const currentLanguage = Object.keys(this.languages).includes(navigator.language) ? navigator.language : this.currentLanguage;
		this.translationService().refreshTranslation(currentLanguage);
	}

	public subIsActive(input) {
		const paths = Array.isArray(input) ? input : [input];
		return paths.some(path => {
			return this.$route.path.indexOf(path) === 0; // current path starts with this path string
		});
	}

	public changeLanguage(newLanguage: string): void {
		this.translationService().refreshTranslation(newLanguage);
	}

	public isActiveLanguage(key: string): boolean {
		return key === this.$store.getters.currentLanguage;
	}

	public logout(): Promise<any> {
		localStorage.removeItem('jhi-authenticationToken');
		sessionStorage.removeItem('jhi-authenticationToken');
		this.$store.commit('logout');
		if (this.$route.path !== '/') {
			return this.$router.push('/');
		}
		return Promise.resolve(this.$router.currentRoute);
	}

	public openLogin(): void {
		this.loginService().openLogin((<any>this).$root);
	}

	public get authenticated(): boolean {
		return this.$store.getters.authenticated;
	}

	public hasAnyAuthority(authorities: any): boolean {
		this.accountService()
			.hasAnyAuthorityAndCheckAuth(authorities)
			.then(value => {
				if (this.hasAnyAuthorityValues[authorities] !== value) {
					this.hasAnyAuthorityValues = { ...this.hasAnyAuthorityValues, [authorities]: value };
				}
			});
		return this.hasAnyAuthorityValues[authorities] ?? false;
	}

	public get openAPIEnabled(): boolean {
		return this.$store.getters.activeProfiles.indexOf('api-docs') > -1;
	}

	public get inProduction(): boolean {
		return this.$store.getters.activeProfiles.indexOf('prod') > -1;
	}
}
