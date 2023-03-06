package com.yc.code.dict.jsp.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserVO {
    private String name;
    private String age;
}
