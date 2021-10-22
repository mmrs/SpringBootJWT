package com.dbbl.mrt.recharge.util;

import org.apache.commons.lang3.StringUtils;

public class Constants {

	public static class COMMON {

		public static final String CURRENCY_NOTATION = " BDT";
		public static final String CURRENCY_FORMAT = "%,.2f";
		public static final String LINE_DASH = StringUtils.repeat("=", 50);
	}

	public static class ATTRIBUTES {

		public static final String PAYMENT_ID = "PaymentId";
		public static final String SECRET_KEY = "SecretKey";
		public static final String PUBLIC_KEY = "PublicKey";
		public static final String CLIENT_IP = "ClientIp";
		public static final String API_ID = "ApiId";

		public static final String MERCHANT_NAME = "MerchantName";

		public static final String INVOICE_NO = "InvoiceNo";
		public static final String AMOUNT = "Amount";
		public static final String FEE = "Fee";
		public static final String EXTRA_REF_NO_1 = "ExtraRefNo1";
		public static final String EXTRA_REF_NO_2 = "ExtraRefNo2";
		public static final String EXTRA_REF_NO_3 = "ExtraRefNo3";

		public static final String CANCEL_URL = "CancelUrl";
		public static final String SUCCESS_URL = "SuccessUrl";
		public static final String FAIL_URL = "FailUrl";

		public static final String ACCOUNT_NO = "AccountNo";
		public static final String IS_OTP_SENT = "IsOtpSent";
		public static final String OTP = "Otp";
		public static final String PIN = "Pin";
	}
}
