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

        System.out.println("������� ���� �� ������: list, info, create_dir, rename, copy, move or delete");

        switch (SCANNER.next()) {
            
            case "list": {
                System.out.println("������� ����: ");
                File f = new File(SCANNER.next());
                if (f.exists() && f.isDirectory()) {
                    String[] strings = f.list();
                    for (String string : strings) {
                        System.out.println(string);
                    }
                    break;
                } else {
                    System.out.println("������� �� ����������!");
                }
            }
            break;

            case "info": {
                System.out.println("������� ����: ");
                File f = new File(SCANNER.next());
                if (f.exists()) {
                    System.out.println("��� = " + f.getName());
                    System.out.println("���� = " + f.getPath());
                    System.out.println("����� ���������� ���� = " + f.isAbsolute());
                    System.out.println("������ = " + f.length() + " B");
                    Instant instantlf = Instant.ofEpochMilli(f.lastModified());
                    LocalDateTime dateTime = LocalDateTime.ofInstant(instantlf, ZoneId.systemDefault());
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy. HH:mm:ss");
                    System.out.println("����� = " + dateTime.format(dateTimeFormatter));
                    break;
                } else {
                    System.out.println("���� ��� ������� �� ����������!");
                }
            }
            break;

            case "create_dir": {
                System.out.println("������� ���� � ����� ��� ��� ��������: ");
                File f = new File(SCANNER.next());

                if (!f.exists()) {
                    f.mkdir();
                    System.out.println("������� " + f.getName() + " ������");
                    break;
                } else {
                    System.out.println("������� �� ����������!");
                }
            }
            break;

            case "rename": {
                System.out.println("������� ��� �������� ��� ���� � �����:");
                File f = new File(SCANNER.next());
                System.out.println("������� ��� �������� ��� ���� � ������ �����:");
                File f2 = new File(SCANNER.next());

                if (!f.exists()) {
                    System.out.println("���� �� ������!");
                    return;
                }
                if (f2.exists()) {
                    return;
                }
                f.renameTo(f2);
                System.out.println(f.getName() + " ������������ � " + f2.getName());
                break;
            }

            case "copy": {
                System.out.println("������� �����  �����: ");
                File f = new File(SCANNER.next());
                System.out.println("������� �������� �������������� �����: ");
                File f2 = new File(SCANNER.next());

                try (FileInputStream inStream = new FileInputStream(f);
                        FileOutputStream outStream = new FileOutputStream(f2)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inStream.read(buffer)) > 0) {
                        outStream.write(buffer, 0, length);
                    }
                    System.out.println("���� ������� ����������!");

                } catch (IOException exc) {
                    System.out.println(exc);
                }
                break;
            }

            case "move": {
                System.out.println("������� ���� � ��������� ��������: ");
                File f = new File(SCANNER.next());
                System.out.println("������� ���� � ��������� ��������: ");
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
                        System.out.println("������� ������������!");
                        f.delete();
                    }
                } else {
                    System.out.println(f + "  ������� �� ����������!");
                }
                break;
            }

            case "delete": {
                while (true) {
                    System.out.println("������� ���� � �����: ");
                    File f = new File(SCANNER.next());

                    if (f.exists()) {
                        f.delete();
                        System.out.println("������!");
                        break;
                    } else {
                        System.out.println("���������� ������� " + f.getName() + " ������ ��� ���� " + f.getName() 
                                + " �� ������.");
                    }
                }
                break;
            }

            default:
                System.out.println("�� ����� ������������ �������!");
        }
    }

}