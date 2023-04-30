package com.moxa.micro.datasource.service.impl;

import com.moxa.dream.boot.impl.ServiceImpl;
import com.moxa.micro.core.exception.SoothException;
import com.moxa.micro.core.util.DbUtil;
import com.moxa.micro.datasource.service.IDatasourceService;
import com.moxa.micro.datasource.view.DatasourceEV;
import com.moxa.micro.datasource.view.DatasourceLV;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;

@Service
public class DatasourceServiceImpl extends ServiceImpl<DatasourceLV, DatasourceEV> implements IDatasourceService {
    @Override
    public String testConnection(DatasourceEV datasourceEV) {
        String type = datasourceEV.getType();
        final String rest = "rest";
        if (rest.equals(type)) {
            Socket socket=null;
            try {
                Integer timeout = datasourceEV.getTimeout();
                URL url = new URL(datasourceEV.getUrl());
                socket = new Socket();
                socket.connect(new InetSocketAddress(url.getHost(), url.getPort()), timeout==null?0:timeout);
                socket.close();
            } catch (Exception e) {
                throw new SoothException("连接失败：" + e.getMessage());
            }finally {
                if(socket!=null){
                    try {
                        if (!socket.isClosed()) {
                            socket.close();
                        }
                    }catch (Exception e){

                    }
                }
            }
        } else {
            String url = datasourceEV.getUrl();
            String username = datasourceEV.getUsername();
            String password = datasourceEV.getPassword();
            Connection connection = DbUtil.getConnection(url, username, password);
            DbUtil.close(connection);
        }
        return "连接成功";
    }
}
