package com.mastercard.evaluation.bin.range.controllers;

import com.google.common.base.Preconditions;
import com.mastercard.evaluation.bin.range.events.EventManager;
import com.mastercard.evaluation.bin.range.events.models.AuditEvent;
import com.mastercard.evaluation.bin.range.models.BinRangeInfo;
import com.mastercard.evaluation.bin.range.services.BinRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("binRangeInfoCache")
@Validated
public class BinRangeInfoCacheController {

    private final BinRangeService binRangeService;
    private final EventManager eventManager;

    @Autowired
    public BinRangeInfoCacheController(BinRangeService binRangeService, EventManager eventManager) {
        Preconditions.checkNotNull(binRangeService, "binRangeService cannot be null.");
        this.binRangeService = binRangeService;
        this.eventManager = eventManager;
    }

    @PostMapping
    public ResponseEntity<BinRangeInfo> createBinRangeInfo(@RequestBody @Valid BinRangeInfo binRangeInfo) {
        binRangeInfo = binRangeService.createBinRangeInfo(binRangeInfo);

        eventManager.publishAsync(new AuditEvent(null, binRangeInfo));

        return new ResponseEntity<>(binRangeInfo, HttpStatus.CREATED);
    }

    @PutMapping("{ref}")
    public ResponseEntity<BinRangeInfo> updateBinRangeInfo(@PathVariable("ref") UUID ref, @RequestBody @Valid BinRangeInfo binRangeInfo) {
        BinRangeInfo foundBinRange = binRangeService.getBinRangeInfo(ref);
        binRangeService.updateBinRangeInfo(ref, binRangeInfo);
        BinRangeInfo updatedFoundBinRange = binRangeService.getBinRangeInfo(ref);

        eventManager.publishAsync(new AuditEvent(foundBinRange, updatedFoundBinRange));

        return new ResponseEntity<>(binRangeInfo, HttpStatus.OK);
    }

    @DeleteMapping("{ref}")
    public ResponseEntity<Void> deleteBinRangeInfo(@PathVariable("ref") UUID ref) {
        BinRangeInfo binRangeInfo = binRangeService.getBinRangeInfo(ref);
        binRangeService.deleteBinRangeInfo(binRangeInfo);

        eventManager.publishAsync(new AuditEvent(binRangeInfo,null));

        return ResponseEntity.noContent().build();
    }

}
