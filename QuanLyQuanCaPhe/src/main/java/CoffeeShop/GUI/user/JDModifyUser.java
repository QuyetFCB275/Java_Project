package CoffeeShop.GUI.user;

import CoffeeShop.DAO.impl.UserDao;
import CoffeeShop.Obj.User;
import CoffeeShop.Util.Common;
import CoffeeShop.Util.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class JDModifyUser extends javax.swing.JDialog {

    private User user;
    private CallbackUserModify callback;
    private UserDao userDao;
    private int updateCurrent;
    private java.awt.Frame parent;

    interface CallbackUserModify {
        public void actionUserModify();
    }

    public JDModifyUser(java.awt.Frame parent, boolean modal, DbUtil dbUtil, CallbackUserModify callback, User user, int updateCurrent) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.parent = parent;
        this.callback = callback;
        this.userDao = new UserDao(dbUtil);
        this.updateCurrent = updateCurrent;

        if (!Common.isNullOrEmpty(user)) {
            lblTitle.setText("SỬA ĐỔI NGƯỜI DÙNG");
            btnModify.setText("Sửa đổi");
            this.user = user;
            loadingData();
        }

        // Custom Style
        txtEmail.setBorder(BorderFactory.createCompoundBorder(
                txtEmail.getBorder(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        txtName.setBorder(BorderFactory.createCompoundBorder(
                txtName.getBorder(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
                txtPassword.getBorder(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        lblNameError.setVisible(false);
        lblEmailError.setVisible(false);
        lblPasswordError.setVisible(false);
        
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        String pers[] = {"Admin", "Nhân viên"};
        for (String per : pers) 
            dcbm.addElement(per);

        cboPermission.setModel(dcbm);
    }

    public void loadingData() {
        txtName.setText(this.user.getName());
        txtPassword.setText(this.user.getPassword());
        txtEmail.setText(this.user.getEmail());
        cboPermission.setSelectedItem(this.user.getRole() == 1 ? "Admin" : "Nhân viên");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnModify = new javax.swing.JButton();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblPermission = new javax.swing.JLabel();
        cboPermission = new javax.swing.JComboBox();
        lblNameError = new javax.swing.JLabel();
        lblEmailError = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        lblPasswordError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CẬP NHẬT NGƯỜI DÙNG");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N
        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/login.png"))); // NOI18N
        lblTitle.setText("THÊM MỚI NGƯỜI DÙNG");
        jPanel1.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 547, 60));

        lblName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        lblName.setText("Tên người dùng");
        jPanel1.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 89, 547, 30));
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 547, 40));

        btnModify.setBackground(new java.awt.Color(0, 204, 106));
        btnModify.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnModify.setForeground(new java.awt.Color(255, 255, 255));
        btnModify.setText("Thêm mới");
        btnModify.setBorderPainted(false);
        btnModify.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyActionPerformed(evt);
            }
        });
        jPanel1.add(btnModify, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 590, 120, 35));

        lblEmail.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        lblEmail.setText("Email");
        jPanel1.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 547, 30));
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 547, 40));

        lblPermission.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        lblPermission.setText("Quyền");
        jPanel1.add(lblPermission, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 547, 30));

        jPanel1.add(cboPermission, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 547, 40));

        lblNameError.setFont(new java.awt.Font("Segoe UI Semibold", 0, 11)); // NOI18N
        lblNameError.setForeground(new java.awt.Color(240, 71, 71));
        lblNameError.setText("Không được để trống");
        jPanel1.add(lblNameError, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 165, 547, 20));

        lblEmailError.setFont(new java.awt.Font("Segoe UI Semibold", 0, 11)); // NOI18N
        lblEmailError.setForeground(new java.awt.Color(240, 71, 71));
        lblEmailError.setText("Không được để trống");
        jPanel1.add(lblEmailError, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 265, 547, 20));

        lblPassword.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        lblPassword.setText("Mật khẩu");
        jPanel1.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 547, 30));
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 547, 40));

        lblPasswordError.setFont(new java.awt.Font("Segoe UI Semibold", 0, 11)); // NOI18N
        lblPasswordError.setForeground(new java.awt.Color(240, 71, 71));
        lblPasswordError.setText("Không được để trống");
        jPanel1.add(lblPasswordError, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 365, 547, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
        //set default
        txtName.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        lblName.setForeground(Color.BLACK);
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        lblPassword.setForeground(Color.BLACK);
        txtEmail.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        lblEmail.setForeground(Color.BLACK);
        lblNameError.setVisible(false);
        lblEmailError.setVisible(false);
        lblPasswordError.setVisible(false);
             
        String name = (String) txtName.getText().trim();
        String email = (String) txtEmail.getText().trim();
        String password = (String) txtPassword.getText().trim();
        int role = cboPermission.getSelectedItem() == "Admin" ? 1 : 0;

        boolean validate = true;
        String email_regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        //Nhập thiếu
        if (name.equals("")) {
            txtName.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.RED),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)));
            lblName.setForeground(Color.RED);
            lblNameError.setVisible(true);
            validate = false;
        }

        if (password.equals("")) {
            txtPassword.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.RED),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)));
            lblPassword.setForeground(Color.RED);
            lblPasswordError.setVisible(true);
            validate = false;
        }

        if (txtEmail.getText().trim().equals("")) {
            txtEmail.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.RED),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)));
            lblEmail.setForeground(Color.RED);
            lblEmailError.setVisible(true);
            validate = false;
        } 
        else if (!email.matches(email_regex)) { //sai dạng email
            txtEmail.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.RED),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)));
            lblEmailError.setText("Email bạn nhập không đúng định dạng!");
            lblEmail.setForeground(Color.RED);
            lblEmailError.setVisible(true);
            validate = false;
        }

        //Nhập đủ thông tin
        if (validate) {
            try {
                User newUser = new User(name, email, password, role);

                if (Common.isNullOrEmpty(user)) { //Thêm user
                    Map<String, Object> result = userDao.create(newUser);

                    if ((boolean) result.get("status") == true) {
                        JOptionPane.showMessageDialog(this, result.get("message"));
                        callback.actionUserModify();
                        
                        dispose();
                    } else 
                        JOptionPane.showMessageDialog(this, result.get("message"));                   
                } 
                else { //Chỉnh sửa User
                    newUser.setId(user.getId());
                    Map<String, Object> result = userDao.update(newUser);
                    if ((boolean) result.get("status") == true) {
                        JOptionPane.showMessageDialog(this, result.get("message"));
                        callback.actionUserModify();
                        dispose();
                        if (updateCurrent == 1) {
                            JOptionPane.showMessageDialog(parent, "Bạn vừa cập nhật thông tin bản thân. Vui lòng đăng nhập lại!");
                            CoffeeShop.GUI.home.Dashboard.getBtnLogOut().doClick();
                        }
                    } else 
                        JOptionPane.showMessageDialog(this, result.get("message"));                    
                }

            } catch (HeadlessException e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_btnModifyActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModify;
    private javax.swing.JComboBox cboPermission;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmailError;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNameError;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPasswordError;
    private javax.swing.JLabel lblPermission;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    // End of variables declaration//GEN-END:variables
}


