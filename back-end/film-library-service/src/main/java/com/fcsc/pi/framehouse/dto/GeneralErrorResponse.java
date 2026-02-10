package com.fcsc.pi.framehouse.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeneralErrorResponse {

    // A code that may be checked in front-end
    private String code = "";

    // A readable message
    private String message = "";

}
