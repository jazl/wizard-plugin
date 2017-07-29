package com.azl.panels;

import com.azl.custom.DialogWithExtraButton;
import com.azl.gui.MainFrame;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.CheckboxTree;
import com.intellij.ui.CheckedTreeNode;
import com.intellij.ui.treeStructure.Tree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jazl on 6/29/2017.
 */
public class PanelA extends JPanel {
    private JCheckBox chkDoNotGo;
    private boolean skip = false;
    private boolean showWebview = true;
    private Project project;
    private boolean doNotGoNext = false;
    private CheckboxTree cbt;

    public PanelA() {
        final FileChooserDescriptor fcd = new FileChooserDescriptor(true,true,true,true,true,true);

        //add(new Label("Panel A"));
        JButton button = new JButton("Choose...");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VirtualFile[] virtualFile = FileChooser.chooseFiles(fcd,null,null);
                ConsoleView console = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
                ConsoleViewContentType contentType = new ConsoleViewContentType("someName", new TextAttributes());
                console.print("oh hai sharon!", contentType);
            }
        });
        add(button);
        JTextField fileInfo = new JTextField();

        // TODO: look into why this component is causing the following error
        // [  23358]  ERROR - i.ui.ComponentWithBrowseButton - multiple selection not supported
//        TextFieldWithBrowseButton textFieldWithBrowseButton = new TextFieldWithBrowseButton(fileInfo);
//        textFieldWithBrowseButton.addBrowseFolderListener(new TextBrowseFolderListener(fcd));
//
//        add(textFieldWithBrowseButton);
//        setSize(new Dimension(500,500));
//        add(textFieldWithBrowseButton);

        JCheckBox chkSkip = new JCheckBox("Skip next step");
        chkSkip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                skip = chkSkip.isSelected();
            }
        });
        add(chkSkip);

        JCheckBox chkShowWebView = new JCheckBox("Show WebView");
        chkShowWebView.setSelected(true);
        chkShowWebView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWebview = chkShowWebView.isSelected();
            }
        });
        add(chkShowWebView);

        chkDoNotGo = new JCheckBox("Do not go next");
        chkDoNotGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doNotGoNext = chkDoNotGo.isSelected();
            }
        });
        add(chkDoNotGo);

        JButton btnShowDialogWithActions = new JButton("Dialog w/ custom actions");
        add(btnShowDialogWithActions);
        btnShowDialogWithActions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DialogWithExtraButton(null).show();
            }
        });

        add(createCheckBoxTreePanel());

        JButton btnShowSelected = new JButton("Show selected");
        btnShowSelected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelA.this.showSelected();
            }
        });
        add(btnShowSelected);

        setSize(new Dimension(500,500));
    }
    public void setProject(Project project) {
        this.project = project;
    }

    public boolean skipNextStep() {
        return skip;
    }

    public boolean showWebView() {
        return showWebview;
    }

    public boolean getDoNotGoNext() { return doNotGoNext;}

    public JComponent getFocusComponent() {
        return chkDoNotGo;
    }

    private static class AppRenderer extends CheckboxTree.CheckboxTreeCellRenderer {
        private AppRenderer() {
            super(true, true);
        }

        @Override
        public void customizeRenderer(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            getTextRenderer().append(value.toString());
        }
    }

    private JPanel createCheckBoxTreePanel() {
        JPanel panel = new JPanel();

        com.intellij.ui.CheckBoxList<Object> cbl = new com.intellij.ui.CheckBoxList<Object>();
        cbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        com.intellij.ui.CollectionListModel<Object> model = new com.intellij.ui.CollectionListModel<Object>();
        model.add(new JCheckBox("Sarah"));
        model.add(new JCheckBox("Sharon"));
        model.add(new JCheckBox("Lindsay"));
        model.add(new JCheckBox("Lyubov"));
        model.add(new JCheckBox("Mary"));
        model.add(new JCheckBox("Veronica"));
        model.add(new JCheckBox("Becca"));
        model.add(new JCheckBox("Erica"));
        model.add(new JCheckBox("Tina"));
        cbl.setModel(model);

        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        //create the child nodes
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        vegetableNode.add(new DefaultMutableTreeNode("Capsicum"));
        vegetableNode.add(new DefaultMutableTreeNode("Carrot"));
        vegetableNode.add(new DefaultMutableTreeNode("Tomato"));
        vegetableNode.add(new DefaultMutableTreeNode("Potato"));

        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
        fruitNode.add(new DefaultMutableTreeNode("Banana"));
        fruitNode.add(new DefaultMutableTreeNode("Mango"));
        fruitNode.add(new DefaultMutableTreeNode("Apple"));
        fruitNode.add(new DefaultMutableTreeNode("Grapes"));
        fruitNode.add(new DefaultMutableTreeNode("Orange"));

        DefaultMutableTreeNode girlsNode = new DefaultMutableTreeNode("Girls");
        girlsNode.add(new DefaultMutableTreeNode("Sharon"));
        girlsNode.add(new DefaultMutableTreeNode("Sarah"));
        girlsNode.add(new DefaultMutableTreeNode("Mary"));

        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);
        root.add(girlsNode);

        //create the tree by passing in the root node
        Tree tree = new Tree(root);

        CheckedTreeNode r2 = new CheckedTreeNode("Root");
        r2.setParent(null);
        r2.setAllowsChildren(true);

        CheckedTreeNode g2 = new CheckedTreeNode("Girls");
        g2.add(new CheckedTreeNode("Lindsay"));
        g2.add(new CheckedTreeNode("Sharon"));
        g2.add(new CheckedTreeNode("Sarah"));
        g2.add(new CheckedTreeNode("Mary"));

        CheckedTreeNode b1 = new CheckedTreeNode("Boys");
        b1.add(new CheckedTreeNode("Chuck"));
        b1.add(new CheckedTreeNode("Elliott"));

        r2.add(g2);
        r2.add(b1);

        cbt = new CheckboxTree(new PanelA.AppRenderer(), r2);

        panel.setLayout(new BorderLayout());
        panel.add(cbt, BorderLayout.CENTER);

        return panel;
    }

    public void showSelected() {
        CheckedTreeNode[] selectedNodes = cbt.getSelectedNodes(CheckedTreeNode.class, null);
        System.out.println("selectedNodes? "+selectedNodes.length);
    }
}
