package com.azl.custom;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class FileTypeTreeCellEditor extends AbstractCellEditor implements TreeCellEditor {
	
	private FileTypeTreeCellRenderer renderer;
	private JCheckBox checkBox;
	private FileTypeInfo info;
	
	public FileTypeTreeCellEditor() {
		renderer = new FileTypeTreeCellRenderer();
	}

	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value,
			boolean isSelected, boolean expanded, boolean leaf, int row) {
		
		Component component = renderer.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, true);
		
		if(leaf) {
			
			DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)value;
			info = (FileTypeInfo)treeNode.getUserObject();
			
			checkBox = (JCheckBox)component;
			
			ItemListener itemListener = new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					fireEditingStopped();
					checkBox.removeItemListener(this);
				}
			};
			
			checkBox.addItemListener(itemListener);
		}
		
		// TODO Auto-generated method stub
		return component;
	}

	@Override
	public Object getCellEditorValue() {
		info.setChecked(checkBox.isSelected());
		return info;
	}

	@Override
	public boolean isCellEditable(EventObject event) {
		
		if(!(event instanceof MouseEvent)) return false;
		
		MouseEvent mouseEvent = (MouseEvent)event;
		JTree tree = (JTree)event.getSource();
		
		TreePath path = tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
		
		if(path == null) return false;
		
		Object lastComponent = path.getLastPathComponent();
		
		if(lastComponent == null) return false;
		
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)lastComponent;
		
		return treeNode.isLeaf();
	}

	
}
