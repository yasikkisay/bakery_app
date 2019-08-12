package com.bakery.test.models.inputs;

public class NewCakeInputParams {

    private String cakeTitleInput;
    private int freshDuration;

    public NewCakeInputParams(String cakeTitleInput, int freshDuration) {
        this.cakeTitleInput = cakeTitleInput;
        this.freshDuration = freshDuration;
    }

    public String getCakeTitleInput() {
        return cakeTitleInput;
    }

    public void setCakeTitleInput(String cakeTitleInput) {
        this.cakeTitleInput = cakeTitleInput;
    }

    public int getFreshDuration() {
        return freshDuration;
    }

    public void setFreshDuration(int freshDuration) {
        this.freshDuration = freshDuration;
    }
}
