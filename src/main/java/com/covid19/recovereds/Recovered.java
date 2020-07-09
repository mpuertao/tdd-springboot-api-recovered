package com.covid19.recovereds;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recovered {

    @Id
    private int id;
    private String name;
    private int age;
}
