package com.ximple.challenge.ximplelibrarysystem.controller.request;

import jakarta.validation.constraints.NotBlank;

public record ReviewRequest(@NotBlank Long bookId,
                            @NotBlank Long UserId,
                            @NotBlank String textReview) {
}
