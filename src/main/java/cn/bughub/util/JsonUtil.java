package cn.bughub.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Map;

/**
 * JSON工具类
 *
 * @author zwj
 * @date 2024-12-10
 */
public class JsonUtil {
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    static {
        // 启用特性以支持美化输出
        OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }
    
    /**
     * 格式化 JSON 字符串
     *
     * @param jsonString 要格式化的对象
     * @return 格式化后的 JSON 字符串
     */
    public static String formatJson(String jsonString) {
        try {
            // 将 JSON 字符串转换为对象，然后再格式化为美化的 JSON 字符串
            Object json = OBJECT_MAPPER.readValue(jsonString, Object.class);
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    /**
     * 格式化 Map 为 JSON 字符串
     *
     * @param paramMap 要格式化的 Map
     * @return 格式化后的 JSON 字符串
     */
    public static String formatJson(Map<String, Object> paramMap) {
        return formatJson((Object) paramMap);
    }
    /**
     * 格式化 JSON 字符串
     *
     * @param object 要格式化的对象
     * @return 格式化后的 JSON 字符串
     */
    public static String formatJson(Object object) {
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    /**
     * 压缩 JSON 字符串
     *
     * @param object 要压缩的对象
     * @return 压缩后的 JSON 字符串
     */
    public static String compressJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    
    /**
     * 转义 JSON 字符串
     *
     * @param jsonString 要转义的 JSON 字符串
     * @return 转义后的 JSON 字符串
     */
    public static String escapeJson(String jsonString) {
        return jsonString.replace("\\", "\\\\").replace("\"", "\\\"").replace("\b", "\\b").replace("\f", "\\f")
                .replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
    }
    public static String unescapeJson(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        
        // 去除转义字符
        return jsonString
                .replace("\\\"", "\"")
                .replace("\\\\", "\\")
                .replace("\\b", "\b")
                .replace("\\f", "\f")
                .replace("\\n", "\n")
                .replace("\\r", "\r")
                .replace("\\t", "\t");
    }
}
