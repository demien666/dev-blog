package com.demien.testloan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AppConst {
	public static final String DATE_FORMAT = "dd.MM.yyyy";
	public final static DateFormat FORMATTER = new SimpleDateFormat(DATE_FORMAT,
			Locale.getDefault());
	// messages
	public static final String HELLO = "Hello world!";
	public static final String RESULT_OK = "Request accepted!";
	public static final String RESULT_REJECTED = "Request rejected :( ";
	public static final String RESULT_EXTENDED = "Extended";
	
	//errors 
	public static final String ERROR_AMOUNT_IS_NULL = "Amount have to be defined. ";
	public static final String ERROR_DATE_IS_NULL = "Date have to be defined. ";
	public static final String ERROR_WRONG_AMOUNT_FORMAT = "Wrong format for amount.";
	public static final String ERROR_WRONG_DATE_FORMAT = "Wrong date format. It have to be " + DATE_FORMAT
	+ ".";
	public static final String ERROR_CAN_NOT_DELETE = "Entity can notbe deleted. ";
	public static final String ERROR_WRONG_USER_ID="Wrong user id";

	public static final float DEFAULT_RATE = 5;
	public static final float CHECK_MAX_AMOUNT = 1000;
	
	public static final int CHECK_MAX_IP_DAY_REQUESTS = 3;
	
	public static final String REJECT_REASON_BAD_TIME = "rejected! reason: suspicious time+amount. ";
	public static final String REJECT_REASON_IP_DAY_ATTEMPTS ="rejected! reason: "+CHECK_MAX_IP_DAY_REQUESTS +" attempts from one ip. ";
	
	public static final int EXTEND_INTERVAL=1000*60*60*24*7; //week
	public static final float EXTEND_RATE_MUL=1.5f; 
	

}
