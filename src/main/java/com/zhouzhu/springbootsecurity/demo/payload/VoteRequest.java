package com.zhouzhu.springbootsecurity.demo.payload;

import javax.validation.constraints.NotNull;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-06-13 15:33
 */
public class VoteRequest {
    @NotNull
    private Long choiceId;

    public Long getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(Long choiceId) {
        this.choiceId = choiceId;
    }
}
