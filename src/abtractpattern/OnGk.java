package abtractpattern;
interface Book{
    void create();
}
class ConcreteBookPaper implements Book{

    @Override
    public void create() {
        System.out.println("Creating Book Paper");
    }
}
class ConcreteBookSpeak implements Book{

    @Override
    public void create() {
        System.out.println("Creating Book speak");
    }
}
abstract class GUI {
    public abstract Book createBook();
}
class CompanyBookPaper extends GUI{

    @Override
    public Book createBook() {
        return new ConcreteBookPaper();
    }


}
 class CompanyBookSpeak extends GUI{

    @Override
    public Book createBook() {
        return new ConcreteBookSpeak();
    }
}
public class OnGk {
    public static void main(String[] args) {
        GUI gui = new CompanyBookPaper();
        Book book = gui.createBook();
        book.create();
        GUI gui2 = new CompanyBookSpeak();
        Book book2 = gui2.createBook();
        book2.create();
    }
}
