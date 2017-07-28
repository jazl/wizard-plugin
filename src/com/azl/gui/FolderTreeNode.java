package com.azl.gui;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Created by jazl on 6/3/17.
 */
public class FolderTreeNode implements TreeNode {

    ArrayList<TreeNode> list;

    public FolderTreeNode() {
        list = new ArrayList<TreeNode>();
        list.add(new FolderTreeNode());
        list.add(new FolderTreeNode());
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return list.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return list.size();
    }

    @Override
    public TreeNode getParent() {
        return null;
    }

    @Override
    public int getIndex(TreeNode node) {
        return 0;
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Enumeration children() {
        return Collections.enumeration(list);
    }
}
