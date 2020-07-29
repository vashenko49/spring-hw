package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Currency;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDtoRequest {
    private Long id;
    @NotBlank
    private String number;
    @NotBlank
    private Currency currency;
    @PositiveOrZero(message = "balance must be positive")
    private Double balance;
    private String customerId;
}
