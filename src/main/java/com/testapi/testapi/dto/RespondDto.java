package com.testapi.testapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RespondDto<T> {

    private boolean status;
    private List<String> message = new ArrayList<>();
    private T payload;
}
