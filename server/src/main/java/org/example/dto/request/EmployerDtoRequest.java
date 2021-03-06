package org.example.dto.request;


import lombok.*;
import org.example.dto.request.groups.New;
import org.example.dto.request.groups.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDtoRequest extends AbstractDtoRequest {
    @NotNull(groups = {Update.class})
    private Long id;
    @NotBlank(groups = {New.class, Update.class})
    @Size(min = 3)
    private String name;
    @NotBlank(groups = {New.class, Update.class})
    @Size(min = 3)
    private String address;
}
