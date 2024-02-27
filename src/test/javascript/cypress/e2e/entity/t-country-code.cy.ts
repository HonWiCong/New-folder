import {
	entityTableSelector,
	entityDetailsButtonSelector,
	entityDetailsBackButtonSelector,
	entityCreateButtonSelector,
	entityCreateSaveButtonSelector,
	entityCreateCancelButtonSelector,
	entityEditButtonSelector,
	entityDeleteButtonSelector,
	entityConfirmDeleteButtonSelector,
} from '../../support/entity';

describe('TCountryCode e2e test', () => {
	const tCountryCodePageUrl = '/t-country-code';
	const tCountryCodePageUrlPattern = new RegExp('/t-country-code(\\?.*)?$');
	const username = Cypress.env('E2E_USERNAME') ?? 'user';
	const password = Cypress.env('E2E_PASSWORD') ?? 'user';
	const tCountryCodeSample = {};

	let tCountryCode;

	beforeEach(() => {
		cy.login(username, password);
	});

	beforeEach(() => {
		cy.intercept('GET', '/api/t-country-codes+(?*|)').as('entitiesRequest');
		cy.intercept('POST', '/api/t-country-codes').as('postEntityRequest');
		cy.intercept('DELETE', '/api/t-country-codes/*').as('deleteEntityRequest');
	});

	afterEach(() => {
		if (tCountryCode) {
			cy.authenticatedRequest({
				method: 'DELETE',
				url: `/api/t-country-codes/${tCountryCode.id}`,
			}).then(() => {
				tCountryCode = undefined;
			});
		}
	});

	it('TCountryCodes menu should load TCountryCodes page', () => {
		cy.visit('/');
		cy.clickOnEntityMenuItem('t-country-code');
		cy.wait('@entitiesRequest').then(({ response }) => {
			if (response.body.length === 0) {
				cy.get(entityTableSelector).should('not.exist');
			} else {
				cy.get(entityTableSelector).should('exist');
			}
		});
		cy.getEntityHeading('TCountryCode').should('exist');
		cy.url().should('match', tCountryCodePageUrlPattern);
	});

	describe('TCountryCode page', () => {
		describe('create button click', () => {
			beforeEach(() => {
				cy.visit(tCountryCodePageUrl);
				cy.wait('@entitiesRequest');
			});

			it('should load create TCountryCode page', () => {
				cy.get(entityCreateButtonSelector).click();
				cy.url().should('match', new RegExp('/t-country-code/new$'));
				cy.getEntityCreateUpdateHeading('TCountryCode');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tCountryCodePageUrlPattern);
			});
		});

		describe('with existing value', () => {
			beforeEach(() => {
				cy.authenticatedRequest({
					method: 'POST',
					url: '/api/t-country-codes',
					body: tCountryCodeSample,
				}).then(({ body }) => {
					tCountryCode = body;

					cy.intercept(
						{
							method: 'GET',
							url: '/api/t-country-codes+(?*|)',
							times: 1,
						},
						{
							statusCode: 200,
							headers: {
								link: '<http://localhost/api/t-country-codes?page=0&size=20>; rel="last",<http://localhost/api/t-country-codes?page=0&size=20>; rel="first"',
							},
							body: [tCountryCode],
						}
					).as('entitiesRequestInternal');
				});

				cy.visit(tCountryCodePageUrl);

				cy.wait('@entitiesRequestInternal');
			});

			it('detail button click should load details TCountryCode page', () => {
				cy.get(entityDetailsButtonSelector).first().click();
				cy.getEntityDetailsHeading('tCountryCode');
				cy.get(entityDetailsBackButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tCountryCodePageUrlPattern);
			});

			it('edit button click should load edit TCountryCode page and go back', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TCountryCode');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tCountryCodePageUrlPattern);
			});

			it.skip('edit button click should load edit TCountryCode page and save', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TCountryCode');
				cy.get(entityCreateSaveButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tCountryCodePageUrlPattern);
			});

			it('last delete button click should delete instance of TCountryCode', () => {
				cy.get(entityDeleteButtonSelector).last().click();
				cy.getEntityDeleteDialogHeading('tCountryCode').should('exist');
				cy.get(entityConfirmDeleteButtonSelector).click();
				cy.wait('@deleteEntityRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(204);
				});
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tCountryCodePageUrlPattern);

				tCountryCode = undefined;
			});
		});
	});

	describe('new TCountryCode page', () => {
		beforeEach(() => {
			cy.visit(`${tCountryCodePageUrl}`);
			cy.get(entityCreateButtonSelector).click();
			cy.getEntityCreateUpdateHeading('TCountryCode');
		});

		it('should create an instance of TCountryCode', () => {
			cy.get(`[data-cy="countryCode"]`).type('EC').should('have.value', 'EC');

			cy.get(`[data-cy="countryName"]`).type('Paradigm Administrator users').should('have.value', 'Paradigm Administrator users');

			cy.get(`[data-cy="countryNationality"]`)
				.type('Communications modular 17(E.U.A.-17)')
				.should('have.value', 'Communications modular 17(E.U.A.-17)');

			cy.get(`[data-cy="enteredBy"]`).type('22925').should('have.value', '22925');

			cy.get(`[data-cy="enteredDate"]`).type('2024-01-09T00:21').blur().should('have.value', '2024-01-09T00:21');

			cy.get(`[data-cy="modifiedBy"]`).type('14595').should('have.value', '14595');

			cy.get(`[data-cy="modifiedDate"]`).type('2024-01-08T23:13').blur().should('have.value', '2024-01-08T23:13');

			cy.get(`[data-cy="orgCustomerType"]`).type('French').should('have.value', 'French');

			cy.get(entityCreateSaveButtonSelector).click();

			cy.wait('@postEntityRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(201);
				tCountryCode = response.body;
			});
			cy.wait('@entitiesRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(200);
			});
			cy.url().should('match', tCountryCodePageUrlPattern);
		});
	});
});
