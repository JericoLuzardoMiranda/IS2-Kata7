package software.ulpgc.ImageViewer.presenter;

import software.ulpgc.ImageViewer.model.Image;

public interface ImageDisplay {
    void show(Image image);
    Image image();
}
