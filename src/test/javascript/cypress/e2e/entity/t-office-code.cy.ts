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

describe('TOfficeCode e2e test', () => {
	const tOfficeCodePageUrl = '/t-office-code';
	const tOfficeCodePageUrlPattern = new RegExp('/t-office-code(\\?.*)?$');
	const username = Cypress.env('E2E_USERNAME') ?? 'user';
	const password = Cypress.env('E2E_PASSWORD') ?? 'user';
	const tOfficeCodeSample = {};

	let tOfficeCode;

	beforeEach(() => {
		cy.login(username, password);
	});

	beforeEach(() => {
		cy.intercept('GET', '/api/t-office-codes+(?*|)').as('entitiesRequest');
		cy.intercept('POST', '/api/t-office-codes').as('postEntityRequest');
		cy.intercept('DELETE', '/api/t-office-codes/*').as('deleteEntityRequest');
	});

	afterEach(() => {
		if (tOfficeCode) {
			cy.authenticatedRequest({
				method: 'DELETE',
				url: `/api/t-office-codes/${tOfficeCode.id}`,
			}).then(() => {
				tOfficeCode = undefined;
			});
		}
	});

	it('TOfficeCodes menu should load TOfficeCodes page', () => {
		cy.visit('/');
		cy.clickOnEntityMenuItem('t-office-code');
		cy.wait('@entitiesRequest').then(({ response }) => {
			if (response.body.length === 0) {
				cy.get(entityTableSelector).should('not.exist');
			} else {
				cy.get(entityTableSelector).should('exist');
			}
		});
		cy.getEntityHeading('TOfficeCode').should('exist');
		cy.url().should('match', tOfficeCodePageUrlPattern);
	});

	describe('TOfficeCode page', () => {
		describe('create button click', () => {
			beforeEach(() => {
				cy.visit(tOfficeCodePageUrl);
				cy.wait('@entitiesRequest');
			});

			it('should load create TOfficeCode page', () => {
				cy.get(entityCreateButtonSelector).click();
				cy.url().should('match', new RegExp('/t-office-code/new$'));
				cy.getEntityCreateUpdateHeading('TOfficeCode');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tOfficeCodePageUrlPattern);
			});
		});

		describe('with existing value', () => {
			beforeEach(() => {
				cy.authenticatedRequest({
					method: 'POST',
					url: '/api/t-office-codes',
					body: tOfficeCodeSample,
				}).then(({ body }) => {
					tOfficeCode = body;

					cy.intercept(
						{
							method: 'GET',
							url: '/api/t-office-codes+(?*|)',
							times: 1,
						},
						{
							statusCode: 200,
							body: [tOfficeCode],
						}
					).as('entitiesRequestInternal');
				});

				cy.visit(tOfficeCodePageUrl);

				cy.wait('@entitiesRequestInternal');
			});

			it('detail button click should load details TOfficeCode page', () => {
				cy.get(entityDetailsButtonSelector).first().click();
				cy.getEntityDetailsHeading('tOfficeCode');
				cy.get(entityDetailsBackButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tOfficeCodePageUrlPattern);
			});

			it('edit button click should load edit TOfficeCode page and go back', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TOfficeCode');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tOfficeCodePageUrlPattern);
			});

			it('edit button click should load edit TOfficeCode page and save', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TOfficeCode');
				cy.get(entityCreateSaveButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tOfficeCodePageUrlPattern);
			});

			it('last delete button click should delete instance of TOfficeCode', () => {
				cy.get(entityDeleteButtonSelector).last().click();
				cy.getEntityDeleteDialogHeading('tOfficeCode').should('exist');
				cy.get(entityConfirmDeleteButtonSelector).click();
				cy.wait('@deleteEntityRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(204);
				});
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tOfficeCodePageUrlPattern);

				tOfficeCode = undefined;
			});
		});
	});

	describe('new TOfficeCode page', () => {
		beforeEach(() => {
			cy.visit(`${tOfficeCodePageUrl}`);
			cy.get(entityCreateButtonSelector).click();
			cy.getEntityCreateUpdateHeading('TOfficeCode');
		});

		it('should create an instance of TOfficeCode', () => {
			cy.get(`[data-cy="offName"]`).type('green').should('have.value', 'green');

			cy.get(`[data-cy="offAddress"]`).type('Pizza Balanced').should('have.value', 'Pizza Balanced');

			cy.get(`[data-cy="offPostcode"]`).type('protocol').should('have.value', 'protocol');

			cy.get(`[data-cy="offCity"]`).type('Turkey').should('have.value', 'Turkey');

			cy.get(`[data-cy="offState"]`).type('Bedfordshire').should('have.value', 'Bedfordshire');

			cy.get(`[data-cy="offOffphone"]`).type('Jamaica').should('have.value', 'Jamaica');

			cy.get(`[data-cy="offOfffax"]`).type('metrics Serbian').should('have.value', 'metrics Serbian');

			cy.get(`[data-cy="enteredBy"]`).type('42168').should('have.value', '42168');

			cy.get(`[data-cy="enteredDate"]`).type('2024-01-21T22:12').blur().should('have.value', '2024-01-21T22:12');

			cy.get(`[data-cy="modifiedBy"]`).type('20881').should('have.value', '20881');

			cy.get(`[data-cy="modifiedDate"]`).type('2024-01-21T16:47').blur().should('have.value', '2024-01-21T16:47');

			cy.get(entityCreateSaveButtonSelector).click();

			cy.wait('@postEntityRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(201);
				tOfficeCode = response.body;
			});
			cy.wait('@entitiesRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(200);
			});
			cy.url().should('match', tOfficeCodePageUrlPattern);
		});
	});
});
