package org.example.sandbox.util.body.pet;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class JsonBody {

    public static String createJsonBody(PostCreatePet body){
        try{
            return new ObjectMapper().writeValueAsString(body.getPetBody());

        } catch (Throwable e){
            throw new RuntimeException("Nody Generation Failure");
        }
    }
    public static String createJsonBody2(PostCreateStore body){
        try{
            return new ObjectMapper().writeValueAsString(body.getStoreOrderBody());

        } catch (Throwable e){
            throw new RuntimeException("Nody Generation Failure");
        }
    }

    public static String createJsonBody3(PostCreateUser body){
        try{
            return new ObjectMapper().writeValueAsString(body.getUserBody());

        } catch (Throwable e){
            throw new RuntimeException("Nody Generation Failure");
        }
    }
}
