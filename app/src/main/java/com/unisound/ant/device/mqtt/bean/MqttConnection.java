package com.unisound.ant.device.mqtt.bean;

public class MqttConnection {
    private String ip;
    private int keepAlive;
    private String password;
    private int port;
    private String protocol;
    private String ssl;
    private String sslPassword;
    private String username;

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip2) {
        this.ip = ip2;
    }

    public int getKeepAlive() {
        return this.keepAlive;
    }

    public void setKeepAlive(int keepAlive2) {
        this.keepAlive = keepAlive2;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password2) {
        this.password = password2;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port2) {
        this.port = port2;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public void setProtocol(String protocol2) {
        this.protocol = protocol2;
    }

    public String getSsl() {
        return this.ssl;
    }

    public void setSsl(String ssl2) {
        this.ssl = ssl2;
    }

    public String getSslPassword() {
        return this.sslPassword;
    }

    public void setSslPassword(String sslPassword2) {
        this.sslPassword = sslPassword2;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username2) {
        this.username = username2;
    }

    public String toString() {
        return "MqttConnection{ip='" + this.ip + '\'' + ", keepAlive=" + this.keepAlive + ", password='" + this.password + '\'' + ", port=" + this.port + ", protocol='" + this.protocol + '\'' + ", ssl='" + this.ssl + '\'' + ", sslPassword='" + this.sslPassword + '\'' + ", username='" + this.username + '\'' + '}';
    }
}
