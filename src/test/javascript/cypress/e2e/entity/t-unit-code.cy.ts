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

describe('TUnitCode e2e test', () => {
	const tUnitCodePageUrl = '/t-unit-code';
	const tUnitCodePageUrlPattern = new RegExp('/t-unit-code(\\?.*)?$');
	const username = Cypress.env('E2E_USERNAME') ?? 'user';
	const password = Cypress.env('E2E_PASSWORD') ?? 'user';
	const tUnitCodeSample = {};

	let tUnitCode;

	beforeEach(() => {
		cy.login(username, password);
	});

	beforeEach(() => {
		cy.intercept('GET', '/api/t-unit-codes+(?*|)').as('entitiesRequest');
		cy.intercept('POST', '/api/t-unit-codes').as('postEntityRequest');
		cy.intercept('DELETE', '/api/t-unit-codes/*').as('deleteEntityRequest');
	});

	afterEach(() => {
		if (tUnitCode) {
			cy.authenticatedRequest({
				method: 'DELETE',
				url: `/api/t-unit-codes/${tUnitCode.id}`,
			}).then(() => {
				tUnitCode = undefined;
			});
		}
	});

	it('TUnitCodes menu should load TUnitCodes page', () => {
		cy.visit('/');
		cy.clickOnEntityMenuItem('t-unit-code');
		cy.wait('@entitiesRequest').then(({ response }) => {
			if (response.body.length === 0) {
				cy.get(entityTableSelector).should('not.exist');
			} else {
				cy.get(entityTableSelector).should('exist');
			}
		});
		cy.getEntityHeading('TUnitCode').should('exist');
		cy.url().should('match', tUnitCodePageUrlPattern);
	});

	describe('TUnitCode page', () => {
		describe('create button click', () => {
			beforeEach(() => {
				cy.visit(tUnitCodePageUrl);
				cy.wait('@entitiesRequest');
			});

			it('should load create TUnitCode page', () => {
				cy.get(entityCreateButtonSelector).click();
				cy.url().should('match', new RegExp('/t-unit-code/new$'));
				cy.getEntityCreateUpdateHeading('TUnitCode');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tUnitCodePageUrlPattern);
			});
		});

		describe('with existing value', () => {
			beforeEach(() => {
				cy.authenticatedRequest({
					method: 'POST',
					url: '/api/t-unit-codes',
					body: tUnitCodeSample,
				}).then(({ body }) => {
					tUnitCode = body;

					cy.intercept(
						{
							method: 'GET',
							url: '/api/t-unit-codes+(?*|)',
							times: 1,
						},
						{
							statusCode: 200,
							body: [tUnitCode],
						}
					).as('entitiesRequestInternal');
				});

				cy.visit(tUnitCodePageUrl);

				cy.wait('@entitiesRequestInternal');
			});

			it('detail button click should load details TUnitCode page', () => {
				cy.get(entityDetailsButtonSelector).first().click();
				cy.getEntityDetailsHeading('tUnitCode');
				cy.get(entityDetailsBackButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tUnitCodePageUrlPattern);
			});

			it('edit button click should load edit TUnitCode page and go back', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TUnitCode');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tUnitCodePageUrlPattern);
			});

			it('edit button click should load edit TUnitCode page and save', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TUnitCode');
				cy.get(entityCreateSaveButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tUnitCodePageUrlPattern);
			});

			it('last delete button click should delete instance of TUnitCode', () => {
				cy.get(entityDeleteButtonSelector).last().click();
				cy.getEntityDeleteDialogHeading('tUnitCode').should('exist');
				cy.get(entityConfirmDeleteButtonSelector).click();
				cy.wait('@deleteEntityRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(204);
				});
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tUnitCodePageUrlPattern);

				tUnitCode = undefined;
			});
		});
	});

	describe('new TUnitCode page', () => {
		beforeEach(() => {
			cy.visit(`${tUnitCodePageUrl}`);
			cy.get(entityCreateButtonSelector).click();
			cy.getEntityCreateUpdateHeading('TUnitCode');
		});

		it('should create an instance of TUnitCode', () => {
			cy.get(`[data-cy="untUnit"]`).type('service-desk').should('have.value', 'service-desk');

			cy.get(`[data-cy="untHeadid"]`).type('74564').should('have.value', '74564');

			cy.get(`[data-cy="untActingHeadid"]`).type('93048').should('have.value', '93048');

			cy.get(`[data-cy="untHrpayId"]`).type('magnetic Frozen').should('have.value', 'magnetic Frozen');

			cy.get(`[data-cy="untPapId"]`).type('Legacy').should('have.value', 'Legacy');

			cy.get(`[data-cy="untPapCode"]`).type('sensor Con').should('have.value', 'sensor Con');

			cy.get(`[data-cy="untPapActive"]`).type('U').should('have.value', 'U');

			cy.get(`[data-cy="untPapDepartmentid"]`).type('Lilangeni').should('have.value', 'Lilangeni');

			cy.get(`[data-cy="enteredBy"]`).type('90864').should('have.value', '90864');

			cy.get(`[data-cy="enteredDate"]`).type('2024-01-21T16:42').blur().should('have.value', '2024-01-21T16:42');

			cy.get(`[data-cy="modifiedBy"]`).type('29187').should('have.value', '29187');

			cy.get(`[data-cy="modifiedDate"]`).type('2024-01-21T04:17').blur().should('have.value', '2024-01-21T04:17');

			cy.get(entityCreateSaveButtonSelector).click();

			cy.wait('@postEntityRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(201);
				tUnitCode = response.body;
			});
			cy.wait('@entitiesRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(200);
			});
			cy.url().should('match', tUnitCodePageUrlPattern);
		});
	});
});
