package com.ml.encryption.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import com.ml.encryption.dto.DtoDatabase;
import com.ml.encryption.dto.DtoClient;

@Service
public class SecretService {

    private final VaultTemplate vaultTemplate;

    public SecretService(VaultTemplate vaultTemplate) {
        this.vaultTemplate = vaultTemplate;
    }

    public DtoDatabase getDatabaseSecrets() {

        DtoDatabase credentials = new DtoDatabase();
        Optional<VaultResponse> vaultResponse = Optional.ofNullable(vaultTemplate.read("cubbyhole/ml/db_config/3305"));

        if (vaultResponse.isPresent()) {

            Optional<Map<String, Object>> optionalSecrets = Optional.ofNullable(vaultResponse.get().getData());

            if (optionalSecrets.isPresent()) {
                var secrets = optionalSecrets.get();
                credentials.setUsername((String) secrets.get("username"));
                credentials.setPassword((String) secrets.get("password"));
            }
        }

        return credentials;
    }

    public DtoClient getGithubSecrets(){
        DtoClient credentials = new DtoClient();
        Optional<VaultResponse> vaultResponse = Optional.ofNullable(vaultTemplate.read("cubbyhole/ml/github/ml.com"));

        if (vaultResponse.isPresent()) {

            Optional<Map<String, Object>> optionalSecrets = Optional.ofNullable(vaultResponse.get().getData());

            if (optionalSecrets.isPresent()) {
                var secrets = optionalSecrets.get();
                credentials.setClientId((String) secrets.get("id"));
                credentials.setClientSecret((String) secrets.get("secret"));
            }
        }

        return credentials;
    }

    public DtoClient getOauthSecrets(){
        DtoClient credentials = new DtoClient();
        Optional<VaultResponse> vaultResponse = Optional.ofNullable(vaultTemplate.read("cubbyhole/ml/oauth/ml.com"));

        if (vaultResponse.isPresent()) {

            Optional<Map<String, Object>> optionalSecrets = Optional.ofNullable(vaultResponse.get().getData());

            if (optionalSecrets.isPresent()) {
                var secrets = optionalSecrets.get();
                credentials.setClientId((String) secrets.get("id"));
                credentials.setClientSecret((String) secrets.get("secret"));
            }
        }

        return credentials;
    }
}
