package lk.ijse.dep.dao;

import lk.ijse.dep.dao.custom.impl.*;

import static com.lowagie.text.pdf.PdfName.ca;

public class DAOFactory {
     private static DAOFactory daoFactory;

     private DAOFactory(){}

     public static DAOFactory getdaoFactory(){
         return daoFactory == null? daoFactory = new DAOFactory():daoFactory;
     }

     public enum DaoTypes{
         CUSTOMER, EMPLOYEE, DELIVERY, SERVICE, PAYMENT, SUPPLIER, ITEM,ORDER, ORDER_DETAILS;
     }

     public SuperDAO getDAO(DAOFactory.DaoTypes daoTypes){
         switch (daoTypes){
             case CUSTOMER:
                 return new CustomerDAOImpl();
             case EMPLOYEE:
                 return new EmployeeDAOImpl();
             case DELIVERY:
                 return new DeliveryDAOImpl();
             case SERVICE:
                 return new ServiceDAOImpl();
             case PAYMENT:
                 return new PaymentDAOImpl();
             case SUPPLIER:
                 return new SupplierDAOImpl();
             case ITEM:
                 return new ItemDAOImpl();
             case ORDER:
                 return new OrderDAOImpl();
             case ORDER_DETAILS:
                 return new OrderDetailsDAOImpl();
             default:
                 return null;
         }
     }
}
