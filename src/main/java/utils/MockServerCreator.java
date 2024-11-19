package utils;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockServerCreator {

    private WireMockServer wireMockServer;

    public void createMockServer() {
        try {
            wireMockServer = new WireMockServer();
            configureFor("localhost", 8080);
            wireMockServer.start();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Failed to start mockserver" + exception.getMessage());
        }
    }

    public void stubAllOkay() {
        try {
            stubFor(any(anyUrl()).willReturn(ok()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void samplePostStub() {
        try {
            String requestJson = "{\n" +
                    "    \"name\": \"Santosh Balaga\",\n" +
                    "    \"age\": 37,\n" +
                    "    \"designation\": \"Software Engineer\"\n" +
                    "}";
            String responseJson = "{\n" +
                    "    \"name\": \"Santosh Balaga\",\n" +
                    "    \"age\": 37,\n" +
                    "    \"designation\": \"Software Engineer\",\n" +
                    "    \"id\": 1\n" +
                    "}";
            Map<String, StringValuePattern> queryParam = new HashMap<>();
            stubFor(
                    post(urlPathEqualTo("/createEmployee"))
                            .withRequestBody(equalToJson(requestJson))
                            //.withHeader("", "")
                            //.withBasicAuth("", "")
                            //.withQueryParams(queryParam)
                            .willReturn(okJson(responseJson)));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void sampleGetStub() {
        try {
            String responseJson = "[\n" +
                    "  {\n" +
                    "\t\"name\" : \"Santosh Balaga\",\n" +
                    "\t\"age\" : 37,\n" +
                    "\t\"designation\" \"Software Engineer\"\n" +
                    "  },\n" +
                    "    {\n" +
                    "\t\"name\" : \"Vasavi Balaga\",\n" +
                    "\t\"age\" : 30,\n" +
                    "\t\"designation\" Home Maker\"\n" +
                    "  }\n" +
                    "]";
            stubFor(
                    get(urlPathEqualTo("/getEmployees"))
                            //.withHeader("", "")
                            //.withBasicAuth("", "")
                            //.withQueryParams(queryParam)
                            .willReturn(okJson(responseJson)));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void stopMockServer() {
        try {
            if(wireMockServer != null)
                wireMockServer.stop();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MockServerCreator mockServerCreator = new MockServerCreator();
        mockServerCreator.createMockServer();
        mockServerCreator.samplePostStub();
        RestAssured.baseURI = "http://localhost:8080";

        String requestJson = "{\n" +
                "    \"name\": \"Santosh Balaga\",\n" +
                "    \"age\": 37,\n" +
                "    \"designation\": \"Software Engineer\"\n" +
                "}";
        Response response = RestAssured.given().body(requestJson).post("/createEmployee");
        System.out.println(response.body().print());

        System.out.println("Get request");
        mockServerCreator.sampleGetStub();
        response = RestAssured.given().get("/getEmployees");
        System.out.println(response.body().print());
        mockServerCreator.stopMockServer();
    }

}
