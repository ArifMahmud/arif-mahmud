import groovy.json.JsonBuilder
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import helper.ApiPath
import helper.TaskConstant
import spock.lang.*

class StoreTestSpec extends Specification {
    RESTClient restClient = new RESTClient(TaskConstant.testUrl)

    @Unroll("Get pet inventories by status #tag")
    def 'Get pet inventories by status'() {

        when:
        def response
        try {

            response = restClient.get(path: ApiPath.petInventory)
        } catch (HttpResponseException ex) {
            // default failure handler throws an exception:
            println "error response: ${ex.statusCode}"
            response = ex.response
        }
        Allure.addDescription("response:\n" + new JsonBuilder(response.responseData).toPrettyString())

        then:
        response.status == 200
    }

    @Unroll("Create an pet order #tag")
    def 'Create an pet order'() {

        when:
        def requestBody =
                [
                        "id"      : orderId,
                        "petId"   : petId,
                        "quantity": quantity,
                        "shipDate": shipDate,
                        "status"  : status,
                        "complete": completeBoolean
                ]

        println "request: " + new JsonBuilder(requestBody).toPrettyString()

        def response
        try {

            response = restClient.post(path: ApiPath.placeOrder,
                    body: requestBody,
                    requestContentType: 'application/json')
        } catch (HttpResponseException ex) {
            // default failure handler throws an exception:
            println "error response: ${ex.statusCode}"
            response = ex.response
        }

        Allure.addDescription("response:\n" + new JsonBuilder(response.responseData).toPrettyString())

        then:
        response.status == responseStatus

        //in swagger, this api definition is not proper, so I can add very limited test case and validate them
        where:
        tag                                  | orderId | petId | quantity | shipDate                   | status     | completeBoolean || responseStatus
        "with valid data for all parameters" | 901     | 301   | 2        | "2021-02-27T09:52:42.620Z" | "approved" | true            || 200
        "with blank json request"            | null    | null  | null     | ""                         | ""         | null            || 405
        "without pet ID"                     | 902     | null  | 2        | "2021-02-27T09:52:42.620Z" | "approved" | true            || 405
        "without order ID"                   | null    | 301   | 2        | "2021-02-27T09:52:42.620Z" | "approved" | true            || 405
        "with quantity 0"                    | 903     | 301   | 0        | "2021-02-27T09:52:42.620Z" | "approved" | true            || 405
        "without any shipping date"          | 904     | 301   | 1        | ""                         | "approved" | true            || 200
    }

    @Unroll("Get a pet order #tag")
    def 'Get a pet order with specific ID'() {

        when:
        def response
        try {

            response = restClient.get(path: ApiPath.store + orderId)
        } catch (HttpResponseException ex) {
            // default failure handler throws an exception:
            println "error response: ${ex.statusCode}"
            response = ex.response
        }
        Allure.addDescription("response:\n" + new JsonBuilder(response.responseData).toPrettyString())

        then:
        response.status == responseStatus

        where:
        tag                                     | orderId || responseStatus
        "with valid order ID"                   | 901     || 200
        "with invalid order ID"                 | "abc"   || 400
        "with an order ID which does not exist" | 3000011 || 404
    }

    @Unroll("Delete a pet order #tag")
    def 'Delete a pet order with specific ID'() {

        when:
        def response
        try {

            response = restClient.delete(path: ApiPath.store + orderId)
        } catch (HttpResponseException ex) {
            // default failure handler throws an exception:
            println "error response: ${ex.statusCode}"
            response = ex.response
        }
        Allure.addDescription("response:\n" + new JsonBuilder(response.responseData).toPrettyString())

        then:
        response.status == responseStatus

        where:
        tag                                   | orderId || responseStatus
        "with valid order ID"                 | 902     || 200
        "with invalid input of order ID"      | "abc"   || 400
        "with a order ID which doesn't exist" | 3000001 || 400
    }
}
