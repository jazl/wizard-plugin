package com.azl.abstractwizard;

import com.azl.abstractwizard._AbstractWizard;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

/**
 * Created by jazl on 6/29/2017.
 */
@SuppressWarnings("ALL")
public class ActionA extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        //Messages.showMessageDialog(project, "Oh Hai","Information",Messages.getInformationIcon());

        _AbstractWizard wizard = new _AbstractWizard("Wizard", project);
        wizard.show();
    }
}
