package com.ximple.challenge.ximplelibrarysystem.controller.request;

import jakarta.validation.constraints.NotBlank;

public record ReservationRequest(@NotBlank Long userId,
                                 @NotBlank Long bookId) {
}
