package edu.hw1.task1;

public final class VideoLength {
    private static final int SECONDS_IN_MINUTE = 60;

    private VideoLength() {
    }

    public static int videoLengthInSeconds(String time) {
        if (time != null && !time.isEmpty()) {
            String[] minutesAndSeconds = time.split(":");
            String stringMinutes = minutesAndSeconds[0];
            String stringSeconds = minutesAndSeconds[1];
            if (minutesAndSeconds.length == 2 && stringMinutes.length() >= 2 && stringSeconds.length() == 2) {
                int minutes;
                int seconds;
                try {
                    minutes = Integer.parseInt(stringMinutes);
                    seconds = Integer.parseInt(stringSeconds);
                } catch (NumberFormatException e) {
                    return -1;
                }
                if (seconds < SECONDS_IN_MINUTE && seconds >= 0 && minutes >= 0) {
                    return minutes * SECONDS_IN_MINUTE + seconds;
                }
            }
        }
        return -1;
    }
}
