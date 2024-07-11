package lk.ijse.dep.bo;

import lk.ijse.dep.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getboFactory(){
        return boFactory == null? boFactory =new BOFactory():boFactory;

    }

    public enum BoTypes{
        CUSTOMER, EMPLOYEE, DELIVERY, SERVICE, PAYMENT, SUPPLIER, ITEM,PURCHASE_ORDER;
    }

    public SuperBO getBO(BOFactory.BoTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case DELIVERY:
                return new DeliveryBOImpl();
            case SERVICE:
                return new ServiceBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PURCHASE_ORDER:
                return new PurchaseOrderBOImpl();

            default:
                return null;
        }
    }
}
