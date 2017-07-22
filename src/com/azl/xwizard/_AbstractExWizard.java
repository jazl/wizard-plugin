package com.azl.xwizard;

import com.azl.common.WizardStep;
import com.intellij.ide.wizard.AbstractWizardEx;
import com.intellij.ide.wizard.AbstractWizardStepEx;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ValidationInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by jazl on 6/29/2017.
 */
public class _AbstractExWizard extends AbstractWizardEx {
    public _AbstractExWizard(String title, @Nullable Project project, List<? extends AbstractWizardStepEx> steps) {
        super(title, project, steps);
        //init();
        //initValidation();
    }

    @Override
    protected void doPreviousAction() {
        super.doPreviousAction();
    }

    @Override
    protected void doNextAction() {
        super.doNextAction();
    }

    @Override
    public void addStep(@NotNull AbstractWizardStepEx step) {
        super.addStep(step);
        ((WizardStep)step).setWizardRef(this);
        System.out.println("ADDING STEP "+step.getStepId()+" and setting wizard ref");
    }

    @Override
    protected boolean canGoNext() {
        //super.canGoNext();
        return false;
    }

    @Override
    protected boolean canFinish() {
        super.canFinish();
        return false;
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        System.out.println("doValidate called from _AbstractExWizard");
        return super.doValidate();
    }
}
