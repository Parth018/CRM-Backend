package com.parth.crm.models;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
    NEW,
    CONTACTED,
    QUALIFIED,
    CLOSED
}