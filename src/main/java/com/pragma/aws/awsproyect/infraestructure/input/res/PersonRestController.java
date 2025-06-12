package com.pragma.aws.awsproyect.infraestructure.input.res;

import com.pragma.aws.awsproyect.application.dto.request.PersonRequest;
import com.pragma.aws.awsproyect.application.dto.response.PersonResponse;
import com.pragma.aws.awsproyect.application.handler.IPersonHandler;
import com.pragma.aws.awsproyect.domain.util.OpenApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person/")
@Tag(name = OpenApiConstants.TITLE_PERSON_REST, description = OpenApiConstants.TITLE_DESCRIPTION_PERSON_REST)
@RequiredArgsConstructor
public class PersonRestController {

    private final IPersonHandler personHandler;

    @Operation(summary =  OpenApiConstants.NEW_PERSON_TITLE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.RESPONSE_CODE_201 , description = OpenApiConstants.NEW_PERSON_CREATED, content = @Content),
            @ApiResponse(responseCode =  OpenApiConstants.RESPONSE_CODE_400 , description =  OpenApiConstants.VALIDATIONS_ERRORS_MESSAGE, content = @Content)
    })
    @PostMapping("saveperson")
    public ResponseEntity<Void> savePerson (@Valid @RequestBody PersonRequest personRequest) {
        personHandler.savePerson(personRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary =  OpenApiConstants.GET_PERSON_TITLE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.RESPONSE_CODE_200 , description =  OpenApiConstants.GET_PERSON_MESSAGE, content = @Content),
            @ApiResponse(responseCode = OpenApiConstants.RESPONSE_CODE_400 , description =  OpenApiConstants.VALIDATIONS_ERRORS_MESSAGE, content = @Content)
    })
    @GetMapping("consultperson/{id}")
    public ResponseEntity<PersonResponse> getPersonById(@PathVariable(value = "id") Long id) {
        return  ResponseEntity.ok(personHandler.getPersonById(id));
    }
}
