package org.example.dto.response;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.example.dto.response.groups.FullUser;


@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDtoResponse extends AbstractDtoResponse {
    @JsonView({FullUser.class})
    private Long id;
    @JsonView({FullUser.class})
    private String name;
    @JsonView({FullUser.class})
    private String address;
}
