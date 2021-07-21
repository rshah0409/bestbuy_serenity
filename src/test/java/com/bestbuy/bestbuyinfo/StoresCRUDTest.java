package com.bestbuy.bestbuyinfo;

import com.bestbuy.Model.StoresPojo;
import com.bestbuy.constants.EndPoints;
import com.bestbuy.constants.Path;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasValue;

//@RunWith(SerenityRunner.class)
public class StoresCRUDTest extends TestBase {
    static String name = "Windsor Road" + TestUtils.getRandomValue();
    static String type = "Beauty" + TestUtils.getRandomValue();
    static String address = "windsor" + TestUtils.getRandomValue();
    static String address2 = "castle" + TestUtils.getRandomValue();
    static String city = "London" + TestUtils.getRandomValue();
    static String state = "England" + TestUtils.getRandomValue();
    static String zip = "123456" + TestUtils.getRandomValue();
    static int lat = Integer.parseInt( "1" + TestUtils.getRandomValue() );
    static int storeId;

    @Title("This Will create a new Store Information")
    @Test
    public void createNewStoreInfo() {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName( name );
        storesPojo.setType( type );
        storesPojo.setAddress( address );
        storesPojo.setAddress2( address2 );
        storesPojo.setCity( city );
        storesPojo.setState( state );
        storesPojo.setZip( zip );
        storesPojo.setLat( lat );
        SerenityRest.rest()
                .given().log().all()
                .header( "Content-Type", "application/json" )
                .body( storesPojo )
                .when()
                .post( Path.STORES )
                .then().log().all();

    }

    @Title("Verify if store was added to the application")
    @Test
    public void verifyAdditionOfStore() {
        String p1 = "findAll{it.city=='";
        String p2 = "'}.get(0)";
        HashMap<String, Object> value =
                SerenityRest.rest()
                        .given().log().all()
                        .when()
                        .get( Path.STORES )
                        .then()
                        .statusCode( 200 )
                        .extract()
                        .path( p1 + city + p2 );
        assertThat( value, hasValue( city ) );
        System.out.println( value );
        //   storeId = (int) value.get( "id" );


    }
}
