package com.moxa.micro.core.dream.wrap;

import com.moxa.dream.template.wrap.Wrapper;
import com.moxa.micro.core.util.ClientUtil;

public class UserWrapper implements Wrapper {
    @Override
    public Object wrap(Object value) {
        if (value == null) {
            return ClientUtil.getLoginUser().getUsername();
        }
        return value;
    }
}
