package com.azl.overriddenwizard;

import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jazl on 7/3/17.
 */
public class OverriddenWizard {

    public static void show(Project project) {
        List<OverriddenWizardStepBase> steps = new ArrayList<>();
        steps.add(new Step1("Step 1"));
        steps.add(new Step2("Step 2"));
        steps.add(new Step3("Step 3"));
        steps.add(new Step4("Step 4"));

        OverriddenWizardEx wizard = new OverriddenWizardEx("Overridden Wizard", project, steps);
        wizard.show();

    }
}
