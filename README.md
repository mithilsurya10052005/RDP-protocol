# RDP Protocol (Remote Desktop Protocol) <img src="anydesk.png" alt="Logo" width="50" height="50"/>



A simple Java-based Remote Desktop Protocol (RDP) implementation, inspired by tools like AnyDesk. This project allows remote control and communication between two systems over a network.

## 🚀 Features

- Remote desktop control
- Authentication system
- Real-time chat functionality between client and server
- Simple and clean GUI interface

## Requirements

- Java (JDK 8 or later) installed on both client and server machines

## 📁 Project Structure
```
RDP-protocol-main/
├── ClientSide/
│ ├── App.java
│ ├── Authentication.java
│ ├── ...
├── Server
│ ├── Start.java
│ ├── ...
├── anydesk.png
├── README.md
```

## 🛠️ How to Run

1. **Install Java**  
   Ensure Java is installed on both the client and server machines. You can verify with:

2. **Start the Server**  
- Navigate to the `ClientSide` directory.
- Compile and run the server:
  ```bash
  javac App.java
  java App
  ```

3. **Start the Client**  
- Navigate to the `ClientSide` directory.
- Compile and run the client:
  ```bash
  javac App.java
  java App
  ```

4. **Connect & Use**  
- Use the client GUI to connect to the server IP.
- Authenticate and start remote control or chat.
