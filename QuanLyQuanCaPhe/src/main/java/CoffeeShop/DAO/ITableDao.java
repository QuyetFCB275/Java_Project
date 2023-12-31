package CoffeeShop.DAO;

import CoffeeShop.Obj.Table;

import java.util.List;
import java.util.Map;

public interface ITableDao {

    public int count();

    public List<Table> getAll(Table table);

    public Table findByName(String name);

    public Map<String, Object> create(Table table);

    public Map<String, Object> update(Table table);

    public Map<String, Object> delete(int id);
}
