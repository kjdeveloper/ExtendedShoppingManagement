package jankowiak.kamil.service;

import jankowiak.kamil.exceptions.AppException;

import java.util.Scanner;

public class UserDataService {
    private Scanner sc = new Scanner(System.in);

    public int getInt(String message) {
        System.out.println(message);

        String text = sc.nextLine();
        if (!text.matches("\\d+")) {
            throw new AppException("INT VALUE IS NOT CORRECT: " + text);
        }
        return Integer.parseInt(text);
    }

    public void close() {
        if (sc != null) {
            sc.close();
            sc = null;
        }
    }
}
