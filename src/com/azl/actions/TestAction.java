package com.azl.actions;

import com.intellij.icons.AllIcons;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;

/**
 * Created by jazl on 7/1/2017.
 */
public class TestAction extends AnAction {

    private int updateCalledCnt = 0;

    @Override
    public void update(AnActionEvent e) {
        System.out.println("updated called: "+(++updateCalledCnt));
        PsiClass psiClass = getPsiClassFromContext(e);
        e.getPresentation().setEnabled(psiClass != null);

        Navigatable navigatable = e.getData(CommonDataKeys.NAVIGATABLE);
        e.getPresentation().setEnabledAndVisible(navigatable != null);
        e.getPresentation().setIcon(AllIcons.General.Gear);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        //showAsNotification(e);
        showStuff(e);
    }

    private void showAsNotification(AnActionEvent e) {
        Project project = e.getProject();
        PsiClass psiClass = getPsiClassFromContext(e);

        String projectName = project.getName();
        StringBuilder sb = new StringBuilder();
        VirtualFile[] vfs = ProjectRootManager.getInstance(project).getContentSourceRoots();
        for(VirtualFile v: vfs){
            sb.append(v.getUrl()).append("\n");
        }
        showNotification(project, "VirtualFile Info", sb.toString());
    }

    private void showStuff(AnActionEvent e) {
        Navigatable navigatable = e.getData(CommonDataKeys.NAVIGATABLE);
        if(navigatable != null) {
            Messages.showDialog(navigatable.toString(), "Selected element:", new String[] {"Okay"}, -1, null);
        }
    }

    private PsiClass getPsiClassFromContext(AnActionEvent e) {
        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if(psiFile == null || editor == null){
            return null;
        }

        int offset = editor.getCaretModel().getOffset();
        PsiElement elementAt = psiFile.findElementAt(offset);

        PsiClass psiClass = PsiTreeUtil.getParentOfType(elementAt, PsiClass.class);
        return psiClass;
    }

    private void showNotification(Project project, String title, String message) {
        final Notification notification = new Notification("testdata", title, message, NotificationType.INFORMATION);
        Notifications.Bus.notify(notification, project);
    }
}
