package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class ServicesCRUDTestWithSteps extends TestBase {
    static String name = "Hair and Care" + TestUtils.getRandomValue();
    static int servicesId;


    @Steps
    ServicesSteps servicesSteps;

    @Title("This test will create a new services and verify its generated")
    @Test

    public void test001() {

        servicesId = servicesSteps.createNewServices(name).log().all().statusCode(201).extract().response()
                .body().jsonPath().getInt("id");
        System.out.println("services ID is" + servicesId);
    }

    @Title("This test will get the services information by ID")
    @Test

    public void test002() {
        servicesSteps.getServicesById(servicesId).log().all().statusCode(200);

    }

    @Title("This test will update the services information and verify the updated information")
    @Test

    public void test003() {

        name = name + "_Changed";

        servicesSteps.updateServices(servicesId, name).statusCode(200);
        servicesSteps.getServicesById(servicesId).body("name", equalTo(name));

    }

    @Title("This test will delete the categories information and verify the categories is deleted ")
    @Test

    public void test004() {
        servicesSteps.deleteServicesById(servicesId).statusCode(200);
        servicesSteps.getServicesById(servicesId).statusCode(404);
    }
}
