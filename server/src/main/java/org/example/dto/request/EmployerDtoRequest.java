package org.example.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.request.groups.New;
import org.example.dto.request.groups.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDtoRequest {
    @NotNull(groups = {Update.class})
    private Long id;
    @NotBlank(groups = {New.class, Update.class})
    @Size(min = 3)
    private String name;
    @NotBlank(groups = {New.class, Update.class})
    @Size(min = 3)
    private String address;
}
