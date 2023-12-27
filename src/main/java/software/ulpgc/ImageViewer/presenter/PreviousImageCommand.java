package software.ulpgc.ImageViewer.presenter;

import software.ulpgc.ImageViewer.presenter.Command;
import software.ulpgc.ImageViewer.presenter.ImageDisplay;

public class PreviousImageCommand implements Command {
    private final ImageDisplay imageDisplay;

    public PreviousImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        imageDisplay.show(imageDisplay.image().prev());
    }
}
