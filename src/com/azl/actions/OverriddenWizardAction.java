package com.azl.actions;

import com.azl.overriddenwizard.OverriddenWizard;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

public class OverriddenWizardAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        OverriddenWizard.show(project);
    }
}