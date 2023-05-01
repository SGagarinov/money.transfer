package domain.entity;

import java.util.UUID;

public class Response {

    private UUID operationId;

    public Response() {
    }

    public Response(UUID operationId) {
        this.operationId = operationId;
    }

    public UUID getOperationId() {
        return operationId;
    }

    public void setOperationId(UUID operationId) {
        this.operationId = operationId;
    }
}
