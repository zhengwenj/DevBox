package cn.bughub.view.json;

import cn.bughub.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * JSON格式化
 *
 * @author zwj
 * @date 2024-11-07
 */
public class JsonFormatPanel extends JPanel {
    
    private JTextArea inputArea;
    
    private JTextArea outputArea;
    
    private JButton formatButton;
    
    private JButton compressButton;
    
    private JButton escapeButton;
    
    private JButton removeEscapingButton;
    
    private JButton compressAndEscapeButton;
    
    private JButton copyButton;
    
    private JButton clearButton;
    
    public JsonFormatPanel() {
        setLayout(new BorderLayout());
        inputArea = new JTextArea("");
        inputArea.setRows(10);
        inputArea.setLineWrap(true);
        
        outputArea = new JTextArea();
        outputArea.setLineWrap(true);
        outputArea.setRows(10);
        
        formatButton = new JButton("格式化");
        compressButton = new JButton("压缩");
        escapeButton = new JButton("转义");
        removeEscapingButton = new JButton("去除转义");
        compressAndEscapeButton = new JButton("压缩并转义");
        
        copyButton = new JButton("复制");
        clearButton = new JButton("清空");
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(formatButton);
        buttonPanel.add(compressButton);
        buttonPanel.add(escapeButton);
        buttonPanel.add(removeEscapingButton);
        buttonPanel.add(compressAndEscapeButton);
        
        JPanel outputButtonPanel = new JPanel();
        outputButtonPanel.add(copyButton);
        outputButtonPanel.add(clearButton);
        
        add(new JScrollPane(inputArea), BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        // add(new JScrollPane(outputArea), BorderLayout.SOUTH);
        
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());
        outputPanel.add(outputButtonPanel, BorderLayout.NORTH);
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        
        add(outputPanel, BorderLayout.SOUTH);
        // 事件监听器
        formatButton.addActionListener(e -> outputArea.setText(format(inputArea.getText())));
        compressButton.addActionListener(e -> outputArea.setText(compressJson(inputArea.getText())));
        escapeButton.addActionListener(e -> outputArea.setText(escapeJson(inputArea.getText())));
        removeEscapingButton.addActionListener(e -> outputArea.setText(unescapeJson(inputArea.getText())));
        compressAndEscapeButton.addActionListener(e -> outputArea.setText(compressAndEscapeJson(inputArea.getText())));
        copyButton.addActionListener(e -> {
            String selectedText = outputArea.getSelectedText();
            if (selectedText == null) {
                selectedText = outputArea.getText();
            }
            StringSelection stringSelection = new StringSelection(selectedText);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        });
    }
    
    
    /**
     * 格式化json
     *
     * @param input 输入
     * @return {@link String }
     */
    private String format(String input) {
        
        String validResult = isValidJson(input);
        if (!StringUtils.isBlank(validResult)) {
            return validResult;
        }
        String json = JsonUtil.formatJson(input);
        if (json == null) {
            return "格式化失败";
        }
        return json;
    }
    
    /**
     * 压缩JSON
     *
     * @param input JSON str
     * @return {@link String }
     */
    public static String compressJson(String input) {
        
        String validResult = isValidJson(input);
        if (!StringUtils.isBlank(validResult)) {
            return validResult;
        }
        
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson = null;
        try {
            JsonNode jsonNode = objectMapper.readTree(input);
            resultJson = objectMapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            
            return "压缩失败";
        }
        return resultJson;
    }
    
    /**
     * 转义JSON
     *
     * @param input 输入
     * @return {@link String }
     */
    public static String escapeJson(String input) {
        
        String validResult = isValidJson(input);
        if (!StringUtils.isBlank(validResult)) {
            return validResult;
        }
        return JsonUtil.escapeJson(input);
    }
    
    /**
     * 去除转义
     *
     * @param input 输入
     * @return {@link String }
     */
    public static String unescapeJson(String input) {
        
        return JsonUtil.unescapeJson(input);
    }
    
    /**
     * 压缩和转义JSON
     *
     * @param input 输入
     * @return {@link String }
     */
    public static String compressAndEscapeJson(String input) {
        
        return escapeJson(compressJson(input));
    }
    
    /**
     * 检查是否是JSON
     *
     * @param jsonStr JSON str
     * @return boolean
     */
    public static String isValidJson(String jsonStr) {
        
        if (StringUtils.isBlank(jsonStr)) {
            return "";
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.readTree(jsonStr);
            return "";
        } catch (Exception e) {
            return "json格式不正确";
        }
    }
    
}
