package com.example.genCodeEmployee.bean;

public class GenCodeEmployeeBean {

    private Long       genCodeId;
    private Long       idCode;


    /**
     * Set the "genCodeId" field value
     * @param genCodeId
     */
    public void setGenCodeId(Long genCodeId) {
        this.genCodeId = genCodeId;
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
