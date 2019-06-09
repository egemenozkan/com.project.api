package com.project.api.data.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.api.data.model.transfer.TransferContract;
import com.project.api.data.service.IAccountingService;
import com.project.common.BaseService;
//transfer_contract_id,
//`transfer_contracts`.`from_type`,
//`transfer_contracts`.`from_self_id`,
//`transfer_contracts`.`to_type`,
//`transfer_contracts`.`to_self_id`,
//`transfer_contracts`.`amount`,
//`transfer_contracts`.`currency`,
//`transfer_contracts`.`begin_date`,
//`transfer_contracts`.`end_date`,
//`transfer_contracts`.`company_id`,
//`transfer_contracts`.`commission`,
//`transfer_contracts`.`commission_type`,
//`transfer_contracts`.`saved_at`
@Service
public class AccountingService extends BaseService implements IAccountingService{

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
