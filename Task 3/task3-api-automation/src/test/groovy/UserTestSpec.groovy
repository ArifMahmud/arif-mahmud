import groovy.json.JsonBuilder
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import helper.ApiPath
import helper.TaskConstant
import spock.lang.*

class UserTestSpec extends Specification {
    RESTClient restClient = new RESTClient(TaskConstant.testUrl)

    @Unroll("Create an user #tag")
    def 'Create an user'() {

        when:
        def requestBody =
                [
                        "id"        : userId,
                        "username"  : userName,
                        "firstName" : userFirstName,
                        "lastName"  : userLastName,
                        "email"     : userEmail,
                        "password"  : userPassword,
                        "phone"     : userPhone,
                        "userStatus": userStatus
                ]

        println "request: " + new JsonBuilder(requestBody).toPrettyString()

        def response
        try {

            response = restClient.post(path: ApiPath.createUser,
                    body: requestBody,
                    requestContentType: 'application/json')
        } catch (HttpResponseException ex) {
            // default failure handler throws an exception:
            println "error response: ${ex.statusCode}"
            response = ex.response
        }

        println "response: " + new JsonBuilder(response.responseData).toPrettyString()

        then:
        response.status == responseStatus

        //in swagger, this api definition is not proper, so I can add very limited test case and validate them
        where:
        tag                                 | userId | userName         | userFirstName | userLastName | userEmail       | userPhone     | userPassword | userStatus || responseStatus
        "with valid data for all parameter" | 1001   | "testuserprince" | "arif"        | "mahmud"     | "arif@test.com" | "01210000000" | "123456"     | 1          || 200
        "without username and password"     | 1002   | ""               | "arif"        | "mahmud"     | "arif@test.com" | "01210000000" | ""           | 1          || 400
    }

    @Unroll("Create multiple users #tag")
    def 'Create multiple users in list'() {

        when:
        def requestBody =
                [
                        [
                                "id"        : userId,
                                "username"  : userName,
                                "firstName" : userFirstName,
                                "lastName"  : userLastName,
                                "email"     : userEmail,
                                "password"  : userPassword,
                                "phone"     : userPhone,
                                "userStatus": userStatus],
                        [
                                "id"        : userId + 1,
                                "username"  : userName + '2',
                                "firstName" : userFirstName + '2',
                                "lastName"  : userLastName + '2',
                                "email"     : userEmail,
                                "password"  : userPassword,
                                "phone"     : userPhone,
                                "userStatus": userStatus]
                ]

        println "request: " + new JsonBuilder(requestBody).toPrettyString()

        def response
        try {

            response = restClient.post(path: ApiPath.createMultipleUsers,
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
        tag                             | userId | userName | userFirstName | userLastName | userEmail      | userPhone     | userPassword | userStatus || responseStatus
        "valid data for all parameter"  | 1003   | "jui"    | "ismot"       | "johora"     | "jui@test.com" | "01310555555" | "123456"     | 1          || 200
        "without username and password" | 1003   | ""       | "ismot"       | "johora"     | "jui@test.com" | "01310555555" | ""           | 1          || 400
    }

    @Unroll("Update an user with #tag")
    def 'Update an user'() {

        when:
        def requestBody =
                [
                        "id"        : userId,
                        "username"  : userName,
                        "firstName" : userFirstName,
                        "lastName"  : userLastName,
                        "email"     : userEmail,
                        "password"  : userPassword,
                        "phone"     : userPhone,
                        "userStatus": userStatus
                ]

        println "request: " + new JsonBuilder(requestBody).toPrettyString()

        def response
        try {

            response = restClient.put(path: ApiPath.updateUser + userName,
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
        tag          | userId | userName | userFirstName | userLastName | userEmail | userPhone | userPassword | userStatus || responseStatus
        "valid data" | 100    | ""       | "Arif"        | 1            | ""        | 1         | ""           | ""         || 200
    }

    @Unroll("User login #tag")
    def 'User login'() {

        when:
        def response
        try {

            response = restClient.get(path: ApiPath.userLogIn, query: ['username': userName, 'password': userPassword])
        } catch (HttpResponseException ex) {
            // default failure handler throws an exception:
            println "error response: ${ex.statusCode}"
            response = ex.response
        }
        Allure.addDescription("response:\n" + new JsonBuilder(response.responseData).toPrettyString())

        then:
        response.status == responseStatus

        where:
        tag                                        | userName        | userPassword || responseStatus
        "with valid username and valid password"   | "jui"           | "123456"     || 200
        "without username/password"                | "jui"           | ""           || 400
        "with valid username and invalid password" | "jui"           | "12345"      || 400
        "with invalid username/user doesn't exist" | "userisinvalid" | "12345"      || 400
    }

    @Unroll("Get an user #tag")
    def 'Get an user by name'() {

        when:
        def response
        println(ApiPath.getUserByName + userName)
        try {

            response = restClient.get(path: ApiPath.getUserByName + userName)
        } catch (HttpResponseException ex) {
            // default failure handler throws an exception:
            println "error response: ${ex.statusCode}"
            response = ex.response
        }
        Allure.addDescription("response:\n" + new JsonBuilder(response.responseData).toPrettyString())

        then:
        response.status == responseStatus

        where:
        tag                                    | userName           || responseStatus
        "with valid username"                  | "jui"              || 200
        "with invalid username"                | 12345              || 400
        "with an username which doesn't exist" | "notexistusername" || 404
    }

    @Unroll("Delete an user #tag")
    def 'Delete an user'() {

        when:
        def response
        try {

            response = restClient.delete(path: ApiPath.deleteUser + userName)
        } catch (HttpResponseException ex) {
            // default failure handler throws an exception:
            println "error response: ${ex.statusCode}"
            response = ex.response
        }
        Allure.addDescription("response:\n" + new JsonBuilder(response.responseData).toPrettyString())

        then:
        response.status == responseStatus

        where:
        tag                                    | userName       || responseStatus
        "with valid username"                  | "jui"          || 200
        "with invalid username"                | 12345          || 400
        "with an username which doesn't exist" | "notexistuser" || 404
    }
}
