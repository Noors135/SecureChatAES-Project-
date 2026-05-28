# Secure Chat System with Hybrid Encryption

A comprehensive Java Swing GUI application that simulates a secure communication environment using hybrid encryption. The system merges symmetric key cryptography (AES) with asymmetric public-key cryptography (RSA) to establish safe key exchanges over insecure channels. 

The simulator tracks cryptographic actions, generates dynamic session keys, verifies data integrity using hashing algorithms (SHA-256), and pipes security event data into a dedicated runtime auditing visualizer (Security Logger View).

## Project Description

This project simulates a secure end-to-end communication pipeline between two distinct structural endpoints: User A (P1) and User B (P2). It demonstrates how session keys can be transferred safely using non-pre-shared secrets. 

The platform supports secure identity hashing for entry authentication (PIN registration and login), real-time packet encryption/decryption, data integrity checking, and live logging. All mathematical and programmatic operations are captured dynamically and pushed onto a centralized monitoring dashboard (DataBase View) for continuous auditing and tampering evaluation.

## Features

* **Simulates a Complete Hybrid Encryption Architecture:**
  * **RSA (2048-bit):** Used for asymmetric key exchange and secure deployment of session keys.
  * **AES (128-bit):** Used for symmetric high-speed encryption and decryption of payload messages.
* **Strict Message Integrity Verification:**
  * Computes a unique **SHA-256** digital fingerprint for each transmitted packet to protect data streams against dynamic modifications and Man-in-the-Middle (MitM) tampering threats.
* **Integrated Security Auditing Logging Engine (`SecurityLogger`):**
  * Logs entry PIN baseline hashes securely (including tracking unauthorized, failed log attempts).
  * Logs RSA operational public and private structural keys upon establishment.
  * Logs randomized runtime AES session keys.
  * Tracks and writes explicit transmission details including plaintext, ciphertext, and comparative hashes.
* **Interactive Swing UI Environments:**
  * Secure credential authentication forms for multiple user profiles.
  * Dual synchronized runtime chat components executing send/receive message event blocks simultaneously.
  * Centralized Database workspace monitoring shared data channels, internal storage configurations, and system-wide verification evaluations.

## Project Structure

```plaintext
SecureChatAES/
│
├── SecureChatAES.java    # Monolithic application entry point and structural launcher
├── AESManager.java       # Utility logic handling symmetric 128-bit AES operations
├── KeyManager.java       # Handles RSA key generation, base storage specs, and key loading
├── HashUtil.java         # Cryptographic engine wrapping SHA-256 message-digesting blocks
├── AuthManager.java      # Isolated file-handler saving and resolving hashed PIN data
├── SecurityLogger.java   # Appends system-wide auditing logs to the textual database file
│
├── Login.java            # UI form executing structural profile login and user mapping
├── ChatA.java            # Chat interface layout for primary endpoint User A (P1)
├── ChatB.java            # Chat interface layout for secondary endpoint User B (P2)
└── DataBase.java         # Diagnostic control panel visualizing shared file lines and logs
File & Code Explanation
SecureChatAES.java
The core structural entry block of the program. It boots up the concurrent graphic loops, initializing the primary credentials interface (Login) and the auxiliary monitoring workspace (DataBase) natively.

AESManager.java
The cryptographic module tracking secret keys. It handles generation algorithms initializing a 128-bit key space and encodes binary materials cleanly into standard Base64 layouts for textual transmissions.

KeyManager.java
Initializes 2048-bit RSA algorithms to output public/private combinations. It handles automated local workspace storage using X509EncodedKeySpec and PKCS8EncodedKeySpec frameworks to serialize keys onto disk paths.

SecurityLogger.java
The logging utility tracking raw architectural changes. It handles data piping directly into security_log_database.txt, formatting distinct visibility headers around cryptographic mutations, key generations, and packet updates.

DataBase.java
The diagnostic visualization cockpit. It parses the shared text channel, maps cross-compiled user keys, and runs an internal integrity engine that recalculates incoming hash matrices to highlight modified or corrupted transmission fragments.

Requirements
To execute and run the environment workspace, you only require:

A Java Development Kit (JDK 8 or newer) installed.

Standard Swing graphic bindings (javax.swing) and compatible UI assets configured in the local build classpath.

How to Run
1. Compile the Java Source Files
Navigate into your project root environment using your standard operating system console and trigger javac:

Bash
javac securechataes/*.java
2. Launch the Application Execution Routine
Execute the compiled byte-code by calling the main application execution context:

Bash
java securechataes.SecureChatAES
Upon launching, the host system will present the main system monitoring interface (DataBase) alongside the user authentication form (Login).

Operational Architecture Workflow
Select a profile layout indicator inside the Login UI (e.g., select P1 to initialize User A configuration paths).

Input an arbitrary numerical baseline value into the PIN container slot; if it is the first login cycle, the profile records the calculated SHA-256 result directly as the ground-truth secret.

Loading ChatA triggers a programmatic block that generates a localized 128-bit AES key. This key is wrapped inside an RSA encryption envelope targeting User B's public key and is outputted into aes_key_encrypted.txt.

Entering text and triggering the Send button fires cryptographic callbacks that update the local chat views, append standard delimiters (Sender|Ciphertext|Hash) to chat_messages.txt, and generate full audit reports in the logger system.

Supported Data Transmission Syntax
Data variables entering the communication pipe line-by-line follow a strict protocol structure:

Plaintext
Sender_ID | Encrypted_Message_Base64 | SHA256_Hash_Value
Security Boundaries Warning: This software simulator abstracts networking layer complexities into file-based data structures to provide maximum educational clarity on hybrid scheduling, auditing models, and asymmetric validation.

Technologies Used
Java Cryptography Architecture (JCA): (javax.crypto.Cipher, java.security.KeyPairGenerator, java.security.MessageDigest).

Java Swing & AWT Library Layers: Used to render graphical frameworks, grid panels, and execution controls.

Author
Noor Sayed Ahmad – Computer Engineering Student.
