package edu.project3.models;

public record Request(RequestType type, String uri, String protocol, String userAgent) {}
