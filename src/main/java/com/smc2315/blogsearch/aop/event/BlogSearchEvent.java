package com.smc2315.blogsearch.aop.event;

import com.smc2315.blogsearch.dto.EventHoldingValue;
import com.smc2315.blogsearch.dto.SendableParameter;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class BlogSearchEvent implements EventHoldingValue<SendableParameter> {

    private final SendableParameter value;

    public BlogSearchEvent(@NonNull SendableParameter sendableParameter) {
        this.value = sendableParameter;
    }
}
