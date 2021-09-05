package com.mastercard.evaluation.bin.range.events.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuditEvent<T> implements Event {

    private Date createdAt;
    private T before;
    private T after;

    public AuditEvent(T before,T after){
        this.createdAt = new Date();
        this.before = before;
        this.after = after;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        before = before != null ? before : null;
        after = after != null ? after : null;

        return "createdAt = {" + createdAt + "}, before = {" + before + "}, after = {" + after + "}";
    }
}
