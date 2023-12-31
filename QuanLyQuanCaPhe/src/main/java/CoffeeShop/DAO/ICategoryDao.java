package CoffeeShop.DAO;

import CoffeeShop.Obj.Category;

import java.util.List;
import java.util.Map;

public interface ICategoryDao {

    public int count();

    public List<Category> getAll(Category category);

    public Map<String, Object> create(Category category);

    public Map<String, Object> update(Category category);

    public Map<String, Object> delete(int id);
}
