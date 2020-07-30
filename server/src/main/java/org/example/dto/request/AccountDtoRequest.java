package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.request.groups.New;
import org.example.dto.request.groups.Update;
import org.example.entity.Currency;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDtoRequest {
    @NotBlank(groups = {Update.class}, message = "number is require")
    private String number;
    @NotNull(groups = {New.class})
    private Currency currency;
    @NotNull(groups = {New.class})
    @PositiveOrZero(message = "balance must be positive", groups = {New.class})
    private Double balance;
    @NotNull(message = "customer is require", groups = {New.class})
    private Long customer;
}
