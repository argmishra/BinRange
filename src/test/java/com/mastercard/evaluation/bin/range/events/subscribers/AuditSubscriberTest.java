package com.mastercard.evaluation.bin.range.events.subscribers;

import com.mastercard.evaluation.bin.range.events.models.AuditEvent;
import com.mastercard.evaluation.bin.range.models.BinRangeInfo;
import com.mastercard.evaluation.bin.range.models.BinRangeInfoTest;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AuditSubscriberTest {

    private AuditSubscriber auditSubscriber = mock(AuditSubscriber.class);

    @Test
    public void handleEvent_pass() {
        BinRangeInfo binRangeInfo = BinRangeInfoTest.generateTestEntity();

        BinRangeInfo newBinRangeInfo = BinRangeInfoTest.generateTestEntity();
        newBinRangeInfo.setRef(binRangeInfo.getRef());

        AuditEvent auditEvent = new AuditEvent(binRangeInfo,newBinRangeInfo);

        auditSubscriber.handleEvent(auditEvent);
        verify(auditSubscriber, times(1)).handleEvent((auditEvent));
    }
}