package ru.dsci.stockdock.tcs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.dsci.stockdock.core.Parameters;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.SandboxRegisterRequest;
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApi;

@Slf4j
@Component
public class TcsApiConnector implements AutoCloseable {

    private final Parameters parameters;
    private OpenApi openApi;

    public TcsApiConnector(Parameters parameters) {
        this.parameters = parameters;
    }

    public OpenApi getOpenApi() throws Exception {
        if (openApi == null) {
            close();
            openApi = new OkHttpOpenApi(parameters.getToken(), parameters.isSandBoxMode());
            if (openApi.isSandboxMode()) {
                openApi.getSandboxContext().performRegistration(new SandboxRegisterRequest()).join();
            }
        }
        return openApi;
    }

    @Override
    public void close() throws Exception {
        if (openApi != null) {
            openApi.close();
        }
    }
}
