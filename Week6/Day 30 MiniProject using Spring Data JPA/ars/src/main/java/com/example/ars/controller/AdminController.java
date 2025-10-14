package com.example.ars.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth/admin")
@Tag(name = "Admin Controller", description = "Admin-only endpoints")
@SecurityRequirement(name = "Bearer Authentication")
public class AdminController {

    @GetMapping("/dashboard")
    @Operation(summary = "Get admin dashboard", description = "Returns admin dashboard information")
    public ResponseEntity<Map<String, Object>> getAdminDashboard() {
        return ResponseEntity.ok(Map.of(
                "message", "Welcome to Admin Dashboard",
                "h2Console", "Access H2 Console at /h2-console (Admin only)",
                "swaggerUI", "Access Swagger UI at /swagger-ui.html (Admin only)",
                "timestamp", System.currentTimeMillis()
        ));
    }

    @GetMapping("/tools")
    @Operation(summary = "Get admin tools", description = "Returns links to admin tools")
    public ResponseEntity<Map<String, String>> getAdminTools() {
        return ResponseEntity.ok(Map.of(
                "h2Console", "/h2-console",
                "swaggerUI", "/swagger-ui.html",
                "apiDocs", "/v3/api-docs"
        ));
    }
}