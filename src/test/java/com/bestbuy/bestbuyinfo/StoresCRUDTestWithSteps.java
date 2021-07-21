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
public class StoresCRUDTestWithSteps extends TestBase {
    static String name = "Windsor Road" ;
    static String type = "Beauty" + TestUtils.getRandomValue();
    static String address = "windsor" + TestUtils.getRandomValue();
    static String address2 = "castle" + TestUtils.getRandomValue();
    static String city = "London" + TestUtils.getRandomValue();
    static String state = "England" + TestUtils.getRandomValue();
    static String zip = "123456" + TestUtils.getRandomValue();
    static int lat = Integer.parseInt( "1" + TestUtils.getRandomValue() );
    static int lng = 3;
    static String hours = "Mon: 9-6; Tue: 9-6; Wed: 9-6; Thurs: 9-6; Fri: 9-6; Sat: 9-6; Sun: 9-6";
    static int storeId;
    @Steps
    StoresSteps storesSteps;
    @Title( "This will create new store" )
    @Test
    public void test001(){
       storesSteps.createNewStoreInfo( name,type,address,address2,city,state,zip,lat,lng,hours );


    }

    @Title("This will read store- By store ID ")
    @Test
    public void test002() {

        storeId =storesSteps.getAllStores().extract().path("data[0].id");
        storesSteps.getSinlgeStoreById(storeId).statusCode( 200 );

    }
    @Title( "This will Update store" )
    @Test
    public void test003(){
        name = name + "_changed";
     address = address + "_updated";
        hours = "Mon: 8-6; Tue: 8-6; Wed: 8-6; Thurs: 8-6; Fri: 8-6; Sat: 8-6; Sun: 8-6";
        storesSteps.updateStore(storeId,name,type,address,address2,city,state,zip,lat,lng,hours).statusCode(200);
        storesSteps.getSinlgeStoreById(storeId).body("address",equalTo(address));

    }
    @Title("This will delete and verify if store is deleted")
    @Test
    public void test004() {
        storesSteps.deletestore(storeId).statusCode(200);
        storesSteps.getSinlgeStoreById(storeId).log().all().statusCode(404);
    }

}
