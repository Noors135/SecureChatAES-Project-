
package securechataes;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
public class DataBase extends javax.swing.JFrame {

   
    private void loadKeysAndSession() {
    try {
        // ===== User A (P1) =====
        txtPublicA.setText(
            Base64.getEncoder().encodeToString(
                KeyManager.loadPublicKey("User A").getEncoded()
            )
        );
        txtPrivateA.setText(
            Base64.getEncoder().encodeToString(
                KeyManager.loadPrivateKey("User A").getEncoded()
            )
        );

        // ===== User B (p2) =====
        txtPublicB.setText(
            Base64.getEncoder().encodeToString(
                KeyManager.loadPublicKey("User B").getEncoded()
            )
        );
        txtPrivateB.setText(
            Base64.getEncoder().encodeToString(
                KeyManager.loadPrivateKey("User B").getEncoded()
            )
        );

        // ===== AES Session Key (encrypted) =====
        File f = new File("aes_key_encrypted.txt");
        if (f.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(f));
            txtAESKey.setText(br.readLine());
            br.close();
        } else {
            txtAESKey.setText("Session key not generated yet");
        }

    } catch (Exception e) {
        txtAESKey.setText("Error loading keys");
        e.printStackTrace();
    }
}
  private void loadSharedChannel() {
    try {
        File file = new File("chat_messages.txt");
        if (!file.exists()) {
            txtSharedChannel.setText("Shared channel file not found.");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            content.append(line).append("\n");
        }

        br.close();
        txtSharedChannel.setText(content.toString());

    } catch (Exception e) {
        txtSharedChannel.setText("Error reading shared channel.");
        e.printStackTrace();
    }
}
  
  private void verifyMessageIntegrity() {
    try {
        File file = new File("chat_messages.txt");
        if (!file.exists()) {
            txtIntegrityResult.setText("No messages found.");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder result = new StringBuilder();

        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length != 3) continue;

            String encrypted = parts[1];
            String receivedHash = parts[2];

            // We cannot decrypt here (demo purpose)
            // Assume original message hash comparison
           // فك التشفير (demo – باستخدام AES key إن وجد)
// Load encrypted AES key from file
BufferedReader keyReader = new BufferedReader(new FileReader("aes_key_encrypted.txt"));
String encryptedAESKey = keyReader.readLine();
keyReader.close();

// Decrypt AES key using User B private key (for demo)
Cipher rsa = Cipher.getInstance("RSA");
rsa.init(Cipher.DECRYPT_MODE, KeyManager.loadPrivateKey("User B"));
byte[] aesKeyBytes = rsa.doFinal(Base64.getDecoder().decode(encryptedAESKey));

SecretKey aesKey = new javax.crypto.spec.SecretKeySpec(aesKeyBytes, "AES");

// Use AES key to decrypt message
Cipher aes = Cipher.getInstance("AES");
aes.init(Cipher.DECRYPT_MODE, aesKey);

String decryptedMessage = new String(
    aes.doFinal(Base64.getDecoder().decode(encrypted))
);

// إعادة حساب الهاش على النص الأصلي
String recalculatedHash = HashUtil.hash(decryptedMessage);


            
            result.append("Encrypted Data:\n").append(encrypted).append("\n");
            result.append("Received Hash:\n").append(receivedHash).append("\n");
            result.append("Calculated Hash:\n").append(recalculatedHash).append("\n");

            if (receivedHash.equals(recalculatedHash)) {
                result.append("Integrity Status: VERIFIED \n\n");
            } else {
                result.append("Integrity Status: FAILED \nMessage has been modified!\n\n");
            }
        }

        br.close();
        txtIntegrityResult.setText(result.toString());

    } catch (Exception e) {
        txtIntegrityResult.setText("Error verifying integrity.");
        e.printStackTrace();
    }
}
  
    private void loadSecurityLog() {
    try {
        File file = new File("security_log_database.txt");
        if (!file.exists()) {
            txtSecurityLog.setText("No security logs available.");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder log = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            log.append(line).append("\n");
        }

        br.close();
        txtSecurityLog.setText(log.toString());

    } catch (Exception e) {
        txtSecurityLog.setText("Error loading security log.");
        e.printStackTrace();
    }
}
    public DataBase() {
        initComponents();
        txtIntegrityResult.setForeground(Color.BLACK);
        loadKeysAndSession();
          loadSharedChannel();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtSecurityLog = new javax.swing.JTextArea();
        btnRefreshLog = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnVerifyIntegrity = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtIntegrityResult = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtPrivateB = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtPublicB = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtPublicA = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtPrivateA = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAESKey = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSharedChannel = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(254, 254, 191));

        txtSecurityLog.setEditable(false);
        txtSecurityLog.setColumns(20);
        txtSecurityLog.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSecurityLog.setForeground(new java.awt.Color(0, 51, 51));
        txtSecurityLog.setLineWrap(true);
        txtSecurityLog.setRows(5);
        txtSecurityLog.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtSecurityLog);

        btnRefreshLog.setBackground(new java.awt.Color(254, 254, 219));
        btnRefreshLog.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRefreshLog.setText("Refresh Log");
        btnRefreshLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshLogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(btnRefreshLog, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRefreshLog, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        jTabbedPane1.addTab("Security Log", jPanel5);

        jPanel1.setBackground(new java.awt.Color(222, 245, 245));

        btnVerifyIntegrity.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnVerifyIntegrity.setText("Verify Integrity");
        btnVerifyIntegrity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyIntegrityActionPerformed(evt);
            }
        });

        txtIntegrityResult.setColumns(20);
        txtIntegrityResult.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIntegrityResult.setRows(5);
        jScrollPane5.setViewportView(txtIntegrityResult);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(btnVerifyIntegrity, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVerifyIntegrity, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Message Integrity", jPanel1);

        jPanel3.setBackground(new java.awt.Color(215, 252, 215));
        jPanel3.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("P2 User B");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(43, 34, 139, 20);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("P1 User A");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(291, 34, 121, 20);

        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("*Displayed for demonstration only ");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(10, 230, 260, 33);

        txtPrivateB.setEditable(false);
        txtPrivateB.setColumns(20);
        txtPrivateB.setLineWrap(true);
        txtPrivateB.setRows(5);
        txtPrivateB.setText("\n");
        txtPrivateB.setWrapStyleWord(true);
        jScrollPane6.setViewportView(txtPrivateB);

        jPanel3.add(jScrollPane6);
        jScrollPane6.setBounds(10, 170, 230, 66);

        jLabel6.setText("Public Key (RSA):  ");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(255, 55, 157, 37);

        jLabel7.setText("Private Key (RSA):");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(10, 140, 157, 37);

        txtPublicB.setEditable(false);
        txtPublicB.setColumns(20);
        txtPublicB.setLineWrap(true);
        txtPublicB.setRows(5);
        txtPublicB.setText("\n");
        txtPublicB.setWrapStyleWord(true);
        jScrollPane7.setViewportView(txtPublicB);

        jPanel3.add(jScrollPane7);
        jScrollPane7.setBounds(7, 90, 230, 58);

        txtPublicA.setEditable(false);
        txtPublicA.setColumns(20);
        txtPublicA.setLineWrap(true);
        txtPublicA.setRows(5);
        txtPublicA.setText("\n");
        txtPublicA.setWrapStyleWord(true);
        jScrollPane8.setViewportView(txtPublicA);

        jPanel3.add(jScrollPane8);
        jScrollPane8.setBounds(250, 90, 236, 58);

        jLabel8.setText("Public Key (RSA):  ");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(10, 60, 157, 31);

        jLabel9.setText("Private Key (RSA):");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(250, 140, 157, 37);

        txtPrivateA.setEditable(false);
        txtPrivateA.setColumns(20);
        txtPrivateA.setLineWrap(true);
        txtPrivateA.setRows(5);
        txtPrivateA.setText("\n");
        txtPrivateA.setWrapStyleWord(true);
        jScrollPane9.setViewportView(txtPrivateA);

        jPanel3.add(jScrollPane9);
        jScrollPane9.setBounds(250, 170, 236, 66);

        txtAESKey.setEditable(false);
        txtAESKey.setColumns(20);
        txtAESKey.setLineWrap(true);
        txtAESKey.setRows(5);
        txtAESKey.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtAESKey);

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(90, 300, 330, 47);

        jLabel5.setText("Current AES Session Key: ");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(170, 280, 167, 16);

        jLabel10.setText("AES session key is generated by User A and encrypted using RSA");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(80, 350, 344, 16);

        jTabbedPane1.addTab("Key & Session", jPanel3);

        jPanel4.setBackground(new java.awt.Color(254, 200, 254));

        txtSharedChannel.setEditable(false);
        txtSharedChannel.setColumns(20);
        txtSharedChannel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSharedChannel.setLineWrap(true);
        txtSharedChannel.setRows(5);
        txtSharedChannel.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtSharedChannel);

        jLabel2.setText("Encrypted messages stored in shared channel (file-based)");

        btnRefresh.setBackground(new java.awt.Color(251, 227, 251));
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Shared Channel", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
         loadSharedChannel();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnVerifyIntegrityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyIntegrityActionPerformed
        // TODO add your handling code here:
         verifyMessageIntegrity();
    }//GEN-LAST:event_btnVerifyIntegrityActionPerformed

    private void btnRefreshLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshLogActionPerformed
            loadSecurityLog();
    }//GEN-LAST:event_btnRefreshLogActionPerformed

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
            java.util.logging.Logger.getLogger(DataBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataBase().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRefreshLog;
    private javax.swing.JButton btnVerifyIntegrity;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea txtAESKey;
    private javax.swing.JTextArea txtIntegrityResult;
    private javax.swing.JTextArea txtPrivateA;
    private javax.swing.JTextArea txtPrivateB;
    private javax.swing.JTextArea txtPublicA;
    private javax.swing.JTextArea txtPublicB;
    private javax.swing.JTextArea txtSecurityLog;
    private javax.swing.JTextArea txtSharedChannel;
    // End of variables declaration//GEN-END:variables
}
