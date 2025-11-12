package org.delcom.todos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.delcom.todos.entities.CashFlow;
import java.util.List;

public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {
    List<CashFlow> findByNamaItemContainingIgnoreCase(String namaItem);
}
