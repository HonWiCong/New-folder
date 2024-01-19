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

describe('TSectionCode e2e test', () => {
	const tSectionCodePageUrl = '/t-section-code';
	const tSectionCodePageUrlPattern = new RegExp('/t-section-code(\\?.*)?$');
	const username = Cypress.env('E2E_USERNAME') ?? 'user';
	const password = Cypress.env('E2E_PASSWORD') ?? 'user';
	const tSectionCodeSample = {};

	let tSectionCode;

	beforeEach(() => {
		cy.login(username, password);
	});

	beforeEach(() => {
		cy.intercept('GET', '/api/t-section-codes+(?*|)').as('entitiesRequest');
		cy.intercept('POST', '/api/t-section-codes').as('postEntityRequest');
		cy.intercept('DELETE', '/api/t-section-codes/*').as('deleteEntityRequest');
	});

	afterEach(() => {
		if (tSectionCode) {
			cy.authenticatedRequest({
				method: 'DELETE',
				url: `/api/t-section-codes/${tSectionCode.id}`,
			}).then(() => {
				tSectionCode = undefined;
			});
		}
	});

	it('TSectionCodes menu should load TSectionCodes page', () => {
		cy.visit('/');
		cy.clickOnEntityMenuItem('t-section-code');
		cy.wait('@entitiesRequest').then(({ response }) => {
			if (response.body.length === 0) {
				cy.get(entityTableSelector).should('not.exist');
			} else {
				cy.get(entityTableSelector).should('exist');
			}
		});
		cy.getEntityHeading('TSectionCode').should('exist');
		cy.url().should('match', tSectionCodePageUrlPattern);
	});

	describe('TSectionCode page', () => {
		describe('create button click', () => {
			beforeEach(() => {
				cy.visit(tSectionCodePageUrl);
				cy.wait('@entitiesRequest');
			});

			it('should load create TSectionCode page', () => {
				cy.get(entityCreateButtonSelector).click();
				cy.url().should('match', new RegExp('/t-section-code/new$'));
				cy.getEntityCreateUpdateHeading('TSectionCode');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tSectionCodePageUrlPattern);
			});
		});

		describe('with existing value', () => {
			beforeEach(() => {
				cy.authenticatedRequest({
					method: 'POST',
					url: '/api/t-section-codes',
					body: tSectionCodeSample,
				}).then(({ body }) => {
					tSectionCode = body;

					cy.intercept(
						{
							method: 'GET',
							url: '/api/t-section-codes+(?*|)',
							times: 1,
						},
						{
							statusCode: 200,
							body: [tSectionCode],
						}
					).as('entitiesRequestInternal');
				});

				cy.visit(tSectionCodePageUrl);

				cy.wait('@entitiesRequestInternal');
			});

			it('detail button click should load details TSectionCode page', () => {
				cy.get(entityDetailsButtonSelector).first().click();
				cy.getEntityDetailsHeading('tSectionCode');
				cy.get(entityDetailsBackButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tSectionCodePageUrlPattern);
			});

			it('edit button click should load edit TSectionCode page and go back', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TSectionCode');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tSectionCodePageUrlPattern);
			});

			it('edit button click should load edit TSectionCode page and save', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TSectionCode');
				cy.get(entityCreateSaveButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tSectionCodePageUrlPattern);
			});

			it('last delete button click should delete instance of TSectionCode', () => {
				cy.get(entityDeleteButtonSelector).last().click();
				cy.getEntityDeleteDialogHeading('tSectionCode').should('exist');
				cy.get(entityConfirmDeleteButtonSelector).click();
				cy.wait('@deleteEntityRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(204);
				});
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tSectionCodePageUrlPattern);

				tSectionCode = undefined;
			});
		});
	});

	describe('new TSectionCode page', () => {
		beforeEach(() => {
			cy.visit(`${tSectionCodePageUrl}`);
			cy.get(entityCreateButtonSelector).click();
			cy.getEntityCreateUpdateHeading('TSectionCode');
		});

		it('should create an instance of TSectionCode', () => {
			cy.get(`[data-cy="sectName"]`).type('infomediaries').should('have.value', 'infomediaries');

			cy.get(`[data-cy="sectHeadid"]`).type('31263').should('have.value', '31263');

			cy.get(`[data-cy="sectActingHeadid"]`).type('71944').should('have.value', '71944');

			cy.get(`[data-cy="sectSainsSubsidiary"]`).select('S');

			cy.get(`[data-cy="sectHrpayId"]`).type('Account Soap').should('have.value', 'Account Soap');

			cy.get(`[data-cy="sectPapId"]`).type('invoice').should('have.value', 'invoice');

			cy.get(`[data-cy="sectPapCode"]`).type('solid').should('have.value', 'solid');

			cy.get(`[data-cy="sectPapCompany"]`).type('engineer HDD Soap').should('have.value', 'engineer HDD Soap');

			cy.get(`[data-cy="sectPapActive"]`).select('A');

			cy.get(`[data-cy="sectCcCode"]`).type('Sudanese payment XSS').should('have.value', 'Sudanese payment XSS');

			cy.get(`[data-cy="enteredBy"]`).type('91726').should('have.value', '91726');

			cy.get(`[data-cy="enteredDate"]`).type('2024-01-17T05:42').blur().should('have.value', '2024-01-17T05:42');

			cy.get(`[data-cy="modifiedBy"]`).type('86393').should('have.value', '86393');

			cy.get(`[data-cy="modifiedDate"]`).type('2024-01-17T06:18').blur().should('have.value', '2024-01-17T06:18');

			cy.get(entityCreateSaveButtonSelector).click();

			cy.wait('@postEntityRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(201);
				tSectionCode = response.body;
			});
			cy.wait('@entitiesRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(200);
			});
			cy.url().should('match', tSectionCodePageUrlPattern);
		});
	});
});
