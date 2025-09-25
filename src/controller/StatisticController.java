package controller;

import model.Statistic;
import repository.MountainRepository;
import repository.StudentRepository;
import service.StatisticsService;
import view.ConsoleView;

import java.util.List;

public class StatisticController {
    private final StudentRepository studentRepository = new StudentRepository();
    private final MountainRepository mountainRepository = new MountainRepository();
    private final StatisticsService statisticsService = new StatisticsService();
    private final ConsoleView consoleView = new ConsoleView();

    public void showStatistics() {
        List<Statistic> statistics = statisticsService.statisticalizeByMountain(
                studentRepository.load(),
                new java.util.ArrayList<>(mountainRepository.findAll().values())
        );
        consoleView.printStatistics(statistics);
    }
}
