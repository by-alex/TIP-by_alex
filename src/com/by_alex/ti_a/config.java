package com.by_alex.ti_a;



class config{

	private static String SERVER_ADDRESS = "pacific-castle-7864.herokuapp.com";
	private static String SAVE_LOCATION = "";

	public static final int ACTIVITY_ONE = 1;
	public static final int ACTIVITY_TWO = 2;

	public static void init(String server_address, String save_location){
		SERVER_ADDRESS = server_address;
		SAVE_LOCATION = save_location;
	}

	public static String getSERVER_ADDRESS(){return SERVER_ADDRESS;}
	public static void setSERVER_ADDRESS(String server_address){
		SERVER_ADDRESS = server_address;
	}
	
	public static String getSAVE_LOCATION(){return SAVE_LOCATION;}
	public static void setSAVE_LOCATION(String save_location){
		SAVE_LOCATION = save_location;
	}
	

}
