package com.moxa.micro;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moxa.micro.core.entity.Result;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/test")
@RestController
public class TestController {

    @ResponseBody
    @GetMapping(value = "/table")
    public Result table(String name) {
        InputStream resourceAsStream = new ClassPathResource("test_table.json").getStream();
        String content = IoUtil.read(new InputStreamReader(resourceAsStream));
        JSONArray jsonArray = JSONArray.parseArray(content);
        Object result = jsonArray;
        if (StrUtil.isNotEmpty(name)) {
            result = jsonArray.stream().filter(o -> {
                JSONObject object = (JSONObject) o;
                if (object.getString("name").contains(name)) {
                    return true;
                } else {
                    return false;
                }
            }).collect(Collectors.toList());
        }
        return Result.ok(result);
    }

    @ResponseBody
    @GetMapping(value = "/chartDong")
    public Result chartDong(@RequestParam(required = false) List<String> type) {
        return chart("dong",type);
    }

    @ResponseBody
    @GetMapping(value = "/chartZhong")
    public Result chartZhong(@RequestParam(required = false) List<String> type) {
        return chart("zhong",type);
    }

    @ResponseBody
    @GetMapping(value = "/chartXi")
    public Result chartXi(@RequestParam(required = false) List<String> type) {
        return chart("xi",type);
    }

    @ResponseBody
    @GetMapping(value = "/chartBei")
    public Result chartBei(@RequestParam(required = false) List<String> type) {
        return chart("bei",type);
    }

    public Result chart(String name,List<String> type) {
        InputStream resourceAsStream = new ClassPathResource("test_" + name + ".json").getStream();
        String content = IoUtil.read(new InputStreamReader(resourceAsStream));
        JSONArray jsonArray = JSONArray.parseArray(content);
        if(CollUtil.isNotEmpty(type)){
            List<Map<String, Object>> mapList = jsonArray.stream().map(o -> {
                JSONObject jsonObject = (JSONObject) o;
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("REGIO_BEZEI",jsonObject.get("REGIO_BEZEI"));
                for (String t : type) {
                    map.put(t, jsonObject.get(t));
                }
                return map;
            }).collect(Collectors.toList());
            return Result.ok(mapList);
        }else{
            return Result.ok(jsonArray);
        }
    }

}
