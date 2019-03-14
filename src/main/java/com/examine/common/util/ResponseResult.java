package com.examine.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class ResultUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Integer status;

    private String message;

    private Object data;

    public ResultUtil() {
    }

    public ResultUtil(Object data) {
        this.status = 200;
        this.message = "OK";
        this.data = data;
    }

    public ResultUtil(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static ResultUtil build(Integer status, String message, Object data){
        return new ResultUtil(status,message,data);
    }

    public static ResultUtil ok(Object data){
        return new ResultUtil(data);
    }

    public static ResultUtil ok(){
        return new ResultUtil(null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultUtil formatToPojo(String jsonData,Class<?> clazz){
        try {
            if(clazz == null){
                return  MAPPER.readValue(jsonData,ResultUtil.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if(clazz != null){
                if(data.isObject()){
                    obj = MAPPER.readValue(data.traverse(),clazz);
                }else if(data.isTextual()){
                    obj = MAPPER.readValue(data.asText(),clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("message").asText(), obj);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultUtil format(String json) {
        try {
            return MAPPER.readValue(json, ResultUtil.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultUtil formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("message").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
}
