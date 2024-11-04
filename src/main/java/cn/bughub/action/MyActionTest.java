package cn.bughub.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;


public class MyActionTest extends AnAction {
    
    @Override
    public void actionPerformed(AnActionEvent e) {
        System.out.println("MyActionTest");
    }
}
