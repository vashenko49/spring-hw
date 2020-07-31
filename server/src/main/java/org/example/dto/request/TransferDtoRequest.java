package org.example.dto.request;

import lombok.Data;
import org.example.dto.request.groups.AdminTransfer;
import org.example.dto.request.groups.FromToTransfer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
@Data
public class TransferDtoRequest {
    @NotBlank(groups = {FromToTransfer.class})
    String fromNum;
    @NotBlank(groups = {FromToTransfer.class, AdminTransfer.class})
    String toNum;
    @NotNull(groups = {FromToTransfer.class, AdminTransfer.class})
    @Positive(groups = {FromToTransfer.class, AdminTransfer.class})
    Long sum;
}
