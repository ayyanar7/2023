package com.ag.bta.utils.constant;

import com.ag.bta.main.models.Product;

public final class Constants {
    public static boolean BL_LOG_ENABLE = true;
    public static String STR_APP_ID = "Bussines_Tax_Agent";
    public static boolean DEBUG = true;
    public static final String ENCRYPT_PASSWORD = "BTAPWD";
    public static final String USER_ID = "vtpower.sln";
    public static final String PASSWORD = "Tamil@123";

    public static final String PRODUCT_LOCATION = "Product/";

    public static final String[] sbrandList = {"Luminous", "Exide", "Microtek", "Amaraon", "SF Sonic", "Massimo"};
    public static final String[] sProductList = {"Invertor", "Battery", "UPS", "Scrap"};
    public static final String[] mainTabList = {"Inventories", "Purchase", "Sale", "Ledger", "Trade Receivable","Trade Payable","Tax Statement", "Reconcilation"};
    public static final String[] subTabList_inventories = {"Stock-in-Trade", "Add Product", "Remove Product", "Reconcile"};
    public static final String[] subTabList_purchase = {"Purchase", "Purchase-Returns", "Goods-in-Transit", "Statement"};
    public static final String[] subTabList_sale = {"Sales", "Sales-Returns", "Goods-in-Transit", "Statements"};
    public static final String[] subTabList_ledger = {"Daybook", "Date-wise", "Month-wise", "Year-wise", "Overview"};
    public static final String[] subTabList_tax_statement = {"ITC details", "GSTR-1", "GSTR-3B", "Overview"};
    public static final String[] subTabList_tax_Reconcilation = {"Search", "Serach by Barcode"};

    public static String PROPRIETOR_JSON_FILE_NAME = "propreitor.json";
    public static boolean IS_NEXT_SCREEN_MOVED = false;
    public static final String INVOICE_NUM = "567";

    ///orderdetails
    public static final String STR_JSON_ITEM_LIST = "productList";
    public static String STR_JSON_ORDER_NUM = "orderNo";
    public static String STR_JSON_DATE = "Date";
    public static String STR_JSON_TIME = "Time";
    public static String STR_JSON_CUSTOMER_NAME = "CustomerName";
    public static String STR_JSON_FULL_ADDRESS = "FullAddress";
    public static String STR_JSON_1_ADDRESS = "Address1";

    public static String STR_JSON_2_ADDRESS = "Address2";
    public static String STR_JSON_3_ADDRESS = "Address3";
    public static String STR_JSON_PINCODE_ADDRESS = "Pincode";
    public static String STR_JSON_STATE_ADDRESS = "State";
    public static String STR_JSON_CONTACT_NUM = "ContactNo";
    public static String STR_JSON_CONTACT_EMAIL = "Email";
    public static String STR_JSON_CONTACT_FAX = "Fax";

    public static String STR_JSON_GENDER = "Gender";
    public static String STR_JSON_AGE = "Age";
    public static String STR_JSON_CUSTOMER_GSTIN = "CustomerGSTIN";
    public static String STR_JSON_SELLER_GSTIN = "SellerGSTIN";
    public static String STR_JSON_PRICE_PER_UNIT = "UnitTotal";
    public static String STR_JSON_CGSTAX_PER_UNIT = "UnitCGSTaxAmount";
    public static String STR_JSON_IGSTAX_PER_UNIT = "UnitIGSTaxAmount";

    public static String STR_JSON_TAXABLE_PER_UNIT = "UnitTaxableAmount";
    public static String STR_JSON_PRICE_PRODUCT_TOTAL = "ProductTotal";
    public static String STR_JSON_CGSTAX_PRODUCT_TOTAL = "ProductCGSTaxAmount";
    public static String STR_JSON_IGSTAX_PRODUCT_TOTAL = "ProductIGSTaxAmount";

    public static String STR_JSON_TAXABLE_PRODUCT_TOTAL = "ProductTaxableAmount";
    public static String STR_JSON_PRICE_GRANT_TOTAL = "GrantTotal";
    public static String STR_JSON_CGSTAX_GRANT_TOTAL = "GrantCGSTaxAmount";
    public static String STR_JSON_IGSTAX_GRANT_TOTAL = "GrantIGSTaxAmount";
    public static String STR_JSON_TAXABLE_GRANT_TOTAL = "GrantTaxableAmount";
    public static String STR_JSON_QUANTITY = "Quantity";
    public static String STR_JSON_1_DESCRIPTION = "Desc1";
    public static String STR_JSON_2_DESCRIPTION = "Desc2";
    public static String STR_JSON_PRODUCT_TYPE = "Type";
    public static String STR_JSON_CGST_RATE = "CGSTRate";
    public static String STR_JSON_IGST_RATE = "IGSTRate";
    public static String BL_JSON_GST_APPLICABILITY = "IsGstApplicable";
    public static String STR_JSON_BRAND_NAME = "BrandName";
    public static String STR_JSON_HSN_CODE = "HSNCode";
    public static String STR_JSON_INVOICE_NUM = "InvoiceNumber";
    //incoice contst
    public static String STR_ORDER_TITLE_1_Order_DETAILS = "Order";
    public static String STR_ORDER_TITLE_2_CUSTOMER_DETAILS = "Customer";
    public static String STR_ORDER_TITLE_3_GSTIN_DETAIL = "GSTIN";
    public static String STR_ORDER_TITLE_4_ITEM_DETAILS = "Item";
    public static String STR_ORDER_TITLE_5_PRICE_DETAILS = "Price(Rs)";
    public static String STR_ORDER_TITLE_6_TOTAL_DETAILS = "Total(Rs)";



//public static final int INVERTER = 0;
//public static final int BATTERY = 1;
//public static final int UPS = 2;
//public static final int COMBO = 3;
//public static final int SCRAP = 4;
//public static final int AMARON = 0;
//public static final int  EXIDE = 1;
//public static final int LUMINOUS = 2;
//public static final int MICROTEK = 3;
//public static final int MASSIMO = 4;
//public static final int SF_SONIC = 5;


    private static int screenWidth;
    private static int screenHeight;

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static void setScreenWidth(int screenWidth) {
       Constants.screenWidth = screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static void setScreenHeight(int screenHeight) {
         Constants.screenHeight = screenHeight;
    }



}

