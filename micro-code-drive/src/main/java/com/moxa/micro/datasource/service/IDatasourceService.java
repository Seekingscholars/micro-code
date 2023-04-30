package com.moxa.micro.datasource.service;

import com.moxa.dream.template.service.IService;
import com.moxa.micro.datasource.view.DatasourceEV;
import com.moxa.micro.datasource.view.DatasourceLV;

public interface IDatasourceService extends IService<DatasourceLV, DatasourceEV> {
    String testConnection(DatasourceEV datasourceEV);
}
