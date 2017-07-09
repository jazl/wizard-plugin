package com.azl.actions;

import com.azl.abstractwizard._AbstractWizard;
import com.azl.xwizard.StepAx;
import com.azl.xwizard.StepBx;
import com.azl.xwizard.StepCx;
import com.azl.xwizard._AbstractExWizard;
import com.intellij.ide.wizard.AbstractWizardStepEx;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jazl on 6/29/2017.
 */
@SuppressWarnings("ALL")
public class ActionX extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();

        List<AbstractWizardStepEx> steps = new ArrayList<>();
        steps.add(new StepAx("Step A", project));
        steps.add(new StepBx("Step B"));
        steps.add(new StepCx("Step C"));

        _AbstractExWizard wizard = new _AbstractExWizard("AbstractEx Wizard", project, steps);
        wizard.show();
    }
}
