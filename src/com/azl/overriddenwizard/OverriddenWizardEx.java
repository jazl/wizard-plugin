package com.azl.overriddenwizard;

import com.intellij.ide.wizard.AbstractWizardEx;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ValidationInfo;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OverriddenWizardEx extends AbstractWizardEx {
    public OverriddenWizardEx(String title, @Nullable Project project, List<? extends OverriddenWizardStepBase> steps) {
        super(title, project, steps);
        //init();
        //initValidation();
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        System.out.println("doValidate called from _AbstractExWizard");
        return super.doValidate();
    }

    @Override
    public void updateButtons(boolean lastStep, boolean canGoNext, boolean firstStep) {
        System.out.println("updateButtons called!");
        super.updateButtons(lastStep, canGoNext(), firstStep);
    }

    @Override
    protected boolean canGoNext() {
        System.out.println("canGoNext called!");
        super.canGoNext();
        return true;
    }
}
