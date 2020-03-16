package lesson03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FileManager {

	private static final Scanner SCANNER = new Scanner(System.in);
	
    public static void main(String[] args) {

        System.out.println("Введите одну из команд: list, info, create_dir, rename, copy, move or delete");

        switch (SCANNER.next()) {
            
            case "list": {
                System.out.println("Введите путь: ");
                File f = new File(SCANNER.next());
                if (f.exists() && f.isDirectory()) {
                    String[] strings = f.list();
                    for (String string : strings) {
                        System.out.println(string);
                    }
                    break;
                } else {
                    System.out.println("Каталог не существует!");
                }
            }
            break;

            case "info": {
                System.out.println("Введите путь: ");
                File f = new File(SCANNER.next());
                if (f.exists()) {
                    System.out.println("Имя = " + f.getName());
                    System.out.println("Путь = " + f.getPath());
                    System.out.println("Имеет абсолютный путь = " + f.isAbsolute());
                    System.out.println("Размер = " + f.length() + " B");
                    Instant instantlf = Instant.ofEpochMilli(f.lastModified());
                    LocalDateTime dateTime = LocalDateTime.ofInstant(instantlf, ZoneId.systemDefault());
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy. HH:mm:ss");
                    System.out.println("Время = " + dateTime.format(dateTimeFormatter));
                    break;
                } else {
                    System.out.println("Файл или каталог ен существуют!");
                }
            }
            break;

            case "create_dir": {
                System.out.println("Введите путь к файлу или имя каталога: ");
                File f = new File(SCANNER.next());

                if (!f.exists()) {
                    f.mkdir();
                    System.out.println("Каталог " + f.getName() + " создан");
                    break;
                } else {
                    System.out.println("Каталог не существует!");
                }
            }
            break;

            case "rename": {
                System.out.println("Введите имя каталога или путь к файлу:");
                File f = new File(SCANNER.next());
                System.out.println("Введите имя каталога или путь к новому файлу:");
                File f2 = new File(SCANNER.next());

                if (!f.exists()) {
                    System.out.println("Файл не найден!");
                    return;
                }
                if (f2.exists()) {
                    return;
                }
                f.renameTo(f2);
                System.out.println(f.getName() + " переименован в " + f2.getName());
                break;
            }

            case "copy": {
                System.out.println("Введите путьк  файлу: ");
                File f = new File(SCANNER.next());
                System.out.println("Введите конечное местоположение файла: ");
                File f2 = new File(SCANNER.next());

                try (FileInputStream inStream = new FileInputStream(f);
                        FileOutputStream outStream = new FileOutputStream(f2)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inStream.read(buffer)) > 0) {
                        outStream.write(buffer, 0, length);
                    }
                    System.out.println("Файл успешно скопирован!");

                } catch (IOException exc) {
                    System.out.println(exc);
                }
                break;
            }

            case "move": {
                System.out.println("Введите путь к исходному каталогу: ");
                File f = new File(SCANNER.next());
                System.out.println("Введите путь к конечному каталогу: ");
                File f2 = new File(SCANNER.next());

                if (!f.exists()) {
                    f2.mkdir();
                }

                if (f.exists() && f.isDirectory()) {
                    File[] listOfFiles = f.listFiles();

                    if (listOfFiles != null) {
                        for (File child : listOfFiles) {
                            child.renameTo(new File(f2 + "\\" + child.getName()));       
                        }
                        System.out.println("Каталог переименован!");
                        f.delete();
                    }
                } else {
                    System.out.println(f + "  Каталог не существует!");
                }
                break;
            }

            case "delete": {
                while (true) {
                    System.out.println("Введите путь к файлу: ");
                    File f = new File(SCANNER.next());

                    if (f.exists()) {
                        f.delete();
                        System.out.println("Удален!");
                        break;
                    } else {
                        System.out.println("Невозможно удалить " + f.getName() + " потому что файл " + f.getName() 
                                + " не найден.");
                    }
                }
                break;
            }

            default:
                System.out.println("Вы ввели некоррекнтую команду!");
        }
    }

}