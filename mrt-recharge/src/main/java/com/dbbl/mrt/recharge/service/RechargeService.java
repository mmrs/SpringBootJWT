package com.dbbl.mrt.recharge.service;

import org.springframework.stereotype.Service;
import com.dbbl.mrt.recharge.model.FunctionResult;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RechargeService implements IRechargeService {

	public FunctionResult getRechargeInfo() {

		FunctionResult functionResult = new FunctionResult();

		try {

			log.info("Entering getRechargeInfo()");

		} catch (Throwable e) {
			log.error("Exception Occured: ", e);
			functionResult.setValid(false);
			functionResult.setMsgCode("API-002");
			functionResult.setMsgCode(e.getLocalizedMessage());
			return functionResult;
		}

		return functionResult;
	}
}
