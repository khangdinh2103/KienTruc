package compositepattern;

import java.util.ArrayList;
import java.util.List;

//Bạn được giao nhiệm vụ xây dựng một hệ thống quản lý thư mục và tập tin theo mô hình cây (tree structure). Trong hệ thống này:
//
//Một thư mục có thể chứa nhiều tập tin hoặc các thư mục con.
//Một tập tin chỉ có thể chứa dữ liệu, không thể chứa thư mục hoặc tập tin khác.
//Cả thư mục và tập tin đều có thể được hiển thị với thông tin của chúng.
interface FileComponent {
    void display();
    Integer totalSize();
}
class File implements FileComponent {

    String name;
    Integer Size;

    public File(Integer size, String name) {
        Size = size;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return Size;
    }

    public void setSize(Integer size) {
        Size = size;
    }

    @Override
    public void display() {
        System.out.println("Name: " + name );
    }

    @Override
    public Integer totalSize() {
        return Size;
    }
}
class Folder implements FileComponent {
    String name;
    Integer Size;
    List<FileComponent> fileComponent;

    public Folder(String name, Integer size, List<FileComponent> fileComponent) {
        this.name = name;
        Size = size;
        this.fileComponent = fileComponent;
    }
    public void addFile(File file) {
        fileComponent.add(file);
    }
    public void addFolder(Folder folder) {
        fileComponent.add(folder);
    }

    @Override
    public void display() {
        for (FileComponent fileComponent : fileComponent) {
            fileComponent.display();
        }


    }

    @Override
    public Integer totalSize() {
        Integer totalSize = 0;
        for (FileComponent fileComponent : fileComponent) {
           totalSize += fileComponent.totalSize();
        }
        return totalSize;
    }
}
public class Ongk {
    public static void main(String[] args) {
        File file1 = new File(6, "1");
        File file2 = new File(6, "2");
        Folder folder = new Folder("khang", 5, new ArrayList<FileComponent>());
        folder.addFile(file1);
        folder.addFile(file2);
        folder.display();
        System.out.println(folder.totalSize());
    }
}
