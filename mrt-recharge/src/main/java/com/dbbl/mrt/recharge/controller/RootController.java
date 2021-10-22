package com.dbbl.mrt.recharge.controller;

public class RootController {

	protected static final String VIEW_MODEL = "viewModel";
	protected static final String REDIRECT_TO = "redirect:";

	/*
	 * No Request Mapping Here. All controller will extend this controller to access
	 * common members only. Declaring request mapping here might cause ambiguous
	 * mapping due to inheritance, as child classes also tries to map the request
	 * again while instantiating.
	 */
}
