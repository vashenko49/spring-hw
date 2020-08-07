package org.example.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerIdAndEmployerIdRequest {
    @NotNull
    private Long customerId;
    @NotNull
    private Long employerId;
}
