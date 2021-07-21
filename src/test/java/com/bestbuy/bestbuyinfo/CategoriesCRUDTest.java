package com.bestbuy.bestbuyinfo;

import com.bestbuy.Model.CategoriesPojo;
import com.bestbuy.constants.Path;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CategoriesCRUDTest extends TestBase {
static String name = "Salon"  + TestUtils.getRandomValue();
static String Id = "2"  +TestUtils.getRandomValue();

@Title( "This Will create a new Category" )
@Test
public void createNewCategories(){
    CategoriesPojo categoriesPojo = new CategoriesPojo();
    categoriesPojo.setName( name );
    categoriesPojo.setId( Id );

    SerenityRest.rest()
            .given().log().all()
            .header( "Content-Type", "application/json")
            .body( categoriesPojo )
            .when()
            .post( Path.CATEGORIES)
            .then().log().all().statusCode( 201 );


}



}
