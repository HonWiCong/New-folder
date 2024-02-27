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

describe('TStateCode e2e test', () => {
	const tStateCodePageUrl = '/t-state-code';
	const tStateCodePageUrlPattern = new RegExp('/t-state-code(\\?.*)?$');
	const username = Cypress.env('E2E_USERNAME') ?? 'user';
	const password = Cypress.env('E2E_PASSWORD') ?? 'user';
	const tStateCodeSample = {};

	let tStateCode;

	beforeEach(() => {
		cy.login(username, password);
	});

	beforeEach(() => {
		cy.intercept('GET', '/api/t-state-codes+(?*|)').as('entitiesRequest');
		cy.intercept('POST', '/api/t-state-codes').as('postEntityRequest');
		cy.intercept('DELETE', '/api/t-state-codes/*').as('deleteEntityRequest');
	});

	afterEach(() => {
		if (tStateCode) {
			cy.authenticatedRequest({
				method: 'DELETE',
				url: `/api/t-state-codes/${tStateCode.id}`,
			}).then(() => {
				tStateCode = undefined;
			});
		}
	});

	it('TStateCodes menu should load TStateCodes page', () => {
		cy.visit('/');
		cy.clickOnEntityMenuItem('t-state-code');
		cy.wait('@entitiesRequest').then(({ response }) => {
			if (response.body.length === 0) {
				cy.get(entityTableSelector).should('not.exist');
			} else {
				cy.get(entityTableSelector).should('exist');
			}
		});
		cy.getEntityHeading('TStateCode').should('exist');
		cy.url().should('match', tStateCodePageUrlPattern);
	});

	describe('TStateCode page', () => {
		describe('create button click', () => {
			beforeEach(() => {
				cy.visit(tStateCodePageUrl);
				cy.wait('@entitiesRequest');
			});

			it('should load create TStateCode page', () => {
				cy.get(entityCreateButtonSelector).click();
				cy.url().should('match', new RegExp('/t-state-code/new$'));
				cy.getEntityCreateUpdateHeading('TStateCode');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tStateCodePageUrlPattern);
			});
		});

		describe('with existing value', () => {
			beforeEach(() => {
				cy.authenticatedRequest({
					method: 'POST',
					url: '/api/t-state-codes',
					body: tStateCodeSample,
				}).then(({ body }) => {
					tStateCode = body;

					cy.intercept(
						{
							method: 'GET',
							url: '/api/t-state-codes+(?*|)',
							times: 1,
						},
						{
							statusCode: 200,
							headers: {
								link: '<http://localhost/api/t-state-codes?page=0&size=20>; rel="last",<http://localhost/api/t-state-codes?page=0&size=20>; rel="first"',
							},
							body: [tStateCode],
						}
					).as('entitiesRequestInternal');
				});

				cy.visit(tStateCodePageUrl);

				cy.wait('@entitiesRequestInternal');
			});

			it('detail button click should load details TStateCode page', () => {
				cy.get(entityDetailsButtonSelector).first().click();
				cy.getEntityDetailsHeading('tStateCode');
				cy.get(entityDetailsBackButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tStateCodePageUrlPattern);
			});

			it('edit button click should load edit TStateCode page and go back', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TStateCode');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tStateCodePageUrlPattern);
			});

			it.skip('edit button click should load edit TStateCode page and save', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TStateCode');
				cy.get(entityCreateSaveButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tStateCodePageUrlPattern);
			});

			it('last delete button click should delete instance of TStateCode', () => {
				cy.get(entityDeleteButtonSelector).last().click();
				cy.getEntityDeleteDialogHeading('tStateCode').should('exist');
				cy.get(entityConfirmDeleteButtonSelector).click();
				cy.wait('@deleteEntityRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(204);
				});
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tStateCodePageUrlPattern);

				tStateCode = undefined;
			});
		});
	});

	describe('new TStateCode page', () => {
		beforeEach(() => {
			cy.visit(`${tStateCodePageUrl}`);
			cy.get(entityCreateButtonSelector).click();
			cy.getEntityCreateUpdateHeading('TStateCode');
		});

		it('should create an instance of TStateCode', () => {
			cy.get(`[data-cy="stateName"]`).type('User').should('have.value', 'User');

			cy.get(`[data-cy="stateCode"]`).type('Keyboard Internal RAM').should('have.value', 'Keyboard Internal RAM');

			cy.get(`[data-cy="enteredBy"]`).type('63965').should('have.value', '63965');

			cy.get(`[data-cy="enteredDate"]`).type('2024-01-09T05:18').blur().should('have.value', '2024-01-09T05:18');

			cy.get(`[data-cy="modifiedBy"]`).type('44722').should('have.value', '44722');

			cy.get(`[data-cy="modifiedDate"]`).type('2024-01-09T06:17').blur().should('have.value', '2024-01-09T06:17');

			cy.get(entityCreateSaveButtonSelector).click();

			cy.wait('@postEntityRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(201);
				tStateCode = response.body;
			});
			cy.wait('@entitiesRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(200);
			});
			cy.url().should('match', tStateCodePageUrlPattern);
		});
	});
});
