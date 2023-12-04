package qqcommon;

import java.io.Serializable;

/**
 * @author Zhiqian Tan
 * @version 1.0
 */
public class Message implements Serializable, MessageType {
    private static final long serialVersionUID = 1L;
    private String sender;
    private String receiver;
    private String time;
    private String content;
    private String messageType;
    private String filePath;
    private byte[] fileContent;
    private long fileLength;

    public String getFilePath() {
        return filePath;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public Message(String sender, String receiver, String time, String content, String messageType, byte[] fileContent, long fileLength) {
        this.sender = sender;
        this.receiver = receiver;
        this.time = time;
        this.content = content;
        this.messageType = messageType;
        this.fileContent = fileContent;
        this.fileLength = fileLength;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Message(String sender, String receiver, String time, String content, String messageType, String filePath) {
        this.sender = sender;
        this.receiver = receiver;
        this.time = time;
        this.content = content;
        this.messageType = messageType;
        this.filePath = filePath;
    }

    public Message(String sender, String receiver, String time, String content, String messageType) {
        this.sender = sender;
        this.receiver = receiver;
        this.time = time;
        this.content = content;
        this.messageType = messageType;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Message() {
    }

    public Message(String sender, String content, String messageType) {
        this.sender = sender;
        this.content = content;
        this.messageType = messageType;
    }

    public Message(String sender, String messageType) {
        this.sender = sender;
        this.messageType = messageType;
    }

    public Message(String sender, String receiver, String time, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.time = time;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}