package Mask;

public class Stored extends AbstractEvent {

    private Long id;
    private String maskType;
    private Integer qty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getMaskType() {
        return maskType;
    }

    public void setMaskType(String maskType) {
        this.maskType = maskType;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}