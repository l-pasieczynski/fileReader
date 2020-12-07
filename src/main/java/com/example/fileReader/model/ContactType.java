package com.example.fileReader.model;

public enum ContactType {
    UNKNOWN(0),
    EMAIL(1),
    PHONE(2),
    JABBER(3);

    private int contactType;

    ContactType(int type){
        contactType = type;
    }

    public int contactType (){
        return contactType;
    }
}
