package edu.project3.inputReader;

import edu.project3.models.ArgumentType;
import java.util.Map;

public interface Reader {
    Map<ArgumentType, String> getArguments(String[] args);
}
