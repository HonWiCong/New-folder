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

describe('TSectorCode e2e test', () => {
  const tSectorCodePageUrl = '/t-sector-code';
  const tSectorCodePageUrlPattern = new RegExp('/t-sector-code(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const tSectorCodeSample = {};

  let tSectorCode;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/t-sector-codes+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/t-sector-codes').as('postEntityRequest');
    cy.intercept('DELETE', '/api/t-sector-codes/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (tSectorCode) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/t-sector-codes/${tSectorCode.id}`,
      }).then(() => {
        tSectorCode = undefined;
      });
    }
  });

  it('TSectorCodes menu should load TSectorCodes page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('t-sector-code');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('TSectorCode').should('exist');
    cy.url().should('match', tSectorCodePageUrlPattern);
  });

  describe('TSectorCode page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(tSectorCodePageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create TSectorCode page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/t-sector-code/new$'));
        cy.getEntityCreateUpdateHeading('TSectorCode');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tSectorCodePageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/t-sector-codes',
          body: tSectorCodeSample,
        }).then(({ body }) => {
          tSectorCode = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/t-sector-codes+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [tSectorCode],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(tSectorCodePageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details TSectorCode page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('tSectorCode');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tSectorCodePageUrlPattern);
      });

      it('edit button click should load edit TSectorCode page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TSectorCode');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tSectorCodePageUrlPattern);
      });

      it('edit button click should load edit TSectorCode page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TSectorCode');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tSectorCodePageUrlPattern);
      });

      it('last delete button click should delete instance of TSectorCode', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('tSectorCode').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tSectorCodePageUrlPattern);

        tSectorCode = undefined;
      });
    });
  });

  describe('new TSectorCode page', () => {
    beforeEach(() => {
      cy.visit(`${tSectorCodePageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('TSectorCode');
    });

    it('should create an instance of TSectorCode', () => {
      cy.get(`[data-cy="sectorName"]`).type('recontextualize').should('have.value', 'recontextualize');

      cy.get(`[data-cy="sectorDescription"]`).type('maroon Niue Account').should('have.value', 'maroon Niue Account');

      cy.get(`[data-cy="enteredBy"]`).type('63373').should('have.value', '63373');

      cy.get(`[data-cy="enteredDate"]`).type('2024-01-08T10:31').blur().should('have.value', '2024-01-08T10:31');

      cy.get(`[data-cy="modifyBy"]`).type('9276').should('have.value', '9276');

      cy.get(`[data-cy="modifiedDate"]`).type('2024-01-08T07:25').blur().should('have.value', '2024-01-08T07:25');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        tSectorCode = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', tSectorCodePageUrlPattern);
    });
  });
});
