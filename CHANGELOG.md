# Changelog

## 2021.11.17

- Bootstrap project
- spring boot database connection configuration
- Value Model
- Value JPA Repository
- base Value Service
- autowire Value Service with Value Repository integration
- value service
  - find all values
  - create value
  - find by id value
- values controller resource

  - find all values route: `[GET] /api/values`

    - Response Body

      ```json
      [
        {
          "id": 1,
          "value": "teste"
        }
      ]
      ```

  - create value route: `[POST] /api/values`

    - Resquest Body

      ```json
      {
        "value": "teste"
      }
      ```

    - Response Body

      ```json
      {
        "id": 1,
        "value": "teste"
      }
      ```

  - find by id value route: `[GET] /api/values/{id}`

    - Response Body

      ```json
      {
        "id": 1,
        "value": "teste"
      }
      ```
