/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal3;

/**
 *
 * @author Sergio
 */
public class NewMain extends javax.swing.JFrame {
    /**
     * @param args the command line arguments
     */
    private Buyer buyer;
    private Supermarket supermarket;
    private Monitor monitor = new Monitor();
    private Server server = new Server(monitor);
    private int counter = 1;
    private LoggerThread log;
    
    public NewMain() {
        initComponents();
        log = new LoggerThread();
        supermarket = new Supermarket(jTextFieldAttendingBuyerButcher, jTextFieldAttendingBuyerFishmonger, jTextFieldButcherShopQueue, jTextFieldFishShopQueue, jTextFieldBuyersShelves, jTextFieldCashier1AttendingBuyer, jTextFieldCashier2AttendingBuyer, jTextFieldCheckAreaQueue, jTextFieldOusideQueue, monitor, log);
        while(counter <= 100)
        {
            //System.out.println("hi");
            if(!monitor.isStopThread())
            {
                //System.out.println("Entering " + counter);
                    try
                    {
                        Sleep(800*Math.random() + 200);
                    }
                    catch(Exception e)
                    {
                    }
                buyer = new Buyer("Buyer " + counter, monitor, supermarket, 100, log);
                counter++;
            }
            else
            {
                monitor.waitResume();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabelOutsideQueue = new javax.swing.JLabel();
        jTextFieldOusideQueue = new javax.swing.JTextField();
        jTextFieldButcherShopQueue = new javax.swing.JTextField();
        jTextFieldFishShopQueue = new javax.swing.JTextField();
        jLabelOutsideQueueButcherShopQueue = new javax.swing.JLabel();
        jLabelFishShopQueue = new javax.swing.JLabel();
        jLabelAttendingBuyerButcher = new javax.swing.JLabel();
        jTextFieldAttendingBuyerButcher = new javax.swing.JTextField();
        jLabelAttendingBuyerFishmonger = new javax.swing.JLabel();
        jTextFieldAttendingBuyerFishmonger = new javax.swing.JTextField();
        jLabelBuyersShelves = new javax.swing.JLabel();
        jTextFieldBuyersShelves = new javax.swing.JTextField();
        jLabelCheckAreaQueue = new javax.swing.JLabel();
        jTextFieldCheckAreaQueue = new javax.swing.JTextField();
        jLabelCashier1AttendingBuyer = new javax.swing.JLabel();
        jTextFieldCashier1AttendingBuyer = new javax.swing.JTextField();
        jLabelCashier2AttendingBuyer = new javax.swing.JLabel();
        jTextFieldCashier2AttendingBuyer = new javax.swing.JTextField();
        jLabelOutsideQueue9 = new javax.swing.JLabel();
        jButtonStop = new javax.swing.JButton();
        jButtonResume = new javax.swing.JButton();
        jButtonComplete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabelOutsideQueue.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelOutsideQueue.setText("People waiting to enter into the supermarket:");

        /*jTextFieldOusideQueue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldOusideQueueActionPerformed(evt);
            }
        });

        jTextFieldButcherShopQueue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldButcherShopQueueActionPerformed(evt);
            }
        });

        jTextFieldFishShopQueue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFishShopQueueActionPerformed(evt);
            }
        });*/

        jLabelOutsideQueueButcherShopQueue.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelOutsideQueueButcherShopQueue.setText("People waiting at the butcher shop:");

        jLabelFishShopQueue.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelFishShopQueue.setText("People waiting at the fish shop:");

        jLabelAttendingBuyerButcher.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelAttendingBuyerButcher.setText("Butcher attending to:");

        /*jTextFieldAttendingBuyerButcher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAttendingBuyerButcherActionPerformed(evt);
            }
        });*/

        jLabelAttendingBuyerFishmonger.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelAttendingBuyerFishmonger.setText("Fishmonger attending to:");

        /*jTextFieldAttendingBuyerFishmonger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAttendingBuyerFishmongerActionPerformed(evt);
            }
        });*/

        jLabelBuyersShelves.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelBuyersShelves.setText("People at the items shelves:");

        /*jTextFieldBuyersShelves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuyersShelvesActionPerformed(evt);
            }
        });*/

        jLabelCheckAreaQueue.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelCheckAreaQueue.setText("People waiting at the check area:");

        /*jTextFieldCheckAreaQueue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCheckAreaQueueActionPerformed(evt);
            }
        });*/

        jLabelCashier1AttendingBuyer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelCashier1AttendingBuyer.setText("Cashier 1 attending to:");

        /*jTextFieldCashier1AttendingBuyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCashier1AttendingBuyerActionPerformed(evt);
            }
        });*/

        jLabelCashier2AttendingBuyer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelCashier2AttendingBuyer.setText("Cashier 2 attending to:");

        /*jTextFieldCashier2AttendingBuyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCashier2AttendingBuyerActionPerformed(evt);
            }
        });*/

        jLabelOutsideQueue9.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabelOutsideQueue9.setText("Supermarket");

        jButtonStop.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonStop.setText("Stop");
        jButtonStop.setMaximumSize(new java.awt.Dimension(100, 23));
        jButtonStop.setMinimumSize(new java.awt.Dimension(100, 23));
        jButtonStop.setPreferredSize(new java.awt.Dimension(100, 23));
        jButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopActionPerformed(evt);
            }
        });

        jButtonResume.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonResume.setText("Resume");
        jButtonResume.setMaximumSize(new java.awt.Dimension(100, 23));
        jButtonResume.setMinimumSize(new java.awt.Dimension(100, 23));
        jButtonResume.setPreferredSize(new java.awt.Dimension(100, 23));
        jButtonResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResumeActionPerformed(evt);
            }
        });
        
        jButtonComplete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonComplete.setText("Complete");
        jButtonComplete.setMaximumSize(new java.awt.Dimension(100, 23));
        jButtonComplete.setMinimumSize(new java.awt.Dimension(100, 23));
        jButtonComplete.setPreferredSize(new java.awt.Dimension(100, 23));
        jButtonComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCompleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCheckAreaQueue)
                    .addComponent(jLabelOutsideQueue)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldOusideQueue, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldButcherShopQueue, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelOutsideQueueButcherShopQueue)
                                .addComponent(jTextFieldAttendingBuyerButcher, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelAttendingBuyerButcher)
                                .addComponent(jLabelBuyersShelves))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelFishShopQueue)
                                .addComponent(jTextFieldFishShopQueue, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldAttendingBuyerFishmonger, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelAttendingBuyerFishmonger)))
                        .addComponent(jTextFieldBuyersShelves, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jButtonStop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jButtonComplete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jButtonResume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldCashier1AttendingBuyer, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelCashier1AttendingBuyer))
                            .addGap(100, 100, 100)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldCashier2AttendingBuyer, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelCashier2AttendingBuyer)))
                        .addComponent(jTextFieldCheckAreaQueue, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(200, 200, 200))
            .addGroup(layout.createSequentialGroup()
                .addGap(482, 482, 482)
                .addComponent(jLabelOutsideQueue9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabelOutsideQueue9)
                .addGap(40, 40, 40)
                .addComponent(jLabelOutsideQueue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldOusideQueue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOutsideQueueButcherShopQueue)
                    .addComponent(jLabelFishShopQueue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldButcherShopQueue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFishShopQueue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jTextFieldAttendingBuyerButcher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelAttendingBuyerFishmonger)
                            .addComponent(jLabelAttendingBuyerButcher))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldAttendingBuyerFishmonger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jLabelBuyersShelves)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBuyersShelves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelCheckAreaQueue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldCheckAreaQueue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabelCashier1AttendingBuyer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCashier1AttendingBuyer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelCashier2AttendingBuyer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCashier2AttendingBuyer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonStop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonResume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonComplete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void jButtonStopActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        monitor.setStopThread(true);
    }                                           

    private void jButtonResumeActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        monitor.setStopThread(false);//It is changed the value of the shared variable to make the thread be active again
        monitor.activeThread();//To active the thread again
    }
    
    private void jButtonCompleteActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
        monitor.setComplete(true);
        monitor.activeThread();
    }   

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify   
    private javax.swing.JButton jButtonComplete;
    private javax.swing.JButton jButtonResume;
    private javax.swing.JButton jButtonStop;
    private javax.swing.JLabel jLabelAttendingBuyerButcher;
    private javax.swing.JLabel jLabelAttendingBuyerFishmonger;
    private javax.swing.JLabel jLabelBuyersShelves;
    private javax.swing.JLabel jLabelCashier1AttendingBuyer;
    private javax.swing.JLabel jLabelCashier2AttendingBuyer;
    private javax.swing.JLabel jLabelCheckAreaQueue;
    private javax.swing.JLabel jLabelFishShopQueue;
    private javax.swing.JLabel jLabelOutsideQueue;
    private javax.swing.JLabel jLabelOutsideQueue9;
    private javax.swing.JLabel jLabelOutsideQueueButcherShopQueue;
    private javax.swing.JTextField jTextFieldAttendingBuyerButcher;
    private javax.swing.JTextField jTextFieldAttendingBuyerFishmonger;
    private javax.swing.JTextField jTextFieldButcherShopQueue;
    private javax.swing.JTextField jTextFieldBuyersShelves;
    private javax.swing.JTextField jTextFieldCashier1AttendingBuyer;
    private javax.swing.JTextField jTextFieldCashier2AttendingBuyer;
    private javax.swing.JTextField jTextFieldCheckAreaQueue;
    private javax.swing.JTextField jTextFieldFishShopQueue;
    private javax.swing.JTextField jTextFieldOusideQueue;
    // End of variables declaration                   

    private void Sleep(double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
