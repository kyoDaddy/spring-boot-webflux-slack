package com.demo.config.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "slack.webhook-url")
public class SlackProp {

    private String manager;
    private ProjectDemo projectDemo;

    public ProjectDemo getProjectDemo() {
        if (projectDemo == null) {
            projectDemo = new ProjectDemo();
        }
        return projectDemo;
    }

    @Getter
    @Setter
    public static class ProjectDemo {

        private String info;
        private String warn;
        private String error;

    }



}

