package software.ulpgc.ImageViewer.presenter;

import software.ulpgc.ImageViewer.presenter.Command;
import software.ulpgc.ImageViewer.presenter.ImageDisplay;

public class NextImageCommand implements Command {
    private final ImageDisplay imageDisplay;

    public NextImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        imageDisplay.show(imageDisplay.image().next());
    }
}
