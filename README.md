# Shopping List Service
A RESTful service to create, retrieve, update and delete shopping items

### REST api endpoints

    POST /items         # creates new item
    GET /items          # lists all items
    PUT /items/12       # updates item #12
    DELETE /items/5     # deletes item #5

#### Data to transfer on POST and PUT

    {
      "title": "My new item"
    }
