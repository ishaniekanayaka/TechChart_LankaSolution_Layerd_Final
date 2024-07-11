package lk.ijse.dep.bo.custom.impl;

import lk.ijse.dep.entity.Supplier;
import lk.ijse.dep.model.SupplierDTO;
import lk.ijse.dep.bo.custom.SupplierBO;
import lk.ijse.dep.dao.DAOFactory;
import lk.ijse.dep.dao.custom.SupplierDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DaoTypes.SUPPLIER);

    //supplierId, s_name, product, contact_num ,itemId
    @Override
    public boolean saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(dto.getSupplierId(),dto.getS_name(),dto.getProduct(),dto.getContact_num(),dto.getItemId()));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getSupplierId(),dto.getS_name(),dto.getProduct(),dto.getContact_num(),dto.getItemId()));
    }

    @Override
    public List<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        List<Supplier> suppliers = supplierDAO.getAll();
        List<SupplierDTO> supplierDTOS = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            supplierDTOS.add(new SupplierDTO(supplier.getSupplierId(), supplier.getS_name(), supplier.getProduct(), supplier.getContact_num(), supplier.getItemId()));
        }
        return supplierDTOS;
    }

    @Override
    public SupplierDTO searchBySupplierId(String Id) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.searchById(Id);
        return new SupplierDTO(supplier.getSupplierId(), supplier.getS_name(), supplier.getProduct(), supplier.getContact_num(), supplier.getItemId());
    }

    @Override
    public List<String> getSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getId();
    }

    @Override
    public String getCurrentSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getCurrentId();
    }

    @Override
    public String generateNewSupplierID() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewID();
    }
}
