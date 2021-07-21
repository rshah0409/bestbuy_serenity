package com.bestbuy.bestbuyinfo;

import com.bestbuy.Model.ProductsPojo;
import com.bestbuy.constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class ProductsSteps {

@Step("Creating new product with name :{0},type :{1},price :{2},shipping :{3},upc :{4},description :{5},manufacturer :{6},model : {7},url :{8},image :{9}")
public ValidatableResponse createProduct(String name,String type,int price,int shipping,String upc,String description,String manufacturer,String model,String url,String image) {
    ProductsPojo productspojo = new ProductsPojo();
    productspojo.setName( name );
    productspojo.setType( type );
    productspojo.setPrice( price );
    productspojo.setShipping( shipping );
    productspojo.setUpc( upc );
    productspojo.setDescription( description );
    productspojo.setManufacturer( manufacturer );
    productspojo.setModel( model );
    productspojo.setUrl( url );
    productspojo.setImage( image );
    return   SerenityRest.rest()
            .given().log().all()
            .header( "Content-Type", "application/json" )
            .when()
            .body( productspojo )
            .post(EndPoints.CREATE_NEW_PRODUCT)
            .then().log().all();


}

    @Step("This will get All products")
    public ValidatableResponse getAllproducts(){
        return SerenityRest.rest()
                .given().log().all()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then();
    }
    @Step("This will get sinlge product by Id")
    public ValidatableResponse getSinlgeproductById(int productId){

        return SerenityRest.rest()
                .given().log().all()
                .pathParam("id", productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();

    }
    @Step("Update product information ")
    public ValidatableResponse updateProduct(int productId,String name,String type,int price,int shipping,String upc,String description,String manufacturer,String model,String url,String image) {

ProductsPojo productsPojo = new ProductsPojo();
    return SerenityRest.rest()
                .given().log().all()
                .pathParam("id", productId)
                .when()
                .body( productsPojo )
                .patch( EndPoints.UPDATE_SINGLE_PRODUCT_BY_ID )
                .then();
    }
    @Step("Deleting product information with Product ID")
    public ValidatableResponse deleteProduct(int productId) {
        return SerenityRest.rest()
                .given().log().all()
                .pathParam("id", productId)
                .when()
                .delete(EndPoints.DELETE_SINGLE_PRODUCT_BY_ID)
                .then();

    }




}
