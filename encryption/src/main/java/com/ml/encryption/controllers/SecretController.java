package com.ml.encryption.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ml.encryption.dto.DtoClient;
import com.ml.encryption.dto.DtoDatabase;
import com.ml.encryption.services.SecretService;

@RestController
@RequestMapping("/api/v1/secrets")
public class SecretController {

    private final SecretService secretService;

    public SecretController(SecretService secretService) {
        this.secretService = secretService;
    }

    @GetMapping("/db")
    public ResponseEntity<DtoDatabase> getDatabaseSecrets() {

        return ResponseEntity.ok(secretService.getDatabaseSecrets());
    }

    @GetMapping("/github")
    public ResponseEntity<DtoClient> getGithubSecrets() {

        return ResponseEntity.ok(secretService.getGithubSecrets());
    }

    @GetMapping("/oauth")
    public ResponseEntity<DtoClient> getOauthSecrets() {

        return ResponseEntity.ok(secretService.getOauthSecrets());
    }

}