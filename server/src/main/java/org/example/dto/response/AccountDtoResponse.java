package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.entity.Currency;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDtoResponse extends AbstractDtoResponse {
    private String number;
    private Currency currency;
    private Double balance;
    private Long customerId;
}
