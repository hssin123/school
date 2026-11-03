package tn.m104.rh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StudentDTO {

        private Integer rollNumber;
        private String name;
        private String address;
        private Double percentage;

}
