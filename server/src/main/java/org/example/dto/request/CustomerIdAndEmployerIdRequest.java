package org.example.dto.request;

import lombok.Data;
import lombok.Builder;

import javax.validation.constraints.NotNull;


@Data
@Builder
public class CustomerIdAndEmployerIdRequest {
    @NotNull
    private Long customerId;
    @NotNull
    private Long employerId;
}
