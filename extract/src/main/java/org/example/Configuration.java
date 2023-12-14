package org.example;

import java.sql.Date;

public class Configuration {
    private int id;
    private String name;
    private String status;
    private int flag;
    private String description;
    private String sourcePath;
    private String location;
    private String format;
    private String separator;
    private String columnsNameStagingTemp;
    private String typeColumnsStagingTemp;
    private String databaseNameStaging;
    private String databaseNameWarehouse;
    private String databaseNameDatamart;
    private String serverName;
    private String port;
    private String username;
    private String password;
    private String destination;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;


    // Constructors, getters, and setters

    public Configuration() {
        // Default constructor
    }

    public Configuration(String sourcePath, String location, String format, String separator) {
        this.sourcePath = sourcePath;
        this.location = location;
        this.format = format;
        this.separator = separator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getColumnsNameStagingTemp() {
        return columnsNameStagingTemp;
    }

    public void setColumnsNameStagingTemp(String columnsNameStagingTemp) {
        this.columnsNameStagingTemp = columnsNameStagingTemp;
    }

    public String getTypeColumnsStagingTemp() {
        return typeColumnsStagingTemp;
    }

    public void setTypeColumnsStagingTemp(String typeColumnsStagingTemp) {
        this.typeColumnsStagingTemp = typeColumnsStagingTemp;
    }

    public String getDatabaseNameStaging() {
        return databaseNameStaging;
    }

    public void setDatabaseNameStaging(String databaseNameStaging) {
        this.databaseNameStaging = databaseNameStaging;
    }

    public String getDatabaseNameWarehouse() {
        return databaseNameWarehouse;
    }

    public void setDatabaseNameWarehouse(String databaseNameWarehouse) {
        this.databaseNameWarehouse = databaseNameWarehouse;
    }

    public String getDatabaseNameDatamart() {
        return databaseNameDatamart;
    }

    public void setDatabaseNameDatamart(String databaseNameDatamart) {
        this.databaseNameDatamart = databaseNameDatamart;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}