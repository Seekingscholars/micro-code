package com.moxa.micro.app.service.impl;

import com.moxa.dream.boot.impl.ServiceImpl;
import com.moxa.micro.app.mapper.AppMapper;
import com.moxa.micro.app.service.IAppService;
import com.moxa.micro.app.view.AppEV;
import com.moxa.micro.app.view.AppLV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppServiceImpl extends ServiceImpl<AppLV, AppEV> implements IAppService {
    @Autowired
    private AppMapper appMapper;

    @Override
    public Integer resetAppCategory(List<Long> appIds, Long categoryId) {
        return appMapper.resetAppCategory(appIds, categoryId);
    }
}
