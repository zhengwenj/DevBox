package cn.bughub.view;

import cn.bughub.view.json.JsonFormatPanel;
import cn.bughub.view.url.UrlEncodingPanel;
import cn.bughub.view.url.UrlFormatPanel;
import cn.bughub.view.url.UrlParamExtractor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.treeStructure.Tree;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MyToolWindowFactory implements ToolWindowFactory {
    
    
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        
        JPanel panel = new JPanel(new BorderLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(0.1);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton button = new JButton("更多");
        JBLabel label = new JBLabel();
        JBTextField searchField = new JBTextField();
        
        topPanel.add(button, BorderLayout.WEST);
        topPanel.add(label, BorderLayout.CENTER);
        topPanel.add(searchField, BorderLayout.EAST);
        
        splitPane.setTopComponent(topPanel);
        
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setBorder(BorderFactory.createEmptyBorder());
        popupMenu.setPreferredSize(new Dimension(200, 150));
        
        SidebarMenu sidebarMenu = new SidebarMenu();
        Tree tree = sidebarMenu.createMenuTree();
        popupMenu.add(new JScrollPane(tree));
        
        Map<String, JPanel> panelMap = new HashMap<>();
        panelMap.put("url编码/解码", new UrlEncodingPanel());
        panelMap.put("url参数格式化", new UrlFormatPanel());
        panelMap.put("url参数与json互转", new UrlParamExtractor());
        panelMap.put("JSON格式化", new JsonFormatPanel());
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!popupMenu.isVisible()) {
                    popupMenu.show(button, 0, button.getHeight());
                } else {
                    popupMenu.setVisible(false);
                }
            }
        });
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        splitPane.setBottomComponent(bottomPanel);
        
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode != null && selectedNode.isLeaf()) {
                label.setText(selectedNode.getParent().toString() + "/" + selectedNode);
                bottomPanel.removeAll();
                // 动态选择子节点并嵌入对应的JPanel
                JPanel selectedPanel = panelMap.get(selectedNode.toString());
                if (selectedPanel != null) {
                    bottomPanel.add(selectedPanel, BorderLayout.CENTER);
                }
                bottomPanel.revalidate();
                bottomPanel.repaint();
                popupMenu.setVisible(false);
            }
        });
        
        /*searchField.addActionListener(e -> {
            String searchText = searchField.getText();
            bottomPanel.removeAll();
            bottomPanel.add(new JLabel("Search results for: " + searchText), BorderLayout.CENTER);
            bottomPanel.revalidate();
            bottomPanel.repaint();
        })*/
        ;
        
        panel.add(splitPane, BorderLayout.CENTER);
        toolWindow.getComponent().add(panel);
    }
}
    

    
