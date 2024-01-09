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

describe('TDivisionCode e2e test', () => {
  const tDivisionCodePageUrl = '/t-division-code';
  const tDivisionCodePageUrlPattern = new RegExp('/t-division-code(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const tDivisionCodeSample = {};

  let tDivisionCode;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/t-division-codes+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/t-division-codes').as('postEntityRequest');
    cy.intercept('DELETE', '/api/t-division-codes/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (tDivisionCode) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/t-division-codes/${tDivisionCode.id}`,
      }).then(() => {
        tDivisionCode = undefined;
      });
    }
  });

  it('TDivisionCodes menu should load TDivisionCodes page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('t-division-code');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('TDivisionCode').should('exist');
    cy.url().should('match', tDivisionCodePageUrlPattern);
  });

  describe('TDivisionCode page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(tDivisionCodePageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create TDivisionCode page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/t-division-code/new$'));
        cy.getEntityCreateUpdateHeading('TDivisionCode');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tDivisionCodePageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/t-division-codes',
          body: tDivisionCodeSample,
        }).then(({ body }) => {
          tDivisionCode = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/t-division-codes+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [tDivisionCode],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(tDivisionCodePageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details TDivisionCode page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('tDivisionCode');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tDivisionCodePageUrlPattern);
      });

      it('edit button click should load edit TDivisionCode page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TDivisionCode');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tDivisionCodePageUrlPattern);
      });

      it('edit button click should load edit TDivisionCode page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TDivisionCode');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tDivisionCodePageUrlPattern);
      });

      it('last delete button click should delete instance of TDivisionCode', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('tDivisionCode').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tDivisionCodePageUrlPattern);

        tDivisionCode = undefined;
      });
    });
  });

  describe('new TDivisionCode page', () => {
    beforeEach(() => {
      cy.visit(`${tDivisionCodePageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('TDivisionCode');
    });

    it('should create an instance of TDivisionCode', () => {
      cy.get(`[data-cy="divName"]`).type('withdrawal').should('have.value', 'withdrawal');

      cy.get(`[data-cy="enteredBy"]`).type('41845').should('have.value', '41845');

      cy.get(`[data-cy="enteredDate"]`).type('2024-01-09T00:56').blur().should('have.value', '2024-01-09T00:56');

      cy.get(`[data-cy="modifyBy"]`).type('4092').should('have.value', '4092');

      cy.get(`[data-cy="modifiedDate"]`).type('2024-01-08T09:48').blur().should('have.value', '2024-01-08T09:48');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        tDivisionCode = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', tDivisionCodePageUrlPattern);
    });
  });
});
