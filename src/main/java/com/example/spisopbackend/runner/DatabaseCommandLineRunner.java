package com.example.spisopbackend.runner;

import com.example.spisopbackend.service.DatabaseService;
import com.example.spisopbackend.utils.aspects.logs.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCommandLineRunner implements Logger, CommandLineRunner {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private Environment environment;

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0 && "clean-seed".equals(args[0])) {
            String activeProfile = environment.getActiveProfiles()[0];
            if ("local".equals(activeProfile)) {
                databaseService.resetDatabase();
                log.info("Database has been reset and seeded.");
            } else {
                log.warn("The clean-seed command is only allowed in the local environment.");
            }
        }
    }
}
