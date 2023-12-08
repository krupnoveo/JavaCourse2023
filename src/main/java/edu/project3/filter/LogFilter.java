package edu.project3.filter;

import edu.project3.models.Log;

public interface LogFilter {
    boolean isMatchingTheFilter(Log log);
}
