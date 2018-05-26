//package com.scope;
//
//import java.math.BigDecimal;
//
//import javax.servlet.http.HttpServletRequest;
//
//import net.authorize.Environment;
//import net.authorize.api.contract.v1.*;
//import net.authorize.api.controller.base.ApiOperationBase;
//import net.authorize.api.controller.CreateTransactionController;
//import net.authorize.api.contract.v1.NameAndAddressType;
//public class ChargeCreditCard {
//	
//	public String tras1(String creditcard,String expire,HttpServletRequest request)
//	{
//		String error="";
//		 //Common code to set for all requests
//        ApiOperationBase.setEnvironment(Environment.SANDBOX);
//
//        MerchantAuthenticationType merchantAuthenticationType  = new MerchantAuthenticationType() ;
//        merchantAuthenticationType.setName("5ppxL2PF7X");
//        merchantAuthenticationType.setTransactionKey("52qJ2R876rZLuqY2");
//        ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);
//
//        // Populate the payment data
//        PaymentType paymentType = new PaymentType();
//        CreditCardType creditCard = new CreditCardType();
//        creditCard.setCardNumber(creditcard);
//        creditCard.setExpirationDate(expire);
//        paymentType.setCreditCard(creditCard);
//
//        // Create the payment transaction request
//        TransactionRequestType txnRequest = new TransactionRequestType();
//        txnRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
//        txnRequest.setPayment(paymentType);
//       // txnRequest.setAmount(new BigDecimal(500.00)); --- amt session
//            //txnRequest.setAmount(new BigDecimal(new servlet.CartSession().retriveAmt(request)));
//        
//        //add ship address
//        
//        txnRequest.setShipTo(getAdress(request));
//
//        // Make the API Request
//        CreateTransactionRequest apiRequest = new CreateTransactionRequest();
//        apiRequest.setTransactionRequest(txnRequest);
//        CreateTransactionController controller = new CreateTransactionController(apiRequest);
//        controller.execute();
//
//
//        CreateTransactionResponse response = controller.getApiResponse();
//
//        if (response!=null) {
//
//            // If API Response is ok, go ahead and check the transaction response
//            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
//                TransactionResponse result = response.getTransactionResponse();
//                if (result.getResponseCode().equals("1")) {
//                   /* System.out.println("ResponseCode :"+result.getResponseCode());
//                    System.out.println(" :"+"Successful Credit Card Transaction");
//                    System.out.println("AuthCode :"+result.getAuthCode());
//                    System.out.println("TransId :"+result.getTransId());
//                    System.out.println("AccountNumber :"+result.getAccountNumber());
//                    System.out.println("AvsResultCode :"+result.getAvsResultCode());
//                    System.out.println("RawResponseCode :"+result.getRawResponseCode());
//                    System.out.println("AuthCode :"+result.getAuthCode());
//                    System.out.println("CvvResultCode :"+result.getCvvResultCode());
//                    System.out.println("CavvResultCode :"+result.getCavvResultCode());
//                    System.out.println("RefTransID :"+result.getRefTransID());
//                    System.out.println("TransHash :"+result.getTransHash());
//                    System.out.println("TestRequest :"+result.getTestRequest());
//                    System.out.println("AccountType :"+result.getAccountType());
//                    System.out.println("SplitTenderId :"+result.getSplitTenderId()); */
//                	error+= "sucessfully trasaction and your order place for confirmation to availabilty";
//                }
//                else
//                {
//                    //System.out.println("Failed Transaction"+result.getResponseCode());
//                	error+=  "Failed Transaction"+result.getResponseCode();
//                }
//            }
//            else
//            {
//                //System.out.println("Failed Transaction:  "+response.getMessages().getResultCode());
//                error+=  "Failed Transaction:  "+response.getMessages().getResultCode();
//            }
//        }
//        return error;
//	}
//	private NameAndAddressType getAdress(HttpServletRequest request)
//	{
//		NameAndAddressType shipAddress=new NameAndAddressType();
////		if(new bal.SessionUser().getSession(request, "customerInfo"))
////        {
////			model.Customer cust=(model.Customer) request.getSession().getAttribute("customerInfo");
////			
////			shipAddress.setFirstName(cust.getName());
////			shipAddress.setLastName(cust.getContactNo());
////			shipAddress.setAddress(cust.getAddress());
////			shipAddress.setCity(cust.getCity());
////			shipAddress.setState(cust.getState());
////			System.out.println(cust);
////        }
//		System.out.println("after cust");
//		return shipAddress;
//	}
//
//    public static void main(String args[]) {
//
//       ChargeCreditCard ch=new ChargeCreditCard();
//       //ch.tras1("4242424242424242", "0822");
////E933A79417AE76CC43E71E13BE871584
//    }
//    
//    
//    public void trasc()
//    {
//    //Common code to set for all requests
//    ApiOperationBase.setEnvironment(Environment.SANDBOX);
//
//    MerchantAuthenticationType merchantAuthenticationType  = new MerchantAuthenticationType() ;
//    merchantAuthenticationType.setName("5ppxL2PF7X");
//    merchantAuthenticationType.setTransactionKey("52qJ2R876rZLuqY2");
//    ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);
//
//    // Populate the payment data
//    PaymentType paymentType = new PaymentType();
//    CreditCardType creditCard = new CreditCardType();
//    creditCard.setCardNumber("4242424242424242");
//    creditCard.setExpirationDate("0822");
//    paymentType.setCreditCard(creditCard);
//
//    // Create the payment transaction request
//    TransactionRequestType txnRequest = new TransactionRequestType();
//    txnRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
//    txnRequest.setPayment(paymentType);
//    txnRequest.setAmount(new BigDecimal(700.00));
//
//    // Make the API Request
//    CreateTransactionRequest apiRequest = new CreateTransactionRequest();
//    apiRequest.setTransactionRequest(txnRequest);
//    CreateTransactionController controller = new CreateTransactionController(apiRequest);
//    controller.execute();
//
//
//    CreateTransactionResponse response = controller.getApiResponse();
//
//    if (response!=null) {
//
//        // If API Response is ok, go ahead and check the transaction response
//        if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
//            TransactionResponse result = response.getTransactionResponse();
//            if (result.getResponseCode().equals("1")) {
//                System.out.println("-----------------------------");
//                System.out.println("Transaction ID :" +result.getTransId());
//                System.out.println("Account No      : "+result.getAccountNumber());
//                System.out.println("Account Type    : "+result.getAccountType());
//                System.out.println("AuthCode        : "+result.getAuthCode());
//                System.out.println("AVSResult Code  : "+result.getAvsResultCode());
//                System.out.println("CAVV Result Code: "+result.getCavvResultCode());
//                System.out.println("Response Code   : "+result.getResponseCode());
//                System.out.println("Test Request    : "+result.getTestRequest());
//                System.out.println("Trans Hash      : "+result.getTransHash());
//                System.out.println("Result Code     : "+result.getResponseCode());
//                System.out.println("Message Code    : "+result.getMessages().getMessage().get(0).getCode());
//                System.out.println("Message Text    : "+result.getMessages().getMessage().get(0).getDescription());
//            }
//            else{
//                System.out.println("Failed Transaction"+result.getResponseCode());
//            }
//        }
//        else{
//            System.out.println("Failed Transaction:  "+response.getMessages().getResultCode());
//        }
//    }
//    }
//}