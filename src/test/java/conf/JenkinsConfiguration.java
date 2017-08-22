package conf;

import java.util.UUID;

public class JenkinsConfiguration implements IConfiguration {

    private String cloudURL;
    private String offlineToken;
    private String tunnelId;
    private static String message = UUID.randomUUID().toString();

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


    public static String getMessage() {
        return message;
    }
}
