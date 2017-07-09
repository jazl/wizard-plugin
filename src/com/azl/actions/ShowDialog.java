package com.azl.actions;

import com.azl.gui.ComponentsDialog;
import com.azl.gui.ComponentsDialog2;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

public class ShowDialog extends AnAction {

    @Override
    public void update(AnActionEvent e) {
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();

        ComponentsDialog dlg = new ComponentsDialog(project, false);
        dlg.show();

    }
}
