package CoffeeShop.GUI.area;

import CoffeeShop.DAO.impl.AreaDao;
import CoffeeShop.Obj.Area;
import CoffeeShop.Util.Common;
import CoffeeShop.Util.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Map;

public final class JDModifyArea extends javax.swing.JDialog {
    
    private CallbackAreaModify callback;
    private Area area;
    private DbUtil dbUtil;
    private AreaDao areaDao;

    public interface CallbackAreaModify {
        public void actionAreaModify();
    }

    public JDModifyArea(java.awt.Frame parent, boolean modal, DbUtil dbUtil, CallbackAreaModify callback, Area area) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        lblNameError.setVisible(false);
        this.callback = callback;
        this.dbUtil = dbUtil;
        this.areaDao = new AreaDao(dbUtil);

        if (!Common.isNullOrEmpty(area)) {
            this.area = area;
            loadData();
        }

        txtName.setBorder(BorderFactory.createCompoundBorder(
                txtName.getBorder(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));
    }

    public void loadData() {
        lblTitle.setText("SỬA ĐỔI KHU VỰC");
        btnModify.setText("Sửa đổi");
        txtName.setText(area.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnModify = new javax.swing.JButton();
        lblNameError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CẬP NHẬT KHU VỰC");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N
        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_location_50px.png"))); // NOI18N
        lblTitle.setText("THÊM MỚI KHU VỰC");

        lblName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        lblName.setText("Tên khu vực");

        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameKeyPressed(evt);
            }
        });

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

        lblNameError.setForeground(new java.awt.Color(240, 71, 71));
        lblNameError.setText("Tên khu vực không được để trống");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNameError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtName)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(lblNameError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
        String name = txtName.getText().trim();

        if (name.equals("")) {
            txtName.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.RED),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)));
            lblName.setForeground(Color.RED);
            lblNameError.setVisible(true);
        } 
        else {
            try {
                if (Common.isNullOrEmpty(area)) {
                    area = new Area();
                    area.setName(name);
                    Map<String, Object> result = areaDao.create(area);

                    if ((boolean) result.get("status")) {
                        JOptionPane.showMessageDialog(this, result.get("message"));
                        callback.actionAreaModify();
                        dispose();
                    } 
                    else 
                        JOptionPane.showMessageDialog(this, result.get("message"));
                } 
                else {
                    area.setName(name);
                    Map<String, Object> result = areaDao.update(area);

                    if ((boolean) result.get("status")) {
                        JOptionPane.showMessageDialog(this, result.get("message"));
                        callback.actionAreaModify();
                        dispose();
                    } 
                    else 
                        JOptionPane.showMessageDialog(this, result.get("message"));                  
                }

            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_btnModifyActionPerformed

    private void txtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnModify.doClick();
        }
    }//GEN-LAST:event_txtNameKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModify;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNameError;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
