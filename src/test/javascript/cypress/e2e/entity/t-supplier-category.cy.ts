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

describe('TSupplierCategory e2e test', () => {
  const tSupplierCategoryPageUrl = '/t-supplier-category';
  const tSupplierCategoryPageUrlPattern = new RegExp('/t-supplier-category(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const tSupplierCategorySample = {};

  let tSupplierCategory;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/t-supplier-categories+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/t-supplier-categories').as('postEntityRequest');
    cy.intercept('DELETE', '/api/t-supplier-categories/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (tSupplierCategory) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/t-supplier-categories/${tSupplierCategory.id}`,
      }).then(() => {
        tSupplierCategory = undefined;
      });
    }
  });

  it('TSupplierCategories menu should load TSupplierCategories page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('t-supplier-category');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('TSupplierCategory').should('exist');
    cy.url().should('match', tSupplierCategoryPageUrlPattern);
  });

  describe('TSupplierCategory page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(tSupplierCategoryPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create TSupplierCategory page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/t-supplier-category/new$'));
        cy.getEntityCreateUpdateHeading('TSupplierCategory');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tSupplierCategoryPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/t-supplier-categories',
          body: tSupplierCategorySample,
        }).then(({ body }) => {
          tSupplierCategory = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/t-supplier-categories+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [tSupplierCategory],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(tSupplierCategoryPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details TSupplierCategory page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('tSupplierCategory');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tSupplierCategoryPageUrlPattern);
      });

      it('edit button click should load edit TSupplierCategory page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TSupplierCategory');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tSupplierCategoryPageUrlPattern);
      });

      it('edit button click should load edit TSupplierCategory page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TSupplierCategory');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tSupplierCategoryPageUrlPattern);
      });

      it('last delete button click should delete instance of TSupplierCategory', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('tSupplierCategory').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tSupplierCategoryPageUrlPattern);

        tSupplierCategory = undefined;
      });
    });
  });

  describe('new TSupplierCategory page', () => {
    beforeEach(() => {
      cy.visit(`${tSupplierCategoryPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('TSupplierCategory');
    });

    it('should create an instance of TSupplierCategory', () => {
      cy.get(`[data-cy="spcCategory"]`).type('Algerian Fantastic Avon').should('have.value', 'Algerian Fantastic Avon');

      cy.get(`[data-cy="enteredBy"]`).type('78114').should('have.value', '78114');

      cy.get(`[data-cy="enteredDate"]`).type('2024-01-08T09:05').blur().should('have.value', '2024-01-08T09:05');

      cy.get(`[data-cy="modifyBy"]`).type('70986').should('have.value', '70986');

      cy.get(`[data-cy="modifiedDate"]`).type('2024-01-09T00:07').blur().should('have.value', '2024-01-09T00:07');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        tSupplierCategory = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', tSupplierCategoryPageUrlPattern);
    });
  });
});
