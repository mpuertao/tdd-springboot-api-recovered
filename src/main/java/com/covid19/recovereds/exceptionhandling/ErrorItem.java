package com.covid19.recovereds.exceptionhandling;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

@Validated
@Data
@AllArgsConstructor
public class ErrorItem {
    @JsonProperty("title")
    @Size(max = 100)
    private String title = null;

    @JsonProperty("code")
    @Size(max = 10)
    private String code = null;

    @JsonProperty("description")
    @Size(max = 100)
    private String description = null;
}
