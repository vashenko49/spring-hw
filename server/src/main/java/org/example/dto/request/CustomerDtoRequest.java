package org.example.dto.request;


import lombok.*;
import org.example.dto.request.groups.New;
import org.example.dto.request.groups.Update;

import javax.validation.constraints.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtoRequest extends AbstractDtoRequest {
    @NotBlank(
            message = "name is require",
            groups = {New.class, Update.class}
    )
    @Size(min = 2)
    private String name;
    @NotNull(groups = {New.class, Update.class})
    @Min(18)
    @Max(150)
    @Positive(
            message = "age must be positive",
            groups = {New.class, Update.class}
    )
    private Integer age;
    @Email(groups = {New.class})
    private String email;
    @NotBlank(message = "phone is require", groups = {New.class})
    @Pattern(
            regexp = "^(\\+)?(\\(\\d{2,3}\\) ?\\d|\\d)(([ \\-]?\\d)|( ?\\(\\d{2,3}\\) ?)){5,12}\\d$",
            message = "not valid phone",
            groups = {New.class}
    )
    private String phone;
    @NotBlank(message = "password is require")
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$",
            message = "not valid password"
    )
    private String password;
    @NotNull(groups = {New.class, Update.class})
    private List<EmployerDtoRequest> employers;
}
