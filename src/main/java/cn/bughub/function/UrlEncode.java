package cn.bughub.function;

import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.net.URLEncodeUtil;

import java.nio.charset.StandardCharsets;

/**
 * URL编码相关功能类
 *
 * @author zwj
 * @date 2024-11-04
 */
public class UrlEncode {
    
    /**
     * URL编码
     *
     * @param input 输入信息
     * @return {@link String }
     */
    public static String urlEncode(String input) {
        
        return URLEncodeUtil.encode(input);
    }
    
    /**
     * URL解码
     *
     * @param input 输入内容
     * @return {@link String }
     */
    public static String urlDecode(String input) {
        
        return URLDecoder.decode(input, StandardCharsets.UTF_8);
    }
}
