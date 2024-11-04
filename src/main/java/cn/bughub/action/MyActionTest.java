package cn.bughub.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;


public class MyActionTest extends AnAction {
    
    @Override
    public void actionPerformed(AnActionEvent e) {
        // 弹窗
        // Messages.showMessageDialog("Hello bughub!!!", "提示信息", Messages.getInformationIcon());
    }
}
