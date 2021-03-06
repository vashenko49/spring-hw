package org.example.dto.response;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.example.dto.response.groups.ListUser;
import org.example.entity.enums.Currency;

@EqualsAndHashCode(callSuper = true)
@Builder
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
