package com.dyp.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NettyPushMessage {

    @ApiModelProperty(value = "登录用户编号")
    private Integer loginUserNum;

    @ApiModelProperty(value = "推送内容")
    private String content;

    public Integer getLoginUserNum() {
        return loginUserNum;
    }

    public void setLoginUserNum(Integer loginUserNum) {
        this.loginUserNum = loginUserNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
