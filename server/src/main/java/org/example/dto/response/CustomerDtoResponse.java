package org.example.dto.response;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.example.dto.response.groups.FullUser;
import org.example.dto.response.groups.ListUser;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtoResponse extends AbstractDtoResponse {
    @JsonView({ListUser.class})
    private Long id;
    @JsonView({ListUser.class})
    private String name;
    @JsonView({FullUser.class})
    private String email;
    @JsonView({FullUser.class})
    private Integer age;
    @JsonView({FullUser.class})
    private String phone;
    @JsonView({ListUser.class})
    List<AccountDtoResponse> accounts;
    @JsonView({FullUser.class})
    List<EmployerDtoResponse> employers;
}
