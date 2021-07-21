package com.bestbuy.bestbuyinfo;

import com.bestbuy.Model.ProductsPojo;
import com.bestbuy.constants.EndPoints;
import com.bestbuy.constants.Path;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import javafx.scene.layout.Priority;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

//@RunWith(SerenityRunner.class)
public class ProductsCRUDTest extends TestBase {
static String name = "Cream Cookies"+TestUtils.getRandomValue();
static String type = "Biscuits" + TestUtils.getRandomValue();
static int price = Integer.parseInt( "3"+TestUtils.getRandomValue() );
static String upc = "Next day" + TestUtils.getRandomValue();
static int shipping = Integer.parseInt( "4"+ TestUtils.getRandomValue() );
static String description = "Cream Based Biscuits" + TestUtils.getRandomValue();
static String manufacturer = "Oreo" + TestUtils.getRandomValue();
static String model = "Fun Size" + TestUtils.getRandomValue();
static String url =  "www.cookie.com"  ;
static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg" ;
static int productId;

@Order(1)
@Title( "This will create a a new product" )
@Test
    public void test001(){
    ProductsPojo productspojo = new ProductsPojo();
    productspojo.setName(name);
    productspojo.setType(type);
    productspojo.setPrice(price);
    productspojo.setShipping(shipping);
    productspojo.setUpc(upc);
    productspojo.setDescription(description);
    productspojo.setManufacturer(manufacturer);
    productspojo.setModel(model);
    productspojo.setUrl(url);
    productspojo.setImage(image);
    SerenityRest.rest()
           . given().log().all()
                    .header("Content-Type", "application/json")
                    .body(productspojo)
                    .when()
                    .post( EndPoints.CREATE_NEW_PRODUCT)
                    .then().log().all();

}
@Order(2)
    @Title("Verify if the product was added to the application")
    @Test
    public void test002(){
        String p1 = "findAll{it.name=='";
        String p2 = "'}.get(0)";
        HashMap<String,Object> value =
                SerenityRest.rest()
                .given().log().all()
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then().log().all().statusCode( 200 )
                .extract()
                .path( p1 + name + p2 );
        assertThat( value,hasValue( name ) );
        System.out.println(value);
        productId = (int) value.get( "id" );
}
@Title( "Update the product information and verify the updated information" )
        @Test
        public void test003() {


    String p1 = "findAll{it.name=='";
    String p2 = "'}.get(0)";
    name = name + "_Updated";


    ProductsPojo productsPojo = new ProductsPojo();
    productsPojo.setName(name);
    productsPojo.setType(type);
    productsPojo.setPrice(price);
    productsPojo.setShipping(shipping);
    productsPojo.setUpc(upc);
    productsPojo.setDescription(description);
    productsPojo.setManufacturer(manufacturer);
    productsPojo.setModel(model);
   productsPojo.setUrl(url);
    productsPojo.setImage(image);
    SerenityRest.rest()
            .given().log().all()
            .header( "Content-Type","application/json" )
            .pathParam( "id",productId )
            .body(productsPojo)
            .when()
            .put(EndPoints.UPDATE_SINGLE_PRODUCT_BY_ID)
            .then().log().all().statusCode( 200 );

    HashMap<String,Object> value =
            SerenityRest.rest()
                    .given().log().all()
                    .when()
                    .get( EndPoints.CREATE_NEW_PRODUCT)
                    .then()
                    .statusCode(200)
                    .extract()
                    .path(p1+ name +p2);
    assertThat(value,hasValue(name));
    System.out.println(value);


}
    @Title("Delete the PRODUCT and verify if the PRODUCT is deleted!")
    @Test
    public void test004() {
        SerenityRest.rest()
                .given()
                .pathParam( "id",productId)
                .when()
                .delete(EndPoints.DELETE_SINGLE_PRODUCT_BY_ID)
                .then().statusCode( 204 );

        SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then()
                .statusCode( 404 );
    }


}
