package edu.project3.models;

import java.time.OffsetDateTime;

public record Log(String remoteAddress, String remoteUser, OffsetDateTime localTime, Request request,
                  Response response, String referer) {}
