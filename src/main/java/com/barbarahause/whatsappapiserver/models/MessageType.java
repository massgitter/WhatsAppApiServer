package com.barbarahause.whatsappapiserver.models;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum MessageType {
    TEXT_MESSAGE("TEXT_MESSAGE"),
    VIDEO_MESSAGE("VIDEO_MESSAGE"),
    ATTACHMENT_MESSAGE("ATTACHMENT_MESSAGE");

    private final String code;

    MessageType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
