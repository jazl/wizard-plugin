package com.azl.custom;

public class FileTypeInfo {
	private String name;
	private String icon;
	private boolean isBinary;
	private boolean checked;

	public FileTypeInfo(String name, boolean checked) {
		this.name = name;
		this.checked = checked;
	}

	public String toString() {
		return name;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isBinary() {
		return isBinary;
	}

	public void setBinary(boolean binary) {
		isBinary = binary;
	}

}