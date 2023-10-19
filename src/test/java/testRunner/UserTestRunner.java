package testRunner;

import api.User;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;
import model.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner extends Setup {

    User user;

    public UserTestRunner() throws IOException {
        initConfig();
        user = new User(properties.getProperty("baseUrl"));
    }

    @Test(priority = 1, description = "Admin Login Successfully")
    public void login() throws ConfigurationException {
        UserModel model =new UserModel();
        model.setEmail("salman@roadtocareer.net");
        model.setPassword("1234");
        String token = user.callingLoginApi(model);
        System.out.println(token);
        Utils.setEnvVar("token",token);

        Allure.description("Admin Login Successfully");
    }

    @Test(priority = 2, description = "Create Agent by Admin Successfully")
    public void createAgent() throws IOException, ParseException, ConfigurationException {
        initConfig();
        UserModel userModel =new UserModel();
        Faker faker =new Faker();
        userModel.setName(faker.name().fullName());
        userModel.setEmail(faker.internet().emailAddress());
        userModel.setPassword("1234");
        userModel.setPhone_number("0191"+ Utils.generateRandomId(1000000,9999999));
        userModel.setNid("987654321");
        userModel.setRole("Agent");

        JsonPath jsonPath =user.createUser(properties.getProperty("token"), properties.getProperty("partnerKey"), userModel);
        String messageActual =jsonPath.get("message");
        String phone_number =jsonPath.get("user.phone_number");
        int id =jsonPath.get("user.id");
        if(messageActual.contains("User created")){
            Utils.setEnvVar("agent_phone_number",phone_number);
            userModel.setId(id);
            Utils.saveUserInfo(userModel);
        }

        Allure.description("Create Agent by Admin Successfully");
    }

    @Test(priority = 3, description = "Create Customer1 by Admin Successfully")
    public void createCustomer1() throws IOException, ParseException, ConfigurationException {
        initConfig();
        UserModel userModel =new UserModel();
        Faker faker =new Faker();
        userModel.setName(faker.name().fullName());
        userModel.setEmail(faker.internet().emailAddress());
        userModel.setPassword("1234");
        userModel.setPhone_number("0191"+ Utils.generateRandomId(1000000,9999999));
        userModel.setNid("987654321");
        userModel.setRole("Customer");

        JsonPath jsonPath =user.createUser(properties.getProperty("token"), properties.getProperty("partnerKey"), userModel);
        String messageActual =jsonPath.get("message");
        String phone_number =jsonPath.get("user.phone_number");
        int id =jsonPath.get("user.id");
        if(messageActual.contains("User created")){
            Utils.setEnvVar("customer1_phone_number",phone_number);
            userModel.setId(id);
            Utils.saveUserInfo(userModel);
        }

        Allure.description("Create Customer1 by Admin Successfully");
    }

    @Test(priority = 4, description = "Create Customer2 by Admin Successfully")
    public void createCustomer2() throws IOException, ParseException, ConfigurationException {
        initConfig();
        UserModel userModel =new UserModel();
        Faker faker =new Faker();
        userModel.setName(faker.name().fullName());
        userModel.setEmail(faker.internet().emailAddress());
        userModel.setPassword("1234");
        userModel.setPhone_number("0191"+ Utils.generateRandomId(1000000,9999999));
        userModel.setNid("987654321");
        userModel.setRole("Customer");

        JsonPath jsonPath =user.createUser(properties.getProperty("token"), properties.getProperty("partnerKey"), userModel);
        String messageActual =jsonPath.get("message");
        String phone_number =jsonPath.get("user.phone_number");
        int id =jsonPath.get("user.id");
        if(messageActual.contains("User created")){
            Utils.setEnvVar("customer2_phone_number",phone_number);
            userModel.setId(id);
            Utils.saveUserInfo(userModel);

        }

        Allure.description("Create Customer2 by Admin Successfully");
    }

}
