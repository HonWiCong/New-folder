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

describe('TDistrictCode e2e test', () => {
  const tDistrictCodePageUrl = '/t-district-code';
  const tDistrictCodePageUrlPattern = new RegExp('/t-district-code(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const tDistrictCodeSample = {};

  let tDistrictCode;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/t-district-codes+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/t-district-codes').as('postEntityRequest');
    cy.intercept('DELETE', '/api/t-district-codes/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (tDistrictCode) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/t-district-codes/${tDistrictCode.id}`,
      }).then(() => {
        tDistrictCode = undefined;
      });
    }
  });

  it('TDistrictCodes menu should load TDistrictCodes page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('t-district-code');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('TDistrictCode').should('exist');
    cy.url().should('match', tDistrictCodePageUrlPattern);
  });

  describe('TDistrictCode page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(tDistrictCodePageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create TDistrictCode page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/t-district-code/new$'));
        cy.getEntityCreateUpdateHeading('TDistrictCode');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tDistrictCodePageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/t-district-codes',
          body: tDistrictCodeSample,
        }).then(({ body }) => {
          tDistrictCode = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/t-district-codes+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [tDistrictCode],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(tDistrictCodePageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details TDistrictCode page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('tDistrictCode');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tDistrictCodePageUrlPattern);
      });

      it('edit button click should load edit TDistrictCode page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TDistrictCode');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tDistrictCodePageUrlPattern);
      });

      it('edit button click should load edit TDistrictCode page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TDistrictCode');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tDistrictCodePageUrlPattern);
      });

      it('last delete button click should delete instance of TDistrictCode', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('tDistrictCode').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tDistrictCodePageUrlPattern);

        tDistrictCode = undefined;
      });
    });
  });

  describe('new TDistrictCode page', () => {
    beforeEach(() => {
      cy.visit(`${tDistrictCodePageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('TDistrictCode');
    });

    it('should create an instance of TDistrictCode', () => {
      cy.get(`[data-cy="disName"]`).type('architectures').should('have.value', 'architectures');

      cy.get(`[data-cy="enteredBy"]`).type('83688').should('have.value', '83688');

      cy.get(`[data-cy="enteredDate"]`).type('2024-01-08T18:57').blur().should('have.value', '2024-01-08T18:57');

      cy.get(`[data-cy="modifyBy"]`).type('44139').should('have.value', '44139');

      cy.get(`[data-cy="modifiedDate"]`).type('2024-01-08T13:53').blur().should('have.value', '2024-01-08T13:53');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        tDistrictCode = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', tDistrictCodePageUrlPattern);
    });
  });
});
