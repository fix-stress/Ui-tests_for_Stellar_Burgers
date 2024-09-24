package site.nomoreparties.stellarburgers.api.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestClient {
    protected static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    protected RequestSpecification specContentType() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    protected RequestSpecification specWithoutContentType() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .build();
    }
    /* Взял и что-то здесь поменял */
    /* Опять что-то поменял */
    /* Опять меняем */
    /* Что-то меняем по результатам ревью*/
    /* опять фиксим*/
    /* опять ломаем */
    /* работает, не трогай */

}