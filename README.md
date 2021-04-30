# Shopping List Service

[![Actions Status](https://github.com/julisch94/shopping-list-service/actions/workflows/test.yml/badge.svg?branch=main)](https://github.com/julisch94/shopping-list-service/actions)
![Coverage](./.github/badges/jacoco.svg)

A Spring Boot based RESTful service to create, retrieve, update and delete shopping items.

### REST api endpoints

    POST /items         # creates new item
    GET /items          # lists all items
    PUT /items/12       # updates item #12
    DELETE /items/5     # deletes item #5

#### Data to transfer on POST and PUT

    {
      "title": "My new item"
    }

#### Api responses

    200     # on successful PUT, GET
    201     # on successful POST
    204     # on successful DELETE (empty body)
    404     # on failed PUT and DELETE (when item not found)
    500     # on internal error

### Possible improvements

* Introduce Logging
* Use api versioning
* Use Swagger UI or Open Api 3 to host api documentation
