import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Paths;
import static java.nio.file.Files.readAllBytes;

public class FullNameValidation {

    //pass login credentials and pass authorization
    RequestSpecification request = RestAssured.given();
    String credentials = "admin:password";
    byte[] encodedCredentials = Base64.encodeBase64(credentials.getBytes());
    String encodedCredentialsAsString = new String(encodedCredentials);

    //Comments are the same throughout the tests
    @Test
    public void verifyNameWithSpecialCharactersReturnsErrorMsg() throws IOException {

        request.header("Authorization","Basic "+encodedCredentialsAsString);
        //Read payload from json file
        byte[] readIdPayLoad = readAllBytes(Paths.get("ProjectPayLoad/nameWithSpecCharacters.json"));
        String idPayLoad = new String(readIdPayLoad);
        System.out.println(idPayLoad);

        //Config the header, Pass payload into the request body, access post url
        request.header("Content-Type", "application/json");
        Response response = request.body(idPayLoad).post("https://assessment-api-loan.herokuapp.com/loans");
        System.out.println("Response Status Code is " + response.getStatusCode());
        String responseBodyAsString = response.getBody().asString();
        //Validate pass and fail
        Assert.assertTrue(responseBodyAsString.contains("Name must not have any special characters and digits"));
        response.prettyPrint();
    }
    @Test
    public void verifySurnameWithSpecialCharactersReturnsErrorMsg() throws IOException {

        request.header("Authorization","Basic "+encodedCredentialsAsString);
        //Read payload from json file
        //Read payload from json file
        byte[] readIdPayLoad = readAllBytes(Paths.get("ProjectPayLoad/surnameWithSpecCharacters.json"));
        String idPayLoad = new String(readIdPayLoad);
        System.out.println(idPayLoad);

        request.header("Content-Type", "application/json");
        Response response = request.body(idPayLoad).post("https://assessment-api-loan.herokuapp.com/loans");
        System.out.println("Response Status Code is " + response.getStatusCode());
        String responseBodyAsString = response.getBody().asString();
        Assert.assertTrue(responseBodyAsString.contains("Surname must not have any special characters and digits"));
        response.prettyPrint();
    }
    @Test
    public void verifyNameWithDigitsReturnsErrorMsg() throws IOException {

        request.header("Authorization","Basic "+encodedCredentialsAsString);
        //Read payload from json file
        //Read payload from json file
        byte[] readIdPayLoad = readAllBytes(Paths.get("ProjectPayLoad/nameWithDigits.json"));
        String idPayLoad = new String(readIdPayLoad);
        System.out.println(idPayLoad);

        request.header("Content-Type", "application/json");
        Response response = request.body(idPayLoad).post("https://assessment-api-loan.herokuapp.com/loans");
        System.out.println("Response Status Code is " + response.getStatusCode());
        String responseBodyAsString = response.getBody().asString();
        Assert.assertTrue(responseBodyAsString.contains("Name must not have any special characters and digits"));
        response.prettyPrint();
    }
    @Test
    public void verifysurnameWithDigitsReturnsErrorMsg() throws IOException {

        request.header("Authorization","Basic "+encodedCredentialsAsString);
        //Read payload from json file
        //Read payload from json file
        byte[] readIdPayLoad = readAllBytes(Paths.get("ProjectPayLoad/surnameWithDigits.json"));
        String idPayLoad = new String(readIdPayLoad);
        System.out.println(idPayLoad);

        request.header("Content-Type", "application/json");
        Response response = request.body(idPayLoad).post("https://assessment-api-loan.herokuapp.com/loans");
        System.out.println("Response Status Code is " + response.getStatusCode());
        String responseBodyAsString = response.getBody().asString();
        Assert.assertTrue(responseBodyAsString.contains("Surname must not have any special characters and digits"));
        response.prettyPrint();
    }
}