package org.example.sandbox.util.body.pet;

import org.example.sandbox.util.swagger.definitions.UserBody;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostCreateUser extends JsonBody{

    @JsonProperty
    private UserBody UserBody;
}
