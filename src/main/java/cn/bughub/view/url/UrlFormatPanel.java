package cn.bughub.view.url;


import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * URL参数格式化面板
 *
 * @author zwj
 * @date 2024-11-05
 */
public class UrlFormatPanel extends JPanel {
    
    private final JTextArea inputArea;
    
    private final JTextArea outputArea;
    
    
    public UrlFormatPanel() {
        setLayout(new BorderLayout());
        inputArea = new JTextArea("");
        inputArea.setForeground(JBColor.GRAY);
        inputArea.setLineWrap(true);
        inputArea.setRows(10);
        outputArea = new JTextArea();
        outputArea.setLineWrap(true);
        outputArea.setRows(10);
        
        JButton encodeButton = new JButton("URL参数格式化");
        // JButton cleanButton = new JButton("清除");
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(encodeButton);
        // buttonPanel.add(cleanButton);
        
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
                    inputArea.setText("请输入url");
                    inputArea.setForeground(JBColor.GRAY); // 设置提示信息的颜色
                }
            }
        });
        // 添加编码和解码的事件监听器
        encodeButton.addActionListener(e -> outputArea.setText(format(inputArea.getText())));
        // cleanButton.addActionListener(e -> inputArea.setText(""));
    }
    
    private String format(String input) {
        
        String host;
        int port;
        String uri;
        String baseUrl;
        Map<String, String> parameters;
        try {
            UrlComponents components = parseUrl(input);
            host = components.getHost();
            port = components.getPort();
            uri = components.getUri();
            baseUrl = components.getBaseUrl();
            parameters = components.getParameters();
        } catch (Exception e) {
            return "格式化失败";
        }
        return String.format("""
                Host: %s
                Port: %d
                URI: %s
                Base URL: %s
                Parameters: %s
                """, host, port, uri, baseUrl, parameters);
    }
    
    /**
     * 解析URL
     *
     * @param urlString URL 字符串
     * @return {@link UrlComponents }
     * @throws Exception 例外
     */
    public static UrlComponents parseUrl(String urlString) throws Exception {
        URL url = new URL(urlString);
        
        String host = url.getHost();
        int port = url.getPort();
        String uri = url.getPath();
        // 构建基础 URL
        String baseUrl = url.getProtocol() + "://" + host + (port != -1 ? ":" + port : "") + uri;
        Map<String, String> parameters = getQueryParameters(url.getQuery());
        
        return new UrlComponents(host, port, uri, baseUrl, parameters);
    }
    
    /**
     * 获取查询参数
     *
     * @param query 查询
     * @return {@link Map }<{@link String }, {@link String }>
     */
    private static Map<String, String> getQueryParameters(String query) {
        
        Map<String, String> params = new LinkedHashMap<>();
        if (query != null && !query.isEmpty()) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                if (idx > 0) {
                    String key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8);
                    String value = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8);
                    params.put(key, value);
                }
            }
        }
        return params;
    }
    
    public static class UrlComponents {
        
        private final String host;
        
        private final int port;
        
        private final String uri;
        
        private final String baseUrl;
        
        /**
         * 参数
         */
        private final Map<String, String> parameters;
        
        public UrlComponents(String host, int port, String uri, String baseUrl, Map<String, String> parameters) {
            this.host = host;
            this.port = port;
            this.uri = uri;
            this.baseUrl = baseUrl;
            this.parameters = parameters;
        }
        
        public String getHost() {
            return host;
        }
        
        public int getPort() {
            return port;
        }
        
        public String getUri() {
            return uri;
        }
        
        public String getBaseUrl() {
            return baseUrl;
        }
        
        public Map<String, String> getParameters() {
            return parameters;
        }
    }
}
