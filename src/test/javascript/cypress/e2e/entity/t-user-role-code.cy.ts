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

describe('TUserRoleCode e2e test', () => {
	const tUserRoleCodePageUrl = '/t-user-role-code';
	const tUserRoleCodePageUrlPattern = new RegExp('/t-user-role-code(\\?.*)?$');
	const username = Cypress.env('E2E_USERNAME') ?? 'user';
	const password = Cypress.env('E2E_PASSWORD') ?? 'user';
	const tUserRoleCodeSample = {};

	let tUserRoleCode;

	beforeEach(() => {
		cy.login(username, password);
	});

	beforeEach(() => {
		cy.intercept('GET', '/api/t-user-role-codes+(?*|)').as('entitiesRequest');
		cy.intercept('POST', '/api/t-user-role-codes').as('postEntityRequest');
		cy.intercept('DELETE', '/api/t-user-role-codes/*').as('deleteEntityRequest');
	});

	afterEach(() => {
		if (tUserRoleCode) {
			cy.authenticatedRequest({
				method: 'DELETE',
				url: `/api/t-user-role-codes/${tUserRoleCode.id}`,
			}).then(() => {
				tUserRoleCode = undefined;
			});
		}
	});

	it('TUserRoleCodes menu should load TUserRoleCodes page', () => {
		cy.visit('/');
		cy.clickOnEntityMenuItem('t-user-role-code');
		cy.wait('@entitiesRequest').then(({ response }) => {
			if (response.body.length === 0) {
				cy.get(entityTableSelector).should('not.exist');
			} else {
				cy.get(entityTableSelector).should('exist');
			}
		});
		cy.getEntityHeading('TUserRoleCode').should('exist');
		cy.url().should('match', tUserRoleCodePageUrlPattern);
	});

	describe('TUserRoleCode page', () => {
		describe('create button click', () => {
			beforeEach(() => {
				cy.visit(tUserRoleCodePageUrl);
				cy.wait('@entitiesRequest');
			});

			it('should load create TUserRoleCode page', () => {
				cy.get(entityCreateButtonSelector).click();
				cy.url().should('match', new RegExp('/t-user-role-code/new$'));
				cy.getEntityCreateUpdateHeading('TUserRoleCode');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tUserRoleCodePageUrlPattern);
			});
		});

		describe('with existing value', () => {
			beforeEach(() => {
				cy.authenticatedRequest({
					method: 'POST',
					url: '/api/t-user-role-codes',
					body: tUserRoleCodeSample,
				}).then(({ body }) => {
					tUserRoleCode = body;

					cy.intercept(
						{
							method: 'GET',
							url: '/api/t-user-role-codes+(?*|)',
							times: 1,
						},
						{
							statusCode: 200,
							body: [tUserRoleCode],
						}
					).as('entitiesRequestInternal');
				});

				cy.visit(tUserRoleCodePageUrl);

				cy.wait('@entitiesRequestInternal');
			});

			it('detail button click should load details TUserRoleCode page', () => {
				cy.get(entityDetailsButtonSelector).first().click();
				cy.getEntityDetailsHeading('tUserRoleCode');
				cy.get(entityDetailsBackButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tUserRoleCodePageUrlPattern);
			});

			it('edit button click should load edit TUserRoleCode page and go back', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TUserRoleCode');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tUserRoleCodePageUrlPattern);
			});

			it('edit button click should load edit TUserRoleCode page and save', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TUserRoleCode');
				cy.get(entityCreateSaveButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tUserRoleCodePageUrlPattern);
			});

			it('last delete button click should delete instance of TUserRoleCode', () => {
				cy.get(entityDeleteButtonSelector).last().click();
				cy.getEntityDeleteDialogHeading('tUserRoleCode').should('exist');
				cy.get(entityConfirmDeleteButtonSelector).click();
				cy.wait('@deleteEntityRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(204);
				});
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tUserRoleCodePageUrlPattern);

				tUserRoleCode = undefined;
			});
		});
	});

	describe('new TUserRoleCode page', () => {
		beforeEach(() => {
			cy.visit(`${tUserRoleCodePageUrl}`);
			cy.get(entityCreateButtonSelector).click();
			cy.getEntityCreateUpdateHeading('TUserRoleCode');
		});

		it('should create an instance of TUserRoleCode', () => {
			cy.get(`[data-cy="roleName"]`).type('innovate').should('have.value', 'innovate');

			cy.get(`[data-cy="roleHead"]`).type('redundant').should('have.value', 'redundant');

			cy.get(`[data-cy="actApprover1"]`).type('40407').should('have.value', '40407');

			cy.get(`[data-cy="actApprover2"]`).type('88914').should('have.value', '88914');

			cy.get(`[data-cy="enteredBy"]`).type('82487').should('have.value', '82487');

			cy.get(`[data-cy="enteredDate"]`).type('2024-01-16T23:38').blur().should('have.value', '2024-01-16T23:38');

			cy.get(`[data-cy="modifiedBy"]`).type('558').should('have.value', '558');

			cy.get(`[data-cy="modifiedDate"]`).type('2024-01-16T21:26').blur().should('have.value', '2024-01-16T21:26');

			cy.get(entityCreateSaveButtonSelector).click();

			cy.wait('@postEntityRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(201);
				tUserRoleCode = response.body;
			});
			cy.wait('@entitiesRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(200);
			});
			cy.url().should('match', tUserRoleCodePageUrlPattern);
		});
	});
});
