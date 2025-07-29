package com.cardvisit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cardvisit.entity.AppConfigEntity;




public interface AppConfigRepository  extends  JpaRepository<AppConfigEntity, Integer>{
	AppConfigEntity findByConfigKey(String configKey);
   
}
