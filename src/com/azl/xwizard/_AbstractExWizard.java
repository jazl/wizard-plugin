package com.azl.xwizard;

import com.intellij.ide.wizard.AbstractWizardEx;
import com.intellij.ide.wizard.AbstractWizardStepEx;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by jazl on 6/29/2017.
 */
public class _AbstractExWizard extends AbstractWizardEx {
    public _AbstractExWizard(String title, @Nullable Project project, List<? extends AbstractWizardStepEx> steps) {
        super(title, project, steps);
    }

    @Override
    protected boolean canGoNext() {
        super.canGoNext();
        return true;
    }
}
