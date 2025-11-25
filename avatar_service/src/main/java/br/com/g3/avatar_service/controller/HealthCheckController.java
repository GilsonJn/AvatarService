package br.com.g3.avatar_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/NewCare")
public class HealthCheckController {

    @GetMapping
    public String healthCheck() {
        return "Verificação de integridade da api NewCare";
    }
}
