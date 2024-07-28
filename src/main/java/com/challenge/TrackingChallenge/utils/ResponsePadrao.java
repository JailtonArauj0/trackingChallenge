package com.challenge.TrackingChallenge.utils;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Data
@Getter
@Setter
public class ResponsePadrao {
    private String mensagem;
    private int status;
}
