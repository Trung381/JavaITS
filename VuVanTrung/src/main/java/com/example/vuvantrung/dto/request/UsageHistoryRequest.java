package com.example.vuvantrung.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsageHistoryRequest {
    @NotNull(message = "Electricity used must be non-negative.")
    private int used;

    @NotNull(message = "Date is mandatory.")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = "User ID must be positive.")
    private int userId;
}
