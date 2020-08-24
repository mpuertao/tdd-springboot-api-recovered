package com.covid19.recovereds.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recovered {

    @Id
    private int id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @Min(1)
    private int age;
}
