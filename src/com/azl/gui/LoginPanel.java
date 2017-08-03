package com.azl.gui;

import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.util.net.HttpProxyConfigurable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class LoginPanel extends JPanel {
	private JComboBox cboAPIURL;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtTenantId;
	private JTextField txtSSOURL;

	private JRadioButton rbUseSSO;
	private JRadioButton rbUseCredentials;
	private JCheckBox chkRememberLoginInfo;

	private final ButtonGroup buttonGroup = new ButtonGroup();

	public enum LOGIN_MODE {
		CREDENTIALS, SSO
	}
	public LoginPanel(Project project) {
		// Layout for root panel
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{104, 66, 0, 0, 0, 23, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		// Page header
		FodUploadHeader header = new FodUploadHeader("Login", "");
		GridBagConstraints gbc_header = new GridBagConstraints();
		gbc_header.fill = GridBagConstraints.BOTH;
		gbc_header.insets = new Insets(0, 0, 5, 0);
		gbc_header.gridx = 0;
		gbc_header.gridy = 0;
		add(header, gbc_header);

		// Page description
		JPanel pnlDescription = new JPanel();
		GridBagConstraints gbc_pnlDescription = new GridBagConstraints();
		gbc_pnlDescription.anchor = GridBagConstraints.NORTH;
		gbc_pnlDescription.insets = new Insets(5, 10, 5, 10);
		gbc_pnlDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnlDescription.gridx = 0;
		gbc_pnlDescription.gridy = 1;
		add(pnlDescription, gbc_pnlDescription);
		pnlDescription.setLayout(new BorderLayout(0, 0));

		JLabel lblDescription = new JLabel("<html>HPE Security Fortify on Demand is an application security program management and testing SaaS solution that<br>enables customers to easily create, supplement, and expand a Software Security Assurance program through a<br>managed service dedicated to best-in-class delivery and customer support. To learn more about Fortify on Demand<br>or request a free trial, please visit our website or contact your sales representative.</html>");
		pnlDescription.add(lblDescription, BorderLayout.NORTH);

		// API Root URL
		GridBagConstraints gbc_pnlURL = new GridBagConstraints();
		gbc_pnlURL.anchor = GridBagConstraints.NORTH;
		gbc_pnlURL.insets = new Insets(0, 5, 5, 5);
		gbc_pnlURL.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnlURL.gridx = 0;
		gbc_pnlURL.gridy = 2;
		add(createUrlPanel(project), gbc_pnlURL);

		// Fortify on Demand Credentials
		GridBagConstraints gbc_pnlFodCredentials = new GridBagConstraints();
		gbc_pnlFodCredentials.anchor = GridBagConstraints.NORTHEAST;
		gbc_pnlFodCredentials.insets = new Insets(0, 5, 5, 5);
		gbc_pnlFodCredentials.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnlFodCredentials.gridx = 0;
		gbc_pnlFodCredentials.gridy = 3;
		add(createFodCredentialsPanel(), gbc_pnlFodCredentials);

		// Use Identity Provider
		GridBagConstraints gbc_pnlSSOCredentials = new GridBagConstraints();
		gbc_pnlSSOCredentials.insets = new Insets(0, 5, 5, 5);
		gbc_pnlSSOCredentials.fill = GridBagConstraints.BOTH;
		gbc_pnlSSOCredentials.gridx = 0;
		gbc_pnlSSOCredentials.gridy = 4;
		add(createSsoPanel(), gbc_pnlSSOCredentials);

		// Remember Login Credentials
		chkRememberLoginInfo = new JCheckBox("Remember login credentials");
		chkRememberLoginInfo.setName("LoginPanel.chkRememberLoginInfo");
		chkRememberLoginInfo.setSelected(true);
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxNewCheckBox.insets = new Insets(0, 5, 5, 0);
		gbc_chckbxNewCheckBox.gridx = 0;
		gbc_chckbxNewCheckBox.gridy = 5;
		add(chkRememberLoginInfo, gbc_chckbxNewCheckBox);

		JLabel proxySettingsLink = new JLabel();
		proxySettingsLink.setText("<html><a href=''>Configure Network Connection</a></html>");
		proxySettingsLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowSettingsUtil.getInstance().showSettingsDialog(project, HttpProxyConfigurable.class);
			}
		});
		proxySettingsLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_configureNetworkConnection = new GridBagConstraints();
		gbc_configureNetworkConnection.insets = new Insets(0, 5, 5, 0);
		gbc_configureNetworkConnection.gridx = 0;
		gbc_configureNetworkConnection.gridy = 99;
		gbc_configureNetworkConnection.anchor = GridBagConstraints.SOUTHWEST;
		gbc_configureNetworkConnection.fill = GridBagConstraints.HORIZONTAL;
		add(proxySettingsLink, gbc_configureNetworkConnection);

		setDefaultFormState();
		setActionListeners();
	}

	private JPanel createUrlPanel(final Project project) {
		JPanel pnlURL = new JPanel();
		GridBagConstraints gbc_pnlURL = new GridBagConstraints();

		GridBagLayout gbl_pnlURL = new GridBagLayout();
		gbl_pnlURL.columnWidths = new int[] {0, 525};
		//gbl_pnlURL.rowHeights = new int[] {15, 17};
		gbl_pnlURL.columnWeights = new double[]{1.0, 1.0};
		gbl_pnlURL.rowWeights = new double[]{0.0, 0.0};
		pnlURL.setLayout(gbl_pnlURL);

		JLabel lblAPIRootURL = new JLabel("API Root:");
		GridBagConstraints gbc_lblAPIRootURL = new GridBagConstraints();
		gbc_lblAPIRootURL.anchor = GridBagConstraints.WEST;
		gbc_lblAPIRootURL.insets = new Insets(0, 0, 5, 5);
		gbc_lblAPIRootURL.fill = GridBagConstraints.VERTICAL;
		gbc_lblAPIRootURL.gridx = 0;
		gbc_lblAPIRootURL.gridy = 0;
		pnlURL.add(lblAPIRootURL, gbc_lblAPIRootURL);

		cboAPIURL = new JComboBox();
		cboAPIURL.setName("LoginPanel.cboAPIURL");
		cboAPIURL.setEditable(true);
		// TODO: doesn't work yet for editable combo box
		//cboAPIURL.addMouseListener(new ContextMenuTextMouseListener());

		//cboAPIURL.setModel(new DefaultComboBoxModel<>(LoginURLUtil.FOD_URLS));

		GridBagConstraints gbc_cboAPIURL = new GridBagConstraints();
		gbc_cboAPIURL.insets = new Insets(0, 0, 5, 0);
		gbc_cboAPIURL.anchor = GridBagConstraints.WEST;
		gbc_cboAPIURL.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboAPIURL.gridx = 1;
		gbc_cboAPIURL.gridy = 0;
		pnlURL.add(cboAPIURL, gbc_cboAPIURL);

		JLabel lnkClearHistory = new JLabel();
		lnkClearHistory.setText("<html><a href=''>Clear Portal History</a></html>");
		lnkClearHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowSettingsUtil.getInstance().showSettingsDialog(project, HttpProxyConfigurable.class);
			}
		});
		lnkClearHistory.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lnkClearHistory.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_clearHistory = new GridBagConstraints();
		gbc_clearHistory.insets = new Insets(0, 5, 5, 0);
		gbc_clearHistory.gridx = 1;
		gbc_clearHistory.gridy = 1;
		gbc_clearHistory.anchor = GridBagConstraints.EAST;
		gbc_clearHistory.fill = GridBagConstraints.HORIZONTAL;
		pnlURL.add(lnkClearHistory, gbc_clearHistory);

		return pnlURL;
	}

	private JPanel createFodCredentialsPanel() {
		JPanel pnlFodCredentials = new JPanel();
		pnlFodCredentials.setBorder(null);

		pnlFodCredentials.setLayout(new BorderLayout(0, 0));

		JPanel pnlUseCredentials = new JPanel();
		FlowLayout fl_pnlUseCredentials = (FlowLayout) pnlUseCredentials.getLayout();
		fl_pnlUseCredentials.setHgap(0);
		fl_pnlUseCredentials.setAlignment(FlowLayout.LEFT);
		pnlFodCredentials.add(pnlUseCredentials, BorderLayout.NORTH);

		rbUseCredentials = new JRadioButton("Use credentials");
		rbUseCredentials.setName("LoginPanel.rbUseCredentials");
		pnlUseCredentials.add(rbUseCredentials);
		buttonGroup.add(rbUseCredentials);

		JPanel pnlCredentialsFields = new JPanel();
		pnlCredentialsFields.setBorder(new EmptyBorder(0, 5, 5, 0));
		pnlFodCredentials.add(pnlCredentialsFields, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCredentialsFields = new GridBagLayout();
		gbl_pnlCredentialsFields.columnWidths = new int[]{75, 520, 0};
		gbl_pnlCredentialsFields.rowHeights = new int[]{20, 0, 0, 0};
		gbl_pnlCredentialsFields.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlCredentialsFields.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		pnlCredentialsFields.setLayout(gbl_pnlCredentialsFields);

		JLabel lblUsername = new JLabel("User Name");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		pnlCredentialsFields.add(lblUsername, gbc_lblUsername);

		txtUsername = new JTextField();
		txtUsername.setName("LoginPanel.txtUsername");
		//txtUsername.addMouseListener(new ContextMenuTextMouseListener());
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsername.gridx = 1;
		gbc_txtUsername.gridy = 0;
		pnlCredentialsFields.add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		pnlCredentialsFields.add(lblPassword, gbc_lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setName("LoginPanel.txtPassword");
		//txtPassword.addMouseListener(new ContextMenuTextMouseListener());
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.anchor = GridBagConstraints.NORTHEAST;
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtPassword.gridx = 1;
		gbc_txtPassword.gridy = 1;
		pnlCredentialsFields.add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);

		JLabel lblTenantId = new JLabel("Tenant id:");
		GridBagConstraints gbc_lblTenantId = new GridBagConstraints();
		gbc_lblTenantId.anchor = GridBagConstraints.WEST;
		gbc_lblTenantId.insets = new Insets(0, 0, 0, 5);
		gbc_lblTenantId.gridx = 0;
		gbc_lblTenantId.gridy = 2;
		pnlCredentialsFields.add(lblTenantId, gbc_lblTenantId);

		txtTenantId = new JTextField();
		txtTenantId.setName("LoginPanel.txtTenantId");
		//txtTenantId.addMouseListener(new ContextMenuTextMouseListener());
		GridBagConstraints gbc_txtTenantId = new GridBagConstraints();
		gbc_txtTenantId.anchor = GridBagConstraints.NORTHEAST;
		gbc_txtTenantId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTenantId.gridx = 1;
		gbc_txtTenantId.gridy = 2;
		pnlCredentialsFields.add(txtTenantId, gbc_txtTenantId);
		txtTenantId.setColumns(10);

		return pnlFodCredentials;
	}

	private JPanel createSsoPanel() {
		JPanel pnlSSOCredentials = new JPanel();
		pnlSSOCredentials.setEnabled(false);
		pnlSSOCredentials.setLayout(new BorderLayout(0, 0));

		JPanel pnlUseSSO = new JPanel();
		FlowLayout fl_pnlUseSSO = (FlowLayout) pnlUseSSO.getLayout();
		fl_pnlUseSSO.setHgap(0);
		fl_pnlUseSSO.setAlignment(FlowLayout.LEFT);
		pnlSSOCredentials.add(pnlUseSSO, BorderLayout.NORTH);

		rbUseSSO = new JRadioButton("Use SSO");
		rbUseSSO.setName("LoginPanel.rbUseSSO");
		pnlUseSSO.add(rbUseSSO);
		buttonGroup.add(rbUseSSO);

		JPanel pnlSSOFields = new JPanel();
		pnlSSOFields.setBorder(new EmptyBorder(0, 5, 5, 0));
		pnlSSOCredentials.add(pnlSSOFields, BorderLayout.CENTER);
		GridBagLayout gbl_pnlSSOFields = new GridBagLayout();
		gbl_pnlSSOFields.columnWidths = new int[]{75, 520, 0};
		gbl_pnlSSOFields.rowHeights = new int[]{0, 0};
		gbl_pnlSSOFields.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlSSOFields.rowWeights = new double[]{0.0, Double.MIN_VALUE};

		pnlSSOFields.setLayout(gbl_pnlSSOFields);

		JLabel lblSsoLoginUrl = new JLabel("SSO URL:");
		lblSsoLoginUrl.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblSsoLoginUrl = new GridBagConstraints();
		gbc_lblSsoLoginUrl.anchor = GridBagConstraints.WEST;
		gbc_lblSsoLoginUrl.insets = new Insets(0, 0, 0, 5);
		gbc_lblSsoLoginUrl.gridx = 0;
		gbc_lblSsoLoginUrl.gridy = 0;
		pnlSSOFields.add(lblSsoLoginUrl, gbc_lblSsoLoginUrl);

		txtSSOURL = new JTextField();
		txtSSOURL.setName("LoginPanel.txtSSOURL");
		//txtSSOURL.addMouseListener(new ContextMenuTextMouseListener());
		GridBagConstraints gbc_txtSSOURL = new GridBagConstraints();
		gbc_txtSSOURL.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSSOURL.gridx = 1;
		gbc_txtSSOURL.gridy = 0;
		pnlSSOFields.add(txtSSOURL, gbc_txtSSOURL);
		txtSSOURL.setColumns(10);

		return pnlSSOCredentials;
	}

	public String getUsername() {
		return txtUsername.getText().trim();
	}

	public String getPassword() {
		return txtPassword.getText().trim();
	}

	public String getTenantId() {
		return txtTenantId.getText().trim();
	}

	public String getSSOURL() {
		return txtSSOURL.getText().trim();
	}

	public String getAPIURL() {
		return cboAPIURL.getSelectedItem().toString();
	}

	public LOGIN_MODE getLoginMode() {
		if(rbUseCredentials.isSelected()){
			return LOGIN_MODE.CREDENTIALS;
		}
		return LOGIN_MODE.SSO;
	}

//	public void setWizardActionListener(WizardActionListener listener) {
//		this.wizardActionListener = listener;
//	}
//
//	@Override
//	public void setWizardButtonStateListener(WizardButtonStateListener listener) {
//		this.buttonStateListener = listener;
//	}

	public void clearCredentials() {
		txtUsername.setText("");
		txtTenantId.setText("");
		clearPassword();
	}

	public void clearPassword() {
		txtPassword.setText("");
	}

	private void setLoginMode() {
		boolean useCreds = getLoginMode()==LOGIN_MODE.CREDENTIALS;
		txtTenantId.setEnabled(useCreds);
		txtPassword.setEnabled(useCreds);
		txtUsername.setEnabled(useCreds);
		txtSSOURL.setEnabled(!useCreds);
	}

	private void setDefaultFormState() {
		setLoginMode();
	}

	private void setActionListeners() {
		rbUseSSO.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setLoginMode();
			}
		});
		rbUseCredentials.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setLoginMode();
			}
		});
	}

	public boolean canGoNext() {
		return doValidate()==null;
	}

	public ValidationInfo doValidate() {
		ValidationInfo validationInfo = null;
//
//		String messageString = null;
//		JComponent invalidField = null;
//		String apiUrl = cboAPIURL.getSelectedItem() != null ? cboAPIURL.getSelectedItem().toString().trim() : null;
//		String ssoUrl = txtSSOURL.getText().trim();
//		String userName = txtUsername.getText().trim();
//		String tenant = txtTenantId.getText().trim();
//		StringBuilder errorMessages = new StringBuilder();
//
//		if (rbUseCredentials.isSelected() && "".equals(txtPassword.getText().trim())) {
//			validationInfo = new ValidationInfo("Password cannot be empty", txtPassword);
//		}
//		else if(!LoginURLUtil.validateFoDGroup(apiUrl, ssoUrl, userName, tenant, rbUseSSO.isSelected(), errorMessages)) {
//			// Find component to mark in error
//			messageString = errorMessages.toString();
//			if("".equals(cboAPIURL.getSelectedItem().toString())){
//				invalidField = cboAPIURL;
//			}
//			if(rbUseSSO.isSelected() && "".equals(txtSSOURL.getText().trim())) {
//				validationInfo = new ValidationInfo(messageString, txtSSOURL);
//			}
//			if(rbUseCredentials.isSelected()) {
//				if ("".equals(txtTenantId.getText().trim())) {
//					invalidField = txtTenantId;
//				}
//				if ("".equals(txtPassword.getText().trim())){
//					invalidField = txtPassword;
//				}
//				if ("".equals(txtUsername.getText().trim())) {
//					invalidField = txtUsername;
//				}
////				if ("".equals(txtPassword.getText().trim())) {
////					messageString = "Password cannot be empty"; // not validated in validateFoDGroup
////					invalidField = txtPassword;
////				}
//			}
//			if(messageString != null) {
//				validationInfo = new ValidationInfo(messageString, invalidField);
//			}
//		}
//
		return validationInfo;
	}

	public boolean getRememberLoginInfo() {
		return chkRememberLoginInfo.isSelected();
	}

//	@Override
//	protected void restorePanelValues() {
//		super.restorePanelValues();
//		loadValueOrDefault(cboAPIURL, LoginURLUtil.FOD_URLS[LoginURLUtil.DEFAULT_URL]);
//		if(cboAPIURL.getItemCount() < LoginURLUtil.FOD_URLS.length) {
//			// Load default URLs for when wizard is launched for the very first time
//			cboAPIURL.setModel(new DefaultComboBoxModel<String>(LoginURLUtil.FOD_URLS));
//			cboAPIURL.setSelectedItem(LoginURLUtil.FOD_URLS[LoginURLUtil.DEFAULT_URL]);
//		}
//		loadValueOrDefault(rbUseSSO, false);
//		loadValueOrDefault(rbUseCredentials, true);
//		loadValueOrDefault(txtUsername, "");
//		//loadValueOrDefault(txtPassword, "");
//		loadValueOrDefault(txtTenantId, "");
//		loadValueOrDefault(txtSSOURL, "");
//		loadValueOrDefault(chkRememberLoginInfo, true);
//		setLoginMode();
//	}
//
//	@Override
//	protected void savePanelValues() {
//		try {
//			saveValue(rbUseSSO);
//			saveValue(rbUseCredentials);
//			saveValue(cboAPIURL);
//			saveValue(txtUsername);
//			//saveValue(txtPassword);
//			saveValue(txtTenantId);
//			saveValue(txtSSOURL);
//			saveValue(chkRememberLoginInfo);
//			super.savePanelValues();
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		GridVertLayoutTest mainFrame = new GridVertLayoutTest();
		mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setSize(600,500);
		mainFrame.setVisible(true);
	}
}