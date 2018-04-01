package com.ibm.ns.schedule;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ibm.ns.schedule.db.DataBaseService;
import com.ibm.ns.schedule.db.MongoDBDataBaseDAO;
import com.ibm.ns.schedule.importer.ImportTask;
import com.ibm.ns.schedule.logger.LogService;

@Configuration
@ComponentScan(basePackageClasses= {ImportTask.class,DataBaseService.class, LogService.class, MongoDBDataBaseDAO.class})
public class ImporterConfig {

}
