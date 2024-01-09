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

describe('TTitleCode e2e test', () => {
  const tTitleCodePageUrl = '/t-title-code';
  const tTitleCodePageUrlPattern = new RegExp('/t-title-code(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const tTitleCodeSample = {};

  let tTitleCode;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/t-title-codes+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/t-title-codes').as('postEntityRequest');
    cy.intercept('DELETE', '/api/t-title-codes/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (tTitleCode) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/t-title-codes/${tTitleCode.id}`,
      }).then(() => {
        tTitleCode = undefined;
      });
    }
  });

  it('TTitleCodes menu should load TTitleCodes page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('t-title-code');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('TTitleCode').should('exist');
    cy.url().should('match', tTitleCodePageUrlPattern);
  });

  describe('TTitleCode page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(tTitleCodePageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create TTitleCode page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/t-title-code/new$'));
        cy.getEntityCreateUpdateHeading('TTitleCode');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tTitleCodePageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/t-title-codes',
          body: tTitleCodeSample,
        }).then(({ body }) => {
          tTitleCode = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/t-title-codes+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [tTitleCode],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(tTitleCodePageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details TTitleCode page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('tTitleCode');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tTitleCodePageUrlPattern);
      });

      it('edit button click should load edit TTitleCode page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TTitleCode');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tTitleCodePageUrlPattern);
      });

      it('edit button click should load edit TTitleCode page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TTitleCode');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tTitleCodePageUrlPattern);
      });

      it('last delete button click should delete instance of TTitleCode', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('tTitleCode').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tTitleCodePageUrlPattern);

        tTitleCode = undefined;
      });
    });
  });

  describe('new TTitleCode page', () => {
    beforeEach(() => {
      cy.visit(`${tTitleCodePageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('TTitleCode');
    });

    it('should create an instance of TTitleCode', () => {
      cy.get(`[data-cy="ttTitle"]`)
        .type('transparent Buckinghamshire revolutionary')
        .should('have.value', 'transparent Buckinghamshire revolutionary');

      cy.get(`[data-cy="enteredBy"]`).type('23700').should('have.value', '23700');

      cy.get(`[data-cy="enteredDate"]`).type('2024-01-08T15:30').blur().should('have.value', '2024-01-08T15:30');

      cy.get(`[data-cy="modifiedBy"]`).type('60337').should('have.value', '60337');

      cy.get(`[data-cy="modifiedDate"]`).type('2024-01-08T23:41').blur().should('have.value', '2024-01-08T23:41');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        tTitleCode = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', tTitleCodePageUrlPattern);
    });
  });
});
