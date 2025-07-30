package com.cardvisit.services.impl;

import org.springframework.stereotype.Service;
import com.cardvisit.services.AppConfigEntityService;
import com.cardvisit.services.AppConfigService;

@Service
public class AppConfigServiceImpl implements AppConfigService {

    private final AppConfigEntityService appConfigEntityService;

    public AppConfigServiceImpl(AppConfigEntityService appConfigEntityService) {
        this.appConfigEntityService = appConfigEntityService;
    }

    @Override
    public String getConfigValue(String key) {
        return appConfigEntityService.getConfigValue(key);
    }
}
