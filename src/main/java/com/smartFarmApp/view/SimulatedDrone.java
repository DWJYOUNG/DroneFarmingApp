package com.smartFarmApp.view;

import java.io.IOException;

public interface SimulatedDrone {
    void scanFarm() throws InterruptedException, IOException;
    void visitItem(int dispX, int dispY) throws InterruptedException, IOException;
}
