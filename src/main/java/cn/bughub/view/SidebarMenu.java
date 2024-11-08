package cn.bughub.view;

import com.intellij.ui.treeStructure.Tree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 侧边栏菜单
 *
 * @author zwj
 * @date 2024-11-07
 */
public class SidebarMenu extends JPanel {
    
    /**
     * 构建菜单树
     *
     * @return {@link Tree }
     */
    public Tree createMenuTree() {
        
        // 顶级节点：URL和MIME Base64
        DefaultMutableTreeNode urlNode = new DefaultMutableTreeNode("URL");
        DefaultMutableTreeNode mimeNode = new DefaultMutableTreeNode("编码与解码");
        DefaultMutableTreeNode dateAndTimeNode = new DefaultMutableTreeNode("时间日期");
        DefaultMutableTreeNode jsonNode = new DefaultMutableTreeNode("JSON相关");
        
        // URL子节点
        DefaultMutableTreeNode urlEncodeNode = new DefaultMutableTreeNode("url编码/解码");
        DefaultMutableTreeNode urlFormatNode = new DefaultMutableTreeNode("url参数格式化");
        DefaultMutableTreeNode urlParameterConvertedToJson = new DefaultMutableTreeNode("url参数与json互转");
        urlNode.add(urlEncodeNode);
        urlNode.add(urlFormatNode);
        urlNode.add(urlParameterConvertedToJson);
        
        // json子节点
        jsonNode.add(new DefaultMutableTreeNode("JSON格式化"));
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        root.add(urlNode);
        root.add(mimeNode);
        root.add(dateAndTimeNode);
        root.add(jsonNode);
        
        Tree tree = new Tree(root);
        tree.setRootVisible(false);
        return tree;
    }
}
