package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.request.groups.Update;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbstractDtoRequest {
    @NotNull(groups = {Update.class})
    private Long id;
    @NotNull(groups = {Update.class})
    private int version;
}
