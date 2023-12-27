package software.ulpgc.ImageViewer.presenter;

import software.ulpgc.ImageViewer.model.FileImageLoader;
import software.ulpgc.ImageViewer.model.Image;
import software.ulpgc.ImageViewer.view.MainFrame;

import java.io.File;

public class Main {
    private static final String ROOT_DIRECTORY = "C:/Users/jeric/IdeaProjects/IS2-ImageViewer/pictures";

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        Image image = new FileImageLoader(new File(ROOT_DIRECTORY)).load();

        if (image != null) {
            frame.imageDisplay().show(image);
            frame.add("<", new PreviousImageCommand(frame.imageDisplay()));
            frame.add(">", new NextImageCommand(frame.imageDisplay()));
            frame.setVisible(true);
        } else {
            System.out.println("No image files found in the specified directory.");
        }

    }
}