package com.material.nominees;

import java.sql.Date;
import java.util.Objects;


public class NomineesEntity {
    private int id;
    private int voteId;
    private String nomineeName;
    private String nomineeImage;
    private String nomineesDescription;
    private Date creationDate;
    private int enabled;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }


    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }


    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }


    public String getNomineeImage() {
        return nomineeImage;
    }

    public void setNomineeImage(String nomineeImage) {
        this.nomineeImage = nomineeImage;
    }


    public String getNomineesDescription() {
        return nomineesDescription;
    }

    public void setNomineesDescription(String nomineesDescription) {
        this.nomineesDescription = nomineesDescription;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


}
