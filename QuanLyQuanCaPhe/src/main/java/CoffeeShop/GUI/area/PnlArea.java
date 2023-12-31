package CoffeeShop.GUI.area;

import CoffeeShop.DAO.impl.AreaDao;
import CoffeeShop.DAO.impl.BillDao;
import CoffeeShop.DAO.impl.TableDao;
import CoffeeShop.Obj.Area;
import CoffeeShop.Obj.Bill;
import CoffeeShop.Obj.Table;
import CoffeeShop.Obj.User;
import CoffeeShop.GUI.table.JDDeleteTable;
import CoffeeShop.GUI.table.JDModifyTable;
import CoffeeShop.GUI.table.JDTable;
import CoffeeShop.Util.Common;
import CoffeeShop.Util.DbUtil;
import CoffeeShop.Util.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class PnlArea extends javax.swing.JPanel implements JDModifyArea.CallbackAreaModify, JDDeleteArea.CallbackAreaDelete, JDModifyTable.CallbackTableModify, JDDeleteTable.CallbackTableDelete, JDTable.CallbackTableExit {
    private Frame parent;
    private JPanel self;
    private DbUtil dbUtil;

    private Area area = null;
    private Table table = null;
    private User user = null;
    private Bill bill = null;

    private List<Area> areas = new ArrayList<>();
    private List<Table> tables = new ArrayList<>();

    private AreaDao areaDao;
    private TableDao tableDao;
    private BillDao billDao;

    public PnlArea(Frame parent, DbUtil dbUtil, User user) {
        initComponents();
        this.parent = parent;
        this.self = this;
        this.dbUtil = dbUtil;
        this.user = user;

        this.areaDao = new AreaDao(dbUtil);
        this.tableDao = new TableDao(dbUtil);
        this.billDao = new BillDao(dbUtil);

        if (user.getRole() != 1) {
            lblAdd.setVisible(false);
            lblUpdate.setVisible(false);
            lblDelete.setVisible(false);
        }

        loading();
    }

    private void loading() {
        area = null;
        table = null;
        bill = null;
        tabbedPane.removeAll();
        areas = areaDao.getAll();
        tables = tableDao.getAll(null);

        if (!Common.isNullOrEmpty(areas)) {
            areas.forEach(objArea -> {
                JComponent panel = makeTextPanel();
                addTab(tabbedPane, objArea.getName(), panel);
                panel.setName(objArea.getName());

                tables.stream().filter(objTable -> (Objects.equals(objTable.getArea_id(), objArea.getId()))).forEachOrdered(objTable -> {
                    makeTable(panel, objTable);
                });
            });

            String name = tabbedPane.getSelectedComponent().getName();
            area = areaDao.findByName(name);
        }
    }

    public void addTab(JTabbedPane tabbedPane, String title, Component tab) { //tab area
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setViewportView(tab);
        tabbedPane.add(jScrollPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        JLabel lbl = new JLabel(title);
        lbl.setIcon(new ImageIcon(getClass().getResource("/image/icons8_place_marker_50px.png")));

        lbl.setHorizontalTextPosition(SwingConstants.CENTER);
        lbl.setVerticalTextPosition(SwingConstants.BOTTOM);

        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);
        jScrollPane.setName(title);
    }

    public JComponent makeTextPanel() { //panel for each tab area
        JPanel panel = new JPanel(false);
        panel.setLayout(new WrapLayout(WrapLayout.LEFT, 35, 5));
        panel.setBackground(Color.WHITE);
        if (user.getRole() == 1) {  //Admin can add tables in each panel area
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(getClass().getResource("/image/icons8_add_new_75px.png")));
            label.setText("Thêm bàn mới");
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            label.setHorizontalTextPosition(SwingConstants.CENTER);
            label.setVerticalTextPosition(SwingConstants.BOTTOM);

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    if (!Common.isNullOrEmpty(area)) {
                        JDModifyTable jdma = new JDModifyTable(parent, true, dbUtil, (JDModifyTable.CallbackTableModify) self, null, area);
                        jdma.setVisible(true);
                    }
                }
            });
            panel.add(label);
        }
        return panel;
    }

    public void makeTable(JComponent rootPanel, Table objTable) {
        JPanel panel = new JPanel(false);
        panel.setLayout(new WrapLayout(WrapLayout.LEFT, 5, 5));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setIcon(new ImageIcon(getClass().getResource("/image/icons8_table_75px.png")));
        label.setText(objTable.getName());

        bill = billDao.getByTableId(new Bill(objTable.getId(), false));

        if (Common.isNullOrEmpty(bill)) 
            label.setForeground(new Color(40, 167, 69));     
        else 
            label.setForeground(Color.RED);
        
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);

        //Click table
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                table = tableDao.findByName(objTable.getName());

                if (evt.getButton() == MouseEvent.BUTTON3) {  //right mouse
                    if (user.getRole() == 1)
                        jPopupMenu.show(panel, evt.getX(), evt.getY());                                   
                }
                else {
                    if (!Common.isNullOrEmpty(table)) {
                        JDTable jDTable = new JDTable(parent, true, dbUtil, (JDTable.CallbackTableExit) self, user, table);
                        jDTable.setVisible(true);
                    }
                }
            }
        });

        panel.add(label);
        rootPanel.add(panel);
        rootPanel.repaint();
        rootPanel.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu = new javax.swing.JPopupMenu();
        jMenuItemEdit = new javax.swing.JMenuItem();
        jMenuItemDelete = new javax.swing.JMenuItem();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblAdd = new javax.swing.JLabel();
        lblUpdate = new javax.swing.JLabel();
        lblDelete = new javax.swing.JLabel();
        lblRefresh = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();

        jMenuItemEdit.setText("Sửa thông tin bàn");
        jMenuItemEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditActionPerformed(evt);
            }
        });
        jPopupMenu.add(jMenuItemEdit);

        jMenuItemDelete.setText("Xoá bàn");
        jMenuItemDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeleteActionPerformed(evt);
            }
        });
        jPopupMenu.add(jMenuItemDelete);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 50, 20));

        lblAdd.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblAdd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_add_50px_2.png"))); // NOI18N
        lblAdd.setText("Thêm mới");
        lblAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddMouseClicked(evt);
            }
        });
        jPanel2.add(lblAdd);

        lblUpdate.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblUpdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_update_50px.png"))); // NOI18N
        lblUpdate.setText("Sửa đổi");
        lblUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblUpdate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUpdateMouseClicked(evt);
            }
        });
        jPanel2.add(lblUpdate);

        lblDelete.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_delete_50px.png"))); // NOI18N
        lblDelete.setText("Xoá");
        lblDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDeleteMouseClicked(evt);
            }
        });
        jPanel2.add(lblDelete);

        lblRefresh.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblRefresh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_repeat_50px_1.png"))); // NOI18N
        lblRefresh.setText("Làm mới");
        lblRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblRefresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRefreshMouseClicked(evt);
            }
        });
        jPanel2.add(lblRefresh);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        tabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedPaneMouseClicked(evt);
            }
        });
        jPanel1.add(tabbedPane, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddMouseClicked
        JDModifyArea jdma = new JDModifyArea(parent, true, dbUtil, this, null);
        jdma.setVisible(true);
    }//GEN-LAST:event_lblAddMouseClicked

    private void lblUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUpdateMouseClicked
        if (!Common.isNullOrEmpty(area)) {
            JDModifyArea jdma = new JDModifyArea(parent, true, dbUtil, this, area);
            jdma.setVisible(true);
        }
    }//GEN-LAST:event_lblUpdateMouseClicked

    private void tabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbedPaneMouseClicked
        if (tabbedPane.getComponents().length > 0 && tabbedPane.getTabCount() > 0) {
            String name = tabbedPane.getSelectedComponent().getName();
            area = areaDao.findByName(name);
        }
    }//GEN-LAST:event_tabbedPaneMouseClicked

    private void lblDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDeleteMouseClicked
        if (!Common.isNullOrEmpty(area)) {
            JDDeleteArea jdda = new JDDeleteArea(parent, true, dbUtil, this, area);
            jdda.setVisible(true);
        }
    }//GEN-LAST:event_lblDeleteMouseClicked

    private void lblRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRefreshMouseClicked
        loading();
    }//GEN-LAST:event_lblRefreshMouseClicked

    private void jMenuItemDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeleteActionPerformed
        JDDeleteTable jDDeleteTable = new JDDeleteTable(parent, true, dbUtil, this, table);
        jDDeleteTable.setVisible(true);
    }//GEN-LAST:event_jMenuItemDeleteActionPerformed

    private void jMenuItemEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditActionPerformed
        JDModifyTable jDModifyTable = new JDModifyTable(parent, true, dbUtil, this, table, area);
        jDModifyTable.setVisible(true);
    }//GEN-LAST:event_jMenuItemEditActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItemDelete;
    private javax.swing.JMenuItem jMenuItemEdit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JLabel lblAdd;
    private javax.swing.JLabel lblDelete;
    private javax.swing.JLabel lblRefresh;
    private javax.swing.JLabel lblUpdate;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionAreaModify() {
        loading();
    }

    @Override
    public void actionAreaDelete() {
        loading();
    }

    @Override
    public void actionTableModify() {
        loading();
    }

    @Override
    public void actionTableDelete() {
        loading();
    }

    @Override
    public void actionTableExit() {
        loading();
    }
}
