package jp.sourceforge.jpmobileutil;

public enum MobileCarrier {
	DOCOMO("docomo"), SOFTBANK("softbank"), AU("au"), UNKNOWN("unknown"), WILLCOM("willcom");

	private String name;

	private MobileCarrier(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}