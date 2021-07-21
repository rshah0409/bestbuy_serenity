package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SerenityRunner.class)
public class ProductCRUDTestWithSteps extends  TestBase{

        static String name = "Cream Cookies"+ TestUtils.getRandomValue();
        static String type = "Biscuits" + TestUtils.getRandomValue();
        static int price = Integer.parseInt( "3"+TestUtils.getRandomValue() );
        static int shipping = Integer.parseInt( "4" + TestUtils.getRandomValue() );
        static String upc = "Next day" + TestUtils.getRandomValue();
        static String description = "Cream Based Biscuits" + TestUtils.getRandomValue();
        static String manufacturer = "Oreo" + TestUtils.getRandomValue();
        static String model = "Fun Size" + TestUtils.getRandomValue();
        static String url =  "www.cookie.com"  ;
        static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg" ;
        static int productId;


        @Steps
    ProductsSteps productsSteps;
    @Title( "This will create new product" )
    @Test
    public void test001(){
        productsSteps.createProduct( name,type,price,shipping,upc,description,manufacturer,model,url,image )
                .statusCode( 201 );
       // extract().response().body().jsonPath().getInt( "productId" )
    }

    @Title("This will read product- By product ID ")
    @Test
    public void test002() {

        productId = productsSteps.getAllproducts().extract().path("data[0].id");
        productsSteps.getSinlgeproductById(productId).statusCode( 200 );

    }
    @Title( "This will Update product" )
    @Test
    public void test003(){
        name = name + "_updated";
        price = 800;
        productsSteps.updateProduct(productId,name,type,price,shipping,upc,description,manufacturer,model,url,image).statusCode(200);

    }
    @Title("This will delete and verify if product is deleted")
    @Test
    public void test004() {
     //   productId = productsSteps.getAllproducts().extract().path("data[0].id");
        productsSteps.deleteProduct(productId).statusCode(200);
        productsSteps.getSinlgeproductById(productId).log().all().statusCode(404);
    }




}
