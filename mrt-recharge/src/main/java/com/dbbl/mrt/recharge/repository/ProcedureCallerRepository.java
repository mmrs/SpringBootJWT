package com.dbbl.mrt.recharge.repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dbbl.mrt.recharge.model.FunctionResult;

@Repository
public class ProcedureCallerRepository {

	@Autowired
	EntityManager entityManager;

	public FunctionResult getData(String name) {

		StoredProcedureQuery procedureQuery = entityManager.createStoredProcedureQuery("TEST_PROCEDURE");

		procedureQuery.registerStoredProcedureParameter("USER_NAME", String.class, ParameterMode.INOUT);
		procedureQuery.registerStoredProcedureParameter("SECRET_KEY", String.class, ParameterMode.OUT);

		procedureQuery.setParameter("USER_NAME", name);

		FunctionResult functionResult = new FunctionResult();
		functionResult.setData("SECRET_KEY", procedureQuery.getOutputParameterValue("SECRET_KEY")).setData("USER_NAME",
				procedureQuery.getOutputParameterValue("USER_NAME"));

		return functionResult;
	}
}
