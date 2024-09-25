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
    /* что-то поменял в main */
    /* что-то поменял в develop */
    /* решил конфликт при слиянии develop и main */
    /* вновь меняем что-то в main */
    /* вновь меняем что-то в develop */
    /* вновь решаем конфликт при слиянии develop и main */
}