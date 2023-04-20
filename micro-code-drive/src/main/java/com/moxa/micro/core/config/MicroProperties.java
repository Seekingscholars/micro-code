package com.moxa.micro.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties("micro")
public class MicroProperties {
    private List<String> excludeUrls;
}
