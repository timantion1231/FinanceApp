/*import org.example.Manager;

import java.io.*;

public class SerializationTest {
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.signUp("test", "password");

        // Сериализация
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("manager.dat"))) {
            oos.writeObject(manager);
            System.out.println("Сериализация успешна");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Десериализация
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("manager.dat"))) {
            Manager deserializedManager = (Manager) ois.readObject();
            System.out.println("Десериализация успешна");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}*/