package com.frameworkium.core.common.reporting.jira.service;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.frameworkium.core.api.services.BaseService;
import com.frameworkium.core.common.properties.Property;
import com.frameworkium.core.common.reporting.jira.endpoint.JiraEndpoint;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public abstract class AbstractJiraService extends BaseService {
    @Override
    protected RequestSpecification getRequestSpec() {
        return RestAssured.given().log().ifValidationFails()
                .baseUri(JiraEndpoint.BASE_URI.getUrl())
                .config(config())
                .relaxedHTTPSValidation()
                .auth().preemptive().basic(
                        Property.JIRA_USERNAME.getValue(),
                        Property.JIRA_PASSWORD.getValue());
    }

    @Override
    protected ResponseSpecification getResponseSpec() {
        throw new UnsupportedOperationException("Unimplemented");
    }

    private RestAssuredConfig config() {
        return RestAssuredConfig.config().objectMapperConfig(
                ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory(
                        (type, s) -> {
                            final ObjectMapper objectMapper = new ObjectMapper();
                            objectMapper.registerModule(new JavaTimeModule());
                            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                            objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
                            objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
                            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                            return objectMapper;
                        }
                )
        );
    }
}

