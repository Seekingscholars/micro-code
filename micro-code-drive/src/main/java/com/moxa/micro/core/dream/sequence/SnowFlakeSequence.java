package com.moxa.micro.core.dream.sequence;

import com.moxa.dream.system.table.TableInfo;
import com.moxa.dream.template.sequence.AbstractSequence;
import com.moxa.micro.core.util.SnowFlake;

public class SnowFlakeSequence extends AbstractSequence {
    @Override
    protected Object sequence(TableInfo tableInfo) {
        return SnowFlake.nextId();
    }
}
