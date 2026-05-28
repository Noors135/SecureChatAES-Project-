package securechataes;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import raven.chat.component.ChatBox;
import raven.chat.component.ChatArea;
import raven.chat.model.ModelMessage;
import raven.chat.swing.ChatEvent;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;


public class ChatA extends javax.swing.JFrame {

    /**
     * Creates new form ChatA
     */
        public SecretKey sessionKey;
 private int lastReceivedIndex = 0;          // آخر رسالة استقبلناها من p2
    private javax.swing.Timer receiveTimer;     // تايمر لمراقبة الرسائل الواردة

    public ChatA() {
        initComponents();
        
         chatArea.setTitle("p2");
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy, hh:mmaa");
        
        chatArea.addChatEvent(new ChatEvent() {
            @Override
            public void mousePressedSendButton(ActionEvent evt) {
                sendMessage(df);
            }

            @Override
            public void mousePressedFileButton(ActionEvent evt) {}

            @Override
            public void keyTyped(KeyEvent evt) {}
        });

        // ===== Generate AES key and send to p2 =====
        try {
            sessionKey = AESManager.generateAESKey();

            PublicKey pbB = KeyManager.loadPublicKey("User B");
            Cipher rsa = Cipher.getInstance("RSA");
            rsa.init(Cipher.ENCRYPT_MODE, pbB);

            String encryptedKey = Base64.getEncoder()
                    .encodeToString(rsa.doFinal(sessionKey.getEncoded()));

            BufferedWriter bw = new BufferedWriter(new FileWriter("aes_key_encrypted.txt"));
            bw.write(encryptedKey);
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // ===== Listen for messages from p2 =====
        receiveTimer = new javax.swing.Timer(1000, e -> checkIncomingFromB(df));
        receiveTimer.start();
    }

    // ================= SEND (P1 → P2) =================
    private void sendMessage(SimpleDateFormat df) {
        try {
            String message = chatArea.getText().trim();
            if (message.isEmpty()) return;

            // Encrypt
            Cipher aes = Cipher.getInstance("AES");
            aes.init(Cipher.ENCRYPT_MODE, sessionKey);
            String encrypted = Base64.getEncoder()
                    .encodeToString(aes.doFinal(message.getBytes()));
            //NOTE: SHA-256 hash is used for basic integrity checking (not HMAC)
 String hash = HashUtil.hash(message);
            // Show message on RIGHT P1
           Icon myIcon = new ImageIcon(getClass().getResource("/securechataes/p2.jpg"));
        chatArea.addChatBox(
                new ModelMessage(myIcon, "P1", df.format(new Date()), message),
                ChatBox.BoxType.RIGHT
);

            // Write to shared channel
            BufferedWriter bw = new BufferedWriter(new FileWriter("chat_messages.txt", true));
 bw.write("A|" + encrypted + "|" + hash);
            bw.newLine();
            bw.close();

            chatArea.clearTextAndGrabFocus();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= RECEIVE (P2 → P1) =================
    private void checkIncomingFromB(SimpleDateFormat df) { 
        try {
        File f = new File("chat_messages.txt");
        if (!f.exists()) return;

        BufferedReader br = new BufferedReader(new FileReader(f));
        String line;
        int index = 0;

        while ((line = br.readLine()) != null) {
 if (index >= lastReceivedIndex && line.startsWith("B|")) {

        String[] parts = line.split("\\|");
        if (parts.length != 3) {
            index++;
            continue;
        }

        String encrypted = parts[1];
        String receivedHash = parts[2];

        Cipher aes = Cipher.getInstance("AES");
        aes.init(Cipher.DECRYPT_MODE, sessionKey);
        String decrypted = new String(
                aes.doFinal(Base64.getDecoder().decode(encrypted))
        );

        // Integrity Check
        String computedHash = HashUtil.hash(decrypted);

        if (!computedHash.equals(receivedHash)) {
            chatArea.addChatBox(
                    new ModelMessage(
                            null,
                            "⚠ SYSTEM",
                            df.format(new Date()),
                            "Message integrity check FAILED! Message may have been tampered."
                    ),
                    ChatBox.BoxType.LEFT
            );
        } else {
            Icon otherIcon = new ImageIcon(getClass().getResource("/securechataes/p1.jpg"));
            chatArea.addChatBox(
                    new ModelMessage(otherIcon, "p2", df.format(new Date()), decrypted),
                    ChatBox.BoxType.LEFT
            );
        }
    }
    index++;
        }

        lastReceivedIndex = index;
        br.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new raven.chat.swing.Background();
        chatArea = new raven.chat.component.ChatArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background1.setLayout(null);

        chatArea.setBackground(new java.awt.Color(32, 40, 48));
        background1.add(chatArea);
        chatArea.setBounds(6, 6, 440, 660);

        jButton1.setBackground(new java.awt.Color(0, 0, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Back to Login ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        background1.add(jButton1);
        jButton1.setBounds(340, 10, 100, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
  public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.chat.swing.Background background1;
    private raven.chat.component.ChatArea chatArea;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
