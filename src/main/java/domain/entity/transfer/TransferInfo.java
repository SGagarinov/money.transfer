package domain.entity.transfer;


public class TransferInfo {

    private TransferProperties transferProperties;
    private Amount amount;

    public TransferInfo() {
    }

    public TransferInfo(TransferProperties transferProperties, Amount amount) {
        this.transferProperties = transferProperties;
        this.amount = amount;
    }

    public TransferProperties getProperties() {
        return transferProperties;
    }

    public void setProperties(TransferProperties transferProperties) {
        this.transferProperties = transferProperties;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
