import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class Synchronizator {
    public static void main(String[] args) throws IOException, InterruptedException {
        Set<String> listA = new TreeSet<>();
        Set<String> listB = new TreeSet<>();

        Path pathA = Paths.get("C:\\Users\\Klosiek\\Desktop\\Laby\\Podstawy Programowania Obiektowego\\Synchronizator\\folder1");
        Path pathB = Paths.get("C:\\Users\\Klosiek\\Desktop\\Laby\\Podstawy Programowania Obiektowego\\Synchronizator\\folder2");


        final File dirA = pathA.toFile();
        final File dirB = pathB.toFile();

        while (true) {
            listA.clear();
            for (final File fileEntry : Objects.requireNonNull(dirA.listFiles())) {
                String f1_file = fileEntry.getName();
                if (!listA.contains(f1_file)) {
                    listA.add(f1_file);
                }
                if (!listB.contains(f1_file)) {
                    System.out.println("Skopiowano plik " + f1_file + ".");
                    listA.add(f1_file);
                    try {
                        Files.copy(Paths.get(dirA.toString(), f1_file),
                                Paths.get(dirB.toString(), f1_file),
                                StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            for (final File fileEntry2 : Objects.requireNonNull(dirB.listFiles())) {
                    String file2 = fileEntry2.getName();
                    if (!listB.contains(file2)) {
                        listB.add(file2);
                    }
                    if (!listA.contains(file2)){
                        listB.remove(file2);
                        Files.delete(Paths.get(pathB.toString(), file2));
                    }
            }
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
