// Copyright 2006 Google Inc.  All Rights Reserved.
package com.google.checkout.sample.protocol;

import com.google.checkout.schema._2.CouponResult;
import com.google.checkout.schema._2.GiftCertificateResult;

import junit.framework.TestCase;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author colinc@google.com
 */
public class MerchantCalculationResultBuilderTest extends TestCase {

  private String couponCodeResultOne = "<?xml version=\"1.0\" " +
      "encoding=\"UTF-8\"?>\n<coupon-result " +
      "xmlns=\"http://checkout.google.com/schema/2\">" +
      "<calculated-amount currency=\"USD\">10.00</calculated-amount>" +
      "<code>C10983488</code><message>coupon 10</message><valid>true</valid>" +
      "</coupon-result>";
  
  private String giftResultOne = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
      "<gift-certificate-result xmlns=\"http://checkout.google.com/schema/2\">" +
      "<calculated-amount currency=\"USD\">100.00</calculated-amount>" +
      "<code>GIFT100</code><message>100$USD value</message>" +
      "<valid>true</valid></gift-certificate-result>";
  
  private String merchantResultByGift = "<?xml version=\"1.0\" " +
      "encoding=\"UTF-8\"?>\n<merchant-calculation-results " +
      "xmlns=\"http://checkout.google.com/schema/2\">" +
      "<results><result address-id=\"address-id\">" +
      "<merchant-code-results><gift-certificate-result>" +
      "<calculated-amount currency=\"USD\">1.00</calculated-amount>" +
      "<code>G100001A</code><message>gift certA</message>" +
      "<valid>true</valid></gift-certificate-result>" +
      "<gift-certificate-result>" +
      "<calculated-amount currency=\"USD\">1.00</calculated-amount>" +
      "<code>G10023343B</code><message>gift certB</message>" +
      "<valid>true</valid></gift-certificate-result>" +
      "</merchant-code-results><shippable>true</shippable>" +
      "<shipping-rate currency=\"USD\">0.10</shipping-rate>" +
      "<total-tax currency=\"USD\">112.00</total-tax></result></results>" +
      "</merchant-calculation-results>";
  
  private String merchantResultByCoupon = "<?xml version=\"1.0\" " +
      "encoding=\"UTF-8\"?>\n<merchant-calculation-results " +
      "xmlns=\"http://checkout.google.com/schema/2\"><results>" +
      "<result address-id=\"address-id\"><merchant-code-results>" +
      "<coupon-result><calculated-amount currency=\"USD\">10.00" +
      "</calculated-amount><code>C0000111A</code><message>normal coupon" +
      "</message><valid>true</valid></coupon-result><coupon-result>" +
      "<calculated-amount currency=\"USD\">100.00</calculated-amount>" +
      "<code>C123!###334A</code><message>coupon alpha </message>" +
      "<valid>false</valid></coupon-result></merchant-code-results>" +
      "<shippable>true</shippable><shipping-rate currency=\"USD\">0.12" +
      "</shipping-rate><total-tax currency=\"USD\">49.99</total-tax>" +
      "</result></results></merchant-calculation-results>";
  
  private String merchantCalResult = "<?xml version=\"1.0\" " +
      "encoding=\"UTF-8\"?>\n<merchant-calculation-results " +
      "xmlns=\"http://checkout.google.com/schema/2\"><results>" +
      "<result address-id=\"address-id\"><merchant-code-results>" +
      "<coupon-result><calculated-amount currency=\"USD\">10.00" +
      "</calculated-amount><code>C0000111A</code><message>normal coupon" +
      "</message><valid>true</valid></coupon-result>" +
      "<gift-certificate-result><calculated-amount currency=\"USD\">1.00" +
      "</calculated-amount><code>G10023343B</code>" +
      "<message>gift certB</message><valid>true</valid>" +
      "</gift-certificate-result></merchant-code-results>" +
      "<shippable>true</shippable><shipping-rate " +
      "currency=\"USD\">0.15</shipping-rate><total-tax " +
      "currency=\"USD\">249.99</total-tax></result></results>" +
      "</merchant-calculation-results>";
  
  /*
   * Test method for 'com.google.checkout.sample.protocol.MerchantCalculationResultBuilder.createCouponResult(boolean, float, String, String)'
   */
  public void testCreateCouponResult() throws Exception {
    MerchantCalculationResultBuilder builder 
        = MerchantCalculationResultBuilder.getInstance();
    CouponResult result1 = builder.createCouponResult(true, 10.00F, 
        "C10983488", "coupon 10");
    String coupon1 = builder.unmarshal(builder.convertToDOM(result1));
    assertEquals(couponCodeResultOne, coupon1);
    
    try { 
      CouponResult invalidCoupon 
          = builder.createCouponResult(false, -100.00F, "X1093432222", 
              "coupon -100");
    } catch (ProtocolException protoEx) {
      assertContains(protoEx.getMessage(), "negative");
    }
  }
  
  private boolean assertContains(String givenString, String toLookFor) {
    return (givenString.indexOf(toLookFor) >= 0);
  }

  /*
   * Test method for 'com.google.checkout.sample.protocol.MerchantCalculationResultBuilder.createGiftCertResult(boolean, float, String, String)'
   */
  public void testCreateGiftCertResult() throws Exception {
    MerchantCalculationResultBuilder builder 
        = MerchantCalculationResultBuilder.getInstance();
    GiftCertificateResult gift1 = builder.createGiftCertResult(true, 100F, 
        "GIFT100", "100$USD value");
    String giftCert1 = builder.unmarshal(builder.convertToDOM(gift1));
    assertEquals(giftResultOne, giftCert1);
    
    try {
      GiftCertificateResult invalidGiftCert 
        = builder.createGiftCertResult(false, -100.00F, "X1093432222", 
            "negative gift value");
    } catch (ProtocolException protoEx) {
      assertContains(protoEx.getMessage(), "negative");
    }

  }

  /*
   * Test method for 'com.google.checkout.sample.protocol.MerchantCalculationResultBuilder.createMerchantCalResultsByGiftCert(List, float, float, boolean, String)'
   */
  public void testCreateMerchantCalResultsByGiftCert() throws Exception {
    MerchantCalculationResultBuilder builder
        = MerchantCalculationResultBuilder.getInstance();
    GiftCertificateResult giftCert1 = builder.createGiftCertResult(true, 1F,
        "G100001A", "gift certA");
    GiftCertificateResult giftCert2 = builder.createGiftCertResult(true, 1F,
        "G10023343B", "gift certB");
    List giftList = new ArrayList();
    giftList.add(giftCert1);
    giftList.add(giftCert2);
    
    Document dom = builder.createMerchantCalResultsByGiftCert(giftList, 112F, 
        0.1F, true, "address-id");
    String merchantCalResultByGiftCertStr = builder.unmarshal(dom);
    assertEquals(merchantResultByGift, merchantCalResultByGiftCertStr);
  }

  /*
   * Test method for 'com.google.checkout.sample.protocol.MerchantCalculationResultBuilder.createMerchantCalResultsByCoupon(List, float, float, boolean, String)'
   */
  public void testCreateMerchantCalResultsByCoupon() throws Exception {
    MerchantCalculationResultBuilder builder 
        = MerchantCalculationResultBuilder.getInstance();
    CouponResult coupon1 = builder.createCouponResult(true, 10.00F, 
        "C0000111A", "normal coupon");
    CouponResult coupon2 = builder.createCouponResult(false, 100.00F, 
        "C123!###334A", "coupon alpha ");
    List couponList = new ArrayList();
    couponList.add(coupon1);
    couponList.add(coupon2);
    
    Document dom = builder.createMerchantCalResultsByCoupon(couponList, 49.99F, 
        0.1222F, true, "address-id");
    String merchantCalResultByCouponStr = builder.unmarshal(dom);
    assertEquals(merchantResultByCoupon, merchantCalResultByCouponStr);
  }

  /*
   * Test method for 'com.google.checkout.sample.protocol.MerchantCalculationResultBuilder.createMerchantCalResults(List, List, float, float, boolean, String)'
   */
  public void testCreateMerchantCalResults() throws Exception {
    MerchantCalculationResultBuilder builder 
        = MerchantCalculationResultBuilder.getInstance();
    CouponResult coupon = builder.createCouponResult(true, 10.00F, 
        "C0000111A", "normal coupon");
    GiftCertificateResult giftCert = builder.createGiftCertResult(true, 1F,
        "G10023343B", "gift certB");

    List couponList = new ArrayList();
    couponList.add(coupon);
    
    List giftList = new ArrayList();
    giftList.add(giftCert);

    Document dom = builder.createMerchantCalResults(couponList, giftList, 
        249.99F, 0.15F, true, "address-id");
    String merchantCalResultStr = builder.unmarshal(dom);
    assertEquals(merchantCalResult, merchantCalResultStr);
  }
}