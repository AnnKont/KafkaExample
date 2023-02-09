package com.ann.kont.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class Message {
    @NotNull
    private Long id;
    @NotBlank
    private String text;
}
