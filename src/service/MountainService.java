package service;

import model.Mountain;
import repository.MountainRepository;

import java.util.Collection;
import java.util.Map;

public class MountainService {
    private final MountainRepository mountainRepository;
    private final Map<String, Mountain> mountainMap;

    public MountainService() {
        this.mountainRepository = new MountainRepository();
        this.mountainMap = mountainRepository.findAll();
    }

    public Mountain get(String mountainCode) {
        return mountainMap.get(mountainCode.toUpperCase());
    }

    public boolean isValidMountainCode(String mountainCode) {
        return mountainMap.containsKey(mountainCode.toUpperCase());
    }

    public Collection<Mountain> getAllMountains() {
        return mountainMap.values();
    }
}
