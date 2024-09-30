package com.example.spisopbackend.runner;

import com.example.spisopbackend.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCommandLineRunner implements CommandLineRunner {

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
                System.out.println("Database has been reset and seeded.");
            } else {
                System.out.println("The clean-seed command is only allowed in the local environment.");
            }
        }
    }
}
