package controller;

import service.MountainService;
import view.ConsoleView;

public class MountainController {
    private final MountainService mountainServiceService;
    private final ConsoleView view;

    public MountainController(MountainService mountainServiceService, ConsoleView view) {
        this.mountainServiceService = mountainServiceService;
        this.view = view;
    }

    public void displayMountains() {
        if(!mountainServiceService.getAllMountains().isEmpty()){
            view.displayMountains(mountainServiceService.getAllMountains());
        }else System.out.println("No mountain have registered yet.");
    }
}
