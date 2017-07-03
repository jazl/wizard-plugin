package com.azl.common;

import com.intellij.ide.wizard.AbstractWizardStepEx;
import org.jetbrains.annotations.Nullable;

/**
 * Created by jazl on 6/30/2017.
 */
public abstract class WizardStep extends AbstractWizardStepEx {

    public WizardStep(@Nullable String title) {
        super(title);
    }
}
