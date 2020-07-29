package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDtoRequest {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
}
