package cn.bughub.view;

import cn.hutool.core.codec.Base64;

import javax.swing.*;
import java.awt.*;

/**
 * Base64编码面板
 *
 * @author zwj
 * @date 2024-11-12
 */
public class Base64EncodingPanel extends JPanel {
    
    private final JTextArea inputArea;
    
    private final JTextArea outputArea;
    
    private final JButton encodeButton;
    
    private final JButton decodeButton;
    
    public Base64EncodingPanel() {
        
        setLayout(new BorderLayout());
        inputArea = new JTextArea("Base64", 5, 20);
        outputArea = new JTextArea();
        encodeButton = new JButton("Base64编码");
        decodeButton = new JButton("Base64解码");
        
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
        if (input == null) {
            return null;
        }
        return Base64.encode(input);
    }
    
    private String decode(String base64Input) {
        if (base64Input == null) {
            return null;
        }
        return Base64.decodeStr(base64Input);
    }
}
