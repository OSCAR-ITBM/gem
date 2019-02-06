package com.gem.sistema.ennum.constans;

public enum ConstansEnum {
	TYPE_PASS("PASSWORD"),
	KEY_PASS_ACUM ("PASS_ACUMULACION");
	
	
	private String value;
	
	private ConstansEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	

}
