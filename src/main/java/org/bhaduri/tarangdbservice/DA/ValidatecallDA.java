/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.tarangdbservice.DA;
import jakarta.persistence.EntityManagerFactory;
import org.bhaduri.tarangdbservice.JPA.ValidatecallJpaController;

/**
 *
 * @author sb
 */
public class ValidatecallDA extends ValidatecallJpaController{
    public ValidatecallDA(EntityManagerFactory emf) {
        super(emf);
    }
    
}
