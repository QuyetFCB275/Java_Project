package CoffeeShop.DAO.impl;

import CoffeeShop.DAO.IUserDao;
import CoffeeShop.Obj.User;
import CoffeeShop.Util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao implements IUserDao {

    private Connection conn = null;
    private CallableStatement cs = null;
    private ResultSet rs = null;

    public UserDao(DbUtil dbUtil) {
        conn = dbUtil.getConnection();
    }

    @Override
    public int count() {
        int count = 0;
        String sql = "{CALL sp_countUsers}";

        try {
            cs = conn.prepareCall(sql);
            rs = cs.executeQuery();

            while (rs.next()) 
                count = rs.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs = null;
            cs = null;
        }

        return count;
    }

    @Override
    public List<User> getAll(User user) { //lấy thông tin các user
        List<User> list = new ArrayList<>();   
        String sql = "{CALL sp_getAllUser(?, ?, ?)}";

        try {
            cs = conn.prepareCall(sql);

            cs.setNull(1, Types.NVARCHAR);
            cs.setNull(2, Types.VARCHAR);
            cs.setNull(3, Types.INTEGER);

            if (!Common.isNullOrEmpty(user)) {
                if (!Common.isNullOrEmpty(user.getName())) {
                    cs.setNString(1, user.getName());
                }
                if (!Common.isNullOrEmpty(user.getEmail())) {
                    cs.setString(2, user.getEmail());
                }
                if (!Common.isNullOrEmpty(user.getRole())) {
                    cs.setInt(3, user.getRole());
                }
            }
            rs = cs.executeQuery();

            while (rs.next()) {
                User obj = new User(
                        rs.getInt("id"),
                        rs.getNString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("role")
                );
                list.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();               
        } finally {
            rs = null;
            cs = null;
        }

        return list;
    }

    @Override
    public Map<String, Object> create(User user) { //tạo, thêm user
        Map<String, Object> output = new HashMap<>();
        String sql = "{CALL sp_insertUser(?, ?, ?, ?, ?, ?)}";

        try {
            cs = conn.prepareCall(sql);
            cs.setNString(1, user.getName());
            cs.setString(2, user.getEmail());
            cs.setString(3, user.getPassword());
            cs.setInt(4, user.getRole());
            cs.registerOutParameter(5, Types.BIT);
            cs.registerOutParameter(6, Types.NVARCHAR);
            cs.execute();
                     
            output.put("status", cs.getBoolean(5));
            output.put("message", cs.getNString(6));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cs = null;
        }

        return output;
    }

    @Override
    public Map<String, Object> update(User user) { //sửa thông tin user
        Map<String, Object> output = new HashMap<>();
        String sql = "{CALL sp_updateUser(?, ?, ?, ?, ?, ?, ?)}";

        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1, user.getId());
            cs.setNString(2, user.getName());
            cs.setString(3, user.getEmail());
            cs.setString(4, user.getPassword());
            cs.setInt(5, user.getRole());            
            cs.registerOutParameter(6, Types.BIT);
            cs.registerOutParameter(7, Types.NVARCHAR);
            cs.execute();
                    
            output.put("status", cs.getBoolean(6));
            output.put("message", cs.getNString(7));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cs = null;
        }

        return output;
    }

    @Override
    public Map<String, Object> delete(int id) { //xoá người dùng
        Map<String, Object> output = new HashMap<>();
        String sql = "{CALL sp_deleteUser(?, ?, ?)}";

        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.BIT);
            cs.registerOutParameter(3, Types.NVARCHAR);
            cs.execute();

            output.put("status", cs.getBoolean(2));
            output.put("message", cs.getNString(3));

        } catch (SQLException e) {
            e.printStackTrace();               
        } finally {
            cs = null;
        }

        return output;
    }

    @Override
    public User auth(String email, String password) { //kiểm tra tài khoản trong db
        User obj = null;
        String sql = "{CALL sp_checkUser(?, ?)}";

        try {
            cs = conn.prepareCall(sql);
            cs.setString(1, email);
            cs.setString(2, password);
            rs = cs.executeQuery();

            while (rs.next()) {
                obj = new User(
                        rs.getInt("id"),
                        rs.getNString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("role")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs = null;
            cs = null;
        }

        return obj;
    }
}
