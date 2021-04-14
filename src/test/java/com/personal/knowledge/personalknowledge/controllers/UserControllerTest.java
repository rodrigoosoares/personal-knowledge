package com.personal.knowledge.personalknowledge.controllers;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.knowledge.personalknowledge.commons.CommonsTest;
import com.personal.knowledge.personalknowledge.configuration.objectmapper.ObjectMapperConfiguration;
import com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorData;
import com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorResponse;
import com.personal.knowledge.personalknowledge.fixtures.processors.ErrorResponseProcessor;
import com.personal.knowledge.personalknowledge.fixtures.templates.ErrorResponseFixtures;
import com.personal.knowledge.personalknowledge.usecases.CreateUser;
import com.personal.knowledge.personalknowledge.usecases.GetUserById;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest extends CommonsTest {

    private final ObjectMapper objectMapper = new ObjectMapperConfiguration().getObjectMapper();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GetUserById getUserById;

    @MockBean
    private CreateUser createUser;

    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates(ErrorResponseFixtures.class.getPackageName());
    }

    @Test
    void shouldGetUserNamesWithSuccessWhenGivenValidParameters() throws Exception {
        final String endpoint = "/v1/user";

        final String name = "name";
        final String age = "20";

        final MvcResult mvcResult = mvc.perform(get(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .param("name", name)
            .param("age", age))
        .andReturn();

        final MockHttpServletResponse response = mvcResult.getResponse();
        assertThat(response.getStatus(), is(OK.value()));
        assertThat(response.getContentAsString(), is(name));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 5, 10, 17})
    void shouldValidateMinimumAgeParameter(Integer age) throws Exception {
        final String endpoint = "/v1/user";

        final String name = "name";

        final MvcResult mvcResult = mvc.perform(get(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .param("name", name)
            .param("age", age.toString()))
            .andReturn();

        final MockHttpServletResponse response = mvcResult.getResponse();

        assertThat(response.getStatus(), is(BAD_REQUEST.value()));
        assertErrorResponse(response, fixture(ErrorResponse.class, new ErrorResponseProcessor(endpoint),
                                              ErrorResponseFixtures.DATA_MIN_VALUE_METHOD_GET_VALIDATION_ERROR));
    }

    private void assertErrorResponse(final MockHttpServletResponse response, final ErrorResponse expectedResponse) throws UnsupportedEncodingException, JsonProcessingException {
        final ErrorResponse errorResponse = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);
        final List<ErrorData> errorsDataResponse = errorResponse.getErrors();

        assertThat(errorResponse.getMethod(), is(expectedResponse.getMethod()));
        assertThat(errorResponse.getPath(), is(errorResponse.getPath()));
        assertThat(errorResponse.getStatus(), is(errorResponse.getStatus()));

        for(int i = 0; i< errorsDataResponse.size(); i++) {
            assertThat(errorsDataResponse.get(i).getFieldName(), is(expectedResponse.getErrors().get(i).getFieldName()));
            assertThat(errorsDataResponse.get(i).getMessage(), is(expectedResponse.getErrors().get(i).getMessage()));
        }
    }


}
