package com.mastercard.evaluation.bin.range.events.models;

import com.mastercard.evaluation.bin.range.models.BinRangeInfo;
import com.mastercard.evaluation.bin.range.models.BinRangeInfoTest;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AuditEventTest {

    @Test
    public void audit_event_create() {
        BinRangeInfo binRangeInfo = BinRangeInfoTest.generateTestEntity();

        Date date = new Date();
        AuditEvent create = new AuditEvent(null,binRangeInfo);
        create.setCreatedAt(date);

        assertEquals(createString(date.toString(), null, binRangeInfo.toString()), create.toString());
    }

    @Test
    public void audit_event_update() {
        BinRangeInfo binRangeInfo = BinRangeInfoTest.generateTestEntity();

        Date date = new Date();
        AuditEvent update = new AuditEvent(binRangeInfo.toString(),binRangeInfo);
        update.setCreatedAt(date);

        assertEquals(createString(date.toString(), binRangeInfo.toString(), binRangeInfo.toString()), update.toString());
    }

    @Test
    public void audit_event_delete() {
        BinRangeInfo binRangeInfo = BinRangeInfoTest.generateTestEntity();

        Date date = new Date();
        AuditEvent delete = new AuditEvent(binRangeInfo, null);
        delete.setCreatedAt(date);

        assertEquals(createString(date.toString(), binRangeInfo.toString(), null), delete.toString());
    }

    private String createString(String date, String before, String after){
        return "createdAt = {" + date + "}, before = {" + before + "}, after = {" + after + "}";
    }
}