package org.delcom.todos.services;

import org.springframework.stereotype.Service;
import org.delcom.todos.entities.CashFlow;
import org.delcom.todos.repositories.CashFlowRepository;
import org.delcom.todos.types.EType;
import org.delcom.todos.types.ESource;

import java.util.List;
import java.util.Optional;

@Service
public class CashFlowService {

    private final CashFlowRepository cashFlowRepository;

    public CashFlowService(CashFlowRepository cashFlowRepository) {
        this.cashFlowRepository = cashFlowRepository;
    }

    public List<CashFlow> getAllCashFlows() {
        return cashFlowRepository.findAll();
    }

    public CashFlow addCashFlow(EType type, ESource source, String namaItem, String detail, long amount) {
        CashFlow flow = new CashFlow(type, source, namaItem, detail, amount);
        return cashFlowRepository.save(flow);
    }

    public Optional<CashFlow> getCashFlowById(Long id) {
        return cashFlowRepository.findById(id);
    }

    public CashFlow updateCashFlow(Long id, EType type, ESource source, String namaItem, String detail, long amount) {
        return cashFlowRepository.findById(id)
                .map(existing -> {
                    existing.setType(type);
                    existing.setSource(source);
                    existing.setNamaItem(namaItem);
                    existing.setKeteranganDetail(detail);
                    existing.setAmount(amount);
                    return cashFlowRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("CashFlow not found with ID: " + id));
    }

    public void deleteCashFlow(Long id) {
        cashFlowRepository.deleteById(id);
    }

    public long getTotalInflow() {
        return cashFlowRepository.findAll().stream()
                .filter(c -> c.getType() == EType.INFLOW)
                .mapToLong(CashFlow::getAmount)
                .sum();
    }

    public long getTotalOutflow() {
        return cashFlowRepository.findAll().stream()
                .filter(c -> c.getType() == EType.OUTFLOW)
                .mapToLong(CashFlow::getAmount)
                .sum();
    }
}
