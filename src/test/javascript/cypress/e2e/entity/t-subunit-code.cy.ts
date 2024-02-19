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

describe('TSubunitCode e2e test', () => {
	const tSubunitCodePageUrl = '/t-subunit-code';
	const tSubunitCodePageUrlPattern = new RegExp('/t-subunit-code(\\?.*)?$');
	const username = Cypress.env('E2E_USERNAME') ?? 'user';
	const password = Cypress.env('E2E_PASSWORD') ?? 'user';
	const tSubunitCodeSample = {};

	let tSubunitCode;

	beforeEach(() => {
		cy.login(username, password);
	});

	beforeEach(() => {
		cy.intercept('GET', '/api/t-subunit-codes+(?*|)').as('entitiesRequest');
		cy.intercept('POST', '/api/t-subunit-codes').as('postEntityRequest');
		cy.intercept('DELETE', '/api/t-subunit-codes/*').as('deleteEntityRequest');
	});

	afterEach(() => {
		if (tSubunitCode) {
			cy.authenticatedRequest({
				method: 'DELETE',
				url: `/api/t-subunit-codes/${tSubunitCode.id}`,
			}).then(() => {
				tSubunitCode = undefined;
			});
		}
	});

	it('TSubunitCodes menu should load TSubunitCodes page', () => {
		cy.visit('/');
		cy.clickOnEntityMenuItem('t-subunit-code');
		cy.wait('@entitiesRequest').then(({ response }) => {
			if (response.body.length === 0) {
				cy.get(entityTableSelector).should('not.exist');
			} else {
				cy.get(entityTableSelector).should('exist');
			}
		});
		cy.getEntityHeading('TSubunitCode').should('exist');
		cy.url().should('match', tSubunitCodePageUrlPattern);
	});

	describe('TSubunitCode page', () => {
		describe('create button click', () => {
			beforeEach(() => {
				cy.visit(tSubunitCodePageUrl);
				cy.wait('@entitiesRequest');
			});

			it('should load create TSubunitCode page', () => {
				cy.get(entityCreateButtonSelector).click();
				cy.url().should('match', new RegExp('/t-subunit-code/new$'));
				cy.getEntityCreateUpdateHeading('TSubunitCode');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tSubunitCodePageUrlPattern);
			});
		});

		describe('with existing value', () => {
			beforeEach(() => {
				cy.authenticatedRequest({
					method: 'POST',
					url: '/api/t-subunit-codes',
					body: tSubunitCodeSample,
				}).then(({ body }) => {
					tSubunitCode = body;

					cy.intercept(
						{
							method: 'GET',
							url: '/api/t-subunit-codes+(?*|)',
							times: 1,
						},
						{
							statusCode: 200,
							body: [tSubunitCode],
						}
					).as('entitiesRequestInternal');
				});

				cy.visit(tSubunitCodePageUrl);

				cy.wait('@entitiesRequestInternal');
			});

			it('detail button click should load details TSubunitCode page', () => {
				cy.get(entityDetailsButtonSelector).first().click();
				cy.getEntityDetailsHeading('tSubunitCode');
				cy.get(entityDetailsBackButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tSubunitCodePageUrlPattern);
			});

			it('edit button click should load edit TSubunitCode page and go back', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TSubunitCode');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tSubunitCodePageUrlPattern);
			});

			it('edit button click should load edit TSubunitCode page and save', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TSubunitCode');
				cy.get(entityCreateSaveButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tSubunitCodePageUrlPattern);
			});

			it('last delete button click should delete instance of TSubunitCode', () => {
				cy.get(entityDeleteButtonSelector).last().click();
				cy.getEntityDeleteDialogHeading('tSubunitCode').should('exist');
				cy.get(entityConfirmDeleteButtonSelector).click();
				cy.wait('@deleteEntityRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(204);
				});
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tSubunitCodePageUrlPattern);

				tSubunitCode = undefined;
			});
		});
	});

	describe('new TSubunitCode page', () => {
		beforeEach(() => {
			cy.visit(`${tSubunitCodePageUrl}`);
			cy.get(entityCreateButtonSelector).click();
			cy.getEntityCreateUpdateHeading('TSubunitCode');
		});

		it('should create an instance of TSubunitCode', () => {
			cy.get(`[data-cy="subuntSubunit"]`).type('Roads').should('have.value', 'Roads');

			cy.get(`[data-cy="subuntHeadid"]`).type('2948').should('have.value', '2948');

			cy.get(`[data-cy="subuntActingHeadid"]`).type('71583').should('have.value', '71583');

			cy.get(`[data-cy="subuntHrpayId"]`).type('Pizza Dynamic primary').should('have.value', 'Pizza Dynamic primary');

			cy.get(`[data-cy="subuntPapId"]`).type('connecting').should('have.value', 'connecting');

			cy.get(`[data-cy="subuntPapCode"]`).type('Multi-chan').should('have.value', 'Multi-chan');

			cy.get(`[data-cy="subuntPapActive"]`).type('E').should('have.value', 'E');

			cy.get(`[data-cy="subunyUnitid"]`).type('Marketing ').should('have.value', 'Marketing ');

			cy.get(entityCreateSaveButtonSelector).click();

			cy.wait('@postEntityRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(201);
				tSubunitCode = response.body;
			});
			cy.wait('@entitiesRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(200);
			});
			cy.url().should('match', tSubunitCodePageUrlPattern);
		});
	});
});
