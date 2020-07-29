package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtoResponse extends AbstractDtoResponse {
    private String name;
    private String email;
    private Integer age;
    private String phone;
    List<AccountDtoResponse> accounts;
    List<EmployerDtoResponse> employers;
}
