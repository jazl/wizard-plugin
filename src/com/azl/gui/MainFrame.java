package com.azl.gui;

import com.intellij.ui.CheckboxTree;
import com.intellij.ui.CheckedTreeNode;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.treeStructure.Tree;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;

public class MainFrame extends JFrame {

	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private com.intellij.ui.CheckBoxList<Object> cbl;

	private static class AppRenderer extends CheckboxTree.CheckboxTreeCellRenderer {
		private AppRenderer() {
			super(true, true);
		}

		@Override
		public void customizeRenderer(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
			getTextRenderer().append(value.toString());
		}
	}

	public MainFrame() {
		super("Hello World");

		setLayout(new BorderLayout());
		setBounds(100,100,400,400);

		toolbar = new Toolbar();
		textPanel = new TextPanel();
		formPanel = new FormPanel();
		cbl = new com.intellij.ui.CheckBoxList<Object>();
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

		CheckboxTree cbt = new CheckboxTree(new AppRenderer(), r2);

//		toolbar.setStringListener(new StringListener() {
//			public void textEmitted(String text) {
//				textPanel.appendText(text);
//			}
//		});
//
		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCat = e.getAgeCategory();

				textPanel.appendText(name + ": " + occupation + ": " + ageCat
						+ "\n");
			}
		});

		add(formPanel, BorderLayout.WEST);
		add(toolbar, BorderLayout.NORTH);
		add(cbt, BorderLayout.CENTER);

//		setSize(600, 500);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setVisible(true);
	}
}
