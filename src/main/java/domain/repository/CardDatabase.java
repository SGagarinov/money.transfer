package domain.repository;

import domain.entity.transfer.TransferInfo;

public interface CardDatabase {
    void transfer(TransferInfo transferInfo);
}
