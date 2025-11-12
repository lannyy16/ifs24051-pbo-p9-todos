package org.delcom.todos.controllers;

import java.util.Map;
import java.util.UUID;

import org.delcom.todos.configs.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/") /*  */
    public String hello() {
        return "Hay, selamat datang di Spring Boot!";
    }

    public class CashFlow {
    }

    public ApiResponse<Map<String, CashFlow>> getCashFlowById(UUID createdId) {
      
      throw new UnsupportedOperationException("Unimplemented method 'getCashFlowById'");
    }

    public ApiResponse<String> deleteCashFlow(UUID createdId) {
     
      throw new UnsupportedOperationException("Unimplemented method 'deleteCashFlow'");
    }

    public ApiResponse<String> updateCashFlow(UUID createdId, CashFlow invalidUpdate) {
      
      throw new UnsupportedOperationException("Unimplemented method 'updateCashFlow'");
    }

}
