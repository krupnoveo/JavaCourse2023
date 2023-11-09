package edu.hw4;

public record ValidationError(TypeOfError error, String message) {

    enum TypeOfError {
        AGE("Age must not be less than 0"),
        HEIGHT("Height must not be less than 0"),
        WEIGHT("Weight must not be less than 0"),
        NAME("Name must not be empty or null");
        private String text;

        public String getText() {
            return text;
        }

        TypeOfError(String text) {
            this.text = text;
        }
    }
}
