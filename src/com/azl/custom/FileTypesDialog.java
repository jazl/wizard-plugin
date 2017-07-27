package com.azl.custom;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class FileTypesDialog extends DialogWrapper {

    private FileTypeDialogPanel fileTypeDialogPanel;
    private static final String[] BINARY_TYPES = new String[] {"jar", "war", "ear", "class", "dll", "exe"};
    private static final TreeSet<String> FILE_TYPES = new TreeSet<String>(
        Arrays.asList(
            "class without source",
            "htm",
            "html",
            "jardesc",
            "jpage",
            "shtml",
            "xml",
            "doc",
            "docx",
            "xmi",
            "log",
            "target",
            "emof",
            "properties",
            "e4xmi",
            "png",
            "jpg",
            "jpeg",
            "gif",
            "bmp",
            "ico",
            "ecore",
            "prefs",
            "class",
            "macrodef",
            "ent",
            "ant",
            "exsd",
            "product",
            "java",
            "txt",
            "jar",
            "war",
            "ear",
            "dll",
            "exe"
        )
    );
    private DefaultTreeModel defaultTreeModel;
    private ArrayList<String> selectedExtensions;

    public FileTypesDialog(final Project project, final ArrayList<String> currentlySelectedExtentions) {
        super(false);
        init();
        setTitle("Select Types");

        if(currentlySelectedExtentions != null) {
            selectedExtensions = currentlySelectedExtentions;
        }
        else {
            selectedExtensions = new ArrayList<String>();
        }
        defaultTreeModel = new DefaultTreeModel(buildFileTypesTree(selectedExtensions));
        fileTypeDialogPanel.setModel(defaultTreeModel);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        fileTypeDialogPanel = new FileTypeDialogPanel();
        return fileTypeDialogPanel;
    }

    @Override
    protected void doOKAction() {
        super.doOKAction();
        selectedExtensions = fileTypeDialogPanel.getSelectedFileTypes();
    }

    public ArrayList<String> getSelectedExtensions(){
        return selectedExtensions;
    }

    private DefaultMutableTreeNode buildFileTypesTree(final ArrayList<String> selected) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("File Types");

        // TODO: where to get list of file types and icons?
        // TODO: set checked state
        for(String extension:FILE_TYPES) {
            rootNode.add(new DefaultMutableTreeNode(buildFileTypeInfo("."+extension, selected)));
        }
        return rootNode;
    }
    
    private FileTypeInfo buildFileTypeInfo(String name, ArrayList<String> selectedList) {
        return new FileTypeInfo("*"+name, selectedList.contains(name));
    }
}

class FileTypeDialogPanel extends JPanel {

    private JTree treeFiles;

    public FileTypeDialogPanel() {
        setMinimumSize(new Dimension(350,200));

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0, 339, 30, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JScrollPane scrTreeFiles = new JScrollPane();
        GridBagConstraints gbc_scrTreeFiles = new GridBagConstraints();
        gbc_scrTreeFiles.insets = new Insets(0, 0, 5, 0);
        gbc_scrTreeFiles.fill = GridBagConstraints.BOTH;
        gbc_scrTreeFiles.gridx = 0;
        gbc_scrTreeFiles.gridy = 1;
        add(scrTreeFiles, gbc_scrTreeFiles);

        treeFiles = new JTree();
        treeFiles.setRootVisible(false);
        treeFiles.setCellRenderer(new FileTypeTreeCellRenderer());
        treeFiles.setCellEditor(new FileTypeTreeCellEditor());
        treeFiles.setEditable(true);
        treeFiles.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);

        scrTreeFiles.setViewportView(treeFiles);

        JPanel pnlSelectButtons = new JPanel();
        GridBagConstraints gbc_pnlSelectButtons = new GridBagConstraints();
        gbc_pnlSelectButtons.fill = GridBagConstraints.BOTH;
        gbc_pnlSelectButtons.gridx = 0;
        gbc_pnlSelectButtons.gridy = 2;
        add(pnlSelectButtons, gbc_pnlSelectButtons);
        pnlSelectButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton btnSelectAll = new JButton("Select All");
        pnlSelectButtons.add(btnSelectAll);

        btnSelectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllChecked(true);
            }
        });

        JButton btnDeselectAll = new JButton("Deselect All");
        pnlSelectButtons.add(btnDeselectAll);

        btnDeselectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllChecked(false);
            }
        });

    }

    public void setModel(DefaultTreeModel defaultTreeModel) {
        treeFiles.setModel(defaultTreeModel);
    }

    public TreeModel getModel() {
        return treeFiles.getModel();
    }

    public ArrayList<String> getSelectedFileTypes() {
        ArrayList<String> fileTypes = new ArrayList<>();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeFiles.getModel().getRoot();
        Enumeration e = root.children();
        while(e.hasMoreElements()) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode)e.nextElement();
            FileTypeInfo fileTypeInfo = (FileTypeInfo)child.getUserObject();
            if(fileTypeInfo.isChecked()) {
                fileTypes.add(fileTypeInfo.toString().substring(1));
            }
        }
        return fileTypes;
    }

    public void setAllChecked(boolean checked) {
        DefaultTreeModel model = (DefaultTreeModel)treeFiles.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
        Enumeration e = root.children();
        while(e.hasMoreElements()) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode)e.nextElement();
            ((FileTypeInfo)child.getUserObject()).setChecked(checked);
        }
        model.reload();
    }
}
