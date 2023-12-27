package software.ulpgc.ImageViewer.model;

public interface Image {
    String name();
    Image next();
    Image prev();
}
