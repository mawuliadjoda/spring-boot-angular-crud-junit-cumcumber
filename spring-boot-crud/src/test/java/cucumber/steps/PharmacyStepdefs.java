package cucumber.steps;

import com.esprit.springbootcrud.dto.PharmacyDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class PharmacyStepdefs {

    private static Response response;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Given("I have a following value of pharmacy to store in DB")
    public void iHaveAFollowingValueOfPharmacyToStoreInDB(@NotNull DataTable dataTable) {
        RequestSpecification request = RestAssured.given();

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        JSONObject requestParams = new JSONObject();
        for (Map<String, String> columns : rows) {
            requestParams.put("name", columns.get("name"));
            requestParams.put("address", columns.get("address"));
            requestParams.put("lat", Double.valueOf(columns.get("lat")));
            requestParams.put("lng", Double.valueOf(columns.get("lng")));
        }

        response = request
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
                .post("/pharmacies");
    }

    @When("I ask for pharmacies list")
    public void iAskForPharmaciesList() {
        RequestSpecification request = RestAssured.given();

        response = request.get("/pharmacies");
    }

    @Then("I should have a success response")
    public void iShouldHaveASuccessResponse() {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @And("The following pharmacies list should be in the response content")
    public void theFollowingPharmaciesListShouldBeInTheResponseContent(DataTable table) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String serializedKata = response.getBody().asString();
        List<PharmacyDTO> actualPharmacies = mapper.readValue(serializedKata, new TypeReference<>() {
        });


        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        List<PharmacyDTO> pharmacyDTOList = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            PharmacyDTO pharmacyDTO = PharmacyDTO.builder()
                    .id(Long.valueOf(columns.get("id")))
                    .name(columns.get("name"))
                    .address(columns.get("address"))
                    .lng(Double.valueOf(columns.get("lng")))
                    .lat(Double.valueOf(columns.get("lat")))
                    .build();
            pharmacyDTOList.add(pharmacyDTO);
        }

        Assertions.assertThat(actualPharmacies).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(pharmacyDTOList);
    }
}
