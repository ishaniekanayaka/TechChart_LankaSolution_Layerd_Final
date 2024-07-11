package lk.ijse.dep.bo.custom;

import lk.ijse.dep.model.SupplierDTO;
import lk.ijse.dep.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {

    public boolean saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;

    public List<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;

    public SupplierDTO searchBySupplierId(String Id) throws SQLException, ClassNotFoundException;

    public List<String> getSupplierId() throws SQLException, ClassNotFoundException;

    public String getCurrentSupplierId() throws SQLException, ClassNotFoundException;

    public String generateNewSupplierID() throws SQLException, ClassNotFoundException;
}
