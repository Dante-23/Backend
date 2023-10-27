package com.learnthistime.learnthistime.controllers;

import com.learnthistime.learnthistime.models.CloudVendor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorApiService {
	
	private static final int VENDOR_COUNT = 10;
	private int index;
	CloudVendor vendors[];
	
	public CloudVendorApiService() {
		System.out.println("Constructor called");
		vendors = new CloudVendor[VENDOR_COUNT];
		index = 0;
	}
	
	@GetMapping("{vendorId}")
	public CloudVendor getCloudVendor(@PathVariable("vendorId") String vendorId) {
		System.out.println("Got request for: " + vendorId);
		for (int i = 0; i < index; i++) {
			if (vendors[i].getVendorId().equalsIgnoreCase(vendorId)) {
				return vendors[i];
			}
		}
		return null;
	}
	
	@GetMapping()
	public CloudVendor[] getAllCloudVendors() {
		return vendors;
	}
	
	@PostMapping()
	public String addCloudVendor(@RequestBody CloudVendor vendor) {
		if (index == VENDOR_COUNT) return "Storage full";
		vendors[index++] = vendor;
		return "Added";
	}
}
