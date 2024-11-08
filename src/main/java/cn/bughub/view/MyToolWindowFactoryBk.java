package cn.bughub.view;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyToolWindowFactoryBk implements ToolWindowFactory {
    
    private JPanel mainPanel;
    
    private JPanel menuPanel;
    
    private JBList<String> menuList;
    
    private JButton moreButton;
    
    private SidebarMenu sidebarMenu;
    
    private JDialog floatingMenuDialog;
    
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
       /* // 初始化“更多”按钮
        MainPanel contentPanel = new MainPanel();
        moreButton = new JButton("更多");
        moreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 切换菜单的显示/隐藏状态
                menuPanel.setVisible(!menuPanel.isVisible());
            }
        });
        
        // 初始化菜单列表
        menuList = new JBList<>("URL编码、解码", "Base64编码、解码");
        menuList.setVisibleRowCount(10);
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // 为菜单列表添加选择监听器
        menuList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedValue = menuList.getSelectedValue();
                
                // 根据选择的菜单项显示相应的面板
                contentPanel.showPanel(selectedValue);
                
                // 隐藏菜单面板
                menuPanel.setVisible(false);
            }
        });
        
        // 创建菜单面板并添加菜单列表
        menuPanel = new JPanel(new BorderLayout());
        menuPanel.add(new JScrollPane(menuList), BorderLayout.CENTER);
        // 初始状态下隐藏菜单面板
        menuPanel.setVisible(false);
        
        // 创建主容器，将更多按钮、菜单面板和内容面板添加进去
        mainPanel = new JPanel(new BorderLayout());
        // 顶部添加“更多”按钮
        mainPanel.add(moreButton, BorderLayout.NORTH);
        // 左侧添加菜单面板
        mainPanel.add(menuPanel, BorderLayout.WEST);
        // 中间添加内容面板
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // 将主容器添加到工具窗口
        ContentFactory contentFactory = ContentFactory.getInstance();
        toolWindow.getContentManager().addContent(contentFactory.createContent(mainPanel, "", false));*/
        
        // 初始化主内容面板
        /*MainPanel contentPanel = new MainPanel();
        
        // 初始化侧边栏菜单并传入主内容面板引用
        sidebarMenu = new SidebarMenu(contentPanel);
        // 默认隐藏侧边栏
        sidebarMenu.setVisible(false);
        
        // 创建“更多”按钮
        moreButton = new JButton("更多");
        moreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sidebarMenu.setVisible(!sidebarMenu.isVisible()); // 切换菜单的显示/隐藏状态
            }
        });
        
        // 创建主容器，将更多按钮、侧边栏菜单和内容面板添加进去
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(moreButton, BorderLayout.NORTH);       // 顶部添加“更多”按钮
        mainPanel.add(sidebarMenu, BorderLayout.WEST);        // 左侧添加侧边栏菜单
        mainPanel.add(contentPanel, BorderLayout.CENTER);     // 中间添加主内容面板
        
        // 将主容器添加到工具窗口
        ContentFactory contentFactory = ContentFactory.getInstance();
        toolWindow.getContentManager().addContent(contentFactory.createContent(mainPanel, "", false));*/
        
        // 初始化主内容面板
        
        // 初始化主内容面板
      /*  MainPanel contentPanel = new MainPanel();
        
        // 创建“更多”按钮
        moreButton = new JButton("更多");
        
        // 创建一个浮动的菜单窗口
        floatingMenuDialog = new JDialog((Frame) null, "Menu", false);
        floatingMenuDialog.setUndecorated(true);
        // 设置大小，你可以根据需要调整
        floatingMenuDialog.setSize(200, 400);
        floatingMenuDialog.setAlwaysOnTop(true);
        
        // 初始化侧边栏菜单并将其添加到浮动对话框中
        sidebarMenu = new SidebarMenu(contentPanel);
        floatingMenuDialog.add(sidebarMenu);
        
        moreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (floatingMenuDialog.isVisible()) {
                    // 隐藏浮动菜单
                    floatingMenuDialog.setVisible(false);
                } else {
                    // 获取“更多”按钮的屏幕位置并调整浮动菜单的位置
                    Point location = moreButton.getLocationOnScreen();
                    floatingMenuDialog.setLocation(location.x, location.y + moreButton.getHeight());
                    // 显示浮动菜单
                    floatingMenuDialog.setVisible(true);
                }
            }
        });
        
        // 创建主容器，将更多按钮和内容面板添加进去
        mainPanel = new JPanel(new BorderLayout());
        // 顶部添加“更多”按钮
        mainPanel.add(moreButton, BorderLayout.NORTH);
        // 中间添加主内容面板
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // 将主容器添加到工具窗口
        ContentFactory contentFactory = ContentFactory.getInstance();
        toolWindow.getContentManager().addContent(contentFactory.createContent(mainPanel, "", false));*/
        
        /*JPanel panel = new JPanel(new BorderLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(0.1);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton button = new JButton("Menu");
        JBLabel label = new JBLabel();
        JBTextField searchField = new JBTextField();
        
        topPanel.add(button, BorderLayout.WEST);
        topPanel.add(label, BorderLayout.CENTER);
        topPanel.add(searchField, BorderLayout.EAST);
        
        splitPane.setTopComponent(topPanel);
        
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setBorder(BorderFactory.createEmptyBorder());
        popupMenu.setPreferredSize(new Dimension(200, 150));
        
        DefaultMutableTreeNode urlNode = new DefaultMutableTreeNode("url");
        DefaultMutableTreeNode urlEncodeNode = new DefaultMutableTreeNode("url编码");
        urlNode.add(urlEncodeNode);
        DefaultMutableTreeNode jsonNode = new DefaultMutableTreeNode("json");
        DefaultMutableTreeNode jsonFormatNode = new DefaultMutableTreeNode("json格式化");
        jsonNode.add(jsonFormatNode);
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        root.add(urlNode);
        root.add(jsonNode);
        
        JTree tree = new JTree(root);
        tree.setRootVisible(false);
        popupMenu.add(new JScrollPane(tree));
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.show(button, 0, button.getHeight());
            }
        });
        
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode != null && selectedNode.isLeaf()) {
                label.setText(selectedNode.getParent().toString() + "/" + selectedNode.toString());
                popupMenu.setVisible(false);
            }
        });
        
        JPanel bottomPanel = new JPanel();
        splitPane.setBottomComponent(bottomPanel);
        
        panel.add(splitPane, BorderLayout.CENTER);
        toolWindow.getComponent().add(panel);*/
        
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
        
        DefaultMutableTreeNode urlNode = new DefaultMutableTreeNode("url");
        DefaultMutableTreeNode urlEncodeNode = new DefaultMutableTreeNode("url编码");
        urlNode.add(urlEncodeNode);
        DefaultMutableTreeNode jsonNode = new DefaultMutableTreeNode("json");
        DefaultMutableTreeNode jsonFormatNode = new DefaultMutableTreeNode("json格式化");
        jsonNode.add(jsonFormatNode);
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        root.add(urlNode);
        root.add(jsonNode);
        
        JTree tree = new JTree(root);
        tree.setRootVisible(false);
        popupMenu.add(new JScrollPane(tree));
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.show(button, 0, button.getHeight());
            }
        });
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        splitPane.setBottomComponent(bottomPanel);
        
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode != null && selectedNode.isLeaf()) {
                label.setText(selectedNode.getParent().toString() + "/" + selectedNode.toString());
                bottomPanel.removeAll();
                bottomPanel.add(new JLabel("Panel for " + selectedNode.toString()), BorderLayout.CENTER);
                bottomPanel.revalidate();
                bottomPanel.repaint();
                popupMenu.setVisible(false);
            }
        });
        
        searchField.addActionListener(e -> {
            String searchText = searchField.getText();
            bottomPanel.removeAll();
            bottomPanel.add(new JLabel("Search results for: " + searchText), BorderLayout.CENTER);
            bottomPanel.revalidate();
            bottomPanel.repaint();
        });
        
        panel.add(splitPane, BorderLayout.CENTER);
        toolWindow.getComponent().add(panel);
    }
}
    

    
