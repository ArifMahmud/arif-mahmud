import groovy.json.JsonBuilder
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import helper.ApiPath
import helper.TaskConstant
import io.qameta.allure.Allure
import spock.lang.*

class PetTestSpec extends Specification {

    RESTClient restClient = new RESTClient(TaskConstant.testUrl)

    @Unroll("Create a pet #tag")
    def 'Create a pet'() {

        when:
        def requestBody =
                [
                        "name"     : petName,
                        "id"       : petId,
                        "photoUrls": [photoUrls],
                        "category" : [
                                "id"  : categoryId,
                                "name": categoryName
                        ],
                        "tags"     : [
                                [
                                        "id"  : tagId,
                                        "name": tagName
                                ]
                        ],
                        "status"   : petStatus

                ]

        println "request: " + new JsonBuilder(requestBody).toPrettyString()

        def response
        try {

            response = restClient.post(path: ApiPath.createPet,
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

        where:
        tag                                          | petName  | petId | categoryName | categoryId | tagName   | tagId | petStatus   | photoUrls     || responseStatus
        "with valid data for all parameter"          | "Tiger"  | 301   | "testpet"    | 301        | "testtag" | 301   | "available" | "testurl.com" || 200
        "with invalid input for pet ID"              | "Tiger"  | "abc" | "testpet"    | 301        | "testtag" | 301   | "available" | "testurl.com" || 405
        "with invalid input for availability status" | "Tiger"  | 302   | "testpet"    | 301        | "testtag" | 301   | "none"      | "testurl.com" || 405
        "with an ID which belongs to another pet"    | "Tiger1" | 301   | "testpet"    | 301        | "testtag" | 301   | "available" | "testurl.com" || 405
        "with mandatory parameters only"             | "Tiger2" | null  | ""           | null       | ""        | null  | ""          | "testurl.com" || 200
        "without mandatory parameters"               | ""       | null  | ""           | null       | ""        | null  | ""          | ""            || 405
    }

    @Unroll("Update a pet #tag")
    def 'Update a pet with ID'() {

        when:
        def requestBody =
                [
                        "name"     : petName,
                        "id"       : petId,
                        "photoUrls": [""],
                        "category" : [
                                "id"  : categoryId,
                                "name": categoryName
                        ],
                        "tags"     : [
                                [
                                        "id"  : tagId,
                                        "name": tagName
                                ]
                        ],
                        "status"   : petStatus

                ]

        println "request: " + new JsonBuilder(requestBody).toPrettyString()

        def response
        try {

            response = restClient.put(path: ApiPath.updatePetById,
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

        where:
        tag                                       | petName         | petId | categoryName     | categoryId | tagName   | tagId | petStatus   | photoUrls     || responseStatus
        "name"                                    | "Tiger Updated" | 301   | "testpet"        | 301        | "testtag" | 301   | "available" | "testurl.com" || 200
        "which does not exist"                    | "Tiger"         | 3001  | "testpet"        | 301        | "testtag" | 301   | "available" | "testurl.com" || 405
        "with an ID which belongs to another pet" | "Tiger1"        | 301   | "testpet"        | 301        | "testtag" | 301   | "available" | "testurl.com" || 405
        "with mandatory parameters ID only"       | "Tiger2"        | 301   | "testpetupdated" | null       | ""        | null  | ""          | "testurl.com" || 200
        "without mandatory parameters"            | "Tiger3"        | null  | ""               | null       | ""        | null  | ""          | ""            || 405
    }

    @Unroll("Get a pet (ID) #tag")
    def 'Get a pet with specific ID'() {

        when:
        def response
        try {

            response = restClient.get(path: ApiPath.getPetById + petId)
        } catch (HttpResponseException ex) {
            // default failure handler throws an exception:
            println "error response: ${ex.statusCode}"
            response = ex.response
        }
        Allure.addDescription("response:\n" + new JsonBuilder(response.responseData).toPrettyString())

        then:
        response.status == responseStatus

        where:
        tag                                   | petId  || responseStatus
        "with valid pet ID"                   | 301    || 200
        "with invalid pet ID"                 | "abc"  || 400
        "with a pet ID which does not exist" | 300011 || 404
    }

    @Unroll("Get a pet (status) #tag")
    def 'Get a pet with status'() {

        when:
        def response
        try {

            response = restClient.get(path: ApiPath.getPetByStatus, query: ['status': petStatus])
        } catch (HttpResponseException ex) {
            // default failure handler throws an exception:
            println "error response: ${ex.statusCode}"
            response = ex.response
        }
        Allure.addDescription("response:\n" + new JsonBuilder(response.responseData).toPrettyString())

        then:
        response.status == responseStatus

        where:
        tag                                          | petStatus   || responseStatus
        "with valid availability status - sold"      | "sold"      || 200
        "with valid availability status - available" | "available" || 200
        "with valid availability status - pending"   | "pending"   || 200
        "with invalid availability status"           | "none"      || 400
    }

    @Unroll("Get a pet (tag) #tag")
    def 'Get a pet with tag'() {

        when:
        def response
        try {

            response = restClient.get(path: ApiPath.getPetByTag, query: ['tags': petTagName])
        } catch (HttpResponseException ex) {
            // default failure handler throws an exception:
            println "error response: ${ex.statusCode}"
            response = ex.response
        }
        Allure.addDescription("response:\n" + new JsonBuilder(response.responseData).toPrettyString())

        then:
        response.status == responseStatus

        where:
        tag                     | petTagName      || responseStatus
        "with valid tag name"   | "testtag"       || 200
        "with invalid tag name" | "notag_invalid" || 400
    }

    @Unroll("Delete a pet #tag")
    def 'Delete a pet with ID'() {

        when:
        def response
        try {

            response = restClient.delete(path: ApiPath.deletePetById + petId)
        } catch (HttpResponseException ex) {
            // default failure handler throws an exception:
            println "error response: ${ex.statusCode}"
            response = ex.response
        }
        Allure.addDescription("response:\n" + new JsonBuilder(response.responseData).toPrettyString())

        then:
        response.status == responseStatus

        where:
        tag                                 | petId || responseStatus
        "with valid pet ID"                 | 301   || 200
        "with invalid input of pet ID"      | "abc" || 400
        "with a pet ID which doesn't exist" | 30001 || 400
    }
}
