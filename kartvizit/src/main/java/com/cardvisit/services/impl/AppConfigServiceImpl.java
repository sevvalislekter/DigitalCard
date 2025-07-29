package com.cardvisit.services.impl;

import org.springframework.stereotype.Service;

import com.cardvisit.entity.AppConfigEntity;
import com.cardvisit.repository.AppConfigRepository;
import com.cardvisit.services.AppConfigService;

@Service
public class AppConfigServiceImpl implements AppConfigService{
	
    private final AppConfigRepository appConfigRepository;
    
    public AppConfigServiceImpl(AppConfigRepository appConfigRepository) {
    	this.appConfigRepository=appConfigRepository;
    }
    @Override
    public String GetConfigValue(String key) {
    	AppConfigEntity entity=appConfigRepository.findByConfigKey(key);
    	if(entity!=null) {
    		return entity.getConfigValue();
    	}
    	return null;
    }
}
