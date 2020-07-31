package org.example.dto.response;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.dto.response.groups.ListUser;
import org.example.entity.enums.Currency;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDtoResponse extends AbstractDtoResponse {
    @JsonView({ListUser.class})
    private Long id;
    @JsonView({ListUser.class})
    private String number;
    @JsonView({ListUser.class})
    private Currency currency;
    @JsonView({ListUser.class})
    private Double balance;
}
