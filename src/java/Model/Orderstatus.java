/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Cao Duy Quân
 */
public class Orderstatus {
    String status_id,status_name;

    public Orderstatus(String status_id, String status_name) {
        this.status_id = status_id;
        this.status_name = status_name;
    }

    public Orderstatus() {
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
    
}
