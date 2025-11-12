package org.delcom.todos.controllers;

import org.delcom.todos.entities.CashFlow;
import org.delcom.todos.services.CashFlowService;
import org.delcom.todos.types.EType;
import org.delcom.todos.types.ESource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cashflow")
@CrossOrigin(origins = "*")
public class CashFlowController {

    private final CashFlowService service;

    public CashFlowController(CashFlowService service) {
        this.service = service;
    }

    @GetMapping
    public List<CashFlow> getAllCashFlows() {
        return service.getAllCashFlows();
    }

    @GetMapping("/{id}")
    public CashFlow getById(@PathVariable Long id) {
        return service.getCashFlowById(id)
                .orElseThrow(() -> new RuntimeException("CashFlow not found"));
    }

    @PostMapping
    public CashFlow create(@RequestBody CashFlow flow) {
        return service.addCashFlow(flow.getType(), flow.getSource(),
                flow.getNamaItem(), flow.getKeteranganDetail(), flow.getAmount());
    }

    @PutMapping("/{id}")
    public CashFlow update(@PathVariable Long id, @RequestBody CashFlow flow) {
        return service.updateCashFlow(id, flow.getType(), flow.getSource(),
                flow.getNamaItem(), flow.getKeteranganDetail(), flow.getAmount());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteCashFlow(id);
    }

    @GetMapping("/summary")
    public String getSummary() {
        return String.format("Inflow: %d | Outflow: %d",
                service.getTotalInflow(), service.getTotalOutflow());
    }
}
