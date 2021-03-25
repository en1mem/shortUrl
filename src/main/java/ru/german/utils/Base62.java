package ru.german.utils;

public class Base62 {

    //first impl
    public static String convertToBase62(long toBeConverted) {
        String[] elements = {
                "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
                "p","q","r","s","t","u","v","w","x","y","z","1","2","3","4",
                "5","6","7","8","9","0","A","B","C","D","E","F","G","H","I",
                "J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X",
                "Y","Z"
        };
        StringBuilder convertedString = new StringBuilder();
        int numOfDiffChars = elements.length;

        if (toBeConverted < numOfDiffChars + 1 && toBeConverted > 0) {
            convertedString = new StringBuilder(elements[(int) (toBeConverted - 1)]);
        } else if (toBeConverted > numOfDiffChars) {
            long mod = 0;
            long multiplier;
            boolean determinedTheLength = false;
            int lengthOfUrlCode = 6;

            for (int j = lengthOfUrlCode; j >= 0; j--) {
                multiplier = (long) (toBeConverted / Math.pow(numOfDiffChars, j));

                if (multiplier > 0 && toBeConverted >= numOfDiffChars) {
                    convertedString.append(elements[(int) multiplier]);
                    determinedTheLength = true;
                } else if (determinedTheLength && multiplier == 0) {
                    convertedString.append(elements[0]);
                } else if (toBeConverted < numOfDiffChars) {
                    convertedString.append(elements[(int) mod]);
                }

                mod = (long) (toBeConverted % Math.pow(numOfDiffChars, j));
                toBeConverted = mod;
            }

        }
        return convertedString.toString();
    }
}
