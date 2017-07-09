package com.azl.actions;

import com.azl.abstractwizard._AbstractWizard;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;

import static javafx.application.Application.launch;

/**
 * Created by jazl on 6/29/2017.
 */
@SuppressWarnings("ALL")
public class ActionA extends AnAction {

    @Override
    public void update(AnActionEvent e) {
        Project project = e.getProject();

        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);

        if(psiFile == null || editor == null){
            e.getPresentation().setEnabled(false);
            return;
        }

        int offset = editor.getCaretModel().getOffset();
        PsiElement elementAt = psiFile.findElementAt(offset);

//        String message = "I CAN'T BELIEVE IT'S NOT BUTTER";
//        final Notification notification = new Notification("testdata", "WHAT'S UP!!!", message, NotificationType.INFORMATION);
//        Notifications.Bus.notify(notification, project);

    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();

        Messages.showMessageDialog(project, "Will now launch JavaFx app???","Information",Messages.getInformationIcon());

        javafx.application.Application.launch(com.azl.fxApp.class);


//        _AbstractWizard wizard = new _AbstractWizard("Wizard", project);
//        wizard.show();

        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if(psiFile == null || editor == null){
            e.getPresentation().setEnabled(false);
            return;
        }

        int offset = editor.getCaretModel().getOffset();
        PsiElement elementAt = psiFile.findElementAt(offset);

        PsiClass parentOfType = PsiTreeUtil.getParentOfType(elementAt, PsiClass.class);
        if(parentOfType == null) {
            e.getPresentation().setEnabled(false);
            return;
        }
    }
}
