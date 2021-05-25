package com.example.genCodeEmployee.bo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gen_code_employee")
public class GenCodeEmployeeBO {

    @Id
    @Column(name = "gen_code_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genCodeId;

    @Column(name = "id_code")
    private Long idCode;


    /**
     * Set the "genCodeId" field value
     * @param genCodeId
     */
    public void setGenCodeId( Long genCodeId ) {
        this.genCodeId = genCodeId ;
    }

    /**
     * Get the "genCodeId" field value
     * @return the field value
     */
    public Long getGenCodeId() {
        return this.genCodeId;
    }

    
    public Long getIdCode() {
        return idCode;
    }

    
    public void setIdCode(Long idCode) {
        this.idCode = idCode;
    }
}
