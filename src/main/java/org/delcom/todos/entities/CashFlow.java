package org.delcom.todos.entities;

import jakarta.persistence.*;
import org.delcom.todos.types.ESource;
import org.delcom.todos.types.EType;

@Entity
@Table(name = "cashflows")
public class CashFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EType type;

    @Enumerated(EnumType.STRING)
    private ESource source;

    private String namaItem;
    private String keteranganDetail;
    private long amount;

    public CashFlow() {}

    public CashFlow(EType type, ESource source, String namaItem, String keteranganDetail, long amount) {
        this.type = type;
        this.source = source;
        this.namaItem = namaItem;
        this.keteranganDetail = keteranganDetail;
        this.amount = amount;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public EType getType() { return type; }
    public ESource getSource() { return source; }
    public String getNamaItem() { return namaItem; }
    public String getKeteranganDetail() { return keteranganDetail; }
    public long getAmount() { return amount; }

    public void setType(EType type) { this.type = type; }
    public void setSource(ESource source) { this.source = source; }
    public void setNamaItem(String namaItem) { this.namaItem = namaItem; }
    public void setKeteranganDetail(String keteranganDetail) { this.keteranganDetail = keteranganDetail; }
    public void setAmount(long amount) { this.amount = amount; }
}
