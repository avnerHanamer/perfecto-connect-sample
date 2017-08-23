package conf;

import java.util.UUID;

public class LocalConfiguration implements IConfiguration {

    private String cloudURL;
    private String offlineToken;
    private String tunnelId;

    public LocalConfiguration() {
        cloudURL = "demo.perfectomobile.com";
        offlineToken = "eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiI2ODZjZWViNC1kNzE0LTQxMjQtYjUxZS1jYjY5MmI5MzZjZDEiLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNTAzMjMyNTM0LCJpc3MiOiJodHRwczovL2F1dGgucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL2RlbW8tcGVyZmVjdG9tb2JpbGUtY29tIiwiYXVkIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJzdWIiOiIyM2Q4MTFkYy02ZTE3LTQ3NzktYjI0Ny0wOWQ5NjhhYjUxOGQiLCJ0eXAiOiJPZmZsaW5lIiwiYXpwIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJzZXNzaW9uX3N0YXRlIjoiYzVmNjI4NjAtNTU1MC00NzBjLTgxNWYtNjRhMTdkODEyMWE1IiwiY2xpZW50X3Nlc3Npb24iOiJkMWY5Njg5YS1kMmM4LTRmYTQtYjc4Yi0wZWVjNzExYTg2ZGYiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50Iiwidmlldy1wcm9maWxlIl19fX0.mSK2o_b9hhdDstinCPH596DrWiNe50-9vTnZAwKhFAXTlwCngIVViPxs_l-GOLbx-VPbvTv_v1OLpwJ4e375EB0i-xPGOqaS3POe7Gt9tCvSV0Ht4k-IqlCnvxhJrsdkEzB4jNadR8klD9Qm3ZDGbJgH7ZJFF41kIx0GvXgQm7WcS0thwHFsBnuc0HFcaj5hhbTFRRMJ8rOK8Nv7pAJ-dMCdRsD1rlOqeEUv2-nJkQlhQ-z6fEDaN7tK9bCWtpxTAyVss1er6KdThMCzZ9iWn59c6vhlwVZ-t16Pyj2996VF1CSuCaCEB2yAeau2vY4mKAK4E_fvJGUqA3eZmWKY-A";
        tunnelId = "db0dc023-8ece-414e-a7df-dd938fc47017";
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
