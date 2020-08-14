package org.example.dto.request;

import lombok.*;
import org.example.dto.request.groups.New;
import org.example.dto.request.groups.Update;
import org.example.entity.enums.Currency;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDtoRequest extends AbstractDtoRequest {
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
