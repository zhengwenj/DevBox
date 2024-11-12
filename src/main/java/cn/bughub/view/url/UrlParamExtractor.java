package cn.bughub.view.url;

import cn.hutool.json.JSONUtil;
import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * URL参数提取面板
 *
 * @author zwj
 * @date 2024-11-06
 */
public class UrlParamExtractor extends JPanel {
    
    private final JTextArea inputArea;
    
    private final JTextArea outputArea;
    
    public UrlParamExtractor() {
        setLayout(new BorderLayout());
        inputArea = new JTextArea("");
        inputArea.setForeground(JBColor.GRAY);
        inputArea.setLineWrap(true);
        inputArea.setRows(10);
        outputArea = new JTextArea();
        outputArea.setLineWrap(true);
        outputArea.setRows(10);
        
        JButton encodeButton = new JButton("URL参数转JSON");
        JButton cleanButton = new JButton("清除");
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(encodeButton);
        buttonPanel.add(cleanButton);
        
        add(new JScrollPane(inputArea), BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);
        // 添加鼠标监听器以处理提示信息
        inputArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // 鼠标悬停时清空内容
                if ("请输入url".equals(inputArea.getText())) {
                    inputArea.setText("");
                    inputArea.setForeground(JBColor.BLACK); // 设置文本颜色为黑色
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                // 鼠标离开时，如果文本区域为空，则恢复提示信息
                if (inputArea.getText().isEmpty()) {
                    inputArea.setText("");
                    // 设置提示信息的颜色
                    inputArea.setForeground(JBColor.GRAY);
                }
            }
        });
        // 添加编码和解码的事件监听器
        encodeButton.addActionListener(e -> outputArea.setText(extractParams(inputArea.getText(), "camelCase")));
        cleanButton.addActionListener(e -> inputArea.setText(""));
    }
    
    public static String extractParams(String urlString, String formatType) {
        Map<String, String> paramMap = new HashMap<>();
        
        try {
            URL url = new URL(urlString);
            String query = url.getQuery();
            
            if (query != null) {
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    String[] keyValue = pair.split("=");
                    if (keyValue.length == 2) {
                        String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
                        String value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8);
                        
                        // 根据入参格式类型，转换参数名
                        String formattedKey = formatKey(key, formatType);
                        
                        paramMap.put(formattedKey, value);
                    }
                }
            }
        } catch (Exception e) {
        
        }
        return JSONUtil.toJsonPrettyStr(paramMap);
    }
    
    // 格式化键名为指定格式
    private static String formatKey(String key, String formatType) {
        return switch (formatType) {
            case "camelCase" -> toCamelCase(key);
            case "snake_case" -> toSnakeCase(key);
            case "kebab-case" -> toKebabCase(key);
            default -> key;
        };
    }
    
    // 将字符串转化为驼峰命名法
    private static String toCamelCase(String key) {
        String[] parts = key.split("_");
        StringBuilder camelCaseString = new StringBuilder(parts[0].toLowerCase());
        
        for (int i = 1; i < parts.length; i++) {
            camelCaseString.append(parts[i].substring(0, 1).toUpperCase());
            camelCaseString.append(parts[i].substring(1).toLowerCase());
        }
        return camelCaseString.toString();
    }
    
    // 将字符串转化为蛇形命名法（snake_case）
    private static String toSnakeCase(String key) {
        return key.replaceAll("([a-z])([A-Z])", "\\$1_\\$2").toLowerCase();
    }
    
    // 将字符串转化为连字符命名法（kebab-case）
    private static String toKebabCase(String key) {
        return key.replaceAll("([a-z])([A-Z])", "\\$1-\\$2").toLowerCase();
    }
}
