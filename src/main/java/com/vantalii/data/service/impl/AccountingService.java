package com.vantalii.data.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.api.data.model.transfer.TransferContract;
import com.vantalii.data.service.IAccountingService;

@Service
public class AccountingService implements IAccountingService {

	@Override
	public List<TransferContract> getTransferContracts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransferContract getTransferContractById(int contractId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveTransferContract(TransferContract contract) {
		// TODO Auto-generated method stub
		return 0;
	}

//    @Override
//    public List<TransferContract> getTransferContracts() {
//	List<TransferContract> result = (List<TransferContract>) this.callList("PKG_ACCOUNTING__P_GET_TRANSFER_CONTRACTS", new TransferContractRowMapper());
//
//	return result;
//    }
//
//    @Override
//    public TransferContract getTransferContractById(int contractId) {
////	TransferContract result = (TransferContract) this.callRow("PKG_ACCOUNTING__P_GET_TRANSFER_CONTRACT_BY_ID",
////		new TransferContractRowMapper(),
////		contractId);
//
//	return result;
//    }
//
//    @Override
//    public int saveTransferContract(TransferContract contract) {
//	int result = callInteger("PKG_ACCOUNTING__P_SAVE_TRANSFER_CONTRACT",
//		contract.getId(), contract.getFromDestinationType().getId(), contract.getFromVenueType().getId(), contract.getFromSelfId(),
//		contract.getToDestinationType().getId(), contract.getToVenueType().getId(), contract.getToSelfId(),
//		contract.getAmount(), contract.getCurrency().toString(), contract.getCommission(), contract.getCommissionType().getId(), 
//		contract.getBeginDate(), contract.getEndDate(), contract.getCompany().getId());
//	
//	return result;
//    }

}
