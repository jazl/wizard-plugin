package com.azl.actions;

import com.azl.xwizard._AbstractExWizardStatic;
import com.intellij.ide.wizard.AbstractWizardStepEx;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jazl on 7/3/17.
 */
public class ActionXStatic extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();

        Messages.showInfoMessage("About to launch using static class...","Info");
        _AbstractExWizardStatic.show(project);
    }
}
