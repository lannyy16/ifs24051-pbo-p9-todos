package org.delcom.todos.controllers;

import org.delcom.todos.entities.CashFlow;
import org.delcom.todos.services.CashFlowService;
import org.delcom.todos.types.EType;
import org.delcom.todos.types.ESource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for CashFlowController.
 */
public class CashFlowControllerTests {

    private CashFlowService service;
    private CashFlowController controller;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(CashFlowService.class);
        controller = new CashFlowController(service);
    }

    @Test
    void testGetAllCashFlows() {
        // Arrange
        CashFlow cf1 = new CashFlow(EType.INFLOW, ESource.CASH, "Gaji", "Gaji bulanan", 5000000);
        CashFlow cf2 = new CashFlow(EType.OUTFLOW, ESource.CASH, "Belanja", "Belanja bulanan", 1500000);
        when(service.getAllCashFlows()).thenReturn(Arrays.asList(cf1, cf2));

        // Act
        List<CashFlow> result = controller.getAllCashFlows();

        // Assert
        assertEquals(2, result.size());
        verify(service, times(1)).getAllCashFlows();
    }

    @Test
    void testGetById_Found() {
        CashFlow cf = new CashFlow(EType.INFLOW, ESource.SAVINGS, "Bonus", "Bonus akhir tahun", 1000000);
        when(service.getCashFlowById(1L)).thenReturn(Optional.of(cf));

        CashFlow result = controller.getById(1L);

        assertEquals("Bonus", result.getNamaItem());
        verify(service, times(1)).getCashFlowById(1L);
    }

    @Test
    void testGetById_NotFound() {
        when(service.getCashFlowById(99L)).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> controller.getById(99L));
        assertEquals("CashFlow not found", ex.getMessage());
    }

    @Test
    void testCreateCashFlow() {
        CashFlow newFlow = new CashFlow(EType.INFLOW, ESource.CASH, "Hadiah", "Ulang tahun", 200000);
        when(service.addCashFlow(EType.INFLOW, ESource.CASH, "Hadiah", "Ulang tahun", 200000))
                .thenReturn(newFlow);

        CashFlow result = controller.create(newFlow);

        assertEquals("Hadiah", result.getNamaItem());
        verify(service, times(1))
                .addCashFlow(EType.INFLOW, ESource.CASH, "Hadiah", "Ulang tahun", 200000);
    }

    @Test
    void testUpdateCashFlow() {
        CashFlow updatedFlow = new CashFlow(EType.OUTFLOW, ESource.CASH, "Transport", "Bensin motor", 100000);
        when(service.updateCashFlow(1L, updatedFlow.getType(), updatedFlow.getSource(),
                updatedFlow.getNamaItem(), updatedFlow.getKeteranganDetail(), updatedFlow.getAmount()))
                .thenReturn(updatedFlow);

        CashFlow result = controller.update(1L, updatedFlow);

        assertEquals("Transport", result.getNamaItem());
        verify(service, times(1)).updateCashFlow(
                1L, updatedFlow.getType(), updatedFlow.getSource(),
                updatedFlow.getNamaItem(), updatedFlow.getKeteranganDetail(), updatedFlow.getAmount());
    }

    @Test
    void testDeleteCashFlow() {
        doNothing().when(service).deleteCashFlow(1L);
        controller.delete(1L);
        verify(service, times(1)).deleteCashFlow(1L);
    }

    @Test
    void testGetSummary() {
        when(service.getTotalInflow()).thenReturn(7000000L);
        when(service.getTotalOutflow()).thenReturn(3000000L);

        String summary = controller.getSummary();

        assertEquals("Inflow: 7000000 | Outflow: 3000000", summary);
        verify(service, times(1)).getTotalInflow();
        verify(service, times(1)).getTotalOutflow();
    }
}
