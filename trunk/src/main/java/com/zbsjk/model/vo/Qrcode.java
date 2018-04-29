package com.zbsjk.model.vo;



public class Qrcode {

	private char qrcodeErrorCorrect;
	
	private char qrcodeEncodeMode;
	
	private int qrcodeVersion;

	public char getQrcodeErrorCorrect() {
		return qrcodeErrorCorrect;
	}

	public void setQrcodeErrorCorrect(char qrcodeErrorCorrect) {
		this.qrcodeErrorCorrect = qrcodeErrorCorrect;
	}

	public char getQrcodeEncodeMode() {
		return qrcodeEncodeMode;
	}

	public void setQrcodeEncodeMode(char qrcodeEncodeMode) {
		this.qrcodeEncodeMode = qrcodeEncodeMode;
	}

	public int getQrcodeVersion() {
		return qrcodeVersion;
	}

	public void setQrcodeVersion(int qrcodeVersion) {
		this.qrcodeVersion = qrcodeVersion;
	}


}
