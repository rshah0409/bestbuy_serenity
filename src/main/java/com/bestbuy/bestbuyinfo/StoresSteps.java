package com.bestbuy.bestbuyinfo;

import com.bestbuy.Model.StoresPojo;
import com.bestbuy.constants.EndPoints;
import com.bestbuy.constants.Path;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.sameInstance;

public class StoresSteps {



    @Step("This Will create a new Store Information with name :{0},type:{1},address:{2},address2{3},city:{4},state:{5},zip :{6},lat:{7}")
    public void createNewStoreInfo(String name,String type,String address,String address2,String city,String state,String zip,int lat,int lng,String hours) {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName( name );
        storesPojo.setType( type );
        storesPojo.setAddress( address );
        storesPojo.setAddress2( address2 );
        storesPojo.setCity( city );
        storesPojo.setState( state );
        storesPojo.setZip( zip );
        storesPojo.setLat( lat );
        storesPojo.setLng( lng );
        storesPojo.setHours( hours );
        SerenityRest.rest()
                .given().log().all()
                .header( "Content-Type", "application/json" )
                .body( storesPojo )
                .when()
                .post( Path.STORES )
                .then().log().all();

    }



    @Step("This will get All stores")
    public ValidatableResponse getAllStores(){
        return SerenityRest.rest()
                .given().log().all()
                .when()
                .get( EndPoints.GET_ALL_STORES)
                .then();

    }
    @Step("This will get sinlge store by Id")
    public ValidatableResponse getSinlgeStoreById(int storeId){

        return SerenityRest.rest()
                .given().log().all()
                .pathParam("id", storeId)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();

    }
    @Step("Update store information by name ")
    public ValidatableResponse updateStore(int storeId,String name,String type,String address,String address2,String city,String state,String zip,int lat,int lng,String hours) {
StoresPojo storesPojo = new StoresPojo();
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        return SerenityRest.rest()
                .given().log().all()
                .contentType( ContentType.JSON )
                .pathParam("id",storeId ).log().all()
                .when()
                .body( storesPojo )
                .patch( EndPoints.UPDATE_SINGLE_STORE_BY_ID )
                .then();
    }
    @Step("Deleting product information with Store ID")
    public ValidatableResponse deletestore(int storeId) {
        return SerenityRest.rest()
                .given().log().all()
                .pathParam("id", storeId)
                .when()
                .delete(EndPoints.DELETE_SINGLE_STORE_BY_ID)
                .then();

    }



}


