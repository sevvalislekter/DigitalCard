package com.cardvisit.services.impl;

import org.springframework.stereotype.Service;
import com.cardvisit.entity.AppConfigEntity;
import com.cardvisit.repository.AppConfigRepository;
import com.cardvisit.services.AppConfigEntityService;

@Service
public class AppConfigEntityServiceImpl implements AppConfigEntityService {

    private final AppConfigRepository appConfigRepository;

    public AppConfigEntityServiceImpl(AppConfigRepository appConfigRepository) {
        this.appConfigRepository = appConfigRepository;
    }

    @Override
    public String getConfigValue(String key) {
        AppConfigEntity entity = appConfigRepository.findByConfigKey(key);
        if (entity != null) {
            return entity.getConfigValue();
        }
        return null;
    }
}
