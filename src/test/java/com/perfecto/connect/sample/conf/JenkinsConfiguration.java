package com.perfecto.connect.sample.conf;

public class JenkinsConfiguration implements IConfiguration {

    private String cloudURL;
    private String offlineToken;
    private String tunnelId;

    public JenkinsConfiguration() {
        cloudURL = System.getProperty("c");
        offlineToken = System.getProperty("s");
        tunnelId = System.getProperty("t");
    }

    @Override
    public String getCloudURL() {
        return cloudURL;
    }

    @Override
    public String getOfflineToken() {
        return offlineToken;
    }

    @Override
    public String getTunnelId() {
        return tunnelId;
    }


}
