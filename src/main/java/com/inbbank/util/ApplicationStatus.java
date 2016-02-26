package com.inbbank.util;

public enum ApplicationStatus {
	REJECTED("Rejected"), APPROVED("approved");

	private final String value;

	private ApplicationStatus(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return getValue();
	}
}
