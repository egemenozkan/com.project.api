package com.vantalii.data.service;

import java.util.List;

import com.project.api.data.model.transfer.TransferContract;

public interface IAccountingService {
    List<TransferContract> getTransferContracts();
    TransferContract getTransferContractById(int contractId);
    int saveTransferContract(TransferContract contract);
}
