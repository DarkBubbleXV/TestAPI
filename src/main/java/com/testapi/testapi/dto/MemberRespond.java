package com.testapi.testapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberRespond {

    private Long id;

    private String memberNumber;

    private String memberName;

    private String memberEmail;

}
