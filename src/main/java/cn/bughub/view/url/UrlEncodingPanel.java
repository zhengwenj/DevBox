package cn.bughub.view.url;


import javax.swing.*;
import java.awt.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlEncodingPanel extends JPanel {
    
    private JTextArea inputArea;
    
    private JTextArea outputArea;
    
    private JButton encodeButton;
    
    private JButton decodeButton;
    
    public UrlEncodingPanel() {
        setLayout(new BorderLayout());
        inputArea = new JTextArea("");
        inputArea.setRows(10);
        inputArea.setLineWrap(true);
        
        outputArea = new JTextArea();
        outputArea.setLineWrap(true);
        outputArea.setRows(10);
        
        encodeButton = new JButton("编码");
        decodeButton = new JButton("解码");
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(encodeButton);
        buttonPanel.add(decodeButton);
        
        add(new JScrollPane(inputArea), BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);
        
        // 添加编码和解码的事件监听器
        encodeButton.addActionListener(e -> outputArea.setText(encode(inputArea.getText())));
        decodeButton.addActionListener(e -> outputArea.setText(decode(inputArea.getText())));
    }
    
    private String encode(String input) {
        return URLEncoder.encode(input, StandardCharsets.UTF_8);
    }
    
    private String decode(String input) {
        return URLDecoder.decode(input, StandardCharsets.UTF_8);
    }
}
