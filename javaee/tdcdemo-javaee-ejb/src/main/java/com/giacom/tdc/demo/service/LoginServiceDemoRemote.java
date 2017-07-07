package com.giacom.tdc.demo.service;

import javax.ejb.Remote;

@Remote
public interface LoginServiceDemoRemote {

	 String getUser();
	
}
