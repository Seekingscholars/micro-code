package com.moxa.micro.core.entity;

import lombok.Data;

@Data
public class AppMapping {
    private String requestUrl;
    private String bizModule;
    private String permission;
}
