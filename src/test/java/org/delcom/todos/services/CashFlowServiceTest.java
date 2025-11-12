package org.delcom.todos.services;

import org.delcom.todos.entities.CashFlow;
import org.delcom.todos.repositories.CashFlowRepository;
import org.delcom.todos.types.EType;
import org.delcom.todos.types.ESource;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class CashFlowServiceTest {

    @Mock
    private CashFlowRepository repository;

    @InjectMocks
    private CashFlowService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCashFlow() {
        CashFlow flow = new CashFlow(EType.INFLOW, ESource.CASH, "Bonus", "Dapat THR", 500000);
        when(repository.save(any())).thenReturn(flow);

        CashFlow result = service.addCashFlow(
                EType.INFLOW, ESource.CASH, "Bonus", "Dapat THR", 500000
        );

        assertThat(result.getNamaItem()).isEqualTo("Bonus");
        verify(repository, times(1)).save(any());
    }

    @Test
    void testGetTotalInflow() {
        List<CashFlow> list = Arrays.asList(
                new CashFlow(EType.INFLOW, ESource.CASH, "Gaji", "Desember", 1000000),
                new CashFlow(EType.OUTFLOW, ESource.CASH, "Belanja", "Shopee", 200000)
        );
        when(repository.findAll()).thenReturn(list);

        long inflow = service.getTotalInflow();
        assertThat(inflow).isEqualTo(1000000);
    }

    @Test
    void testUpdateCashFlow() {
        CashFlow existing = new CashFlow(EType.INFLOW, ESource.CASH, "Gaji", "Mei", 1000);
        existing.setAmount(2000);
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any())).thenReturn(existing);

        CashFlow updated = service.updateCashFlow(1L, EType.OUTFLOW, ESource.OTHERS, "Beli", "Minuman", 500);
        assertThat(updated.getType()).isEqualTo(EType.OUTFLOW);
    }
}
