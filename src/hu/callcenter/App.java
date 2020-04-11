package hu.callcenter;

import hu.callcenter.controller.CallService;
import hu.callcenter.domain.model.LogTime;
import hu.callcenter.domain.service.*;

import java.util.Scanner;

public class App {

    private final CallService callService;
    private final Console console;
    private final DataParser dataParser;
    private final FileWriter fileWriter;

    private App() {
        console = new Console(new Scanner(System.in));
        dataParser = new DataParser();
        fileWriter = new FileWriter("sikeres.txt");
        DataApi dataApi = new DataApi(new FileReader(), dataParser);
        callService = new CallService(dataApi.getData("hivas.txt"));
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("3. feladat");
        System.out.println(callService.getCallStatisticByHour());
        System.out.println("4. feladat");
        System.out.println(callService.getLongestCallDetails());
        System.out.println("5. feladat");
        System.out.print("Adjon meg egy időpontot! (óra perc másodperc): ");
        LogTime time = dataParser.parseTime(console.read());
        System.out.println(callService.getActualCallerDetails(time));
        System.out.println("6. feladat");
        System.out.println(callService.getLastHappyCallerDetails());
        fileWriter.writeAll(callService.getSpeakersList());
    }
}
