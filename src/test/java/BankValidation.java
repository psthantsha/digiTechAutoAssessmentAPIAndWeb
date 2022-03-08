import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Paths;
import static java.nio.file.Files.readAllBytes;

public class BankValidation {

    //pass login credentials and pass authorization
    RequestSpecification request = RestAssured.given();
    String credentials = "admin:password";
    byte[] encodedCredentials = Base64.encodeBase64(credentials.getBytes());
    String encodedCredentialsAsString = new String(encodedCredentials);

    //Comments are the same throughout the tests
    @Test
    public void verifyScrumBankValidationIsSuccessful() throws IOException
    {
        request.header("Authorization","Basic "+encodedCredentialsAsString);
        //Read payload from json file
        byte[] readIdPayLoad = readAllBytes(Paths.get("ProjectPayLoad/scrumBank.json"));
        String idPayLoad = new String(readIdPayLoad);
        System.out.println(idPayLoad);

        //Config the header, Pass payload into the request body, access post url
        request.header("Content-Type","application/json");
        Response response = request.body(idPayLoad).post("https://assessment-api-loan.herokuapp.com/loans");
        System.out.println("Response Status Code is "+response.getStatusCode());
        String responseBodyAsString = response.getBody().asString();
        //Validate pass and fail
        Assert.assertTrue(responseBodyAsString.contains("true"));
        response.prettyPrint();
    }
    @Test
    public void verifyIconicBankValidationIsSuccessful() throws IOException
    {
        request.header("Authorization","Basic "+encodedCredentialsAsString);
        //Read payload from json file
        byte[] readIdPayLoad = readAllBytes(Paths.get("ProjectPayLoad/iconicBank.json"));
        String idPayLoad = new String(readIdPayLoad);
        System.out.println(idPayLoad);

        request.header("Content-Type","application/json");
        Response response = request.body(idPayLoad).post("https://assessment-api-loan.herokuapp.com/loans");
        System.out.println("Response Status Code is "+response.getStatusCode());
        String responseBodyAsString = response.getBody().asString();
        Assert.assertTrue(responseBodyAsString.contains("true"));
        response.prettyPrint();
    }
    @Test
    public void verifyMinionsBankValidationIsSuccessful() throws IOException
    {
        request.header("Authorization","Basic "+encodedCredentialsAsString);
        //Read payload from json file
        byte[] readIdPayLoad = readAllBytes(Paths.get("ProjectPayLoad/minionsBank.json"));
        String idPayLoad = new String(readIdPayLoad);
        System.out.println(idPayLoad);

        request.header("Content-Type","application/json");
        Response response = request.body(idPayLoad).post("https://assessment-api-loan.herokuapp.com/loans");
        System.out.println("Response Status Code is "+response.getStatusCode());
        String responseBodyAsString = response.getBody().asString();
        Assert.assertTrue(responseBodyAsString.contains("true"));
        response.prettyPrint();
    }
    @Test
    public void verifyMolewaBankValidationReturnsAWarningMsg() throws IOException
    {
        request.header("Authorization","Basic "+encodedCredentialsAsString);
        //Read payload from json file
        byte[] readIdPayLoad = readAllBytes(Paths.get("ProjectPayLoad/molewaBank.json"));
        String idPayLoad = new String(readIdPayLoad);
        System.out.println(idPayLoad);

        request.header("Content-Type","application/json");
        Response response = request.body(idPayLoad).post("https://assessment-api-loan.herokuapp.com/loans");
        System.out.println("Response Status Code is "+response.getStatusCode());
        String responseBodyAsString = response.getBody().asString();
        Assert.assertTrue(responseBodyAsString.contains("Refer to compliance"));
        Assert.assertTrue(responseBodyAsString.contains("true"));
        response.prettyPrint();
    }
}


