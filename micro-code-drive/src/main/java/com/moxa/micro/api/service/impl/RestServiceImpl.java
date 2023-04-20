package com.moxa.micro.api.service.impl;

import com.moxa.dream.boot.impl.ServiceImpl;
import com.moxa.micro.api.service.IRestApiService;
import com.moxa.micro.api.view.RestApiEV;
import com.moxa.micro.api.view.RestApiLV;
import org.springframework.stereotype.Service;

@Service
public class RestServiceImpl extends ServiceImpl<RestApiLV, RestApiEV> implements IRestApiService {
}
