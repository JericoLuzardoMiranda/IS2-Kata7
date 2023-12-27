package software.ulpgc.ImageViewer.model;

import software.ulpgc.ImageViewer.presenter.ImageLoader;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Set;

public class FileImageLoader implements ImageLoader {
    private final File[] files;
    private int currentIndex;
    private static final Set<String> imageExtensions = Set.of(".jpg", ".png");

    public FileImageLoader(File folder) {
        this.files = folder.listFiles(isImage());
        this.currentIndex = 0;
    }

    private static FilenameFilter isImage() {
        return (dir, name) -> imageExtensions.stream().anyMatch(name::endsWith);
    }

    @Override
    public Image load() {
        if (files != null && files.length > 0) {
            return imageAt(currentIndex);
        } else {
            // Handle the case when no image files are found
            return null;
        }
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String name() {
                return files[i].getAbsolutePath();
            }

            @Override
            public Image next() {
                currentIndex = (currentIndex + 1) % files.length;
                return imageAt(currentIndex);
            }

            @Override
            public Image prev() {
                currentIndex = (currentIndex - 1 + files.length) % files.length;
                return imageAt(currentIndex);
            }
        };
    }
}
