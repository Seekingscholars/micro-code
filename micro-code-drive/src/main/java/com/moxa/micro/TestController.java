package com.moxa.micro;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import com.alibaba.fastjson.JSONArray;
import com.moxa.micro.core.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.io.InputStreamReader;
@RequestMapping("/test")
@RestController
public class TestController {

    @ResponseBody
    @GetMapping(value = "/table")
    public Result table() {
        InputStream resourceAsStream = new ClassPathResource("test_table.json").getStream();
        String content = IoUtil.read(new InputStreamReader(resourceAsStream));
        return Result.ok(JSONArray.parseArray(content));
    }

    @ResponseBody
    @GetMapping(value = "/chart")
    public Result chart() {
        InputStream resourceAsStream =new ClassPathResource("test_chart.json").getStream();
        String content = IoUtil.read(new InputStreamReader(resourceAsStream));
        return Result.ok(JSONArray.parseArray(content));
    }

}
