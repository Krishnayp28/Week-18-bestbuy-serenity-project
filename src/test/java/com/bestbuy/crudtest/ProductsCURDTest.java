package com.bestbuy.crudtest;

import com.bestbuy.info.ProductsSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ProductsCURDTest extends TestBase {
    static int id;
    String name = "Duracell11 " + TestUtils.getRandomValue();
    String type = "max";
    int price = 25;
    int shipping = 15;
    String upc = "XYZ";
    String description = "small";
    String manufacturer = "Phylips";
    String model = "AAA";
    String url = "http://www.bestbuy.com/site/duracell-aa-1-5v-coppertop-batteries-4-pack/48530.p?id=1099385268988&skuId=48530&cmp=RMXCC";
    String image = "http://img.bbystatic.com/BestBuy_US/images/products/4853/48530_sa.jpg";


    @Steps
    ProductsSteps productsSteps;
    @Title("Creating a new product.")
    @Test
    public void test001() {
        ValidatableResponse response = productsSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        id = response.extract().path("id");
    }

    @Title("Getting single product.")
    @Test
    public void test002() {
        ValidatableResponse response = productsSteps.getProduct(id);
        response.log().all().statusCode(200);
    }

    @Title("Updating the product.")
    @Test
    public void test003() {
        name = "Duracell11 " + TestUtils.getRandomValue();
        ValidatableResponse response = productsSteps.updateProduct(id, name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(200);
    }

    @Title("Deleting the product and verifying the deletion.")
    @Test
    public void test004() {
        productsSteps.deleteProduct(id).statusCode(200);
        productsSteps.getProduct(id).statusCode(404);
    }
}
