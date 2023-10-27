package com.learnthistime.learnthistime.models;

public class CloudVendor {
	private String vendorId;
	private String name;
	public CloudVendor() {
		this.setVendorId("1024");
		this.setName("Unknown");
	}
	public CloudVendor(String vendorId, String name) {
		this.setVendorId(vendorId);
		this.setName(name);
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
