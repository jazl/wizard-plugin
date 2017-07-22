package com.azl.overriddenwizard;

import com.azl.common.SharedObject;
import com.intellij.ide.wizard.AbstractWizardEx;
import com.intellij.ide.wizard.AbstractWizardStepEx;
import org.jetbrains.annotations.Nullable;

public abstract class OverriddenWizardStepBase extends AbstractWizardStepEx {
    protected boolean isCompleteBase = false;
    public OverriddenWizardStepBase(@Nullable String title) {
        super(title);
    }
}
