package com.azl.xwizard;

import com.intellij.ide.wizard.AbstractWizardStepEx;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jazl on 7/3/17.
 */
public class _AbstractExWizardStatic {

    public static void show(Project project) {
        List<AbstractWizardStepEx> steps = new ArrayList<>();
        steps.add(new StepAx("Step A", project));
        steps.add(new StepBx("Step B"));
        steps.add(new StepCx("Step C"));
        steps.add(new StepDx("Step D"));

        _AbstractExWizard wizard = new _AbstractExWizard("AbstractEx Wizard", project, steps);
        wizard.show();

    }
}
