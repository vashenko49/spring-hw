package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationWithAccountResponse implements Serializable {
    TypeOperationWithAccount type;
    String from;
    String to;
    double sum;
}
