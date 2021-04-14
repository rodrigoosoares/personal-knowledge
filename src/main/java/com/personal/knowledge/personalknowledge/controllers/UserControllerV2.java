package com.personal.knowledge.personalknowledge.controllers;

import com.personal.knowledge.personalknowledge.controllers.constants.SwaggerApiConstants;
import com.personal.knowledge.personalknowledge.controllers.entities.UserRequest;
import com.personal.knowledge.personalknowledge.controllers.validation.annotations.AppAccessValidate;
import com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorResponse;
import com.personal.knowledge.personalknowledge.controllers.validation.entities.Header;
import com.personal.knowledge.personalknowledge.controllers.validation.headers.RequestHeaderValidator;
import com.personal.knowledge.personalknowledge.entities.User;
import com.personal.knowledge.personalknowledge.usecases.CreateUser;
import com.personal.knowledge.personalknowledge.usecases.GetUserById;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.personal.knowledge.personalknowledge.controllers.validation.headers.enums.HeaderType.REQUEST_TRACE_ID;

/**
 * - Controller using my custom validation
 * - Just set the headers requirement to false to use my own validation and avoid spring default validation that is used
 *   in V1 controller
 */
@RestController
@Api(value = "/v2/user", tags = {"User"})
@RequestMapping("/v2/user")
public class UserControllerV2 {

    @Autowired
    private RequestHeaderValidator requestHeaderValidator;

    @Autowired
    private GetUserById getUserById;

    @Autowired
    private CreateUser createUser;

    @AppAccessValidate
    @ApiResponses(value = {
        @ApiResponse(code = SwaggerApiConstants.OK, message = "Success found user names by given filters", response = User.class),
        @ApiResponse(code = SwaggerApiConstants.BAD_REQUEST, message = "Bad request error. A response will be returned with error information", response = ErrorResponse.class)
    })
    @GetMapping
    public ResponseEntity<String> getUsersNames(@ApiParam(value = "request-trace-id", required = true) @RequestHeader(name = "request-trace-id", required = false) final String requestTraceId,
        @ApiParam(value = "User name to search", required = true, example = "John") @RequestParam(name = "name") final String name) {

        final Header header = Header.builder()
            .type(REQUEST_TRACE_ID)
            .value(requestTraceId)
            .build();

        requestHeaderValidator.execute(header);

        return ResponseEntity.ok(name);
    }

    @AppAccessValidate
    @ApiResponses(value = {
        @ApiResponse(code = SwaggerApiConstants.OK, message = "Success found user by given ID", response = User.class),
        @ApiResponse(code = SwaggerApiConstants.BAD_REQUEST, message = "Bad request error. A response will be returned with error information", response = ErrorResponse.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable final int id) {
        return ResponseEntity.ok(getUserById.execute(id));
    }

    @AppAccessValidate
    @ApiResponses(value = {
        @ApiResponse(code = SwaggerApiConstants.NO_CONTENT, message = "User created with success", response = User.class),
        @ApiResponse(code = SwaggerApiConstants.BAD_REQUEST, message = "Bad request error. A response will be returned with error information", response = ErrorResponse.class)
    })
    @PostMapping
    public void createUser(@RequestBody(required = false) final UserRequest userRequest) {
        createUser.execute(userRequest);
    }

}