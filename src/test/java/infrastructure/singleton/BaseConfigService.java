package infrastructure.singleton;

import service.config.ConfigServiceReader;

public class BaseConfigService {
    public BaseConfigService() {
    }

    protected ConfigServiceReader configServiceReader = new ConfigServiceReader();
}
