package com.bestbuy.bestbuyinfo;

import com.bestbuy.Model.CategoriesPojo;
import com.bestbuy.Model.ServicePojo;
import com.bestbuy.constants.Path;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ServicesCRUDTest extends TestBase {
static String name = "Gym"  + TestUtils.getRandomValue();


@Title("This Will create a anew service Information")
@Test

public void createNewService(){

    ServicePojo servicePojo = new ServicePojo();
    servicePojo.setName( name );

    SerenityRest.rest()
            .given().log().all()
            .header( "Content-Type","application/json" )
            .body( servicePojo )
            .when()
            .post( Path.SERVICES)
            .then().log().all().statusCode( 201 );


}

}
