package org.delcom.todos.services;

import org.delcom.todos.entities.CashFlow;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICashFlowService {
    CashFlow addCashFlow(CashFlow cashFlow);
    List<CashFlow> getAllCashFlows(String search);
    Optional<CashFlow> getCashFlowById(UUID id);
    boolean updateCashFlow(UUID id, CashFlow updated);
    boolean deleteCashFlow(UUID id);
    List<String> getAllLabels();
}
