package com.trending;

public class Trending {
    private String profilePic;
    private String title;
    private String owner;
    private String description;
    private int descriptionType;
    private String time;


    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDescriptionType() {
        return descriptionType;
    }

    public void setDescriptionType(int descriptionType) {
        this.descriptionType = descriptionType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
