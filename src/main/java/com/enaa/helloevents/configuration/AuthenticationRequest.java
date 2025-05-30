package com.enaa.helloevents.configuration;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationRequest {

    private String username;
    private String password;

}
