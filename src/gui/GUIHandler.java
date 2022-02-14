package gui;

import java.util.ArrayList;
import java.util.List;

public class GUIHandler {

    private List<GuiTexture> guis = new ArrayList<>();

    public void addGui(GuiTexture t) {
        guis.add(t);
    }

    public GuiTexture getGui(int index) {
        return guis.get(index);
    }

    public List<GuiTexture> getGuis() {
        return guis;
    }

}
