package yc.code.dict.wechat.data;

import lombok.Data;

import java.util.Date;

@Data
public class Token {
    private String pk1;
    private String token_type;
    private String token_value;
    private Date apply_time;
    private Integer expires_in;
}
