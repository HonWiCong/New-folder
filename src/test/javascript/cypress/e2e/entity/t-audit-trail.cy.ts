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

describe('TAuditTrail e2e test', () => {
  const tAuditTrailPageUrl = '/t-audit-trail';
  const tAuditTrailPageUrlPattern = new RegExp('/t-audit-trail(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const tAuditTrailSample = {};

  let tAuditTrail;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/t-audit-trails+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/t-audit-trails').as('postEntityRequest');
    cy.intercept('DELETE', '/api/t-audit-trails/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (tAuditTrail) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/t-audit-trails/${tAuditTrail.id}`,
      }).then(() => {
        tAuditTrail = undefined;
      });
    }
  });

  it('TAuditTrails menu should load TAuditTrails page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('t-audit-trail');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('TAuditTrail').should('exist');
    cy.url().should('match', tAuditTrailPageUrlPattern);
  });

  describe('TAuditTrail page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(tAuditTrailPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create TAuditTrail page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/t-audit-trail/new$'));
        cy.getEntityCreateUpdateHeading('TAuditTrail');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tAuditTrailPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/t-audit-trails',
          body: tAuditTrailSample,
        }).then(({ body }) => {
          tAuditTrail = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/t-audit-trails+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [tAuditTrail],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(tAuditTrailPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details TAuditTrail page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('tAuditTrail');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tAuditTrailPageUrlPattern);
      });

      it('edit button click should load edit TAuditTrail page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TAuditTrail');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tAuditTrailPageUrlPattern);
      });

      it('edit button click should load edit TAuditTrail page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TAuditTrail');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tAuditTrailPageUrlPattern);
      });

      it('last delete button click should delete instance of TAuditTrail', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('tAuditTrail').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tAuditTrailPageUrlPattern);

        tAuditTrail = undefined;
      });
    });
  });

  describe('new TAuditTrail page', () => {
    beforeEach(() => {
      cy.visit(`${tAuditTrailPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('TAuditTrail');
    });

    it('should create an instance of TAuditTrail', () => {
      cy.get(`[data-cy="userId"]`).type('93768').should('have.value', '93768');

      cy.get(`[data-cy="dateTime"]`).type('2024-01-08T15:36').blur().should('have.value', '2024-01-08T15:36');

      cy.get(`[data-cy="tableName"]`).type('SDR adapter turquoise').should('have.value', 'SDR adapter turquoise');

      cy.get(`[data-cy="auditAction"]`).type('Metal Connecticut Soft').should('have.value', 'Metal Connecticut Soft');

      cy.get(`[data-cy="recordId"]`).type('45324').should('have.value', '45324');

      cy.get(`[data-cy="fieldDesc"]`).type('Rubber Account').should('have.value', 'Rubber Account');

      cy.get(`[data-cy="atStatus"]`).type('Views grey').should('have.value', 'Views grey');

      cy.get(`[data-cy="stFullDesc"]`).type('Metal Dynamic').should('have.value', 'Metal Dynamic');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        tAuditTrail = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', tAuditTrailPageUrlPattern);
    });
  });
});
