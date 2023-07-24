package com.user.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionCreateRequest {
    @NotBlank
    private String receiver;

    @NotBlank
    private String sender;

    @Min(1)
    private Long amount; // lowest denomination

    private String reason;
}
