package com.scope;

public class TransactionCode {
	
	//-- Registration code
	public final static String  bla101_newRegistration="new Registration_101"; //first time
	public final static String bla102_emailUpdate="email update_102";
	public final static String bla103_passwordUpdate="password update_103"; 
	
	
	//-- New Product Register
	
	public final static String bla151_productRegistration="product registration_151"; // first time
	public final static String bla152_acceptProduct_verify="accept product verify_152";
	public final static String bla153_deniedProduct_verify="denied product verify_153";
	public final static String bla154_pendingProduct_verify="pending product verify_154";
	public final static String bla155_acceptProduct_permit="accept product permit_155";
	public final static String bla156_deniedProduct_permit="denied product permit_156";
	public final static String bla157_pendingProduct_permit="pending product permit_157";
	public final static String bla158_DescriptionUpdate="Product Description Update_158";
	
	
	//-- tt information
	public final static String tt_registration="11";
	public final static String tt_prodcut="12"; 
	public final static String tt_merchant="13";
	public final static String tt_employee="14";
	public final static String tt_priceManage="15";
	public final static String tt_orderManage="16";
	public final static String tt_PinManage="17";
	
	//--Merchant action

	public final static String bla201_acceptMerchant="accept merchant_201"; // first time
	public final static String bla202_pendingMerchant="pending merchant_202";
	public final static String bla203_deniedMerchant="denied merchant_203";
	
	//--Employee 
	
	public final static String bla251_employeeInactive="employee stop to work_251"; 
	public final static String bla252_employeeActive="employee permit to work_252"; 
	public final static String bla253_employeeTerminate="employee terminate_253"; 
	
	//--price management 
	
	public final static String bla301_priceManageUpdate="update price details table_301"; 
		
	//--Order management 
		
	public final static String bla351_orderItemConfirm="confirm order item_351";
	public final static String bla352_orderItemCancel="cancel order or out of stock item_352"; 
	public final static String bla353_orderShippingConfirm="shipping detail confirm_353"; 
	public final static String bla354_orderDispatchConfirm="order successfully dispatched_354";
	public final static String bla355_orderDispatchCancel="order dispatched Cancel_355";
	
	public final static String bla356_orderPaymentRecieve="order Payment Recieve _356";
	public final static String bla357_orderPaymentDelay="order Payment Delay _357";
	public final static String bla358_orderPaymentRecheck="order Payment Recheck _358"; 
	
	
	public final static String bla359_DispatchDetailsConfirm="order dispatch details confirm_359";
	public final static String bla360_DispatchDetailsCancel="order dispatch details not right or cancel_360";
	public final static String bla361_DispatchItemDamage="order items damage_361";
	public final static String bla362_DispatchItemReplace="order items replace_362";
	
	//--Delivery Location management 
	
	public final static String bla401_pinDeactivate="Pin no Deactivate to deliver items_401";
	public final static String bla402_pinActivate="Pin no activate to deliver items_402";
	public final static String bla403_pinstatusPending="Pin status pending_403";
	public final static String bla404_pinstatusLocal="pincode status under local_404";
	public final static String bla405_pinstatusCircle="Pincode under state level_405";
	public final static String bla406_pinstatusNational="pincode under national level_406";
	
	
}