package api;

import io.restassured.mapper.ObjectMapperType;
import net.datafaker.Faker;
import io.restassured.response.Response;
import requestModel.CreateUserModel;
import io.restassured.RestAssured;
import requestModel.LoginModel;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;


public class ApiHelper {
    private static Faker faker = new Faker();

    public static HashMap getNewUser(){
        RestAssured.baseURI = API.baseURI;
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String name =faker.name().firstName();
        CreateUserModel createUserModel = new CreateUserModel(email,password,name);
        Response response = given()
                .header("Content-type", "application/json")
                .body(createUserModel)
                .when()
                .post(API.registerURI);
        response.then().assertThat().body("success", equalTo(true))
                .and().statusCode(200);
        HashMap userData = new HashMap<>();
        userData.put("email",email);
        userData.put("password", password);
        userData.put("name",name);
        return userData;
    }
    public static HashMap generateRegitrationData(boolean validPassword){
        String email = faker.internet().emailAddress();
        String password = validPassword?faker.internet().password():faker.regexify("[a-zA-Z0-9]{4}");;
        String name =faker.name().firstName();
        HashMap userData = new HashMap<>();
        userData.put("email",email);
        userData.put("name",name);
        userData.put("password", password);
        return userData;
    }
    public static String getAccessToken(HashMap<String,String> userData){
        RestAssured.baseURI = API.baseURI;
        LoginModel loginModel=new LoginModel(userData.get("email"),userData.get("password"));
        Response response = given()
                .header("Content-type", "application/json")
                .body(loginModel)
                .when()
                .post(API.loginURI);
        response.then().assertThat().body("success", equalTo(true))
                .statusCode(200);
        return response.path("accessToken");
    }
    public static void deleteUser(HashMap<String,String> userData){
        String accessToken =getAccessToken(userData);
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .delete(API.userURI);
        response.then().assertThat().statusCode(202);
    }
}
